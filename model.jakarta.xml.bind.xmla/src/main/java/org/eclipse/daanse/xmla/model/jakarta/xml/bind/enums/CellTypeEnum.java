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

@XmlType(name = "CellTypeEnum")
@XmlEnum
public enum CellTypeEnum {

    @XmlEnumValue("xsd:int")
    INTEGER("xsd:int"),

    @XmlEnumValue("xsd:decimal")
    DECIMAL("xsd:decimal"),

    @XmlEnumValue("xsd:double")
    DOUBLE("xsd:double"),

    @XmlEnumValue("xsd:string")
    STRING("xsd:string");

    private final String value;

    CellTypeEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static CellTypeEnum fromValue(String v) {
        return Stream.of(CellTypeEnum.values()).filter(e -> (e.value.equals(v))).findFirst()
                .orElse(CellTypeEnum.STRING);
    }
}
