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

import java.math.BigInteger;
import java.util.List;

import javax.xml.datatype.Duration;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine.ImpersonationInfo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSource", propOrder = {

})
@XmlSeeAlso({ RelationalDataSource.class, OlapDataSource.class })
public abstract class DataSource extends AbstractItem {

    @XmlElement(name = "ManagedProvider")
    protected String managedProvider;
    @XmlElement(name = "ConnectionString", required = true)
    protected String connectionString;
    @XmlElement(name = "ConnectionStringSecurity")
    protected String connectionStringSecurity;
    @XmlElement(name = "ImpersonationInfo")
    protected ImpersonationInfo impersonationInfo;
    @XmlElement(name = "Isolation")
    protected String isolation;
    @XmlElement(name = "MaxActiveConnections")
    protected BigInteger maxActiveConnections;
    @XmlElement(name = "Timeout")
    protected Duration timeout;
    @XmlElement(name = "DataSourcePermission")
    @XmlElementWrapper(name = "DataSourcePermissions")
    protected List<DataSourcePermission> dataSourcePermissions;
    @XmlElement(name = "QueryImpersonationInfo", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2011/engine/300")
    protected ImpersonationInfo queryImpersonationInfo;
    @XmlElement(name = "QueryHints", namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300")
    protected String queryHints;

    public String getManagedProvider() {
        return managedProvider;
    }

    public void setManagedProvider(String value) {
        this.managedProvider = value;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String value) {
        this.connectionString = value;
    }

    public String getConnectionStringSecurity() {
        return connectionStringSecurity;
    }

    public void setConnectionStringSecurity(String value) {
        this.connectionStringSecurity = value;
    }

    public ImpersonationInfo getImpersonationInfo() {
        return impersonationInfo;
    }

    public void setImpersonationInfo(ImpersonationInfo value) {
        this.impersonationInfo = value;
    }

    public String getIsolation() {
        return isolation;
    }

    public void setIsolation(String value) {
        this.isolation = value;
    }

    public BigInteger getMaxActiveConnections() {
        return maxActiveConnections;
    }

    public void setMaxActiveConnections(BigInteger value) {
        this.maxActiveConnections = value;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration value) {
        this.timeout = value;
    }

    public List<DataSourcePermission> getDataSourcePermissions() {
        return dataSourcePermissions;
    }

    public void setDataSourcePermissions(List<DataSourcePermission> value) {
        this.dataSourcePermissions = value;
    }

    public ImpersonationInfo getQueryImpersonationInfo() {
        return queryImpersonationInfo;
    }

    public void setQueryImpersonationInfo(ImpersonationInfo value) {
        this.queryImpersonationInfo = value;
    }

    public String getQueryHints() {
        return queryHints;
    }

    public void setQueryHints(String value) {
        this.queryHints = value;
    }

}
