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

import java.util.stream.Stream;

public enum ItemTypeEnum {

    INTEGER("xsd:int"),

    DECIMAL("xsd:decimal"),

    DOUBLE("xsd:double"),

    STRING("xsd:string"),

	DATETIME("xsd:dateTime"),

	UNSIGNEDSHOT("xsd:unsignedShort"),

	UNSIGNEDINT("xsd:xsd:unsignedInt"),

	BOOLEAN("xsd:boolean");

    private final String value;

    ItemTypeEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static ItemTypeEnum fromValue(String v) {
        if (v != null) {
            return Stream.of(ItemTypeEnum.values()).filter(e -> (e.value.equals(v))).findFirst()
                    .orElse(ItemTypeEnum.STRING);
        }
        return null;
    }

}
