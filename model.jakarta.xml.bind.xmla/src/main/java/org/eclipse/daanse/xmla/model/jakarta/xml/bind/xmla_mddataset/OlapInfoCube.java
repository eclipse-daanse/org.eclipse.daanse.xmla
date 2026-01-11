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

import java.time.LocalDateTime;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.adapters.LocalDateTimeAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OlapInfoCube", propOrder = { "cubeName", "lastDataUpdate", "lastSchemaUpdate" })
public class OlapInfoCube {

    @XmlElement(name = "CubeName", required = true)
    protected String cubeName;
    @XmlElement(name = "LastDataUpdate", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    protected LocalDateTime lastDataUpdate;
    @XmlElement(name = "LastSchemaUpdate", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    protected LocalDateTime lastSchemaUpdate;

    public String getCubeName() {
        return cubeName;
    }

    public void setCubeName(String value) {
        this.cubeName = value;
    }

    public LocalDateTime getLastDataUpdate() {
        return lastDataUpdate;
    }

    public void setLastDataUpdate(LocalDateTime value) {
        this.lastDataUpdate = value;
    }

    public LocalDateTime getLastSchemaUpdate() {
        return lastSchemaUpdate;
    }

    public void setLastSchemaUpdate(LocalDateTime value) {
        this.lastSchemaUpdate = value;
    }

}
