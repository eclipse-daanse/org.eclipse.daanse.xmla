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
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Permission", propOrder = { "roleID", "process", "readDefinition", "read", "write" })
@XmlSeeAlso({ DatabasePermission.class, DataSourcePermission.class, DimensionPermission.class,
        MiningStructurePermission.class, MiningModelPermission.class, CubePermission.class })
public class Permission extends AbstractItem {

    @XmlElement(name = "RoleID", required = true)
    protected String roleID;
    @XmlElement(name = "Process")
    protected Boolean process;
    @XmlElement(name = "ReadDefinition")
    protected String readDefinition;
    @XmlElement(name = "Read")
    protected String read;
    @XmlElement(name = "Write")
    protected String write;

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String value) {
        this.roleID = value;
    }

    public Boolean isProcess() {
        return process;
    }

    public void setProcess(Boolean value) {
        this.process = value;
    }

    public String getReadDefinition() {
        return readDefinition;
    }

    public void setReadDefinition(String value) {
        this.readDefinition = value;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String value) {
        this.read = value;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }

}
