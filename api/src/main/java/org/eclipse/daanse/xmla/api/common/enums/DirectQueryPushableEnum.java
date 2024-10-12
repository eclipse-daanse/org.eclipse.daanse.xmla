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

public enum DirectQueryPushableEnum {

    /**
     * This function can be used in measure expressions.
     */
    MEASURE(0x1),

    /**
     * This function can be used in calculated column expressions.
     */
    CALCCOL(0x2);

    private final int value;

    DirectQueryPushableEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static DirectQueryPushableEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(DirectQueryPushableEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("DirectQueryPushableEnum Illegal argument ").append(v).toString()));
    }
}
