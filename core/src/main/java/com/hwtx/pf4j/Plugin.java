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

/**
 * This class will be extended by all plugins and
 * serve as the common class between a plugin and the application.
 *
 * @author Decebal Suiu
 */
public class Plugin {

    /**
     * Makes logging service available for descending classes.
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Wrapper of the plugin.
     */
    protected PluginWrapper wrapper;

    /**
     * Constructor to be used by plugin manager for plugin instantiation.
     * Your plugins have to provide constructor with this exact signature to
     * be successfully loaded by manager.
     */
    public Plugin(final PluginWrapper wrapper) {
        if (wrapper == null) {
            throw new IllegalArgumentException("Wrapper cannot be null");
        }

        this.wrapper = wrapper;
    }

    /**
     * Retrieves the wrapper of this plug-in.
     */
    public final PluginWrapper getWrapper() {
        return wrapper;
    }

    /**
     * This method is called by the application when the plugin is started.
     * See {@link PluginManager#startPlugin(String)}.
     */
    public void start() {
    }

    /**
     * This method is called by the application when the plugin is stopped.
     * See {@link PluginManager#stopPlugin(String)}.
     */
    public void stop() {
    }

    /**
     * This method is called by the application when the plugin is deleted.
     * See {@link PluginManager#deletePlugin(String)}.
     */
    public void delete() {
    }

}
