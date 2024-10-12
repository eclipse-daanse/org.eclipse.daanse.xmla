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
 * The content type of the property. Built-in values are values listed as follows. This enumeration
 * is extensible and additional values can be added by users.
 */
public enum PropertyContentTypeEnum {

    REGULAR(0x00), ID(0x01), RELATION_TO_PARENT(0x02), ROLLUP_OPERATOR(0x03), ORGANIZATION_TITLE(0x11), CAPTION(0x21),
    CAPTION_SHORT(0x22), CAPTION_DESCRIPTION(0x23), CAPTION_ABBREVIATION(0x24), WEB_URL(0x31), WEB_HTML(0x32),
    WEB_XML_OR_XSL(0x33), WEB_MAIL_ALIAS(0x34), ADDRESS(0x41), ADDRESS_STREET(0x42), ADDRESS_HOUSE(0x43),
    ADDRESS_CITY(0x44), ADDRESS_STATE_OR_PROVINCE(0x45), ADDRESS_ZIP(0x46), ADDRESS_QUARTER(0x47),
    ADDRESS_COUNTRY(0x48), ADDRESS_BUILDING(0x49), ADDRESS_ROOM(0x4A), ADDRESS_FLOOR(0x4B), ADDRESS_FAX(0x4C),
    ADDRESS_PHONE(0x4D), GEOGRAPHY_CENTROIDX(0x61), GEOGRAPHY_CENTROIDY(0x62), GEOGRAPHY_CENTROIDZ(0x63),
    GEOGRAPHY_BOUNDARY_TOP(0x64), GEOGRAPHY_BOUNDARY_LEFT(0x65), GEOGRAPHY_BOUNDARY_BOTTOM(0x66),
    GEOGRAPHY_BOUNDARY_RIGHT(0x67), GEOGRAPHY_BOUNDARY_FRONT(0x68), GEOGRAPHY_BOUNDARY_REAR(0x69),
    GEOGRAPHY_BOUNDARY_POLYGON(0x6A), PHYSICAL_SIZE(0x71), PHYSICAL_COLOR(0x72), PHYSICAL_WEIGHT(0x73),
    PHYSICAL_HEIGHT(0x74), PHYSICAL_WIDTH(0x75), PHYSICAL_DEPTH(0x76), PHYSICAL_VOLUME(0x77), PHYSICAL_DENSITY(0x78),
    PERSON_FULL_NAME(0x82), PERSON_FIRST_NAME(0x83), PERSON_LAST_NAME(0x84), PERSON_MIDDLE_NAME(0x85),
    PERSON_DEMOGRAPHIC(0x86), PERSON_CONTACT(0x87), QUANTITY_RANGE_LOW(0x91), QUANTITY_RANGE_HIGH(0x92),
    FORMATTING_COLOR(0xA1), FORMATTING_ORDER(0xA2), FORMATTING_FONT(0xA3), FORMATTING_FONT_EFFECTS(0xA4),
    FORMATTING_FONT_SIZE(0xA5), FORMATTING_SUB_TOTAL(0xA6), DATE(0xB1), DATE_START(0xB2), DATE_ENDED(0xB3),
    DATE_CANCELED(0xB4), DATE_MODIFIED(0xB5), DATE_DURATION(0xB6), VERSION(0xC1);

    private final int value;

    PropertyContentTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static PropertyContentTypeEnum fromValue(String v) {
        if (v == null) {
            return null;
        }
        int vi = Integer.decode(v);
        return Stream.of(PropertyContentTypeEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("PropertyContentTypeEnum Illegal argument ").append(v).toString()));
    }
}
