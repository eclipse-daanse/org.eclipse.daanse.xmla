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
 * A string that specifies whether the role has permission to read the XML definition of the object
 * or any of its contained objects using DISCOVER_XML_METADATA. "None" implies no access to object
 * definition. "Basic" implies limited access to object definition. "Allowed" implies full access to
 * object definition.
 */
public enum ReadDefinitionEnum {

    /**
     * implies no access to object definition.
     */
    NONE("None"),

    /**
     * implies limited access to object definition.
     */
    BASIC("Basic"),

    /**
     * implies full access to object definition.
     */
    ALLOWED("Allowed");

    private final String value;

    ReadDefinitionEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static ReadDefinitionEnum fromValue(String v) {
        if (v == null) {
            return NONE;
        }
        for (ReadDefinitionEnum e : ReadDefinitionEnum.values()) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(
                new StringBuilder("ReadDefinitionEnum Illegal argument ").append(v).toString());
    }

}
