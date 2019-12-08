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
public enum PluginState {

    /**
     * The runtime knows the plugin is there. It knows about the plugin path, the plugin descriptor.
     */
    CREATED("CREATED"),
    
    /**
     * The plugin cannot be used.
     */
    DISABLED("DISABLED"),
    
    /**
     * The plugin is created. All the dependencies are created and resolved.
     * The plugin is ready to be started.
     */
    RESOLVED("RESOLVED"),

    /**
     * The {@link Plugin#start()} has executed. A started plugin may contribute extensions.
     */
    STARTED("STARTED"),

    /**
     * The {@link Plugin#stop()} has executed.
     */
    STOPPED("STOPPED");

    private String status;

    private PluginState(String status) {
        this.status = status;
    }

    public boolean equals(String status) {
    	return (status == null ? false : this.status.equalsIgnoreCase(status));
    }

    @Override
    public String toString() {
        return status;
    }

    public static PluginState parse(String string) {
		for (PluginState status : values()) {
			if (status.equals(string)) {
				return status;
			}
		}

		return null;
	}
}
