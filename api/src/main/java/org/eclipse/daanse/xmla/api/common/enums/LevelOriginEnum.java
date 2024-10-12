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

public enum LevelOriginEnum {

    /**
     * None
     */
    NONE(0x0000),

    /**
     * Identifies levels in a user defined hierarchy.
     */
    USER_DEFINED(0x0001),

    /**
     * Identifies levels in an attribute hierarchy.
     */
    ATTRIBUTE(0x0002),

    /**
     * Identifies levels in a key attribute hierarchy.
     */
    KEY(0x0004),

    /**
     * Identifies levels in attribute hierarchies that are not enabled
     */
    NOT_ENABLED(0x0008);

    private final int value;

    LevelOriginEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static LevelOriginEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(LevelOriginEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("LevelOriginEnum Illegal argument ").append(v).toString()));
    }
}
