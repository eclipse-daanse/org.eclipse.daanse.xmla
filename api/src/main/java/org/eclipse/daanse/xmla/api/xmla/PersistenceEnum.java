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

public enum PersistenceEnum {

    /**
     * Source metadata, members, and data are all dynamic.
     */
    NOT_PERSISTED("NotPersisted"),

    /**
     * Source metadata is static, but members and data are dynamic.
     */
    METADATA("Metadata");

    private final String value;

    PersistenceEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static PersistenceEnum fromValue(String v) {
        if (v == null) {
            return NOT_PERSISTED;
        }
        for (PersistenceEnum e : PersistenceEnum.values()) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(new StringBuilder("PersistenceEnum Illegal argument ").append(v).toString());
    }

}
