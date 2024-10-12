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
@XmlType(name = "Restore", propOrder = {

})
public class Restore {

    @XmlElement(name = "DatabaseName")
    protected String databaseName;
    @XmlElement(name = "DatabaseID")
    protected String databaseID;
    @XmlElement(name = "File", required = true)
    protected String file;
    @XmlElement(name = "Security")
    protected String security;
    @XmlElement(name = "AllowOverwrite")
    protected Boolean allowOverwrite;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "DbStorageLocation", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2008/engine/100/100")
    protected String dbStorageLocation;
    @XmlElement(name = "ReadWriteMode", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100")
    protected String readWriteMode;
    @XmlElement(name = "Location", type = Location.class)
    @XmlElementWrapper(name = "Locations")
    protected List<Location> locations;

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

    public String getFile() {
        return file;
    }

    public void setFile(String value) {
        this.file = value;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String value) {
        this.security = value;
    }

    public Boolean isAllowOverwrite() {
        return allowOverwrite;
    }

    public void setAllowOverwrite(Boolean value) {
        this.allowOverwrite = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getDbStorageLocation() {
        return dbStorageLocation;
    }

    public void setDbStorageLocation(String value) {
        this.dbStorageLocation = value;
    }

    public String getReadWriteMode() {
        return readWriteMode;
    }

    public void setReadWriteMode(String value) {
        this.readWriteMode = value;
    }

    public List<Location> getLocations() {
        return locations;
    }

}
