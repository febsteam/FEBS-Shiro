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
 * It's a compound {@link PluginClasspath} ({@link #MAVEN} + {@link #GRADLE} + {@link #KOTLIN})
 * used in development mode ({@link RuntimeMode#DEVELOPMENT}).
 *
 * @author Decebal Suiu
 */
public class DevelopmentPluginClasspath extends PluginClasspath {

    /**
     * The development plugin classpath for <a href="https://maven.apache.org">Maven</a>.
     * The classes directory is {@code target/classes} and the lib directory is {@code target/lib}.
     */
    public static final PluginClasspath MAVEN = new PluginClasspath().addClassesDirectories("target/classes").addJarsDirectories("target/lib");

    /**
     * The development plugin classpath for <a href="https://gradle.org">Gradle</a>.
     * The classes directories are {@code build/classes/java/main, build/resources/main}.
     */
    public static final PluginClasspath GRADLE = new PluginClasspath().addClassesDirectories("build/classes/java/main", "build/resources/main");

    /**
     * The development plugin classpath for <a href="https://kotlinlang.org">Kotlin</a>.
     * The classes directories are {@code build/classes/kotlin/main", build/resources/main, build/tmp/kapt3/classes/main}.
     */
    public static final PluginClasspath KOTLIN = new PluginClasspath().addClassesDirectories("build/classes/kotlin/main", "build/resources/main", "build/tmp/kapt3/classes/main");

    /**
     * The development plugin classpath for <a href="https://www.jetbrains.com/help/idea/specifying-compilation-settings.html">IDEA</a>.
     * The classes directories are {@code out/production/classes", out/production/resource}.
     */
    public static final PluginClasspath IDEA = new PluginClasspath().addClassesDirectories("out/production/classes", "out/production/resource");

    public DevelopmentPluginClasspath() {
        addClassesDirectories(MAVEN.getClassesDirectories());
        addClassesDirectories(GRADLE.getClassesDirectories());
        addClassesDirectories(KOTLIN.getClassesDirectories());
        addClassesDirectories(IDEA.getClassesDirectories());

        addJarsDirectories(MAVEN.getJarsDirectories());
        addJarsDirectories(GRADLE.getJarsDirectories());
        addJarsDirectories(KOTLIN.getJarsDirectories());
        addJarsDirectories(IDEA.getJarsDirectories());
    }

}
