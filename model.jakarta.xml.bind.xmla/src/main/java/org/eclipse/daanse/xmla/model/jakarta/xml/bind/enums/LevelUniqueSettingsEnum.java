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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums;

import java.util.stream.Stream;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "LevelUniqueSettings")
@XmlEnum
public enum LevelUniqueSettingsEnum {

    @XmlEnumValue("0x00000000")
    NONE(0x00000000),

    /**
     * Member key columns establish uniqueness.
     */
    @XmlEnumValue("0x00000001")
    KEY_COLUMNS(0x00000001),

    /**
     * Member name columns establish uniqueness.
     */
    @XmlEnumValue("0x00000002")
    NAME_COLUMNS(0x00000002),

    /**
     * Member name columns establish uniqueness.
     */
    @XmlEnumValue("0x00000003")
    KEY_NAME_COLUMNS(0x00000003);

    private final int value;

    LevelUniqueSettingsEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static LevelUniqueSettingsEnum fromValue(int v) {
        return Stream.of(LevelUniqueSettingsEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("LevelUniqueSettingsEnum Illegal argument ").append(v).toString()));
    }
}
