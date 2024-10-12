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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.discover;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.AuthenticationModeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.ProviderTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset returns a list of names, data types, and enumeration values of enumerators
 * supported by the XMLA Provider for a specific data source.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoverDataSourcesResponseRowXml")
public class DiscoverDataSourcesResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 5049193502349862159L;

    @XmlElement(name = "DataSourceName", required = true)
    private String dataSourceName;

    @XmlElement(name = "DataSourceDescription")
    private String dataSourceDescription;

    @XmlElement(name = "URL")
    private String url;

    @XmlElement(name = "DataSourceInfo")
    private String dataSourceInfo;

    @XmlElement(name = "ProviderName", required = true)
    private String providerName;

    @XmlElement(name = "ProviderType")
    private ProviderTypeEnum providerType;

    @XmlElement(name = "AuthenticationMode")
    private AuthenticationModeEnum authenticationMode;

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceDescription() {
        return dataSourceDescription;
    }

    public void setDataSourceDescription(String dataSourceDescription) {
        this.dataSourceDescription = dataSourceDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataSourceInfo() {
        return dataSourceInfo;
    }

    public void setDataSourceInfo(String dataSourceInfo) {
        this.dataSourceInfo = dataSourceInfo;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public ProviderTypeEnum getProviderType() {
        return providerType;
    }

    public void setProviderType(ProviderTypeEnum providerType) {
        this.providerType = providerType;
    }

    public AuthenticationModeEnum getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(AuthenticationModeEnum authenticationMode) {
        this.authenticationMode = authenticationMode;
    }
}
