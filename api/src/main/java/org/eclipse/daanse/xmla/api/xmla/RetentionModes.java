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

public enum RetentionModes {

    ALLOW_SINGLE_EVENT_LOSS("allowSingleEventLoss"), ALLOW_MULTIPLE_EVENT_LOSS("allowMultipleEventLoss"),
    NO_EVENT_LOSS("noEventLoss");

    private final String value;

    RetentionModes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RetentionModes fromValue(String v) {
        for (RetentionModes c : RetentionModes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
