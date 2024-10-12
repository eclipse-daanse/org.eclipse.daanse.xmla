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

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trace", propOrder = {

})
public class Trace extends AbstractItem {

    @XmlElement(name = "LogFileName")
    protected String logFileName;
    @XmlElement(name = "LogFileAppend")
    protected Boolean logFileAppend;
    @XmlElement(name = "LogFileSize")
    protected Long logFileSize;
    @XmlElement(name = "Audit")
    protected Boolean audit;
    @XmlElement(name = "LogFileRollover")
    protected Boolean logFileRollover;
    @XmlElement(name = "AutoRestart")
    protected Boolean autoRestart;
    @XmlElement(name = "StopTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar stopTime;
    @XmlElement(name = "Filter")
    protected TraceFilter filter;
    @XmlElement(name = "EventType", namespace = "urn:schemas-microsoft-com:xml-analysis", required = true)
    protected EventType eventType;

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String value) {
        this.logFileName = value;
    }

    public Boolean isLogFileAppend() {
        return logFileAppend;
    }

    public void setLogFileAppend(Boolean value) {
        this.logFileAppend = value;
    }

    public Long getLogFileSize() {
        return logFileSize;
    }

    public void setLogFileSize(Long value) {
        this.logFileSize = value;
    }

    public Boolean isAudit() {
        return audit;
    }

    public void setAudit(Boolean value) {
        this.audit = value;
    }

    public Boolean isLogFileRollover() {
        return logFileRollover;
    }

    public void setLogFileRollover(Boolean value) {
        this.logFileRollover = value;
    }

    public Boolean isAutoRestart() {
        return autoRestart;
    }

    public void setAutoRestart(Boolean value) {
        this.autoRestart = value;
    }

    public XMLGregorianCalendar getStopTime() {
        return stopTime;
    }

    public void setStopTime(XMLGregorianCalendar value) {
        this.stopTime = value;
    }

    public TraceFilter getFilter() {
        return filter;
    }

    public void setFilter(TraceFilter value) {
        this.filter = value;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType value) {
        this.eventType = value;
    }

    public boolean isSetEventType() {
        return (this.eventType != null);
    }

}
