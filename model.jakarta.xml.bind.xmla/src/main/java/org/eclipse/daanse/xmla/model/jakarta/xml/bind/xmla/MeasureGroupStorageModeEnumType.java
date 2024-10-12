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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "MeasureGroupStorageModeEnumType")
@XmlEnum
public enum MeasureGroupStorageModeEnumType {

    @XmlEnumValue("Molap")
    MOLAP("Molap"), @XmlEnumValue("Rolap")
    ROLAP("Rolap"), @XmlEnumValue("Holap")
    HOLAP("Holap"), @XmlEnumValue("InMemory")
    IN_MEMORY("InMemory");

    private final String value;

    MeasureGroupStorageModeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeasureGroupStorageModeEnumType fromValue(String v) {
        for (MeasureGroupStorageModeEnumType c : MeasureGroupStorageModeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
