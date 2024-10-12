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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import java.util.stream.Stream;

/**
 * The cardinality of the property. Possible values include the following strings:
 */
@XmlType(name = "PropertyCardinality")
@XmlEnum
public enum PropertyCardinalityEnum {

    @XmlEnumValue("ONE")
    ONE,

    @XmlEnumValue("MANY")
    MANY;

    public static PropertyCardinalityEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(PropertyCardinalityEnum.values()).filter(e -> (e.name().equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("PropertyCardinalityEnum Illegal argument ").append(v).toString()));
    }
}
