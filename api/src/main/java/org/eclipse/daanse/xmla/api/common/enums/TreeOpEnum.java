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

import org.eclipse.daanse.xmla.api.annotation.Enumerator;

/**
 * Applies only to a single member:
 */
@Enumerator(name = "TREE_OP")
public enum TreeOpEnum {

    /**
     * Returns all of the ancestors.
     */
    ALL(0x20),

    /**
     * Returns only the immediate children.
     */
    CHILDREN(0x01),

    /**
     * Returns members on the same level.
     */
    LEVEL(0x02),

    /**
     * Returns only the immediate parent.
     */
    PARENT(0x04),

    /**
     * Returns only itself.
     */
    ITSELF(0x08),

    /**
     * Returns all of the descendants.
     */
    ALL_DESCENDANTS(0x10);

    private final int value;

    TreeOpEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static TreeOpEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(TreeOpEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("TreeOpEnum Illegal argument ").append(v).toString()));
    }
}
