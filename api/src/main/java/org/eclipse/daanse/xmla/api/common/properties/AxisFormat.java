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

public enum AxisFormat {

    /**
     * The MDDataSet axis is made up of one or more CrossProduct elements.
     */
    TUPLE_FORMAT("TupleFormat"),

    /**
     * Analysis Services uses the TupleFormat format for this setting.
     */
    CLUSTER_FORMAT("ClusterFormat"),

    /**
     * The MDDataSet axis contains one or more Tuple elements.
     */
    CUSTOM_FORMAT("CustomFormat");

    private final String value;

    AxisFormat(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static AxisFormat fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(AxisFormat.values()).filter(e -> (e.getValue().equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("AxisFormat Illegal argument ").append(v).toString()));
    }

}
