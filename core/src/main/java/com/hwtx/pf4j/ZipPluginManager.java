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

/**
 * It's a {@link PluginManager} that loads each plugin from a {@code zip} file.
 * The structure of the zip file is:
 * <ul>
 * <li>{@code lib} directory that contains all dependencies (as jar files); it's optional (no dependencies)
 * <li>{@code classes} directory that contains all plugin's classes
 * </ul>
 *
 * @author Decebal Suiu
 */
public class ZipPluginManager extends DefaultPluginManager {

    @Override
    protected PluginDescriptorFinder createPluginDescriptorFinder() {
        return new PropertiesPluginDescriptorFinder();
    }

    @Override
    protected PluginLoader createPluginLoader() {
        return new CompoundPluginLoader()
            .add(new DevelopmentPluginLoader(this), this::isDevelopment)
            .add(new DefaultPluginLoader(this), this::isNotDevelopment);
    }

    @Override
    protected PluginRepository createPluginRepository() {
        return new CompoundPluginRepository()
            .add(new DevelopmentPluginRepository(getPluginsRoot()), this::isDevelopment)
            .add(new DefaultPluginRepository(getPluginsRoot()), this::isNotDevelopment);
    }

}
