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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Object", propOrder = {

})
public class XmlaObject {

    @XmlElement(name = "Database", required = true)
    protected String database;
    @XmlElement(name = "Cube", required = true)
    protected String cube;
    @XmlElement(name = "Dimension", required = true)
    protected String dimension;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String value) {
        this.database = value;
    }

    public String getCube() {
        return cube;
    }

    public void setCube(String value) {
        this.cube = value;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String value) {
        this.dimension = value;
    }
}
