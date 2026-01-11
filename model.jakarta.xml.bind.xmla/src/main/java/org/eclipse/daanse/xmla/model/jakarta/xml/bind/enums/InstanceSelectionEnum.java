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

@XmlType(name = "InstanceSelectionEnum")
@XmlEnum
public enum InstanceSelectionEnum {

    /**
     * DROPDOWN type of display is suggested.
     */
    @XmlEnumValue("1")
    DROPDOWN(1),

    /**
     * LIST type of display is suggested.
     */
    @XmlEnumValue("2")
    LIST(2),

    /**
     * FILTERED LIST type of display is suggested.
     */
    @XmlEnumValue("3")
    FILTERED_LIST(3),

    /**
     * MANDATORY FILTER type of display is suggested
     */
    @XmlEnumValue("4")
    MANDATORY_FILTER(4);

    private final int value;

    InstanceSelectionEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static InstanceSelectionEnum fromValue(int v) {
        return Stream.of(InstanceSelectionEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("InstanceSelectionEnum Illegal argument ").append(v).toString()));
    }
}
