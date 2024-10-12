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
*/
package org.eclipse.daanse.xmla.api.xmla;

public enum NullProcessingEnum {

    /**
     * The server determines how null processing is handled.<97> "Automatic" uses the default processing
     * that is appropriate for the element
     */
    AUTOMATIC("Automatic"),

    /**
     * Generates an unknown member. This value MUST NOT be used if the column is associated with a
     * measure.
     */
    UNKNOWN_MEMBER("UnknownMember"),

    /**
     * Converts the null value to zero (for numeric data items) or a blank string (for string data
     * items)
     */
    ZERO_OR_BLANK("ZeroOrBlank"),

    /**
     * Preserves the null value.
     */
    PRESERVE("Preserve"),

    /**
     * Raises an error. Value "Error" is not supported for measures. This value MUST NOT be used if the
     * column is associated with a measure
     */
    ERROR("Error");

    private final String value;

    NullProcessingEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static NullProcessingEnum fromValue(String v) {
        if (v == null) {
            return NullProcessingEnum.AUTOMATIC;
        }
        for (NullProcessingEnum e : NullProcessingEnum.values()) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(
                new StringBuilder("NullProcessingEnum Illegal argument ").append(v).toString());
    }

}
