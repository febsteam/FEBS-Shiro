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
package com.hwtx.pf4j.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This filter providing conditional OR logic across a list of file filters.
 * This filter returns {@code true} if one filter in the list return {@code true}. Otherwise, it returns {@code false}.
 * Checking of the file filter list stops when the first filter returns {@code true}.
 *
 * @author Decebal Suiu
 */
public class OrFileFilter implements FileFilter {

    /** The list of file filters. */
    private List<FileFilter> fileFilters;

    public OrFileFilter() {
        this(new ArrayList<>());
    }

    public OrFileFilter(FileFilter... fileFilters) {
        this(Arrays.asList(fileFilters));
    }

    public OrFileFilter(List<FileFilter> fileFilters) {
        this.fileFilters = new ArrayList<>(fileFilters);
    }

    public OrFileFilter addFileFilter(FileFilter fileFilter) {
        fileFilters.add(fileFilter);

        return this;
    }

    public List<FileFilter> getFileFilters() {
        return Collections.unmodifiableList(fileFilters);
    }

    public boolean removeFileFilter(FileFilter fileFilter) {
        return fileFilters.remove(fileFilter);
    }

    public void setFileFilters(List<FileFilter> fileFilters) {
        this.fileFilters = new ArrayList<>(fileFilters);
    }

    @Override
    public boolean accept(File file) {
        if (this.fileFilters.isEmpty()) {
            return true;
        }

        for (FileFilter fileFilter : this.fileFilters) {
            if (fileFilter.accept(file)) {
                return true;
            }
        }

        return false;
    }

}
