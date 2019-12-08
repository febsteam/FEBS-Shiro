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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Default implementation of the {@link PluginManager} interface.
 * In essence it is a {@link ZipPluginManager} plus a {@link JarPluginManager}.
 * So, it can load plugins from jar and zip, simultaneous.
 *
 * <p>This class is not thread-safe.
 *
 * @author Decebal Suiu
 */
public class DefaultPluginManager extends AbstractPluginManager {

    private static final Logger log = LoggerFactory.getLogger(DefaultPluginManager.class);

    public static final String PLUGINS_DIR_CONFIG_PROPERTY_NAME = "pf4j.pluginsConfigDir";

    public DefaultPluginManager() {
        super();
    }

    public DefaultPluginManager(Path pluginsRoot) {
        super(pluginsRoot);
    }

    @Override
    protected PluginDescriptorFinder createPluginDescriptorFinder() {
        return new CompoundPluginDescriptorFinder()
                .add(new PropertiesPluginDescriptorFinder())
                .add(new ManifestPluginDescriptorFinder());
    }

    @Override
    protected PluginFactory createPluginFactory() {
        return new DefaultPluginFactory();
    }

    @Override
    protected PluginStatusProvider createPluginStatusProvider() {
        String configDir = System.getProperty(PLUGINS_DIR_CONFIG_PROPERTY_NAME);
        Path configPath = configDir != null ? Paths.get(configDir) : getPluginsRoot();

        return new DefaultPluginStatusProvider(configPath);
    }

    @Override
    protected PluginRepository createPluginRepository() {
        return new CompoundPluginRepository()
                .add(new DevelopmentPluginRepository(getPluginsRoot()), this::isDevelopment)
                .add(new JarPluginRepository(getPluginsRoot()), this::isNotDevelopment)
                .add(new DefaultPluginRepository(getPluginsRoot()), this::isNotDevelopment);
    }

    @Override
    protected PluginLoader createPluginLoader() {
        return new CompoundPluginLoader()
                .add(new DevelopmentPluginLoader(this), this::isDevelopment)
                .add(new JarPluginLoader(this), this::isNotDevelopment)
                .add(new DefaultPluginLoader(this), this::isNotDevelopment);
    }

    @Override
    protected VersionManager createVersionManager() {
        return new DefaultVersionManager();
    }

    @Override
    protected void initialize() {
        super.initialize();

        if (isDevelopment()) {
            addPluginStateListener(new LoggingPluginStateListener());
        }

        log.info("PF4J version {} in '{}' mode", getVersion(), getRuntimeMode());
    }

    /**
     * Load a plugin from disk. If the path is a zip file, first unpack.
     *
     * @param pluginPath plugin location on disk
     * @return PluginWrapper for the loaded plugin or null if not loaded
     * @throws PluginRuntimeException if problems during load
     */
    @Override
    protected PluginWrapper loadPluginFromPath(Path pluginPath) {
        // First unzip any ZIP files
        try {
            pluginPath = FileUtils.expandIfZip(pluginPath);
        } catch (Exception e) {
            log.warn("Failed to unzip " + pluginPath, e);
            return null;
        }

        return super.loadPluginFromPath(pluginPath);
    }

}
