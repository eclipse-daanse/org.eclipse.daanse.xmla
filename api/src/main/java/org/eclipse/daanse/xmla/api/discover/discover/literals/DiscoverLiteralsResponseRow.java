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
package org.eclipse.daanse.xmla.api.discover.discover.literals;

import org.eclipse.daanse.xmla.api.common.enums.LiteralNameEnumValueEnum;

/**
 * This schema rowset returns information about literals supported by the server.
 */
public interface DiscoverLiteralsResponseRow {

    /**
     * @return The name of the literal.
     */
    String literalName();

    /**
     * @return The literal value.
     */
    String literalValue();

    /**
     * @return The characters that are not valid in the literal.
     */
    String literalInvalidChars();

    /**
     * @return The characters that are not valid as the first character of the literal.
     */
    String literalInvalidStartingChars();

    /**
     * @return The maximum number of characters in the literal. If there is no maximum or the maximum is
     *         unknown, the value is -1.
     */
    Integer literalMaxLength();

    /**
     * @return The value is one of the following: DBLITERAL_INVALID = 0 DBLITERAL_BINARY_LITERAL = 1
     *         DBLITERAL_CATALOG_NAME = 2 DBLITERAL_CATALOG_SEPARATOR = 3 DBLITERAL_CHAR_LITERAL = 4
     *         DBLITERAL_COLUMN_ALIAS = 5 DBLITERAL_COLUMN_NAME = 6 DBLITERAL_CORRELATION_NAME = 7
     *         DBLITERAL_CURSOR_NAME = 8 DBLITERAL_ESCAPE_PERCENT = 9 DBLITERAL_ESCAPE_UNDERSCORE = 10
     *         DBLITERAL_INDEX_NAME = 11 DBLITERAL_LIKE_PERCENT = 12 DBLITERAL_LIKE_UNDERSCORE = 13
     *         DBLITERAL_PROCEDURE_NAME = 14 DBLITERAL_QUOTE_PREFIX = 15 DBLITERAL_SCHEMA_NAME = 16
     *         DBLITERAL_TABLE_NAME = 17 DBLITERAL_TEXT_COMMAND = 18 DBLITERAL_USER_NAME = 19
     */
    LiteralNameEnumValueEnum literalNameEnumValue();

}
