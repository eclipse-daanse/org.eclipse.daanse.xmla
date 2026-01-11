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

@XmlType(name = "LiteralNameEnumValue")
@XmlEnum
public enum LiteralNameEnumValueEnum {

    @XmlEnumValue("0")
    DBLITERAL_INVALID(0),

    @XmlEnumValue("1")
    DBLITERAL_BINARY_LITERAL(1),

    @XmlEnumValue("2")
    DBLITERAL_CATALOG_NAME(2),

    @XmlEnumValue("3")
    DBLITERAL_CATALOG_SEPARATOR(3),

    @XmlEnumValue("4")
    DBLITERAL_CHAR_LITERAL(4),

    @XmlEnumValue("5")
    DBLITERAL_COLUMN_ALIAS(5),

    @XmlEnumValue("6")
    DBLITERAL_COLUMN_NAME(6),

    @XmlEnumValue("7")
    DBLITERAL_CORRELATION_NAME(7),

    @XmlEnumValue("8")
    DBLITERAL_CURSOR_NAME(8),

    @XmlEnumValue("9")
    DBLITERAL_ESCAPE_PERCENT(9),

    @XmlEnumValue("10")
    DBLITERAL_ESCAPE_UNDERSCORE(10),

    @XmlEnumValue("11")
    DBLITERAL_INDEX_NAME(11),

    @XmlEnumValue("12")
    DBLITERAL_LIKE_PERCENT(12),

    @XmlEnumValue("13")
    DBLITERAL_LIKE_UNDERSCORE(13),

    @XmlEnumValue("14")
    DBLITERAL_PROCEDURE_NAME(14),

    @XmlEnumValue("15")
    DBLITERAL_QUOTE_PREFIX(15),

    @XmlEnumValue("16")
    DBLITERAL_SCHEMA_NAME(16),

    @XmlEnumValue("17")
    DBLITERAL_TABLE_NAME(17),

    @XmlEnumValue("18")
    DBLITERAL_TEXT_COMMAND(18),

    @XmlEnumValue("19")
    DBLITERAL_USER_NAME(19);

    private final int value;

    LiteralNameEnumValueEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static LiteralNameEnumValueEnum fromValue(int v) {
        return Stream.of(LiteralNameEnumValueEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("LiteralNameEnumValueEnum Illegal argument ").append(v).toString()));
    }
}
