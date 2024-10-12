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
package org.eclipse.daanse.xmla.model.record.xmla;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.FiscalYearNameEnum;
import org.eclipse.daanse.xmla.api.xmla.ReportingWeekToMonthPatternEnum;
import org.eclipse.daanse.xmla.api.xmla.TimeBinding;

public record TimeBindingR(Instant calendarStartDate, Instant calendarEndDate, Optional<Integer> firstDayOfWeek,
        Optional<BigInteger> calendarLanguage, Optional<Integer> fiscalFirstMonth,
        Optional<Integer> fiscalFirstDayOfMonth, Optional<FiscalYearNameEnum> fiscalYearName,
        Optional<Integer> reportingFirstMonth, Optional<String> reportingFirstWeekOfMonth,
        Optional<ReportingWeekToMonthPatternEnum> reportingWeekToMonthPattern,
        Optional<Integer> manufacturingFirstMonth, Optional<Integer> manufacturingFirstWeekOfMonth,
        Optional<Integer> manufacturingExtraMonthQuarter) implements TimeBinding {

}
