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

import com.hwtx.pf4j.util.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Load all information needed by a plugin.
 * This means add to the plugin's {@link ClassLoader} all the jar files and
 * all the class files specified in the {@link PluginClasspath}.
 *
 * @author Decebal Suiu
 */
public class BasePluginLoader implements PluginLoader {

    protected PluginManager pluginManager;
    protected PluginClasspath pluginClasspath;

    public BasePluginLoader(PluginManager pluginManager, PluginClasspath pluginClasspath) {
        this.pluginManager = pluginManager;
        this.pluginClasspath = pluginClasspath;
    }

    @Override
    public boolean isApplicable(Path pluginPath) {
        return Files.exists(pluginPath);
    }

    @Override
    public ClassLoader loadPlugin(Path pluginPath, PluginDescriptor pluginDescriptor) {
        PluginClassLoader pluginClassLoader = createPluginClassLoader(pluginPath, pluginDescriptor);

        loadClasses(pluginPath, pluginClassLoader);
        loadJars(pluginPath, pluginClassLoader);

        return pluginClassLoader;
    }

    protected PluginClassLoader createPluginClassLoader(Path pluginPath, PluginDescriptor pluginDescriptor) {
        return new PluginClassLoader(pluginManager, pluginDescriptor, getClass().getClassLoader());
    }

    /**
     * Add all {@code *.class} files from {@link PluginClasspath#getClassesDirectories()}
     * to the plugin's {@link ClassLoader}.
     */
    protected void loadClasses(Path pluginPath, PluginClassLoader pluginClassLoader) {
        for (String directory : pluginClasspath.getClassesDirectories()) {
            File file = pluginPath.resolve(directory).toFile();
            if (file.exists() && file.isDirectory()) {
                pluginClassLoader.addFile(file);
            }
        }
    }

    /**
     * Add all {@code *.jar} files from {@link PluginClasspath#getJarsDirectories()}
     * to the plugin's {@link ClassLoader}.
     */
    protected void loadJars(Path pluginPath, PluginClassLoader pluginClassLoader) {
        for (String jarsDirectory : pluginClasspath.getJarsDirectories()) {
            Path file = pluginPath.resolve(jarsDirectory);
            List<File> jars = FileUtils.getJars(file);
            for (File jar : jars) {
                pluginClassLoader.addFile(jar);
            }
        }
    }

}
