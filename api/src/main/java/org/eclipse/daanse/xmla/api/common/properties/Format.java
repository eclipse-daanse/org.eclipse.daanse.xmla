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
package org.eclipse.daanse.xmla.api.common.properties;

import java.util.stream.Stream;

public enum Format {

    TABULAR("Tabular"), MULTIDIMENSIONAL("Multidimensional"), NATIVE("Native");

    private final String value;

    Format(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static Format fromValue(String v) {
        if (v == null) {
            return null;
        }

        return Stream.of(Format.values()).filter(e -> (e.getValue().equals(v))).findFirst().orElseThrow(
                () -> new IllegalArgumentException(new StringBuilder("Format Illegal argument ").append(v).toString()));
    }
}
