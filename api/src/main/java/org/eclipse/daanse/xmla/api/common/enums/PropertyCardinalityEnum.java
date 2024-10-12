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
package org.eclipse.daanse.xmla.api.common.enums;

import java.util.stream.Stream;

/**
 * The cardinality of the property. Possible values include the following strings:
 */
public enum PropertyCardinalityEnum {

    ONE, MANY;

    public static PropertyCardinalityEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(PropertyCardinalityEnum.values()).filter(e -> (e.name().equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("PropertyCardinalityEnum Illegal argument ").append(v).toString()));
    }
}
