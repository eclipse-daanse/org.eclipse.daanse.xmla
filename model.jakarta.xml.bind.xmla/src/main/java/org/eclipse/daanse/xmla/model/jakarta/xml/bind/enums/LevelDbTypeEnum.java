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

@XmlType(name = "LevelDbType")
@XmlEnum
public enum LevelDbTypeEnum {

    /**
     * Indicates that no value was specified.
     */
    @XmlEnumValue("0")
    DBTYPE_EMPTY(0),

    /**
     * Indicates a two-byte signed integer.
     */
    @XmlEnumValue("2")
    DBTYPE_I2(2),

    /**
     * Indicates a four-byte signed integer.
     */
    @XmlEnumValue("3")
    DBTYPE_I4(3),

    /**
     * Indicates a single- precision floating-point value.
     */
    @XmlEnumValue("4")
    DBTYPE_R4(4),

    /**
     * Indicates a double- precision floating-point value.
     */
    @XmlEnumValue("5")
    DBTYPE_R8(5),

    /**
     * Indicates a currency value. Currency is a fixed-point number with four digits to the right of the
     * decimal point and is stored in an eight-byte signed integer scaled by 10,000.
     */
    @XmlEnumValue("6")
    DBTYPE_CY(6),

    /**
     * Indicates a date value. Date values are stored as Double, the whole part of which is the number
     * of days since December 30, 1899, and the fractional part of which is the fraction of a day.
     */
    @XmlEnumValue("7")
    DBTYPE_DATE(7),

    /**
     * A pointer to a BSTR, which is a null-terminated character string in which the string length is
     * stored with the string.
     */
    @XmlEnumValue("8")
    DBTYPE_BSTR(8),

    /**
     * Indicates a pointer to an IDispatch interface on an OLE object.
     */
    @XmlEnumValue("9")
    DBTYPE_IDISPATCH(9),

    /**
     * Indicates a 32-bit error code.
     */
    @XmlEnumValue("10")
    DBTYPE_ERROR(10),

    /**
     * Indicates a Boolean value.
     */
    @XmlEnumValue("11")
    DBTYPE_BOOL(11),

    /**
     * Indicates an Automation variant.
     */
    @XmlEnumValue("12")
    DBTYPE_VARIANT(12),

    /**
     * Indicates a pointer to an IUnknown interface on an OLE object.
     */
    @XmlEnumValue("13")
    DBTYPE_IUNKNOWN(13),

    /**
     * Indicates an exact numeric value with a fixed precision and scale. The scale is between 0 and 28.
     */
    @XmlEnumValue("14")
    DBTYPE_DECIMAL(14),

    /**
     * Indicates a one-byte signed integer.
     */
    @XmlEnumValue("16")
    DBTYPE_I1(16),

    /**
     * Indicates a one-byte unsigned integer.
     */
    @XmlEnumValue("17")
    DBTYPE_UI1(17),

    /**
     * Indicates a two-byte unsigned integer.
     */
    @XmlEnumValue("18")
    DBTYPE_UI2(18),

    /**
     * Indicates a four-byte unsigned integer.
     */
    @XmlEnumValue("19")
    DBTYPE_UI4(19),

    /**
     * Indicates an eight-byte signed integer.
     */
    @XmlEnumValue("20")
    DBTYPE_I8(20),

    /**
     * Indicates an eight-byte unsigned integer.
     */
    @XmlEnumValue("21")
    DBTYPE_UI8(21),

    /**
     * Indicates a GUID.
     */
    @XmlEnumValue("72")
    DBTYPE_GUID(72),

    /**
     * Indicates a binary value.
     */
    @XmlEnumValue("128")
    DBTYPE_BYTES(128),

    /**
     * Indicates a string value.
     */
    @XmlEnumValue("129")
    DBTYPE_STR(129),

    /**
     * Indicates a null- terminated Unicode character string.
     */
    @XmlEnumValue("130")
    DBTYPE_WSTR(130),

    /**
     * Indicates an exact numeric value with a fixed precision and scale. The scale is between 0 and 38.
     */
    @XmlEnumValue("131")
    DBTYPE_NUMERIC(131),

    /**
     * Indicates a user-defined variable.
     */
    @XmlEnumValue("132")
    DBTYPE_UDT(132),

    /**
     * Indicates a date value (yyyymmdd).
     */
    @XmlEnumValue("133")
    DBTYPE_DBDATE(133),

    /**
     * Indicates a time value (hhmmss).
     */
    @XmlEnumValue("134")
    DBTYPE_DBTIME(134),

    /**
     * Indicates a date-time stamp (yyyymmddhhmmss plus a fraction in billionths).
     */
    @XmlEnumValue("135")
    DBTYPE_DBTIMESTAMP(135),

    /**
     * Indicates a four-byte chapter value used to identify rows in a child rowset.
     */
    @XmlEnumValue("136")
    DBTYPE_HCHAPTER(136);

    private final int value;

    LevelDbTypeEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static LevelDbTypeEnum fromValue(int v) {
        return Stream.of(LevelDbTypeEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("LevelDbTypeEnum Illegal argument ").append(v).toString()));
    }
}
