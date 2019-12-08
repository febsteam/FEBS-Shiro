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

import java.util.EventObject;

/**
 * @author Decebal Suiu
 */
public class PluginStateEvent extends EventObject {

    private PluginWrapper plugin;
    private PluginState oldState;

    public PluginStateEvent(PluginManager source, PluginWrapper plugin, PluginState oldState) {
        super(source);

        this.plugin = plugin;
        this.oldState = oldState;
    }

    @Override
    public PluginManager getSource() {
        return (PluginManager) super.getSource();
    }

    public PluginWrapper getPlugin() {
        return plugin;
    }

    public PluginState getPluginState() {
        return plugin.getPluginState();
    }

    public PluginState getOldState() {
        return oldState;
    }

    @Override
    public String toString() {
        return "PluginStateEvent [plugin=" + plugin.getPluginId() +
                ", newState=" + getPluginState() +
                ", oldState=" + oldState +
                ']';
    }

}
