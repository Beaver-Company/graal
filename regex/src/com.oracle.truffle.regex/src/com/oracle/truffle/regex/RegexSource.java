/*
 * Copyright (c) 2017, 2017, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.truffle.regex;

import com.oracle.truffle.regex.tregex.util.DebugUtil;

public final class RegexSource {

    private final String pattern;
    private final RegexFlags flags;
    private boolean hashComputed = false;
    private int cachedHash;

    public RegexSource(String pattern, RegexFlags flags) {
        this.pattern = pattern;
        this.flags = flags;
    }

    public String getPattern() {
        return pattern;
    }

    public RegexFlags getFlags() {
        return flags;
    }

    @Override
    public int hashCode() {
        if (!hashComputed) {
            final int prime = 31;
            cachedHash = 1;
            cachedHash = prime * cachedHash + pattern.hashCode();
            cachedHash = prime * cachedHash + flags.hashCode();
            hashComputed = true;
        }
        return cachedHash;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof RegexSource &&
                        pattern.equals(((RegexSource) obj).pattern) &&
                        flags.equals(((RegexSource) obj).flags);
    }

    @Override
    public String toString() {
        return "/" + pattern + "/" + flags;
    }

    public DebugUtil.Table toTable() {
        return new DebugUtil.Table("RegexSource",
                        new DebugUtil.Value("pattern", pattern),
                        new DebugUtil.Value("flags", flags));
    }
}
