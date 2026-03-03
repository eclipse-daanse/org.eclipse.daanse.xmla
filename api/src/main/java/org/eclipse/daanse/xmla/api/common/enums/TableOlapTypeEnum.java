/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 */
package org.eclipse.daanse.xmla.api.common.enums;

import java.util.stream.Stream;

public enum TableOlapTypeEnum {

    /**
     * CUBE_DIMENSION indicates that the object is a cube dimension.
     * <p>
     * MEASURE_GROUP indicates that the object is a measure group.
     * <p>
     * SCHEMA indicates that the object is a schema rowset table.
     */
    CUBE_DIMENSION("CUBE_DIMENSION"), MEASURE_GROUP("MEASURE_GROUP"), SCHEMA("SCHEMA");

    private final String value;

    TableOlapTypeEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static TableOlapTypeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(TableOlapTypeEnum.values()).filter(e -> (e.value.equals(v))).findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                new StringBuilder("TableOlapTypeEnum Illegal argument ").append(v).toString()));
    }
}
