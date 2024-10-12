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
package org.eclipse.daanse.xmla.api.xmla;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Optional;

/**
 * The TimeBinding complex type represents a binding to a time calendar.
 */
public interface TimeBinding extends Binding {

    /**
     * @return The start date of the calendar.
     */
    Instant calendarStartDate();

    /**
     * @return The end date of the calendar. The end date needs to be on or after the start date.
     */
    Instant calendarEndDate();

    /**
     * @return The first day of the week. 1=Sunday 7=Saturday default 1
     */
    Optional<Integer> firstDayOfWeek();

    /**
     * @return The language in which the dimension member names will be created. This MUST be a language
     *         code identifier (LCID) code. default 1033 (English-US)
     */
    Optional<BigInteger> calendarLanguage();

    /**
     * @return The first month of the fiscal calendar. 1=January 12=December default 1
     */
    Optional<Integer> fiscalFirstMonth();

    /**
     * @return The first day of the fiscal calendar. default 1
     */
    Optional<Integer> fiscalFirstDayOfMonth();

    /**
     * @return This enumeration value specifies how the name of the fiscal year is generated.
     */
    Optional<FiscalYearNameEnum> fiscalYearName();

    /**
     * @return The first month of the reporting calendar. 1=January 12=December default 1
     */
    Optional<Integer> reportingFirstMonth();

    /**
     * @return The first week of the reporting calendar. default 1
     */
    Optional<String> reportingFirstWeekOfMonth();

    /**
     * @return The pattern by which to match weeks to months. default Weeks445
     */
    Optional<ReportingWeekToMonthPatternEnum> reportingWeekToMonthPattern();

    /**
     * @return The first month of the manufacturing calendar. 1=January 12=December default 1
     */
    Optional<Integer> manufacturingFirstMonth();

    /**
     * @return The first week of the manufacturing calendar. default 1
     */
    Optional<Integer> manufacturingFirstWeekOfMonth();

    /**
     * @return The quarter of a year that contains the extra month. default 4
     */
    Optional<Integer> manufacturingExtraMonthQuarter();

}
