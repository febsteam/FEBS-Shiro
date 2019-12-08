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

import java.nio.file.Path;

/**
 * A wrapper over plugin instance.
 *
 * @author Decebal Suiu
 */
public class PluginWrapper {

    private PluginManager pluginManager;
    private PluginDescriptor descriptor;
    private Path pluginPath;
    private ClassLoader pluginClassLoader;
    private PluginFactory pluginFactory;
    private PluginState pluginState;
    private RuntimeMode runtimeMode;

    Plugin plugin; // cache

    public PluginWrapper(PluginManager pluginManager, PluginDescriptor descriptor, Path pluginPath, ClassLoader pluginClassLoader) {
        this.pluginManager = pluginManager;
        this.descriptor = descriptor;
        this.pluginPath = pluginPath;
        this.pluginClassLoader = pluginClassLoader;

        pluginState = PluginState.CREATED;
        runtimeMode = pluginManager.getRuntimeMode();
    }

    /**
     * Returns the plugin manager.
     */
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    /**
     * Returns the plugin descriptor.
     */
    public PluginDescriptor getDescriptor() {
        return descriptor;
    }

    /**
     * Returns the path of this plugin.
     */
    public Path getPluginPath() {
        return pluginPath;
    }

    /**
     * Returns the plugin class loader used to load classes and resources
     * for this plug-in. The class loader can be used to directly access
     * plug-in resources and classes.
     */
    public ClassLoader getPluginClassLoader() {
        return pluginClassLoader;
    }

    public Plugin getPlugin() {
        if (plugin == null) {
            plugin = pluginFactory.create(this);
        }

        return plugin;
    }

    public PluginState getPluginState() {
        return pluginState;
    }

    public RuntimeMode getRuntimeMode() {
        return runtimeMode;
    }

    /**
     * Shortcut
     */
    public String getPluginId() {
        return getDescriptor().getPluginId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + descriptor.getPluginId().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        PluginWrapper other = (PluginWrapper) obj;

        return descriptor.getPluginId().equals(other.descriptor.getPluginId());

    }

    @Override
    public String toString() {
        return "PluginWrapper [descriptor=" + descriptor + ", pluginPath=" + pluginPath + "]";
    }

    public void setPluginState(PluginState pluginState) {
        this.pluginState = pluginState;
    }

    public void setPluginFactory(PluginFactory pluginFactory) {
        this.pluginFactory = pluginFactory;
    }

}
