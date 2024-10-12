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
 * Describes the current hierarchy structure. Values are the following: Natural Unnatural Unknown
 */
public enum StructureTypeEnum {

    NATURAL("Natural"), UNNATURAL("Unnatural"), UNKNOWN("Unknown");

    private final String value;

    StructureTypeEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static StructureTypeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(StructureTypeEnum.values()).filter(e -> (e.value.equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("StructureTypeEnum Illegal argument ").append(v).toString()));
    }
}
