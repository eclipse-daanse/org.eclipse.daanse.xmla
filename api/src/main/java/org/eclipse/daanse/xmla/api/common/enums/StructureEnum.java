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

public enum StructureEnum {

    /**
     * Hierarchy is a fully balanced structure.
     */
    HIERARCHY_FULLY_BALANCED(0),

    /**
     * Hierarchy is a ragged balanced structure.
     */
    HIERARCHY_RAGGED_BALANCED(1),

    /**
     * Hierarchy is an unbalanced
     */
    HIERARCHY_UNBALANCED(2),

    /**
     * Hierarchy is a network
     */
    HIERARCHY_NETWORK(3);

    private final int value;

    StructureEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static StructureEnum fromValue(String v) {
        int vi = Integer.parseInt(v);
        return Stream.of(StructureEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("StructureEnum Illegal argument ").append(v).toString()));
    }
}
