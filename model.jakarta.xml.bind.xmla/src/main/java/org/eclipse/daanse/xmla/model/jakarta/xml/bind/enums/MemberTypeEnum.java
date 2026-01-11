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

/**
 * The type of the member
 */
@XmlType(name = "MemberType")
@XmlEnum
public enum MemberTypeEnum {

    /**
     * Is a regular member.
     */
    @XmlEnumValue("1")
    REGULAR_MEMBER(1),

    /**
     * Is the All member.
     */
    @XmlEnumValue("2")
    ALL_MEMBER(2),

    /**
     * Is a measure.
     */
    @XmlEnumValue("3")
    MEASURE(3),

    /**
     * Is a formula.
     */
    @XmlEnumValue("4")
    FORMULA(4),

    /**
     * Is of unknown type.
     */
    @XmlEnumValue("0")
    UNKNOWN(0);

    private final int value;

    MemberTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static MemberTypeEnum fromValue(int v) {
        return Stream.of(MemberTypeEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("MemberTypeEnum Illegal argument ").append(v).toString()));
    }
}
