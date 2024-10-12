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
 * Table type. One of the following or a provider-specific
 */
public enum TableTypeEnum {

    ALIAS("ALIAS"), TABLE("TABLE"), SYNONYM("SYNONYM"), SYSTEM_TABLE("SYSTEM TABLE"), VIEW("VIEW"),
    GLOBAL_TEMPORARY("GLOBAL TEMPORARY"), LOCAL_TEMPORARY("LOCAL TEMPORARY"), EXTERNAL_TABLE("EXTERNAL TABLE"),
    SYSTEM_VIEW("SYSTEM VIEW");

    private final String value;

    TableTypeEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static TableTypeEnum fromValue(String v) {
        return Stream.of(TableTypeEnum.values()).filter(e -> (e.value.equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("TableTypeEnum Illegal argument ").append(v).toString()));
    }
}
