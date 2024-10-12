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
@XmlType(name = "Location", propOrder = { "dataSourceType", "connectionString", "folders" })
public class Location extends LocationBackup {

    @XmlElement(name = "DataSourceType")
    protected String dataSourceType;
    @XmlElement(name = "ConnectionString")
    protected String connectionString;
    @XmlElement(name = "Folder")
    @XmlElementWrapper(name = "Folders")
    protected List<Folder> folders;

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String value) {
        this.dataSourceType = value;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String value) {
        this.connectionString = value;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> value) {
        this.folders = value;
    }

}
