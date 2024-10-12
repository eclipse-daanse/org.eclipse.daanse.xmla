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

public enum SearchableEnum {

    /**
     * indicates that the data type cannot be used in a WHERE clause.
     */
    DB_UNSEARCHABLE(0x01),

    /**
     * indicates that the data type can be used in a WHERE clause only with the LIKE predicate.
     */
    DB_LIKE_ONLY(0x02),

    /**
     * indicates that the data type can be used in a WHERE clause with all comparison operators except
     * LIKE.
     */
    DB_ALL_EXCEPT_LIKE(0x03),

    /**
     * indicates that the data type can be used in a WHERE clause with any comparison operator.
     */
    DB_SEARCHABLE(0x04);

    private final int value;

    SearchableEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static SearchableEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(SearchableEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("SearchableEnum Illegal argument ").append(v).toString()));
    }
}
