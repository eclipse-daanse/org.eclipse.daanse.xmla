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

import java.util.stream.Stream;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * The content type of the property. Built-in values are values listed as follows. This enumeration
 * is extensible and additional values can be added by users.
 */
@XmlType(name = "PropertyContentType")
@XmlEnum
public enum PropertyContentTypeEnum {

    @XmlEnumValue("0x00")
    REGULAR(0x00),

    @XmlEnumValue("0x001")
    ID(0x01),

    @XmlEnumValue("0x02")
    RELATION_TO_PARENT(0x02),

    @XmlEnumValue("0x03")
    ROLLUP_OPERATOR(0x03),

    @XmlEnumValue("0x11")
    ORGANIZATION_TITLE(0x11),

    @XmlEnumValue("0x21")
    CAPTION(0x21),

    @XmlEnumValue("0x22")
    CAPTION_SHORT(0x22),

    @XmlEnumValue("0x23")
    CAPTION_DESCRIPTION(0x23),

    @XmlEnumValue("0x24")
    CAPTION_ABBREVIATION(0x24),

    @XmlEnumValue("0x31")
    WEB_URL(0x31),

    @XmlEnumValue("0x32")
    WEB_HTML(0x32),

    @XmlEnumValue("0x33")
    WEB_XML_OR_XSL(0x33),

    @XmlEnumValue("0x34")
    WEB_MAIL_ALIAS(0x34),

    @XmlEnumValue("0x41")
    ADDRESS(0x41),

    @XmlEnumValue("0x42")
    ADDRESS_STREET(0x42),

    @XmlEnumValue("0x43")
    ADDRESS_HOUSE(0x43),

    @XmlEnumValue("0x44")
    ADDRESS_CITY(0x44),

    @XmlEnumValue("0x45")
    ADDRESS_STATE_OR_PROVINCE(0x45),

    @XmlEnumValue("0x46")
    ADDRESS_ZIP(0x46),

    @XmlEnumValue("0x47")
    ADDRESS_QUARTER(0x47),

    @XmlEnumValue("0x48")
    ADDRESS_COUNTRY(0x48),

    @XmlEnumValue("0x49")
    ADDRESS_BUILDING(0x49),

    @XmlEnumValue("0x4A")
    ADDRESS_ROOM(0x4A),

    @XmlEnumValue("0x4B")
    ADDRESS_FLOOR(0x4B),

    @XmlEnumValue("0x4C")
    ADDRESS_FAX(0x4C),

    @XmlEnumValue("0x4D")
    ADDRESS_PHONE(0x4D),

    @XmlEnumValue("0x61")
    GEOGRAPHY_CENTROIDX(0x61),

    @XmlEnumValue("0x62")
    GEOGRAPHY_CENTROIDY(0x62),

    @XmlEnumValue("0x63")
    GEOGRAPHY_CENTROIDZ(0x63),

    @XmlEnumValue("0x64")
    GEOGRAPHY_BOUNDARY_TOP(0x64),

    @XmlEnumValue("0x65")
    GEOGRAPHY_BOUNDARY_LEFT(0x65),

    @XmlEnumValue("0x66")
    GEOGRAPHY_BOUNDARY_BOTTOM(0x66),

    @XmlEnumValue("0x67")
    GEOGRAPHY_BOUNDARY_RIGHT(0x67),

    @XmlEnumValue("0x68")
    GEOGRAPHY_BOUNDARY_FRONT(0x68),

    @XmlEnumValue("0x69")
    GEOGRAPHY_BOUNDARY_REAR(0x69),

    @XmlEnumValue("0x6A")
    GEOGRAPHY_BOUNDARY_POLYGON(0x6A),

    @XmlEnumValue("0x71")
    PHYSICAL_SIZE(0x71),

    @XmlEnumValue("0x72")
    PHYSICAL_COLOR(0x72),

    @XmlEnumValue("0x73")
    PHYSICAL_WEIGHT(0x73),

    @XmlEnumValue("0x74")
    PHYSICAL_HEIGHT(0x74),

    @XmlEnumValue("0x75")
    PHYSICAL_WIDTH(0x75),

    @XmlEnumValue("0x76")
    PHYSICAL_DEPTH(0x76),

    @XmlEnumValue("0x77")
    PHYSICAL_VOLUME(0x77),

    @XmlEnumValue("0x78")
    PHYSICAL_DENSITY(0x78),

    @XmlEnumValue("0x82")
    PERSON_FULL_NAME(0x82),

    @XmlEnumValue("0x83")
    PERSON_FIRST_NAME(0x83),

    @XmlEnumValue("0x84")
    PERSON_LAST_NAME(0x84),

    @XmlEnumValue("0x85")
    PERSON_MIDDLE_NAME(0x85),

    @XmlEnumValue("0x86")
    PERSON_DEMOGRAPHIC(0x86),

    @XmlEnumValue("0x87")
    PERSON_CONTACT(0x87),

    @XmlEnumValue("0x91")
    QUANTITY_RANGE_LOW(0x91),

    @XmlEnumValue("0x92")
    QUANTITY_RANGE_HIGH(0x92),

    @XmlEnumValue("0xA1")
    FORMATTING_COLOR(0xA1),

    @XmlEnumValue("0xA2")
    FORMATTING_ORDER(0xA2),

    @XmlEnumValue("0xA3")
    FORMATTING_FONT(0xA3),

    @XmlEnumValue("0xA4")
    FORMATTING_FONT_EFFECTS(0xA4),

    @XmlEnumValue("0xA5")
    FORMATTING_FONT_SIZE(0xA5),

    @XmlEnumValue("0xA6")
    FORMATTING_SUB_TOTAL(0xA6),

    @XmlEnumValue("0xB1")
    DATE(0xB1),

    @XmlEnumValue("0xB2")
    DATE_START(0xB2),

    @XmlEnumValue("0xB3")
    DATE_ENDED(0xB3),

    @XmlEnumValue("0xB4")
    DATE_CANCELED(0xB4),

    @XmlEnumValue("0xB5")
    DATE_MODIFIED(0xB5),

    @XmlEnumValue("0xB6")
    DATE_DURATION(0xB6),

    @XmlEnumValue("0xC1")
    VERSION(0xC1);

    private final int value;

    PropertyContentTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static PropertyContentTypeEnum fromValue(int v) {
        return Stream.of(PropertyContentTypeEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("PropertyContentTypeEnum Illegal argument ").append(v).toString()));
    }
}
