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

public enum ObjectExpansionEnum {

    /**
     * Returns only the name/ID/timestamp/state requested for the requested objects and all descendant
     * major objects recursively.
     */
    REFERENCE_ONLY("ReferenceOnly"),

    /**
     * Expands the requested object with no references to contained objects (includes expanded minor
     * contained objects).
     */
    OBJECT_PROPERTIES("ObjectProperties"),

    /**
     * Same as ObjectProperties, but also returns the name, ID, and timestamp for contained major
     * objects.
     */
    EXPAND_OBJECT("ExpandObject"),

    /**
     * Fully expands the requested object recursively to the bottom of every contained object.
     */
    EXPAND_FULL("ExpandFull");

    private final String value;

    ObjectExpansionEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static ObjectExpansionEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(ObjectExpansionEnum.values()).filter(e -> (e.value.equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("ObjectExpansionEnum Illegal argument ").append(v).toString()));
    }
}
