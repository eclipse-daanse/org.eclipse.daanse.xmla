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

@XmlType(name = "CoordinateType")
@XmlEnum
public enum CoordinateTypeEnum {

    /**
     * Action coordinate refers to the cube.
     */
    @XmlEnumValue("1")
    CUBE(1),

    /**
     * Action coordinate refers to a dimension.
     */
    @XmlEnumValue("2")
    DIMENSION(2),

    /**
     * Action coordinate refers to a level.
     */
    @XmlEnumValue("3")
    LEVEL(3),

    /**
     * Action coordinate refers to a member.
     */
    @XmlEnumValue("4")
    MEMBER(4),

    /**
     * Action coordinate refers to a set.
     */
    @XmlEnumValue("5")
    SET(5),

    /**
     * Action coordinate refers to a cell.
     */
    @XmlEnumValue("6")
    CELL(6);

    private final int value;

    CoordinateTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static CoordinateTypeEnum fromValue(int v) {
        return Stream.of(CoordinateTypeEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("CoordinateTypeEnum Illegal argument ").append(v).toString()));
    }
}
