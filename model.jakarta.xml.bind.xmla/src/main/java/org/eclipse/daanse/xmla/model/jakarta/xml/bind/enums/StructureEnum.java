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

@XmlType(name = "Structure")
@XmlEnum
public enum StructureEnum {

    /**
     * Hierarchy is a fully balanced structure.
     */
    @XmlEnumValue("0")
    HIERARCHY_FULLY_BALANCED(0),

    /**
     * Hierarchy is a ragged balanced structure.
     */
    @XmlEnumValue("1")
    HIERARCHY_RAGGED_BALANCED(1),

    /**
     * Hierarchy is an unbalanced
     */
    @XmlEnumValue("2")
    HIERARCHY_UNBALANCED(2),

    /**
     * Hierarchy is a network
     */
    @XmlEnumValue("3")
    HIERARCHY_NETWORK(3);

    private final int value;

    StructureEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static StructureEnum fromValue(int v) {
        return Stream.of(StructureEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("StructureEnum Illegal argument ").append(v).toString()));
    }
}
