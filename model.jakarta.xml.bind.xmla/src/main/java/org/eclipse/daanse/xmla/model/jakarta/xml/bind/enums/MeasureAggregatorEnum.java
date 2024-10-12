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
 * An enumeration that identifies how a measure was derived. This enumeration can be one of the
 * following values:
 */
@XmlType(name = "MeasureAggregator")
@XmlEnum
public enum MeasureAggregatorEnum {

    /**
     * MEASURE aggregates from SUM.
     */
    @XmlEnumValue("1")
    MDMEASURE_AGGR_SUM(1),

    /**
     * MEASURE aggregates from COUNT.
     */
    @XmlEnumValue("2")
    MDMEASURE_AGGR_COUNT(2),

    /**
     * MEASURE aggregates from MIN.
     */
    @XmlEnumValue("3")
    MDMEASURE_AGGR_MIN(3),

    /**
     * MEASURE aggregates from MAX.
     */
    @XmlEnumValue("4")
    MDMEASURE_AGGR_MAX(4),

    /**
     * MEASURE aggregates from AVG.
     */
    @XmlEnumValue("5")
    MDMEASURE_AGGR_AVG(5),

    /**
     * MEASURE aggregates from VAR.
     */
    @XmlEnumValue("6")
    MDMEASURE_AGGR_VAR(6),

    /**
     * Identifies that the measure aggregates from STDEV.
     */
    @XmlEnumValue("7")
    MDMEASURE_AGGR_STD(7),

    /**
     * Distinct Count: The aggregation is a count of unique members.
     */
    @XmlEnumValue("8")
    MDMEASURE_AGGR_DST(8),

    /**
     * None: No aggregation is applied.
     */
    @XmlEnumValue("9")
    MDMEASURE_AGGR_NONE(9),

    /**
     * Average of Children: The aggregation of a member is the average of its children.
     */
    @XmlEnumValue("10")
    MDMEASURE_AGGR_AVGCHILDREN(10),

    /**
     * First Child: The member value is evaluated as the value of its first child along the time
     * dimension.
     */
    @XmlEnumValue("11")
    MDMEASURE_AGGR_FIRSTCHILD(11),

    /**
     * Last Child: The member value is evaluated as the value of its last child along the time
     * dimension.
     */
    @XmlEnumValue("12")
    MDMEASURE_AGGR_LASTCHILD(12),

    /**
     * First Non-Empty: The member value is evaluated as the value of its first child along the time
     * dimension that contains data.
     */
    @XmlEnumValue("13")
    MDMEASURE_AGGR_FIRSTNONEMPTY(13),

    /**
     * Last Non-Empty: The member value is evaluated as the value of its last child along the time
     * dimension that contains data.
     */
    @XmlEnumValue("14")
    MDMEASURE_AGGR_LASTNONEMPTY(14),

    /**
     * ByAccount: The system uses the semiadditive behavior specified for the account type.
     */
    @XmlEnumValue("15")
    MDMEASURE_AGGR_BYACCOUNT(15),

    /**
     * Identifies that the measure was derived from a formula that was not any of the single functions
     * listed in any of the preceding single functions.
     */
    @XmlEnumValue("127")
    MDMEASURE_AGGR_CALCULATED(127),

    /**
     * Identifies that the measure was derived from an unknown aggregation function or formula.
     */
    @XmlEnumValue("0")
    MDMEASURE_AGGR_UNKNOWN(0);

    private final int value;

    MeasureAggregatorEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static MeasureAggregatorEnum fromValue(int v) {
        return Stream.of(MeasureAggregatorEnum.values()).filter(e -> (e.value == v)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("MeasureAggregatorEnum Illegal argument ").append(v).toString()));
    }
}
