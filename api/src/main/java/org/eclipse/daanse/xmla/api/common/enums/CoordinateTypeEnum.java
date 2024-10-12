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

public enum CoordinateTypeEnum {

    /**
     * Action coordinate refers to the cube.
     */
    CUBE(1),

    /**
     * Action coordinate refers to a dimension.
     */
    DIMENSION(2),

    /**
     * Action coordinate refers to a level.
     */
    LEVEL(3),

    /**
     * Action coordinate refers to a member.
     */
    MEMBER(4),

    /**
     * Action coordinate refers to a set.
     */
    SET(5),

    /**
     * Action coordinate refers to a cell.
     */
    CELL(6);

    private final int value;

    CoordinateTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static CoordinateTypeEnum fromValue(String v) {
        int vi = Integer.parseInt(v);
        return Stream.of(CoordinateTypeEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("CoordinateTypeEnum Illegal argument ").append(v).toString()));
    }
}
