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
@XmlType(name = "Server", propOrder = {

})
public class Server extends AbstractItem {

    @XmlElement(name = "ProductName")
    protected String productName;
    @XmlElement(name = "Edition")
    protected String edition;
    @XmlElement(name = "EditionID")
    protected Long editionID;
    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "ServerMode", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String serverMode;
    @XmlElement(name = "ProductLevel")
    protected String productLevel;
    @XmlElement(name = "DefaultCompatibilityLevel", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2012/engine/400")
    protected Long defaultCompatibilityLevel;
    @XmlElement(name = "SupportedCompatibilityLevels", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2013/engine/600")
    protected String supportedCompatibilityLevels;
    @XmlElement(name = "Database", type = Database.class)
    @XmlElementWrapper(name = "Databases")
    protected List<Database> databases;
    @XmlElement(name = "Assembly", type = Assembly.class)
    @XmlElementWrapper(name = "Assemblies")
    protected List<Assembly> assemblies;
    @XmlElement(name = "Trace", type = Trace.class)
    @XmlElementWrapper(name = "Traces")
    protected List<Trace> traces;
    @XmlElement(name = "Role", type = Role.class)
    @XmlElementWrapper(name = "Roles")
    protected List<Role> roles;
    @XmlElement(name = "ServerProperty", type = ServerProperty.class)
    @XmlElementWrapper(name = "ServerProperties")
    protected List<ServerProperty> serverProperties;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String value) {
        this.productName = value;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String value) {
        this.edition = value;
    }

    public Long getEditionID() {
        return editionID;
    }

    public void setEditionID(Long value) {
        this.editionID = value;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String value) {
        this.version = value;
    }

    public String getServerMode() {
        return serverMode;
    }

    public void setServerMode(String value) {
        this.serverMode = value;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(String value) {
        this.productLevel = value;
    }

    public Long getDefaultCompatibilityLevel() {
        return defaultCompatibilityLevel;
    }

    public void setDefaultCompatibilityLevel(Long value) {
        this.defaultCompatibilityLevel = value;
    }

    public String getSupportedCompatibilityLevels() {
        return supportedCompatibilityLevels;
    }

    public void setSupportedCompatibilityLevels(String value) {
        this.supportedCompatibilityLevels = value;
    }

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> value) {
        this.databases = value;
    }

    public List<Assembly> getAssemblies() {
        return assemblies;
    }

    public void setAssemblies(List<Assembly> value) {
        this.assemblies = value;
    }

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> value) {
        this.traces = value;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> value) {
        this.roles = value;
    }

    public List<ServerProperty> getServerProperties() {
        return serverProperties;
    }

    public void setServerProperties(List<ServerProperty> value) {
        this.serverProperties = value;
    }

}
