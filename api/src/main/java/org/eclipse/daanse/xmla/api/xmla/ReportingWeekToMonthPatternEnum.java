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

/**
 * The pattern by which to match weeks to months.
 */
public enum ReportingWeekToMonthPatternEnum {

    WEEKS_445("Weeks445"), WEEKS_454("Weeks454"), WEEKS_544("Weeks544");

    private final String value;

    ReportingWeekToMonthPatternEnum(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public static ReportingWeekToMonthPatternEnum fromValue(String v) {
        if (v == null) {
            return WEEKS_445;
        }
        for (ReportingWeekToMonthPatternEnum e : ReportingWeekToMonthPatternEnum.values()) {
            if (e.getValue().equals(v)) {
                return e;
            }
        }
        throw new IllegalArgumentException(new StringBuilder("PersistenceEnum Illegal argument ").append(v).toString());
    }

}
