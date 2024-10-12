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

public enum VisibilityEnum {

    VISIBLE(0x01), NOT_VISIBLE(0x02), ALL(0x03);

    private final int value;

    VisibilityEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static VisibilityEnum fromValue(String v) {
        if (v == null) {
            return VISIBLE;
        }
        // TODO: check the true and false value, is this Specification complaint?
        else if ("true".equals(v)) {
            return VISIBLE;
        } else if ("false".equals(v)) {
            return NOT_VISIBLE;
        }
        int vi = Integer.decode(v);
        return Stream.of(VisibilityEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("VisibilityEnum Illegal argument: ").append(v).toString()));
    }
}
