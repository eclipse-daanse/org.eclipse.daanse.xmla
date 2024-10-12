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

/**
 * The type of the level
 */
public enum LevelTypeEnum {

    ACCOUNT(0x1014), ALL(0x0001), BILL_OF_MATERIAL_RESOURCE(0x1012), CALCULATED(0x0002), CHANNEL(0x1061),
    COMPANY(0x1042), CURRENCY_DESTINATION(0x1052), CURRENCY_SOURCE(0x1051), CUSTOMER(0x1021), CUSTOMER_GROUP(0x1022),
    CUSTOMER_HOUSEHOLD(0x1023), GEOGRAPHY_CITY(0x2006), GEOGRAPHY_CONTINENT(0x2001), GEOGRAPHY_COUNTRY(0x2003),
    GEOGRAPHY_COUNTY(0x2005), GEOGRAPHY_POINT(0x2008), POSTAL_CODE(0x2007), GEOGRAPHY_REGION(0x2002),
    GEOGRAPHY_STATE_OR_PROVINCE(0x2004), ORGANIZATION_UNIT(0x1011), PERSON(0x1041), PRODUCT(0x1031),
    PRODUCT_GROUP(0x1032), PROMOTION(0x1071), QUANTITATIVE(0x1013), REGULAR(0x0000), REPRESENTATIVE(0x1062),
    RESERVED_1(0x0008), SCENARIO(0x1015), TIME(0x0004), TIME_DAYS(0x0204), TIME_HALF_YEARS(0x0024),
    TIME_QUARTERS(0x0044), TIME_SECONDS(0x0804), TIME_UNDEFINED(0x1004), TIME_WEEKS(0x0104), TIME_YEARS(0x0014),
    UTILITY(0x1016);

    private final int value;

    LevelTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static LevelTypeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(LevelTypeEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("LevelTypeEnum Illegal argument ").append(v).toString()));
    }
}
