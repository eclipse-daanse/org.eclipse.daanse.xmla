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
 * The type of the member
 */
public enum MemberTypeEnum {

    /**
     * Is a regular member.
     */
    REGULAR_MEMBER(1),

    /**
     * Is the All member.
     */
    ALL_MEMBER(2),

    /**
     * Is a measure.
     */
    MEASURE(3),

    /**
     * Is a formula.
     */
    FORMULA(4),

    /**
     * Is of unknown type.
     */
    UNKNOWN(0);

    private final int value;

    MemberTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static MemberTypeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.parseInt(v);
        return Stream.of(MemberTypeEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("MemberTypeEnum Illegal argument ").append(v).toString()));
    }
}
