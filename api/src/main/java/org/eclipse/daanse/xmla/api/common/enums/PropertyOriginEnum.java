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

/**
 * A bitmask that specifies the type of hierarchy to which the property applies, as follows:
 */
public enum PropertyOriginEnum {

    /**
     * Indicates the property is on a user defined hierarchy.
     */
    USER_DEFINED(1),

    /**
     * Indicates the property is on an attribute hierarchy.
     */
    ATTRIBUTE_HIERARCHY(2),

    /**
     * Indicates the property is on a key attribute hierarchy.
     */
    KEY_ATTRIBUTE_HIERARCHY(4),

    /**
     * Indicates the property is on an attribute hierarchy that is not enabled.
     */
    ATTRIBUTE_HIERARCHY_NOT_ENABLED(8);

    private final int value;

    PropertyOriginEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static PropertyOriginEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.parseInt(v);
        return Stream.of(PropertyOriginEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("PropertyOriginEnum Illegal argument ").append(v).toString()));
    }
}
