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
 * An enumeration that identifies how a measure was derived. This enumeration can be one of the
 * following values:
 */
public enum MeasureAggregatorEnum {

    /**
     * MEASURE aggregates from SUM.
     */
    MDMEASURE_AGGR_SUM(1),

    /**
     * MEASURE aggregates from COUNT.
     */
    MDMEASURE_AGGR_COUNT(2),

    /**
     * MEASURE aggregates from MIN.
     */
    MDMEASURE_AGGR_MIN(3),

    /**
     * MEASURE aggregates from MAX.
     */
    MDMEASURE_AGGR_MAX(4),

    /**
     * MEASURE aggregates from AVG.
     */
    MDMEASURE_AGGR_AVG(5),

    /**
     * MEASURE aggregates from VAR.
     */
    MDMEASURE_AGGR_VAR(6),

    /**
     * Identifies that the measure aggregates from STDEV.
     */
    MDMEASURE_AGGR_STD(7),

    /**
     * Distinct Count: The aggregation is a count of unique members.
     */
    MDMEASURE_AGGR_DST(8),

    /**
     * None: No aggregation is applied.
     */
    MDMEASURE_AGGR_NONE(9),

    /**
     * Average of Children: The aggregation of a member is the average of its children.
     */
    MDMEASURE_AGGR_AVGCHILDREN(10),

    /**
     * First Child: The member value is evaluated as the value of its first child along the time
     * dimension.
     */
    MDMEASURE_AGGR_FIRSTCHILD(11),

    /**
     * Last Child: The member value is evaluated as the value of its last child along the time
     * dimension.
     */
    MDMEASURE_AGGR_LASTCHILD(12),

    /**
     * First Non-Empty: The member value is evaluated as the value of its first child along the time
     * dimension that contains data.
     */
    MDMEASURE_AGGR_FIRSTNONEMPTY(13),

    /**
     * Last Non-Empty: The member value is evaluated as the value of its last child along the time
     * dimension that contains data.
     */
    MDMEASURE_AGGR_LASTNONEMPTY(14),

    /**
     * ByAccount: The system uses the semiadditive behavior specified for the account type.
     */
    MDMEASURE_AGGR_BYACCOUNT(15),

    /**
     * Identifies that the measure was derived from a formula that was not any of the single functions
     * listed in any of the preceding single functions.
     */
    MDMEASURE_AGGR_CALCULATED(127),

    /**
     * Identifies that the measure was derived from an unknown aggregation function or formula.
     */
    MDMEASURE_AGGR_UNKNOWN(0);

    private final int value;

    MeasureAggregatorEnum(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static MeasureAggregatorEnum fromValue(String v) {
        int vi = Integer.parseInt(v);
        return Stream.of(MeasureAggregatorEnum.values()).filter(e -> (e.value == vi)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        new StringBuilder("MeasureAggregatorEnum Illegal argument ").append(v).toString()));
    }
}
