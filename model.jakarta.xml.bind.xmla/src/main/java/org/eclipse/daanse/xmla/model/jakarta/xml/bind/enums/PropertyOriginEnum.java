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
 * A bitmask that specifies the type of hierarchy to which the property applies, as follows:
 */
@XmlType(name = "PropertyOrigin")
@XmlEnum
public enum PropertyOriginEnum {

    /**
     * Indicates the property is on a user defined hierarchy.
     */
    @XmlEnumValue("1")
    USER_DEFINED(1),

    /**
     * Indicates the property is on an attribute hierarchy.
     */
    @XmlEnumValue("2")
    ATTRIBUTE_HIERARCHY(2),

    /**
     * Indicates the property is on a key attribute hierarchy.
     */
    @XmlEnumValue("4")
    KEY_ATTRIBUTE_HIERARCHY(4),

    /**
     * Indicates the property is on an attribute hierarchy that is not enabled.
     */
    @XmlEnumValue("8")
    ATTRIBUTE_HIERARCHY_NOT_ENABLED(8);

    private final int value;

    PropertyOriginEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static PropertyOriginEnum fromValue(int v) {
        return Stream.of(PropertyOriginEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("PropertyOriginEnum Illegal argument ").append(v).toString()));
    }
}
