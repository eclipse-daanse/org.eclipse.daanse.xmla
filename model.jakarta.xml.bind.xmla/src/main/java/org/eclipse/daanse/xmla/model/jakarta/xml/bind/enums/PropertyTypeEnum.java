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
 * A bitmask that specifies the type of the property, as follows:
 */
@XmlType(name = "PropertyType")
@XmlEnum
public enum PropertyTypeEnum {

    /**
     * Identifies a property of a member.
     */
    @XmlEnumValue("1")
    PROPERTY_MEMBER(1),

    /**
     * Identifies a property of a cell.
     */
    @XmlEnumValue("2")
    PROPERTY_CELL(2),

    /**
     * Identifies an internal property.
     */
    @XmlEnumValue("4")
    INTERNAL_PROPERTY(4),

    /**
     * Identifies a property which contains a binary large object (BLOB).
     */
    @XmlEnumValue("8")
    BLOB_PROPERTY(8);

    private final int value;

    PropertyTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static PropertyTypeEnum fromValue(int v) {
        return Stream.of(PropertyTypeEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("PropertyTypeEnum Illegal argument ").append(v).toString()));
    }
}
