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

import com.hwtx.pf4j.util.*;

import java.io.FileFilter;
import java.nio.file.Path;

/**
 * @author Decebal Suiu
 */
public class DevelopmentPluginRepository extends BasePluginRepository {

    public static final String MAVEN_BUILD_DIR = "target";
    public static final String GRADLE_BUILD_DIR = "build";

    public DevelopmentPluginRepository(Path pluginsRoot) {
        super(pluginsRoot);

        AndFileFilter pluginsFilter = new AndFileFilter(new DirectoryFileFilter());
        pluginsFilter.addFileFilter(new NotFileFilter(createHiddenPluginFilter()));
        setFilter(pluginsFilter);
    }

    protected FileFilter createHiddenPluginFilter() {
        OrFileFilter hiddenPluginFilter = new OrFileFilter(new HiddenFilter());

        // skip default build output folders since these will cause errors in the logs
        hiddenPluginFilter
                .addFileFilter(new NameFileFilter(MAVEN_BUILD_DIR))
                .addFileFilter(new NameFileFilter(GRADLE_BUILD_DIR));

        return hiddenPluginFilter;
    }

}
