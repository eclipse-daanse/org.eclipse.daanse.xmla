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

public enum LevelUniqueSettingsEnum {

    /**
     * None
     */
    NONE(0x00000000),

    /**
     * Member key columns establish uniqueness.
     */
    KEY_COLUMNS(0x00000001),

    /**
     * Member name columns establish uniqueness.
     */
    NAME_COLUMNS(0x00000002),

    /**
     * Member key and name columns establish uniqueness.
     */
    KEY_NAME_COLUMNS(0x00000003);

    private final int value;

    LevelUniqueSettingsEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static LevelUniqueSettingsEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(LevelUniqueSettingsEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("LevelUniqueSettingsEnum Illegal argument ").append(v).toString()));
    }
}
