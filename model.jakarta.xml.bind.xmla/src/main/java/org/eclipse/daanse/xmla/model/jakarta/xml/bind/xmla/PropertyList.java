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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyList")
public class PropertyList {

    @XmlElement(name = "DataSourceInfo")
    protected String dataSourceInfo;
    @XmlElement(name = "Timeout")
    protected BigInteger timeout;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "LocaleIdentifier")
    protected Integer localeIdentifier;
    @XmlElement(name = "Catalog")
    protected String catalog;
    @XmlElement(name = "StateSupport")
    protected String stateSupport;
    @XmlElement(name = "Content")
    protected String content;
    @XmlElement(name = "Format")
    protected String format;
    @XmlElement(name = "AxisFormat")
    protected String axisFormat;
    @XmlElement(name = "BeginRange")
    protected BigInteger beginRange;
    @XmlElement(name = "EndRange")
    protected BigInteger endRange;
    @XmlElement(name = "MDXSupport")
    protected String mdxSupport;
    @XmlElement(name = "ProviderName")
    protected String providerName;
    @XmlElement(name = "ProviderVersion")
    protected String providerVersion;
    @XmlElement(name = "DBMSVersion")
    protected String dbmsVersion;
    @XmlElement(name = "ProviderType")
    protected BigInteger providerType;
    @XmlElement(name = "ShowHiddenCubes")
    protected Boolean showHiddenCubes;
    @XmlElement(name = "SQLSupport")
    protected BigInteger sqlSupport;
    @XmlElement(name = "TransactionDDL")
    protected BigInteger transactionDDL;
    @XmlElement(name = "MaximumRows")
    protected BigInteger maximumRows;
    @XmlElement(name = "Roles")
    protected String roles;
    @XmlElement(name = "VisualMode")
    protected BigInteger visualMode;
    @XmlElement(name = "EffectiveRoles")
    protected String effectiveRoles;
    @XmlElement(name = "EffectiveUserName")
    protected String effectiveUserName;
    @XmlElement(name = "ServerName")
    protected String serverName;
    @XmlElement(name = "CatalogLocation")
    protected BigInteger catalogLocation;
    @XmlElement(name = "DbpropCatalogTerm")
    protected String dbpropCatalogTerm;
    @XmlElement(name = "DbpropCatalogUsage")
    protected BigInteger dbpropCatalogUsage;
    @XmlElement(name = "DbpropColumnDefinition")
    protected BigInteger dbpropColumnDefinition;
    @XmlElement(name = "DbpropConcatNullBehavior")
    protected BigInteger dbpropConcatNullBehavior;
    @XmlElement(name = "DbpropDataSourceReadOnly")
    protected Boolean dbpropDataSourceReadOnly;
    @XmlElement(name = "DbpropGroupBy")
    protected BigInteger dbpropGroupBy;
    @XmlElement(name = "DbpropHeterogeneousTables")
    protected BigInteger dbpropHeterogeneousTables;
    @XmlElement(name = "DbpropIdentifierCase")
    protected BigInteger dbpropIdentifierCase;
    @XmlElement(name = "DbpropMaxIndexSize")
    protected BigInteger dbpropMaxIndexSize;
    @XmlElement(name = "DbpropMaxOpenChapters")
    protected BigInteger dbpropMaxOpenChapters;
    @XmlElement(name = "DbpropMaxRowSize")
    protected BigInteger dbpropMaxRowSize;
    @XmlElement(name = "DbpropMaxRowSizeIncludeBlob")
    protected Boolean dbpropMaxRowSizeIncludeBlob;
    @XmlElement(name = "DbpropMaxTablesInSelect")
    protected BigInteger dbpropMaxTablesInSelect;
    @XmlElement(name = "DbpropMultiTableUpdate")
    protected Boolean dbpropMultiTableUpdate;
    @XmlElement(name = "DbpropNullCollation")
    protected BigInteger dbpropNullCollation;
    @XmlElement(name = "DbpropOrderByColumnsInSelect")
    protected Boolean dbpropOrderByColumnsInSelect;
    @XmlElement(name = "DbpropOutputParameterAvailable")
    protected BigInteger dbpropOutputParameterAvailable;
    @XmlElement(name = "DbpropPersistentIdType")
    protected BigInteger dbpropPersistentIdType;
    @XmlElement(name = "DbpropPrepareAbortBehavior")
    protected BigInteger dbpropPrepareAbortBehavior;
    @XmlElement(name = "DbpropPrepareCommitBehavior")
    protected BigInteger dbpropPrepareCommitBehavior;
    @XmlElement(name = "DbpropProcedureTerm")
    protected String dbpropProcedureTerm;
    @XmlElement(name = "DbpropQuotedIdentifierCase")
    protected BigInteger dbpropQuotedIdentifierCase;
    @XmlElement(name = "DbpropSchemaUsage")
    protected BigInteger dbpropSchemaUsage;
    @XmlElement(name = "DbpropSqlSupport")
    protected BigInteger dbpropSqlSupport;
    @XmlElement(name = "DbpropSubqueries")
    protected BigInteger dbpropSubqueries;
    @XmlElement(name = "DbpropSupportedTxnDdl")
    protected BigInteger dbpropSupportedTxnDdl;
    @XmlElement(name = "MdpropMdxSubqueries")
    protected BigInteger mdpropMdxSubqueries;
    @XmlElement(name = "DbpropSupportedTxnIsoLevels")
    protected BigInteger dbpropSupportedTxnIsoLevels;
    @XmlElement(name = "DbpropSupportedTxnIsoRetain")
    protected BigInteger dbpropSupportedTxnIsoRetain;
    @XmlElement(name = "DbpropTableTerm")
    protected String dbpropTableTerm;
    @XmlElement(name = "MdpropAggregateCellUpdate")
    protected BigInteger mdpropAggregateCellUpdate;
    @XmlElement(name = "MdpropAxes")
    protected BigInteger mdpropAxes;
    @XmlElement(name = "MdpropFlatteningSupport")
    protected BigInteger mdpropFlatteningSupport;
    @XmlElement(name = "MdpropMdxCaseSupport")
    protected BigInteger mdpropMdxCaseSupport;
    @XmlElement(name = "MdpropMdxDescFlags")
    protected BigInteger mdpropMdxDescFlags;
    @XmlElement(name = "MdpropMdxDrillFunctions")
    protected BigInteger mdpropMdxDrillFunctions;
    @XmlElement(name = "MdpropMdxFormulas")
    protected BigInteger mdpropMdxFormulas;
    @XmlElement(name = "MdpropMdxJoinCubes")
    protected BigInteger mdpropMdxJoinCubes;
    @XmlElement(name = "MdpropMdxMemberFunctions")
    protected BigInteger mdpropMdxMemberFunctions;
    @XmlElement(name = "MdpropMdxNonMeasureExpressions")
    protected BigInteger mdpropMdxNonMeasureExpressions;
    @XmlElement(name = "MdpropMdxNumericFunctions")
    protected BigInteger mdpropMdxNumericFunctions;
    @XmlElement(name = "MdpropMdxObjQualification")
    protected BigInteger mdpropMdxObjQualification;
    @XmlElement(name = "MdpropMdxOuterReference")
    protected BigInteger mdpropMdxOuterReference;
    @XmlElement(name = "MdpropMdxQueryByProperty")
    protected Boolean mdpropMdxQueryByProperty;
    @XmlElement(name = "MdpropMdxRangeRowset")
    protected BigInteger mdpropMdxRangeRowset;
    @XmlElement(name = "MdpropMdxSetFunctions")
    protected BigInteger mdpropMdxSetFunctions;
    @XmlElement(name = "MdpropMdxSlicer")
    protected BigInteger mdpropMdxSlicer;
    @XmlElement(name = "MdpropMdxStringCompop")
    protected BigInteger mdpropMdxStringCompop;
    @XmlElement(name = "MdpropNamedLevels")
    protected BigInteger mdpropNamedLevels;
    @XmlElement(name = "DbpropMsmdMDXCompatibility")
    protected BigInteger dbpropMsmdMDXCompatibility;
    @XmlElement(name = "DbpropMsmdSQLCompatibility")
    protected BigInteger dbpropMsmdSQLCompatibility;
    @XmlElement(name = "DbpropMsmdMDXUniqueNameStyle")
    protected BigInteger dbpropMsmdMDXUniqueNameStyle;
    @XmlElement(name = "DbpropMsmdCachePolicy")
    protected BigInteger dbpropMsmdCachePolicy;
    @XmlElement(name = "DbpropMsmdCacheRatio")
    protected BigInteger dbpropMsmdCacheRatio;
    @XmlElement(name = "DbpropMsmdCacheMode")
    protected BigInteger dbpropMsmdCacheMode;
    @XmlElement(name = "DbpropMsmdCompareCaseSensitiveStringFlags")
    protected BigInteger dbpropMsmdCompareCaseSensitiveStringFlags;
    @XmlElement(name = "DbpropMsmdCompareCaseNotSensitiveStringFlags")
    protected BigInteger dbpropMsmdCompareCaseNotSensitiveStringFlags;
    @XmlElement(name = "DbpropMsmdFlattened2")
    protected Boolean dbpropMsmdFlattened2;
    @XmlElement(name = "DbpropInitMode")
    protected BigInteger dbpropInitMode;
    @XmlElement(name = "SspropInitAppName")
    protected String sspropInitAppName;
    @XmlElement(name = "SspropInitWsid")
    protected String sspropInitWsid;
    @XmlElement(name = "SspropInitPacketsize")
    protected BigInteger sspropInitPacketsize;
    @XmlElement(name = "ReadOnlySession")
    protected BigInteger readOnlySession;
    @XmlElement(name = "SecuredCellValue")
    protected BigInteger securedCellValue;
    @XmlElement(name = "NonEmptyThreshold")
    protected BigInteger nonEmptyThreshold;
    @XmlElement(name = "SafetyOptions")
    protected BigInteger safetyOptions;
    @XmlElement(name = "DbpropMsmdCacheRatio2")
    protected Double dbpropMsmdCacheRatio2;
    @XmlElement(name = "DbpropMsmdUseFormulaCache")
    protected String dbpropMsmdUseFormulaCache;
    @XmlElement(name = "DbpropMsmdDynamicDebugLimit")
    protected BigInteger dbpropMsmdDynamicDebugLimit;
    @XmlElement(name = "DbpropMsmdDebugMode")
    protected String dbpropMsmdDebugMode;
    @XmlElement(name = "Dialect")
    protected String dialect;
    @XmlElement(name = "ImpactAnalysis")
    protected Boolean impactAnalysis;
    @XmlElement(name = "SQLQueryMode")
    protected String sqlQueryMode;
    @XmlElement(name = "ClientProcessID")
    protected BigInteger clientProcessID;
    @XmlElement(name = "Cube")
    protected String cube;
    @XmlElement(name = "ReturnCellProperties")
    protected Boolean returnCellProperties;
    @XmlElement(name = "CommitTimeout")
    protected BigInteger commitTimeout;
    @XmlElement(name = "ForceCommitTimeout")
    protected BigInteger forceCommitTimeout;
    @XmlElement(name = "ExecutionMode")
    protected String executionMode;
    @XmlElement(name = "RealTimeOlap")
    protected Boolean realTimeOlap;
    @XmlElement(name = "MdxMissingMemberMode")
    protected String mdxMissingMemberMode;
    @XmlElement(name = "MdpropMdxNamedSets")
    protected BigInteger mdpropMdxNamedSets;
    @XmlElement(name = "DbpropMsmdSubqueries")
    protected BigInteger dbpropMsmdSubqueries;
    @XmlElement(name = "DbpropMsmdAutoExists")
    protected BigInteger dbpropMsmdAutoExists;
    @XmlElement(name = "CustomData")
    protected String customData;
    @XmlElement(name = "DisablePrefetchFacts")
    protected Boolean disablePrefetchFacts;
    @XmlElement(name = "UpdateIsolationLevel")
    protected BigInteger updateIsolationLevel;
    @XmlElement(name = "DbpropMsmdErrorMessageMode")
    protected BigInteger dbpropMsmdErrorMessageMode;
    @XmlElement(name = "MdpropMdxDdlExtensions")
    protected BigInteger mdpropMdxDdlExtensions;
    @XmlElement(name = "ResponseEncoding")
    protected String responseEncoding;
    @XmlElement(name = "MemoryLockingMode")
    protected BigInteger memoryLockingMode;
    @XmlElement(name = "DbpropMsmdOptimizeResponse")
    protected BigInteger dbpropMsmdOptimizeResponse;
    @XmlElement(name = "DbpropMsmdActivityID")
    protected String dbpropMsmdActivityID;
    @XmlElement(name = "DbpropMsmdRequestID")
    protected String dbpropMsmdRequestID;
    @XmlElement(name = "ReturnAffectedObjects")
    protected BigInteger returnAffectedObjects;
    @XmlElement(name = "DbpropMsmdRequestMemoryLimit")
    protected BigInteger dbpropMsmdRequestMemoryLimit;
    @XmlElement(name = "ApplicationContext")
    protected String applicationContext;

    public PropertyList() {
        // constructor
    }

    public String getDataSourceInfo() {
        return dataSourceInfo;
    }

    public void setDataSourceInfo(String dataSourceInfo) {
        this.dataSourceInfo = dataSourceInfo;
    }

    public BigInteger getTimeout() {
        return timeout;
    }

    public void setTimeout(BigInteger timeout) {
        this.timeout = timeout;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLocaleIdentifier() {
        return localeIdentifier;
    }

    public void setLocaleIdentifier(Integer localeIdentifier) {
        this.localeIdentifier = localeIdentifier;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getStateSupport() {
        return stateSupport;
    }

    public void setStateSupport(String stateSupport) {
        this.stateSupport = stateSupport;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAxisFormat() {
        return axisFormat;
    }

    public void setAxisFormat(String axisFormat) {
        this.axisFormat = axisFormat;
    }

    public BigInteger getBeginRange() {
        return beginRange;
    }

    public void setBeginRange(BigInteger beginRange) {
        this.beginRange = beginRange;
    }

    public BigInteger getEndRange() {
        return endRange;
    }

    public void setEndRange(BigInteger endRange) {
        this.endRange = endRange;
    }

    public String getMdxSupport() {
        return mdxSupport;
    }

    public void setMdxSupport(String mdxSupport) {
        this.mdxSupport = mdxSupport;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderVersion() {
        return providerVersion;
    }

    public void setProviderVersion(String providerVersion) {
        this.providerVersion = providerVersion;
    }

    public String getDbmsVersion() {
        return dbmsVersion;
    }

    public void setDbmsVersion(String dbmsVersion) {
        this.dbmsVersion = dbmsVersion;
    }

    public BigInteger getProviderType() {
        return providerType;
    }

    public void setProviderType(BigInteger providerType) {
        this.providerType = providerType;
    }

    public Boolean getShowHiddenCubes() {
        return showHiddenCubes;
    }

    public void setShowHiddenCubes(Boolean showHiddenCubes) {
        this.showHiddenCubes = showHiddenCubes;
    }

    public BigInteger getSqlSupport() {
        return sqlSupport;
    }

    public void setSqlSupport(BigInteger sqlSupport) {
        this.sqlSupport = sqlSupport;
    }

    public BigInteger getTransactionDDL() {
        return transactionDDL;
    }

    public void setTransactionDDL(BigInteger transactionDDL) {
        this.transactionDDL = transactionDDL;
    }

    public BigInteger getMaximumRows() {
        return maximumRows;
    }

    public void setMaximumRows(BigInteger maximumRows) {
        this.maximumRows = maximumRows;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public BigInteger getVisualMode() {
        return visualMode;
    }

    public void setVisualMode(BigInteger visualMode) {
        this.visualMode = visualMode;
    }

    public String getEffectiveRoles() {
        return effectiveRoles;
    }

    public void setEffectiveRoles(String effectiveRoles) {
        this.effectiveRoles = effectiveRoles;
    }

    public String getEffectiveUserName() {
        return effectiveUserName;
    }

    public void setEffectiveUserName(String effectiveUserName) {
        this.effectiveUserName = effectiveUserName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public BigInteger getCatalogLocation() {
        return catalogLocation;
    }

    public void setCatalogLocation(BigInteger catalogLocation) {
        this.catalogLocation = catalogLocation;
    }

    public String getDbpropCatalogTerm() {
        return dbpropCatalogTerm;
    }

    public void setDbpropCatalogTerm(String dbpropCatalogTerm) {
        this.dbpropCatalogTerm = dbpropCatalogTerm;
    }

    public BigInteger getDbpropCatalogUsage() {
        return dbpropCatalogUsage;
    }

    public void setDbpropCatalogUsage(BigInteger dbpropCatalogUsage) {
        this.dbpropCatalogUsage = dbpropCatalogUsage;
    }

    public BigInteger getDbpropColumnDefinition() {
        return dbpropColumnDefinition;
    }

    public void setDbpropColumnDefinition(BigInteger dbpropColumnDefinition) {
        this.dbpropColumnDefinition = dbpropColumnDefinition;
    }

    public BigInteger getDbpropConcatNullBehavior() {
        return dbpropConcatNullBehavior;
    }

    public void setDbpropConcatNullBehavior(BigInteger dbpropConcatNullBehavior) {
        this.dbpropConcatNullBehavior = dbpropConcatNullBehavior;
    }

    public Boolean getDbpropDataSourceReadOnly() {
        return dbpropDataSourceReadOnly;
    }

    public void setDbpropDataSourceReadOnly(Boolean dbpropDataSourceReadOnly) {
        this.dbpropDataSourceReadOnly = dbpropDataSourceReadOnly;
    }

    public BigInteger getDbpropGroupBy() {
        return dbpropGroupBy;
    }

    public void setDbpropGroupBy(BigInteger dbpropGroupBy) {
        this.dbpropGroupBy = dbpropGroupBy;
    }

    public BigInteger getDbpropHeterogeneousTables() {
        return dbpropHeterogeneousTables;
    }

    public void setDbpropHeterogeneousTables(BigInteger dbpropHeterogeneousTables) {
        this.dbpropHeterogeneousTables = dbpropHeterogeneousTables;
    }

    public BigInteger getDbpropIdentifierCase() {
        return dbpropIdentifierCase;
    }

    public void setDbpropIdentifierCase(BigInteger dbpropIdentifierCase) {
        this.dbpropIdentifierCase = dbpropIdentifierCase;
    }

    public BigInteger getDbpropMaxIndexSize() {
        return dbpropMaxIndexSize;
    }

    public void setDbpropMaxIndexSize(BigInteger dbpropMaxIndexSize) {
        this.dbpropMaxIndexSize = dbpropMaxIndexSize;
    }

    public BigInteger getDbpropMaxOpenChapters() {
        return dbpropMaxOpenChapters;
    }

    public void setDbpropMaxOpenChapters(BigInteger dbpropMaxOpenChapters) {
        this.dbpropMaxOpenChapters = dbpropMaxOpenChapters;
    }

    public BigInteger getDbpropMaxRowSize() {
        return dbpropMaxRowSize;
    }

    public void setDbpropMaxRowSize(BigInteger dbpropMaxRowSize) {
        this.dbpropMaxRowSize = dbpropMaxRowSize;
    }

    public Boolean getDbpropMaxRowSizeIncludeBlob() {
        return dbpropMaxRowSizeIncludeBlob;
    }

    public void setDbpropMaxRowSizeIncludeBlob(Boolean dbpropMaxRowSizeIncludeBlob) {
        this.dbpropMaxRowSizeIncludeBlob = dbpropMaxRowSizeIncludeBlob;
    }

    public BigInteger getDbpropMaxTablesInSelect() {
        return dbpropMaxTablesInSelect;
    }

    public void setDbpropMaxTablesInSelect(BigInteger dbpropMaxTablesInSelect) {
        this.dbpropMaxTablesInSelect = dbpropMaxTablesInSelect;
    }

    public Boolean getDbpropMultiTableUpdate() {
        return dbpropMultiTableUpdate;
    }

    public void setDbpropMultiTableUpdate(Boolean dbpropMultiTableUpdate) {
        this.dbpropMultiTableUpdate = dbpropMultiTableUpdate;
    }

    public BigInteger getDbpropNullCollation() {
        return dbpropNullCollation;
    }

    public void setDbpropNullCollation(BigInteger dbpropNullCollation) {
        this.dbpropNullCollation = dbpropNullCollation;
    }

    public Boolean getDbpropOrderByColumnsInSelect() {
        return dbpropOrderByColumnsInSelect;
    }

    public void setDbpropOrderByColumnsInSelect(Boolean dbpropOrderByColumnsInSelect) {
        this.dbpropOrderByColumnsInSelect = dbpropOrderByColumnsInSelect;
    }

    public BigInteger getDbpropOutputParameterAvailable() {
        return dbpropOutputParameterAvailable;
    }

    public void setDbpropOutputParameterAvailable(BigInteger dbpropOutputParameterAvailable) {
        this.dbpropOutputParameterAvailable = dbpropOutputParameterAvailable;
    }

    public BigInteger getDbpropPersistentIdType() {
        return dbpropPersistentIdType;
    }

    public void setDbpropPersistentIdType(BigInteger dbpropPersistentIdType) {
        this.dbpropPersistentIdType = dbpropPersistentIdType;
    }

    public BigInteger getDbpropPrepareAbortBehavior() {
        return dbpropPrepareAbortBehavior;
    }

    public void setDbpropPrepareAbortBehavior(BigInteger dbpropPrepareAbortBehavior) {
        this.dbpropPrepareAbortBehavior = dbpropPrepareAbortBehavior;
    }

    public BigInteger getDbpropPrepareCommitBehavior() {
        return dbpropPrepareCommitBehavior;
    }

    public void setDbpropPrepareCommitBehavior(BigInteger dbpropPrepareCommitBehavior) {
        this.dbpropPrepareCommitBehavior = dbpropPrepareCommitBehavior;
    }

    public String getDbpropProcedureTerm() {
        return dbpropProcedureTerm;
    }

    public void setDbpropProcedureTerm(String dbpropProcedureTerm) {
        this.dbpropProcedureTerm = dbpropProcedureTerm;
    }

    public BigInteger getDbpropQuotedIdentifierCase() {
        return dbpropQuotedIdentifierCase;
    }

    public void setDbpropQuotedIdentifierCase(BigInteger dbpropQuotedIdentifierCase) {
        this.dbpropQuotedIdentifierCase = dbpropQuotedIdentifierCase;
    }

    public BigInteger getDbpropSchemaUsage() {
        return dbpropSchemaUsage;
    }

    public void setDbpropSchemaUsage(BigInteger dbpropSchemaUsage) {
        this.dbpropSchemaUsage = dbpropSchemaUsage;
    }

    public BigInteger getDbpropSqlSupport() {
        return dbpropSqlSupport;
    }

    public void setDbpropSqlSupport(BigInteger dbpropSqlSupport) {
        this.dbpropSqlSupport = dbpropSqlSupport;
    }

    public BigInteger getDbpropSubqueries() {
        return dbpropSubqueries;
    }

    public void setDbpropSubqueries(BigInteger dbpropSubqueries) {
        this.dbpropSubqueries = dbpropSubqueries;
    }

    public BigInteger getDbpropSupportedTxnDdl() {
        return dbpropSupportedTxnDdl;
    }

    public void setDbpropSupportedTxnDdl(BigInteger dbpropSupportedTxnDdl) {
        this.dbpropSupportedTxnDdl = dbpropSupportedTxnDdl;
    }

    public BigInteger getMdpropMdxSubqueries() {
        return mdpropMdxSubqueries;
    }

    public void setMdpropMdxSubqueries(BigInteger mdpropMdxSubqueries) {
        this.mdpropMdxSubqueries = mdpropMdxSubqueries;
    }

    public BigInteger getDbpropSupportedTxnIsoLevels() {
        return dbpropSupportedTxnIsoLevels;
    }

    public void setDbpropSupportedTxnIsoLevels(BigInteger dbpropSupportedTxnIsoLevels) {
        this.dbpropSupportedTxnIsoLevels = dbpropSupportedTxnIsoLevels;
    }

    public BigInteger getDbpropSupportedTxnIsoRetain() {
        return dbpropSupportedTxnIsoRetain;
    }

    public void setDbpropSupportedTxnIsoRetain(BigInteger dbpropSupportedTxnIsoRetain) {
        this.dbpropSupportedTxnIsoRetain = dbpropSupportedTxnIsoRetain;
    }

    public String getDbpropTableTerm() {
        return dbpropTableTerm;
    }

    public void setDbpropTableTerm(String dbpropTableTerm) {
        this.dbpropTableTerm = dbpropTableTerm;
    }

    public BigInteger getMdpropAggregateCellUpdate() {
        return mdpropAggregateCellUpdate;
    }

    public void setMdpropAggregateCellUpdate(BigInteger mdpropAggregateCellUpdate) {
        this.mdpropAggregateCellUpdate = mdpropAggregateCellUpdate;
    }

    public BigInteger getMdpropAxes() {
        return mdpropAxes;
    }

    public void setMdpropAxes(BigInteger mdpropAxes) {
        this.mdpropAxes = mdpropAxes;
    }

    public BigInteger getMdpropFlatteningSupport() {
        return mdpropFlatteningSupport;
    }

    public void setMdpropFlatteningSupport(BigInteger mdpropFlatteningSupport) {
        this.mdpropFlatteningSupport = mdpropFlatteningSupport;
    }

    public BigInteger getMdpropMdxCaseSupport() {
        return mdpropMdxCaseSupport;
    }

    public void setMdpropMdxCaseSupport(BigInteger mdpropMdxCaseSupport) {
        this.mdpropMdxCaseSupport = mdpropMdxCaseSupport;
    }

    public BigInteger getMdpropMdxDescFlags() {
        return mdpropMdxDescFlags;
    }

    public void setMdpropMdxDescFlags(BigInteger mdpropMdxDescFlags) {
        this.mdpropMdxDescFlags = mdpropMdxDescFlags;
    }

    public BigInteger getMdpropMdxDrillFunctions() {
        return mdpropMdxDrillFunctions;
    }

    public void setMdpropMdxDrillFunctions(BigInteger mdpropMdxDrillFunctions) {
        this.mdpropMdxDrillFunctions = mdpropMdxDrillFunctions;
    }

    public BigInteger getMdpropMdxFormulas() {
        return mdpropMdxFormulas;
    }

    public void setMdpropMdxFormulas(BigInteger mdpropMdxFormulas) {
        this.mdpropMdxFormulas = mdpropMdxFormulas;
    }

    public BigInteger getMdpropMdxJoinCubes() {
        return mdpropMdxJoinCubes;
    }

    public void setMdpropMdxJoinCubes(BigInteger mdpropMdxJoinCubes) {
        this.mdpropMdxJoinCubes = mdpropMdxJoinCubes;
    }

    public BigInteger getMdpropMdxMemberFunctions() {
        return mdpropMdxMemberFunctions;
    }

    public void setMdpropMdxMemberFunctions(BigInteger mdpropMdxMemberFunctions) {
        this.mdpropMdxMemberFunctions = mdpropMdxMemberFunctions;
    }

    public BigInteger getMdpropMdxNonMeasureExpressions() {
        return mdpropMdxNonMeasureExpressions;
    }

    public void setMdpropMdxNonMeasureExpressions(BigInteger mdpropMdxNonMeasureExpressions) {
        this.mdpropMdxNonMeasureExpressions = mdpropMdxNonMeasureExpressions;
    }

    public BigInteger getMdpropMdxNumericFunctions() {
        return mdpropMdxNumericFunctions;
    }

    public void setMdpropMdxNumericFunctions(BigInteger mdpropMdxNumericFunctions) {
        this.mdpropMdxNumericFunctions = mdpropMdxNumericFunctions;
    }

    public BigInteger getMdpropMdxObjQualification() {
        return mdpropMdxObjQualification;
    }

    public void setMdpropMdxObjQualification(BigInteger mdpropMdxObjQualification) {
        this.mdpropMdxObjQualification = mdpropMdxObjQualification;
    }

    public BigInteger getMdpropMdxOuterReference() {
        return mdpropMdxOuterReference;
    }

    public void setMdpropMdxOuterReference(BigInteger mdpropMdxOuterReference) {
        this.mdpropMdxOuterReference = mdpropMdxOuterReference;
    }

    public Boolean getMdpropMdxQueryByProperty() {
        return mdpropMdxQueryByProperty;
    }

    public void setMdpropMdxQueryByProperty(Boolean mdpropMdxQueryByProperty) {
        this.mdpropMdxQueryByProperty = mdpropMdxQueryByProperty;
    }

    public BigInteger getMdpropMdxRangeRowset() {
        return mdpropMdxRangeRowset;
    }

    public void setMdpropMdxRangeRowset(BigInteger mdpropMdxRangeRowset) {
        this.mdpropMdxRangeRowset = mdpropMdxRangeRowset;
    }

    public BigInteger getMdpropMdxSetFunctions() {
        return mdpropMdxSetFunctions;
    }

    public void setMdpropMdxSetFunctions(BigInteger mdpropMdxSetFunctions) {
        this.mdpropMdxSetFunctions = mdpropMdxSetFunctions;
    }

    public BigInteger getMdpropMdxSlicer() {
        return mdpropMdxSlicer;
    }

    public void setMdpropMdxSlicer(BigInteger mdpropMdxSlicer) {
        this.mdpropMdxSlicer = mdpropMdxSlicer;
    }

    public BigInteger getMdpropMdxStringCompop() {
        return mdpropMdxStringCompop;
    }

    public void setMdpropMdxStringCompop(BigInteger mdpropMdxStringCompop) {
        this.mdpropMdxStringCompop = mdpropMdxStringCompop;
    }

    public BigInteger getMdpropNamedLevels() {
        return mdpropNamedLevels;
    }

    public void setMdpropNamedLevels(BigInteger mdpropNamedLevels) {
        this.mdpropNamedLevels = mdpropNamedLevels;
    }

    public BigInteger getDbpropMsmdMDXCompatibility() {
        return dbpropMsmdMDXCompatibility;
    }

    public void setDbpropMsmdMDXCompatibility(BigInteger dbpropMsmdMDXCompatibility) {
        this.dbpropMsmdMDXCompatibility = dbpropMsmdMDXCompatibility;
    }

    public BigInteger getDbpropMsmdSQLCompatibility() {
        return dbpropMsmdSQLCompatibility;
    }

    public void setDbpropMsmdSQLCompatibility(BigInteger dbpropMsmdSQLCompatibility) {
        this.dbpropMsmdSQLCompatibility = dbpropMsmdSQLCompatibility;
    }

    public BigInteger getDbpropMsmdMDXUniqueNameStyle() {
        return dbpropMsmdMDXUniqueNameStyle;
    }

    public void setDbpropMsmdMDXUniqueNameStyle(BigInteger dbpropMsmdMDXUniqueNameStyle) {
        this.dbpropMsmdMDXUniqueNameStyle = dbpropMsmdMDXUniqueNameStyle;
    }

    public BigInteger getDbpropMsmdCachePolicy() {
        return dbpropMsmdCachePolicy;
    }

    public void setDbpropMsmdCachePolicy(BigInteger dbpropMsmdCachePolicy) {
        this.dbpropMsmdCachePolicy = dbpropMsmdCachePolicy;
    }

    public BigInteger getDbpropMsmdCacheRatio() {
        return dbpropMsmdCacheRatio;
    }

    public void setDbpropMsmdCacheRatio(BigInteger dbpropMsmdCacheRatio) {
        this.dbpropMsmdCacheRatio = dbpropMsmdCacheRatio;
    }

    public BigInteger getDbpropMsmdCacheMode() {
        return dbpropMsmdCacheMode;
    }

    public void setDbpropMsmdCacheMode(BigInteger dbpropMsmdCacheMode) {
        this.dbpropMsmdCacheMode = dbpropMsmdCacheMode;
    }

    public BigInteger getDbpropMsmdCompareCaseSensitiveStringFlags() {
        return dbpropMsmdCompareCaseSensitiveStringFlags;
    }

    public void setDbpropMsmdCompareCaseSensitiveStringFlags(BigInteger dbpropMsmdCompareCaseSensitiveStringFlags) {
        this.dbpropMsmdCompareCaseSensitiveStringFlags = dbpropMsmdCompareCaseSensitiveStringFlags;
    }

    public BigInteger getDbpropMsmdCompareCaseNotSensitiveStringFlags() {
        return dbpropMsmdCompareCaseNotSensitiveStringFlags;
    }

    public void setDbpropMsmdCompareCaseNotSensitiveStringFlags(
            BigInteger dbpropMsmdCompareCaseNotSensitiveStringFlags) {
        this.dbpropMsmdCompareCaseNotSensitiveStringFlags = dbpropMsmdCompareCaseNotSensitiveStringFlags;
    }

    public Boolean getDbpropMsmdFlattened2() {
        return dbpropMsmdFlattened2;
    }

    public void setDbpropMsmdFlattened2(Boolean dbpropMsmdFlattened2) {
        this.dbpropMsmdFlattened2 = dbpropMsmdFlattened2;
    }

    public BigInteger getDbpropInitMode() {
        return dbpropInitMode;
    }

    public void setDbpropInitMode(BigInteger dbpropInitMode) {
        this.dbpropInitMode = dbpropInitMode;
    }

    public String getSspropInitAppName() {
        return sspropInitAppName;
    }

    public void setSspropInitAppName(String sspropInitAppName) {
        this.sspropInitAppName = sspropInitAppName;
    }

    public String getSspropInitWsid() {
        return sspropInitWsid;
    }

    public void setSspropInitWsid(String sspropInitWsid) {
        this.sspropInitWsid = sspropInitWsid;
    }

    public BigInteger getSspropInitPacketsize() {
        return sspropInitPacketsize;
    }

    public void setSspropInitPacketsize(BigInteger sspropInitPacketsize) {
        this.sspropInitPacketsize = sspropInitPacketsize;
    }

    public BigInteger getReadOnlySession() {
        return readOnlySession;
    }

    public void setReadOnlySession(BigInteger readOnlySession) {
        this.readOnlySession = readOnlySession;
    }

    public BigInteger getSecuredCellValue() {
        return securedCellValue;
    }

    public void setSecuredCellValue(BigInteger securedCellValue) {
        this.securedCellValue = securedCellValue;
    }

    public BigInteger getNonEmptyThreshold() {
        return nonEmptyThreshold;
    }

    public void setNonEmptyThreshold(BigInteger nonEmptyThreshold) {
        this.nonEmptyThreshold = nonEmptyThreshold;
    }

    public BigInteger getSafetyOptions() {
        return safetyOptions;
    }

    public void setSafetyOptions(BigInteger safetyOptions) {
        this.safetyOptions = safetyOptions;
    }

    public Double getDbpropMsmdCacheRatio2() {
        return dbpropMsmdCacheRatio2;
    }

    public void setDbpropMsmdCacheRatio2(Double dbpropMsmdCacheRatio2) {
        this.dbpropMsmdCacheRatio2 = dbpropMsmdCacheRatio2;
    }

    public String getDbpropMsmdUseFormulaCache() {
        return dbpropMsmdUseFormulaCache;
    }

    public void setDbpropMsmdUseFormulaCache(String dbpropMsmdUseFormulaCache) {
        this.dbpropMsmdUseFormulaCache = dbpropMsmdUseFormulaCache;
    }

    public BigInteger getDbpropMsmdDynamicDebugLimit() {
        return dbpropMsmdDynamicDebugLimit;
    }

    public void setDbpropMsmdDynamicDebugLimit(BigInteger dbpropMsmdDynamicDebugLimit) {
        this.dbpropMsmdDynamicDebugLimit = dbpropMsmdDynamicDebugLimit;
    }

    public String getDbpropMsmdDebugMode() {
        return dbpropMsmdDebugMode;
    }

    public void setDbpropMsmdDebugMode(String dbpropMsmdDebugMode) {
        this.dbpropMsmdDebugMode = dbpropMsmdDebugMode;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public Boolean getImpactAnalysis() {
        return impactAnalysis;
    }

    public void setImpactAnalysis(Boolean impactAnalysis) {
        this.impactAnalysis = impactAnalysis;
    }

    public String getSqlQueryMode() {
        return sqlQueryMode;
    }

    public void setSqlQueryMode(String sqlQueryMode) {
        this.sqlQueryMode = sqlQueryMode;
    }

    public BigInteger getClientProcessID() {
        return clientProcessID;
    }

    public void setClientProcessID(BigInteger clientProcessID) {
        this.clientProcessID = clientProcessID;
    }

    public String getCube() {
        return cube;
    }

    public void setCube(String cube) {
        this.cube = cube;
    }

    public Boolean getReturnCellProperties() {
        return returnCellProperties;
    }

    public void setReturnCellProperties(Boolean returnCellProperties) {
        this.returnCellProperties = returnCellProperties;
    }

    public BigInteger getCommitTimeout() {
        return commitTimeout;
    }

    public void setCommitTimeout(BigInteger commitTimeout) {
        this.commitTimeout = commitTimeout;
    }

    public BigInteger getForceCommitTimeout() {
        return forceCommitTimeout;
    }

    public void setForceCommitTimeout(BigInteger forceCommitTimeout) {
        this.forceCommitTimeout = forceCommitTimeout;
    }

    public String getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }

    public Boolean getRealTimeOlap() {
        return realTimeOlap;
    }

    public void setRealTimeOlap(Boolean realTimeOlap) {
        this.realTimeOlap = realTimeOlap;
    }

    public String getMdxMissingMemberMode() {
        return mdxMissingMemberMode;
    }

    public void setMdxMissingMemberMode(String mdxMissingMemberMode) {
        this.mdxMissingMemberMode = mdxMissingMemberMode;
    }

    public BigInteger getMdpropMdxNamedSets() {
        return mdpropMdxNamedSets;
    }

    public void setMdpropMdxNamedSets(BigInteger mdpropMdxNamedSets) {
        this.mdpropMdxNamedSets = mdpropMdxNamedSets;
    }

    public BigInteger getDbpropMsmdSubqueries() {
        return dbpropMsmdSubqueries;
    }

    public void setDbpropMsmdSubqueries(BigInteger dbpropMsmdSubqueries) {
        this.dbpropMsmdSubqueries = dbpropMsmdSubqueries;
    }

    public BigInteger getDbpropMsmdAutoExists() {
        return dbpropMsmdAutoExists;
    }

    public void setDbpropMsmdAutoExists(BigInteger dbpropMsmdAutoExists) {
        this.dbpropMsmdAutoExists = dbpropMsmdAutoExists;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public Boolean getDisablePrefetchFacts() {
        return disablePrefetchFacts;
    }

    public void setDisablePrefetchFacts(Boolean disablePrefetchFacts) {
        this.disablePrefetchFacts = disablePrefetchFacts;
    }

    public BigInteger getUpdateIsolationLevel() {
        return updateIsolationLevel;
    }

    public void setUpdateIsolationLevel(BigInteger updateIsolationLevel) {
        this.updateIsolationLevel = updateIsolationLevel;
    }

    public BigInteger getDbpropMsmdErrorMessageMode() {
        return dbpropMsmdErrorMessageMode;
    }

    public void setDbpropMsmdErrorMessageMode(BigInteger dbpropMsmdErrorMessageMode) {
        this.dbpropMsmdErrorMessageMode = dbpropMsmdErrorMessageMode;
    }

    public BigInteger getMdpropMdxDdlExtensions() {
        return mdpropMdxDdlExtensions;
    }

    public void setMdpropMdxDdlExtensions(BigInteger mdpropMdxDdlExtensions) {
        this.mdpropMdxDdlExtensions = mdpropMdxDdlExtensions;
    }

    public String getResponseEncoding() {
        return responseEncoding;
    }

    public void setResponseEncoding(String responseEncoding) {
        this.responseEncoding = responseEncoding;
    }

    public BigInteger getMemoryLockingMode() {
        return memoryLockingMode;
    }

    public void setMemoryLockingMode(BigInteger memoryLockingMode) {
        this.memoryLockingMode = memoryLockingMode;
    }

    public BigInteger getDbpropMsmdOptimizeResponse() {
        return dbpropMsmdOptimizeResponse;
    }

    public void setDbpropMsmdOptimizeResponse(BigInteger dbpropMsmdOptimizeResponse) {
        this.dbpropMsmdOptimizeResponse = dbpropMsmdOptimizeResponse;
    }

    public String getDbpropMsmdActivityID() {
        return dbpropMsmdActivityID;
    }

    public void setDbpropMsmdActivityID(String dbpropMsmdActivityID) {
        this.dbpropMsmdActivityID = dbpropMsmdActivityID;
    }

    public String getDbpropMsmdRequestID() {
        return dbpropMsmdRequestID;
    }

    public void setDbpropMsmdRequestID(String dbpropMsmdRequestID) {
        this.dbpropMsmdRequestID = dbpropMsmdRequestID;
    }

    public BigInteger getReturnAffectedObjects() {
        return returnAffectedObjects;
    }

    public void setReturnAffectedObjects(BigInteger returnAffectedObjects) {
        this.returnAffectedObjects = returnAffectedObjects;
    }

    public BigInteger getDbpropMsmdRequestMemoryLimit() {
        return dbpropMsmdRequestMemoryLimit;
    }

    public void setDbpropMsmdRequestMemoryLimit(BigInteger dbpropMsmdRequestMemoryLimit) {
        this.dbpropMsmdRequestMemoryLimit = dbpropMsmdRequestMemoryLimit;
    }

    public String getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(String applicationContext) {
        this.applicationContext = applicationContext;
    }
}
