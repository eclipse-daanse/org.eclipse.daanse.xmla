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

public enum InterfaceNameEnum {

    DATETIME, LOGICAL, FILTER, NAVIGATION, STATISTICAL, STRING, NUMERIC, SET, TUPLE, MEMBER, LEVEL, HIERARCHY,
    DIMENSION, ARRAY, SUBCUBE, METADATA, KPI, UDF, VALUE, OTHER;

    /**
     * Lenient: unknown values map to {@link #OTHER} so that client-supplied
     * restrictions never fail the whole discover request.
     */
    public static InterfaceNameEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(InterfaceNameEnum.values()).filter(e -> (e.name().equalsIgnoreCase(v))).findFirst()
                .orElse(OTHER);
    }
}
