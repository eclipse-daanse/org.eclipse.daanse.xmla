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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla;

import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeBinding", propOrder = { "calendarStartDate", "calendarEndDate", "firstDayOfWeek",
        "calendarLanguage", "fiscalFirstMonth", "fiscalFirstDayOfMonth", "fiscalYearName", "reportingFirstMonth",
        "reportingFirstWeekOfMonth", "reportingWeekToMonthPattern", "manufacturingFirstMonth",
        "manufacturingFirstWeekOfMonth", "manufacturingExtraMonthQuarter" })
public class TimeBinding extends Binding {

    @XmlElement(name = "CalendarStartDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calendarStartDate;
    @XmlElement(name = "CalendarEndDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calendarEndDate;
    @XmlElement(name = "FirstDayOfWeek")
    protected Integer firstDayOfWeek;
    @XmlElement(name = "CalendarLanguage")
    protected BigInteger calendarLanguage;
    @XmlElement(name = "FiscalFirstMonth")
    protected Integer fiscalFirstMonth;
    @XmlElement(name = "FiscalFirstDayOfMonth")
    protected Integer fiscalFirstDayOfMonth;
    @XmlElement(name = "FiscalYearName")
    protected String fiscalYearName;
    @XmlElement(name = "ReportingFirstMonth")
    protected Integer reportingFirstMonth;
    @XmlElement(name = "ReportingFirstWeekOfMonth")
    protected String reportingFirstWeekOfMonth;
    @XmlElement(name = "ReportingWeekToMonthPattern")
    protected String reportingWeekToMonthPattern;
    @XmlElement(name = "ManufacturingFirstMonth")
    protected Integer manufacturingFirstMonth;
    @XmlElement(name = "ManufacturingFirstWeekOfMonth")
    protected Integer manufacturingFirstWeekOfMonth;
    @XmlElement(name = "ManufacturingExtraMonthQuarter")
    protected Integer manufacturingExtraMonthQuarter;

    public XMLGregorianCalendar getCalendarStartDate() {
        return calendarStartDate;
    }

    public void setCalendarStartDate(XMLGregorianCalendar value) {
        this.calendarStartDate = value;
    }

    public XMLGregorianCalendar getCalendarEndDate() {
        return calendarEndDate;
    }

    public void setCalendarEndDate(XMLGregorianCalendar value) {
        this.calendarEndDate = value;
    }

    public Integer getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(Integer value) {
        this.firstDayOfWeek = value;
    }

    public BigInteger getCalendarLanguage() {
        return calendarLanguage;
    }

    public void setCalendarLanguage(BigInteger value) {
        this.calendarLanguage = value;
    }

    public Integer getFiscalFirstMonth() {
        return fiscalFirstMonth;
    }

    public void setFiscalFirstMonth(Integer value) {
        this.fiscalFirstMonth = value;
    }

    public Integer getFiscalFirstDayOfMonth() {
        return fiscalFirstDayOfMonth;
    }

    public void setFiscalFirstDayOfMonth(Integer value) {
        this.fiscalFirstDayOfMonth = value;
    }

    public String getFiscalYearName() {
        return fiscalYearName;
    }

    public void setFiscalYearName(String value) {
        this.fiscalYearName = value;
    }

    public Integer getReportingFirstMonth() {
        return reportingFirstMonth;
    }

    public void setReportingFirstMonth(Integer value) {
        this.reportingFirstMonth = value;
    }

    public String getReportingFirstWeekOfMonth() {
        return reportingFirstWeekOfMonth;
    }

    public void setReportingFirstWeekOfMonth(String value) {
        this.reportingFirstWeekOfMonth = value;
    }

    public String getReportingWeekToMonthPattern() {
        return reportingWeekToMonthPattern;
    }

    public void setReportingWeekToMonthPattern(String value) {
        this.reportingWeekToMonthPattern = value;
    }

    public Integer getManufacturingFirstMonth() {
        return manufacturingFirstMonth;
    }

    public void setManufacturingFirstMonth(Integer value) {
        this.manufacturingFirstMonth = value;
    }

    public Integer getManufacturingFirstWeekOfMonth() {
        return manufacturingFirstWeekOfMonth;
    }

    public void setManufacturingFirstWeekOfMonth(Integer value) {
        this.manufacturingFirstWeekOfMonth = value;
    }

    public Integer getManufacturingExtraMonthQuarter() {
        return manufacturingExtraMonthQuarter;
    }

    public void setManufacturingExtraMonthQuarter(Integer value) {
        this.manufacturingExtraMonthQuarter = value;
    }
}
