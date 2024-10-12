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
@XmlType(name = "Backup", propOrder = {

})
public class Backup {

    @XmlElement(name = "Object", required = true)
    protected ObjectReference object;
    @XmlElement(name = "File", required = true)
    protected String file;
    @XmlElement(name = "Security")
    protected String security;
    @XmlElement(name = "ApplyCompression")
    protected Boolean applyCompression;
    @XmlElement(name = "AllowOverwrite")
    protected Boolean allowOverwrite;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "BackupRemotePartitions")
    protected Boolean backupRemotePartitions;
    @XmlElement(name = "Location")
    @XmlElementWrapper(name = "Locations")
    protected List<LocationBackup> locations;

    public ObjectReference getObject() {
        return object;
    }

    public void setObject(ObjectReference value) {
        this.object = value;
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

    public Boolean isApplyCompression() {
        return applyCompression;
    }

    public void setApplyCompression(Boolean value) {
        this.applyCompression = value;
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

    public Boolean isBackupRemotePartitions() {
        return backupRemotePartitions;
    }

    public void setBackupRemotePartitions(Boolean value) {
        this.backupRemotePartitions = value;
    }

    public List<LocationBackup> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationBackup> value) {
        this.locations = value;
    }

}
