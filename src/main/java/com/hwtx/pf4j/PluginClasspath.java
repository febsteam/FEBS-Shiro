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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The classpath of the plugin.
 * It contains {@code classes} directories (directories that contain classes files)
 * and {@code jars} directories (directories that contain jars files).
 *
 * @author Decebal Suiu
 */
public class PluginClasspath {

    private Set<String> classesDirectories = new HashSet<>();
    private Set<String> jarsDirectories = new HashSet<>();

    public Set<String> getClassesDirectories() {
        return classesDirectories;
    }

    public PluginClasspath addClassesDirectories(String... classesDirectories) {
        return addClassesDirectories(Arrays.asList(classesDirectories));
    }

    public PluginClasspath addClassesDirectories(Collection<String> classesDirectories) {
        this.classesDirectories.addAll(classesDirectories);

        return this;
    }

    public Set<String> getJarsDirectories() {
        return jarsDirectories;
    }

    public PluginClasspath addJarsDirectories(String... jarsDirectories) {
        return addJarsDirectories(Arrays.asList(jarsDirectories));
    }

    public PluginClasspath addJarsDirectories(Collection<String> jarsDirectories) {
        this.jarsDirectories.addAll(jarsDirectories);

        return this;
    }

}
