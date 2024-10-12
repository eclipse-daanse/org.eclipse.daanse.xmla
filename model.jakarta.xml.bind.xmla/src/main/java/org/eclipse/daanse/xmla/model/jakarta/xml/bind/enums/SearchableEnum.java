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

@XmlType(name = "Searchable")
@XmlEnum
public enum SearchableEnum {

    /**
     * indicates that the data type cannot be used in a WHERE clause.
     */
    @XmlEnumValue("0x01")
    DB_UNSEARCHABLE(0x01),

    /**
     * indicates that the data type can be used in a WHERE clause only with the LIKE predicate.
     */
    @XmlEnumValue("0x02")
    DB_LIKE_ONLY(0x02),

    /**
     * indicates that the data type can be used in a WHERE clause with all comparison operators except
     * LIKE.
     */
    @XmlEnumValue("0x03")
    DB_ALL_EXCEPT_LIKE(0x03),

    /**
     * indicates that the data type can be used in a WHERE clause with any comparison operator.
     */
    @XmlEnumValue("0x04")
    DB_SEARCHABLE(0x04);

    private final int value;

    SearchableEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static SearchableEnum fromValue(int v) {
        return Stream.of(SearchableEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("SearchableEnum Illegal argument ").append(v).toString()));
    }
}
