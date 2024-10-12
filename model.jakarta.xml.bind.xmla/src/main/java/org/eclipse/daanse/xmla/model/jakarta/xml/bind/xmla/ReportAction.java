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

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportAction", propOrder = { "reportServer", "path", "reportParameters", "reportFormatParameters" })
public class ReportAction extends Action {

    @XmlElement(name = "ReportServer", required = true)
    protected String reportServer;
    @XmlElement(name = "Path")
    protected String path;
    @XmlElement(name = "ReportParameter")
    @XmlElementWrapper(name = "ReportParameters")
    protected List<ReportParameter> reportParameters;
    @XmlElement(name = "ReportFormatParameter")
    @XmlElementWrapper(name = "ReportFormatParameters")
    protected List<ReportFormatParameter> reportFormatParameters;

    public String getReportServer() {
        return reportServer;
    }

    public void setReportServer(String value) {
        this.reportServer = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String value) {
        this.path = value;
    }

    public List<ReportParameter> getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(List<ReportParameter> value) {
        this.reportParameters = value;
    }

    public List<ReportFormatParameter> getReportFormatParameters() {
        return reportFormatParameters;
    }

    public void setReportFormatParameters(List<ReportFormatParameter> value) {
        this.reportFormatParameters = value;
    }

}
