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

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author Decebal Suiu
 */
public enum RuntimeMode {

    DEVELOPMENT("development", "dev"), // development
    DEPLOYMENT("deployment", "prod"); // deployment

    private final String name;
    private final String[] aliases;

    private static final Map<String, RuntimeMode> map = new HashMap<>();

    static {
        for (RuntimeMode mode : RuntimeMode.values()) {
            map.put(mode.name, mode);
            for (String alias : mode.aliases) {
                map.put(alias, mode);
            }
        }
    }

    RuntimeMode(final String name, final String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    @Override
    public String toString() {
        return name;
    }

    public static RuntimeMode byName(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }

        throw new NoSuchElementException("Cannot found PF4J runtime mode with name '" + name + "'." +
            "Must be one value from '" + map.keySet() + ".");
    }

}
