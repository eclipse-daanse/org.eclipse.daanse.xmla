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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.dbschema;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.adapters.LocalDateTimeAdapter;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.ClientCacheRefreshPolicyEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.TypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DbSchemaCatalogsResponseRowXml")
public class DbSchemaCatalogsResponseRowXml extends Row implements Serializable {
    @XmlTransient
    private static final long serialVersionUID = 5290912238416901608L;

    /**
     * The catalog name.
     */
    @XmlElement(name = "CATALOG_NAME", required = false)
    String catalogName;

    /**
     * The catalog description.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    String description;

    /**
     * A comma-delimited list of roles to which the current user belongs.
     */
    @XmlElement(name = "ROLES", required = false)
    String roles;

    /**
     * The date that the catalog was last modified.
     */
    @XmlElement(name = "DATE_MODIFIED", required = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateModified;

    /**
     * The compatibility level of the database.
     */
    @XmlElement(name = "COMPATIBILITY_LEVEL", required = false)
    Integer compatibilityLevel;

    /**
     * A mask with the following flags: (0x0) Multidimensional. If the other bits are not set, the
     * database is a Multidimensional database. (0x1) TabularMetadata. The Tabular model is built by
     * using Tabular metadata. (0x2) TabularModel. This is a Tabular model, including those built using
     * Tabular or Multidimensional metadata.
     */
    @XmlElement(name = "TYPE", required = false)
    TypeEnum type;

    /**
     * A database that uses Tabular Metadata will return the current version of the database. For more
     * details, see [MS-SSAS-T]. Otherwise, the value will be 0.
     */
    @XmlElement(name = "VERSION", required = false)
    Integer version;

    /**
     * The ID of the database object.
     */
    @XmlElement(name = "DATABASE_ID", required = false)
    String databaseId;

    /**
     * @deprecated Unused
     */
    @Deprecated(since = "deprecated in specification")
    @XmlElement(name = "DATE_QUERIED", required = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateQueried;

    /**
     * @deprecated Unused
     */
    @Deprecated(since = "deprecated in specification")
    @XmlElement(name = "CURRENTLY_USED", required = false)
    Boolean currentlyUsed;

    /**
     * A measure of how frequently the database is used. The value is empty for the system tracker.
     */
    @XmlElement(name = "POPULARITY", required = false)
    Double popularity;

    /**
     * A measure of how frequently the database is used, expressed as a fraction with respect to the
     * other databases. The value is empty for the system tracker.
     */
    @XmlElement(name = "WEIGHTEDPOPULARITY", required = false)
    Double weightedPopularity;

    /**
     * A hint to the client applications about when their data caches, if any, SHOULD<180> be refreshed
     * after a Refresh command changes the data on the server. The possible values are as follows: 0 –
     * Client applications are notified to refresh their caches only if a user query/interaction needs
     * newer data. 1 (default) – Client applications are notified to allow all background cache
     * refreshes.
     */
    @XmlElement(name = "CLIENTCACHEREFRESHPOLICY", required = false)
    ClientCacheRefreshPolicyEnum clientCacheRefreshPolicy;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public Integer getCompatibilityLevel() {
        return compatibilityLevel;
    }

    public void setCompatibilityLevel(Integer compatibilityLevel) {
        this.compatibilityLevel = compatibilityLevel;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated(since = "deprecated in specification")
    public LocalDateTime getDateQueried() {
        return dateQueried;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated(since = "deprecated in specification")
    public void setDateQueried(LocalDateTime dateQueried) {
        this.dateQueried = dateQueried;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated(since = "deprecated in specification")
    public Boolean getCurrentlyUsed() {
        return currentlyUsed;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated(since = "deprecated in specification")
    public void setCurrentlyUsed(Boolean currentlyUsed) {
        this.currentlyUsed = currentlyUsed;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Double getWeightedPopularity() {
        return weightedPopularity;
    }

    public void setWeightedPopularity(Double weightedPopularity) {
        this.weightedPopularity = weightedPopularity;
    }

    public ClientCacheRefreshPolicyEnum getClientCacheRefreshPolicy() {
        return clientCacheRefreshPolicy;
    }

    public void setClientCacheRefreshPolicy(ClientCacheRefreshPolicyEnum clientCacheRefreshPolicy) {
        this.clientCacheRefreshPolicy = clientCacheRefreshPolicy;
    }
}
