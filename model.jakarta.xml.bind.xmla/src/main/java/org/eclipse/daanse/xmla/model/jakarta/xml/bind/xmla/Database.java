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

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine.ImpersonationInfo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Database", propOrder = {

})
public class Database extends AbstractItem {

    @XmlElement(name = "LastUpdate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "ReadWriteMode", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100")
    protected String readWriteMode;
    @XmlElement(name = "DbStorageLocation", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2008/engine/100/100")
    protected String dbStorageLocation;
    @XmlElement(name = "AggregationPrefix")
    protected String aggregationPrefix;
    @XmlElement(name = "ProcessingPriority")
    protected BigInteger processingPriority;
    @XmlElement(name = "EstimatedSize")
    protected Long estimatedSize;
    @XmlElement(name = "LastProcessed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastProcessed;
    @XmlElement(name = "Language")
    protected BigInteger language;
    @XmlElement(name = "Collation")
    protected String collation;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "MasterDataSourceID")
    protected String masterDataSourceID;
    @XmlElement(name = "DataSourceImpersonationInfo")
    protected ImpersonationInfo dataSourceImpersonationInfo;
    @XmlElement(name = "Account")
    @XmlElementWrapper(name = "Accounts")
    protected List<Account> accounts;
    @XmlElement(name = "DataSource")
    @XmlElementWrapper(name = "DataSources")
    protected List<DataSource> dataSources;
    @XmlElement(name = "DataSourceView")
    @XmlElementWrapper(name = "DataSourceViews")
    protected List<DataSourceView> dataSourceViews;
    @XmlElement(name = "Dimension")
    @XmlElementWrapper(name = "Dimensions")
    protected List<Dimension> dimensions;
    @XmlElement(name = "Cube")
    @XmlElementWrapper(name = "Cubes")
    protected List<Cube> cubes;
    @XmlElement(name = "MiningStructure")
    @XmlElementWrapper(name = "MiningStructures")
    protected List<MiningStructure> miningStructures;
    @XmlElement(name = "Role")
    @XmlElementWrapper(name = "Roles")
    protected List<Role> roles;
    @XmlElement(name = "Assembly")
    @XmlElementWrapper(name = "Assemblies")
    protected List<Assembly> assemblies;
    @XmlElement(name = "DatabasePermission")
    @XmlElementWrapper(name = "DatabasePermissions")
    protected List<DatabasePermission> databasePermissions;
    @XmlElement(name = "Translation")
    @XmlElementWrapper(name = "Translations")
    protected List<Translation> translations;
    @XmlElement(name = "StorageEngineUsed", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2010/engine/200/200")
    protected String storageEngineUsed;
    @XmlElement(name = "ImagePath", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imagePath;
    @XmlElement(name = "ImageUrl", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageUrl;
    @XmlElement(name = "ImageUniqueID", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageUniqueID;
    @XmlElement(name = "ImageVersion", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageVersion;
    @XmlElement(name = "Token", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String token;
    @XmlElement(name = "CompatibilityLevel", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2010/engine/200")
    protected BigInteger compatibilityLevel;
    @XmlElement(name = "DirectQueryMode", namespace = "http://schemas.microsoft"
            + ".com/analysisservices/2011/engine/300/300")
    protected String directQueryMode;

    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getReadWriteMode() {
        return readWriteMode;
    }

    public void setReadWriteMode(String value) {
        this.readWriteMode = value;
    }

    public String getDbStorageLocation() {
        return dbStorageLocation;
    }

    public void setDbStorageLocation(String value) {
        this.dbStorageLocation = value;
    }

    public String getAggregationPrefix() {
        return aggregationPrefix;
    }

    public void setAggregationPrefix(String value) {
        this.aggregationPrefix = value;
    }

    public BigInteger getProcessingPriority() {
        return processingPriority;
    }

    public void setProcessingPriority(BigInteger value) {
        this.processingPriority = value;
    }

    public Long getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(Long value) {
        this.estimatedSize = value;
    }

    public XMLGregorianCalendar getLastProcessed() {
        return lastProcessed;
    }

    public void setLastProcessed(XMLGregorianCalendar value) {
        this.lastProcessed = value;
    }

    public BigInteger getLanguage() {
        return language;
    }

    public void setLanguage(BigInteger value) {
        this.language = value;
    }

    public String getCollation() {
        return collation;
    }

    public void setCollation(String value) {
        this.collation = value;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean value) {
        this.visible = value;
    }

    public String getMasterDataSourceID() {
        return masterDataSourceID;
    }

    public void setMasterDataSourceID(String value) {
        this.masterDataSourceID = value;
    }

    public ImpersonationInfo getDataSourceImpersonationInfo() {
        return dataSourceImpersonationInfo;
    }

    public void setDataSourceImpersonationInfo(ImpersonationInfo value) {
        this.dataSourceImpersonationInfo = value;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> value) {
        this.accounts = value;
    }

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> value) {
        this.dataSources = value;
    }

    public List<DataSourceView> getDataSourceViews() {
        return dataSourceViews;
    }

    public void setDataSourceViews(List<DataSourceView> value) {
        this.dataSourceViews = value;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> value) {
        this.dimensions = value;
    }

    public List<Cube> getCubes() {
        return cubes;
    }

    public void setCubes(List<Cube> value) {
        this.cubes = value;
    }

    public List<MiningStructure> getMiningStructures() {
        return miningStructures;
    }

    public void setMiningStructures(List<MiningStructure> value) {
        this.miningStructures = value;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> value) {
        this.roles = value;
    }

    public List<Assembly> getAssemblies() {
        return assemblies;
    }

    public void setAssemblies(List<Assembly> value) {
        this.assemblies = value;
    }

    public List<DatabasePermission> getDatabasePermissions() {
        return databasePermissions;
    }

    public void setDatabasePermissions(List<DatabasePermission> value) {
        this.databasePermissions = value;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> value) {
        this.translations = value;
    }

    public String getStorageEngineUsed() {
        return storageEngineUsed;
    }

    public void setStorageEngineUsed(String value) {
        this.storageEngineUsed = value;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String value) {
        this.imagePath = value;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String value) {
        this.imageUrl = value;
    }

    public String getImageUniqueID() {
        return imageUniqueID;
    }

    public void setImageUniqueID(String value) {
        this.imageUniqueID = value;
    }

    public String getImageVersion() {
        return imageVersion;
    }

    public void setImageVersion(String value) {
        this.imageVersion = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String value) {
        this.token = value;
    }

    public BigInteger getCompatibilityLevel() {
        return compatibilityLevel;
    }

    public void setCompatibilityLevel(BigInteger value) {
        this.compatibilityLevel = value;
    }

    public String getDirectQueryMode() {
        return directQueryMode;
    }

    public void setDirectQueryMode(String value) {
        this.directQueryMode = value;
    }

}
