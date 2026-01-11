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

@XmlType(name = "HierarchyOrigin")
@XmlEnum
public enum HierarchyOriginEnum {

    /**
     * Identifies user-defined hierarchies.
     */
    @XmlEnumValue("0x0001")
    USER_DEFINED(0x0001),

    /**
     * Identifies attribute hierarchies.
     */
    @XmlEnumValue("0x0002")
    ATTRIBUTE(0x0002),

    /**
     * Identifies key attribute hierarchies.
     */
    @XmlEnumValue("0x0004")
    KEY(0x0004),

    /**
     * Identifies attributes with no attribute hierarchies.
     */
    @XmlEnumValue("0x0008")
    WITH_NO_ATTRIBUTE(0x0008),

    /**
     * The default restriction
     */
    @XmlEnumValue("0x0003")
    DEFAULT(0x0003);

    private final int value;

    HierarchyOriginEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static HierarchyOriginEnum fromValue(int v) {
        return Stream.of(HierarchyOriginEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("HierarchyOriginEnum Illegal argument ").append(v).toString()));
    }
}
