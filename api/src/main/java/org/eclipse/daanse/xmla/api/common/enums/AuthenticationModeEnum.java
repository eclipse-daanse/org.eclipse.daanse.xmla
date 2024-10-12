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

import org.eclipse.daanse.xmla.api.annotation.Enumerator;

import java.util.stream.Stream;

@Enumerator(name = "AuthenticationMode")
public enum AuthenticationModeEnum {

    /**
     * No user ID or password has to be sent.
     */
    UNAUTHENTICATED("Unauthenticated"),

    /**
     * User ID and password MUST be included in the information required to connect to the data source.
     */
    AUTHENTICATED("Authenticated"),

    /**
     * The data source uses the underlying security to determine authorization.
     */
    INTEGRATED("Integrated");

    private final String value;

    AuthenticationModeEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static AuthenticationModeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(AuthenticationModeEnum.values()).filter(e -> (e.getValue().equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("AuthenticationModeEnum Illegal argument ").append(v).toString()));
    }
}
