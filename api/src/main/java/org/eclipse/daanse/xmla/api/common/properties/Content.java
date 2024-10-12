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

public enum Content {

    NONE("None"), SCHEMA("Schema"), DATA("Data"), SCHEMA_DATA("SchemaData"),
    DATA_OMIT_DEFAULT_SLICER("DataOmitDefaultSlicer"), DATA_INCLUDE_DEFAULT_SLICER("DataIncludeDefaultSlicer");

    private final String value;

    Content(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static Content fromValue(String v) {
        if (v == null) {
            return SCHEMA_DATA;
        }
        return Stream.of(Content.values()).filter(e -> (e.getValue().equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("Content Illegal argument ").append(v).toString()));
    }

    public static final Content DEFAULT = SCHEMA_DATA;
}
