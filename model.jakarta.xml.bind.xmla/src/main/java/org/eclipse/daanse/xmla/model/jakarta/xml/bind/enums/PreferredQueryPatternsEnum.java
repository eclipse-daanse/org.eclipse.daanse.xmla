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

@XmlType(name = "PreferredQueryPatterns")
@XmlEnum
public enum PreferredQueryPatternsEnum {

    @XmlEnumValue("0x00")
    CROSS_JOIN(0x00),

    @XmlEnumValue("0x01")
    DRILL_DOWN_MEMBER(0x01);

    private final int value;

    PreferredQueryPatternsEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static PreferredQueryPatternsEnum fromValue(int v) {
        return Stream.of(PreferredQueryPatternsEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("PreferredQueryPatternsEnum Illegal argument ").append(v).toString()));
    }
}
