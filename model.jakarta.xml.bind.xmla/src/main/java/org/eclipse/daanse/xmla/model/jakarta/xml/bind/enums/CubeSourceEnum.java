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

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import java.util.stream.Stream;

@XmlType(name = "ACubeSource")
@XmlEnum
public enum CubeSourceEnum {

    @XmlEnumValue("0x01")
    CUBE(0x01),

    @XmlEnumValue("0x02")
    DIMENSION(0x02);

    private final int value;

    CubeSourceEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static CubeSourceEnum fromValue(int v) {
        return Stream.of(CubeSourceEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("CubeSourceEnum Illegal argument ").append(v).toString()));
    }
}
