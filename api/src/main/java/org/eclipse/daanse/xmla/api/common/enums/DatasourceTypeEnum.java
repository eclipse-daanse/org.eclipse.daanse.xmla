/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
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

public enum DatasourceTypeEnum {

    RELATIONAL("Relational"), OLAP("Olap");

    private final String value;

    DatasourceTypeEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static DatasourceTypeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(DatasourceTypeEnum.values()).filter(e -> (e.value.equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("DatasourceTypeEnum Illegal argument ").append(v).toString()));
    }
}
