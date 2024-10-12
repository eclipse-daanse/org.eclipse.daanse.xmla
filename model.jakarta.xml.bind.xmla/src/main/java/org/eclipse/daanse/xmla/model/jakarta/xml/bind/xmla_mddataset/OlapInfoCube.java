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

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OlapInfoCube", propOrder = { "cubeName", "lastDataUpdate", "lastSchemaUpdate" })
public class OlapInfoCube {

    @XmlElement(name = "CubeName", required = true)
    protected String cubeName;
    @XmlElement(name = "LastDataUpdate", namespace = "http://schemas.microsoft.com/analysisservices/2003/engine")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastDataUpdate;
    @XmlElement(name = "LastSchemaUpdate", namespace = "http://schemas.microsoft.com/analysisservices/2003/engine")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastSchemaUpdate;

    public String getCubeName() {
        return cubeName;
    }

    public void setCubeName(String value) {
        this.cubeName = value;
    }

    public XMLGregorianCalendar getLastDataUpdate() {
        return lastDataUpdate;
    }

    public void setLastDataUpdate(XMLGregorianCalendar value) {
        this.lastDataUpdate = value;
    }

    public XMLGregorianCalendar getLastSchemaUpdate() {
        return lastSchemaUpdate;
    }

    public void setLastSchemaUpdate(XMLGregorianCalendar value) {
        this.lastSchemaUpdate = value;
    }

}
