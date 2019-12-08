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
 * Find a plugin descriptor for a plugin path.
 * You can find the plugin descriptor in manifest file {@link ManifestPluginDescriptorFinder},
 * properties file {@link PropertiesPluginDescriptorFinder}, xml file,
 * java services (with {@link java.util.ServiceLoader}), etc.
 *
 * @author Decebal Suiu
 */
public interface PluginDescriptorFinder {

    /**
     * Returns true if this finder is applicable to the given {@link Path}.
     */
    boolean isApplicable(Path pluginPath);

    PluginDescriptor find(Path pluginPath);

}
