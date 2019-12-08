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
import java.util.List;
import java.util.Set;

/**
 * Provides the functionality for plugin management such as load,
 * start and stop the plugins.
 *
 * @author Decebal Suiu
 */
public interface PluginManager {

    /**
     * Retrieves all plugins.
     */
    List<PluginWrapper> getPlugins();

    /**
     * Retrieves all plugins with this state.
     */
    List<PluginWrapper> getPlugins(PluginState pluginState);

    /**
     * Retrieves all resolved plugins (with resolved dependency).
     */
    List<PluginWrapper> getResolvedPlugins();

    /**
     * Retrieves all unresolved plugins (with unresolved dependency).
     */
    List<PluginWrapper> getUnresolvedPlugins();

    /**
     * Retrieves all started plugins.
     */
    List<PluginWrapper> getStartedPlugins();

    /**
     * Retrieves the plugin with this id, or null if the plugin does not exist.
     *
     * @param pluginId the unique plugin identifier, specified in its metadata
     * @return A PluginWrapper object for this plugin, or null if it does not exist.
     */
    PluginWrapper getPlugin(String pluginId);

    /**
     * Load plugins.
     */
    void loadPlugins();

    /**
     * Load a plugin.
     *
     * @param pluginPath the plugin location
     * @return the pluginId of the installed plugin as specified in its {@linkplain PluginDescriptor metadata}
     * @throws PluginRuntimeException if something goes wrong
     */
    String loadPlugin(Path pluginPath);

    /**
     * Start all active plugins.
     */
    void startPlugins();

    /**
     * Start the specified plugin and its dependencies.
     *
     * @return the plugin state
     * @throws PluginRuntimeException if something goes wrong
     */
    PluginState startPlugin(String pluginId);

    /**
     * Stop all active plugins.
     */
    void stopPlugins();

    /**
     * Stop the specified plugin and its dependencies.
     *
     * @return the plugin state
     * @throws PluginRuntimeException if something goes wrong
     */
    PluginState stopPlugin(String pluginId);

    /**
     * Unload a plugin.
     *
     * @param pluginId the unique plugin identifier, specified in its metadata
     * @return true if the plugin was unloaded
     * @throws PluginRuntimeException if something goes wrong
     */
    boolean unloadPlugin(String pluginId);

    /**
     * Disables a plugin from being loaded.
     *
     * @param pluginId the unique plugin identifier, specified in its metadata
     * @return true if plugin is disabled
     * @throws PluginRuntimeException if something goes wrong
     */
    boolean disablePlugin(String pluginId);

    /**
     * Enables a plugin that has previously been disabled.
     *
     * @param pluginId the unique plugin identifier, specified in its metadata
     * @return true if plugin is enabled
     * @throws PluginRuntimeException if something goes wrong
     */
    boolean enablePlugin(String pluginId);

    /**
     * Deletes a plugin.
     *
     * @param pluginId the unique plugin identifier, specified in its metadata
     * @return true if the plugin was deleted
     * @throws PluginRuntimeException if something goes wrong
     */
    boolean deletePlugin(String pluginId);

    ClassLoader getPluginClassLoader(String pluginId);

    /**
     * The runtime mode. Must currently be either DEVELOPMENT or DEPLOYMENT.
     */
    RuntimeMode getRuntimeMode();

    /**
     * Returns {@code true} if the runtime mode is {@code RuntimeMode.DEVELOPMENT}.
     */
    default boolean isDevelopment() {
        return RuntimeMode.DEVELOPMENT.equals(getRuntimeMode());
    }

    /**
     * Returns {@code true} if the runtime mode is not {@code RuntimeMode.DEVELOPMENT}.
     */
    default boolean isNotDevelopment() {
        return !isDevelopment();
    }

    /**
     * Retrieves the {@link PluginWrapper} that loaded the given class 'clazz'.
     */
    PluginWrapper whichPlugin(Class<?> clazz);

    void addPluginStateListener(PluginStateListener listener);

    void removePluginStateListener(PluginStateListener listener);

    /**
     * Set the system version.  This is used to compare against the plugin
     * requires attribute.  The default system version is 0.0.0 which
     * disables all version checking.
     *
     * @param version
     * @default 0.0.0
     */
    void setSystemVersion(String version);

    /**
     * Returns the system version.
     *
     * @return the system version
     */
    String getSystemVersion();

    /**
     * Gets the path of the folder where plugins are installed.
     *
     * @return Path of plugins root
     */
    Path getPluginsRoot();

    VersionManager getVersionManager();

}
