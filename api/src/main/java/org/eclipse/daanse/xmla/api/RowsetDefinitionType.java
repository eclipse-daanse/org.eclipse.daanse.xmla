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
*   Stefan Bischof (bipolis.org)
*   Copyright (C) 2003-2005 Julian Hyde
*   Copyright (C) 2005-2017 Hitachi Vantara
*   Copyright (C) 2019 Topsoft
*   Copyright (C) 2020 - 2022 Sergei Semenkov
*   All Rights Reserved.
*/

package org.eclipse.daanse.xmla.api;

/**
 * <code>RowsetDefinition</code> defines a rowset, including the columns it
 * should contain.
 *
 * <p>See "XML for Analysis Rowsets", page 38 of the XML for Analysis
 * Specification, version 1.1.
 *
 * @author jhyde
 */
public enum RowsetDefinitionType {
        STRING("string","xsd:string"),
        STRING_ARRAY("StringArray","xsd:string"),
        ARRAY("Array","xsd:string"),
        ENUMERATION("Enumeration","xsd:string"),
        ENUMERATION_ARRAY("EnumerationArray","xsd:string"),
        ENUM_STRING("EnumString","xsd:string"),
        BOOLEAN("Boolean","xsd:boolean"),
        STRING_SOMETIMES_ARRAY("StringSometimesArray","xsd:string"),
        INTEGER("Integer","xsd:int"),
        UNSIGNED_INTEGER("UnsignedInteger","xsd:unsignedInt"),
        DOUBLE("Double","xsd:double"),
        DATE_TIME("DateTime","xsd:dateTime"),
        ROW_SET("Rowset",null),
        SHORT("Short","xsd:short"),
        UUID("UUID","uuid"),
        UNSIGNED_SHORT("UnsignedShort","xsd:unsignedShort"),
        LONG("Long","xsd:long"),
        UNSIGNED_LONG("UnsignedLong","xsd:unsignedLong");

        public final String columnType;
        public final String value;

        RowsetDefinitionType(String value, String columnType) {
            this.value = value;
            this.columnType = columnType;
        }

        boolean isEnum() {
            return this == ENUMERATION
               || this == ENUMERATION_ARRAY
               || this == ENUM_STRING;
        }

        String getName() {
            return value;
        }

    }
