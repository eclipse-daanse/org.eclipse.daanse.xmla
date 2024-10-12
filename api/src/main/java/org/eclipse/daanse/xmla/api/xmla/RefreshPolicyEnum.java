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
package org.eclipse.daanse.xmla.api.xmla;

/**
 * Determines how often the dynamic part of the dimension or measure group (as specified by the
 * Persistence element) is checked for changes. Enumeration values are as follows: ByQuery - Every
 * query checks to see whether the source data has changed. ByInterval - Source data is checked for
 * changes only at the interval that is specified by the RefreshInterval element.
 */
public enum RefreshPolicyEnum {

    /**
     * Every query checks to see whether the source data has changed.
     */
    BY_QUERY("ByQuery"),

    /**
     * Source data is checked for changes only at the interval that is specified by the RefreshInterval
     * element.
     */
    BY_INTERVAL("ByInterval");

    private final String value;

    RefreshPolicyEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static RefreshPolicyEnum fromValue(String v) {
        if (v == null) {
            return BY_QUERY;
        }
        for (RefreshPolicyEnum e : RefreshPolicyEnum.values()) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(
                new StringBuilder("RefreshPolicyEnum Illegal argument ").append(v).toString());
    }

}
