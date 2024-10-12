/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.api.common.enums;

import java.util.stream.Stream;

/**
 * The scope of the member. The member can be a session-calculated member or a global-calculated
 * member. The column returns NULL for non- calculated members. This column can have one of the
 * following
 */
public enum ScopeEnum {

    GLOBAL(1),

    SESSION(2);

    private final int value;

    ScopeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static ScopeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.parseInt(v);
        return Stream.of(ScopeEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("ScopeEnum Illegal argument ").append(v).toString()));
    }
}
