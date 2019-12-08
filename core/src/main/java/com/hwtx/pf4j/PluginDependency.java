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
 * @author Decebal Suiu
 */
public class PluginDependency {

    private String pluginId;
    private String pluginVersionSupport = "*";
    private boolean optional;

    public PluginDependency(String dependency) {
        int index = dependency.indexOf('@');
        if (index == -1) {
            this.pluginId = dependency;
        } else {
            this.pluginId = dependency.substring(0, index);
            if (dependency.length() > index + 1) {
                this.pluginVersionSupport = dependency.substring(index + 1);
            }
        }

        // A dependency is considered as optional, if the plugin id ends with a question mark.
        this.optional = this.pluginId.endsWith("?");
        if (this.optional) {
            this.pluginId = this.pluginId.substring(0, this.pluginId.length() - 1);
        }
    }

    public String getPluginId() {
        return pluginId;
    }

    public String getPluginVersionSupport() {
        return pluginVersionSupport;
    }

    public boolean isOptional() {
        return optional;
    }

    @Override
    public String toString() {
        return "PluginDependency [pluginId=" + pluginId + ", pluginVersionSupport="
            + pluginVersionSupport + ", optional="
            + optional + "]";
    }

}
