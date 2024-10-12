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

@XmlType(name = "ActionType")
@XmlEnum
public enum ActionTypeEnum {

    /**
     * Action type is URL.
     */
    @XmlEnumValue("0x01")
    URL(0x01),

    /**
     * Action type is HTML.
     */
    @XmlEnumValue("0x02")
    HTML(0x02),

    /**
     * Action type is Statement.
     */
    @XmlEnumValue("0x04")
    STATEMENT(0x04),

    /**
     * Action type is Dataset.
     */
    @XmlEnumValue("0x08")
    DATASET(0x08),

    /**
     * Action type is Rowset.
     */
    @XmlEnumValue("0x10")
    ROW_SET(0x10),

    /**
     * Action type is Commandline.
     */
    @XmlEnumValue("0x20")
    COMMANDLINE(0x20),

    /**
     * Action type is Proprietary.
     */
    @XmlEnumValue("0x40")
    PROPRIETARY(0x40),

    /**
     * Action type is Report.
     */
    @XmlEnumValue("0x80")
    REPORT(0x80),

    /**
     * Action type is DrillThrough.
     */
    @XmlEnumValue("0x100")
    DRILL_THROUGH(0x100);

    private final int value;

    ActionTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static ActionTypeEnum fromValue(int v) {
        return Stream.of(ActionTypeEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("ActionTypeEnum Illegal argument ").append(v).toString()));
    }
}
