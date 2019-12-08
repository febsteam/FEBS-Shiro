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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Decebal Suiu
 */
public class CompoundPluginDescriptorFinder implements PluginDescriptorFinder {

    private static final Logger log = LoggerFactory.getLogger(CompoundPluginDescriptorFinder.class);

    private List<PluginDescriptorFinder> finders = new ArrayList<>();

    public CompoundPluginDescriptorFinder add(PluginDescriptorFinder finder) {
        if (finder == null) {
            throw new IllegalArgumentException("null not allowed");
        }

        finders.add(finder);

        return this;
    }

    public int size() {
        return finders.size();
    }

    @Override
    public boolean isApplicable(Path pluginPath) {
        for (PluginDescriptorFinder finder : finders) {
            if (finder.isApplicable(pluginPath)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public PluginDescriptor find(Path pluginPath) {
        for (PluginDescriptorFinder finder : finders) {
            if (finder.isApplicable(pluginPath)) {
                log.debug("'{}' is applicable for plugin '{}'", finder, pluginPath);
                try {
                    PluginDescriptor pluginDescriptor = finder.find(pluginPath);
                    if (pluginDescriptor != null) {
                        return pluginDescriptor;
                    }
                } catch (Exception e) {
                    if (finders.indexOf(finder) == finders.size() - 1) {
                        // it's the last finder
                        log.error(e.getMessage(), e);
                    } else {
                        // log the exception and continue with the next finder
                        log.debug(e.getMessage());
                        log.debug("Try to continue with the next finder");
                    }
                }
            } else {
                log.debug("'{}' is not applicable for plugin '{}'", finder, pluginPath);
            }
        }

        throw new PluginRuntimeException("No PluginDescriptorFinder for plugin '{}'", pluginPath);
    }

}
