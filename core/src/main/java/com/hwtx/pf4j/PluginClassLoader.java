/*
 * Copyright (C) 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hwtx.pf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * One instance of this class should be created by plugin manager for every available plug-in.
 * By default, this class loader is a Parent Last ClassLoader - it loads the classes from the plugin's jars
 * before delegating to the parent class loader.
 * Use {@link #parentFirst} to change the loading strategy.
 *
 * @author Decebal Suiu
 */
public class PluginClassLoader extends URLClassLoader {

    private static final Logger log = LoggerFactory.getLogger(PluginClassLoader.class);

    private static final String JAVA_PACKAGE_PREFIX = "java.";
    private static final String PLUGIN_PACKAGE_PREFIX = "org.pf4j.";

    private PluginManager pluginManager;
    private PluginDescriptor pluginDescriptor;
    private boolean parentFirst;

    public PluginClassLoader(PluginManager pluginManager, PluginDescriptor pluginDescriptor, ClassLoader parent) {
        this(pluginManager, pluginDescriptor, parent, false);
    }

    /**
     * If {@code parentFirst} is {@code true}, indicates that the parent {@link ClassLoader} should be consulted
     * before trying to load the a class through this loader.
     */
    public PluginClassLoader(PluginManager pluginManager, PluginDescriptor pluginDescriptor, ClassLoader parent, boolean parentFirst) {
        super(new URL[0], parent);

        this.pluginManager = pluginManager;
        this.pluginDescriptor = pluginDescriptor;
        this.parentFirst = parentFirst;
    }

    @Override
    public void addURL(URL url) {
        log.debug("Add '{}'", url);
        super.addURL(url);
    }

    public void addFile(File file) {
        try {
            addURL(file.getCanonicalFile().toURI().toURL());
        } catch (IOException e) {
//            throw new RuntimeException(e);
            log.error(e.getMessage(), e);
        }
    }

    /**
     * By default, it uses a child first delegation model rather than the standard parent first.
     * If the requested class cannot be found in this class loader, the parent class loader will be consulted
     * via the standard {@link ClassLoader#loadClass(String)} mechanism.
     * Use {@link #parentFirst} to change the loading strategy.
     */
    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(className)) {
            // first check whether it's a system class, delegate to the system loader
            if (className.startsWith(JAVA_PACKAGE_PREFIX)) {
                return findSystemClass(className);
            }

            // if the class is part of the plugin engine use parent class loader
            if (className.startsWith(PLUGIN_PACKAGE_PREFIX) && !className.startsWith("org.pf4j.demo")) {
//                log.trace("Delegate the loading of PF4J class '{}' to parent", className);
                return getParent().loadClass(className);
            }

            log.trace("Received request to load class '{}'", className);

            // second check whether it's already been loaded
            Class<?> loadedClass = findLoadedClass(className);
            if (loadedClass != null) {
                log.trace("Found loaded class '{}'", className);
                return loadedClass;
            }

            if (!parentFirst) {
                // nope, try to load locally
                try {
                    loadedClass = findClass(className);
                    log.trace("Found class '{}' in plugin classpath", className);
                    return loadedClass;
                } catch (ClassNotFoundException e) {
                    // try next step
                }

                // look in dependencies
                loadedClass = loadClassFromDependencies(className);
                if (loadedClass != null) {
                    log.trace("Found class '{}' in dependencies", className);
                    return loadedClass;
                }

                log.trace("Couldn't find class '{}' in plugin classpath. Delegating to parent", className);

                // use the standard ClassLoader (which follows normal parent delegation)
                return super.loadClass(className);
            } else {
                // try to load from parent
                try {
                    return super.loadClass(className);
                } catch (ClassCastException e) {
                    // try next step
                }

                log.trace("Couldn't find class '{}' in parent. Delegating to plugin classpath", className);

                // nope, try to load locally
                try {
                    loadedClass = findClass(className);
                    log.trace("Found class '{}' in plugin classpath", className);
                    return loadedClass;
                } catch (ClassNotFoundException e) {
                    // try next step
                }

                // look in dependencies
                loadedClass = loadClassFromDependencies(className);
                if (loadedClass != null) {
                    log.trace("Found class '{}' in dependencies", className);
                    return loadedClass;
                }

                throw new ClassNotFoundException(className);
            }
        }
    }

    /**
     * Load the named resource from this plugin.
     * By default, this implementation checks the plugin's classpath first then delegates to the parent.
     * Use {@link #parentFirst} to change the loading strategy.
     *
     * @param name the name of the resource.
     * @return the URL to the resource, {@code null} if the resource was not found.
     */
    @Override
    public URL getResource(String name) {
        log.trace("Received request to load resource '{}'", name);
        if (!parentFirst) {
            URL url = findResource(name);
            if (url != null) {
                log.trace("Found resource '{}' in plugin classpath", name);
                return url;
            }

            log.trace("Couldn't find resource '{}' in plugin classpath. Delegating to parent", name);

            return super.getResource(name);
        } else {
            URL url = super.getResource(name);
            if (url != null) {
                log.trace("Found resource '{}' in parent", name);
                return url;
            }

            log.trace("Couldn't find resource '{}' in parent", name);

            return findResource(name);
        }
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        if (!parentFirst) {
            List<URL> resources = new ArrayList<>();

            resources.addAll(Collections.list(findResources(name)));

            if (getParent() != null) {
                resources.addAll(Collections.list(getParent().getResources(name)));
            }

            return Collections.enumeration(resources);
        } else {
            return super.getResources(name);
        }
    }

    private Class<?> loadClassFromDependencies(String className) {
        log.trace("Search in dependencies for class '{}'", className);
        List<PluginDependency> dependencies = pluginDescriptor.getDependencies();
        for (PluginDependency dependency : dependencies) {
            ClassLoader classLoader = pluginManager.getPluginClassLoader(dependency.getPluginId());

            // If the dependency is marked as optional, its class loader might not be available.
            if (classLoader == null && dependency.isOptional()) {
                continue;
            }

            try {
                return classLoader.loadClass(className);
            } catch (ClassNotFoundException e) {
                // try next dependency
            }
        }

        return null;
    }

}
