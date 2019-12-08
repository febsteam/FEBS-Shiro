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

/**
 * @author Decebal Suiu
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String str) {
        return (str == null) || str.isEmpty();
    }

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    /**
     * Format the string. Replace "{}" with %s and format the string using {@link String#format(String, Object...)}.
     */
    public static String format(String str, Object... args) {
        str = str.replaceAll("\\{}", "%s");

        return String.format(str, args);
    }

    /**
     * <p>Adds a substring only if the source string does not already start with the substring,
     * otherwise returns the source string.</p>
     * <p/>
     * <p>A {@code null} source string will return {@code null}.
     * An empty ("") source string will return the empty string.
     * A {@code null} search string will return the source string.</p>
     * <p/>
     * <pre>
     * StringUtils.addStart(null, *)      = *
     * StringUtils.addStart("", *)        = *
     * StringUtils.addStart(*, null)      = *
     * StringUtils.addStart("domain.com", "www.")  = "www.domain.com"
     * StringUtils.addStart("abc123", "abc")    = "abc123"
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param add the String to search for and add, may be null
     * @return the substring with the string added if required
     */
    public static String addStart(String str, String add) {
        if (isNullOrEmpty(add)) {
            return str;
        }

        if (isNullOrEmpty(str)) {
            return add;
        }

        if (!str.startsWith(add)) {
            return add + str;
        }

        return str;
    }

}
