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

package org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import java.util.stream.Stream;

@XmlType(name = "ACubeType")
@XmlEnum
public enum CubeTypeEnum {

    @XmlEnumValue("CUBE")
    CUBE,

    @XmlEnumValue("DIMENSION")
    DIMENSION;

    public static CubeTypeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        return Stream.of(CubeTypeEnum.values()).filter(e -> (e.name().equals(v))).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("CubeTypeEnum Illegal argument ").append(v).toString()));
    }
}
