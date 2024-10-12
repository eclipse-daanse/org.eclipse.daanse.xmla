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
@XmlType(name = "AbstractTarget", propOrder = { "dbStorageLocation", "databaseName", "databaseID" })
public class AbstractTarget {

    @XmlElement(name = "DbStorageLocation", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100")
    protected String dbStorageLocation;
    @XmlElement(name = "DatabaseName", required = true)
    protected String databaseName;
    @XmlElement(name = "DatabaseID", required = true)
    protected String databaseID;

    public String getDbStorageLocation() {
        return dbStorageLocation;
    }

    public void setDbStorageLocation(String value) {
        this.dbStorageLocation = value;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String value) {
        this.databaseName = value;
    }

    public String getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(String value) {
        this.databaseID = value;
    }

}
