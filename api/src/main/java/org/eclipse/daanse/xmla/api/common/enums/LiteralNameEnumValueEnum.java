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
package org.eclipse.daanse.xmla.api.common.enums;

import java.util.stream.Stream;

public enum LiteralNameEnumValueEnum {

    DBLITERAL_INVALID(0), DBLITERAL_BINARY_LITERAL(1), DBLITERAL_CATALOG_NAME(2), DBLITERAL_CATALOG_SEPARATOR(3),
    DBLITERAL_CHAR_LITERAL(4), DBLITERAL_COLUMN_ALIAS(5), DBLITERAL_COLUMN_NAME(6), DBLITERAL_CORRELATION_NAME(7),
    DBLITERAL_CURSOR_NAME(8), DBLITERAL_ESCAPE_PERCENT(9), DBLITERAL_ESCAPE_UNDERSCORE(10), DBLITERAL_INDEX_NAME(11),
    DBLITERAL_LIKE_PERCENT(12), DBLITERAL_LIKE_UNDERSCORE(13), DBLITERAL_PROCEDURE_NAME(14), DBLITERAL_QUOTE_PREFIX(15),
    DBLITERAL_SCHEMA_NAME(16), DBLITERAL_TABLE_NAME(17), DBLITERAL_TEXT_COMMAND(18), DBLITERAL_USER_NAME(19),
    DBLITERAL_VIEW_NAME(20), DBLITERAL_CUBE_NAME(21), DBLITERAL_DIMENSION_NAME(22), DBLITERAL_HIERARCHY_NAME(23),
    DBLITERAL_LEVEL_NAME(24), DBLITERAL_MEMBER_NAME(25), DBLITERAL_PROPERTY_NAME(26), DBLITERAL_SCHEMA_SEPARATOR(27),
    DBLITERAL_QUOTE_SUFFIX(28), DBLITERAL_ESCAPE_PERCENT_SUFFIX(29), DBLITERAL_ESCAPE_UNDERSCORE_SUFFIX(30);

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
