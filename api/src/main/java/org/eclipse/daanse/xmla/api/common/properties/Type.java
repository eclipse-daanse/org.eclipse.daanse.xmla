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
package org.eclipse.daanse.xmla.api.common.properties;

enum Type {
    STRING("string", XsdType.XSD_STRING), STRING_ARRAY("StringArray", XsdType.XSD_STRING),
    ARRAY("Array", XsdType.XSD_STRING), ENUMERATION("Enumeration", XsdType.XSD_STRING),
    ENUMERATION_ARRAY("EnumerationArray", XsdType.XSD_STRING), ENUM_STRING("EnumString", XsdType.XSD_STRING),
    BOOLEAN("Boolean", XsdType.XSD_BOOLEAN), STRING_SOMETIMES_ARRAY("StringSometimesArray", XsdType.XSD_STRING),
    INTEGER("Integer", XsdType.XSD_INTEGER), UNSIGNED_INTEGER("UnsignedInteger", XsdType.XSD_UNSIGNED_INTEGER),
    DOUBLE("Double", XsdType.XSD_DOUBLE), DATE_TIME("DateTime", XsdType.XSD_DATE_TIME), ROW_SET("Rowset", null),
    SHORT("Short", XsdType.XSD_SHORT), UUID("UUID", XsdType.UUID),
    UNSIGNED_SHORT("UnsignedShort", XsdType.XSD_UNSIGNED_SHORT), LONG("Long", XsdType.XSD_LONG),
    UNSIGNED_LONG("UnsignedLong", XsdType.XSD_UNSIGNED_LONG);

    public final String columnType;
    public final String nameValue;

    Type(String nameValue, String columnType) {
        this.nameValue = nameValue;
        this.columnType = columnType;
    }

    boolean isEnum() {
        return this == ENUMERATION || this == ENUMERATION_ARRAY || this == ENUM_STRING;
    }

    String getNameValue() {
        return nameValue;
    }

}
