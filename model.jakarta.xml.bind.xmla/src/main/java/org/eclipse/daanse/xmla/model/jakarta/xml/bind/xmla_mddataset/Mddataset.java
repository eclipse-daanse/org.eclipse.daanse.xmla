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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_mddataset;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_exception.Exception;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_exception.Messages;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
@XmlType(name = "mddataset", propOrder = { "olapInfo", "axes", "cellData", "exception", "messages" })
public class Mddataset {

    @XmlElement(name = "OlapInfo")
    protected OlapInfo olapInfo;
    @XmlElement(name = "Axes")
    protected Axes axes;
    @XmlElement(name = "CellData")
    protected CellData cellData;
    @XmlElement(name = "Exception")
    protected Exception exception;
    @XmlElement(name = "Messages")
    protected Messages messages;

    public OlapInfo getOlapInfo() {
        return olapInfo;
    }

    public void setOlapInfo(OlapInfo value) {
        this.olapInfo = value;
    }

    public Axes getAxes() {
        return axes;
    }

    public void setAxes(Axes value) {
        this.axes = value;
    }

    public CellData getCellData() {
        return cellData;
    }

    public void setCellData(CellData value) {
        this.cellData = value;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception value) {
        this.exception = value;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages value) {
        this.messages = value;
    }

}
