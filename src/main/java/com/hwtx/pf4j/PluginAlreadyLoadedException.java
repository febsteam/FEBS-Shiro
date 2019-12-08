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
 * @author Decebal Suiu
 */
public class PluginAlreadyLoadedException extends PluginRuntimeException {

    private final String pluginId;
    private final Path pluginPath;

    public PluginAlreadyLoadedException(String pluginId, Path pluginPath) {
        super("Plugin '{}' already loaded with id '{}'", pluginPath, pluginId);

        this.pluginId = pluginId;
        this.pluginPath = pluginPath;
    }

    public String getPluginId() {
        return pluginId;
    }

    public Path getPluginPath() {
        return pluginPath;
    }

}
