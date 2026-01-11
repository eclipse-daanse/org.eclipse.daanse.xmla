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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.discover;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.LiteralNameEnumValueEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoverLiteralsResponseRowXml")
public class DiscoverLiteralsResponseRowXml extends Row implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The name of the literal.
     */
    @XmlElement(name = "LiteralName", required = true)
    private String literalName;

    /**
     * The literal value.
     */
    @XmlElement(name = "LiteralValue", required = true)
    private String literalValue;

    /**
     * The characters that are not valid in the literal.
     */
    @XmlElement(name = "LiteralInvalidChars", required = true)
    private String literalInvalidChars;

    /**
     * The characters that are not valid as the first character of the literal.
     */
    @XmlElement(name = "LiteralInvalidStartingChars", required = true)
    private String literalInvalidStartingChars;

    /**
     * The maximum number of characters in the literal. If there is no maximum or the maximum is
     * unknown, the value is -1.
     */
    @XmlElement(name = "LiteralMaxLength", required = true)
    private Integer literalMaxLength;

    /**
     * The value is one of the following: DBLITERAL_INVALID = 0 DBLITERAL_BINARY_LITERAL = 1
     * DBLITERAL_CATALOG_NAME = 2 DBLITERAL_CATALOG_SEPARATOR = 3 DBLITERAL_CHAR_LITERAL = 4
     * DBLITERAL_COLUMN_ALIAS = 5 DBLITERAL_COLUMN_NAME = 6 DBLITERAL_CORRELATION_NAME = 7
     * DBLITERAL_CURSOR_NAME = 8 DBLITERAL_ESCAPE_PERCENT = 9 DBLITERAL_ESCAPE_UNDERSCORE = 10
     * DBLITERAL_INDEX_NAME = 11 DBLITERAL_LIKE_PERCENT = 12 DBLITERAL_LIKE_UNDERSCORE = 13
     * DBLITERAL_PROCEDURE_NAME = 14 DBLITERAL_QUOTE_PREFIX = 15 DBLITERAL_SCHEMA_NAME = 16
     * DBLITERAL_TABLE_NAME = 17 DBLITERAL_TEXT_COMMAND = 18 DBLITERAL_USER_NAME = 19
     */
    @XmlElement(name = "LiteralNameValue", required = true)
    private LiteralNameEnumValueEnum literalNameValue;

    public String getLiteralName() {
        return literalName;
    }

    public void setLiteralName(String literalName) {
        this.literalName = literalName;
    }

    public String getLiteralValue() {
        return literalValue;
    }

    public void setLiteralValue(String literalValue) {
        this.literalValue = literalValue;
    }

    public String getLiteralInvalidChars() {
        return literalInvalidChars;
    }

    public void setLiteralInvalidChars(String literalInvalidChars) {
        this.literalInvalidChars = literalInvalidChars;
    }

    public String getLiteralInvalidStartingChars() {
        return literalInvalidStartingChars;
    }

    public void setLiteralInvalidStartingChars(String literalInvalidStartingChars) {
        this.literalInvalidStartingChars = literalInvalidStartingChars;
    }

    public Integer getLiteralMaxLength() {
        return literalMaxLength;
    }

    public void setLiteralMaxLength(Integer literalMaxLength) {
        this.literalMaxLength = literalMaxLength;
    }

    public LiteralNameEnumValueEnum getLiteralNameValue() {
        return literalNameValue;
    }

    public void setLiteralNameValue(LiteralNameEnumValueEnum literalNameEnumValue) {
        this.literalNameValue = literalNameEnumValue;
    }
}
