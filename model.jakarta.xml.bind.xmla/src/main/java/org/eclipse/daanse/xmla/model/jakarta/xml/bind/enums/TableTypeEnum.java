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
 * Table type. One of the following or a provider-specific
 */
@XmlType(name = "TableType")
@XmlEnum
public enum TableTypeEnum {

    @XmlEnumValue("ALIAS")
    ALIAS("ALIAS"),

    @XmlEnumValue("TABLE")
    TABLE("TABLE"),

    @XmlEnumValue("SYNONYM")
    SYNONYM("SYNONYM"),

    @XmlEnumValue("SYSTEM TABLE")
    SYSTEM_TABLE("SYSTEM TABLE"),

    @XmlEnumValue("VIEW")
    VIEW("VIEW"),

    @XmlEnumValue("GLOBAL TEMPORARY")
    GLOBAL_TEMPORARY("GLOBAL TEMPORARY"),

    @XmlEnumValue("LOCAL TEMPORARY")
    LOCAL_TEMPORARY("LOCAL TEMPORARY"),

    @XmlEnumValue("EXTERNAL TABLE")
    EXTERNAL_TABLE("EXTERNAL TABLE"),

    @XmlEnumValue("SYSTEM VIEW")
    SYSTEM_VIEW("SYSTEM VIEW");

    private final String value;

    TableTypeEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static TableTypeEnum fromValue(String v) {
        return Stream.of(TableTypeEnum.values()).filter(e -> (e.value.equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("TableTypeEnum Illegal argument ").append(v).toString()));
    }
}
