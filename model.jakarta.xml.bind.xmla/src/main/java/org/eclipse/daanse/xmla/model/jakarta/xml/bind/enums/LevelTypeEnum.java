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

/**
 * The type of the level
 */
@XmlType(name = "LevelType")
@XmlEnum
public enum LevelTypeEnum {

    @XmlEnumValue("0x1014")
    ACCOUNT(0x1014),

    @XmlEnumValue("0x0001")
    ALL(0x0001),

    @XmlEnumValue("0x1012")
    BILL_OF_MATERIAL_RESOURCE(0x1012),

    @XmlEnumValue("0x0002")
    CALCULATED(0x0002),

    @XmlEnumValue("0x1061")
    CHANNEL(0x1061),

    @XmlEnumValue("0x1042")
    COMPANY(0x1042),

    @XmlEnumValue("0x1052")
    CURRENCY_DESTINATION(0x1052),

    @XmlEnumValue("0x1051")
    CURRENCY_SOURCE(0x1051),

    @XmlEnumValue("0x1021")
    CUSTOMER(0x1021),

    @XmlEnumValue("0x1022")
    CUSTOMER_GROUP(0x1022),

    @XmlEnumValue("0x1023")
    CUSTOMER_HOUSEHOLD(0x1023),

    @XmlEnumValue("0x2006")
    GEOGRAPHY_CITY(0x2006),

    @XmlEnumValue("0x2001")
    GEOGRAPHY_CONTINENT(0x2001),

    @XmlEnumValue("0x2003")
    GEOGRAPHY_COUNTRY(0x2003),

    @XmlEnumValue("0x2005")
    GEOGRAPHY_COUNTY(0x2005),

    @XmlEnumValue("0x2008")
    GEOGRAPHY_POINT(0x2008),

    @XmlEnumValue("0x2007")
    POSTAL_CODE(0x2007),

    @XmlEnumValue("0x2002")
    GEOGRAPHY_REGION(0x2002),

    @XmlEnumValue("0x2004")
    GEOGRAPHY_STATE_OR_PROVINCE(0x2004),

    @XmlEnumValue("0x1011")
    ORGANIZATION_UNIT(0x1011),

    @XmlEnumValue("0x1041")
    PERSON(0x1041),

    @XmlEnumValue("0x1031")
    PRODUCT(0x1031),

    @XmlEnumValue("0x1032")
    PRODUCT_GROUP(0x1032),

    @XmlEnumValue("0x1071")
    PROMOTION(0x1071),

    @XmlEnumValue("0x1013")
    QUANTITATIVE(0x1013),

    @XmlEnumValue("0x0000")
    REGULAR(0x0000),

    @XmlEnumValue("0x1062")
    REPRESENTATIVE(0x1062),

    @XmlEnumValue("0x0008")
    RESERVED_1(0x0008),

    @XmlEnumValue("0x1015")
    SCENARIO(0x1015),

    @XmlEnumValue("0x0004")
    TIME(0x0004),

    @XmlEnumValue("0x0204")
    TIME_DAYS(0x0204),

    @XmlEnumValue("0x0024")
    TIME_HALF_YEARS(0x0024),

    @XmlEnumValue("0x0044")
    TIME_QUARTERS(0x0044),

    @XmlEnumValue("0x0804")
    TIME_SECONDS(0x0804),

    @XmlEnumValue("0x1004")
    TIME_UNDEFINED(0x1004),

    @XmlEnumValue("0x0104")
    TIME_WEEKS(0x0104),

    @XmlEnumValue("0x0014")
    TIME_YEARS(0x0014),

    @XmlEnumValue("0x1016")
    UTILITY(0x1016);

    private final int value;

    LevelTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static LevelTypeEnum fromValue(int v) {
        return Stream.of(LevelTypeEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("LevelTypeEnum Illegal argument ").append(v).toString()));
    }
}
