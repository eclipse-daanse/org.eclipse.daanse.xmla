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

package org.eclipse.daanse.xmla.model.record.discover;

import java.math.BigInteger;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.properties.AxisFormat;
import org.eclipse.daanse.xmla.api.common.properties.Content;
import org.eclipse.daanse.xmla.api.common.properties.Format;
import org.eclipse.daanse.xmla.api.common.properties.PropertyListElementDefinition;
import org.eclipse.daanse.xmla.api.discover.Properties;

public class PropertiesR implements Properties {

    protected Optional<String> applicationContext;

    protected Optional<AxisFormat> axisFormat = Optional.empty();

    protected Optional<BigInteger> beginRange = Optional.empty();

    protected Optional<String> catalog = Optional.empty();

    protected Optional<BigInteger> catalogLocation = Optional.empty();

    protected Optional<BigInteger> clientProcessID;

    protected Optional<BigInteger> commitTimeout;

    protected Optional<Content> content = Optional.empty();

    protected Optional<String> cube;

    protected Optional<String> customData;

    protected Optional<String> dataSourceInfo = Optional.empty();

    protected Optional<String> dbmsVersion = Optional.empty();

    protected Optional<String> dbpropCatalogTerm = Optional.empty();

    protected Optional<BigInteger> dbpropCatalogUsage = Optional.empty();

    protected Optional<BigInteger> dbpropColumnDefinition = Optional.empty();

    protected Optional<BigInteger> dbpropConcatNullBehavior = Optional.empty();

    protected Optional<Boolean> dbpropDataSourceReadOnly = Optional.empty();

    protected Optional<BigInteger> dbpropGroupBy = Optional.empty();

    protected Optional<BigInteger> dbpropHeterogeneousTables = Optional.empty();

    protected Optional<BigInteger> dbpropIdentifierCase = Optional.empty();

    protected Optional<BigInteger> dbpropInitMode = Optional.empty();

    protected Optional<BigInteger> dbpropMaxIndexSize = Optional.empty();

    protected Optional<BigInteger> dbpropMaxOpenChapters = Optional.empty();

    protected Optional<BigInteger> dbpropMaxRowSize = Optional.empty();

    protected Optional<Boolean> dbpropMaxRowSizeIncludeBlob = Optional.empty();

    protected Optional<BigInteger> dbpropMaxTablesInSelect = Optional.empty();

    protected Optional<String> dbpropMsmdActivityID;

    protected Optional<BigInteger> dbpropMsmdAutoExists;

    protected Optional<BigInteger> dbpropMsmdCacheMode = Optional.empty();

    protected Optional<BigInteger> dbpropMsmdCachePolicy = Optional.empty();

    protected Optional<BigInteger> dbpropMsmdCacheRatio = Optional.empty();

    protected Optional<Double> dbpropMsmdCacheRatio2 = Optional.empty();

    protected Optional<BigInteger> dbpropMsmdCompareCaseNotSensitiveStringFlags = Optional.empty();

    protected Optional<BigInteger> dbpropMsmdCompareCaseSensitiveStringFlags = Optional.empty();

    protected Optional<String> dbpropMsmdDebugMode;

    protected Optional<BigInteger> dbpropMsmdDynamicDebugLimit;

    protected Optional<BigInteger> dbpropMsmdErrorMessageMode;

    protected Optional<Boolean> dbpropMsmdFlattened2 = Optional.empty();

    protected Optional<BigInteger> dbpropMsmdMDXCompatibility = Optional.empty();

    protected Optional<BigInteger> dbpropMsmdMDXUniqueNameStyle = Optional.empty();

    protected Optional<BigInteger> dbpropMsmdOptimizeResponse;

    protected Optional<String> dbpropMsmdRequestID;

    protected Optional<BigInteger> dbpropMsmdRequestMemoryLimit;

    protected Optional<BigInteger> dbpropMsmdSQLCompatibility = Optional.empty();

    protected Optional<BigInteger> dbpropMsmdSubqueries;

    protected Optional<String> dbpropMsmdUseFormulaCache;

    protected Optional<Boolean> dbpropMultiTableUpdate = Optional.empty();

    protected Optional<BigInteger> dbpropNullCollation = Optional.empty();

    protected Optional<Boolean> dbpropOrderByColumnsInSelect = Optional.empty();

    protected Optional<BigInteger> dbpropOutputParameterAvailable = Optional.empty();

    protected Optional<BigInteger> dbpropPersistentIdType = Optional.empty();

    protected Optional<BigInteger> dbpropPrepareAbortBehavior = Optional.empty();

    protected Optional<BigInteger> dbpropPrepareCommitBehavior = Optional.empty();

    protected Optional<String> dbpropProcedureTerm = Optional.empty();

    protected Optional<BigInteger> dbpropQuotedIdentifierCase = Optional.empty();

    protected Optional<BigInteger> dbpropSchemaUsage = Optional.empty();

    protected Optional<BigInteger> dbpropSqlSupport = Optional.empty();

    protected Optional<BigInteger> dbpropSubqueries = Optional.empty();

    protected Optional<BigInteger> dbpropSupportedTxnDdl = Optional.empty();

    protected Optional<BigInteger> dbpropSupportedTxnIsoLevels = Optional.empty();

    protected Optional<BigInteger> dbpropSupportedTxnIsoRetain = Optional.empty();

    protected Optional<String> dbpropTableTerm = Optional.empty();

    protected Optional<String> dialect;

    protected Optional<Boolean> disablePrefetchFacts;

    protected Optional<String> effectiveRoles = Optional.empty();

    protected Optional<String> effectiveUserName = Optional.empty();

    protected Optional<BigInteger> endRange = Optional.empty();

    protected Optional<String> executionMode;

    protected Optional<BigInteger> forceCommitTimeout;

    protected Optional<Format> format = Optional.empty();

    protected Optional<Boolean> impactAnalysis;

    protected Optional<Integer> localeIdentifier = Optional.empty();

    protected Optional<BigInteger> maximumRows = Optional.empty();

    protected Optional<BigInteger> mdpropAggregateCellUpdate = Optional.empty();

    protected Optional<BigInteger> mdpropAxes = Optional.empty();

    protected Optional<BigInteger> mdpropFlatteningSupport = Optional.empty();

    protected Optional<BigInteger> mdpropMdxCaseSupport = Optional.empty();

    protected Optional<BigInteger> mdpropMdxDdlExtensions;

    protected Optional<BigInteger> mdpropMdxDescFlags = Optional.empty();

    protected Optional<BigInteger> mdpropMdxDrillFunctions = Optional.empty();

    protected Optional<BigInteger> mdpropMdxFormulas = Optional.empty();

    protected Optional<BigInteger> mdpropMdxJoinCubes = Optional.empty();

    protected Optional<BigInteger> mdpropMdxMemberFunctions = Optional.empty();

    protected Optional<BigInteger> mdpropMdxNamedSets;

    protected Optional<BigInteger> mdpropMdxNonMeasureExpressions = Optional.empty();

    protected Optional<BigInteger> mdpropMdxNumericFunctions = Optional.empty();

    protected Optional<BigInteger> mdpropMdxObjQualification = Optional.empty();

    protected Optional<BigInteger> mdpropMdxOuterReference = Optional.empty();

    protected Optional<Boolean> mdpropMdxQueryByProperty = Optional.empty();

    protected Optional<BigInteger> mdpropMdxRangeRowset = Optional.empty();

    protected Optional<BigInteger> mdpropMdxSetFunctions = Optional.empty();

    protected Optional<BigInteger> mdpropMdxSlicer = Optional.empty();

    protected Optional<BigInteger> mdpropMdxStringCompop = Optional.empty();

    protected Optional<BigInteger> mdpropMdxSubqueries = Optional.empty();

    protected Optional<BigInteger> mdpropNamedLevels = Optional.empty();

    protected Optional<String> mdxMissingMemberMode;

    protected Optional<String> mdxSupport = Optional.empty();

    protected Optional<BigInteger> memoryLockingMode;

    protected Optional<BigInteger> nonEmptyThreshold = Optional.empty();

    protected Optional<String> password = Optional.empty();

    protected Optional<String> providerName = Optional.empty();

    protected Optional<BigInteger> providerType = Optional.empty();

    protected Optional<String> providerVersion = Optional.empty();

    protected Optional<BigInteger> readOnlySession = Optional.empty();

    protected Optional<Boolean> realTimeOlap;

    protected Optional<String> responseEncoding;

    protected Optional<BigInteger> returnAffectedObjects;

    protected Optional<Boolean> returnCellProperties;

    protected Optional<String> roles = Optional.empty();

    protected Optional<BigInteger> safetyOptions = Optional.empty();

    protected Optional<BigInteger> securedCellValue = Optional.empty();

    protected Optional<String> serverName = Optional.empty();

    protected Optional<Boolean> showHiddenCubes = Optional.empty();

    protected Optional<String> sqlQueryMode;

    protected Optional<BigInteger> sqlSupport = Optional.empty();

    protected Optional<String> sspropInitAppName = Optional.empty();

    protected Optional<BigInteger> sspropInitPacketsize = Optional.empty();

    protected Optional<String> sspropInitWsid = Optional.empty();

    protected Optional<String> stateSupport = Optional.empty();

    protected Optional<BigInteger> timeout = Optional.empty();

    protected Optional<BigInteger> transactionDDL = Optional.empty();

    protected Optional<BigInteger> updateIsolationLevel;

    protected Optional<String> userName = Optional.empty();

    protected Optional<BigInteger> visualMode = Optional.empty();

    protected Optional<Boolean> deep = Optional.of(false);

    protected Optional<Boolean> emitInvisibleMembers = Optional.of(false);

    protected Optional<String> tableFields = Optional.empty();

    protected Optional<Boolean> advancedFlag = Optional.of(false);

    public void addProperty(PropertyListElementDefinition property, String value) {

        switch (property) {
        case CONTENT: {
            content = Optional.of(Content.fromValue(value));
        }
            break;
        case DATA_SOURCE_INFO: {
            dataSourceInfo = Optional.of(value);
        }
            break;
        case FORMAT: {
            format = Optional.of(Format.fromValue(value));
        }
            break;
        case LOCALE_IDENTIFIER: {
            localeIdentifier = Optional.of(Integer.valueOf(value));
        }
            break;
        default:
            break;
        }

    }

    public Optional<String> applicationContext() {
        return applicationContext;
    }

    @Override
    public Optional<AxisFormat> axisFormat() {
        return axisFormat;
    }

    public Optional<BigInteger> beginRange() {
        return beginRange;
    }

    @Override
    public Optional<String> catalog() {
        return catalog;
    }

    public Optional<BigInteger> catalogLocation() {
        return catalogLocation;
    }

    public Optional<BigInteger> clientProcessID() {
        return clientProcessID;
    }

    public Optional<BigInteger> commitTimeout() {
        return commitTimeout;
    }

    @Override
    public Optional<Content> content() {
        return content;
    }

    public Optional<String> cube() {
        return cube;
    }

    public Optional<String> customData() {
        return customData;
    }

    @Override
    public Optional<String> dataSourceInfo() {
        return dataSourceInfo;
    }

    public Optional<String> dbmsVersion() {
        return dbmsVersion;
    }

    public Optional<String> dbpropCatalogTerm() {
        return dbpropCatalogTerm;
    }

    public Optional<BigInteger> dbpropCatalogUsage() {
        return dbpropCatalogUsage;
    }

    public Optional<BigInteger> dbpropColumnDefinition() {
        return dbpropColumnDefinition;
    }

    public Optional<BigInteger> dbpropConcatNullBehavior() {
        return dbpropConcatNullBehavior;
    }

    public Optional<Boolean> dbpropDataSourceReadOnly() {
        return dbpropDataSourceReadOnly;
    }

    public Optional<BigInteger> dbpropGroupBy() {
        return dbpropGroupBy;
    }

    public Optional<BigInteger> dbpropHeterogeneousTables() {
        return dbpropHeterogeneousTables;
    }

    public Optional<BigInteger> dbpropIdentifierCase() {
        return dbpropIdentifierCase;
    }

    public Optional<BigInteger> dbpropInitMode() {
        return dbpropInitMode;
    }

    public Optional<BigInteger> dbpropMaxIndexSize() {
        return dbpropMaxIndexSize;
    }

    public Optional<BigInteger> dbpropMaxOpenChapters() {
        return dbpropMaxOpenChapters;
    }

    public Optional<BigInteger> dbpropMaxRowSize() {
        return dbpropMaxRowSize;
    }

    public Optional<Boolean> dbpropMaxRowSizeIncludeBlob() {
        return dbpropMaxRowSizeIncludeBlob;
    }

    public Optional<BigInteger> dbpropMaxTablesInSelect() {
        return dbpropMaxTablesInSelect;
    }

    public Optional<String> dbpropMsmdActivityID() {
        return dbpropMsmdActivityID;
    }

    public Optional<BigInteger> dbpropMsmdAutoExists() {
        return dbpropMsmdAutoExists;
    }

    public Optional<BigInteger> dbpropMsmdCacheMode() {
        return dbpropMsmdCacheMode;
    }

    public Optional<BigInteger> dbpropMsmdCachePolicy() {
        return dbpropMsmdCachePolicy;
    }

    public Optional<BigInteger> dbpropMsmdCacheRatio() {
        return dbpropMsmdCacheRatio;
    }

    public Optional<Double> dbpropMsmdCacheRatio2() {
        return dbpropMsmdCacheRatio2;
    }

    public Optional<BigInteger> dbpropMsmdCompareCaseNotSensitiveStringFlags() {
        return dbpropMsmdCompareCaseNotSensitiveStringFlags;
    }

    public Optional<BigInteger> dbpropMsmdCompareCaseSensitiveStringFlags() {
        return dbpropMsmdCompareCaseSensitiveStringFlags;
    }

    public Optional<String> dbpropMsmdDebugMode() {
        return dbpropMsmdDebugMode;
    }

    public Optional<BigInteger> dbpropMsmdDynamicDebugLimit() {
        return dbpropMsmdDynamicDebugLimit;
    }

    public Optional<BigInteger> dbpropMsmdErrorMessageMode() {
        return dbpropMsmdErrorMessageMode;
    }

    public Optional<Boolean> dbpropMsmdFlattened2() {
        return dbpropMsmdFlattened2;
    }

    public Optional<BigInteger> dbpropMsmdMDXCompatibility() {
        return dbpropMsmdMDXCompatibility;
    }

    public Optional<BigInteger> dbpropMsmdMDXUniqueNameStyle() {
        return dbpropMsmdMDXUniqueNameStyle;
    }

    public Optional<BigInteger> dbpropMsmdOptimizeResponse() {
        return dbpropMsmdOptimizeResponse;
    }

    public Optional<String> dbpropMsmdRequestID() {
        return dbpropMsmdRequestID;
    }

    public Optional<BigInteger> dbpropMsmdRequestMemoryLimit() {
        return dbpropMsmdRequestMemoryLimit;
    }

    public Optional<BigInteger> dbpropMsmdSQLCompatibility() {
        return dbpropMsmdSQLCompatibility;
    }

    public Optional<BigInteger> dbpropMsmdSubqueries() {
        return dbpropMsmdSubqueries;
    }

    public Optional<String> dbpropMsmdUseFormulaCache() {
        return dbpropMsmdUseFormulaCache;
    }

    public Optional<Boolean> dbpropMultiTableUpdate() {
        return dbpropMultiTableUpdate;
    }

    public Optional<BigInteger> dbpropNullCollation() {
        return dbpropNullCollation;
    }

    public Optional<Boolean> dbpropOrderByColumnsInSelect() {
        return dbpropOrderByColumnsInSelect;
    }

    public Optional<BigInteger> dbpropOutputParameterAvailable() {
        return dbpropOutputParameterAvailable;
    }

    public Optional<BigInteger> dbpropPersistentIdType() {
        return dbpropPersistentIdType;
    }

    public Optional<BigInteger> dbpropPrepareAbortBehavior() {
        return dbpropPrepareAbortBehavior;
    }

    public Optional<BigInteger> dbpropPrepareCommitBehavior() {
        return dbpropPrepareCommitBehavior;
    }

    public Optional<String> dbpropProcedureTerm() {
        return dbpropProcedureTerm;
    }

    public Optional<BigInteger> dbpropQuotedIdentifierCase() {
        return dbpropQuotedIdentifierCase;
    }

    public Optional<BigInteger> dbpropSchemaUsage() {
        return dbpropSchemaUsage;
    }

    public Optional<BigInteger> dbpropSqlSupport() {
        return dbpropSqlSupport;
    }

    public Optional<BigInteger> dbpropSubqueries() {
        return dbpropSubqueries;
    }

    public Optional<BigInteger> dbpropSupportedTxnDdl() {
        return dbpropSupportedTxnDdl;
    }

    public Optional<BigInteger> dbpropSupportedTxnIsoLevels() {
        return dbpropSupportedTxnIsoLevels;
    }

    public Optional<BigInteger> dbpropSupportedTxnIsoRetain() {
        return dbpropSupportedTxnIsoRetain;
    }

    public Optional<String> dbpropTableTerm() {
        return dbpropTableTerm;
    }

    public Optional<String> dialect() {
        return dialect;
    }

    public Optional<Boolean> disablePrefetchFacts() {
        return disablePrefetchFacts;
    }

    public Optional<String> effectiveRoles() {
        return effectiveRoles;
    }

    public Optional<String> effectiveUserName() {
        return effectiveUserName;
    }

    public Optional<BigInteger> endRange() {
        return endRange;
    }

    public Optional<String> executionMode() {
        return executionMode;
    }

    public Optional<BigInteger> forceCommitTimeout() {
        return forceCommitTimeout;
    }

    @Override
    public Optional<Format> format() {
        return format;
    }

    public Optional<Boolean> impactAnalysis() {
        return impactAnalysis;
    }

    @Override
    public Optional<Integer> localeIdentifier() {
        return localeIdentifier;
    }

    public Optional<BigInteger> maximumRows() {
        return maximumRows;
    }

    public Optional<BigInteger> mdpropAggregateCellUpdate() {
        return mdpropAggregateCellUpdate;
    }

    public Optional<BigInteger> mdpropAxes() {
        return mdpropAxes;
    }

    public Optional<BigInteger> mdpropFlatteningSupport() {
        return mdpropFlatteningSupport;
    }

    public Optional<BigInteger> mdpropMdxCaseSupport() {
        return mdpropMdxCaseSupport;
    }

    public Optional<BigInteger> mdpropMdxDdlExtensions() {
        return mdpropMdxDdlExtensions;
    }

    public Optional<BigInteger> mdpropMdxDescFlags() {
        return mdpropMdxDescFlags;
    }

    public Optional<BigInteger> mdpropMdxDrillFunctions() {
        return mdpropMdxDrillFunctions;
    }

    public Optional<BigInteger> mdpropMdxFormulas() {
        return mdpropMdxFormulas;
    }

    public Optional<BigInteger> mdpropMdxJoinCubes() {
        return mdpropMdxJoinCubes;
    }

    public Optional<BigInteger> mdpropMdxMemberFunctions() {
        return mdpropMdxMemberFunctions;
    }

    public Optional<BigInteger> mdpropMdxNamedSets() {
        return mdpropMdxNamedSets;
    }

    public Optional<BigInteger> mdpropMdxNonMeasureExpressions() {
        return mdpropMdxNonMeasureExpressions;
    }

    public Optional<BigInteger> mdpropMdxNumericFunctions() {
        return mdpropMdxNumericFunctions;
    }

    public Optional<BigInteger> mdpropMdxObjQualification() {
        return mdpropMdxObjQualification;
    }

    public Optional<BigInteger> mdpropMdxOuterReference() {
        return mdpropMdxOuterReference;
    }

    public Optional<Boolean> mdpropMdxQueryByProperty() {
        return mdpropMdxQueryByProperty;
    }

    public Optional<BigInteger> mdpropMdxRangeRowset() {
        return mdpropMdxRangeRowset;
    }

    public Optional<BigInteger> mdpropMdxSetFunctions() {
        return mdpropMdxSetFunctions;
    }

    public Optional<BigInteger> mdpropMdxSlicer() {
        return mdpropMdxSlicer;
    }

    public Optional<BigInteger> mdpropMdxStringCompop() {
        return mdpropMdxStringCompop;
    }

    public Optional<BigInteger> mdpropMdxSubqueries() {
        return mdpropMdxSubqueries;
    }

    public Optional<BigInteger> mdpropNamedLevels() {
        return mdpropNamedLevels;
    }

    public Optional<String> mdxMissingMemberMode() {
        return mdxMissingMemberMode;
    }

    public Optional<String> mdxSupport() {
        return mdxSupport;
    }

    public Optional<BigInteger> memoryLockingMode() {
        return memoryLockingMode;
    }

    public Optional<BigInteger> nonEmptyThreshold() {
        return nonEmptyThreshold;
    }

    public Optional<String> password() {
        return password;
    }

    public Optional<String> providerName() {
        return providerName;
    }

    public Optional<BigInteger> providerType() {
        return providerType;
    }

    public Optional<String> providerVersion() {
        return providerVersion;
    }

    public Optional<BigInteger> readOnlySession() {
        return readOnlySession;
    }

    public Optional<Boolean> realTimeOlap() {
        return realTimeOlap;
    }

    public Optional<String> responseEncoding() {
        return responseEncoding;
    }

    public Optional<BigInteger> returnAffectedObjects() {
        return returnAffectedObjects;
    }

    public Optional<Boolean> returnCellProperties() {
        return returnCellProperties;
    }

    public Optional<String> roles() {
        return roles;
    }

    public Optional<BigInteger> safetyOptions() {
        return safetyOptions;
    }

    public Optional<BigInteger> securedCellValue() {
        return securedCellValue;
    }

    public Optional<String> serverName() {
        return serverName;
    }

    public void setApplicationContext(Optional<String> applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setAxisFormat(Optional<AxisFormat> axisFormat) {
        this.axisFormat = axisFormat;
    }

    public void setBeginRange(Optional<BigInteger> beginRange) {
        this.beginRange = beginRange;
    }

    public void setCatalog(Optional<String> catalog) {
        this.catalog = catalog;
    }

    public void setCatalogLocation(Optional<BigInteger> catalogLocation) {
        this.catalogLocation = catalogLocation;
    }

    public void setClientProcessID(Optional<BigInteger> clientProcessID) {
        this.clientProcessID = clientProcessID;
    }

    public void setCommitTimeout(Optional<BigInteger> commitTimeout) {
        this.commitTimeout = commitTimeout;
    }

    public void setContent(Optional<Content> content) {
        this.content = content;
    }

    public void setCube(Optional<String> cube) {
        this.cube = cube;
    }

    public void setCustomData(Optional<String> customData) {
        this.customData = customData;
    }

    public void setDataSourceInfo(Optional<String> dataSourceInfo) {
        this.dataSourceInfo = dataSourceInfo;
    }

    public void setDbmsVersion(Optional<String> dbmsVersion) {
        this.dbmsVersion = dbmsVersion;
    }

    public void setDbpropCatalogTerm(Optional<String> dbpropCatalogTerm) {
        this.dbpropCatalogTerm = dbpropCatalogTerm;
    }

    public void setDbpropCatalogUsage(Optional<BigInteger> dbpropCatalogUsage) {
        this.dbpropCatalogUsage = dbpropCatalogUsage;
    }

    public void setDbpropColumnDefinition(Optional<BigInteger> dbpropColumnDefinition) {
        this.dbpropColumnDefinition = dbpropColumnDefinition;
    }

    public void setDbpropConcatNullBehavior(Optional<BigInteger> dbpropConcatNullBehavior) {
        this.dbpropConcatNullBehavior = dbpropConcatNullBehavior;
    }

    public void setDbpropDataSourceReadOnly(Optional<Boolean> dbpropDataSourceReadOnly) {
        this.dbpropDataSourceReadOnly = dbpropDataSourceReadOnly;
    }

    public void setDbpropGroupBy(Optional<BigInteger> dbpropGroupBy) {
        this.dbpropGroupBy = dbpropGroupBy;
    }

    public void setDbpropHeterogeneousTables(Optional<BigInteger> dbpropHeterogeneousTables) {
        this.dbpropHeterogeneousTables = dbpropHeterogeneousTables;
    }

    public void setDbpropIdentifierCase(Optional<BigInteger> dbpropIdentifierCase) {
        this.dbpropIdentifierCase = dbpropIdentifierCase;
    }

    public void setDbpropInitMode(Optional<BigInteger> dbpropInitMode) {
        this.dbpropInitMode = dbpropInitMode;
    }

    public void setDbpropMaxIndexSize(Optional<BigInteger> dbpropMaxIndexSize) {
        this.dbpropMaxIndexSize = dbpropMaxIndexSize;
    }

    public void setDbpropMaxOpenChapters(Optional<BigInteger> dbpropMaxOpenChapters) {
        this.dbpropMaxOpenChapters = dbpropMaxOpenChapters;
    }

    public void setDbpropMaxRowSize(Optional<BigInteger> dbpropMaxRowSize) {
        this.dbpropMaxRowSize = dbpropMaxRowSize;
    }

    public void setDbpropMaxRowSizeIncludeBlob(Optional<Boolean> dbpropMaxRowSizeIncludeBlob) {
        this.dbpropMaxRowSizeIncludeBlob = dbpropMaxRowSizeIncludeBlob;
    }

    public void setDbpropMaxTablesInSelect(Optional<BigInteger> dbpropMaxTablesInSelect) {
        this.dbpropMaxTablesInSelect = dbpropMaxTablesInSelect;
    }

    public void setDbpropMsmdActivityID(Optional<String> dbpropMsmdActivityID) {
        this.dbpropMsmdActivityID = dbpropMsmdActivityID;
    }

    public void setDbpropMsmdAutoExists(Optional<BigInteger> dbpropMsmdAutoExists) {
        this.dbpropMsmdAutoExists = dbpropMsmdAutoExists;
    }

    public void setDbpropMsmdCacheMode(Optional<BigInteger> dbpropMsmdCacheMode) {
        this.dbpropMsmdCacheMode = dbpropMsmdCacheMode;
    }

    public void setDbpropMsmdCachePolicy(Optional<BigInteger> dbpropMsmdCachePolicy) {
        this.dbpropMsmdCachePolicy = dbpropMsmdCachePolicy;
    }

    public void setDbpropMsmdCacheRatio(Optional<BigInteger> dbpropMsmdCacheRatio) {
        this.dbpropMsmdCacheRatio = dbpropMsmdCacheRatio;
    }

    public void setDbpropMsmdCacheRatio2(Optional<Double> dbpropMsmdCacheRatio2) {
        this.dbpropMsmdCacheRatio2 = dbpropMsmdCacheRatio2;
    }

    public void setDbpropMsmdCompareCaseNotSensitiveStringFlags(
            Optional<BigInteger> dbpropMsmdCompareCaseNotSensitiveStringFlags) {
        this.dbpropMsmdCompareCaseNotSensitiveStringFlags = dbpropMsmdCompareCaseNotSensitiveStringFlags;
    }

    public void setDbpropMsmdCompareCaseSensitiveStringFlags(
            Optional<BigInteger> dbpropMsmdCompareCaseSensitiveStringFlags) {
        this.dbpropMsmdCompareCaseSensitiveStringFlags = dbpropMsmdCompareCaseSensitiveStringFlags;
    }

    public void setDbpropMsmdDebugMode(Optional<String> dbpropMsmdDebugMode) {
        this.dbpropMsmdDebugMode = dbpropMsmdDebugMode;
    }

    public void setDbpropMsmdDynamicDebugLimit(Optional<BigInteger> dbpropMsmdDynamicDebugLimit) {
        this.dbpropMsmdDynamicDebugLimit = dbpropMsmdDynamicDebugLimit;
    }

    public void setDbpropMsmdErrorMessageMode(Optional<BigInteger> dbpropMsmdErrorMessageMode) {
        this.dbpropMsmdErrorMessageMode = dbpropMsmdErrorMessageMode;
    }

    public void setDbpropMsmdFlattened2(Optional<Boolean> dbpropMsmdFlattened2) {
        this.dbpropMsmdFlattened2 = dbpropMsmdFlattened2;
    }

    public void setDbpropMsmdMDXCompatibility(Optional<BigInteger> dbpropMsmdMDXCompatibility) {
        this.dbpropMsmdMDXCompatibility = dbpropMsmdMDXCompatibility;
    }

    public void setDbpropMsmdMDXUniqueNameStyle(Optional<BigInteger> dbpropMsmdMDXUniqueNameStyle) {
        this.dbpropMsmdMDXUniqueNameStyle = dbpropMsmdMDXUniqueNameStyle;
    }

    public void setDbpropMsmdOptimizeResponse(Optional<BigInteger> dbpropMsmdOptimizeResponse) {
        this.dbpropMsmdOptimizeResponse = dbpropMsmdOptimizeResponse;
    }

    public void setDbpropMsmdRequestID(Optional<String> dbpropMsmdRequestID) {
        this.dbpropMsmdRequestID = dbpropMsmdRequestID;
    }

    public void setDbpropMsmdRequestMemoryLimit(Optional<BigInteger> dbpropMsmdRequestMemoryLimit) {
        this.dbpropMsmdRequestMemoryLimit = dbpropMsmdRequestMemoryLimit;
    }

    public void setDbpropMsmdSQLCompatibility(Optional<BigInteger> dbpropMsmdSQLCompatibility) {
        this.dbpropMsmdSQLCompatibility = dbpropMsmdSQLCompatibility;
    }

    public void setDbpropMsmdSubqueries(Optional<BigInteger> dbpropMsmdSubqueries) {
        this.dbpropMsmdSubqueries = dbpropMsmdSubqueries;
    }

    public void setDbpropMsmdUseFormulaCache(Optional<String> dbpropMsmdUseFormulaCache) {
        this.dbpropMsmdUseFormulaCache = dbpropMsmdUseFormulaCache;
    }

    public void setDbpropMultiTableUpdate(Optional<Boolean> dbpropMultiTableUpdate) {
        this.dbpropMultiTableUpdate = dbpropMultiTableUpdate;
    }

    public void setDbpropNullCollation(Optional<BigInteger> dbpropNullCollation) {
        this.dbpropNullCollation = dbpropNullCollation;
    }

    public void setDbpropOrderByColumnsInSelect(Optional<Boolean> dbpropOrderByColumnsInSelect) {
        this.dbpropOrderByColumnsInSelect = dbpropOrderByColumnsInSelect;
    }

    public void setDbpropOutputParameterAvailable(Optional<BigInteger> dbpropOutputParameterAvailable) {
        this.dbpropOutputParameterAvailable = dbpropOutputParameterAvailable;
    }

    public void setDbpropPersistentIdType(Optional<BigInteger> dbpropPersistentIdType) {
        this.dbpropPersistentIdType = dbpropPersistentIdType;
    }

    public void setDbpropPrepareAbortBehavior(Optional<BigInteger> dbpropPrepareAbortBehavior) {
        this.dbpropPrepareAbortBehavior = dbpropPrepareAbortBehavior;
    }

    public void setDbpropPrepareCommitBehavior(Optional<BigInteger> dbpropPrepareCommitBehavior) {
        this.dbpropPrepareCommitBehavior = dbpropPrepareCommitBehavior;
    }

    public void setDbpropProcedureTerm(Optional<String> dbpropProcedureTerm) {
        this.dbpropProcedureTerm = dbpropProcedureTerm;
    }

    public void setDbpropQuotedIdentifierCase(Optional<BigInteger> dbpropQuotedIdentifierCase) {
        this.dbpropQuotedIdentifierCase = dbpropQuotedIdentifierCase;
    }

    public void setDbpropSchemaUsage(Optional<BigInteger> dbpropSchemaUsage) {
        this.dbpropSchemaUsage = dbpropSchemaUsage;
    }

    public void setDbpropSqlSupport(Optional<BigInteger> dbpropSqlSupport) {
        this.dbpropSqlSupport = dbpropSqlSupport;
    }

    public void setDbpropSubqueries(Optional<BigInteger> dbpropSubqueries) {
        this.dbpropSubqueries = dbpropSubqueries;
    }

    public void setDbpropSupportedTxnDdl(Optional<BigInteger> dbpropSupportedTxnDdl) {
        this.dbpropSupportedTxnDdl = dbpropSupportedTxnDdl;
    }

    public void setDbpropSupportedTxnIsoLevels(Optional<BigInteger> dbpropSupportedTxnIsoLevels) {
        this.dbpropSupportedTxnIsoLevels = dbpropSupportedTxnIsoLevels;
    }

    public void setDbpropSupportedTxnIsoRetain(Optional<BigInteger> dbpropSupportedTxnIsoRetain) {
        this.dbpropSupportedTxnIsoRetain = dbpropSupportedTxnIsoRetain;
    }

    public void setDbpropTableTerm(Optional<String> dbpropTableTerm) {
        this.dbpropTableTerm = dbpropTableTerm;
    }

    public void setDialect(Optional<String> dialect) {
        this.dialect = dialect;
    }

    public void setDisablePrefetchFacts(Optional<Boolean> disablePrefetchFacts) {
        this.disablePrefetchFacts = disablePrefetchFacts;
    }

    public void setEffectiveRoles(Optional<String> effectiveRoles) {
        this.effectiveRoles = effectiveRoles;
    }

    public void setEffectiveUserName(Optional<String> effectiveUserName) {
        this.effectiveUserName = effectiveUserName;
    }

    public void setEndRange(Optional<BigInteger> endRange) {
        this.endRange = endRange;
    }

    public void setExecutionMode(Optional<String> executionMode) {
        this.executionMode = executionMode;
    }

    public void setForceCommitTimeout(Optional<BigInteger> forceCommitTimeout) {
        this.forceCommitTimeout = forceCommitTimeout;
    }

    public void setFormat(Optional<Format> format) {
        this.format = format;
    }

    public void setImpactAnalysis(Optional<Boolean> impactAnalysis) {
        this.impactAnalysis = impactAnalysis;
    }

    public void setLocaleIdentifier(Optional<Integer> localeIdentifier) {
        this.localeIdentifier = localeIdentifier;
    }

    public void setMaximumRows(Optional<BigInteger> maximumRows) {
        this.maximumRows = maximumRows;
    }

    public void setMdpropAggregateCellUpdate(Optional<BigInteger> mdpropAggregateCellUpdate) {
        this.mdpropAggregateCellUpdate = mdpropAggregateCellUpdate;
    }

    public void setMdpropAxes(Optional<BigInteger> mdpropAxes) {
        this.mdpropAxes = mdpropAxes;
    }

    public void setMdpropFlatteningSupport(Optional<BigInteger> mdpropFlatteningSupport) {
        this.mdpropFlatteningSupport = mdpropFlatteningSupport;
    }

    public void setMdpropMdxCaseSupport(Optional<BigInteger> mdpropMdxCaseSupport) {
        this.mdpropMdxCaseSupport = mdpropMdxCaseSupport;
    }

    public void setMdpropMdxDdlExtensions(Optional<BigInteger> mdpropMdxDdlExtensions) {
        this.mdpropMdxDdlExtensions = mdpropMdxDdlExtensions;
    }

    public void setMdpropMdxDescFlags(Optional<BigInteger> mdpropMdxDescFlags) {
        this.mdpropMdxDescFlags = mdpropMdxDescFlags;
    }

    public void setMdpropMdxDrillFunctions(Optional<BigInteger> mdpropMdxDrillFunctions) {
        this.mdpropMdxDrillFunctions = mdpropMdxDrillFunctions;
    }

    public void setMdpropMdxFormulas(Optional<BigInteger> mdpropMdxFormulas) {
        this.mdpropMdxFormulas = mdpropMdxFormulas;
    }

    public void setMdpropMdxJoinCubes(Optional<BigInteger> mdpropMdxJoinCubes) {
        this.mdpropMdxJoinCubes = mdpropMdxJoinCubes;
    }

    public void setMdpropMdxMemberFunctions(Optional<BigInteger> mdpropMdxMemberFunctions) {
        this.mdpropMdxMemberFunctions = mdpropMdxMemberFunctions;
    }

    public void setMdpropMdxNamedSets(Optional<BigInteger> mdpropMdxNamedSets) {
        this.mdpropMdxNamedSets = mdpropMdxNamedSets;
    }

    public void setMdpropMdxNonMeasureExpressions(Optional<BigInteger> mdpropMdxNonMeasureExpressions) {
        this.mdpropMdxNonMeasureExpressions = mdpropMdxNonMeasureExpressions;
    }

    public void setMdpropMdxNumericFunctions(Optional<BigInteger> mdpropMdxNumericFunctions) {
        this.mdpropMdxNumericFunctions = mdpropMdxNumericFunctions;
    }

    public void setMdpropMdxObjQualification(Optional<BigInteger> mdpropMdxObjQualification) {
        this.mdpropMdxObjQualification = mdpropMdxObjQualification;
    }

    public void setMdpropMdxOuterReference(Optional<BigInteger> mdpropMdxOuterReference) {
        this.mdpropMdxOuterReference = mdpropMdxOuterReference;
    }

    public void setMdpropMdxQueryByProperty(Optional<Boolean> mdpropMdxQueryByProperty) {
        this.mdpropMdxQueryByProperty = mdpropMdxQueryByProperty;
    }

    public void setMdpropMdxRangeRowset(Optional<BigInteger> mdpropMdxRangeRowset) {
        this.mdpropMdxRangeRowset = mdpropMdxRangeRowset;
    }

    public void setMdpropMdxSetFunctions(Optional<BigInteger> mdpropMdxSetFunctions) {
        this.mdpropMdxSetFunctions = mdpropMdxSetFunctions;
    }

    public void setMdpropMdxSlicer(Optional<BigInteger> mdpropMdxSlicer) {
        this.mdpropMdxSlicer = mdpropMdxSlicer;
    }

    public void setMdpropMdxStringCompop(Optional<BigInteger> mdpropMdxStringCompop) {
        this.mdpropMdxStringCompop = mdpropMdxStringCompop;
    }

    public void setMdpropMdxSubqueries(Optional<BigInteger> mdpropMdxSubqueries) {
        this.mdpropMdxSubqueries = mdpropMdxSubqueries;
    }

    public void setMdpropNamedLevels(Optional<BigInteger> mdpropNamedLevels) {
        this.mdpropNamedLevels = mdpropNamedLevels;
    }

    public void setMdxMissingMemberMode(Optional<String> mdxMissingMemberMode) {
        this.mdxMissingMemberMode = mdxMissingMemberMode;
    }

    public void setMdxSupport(Optional<String> mdxSupport) {
        this.mdxSupport = mdxSupport;
    }

    public void setMemoryLockingMode(Optional<BigInteger> memoryLockingMode) {
        this.memoryLockingMode = memoryLockingMode;
    }

    public void setNonEmptyThreshold(Optional<BigInteger> nonEmptyThreshold) {
        this.nonEmptyThreshold = nonEmptyThreshold;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }

    public void setProviderName(Optional<String> providerName) {
        this.providerName = providerName;
    }

    public void setProviderType(Optional<BigInteger> providerType) {
        this.providerType = providerType;
    }

    public void setProviderVersion(Optional<String> providerVersion) {
        this.providerVersion = providerVersion;
    }

    public void setReadOnlySession(Optional<BigInteger> readOnlySession) {
        this.readOnlySession = readOnlySession;
    }

    public void setRealTimeOlap(Optional<Boolean> realTimeOlap) {
        this.realTimeOlap = realTimeOlap;
    }

    public void setResponseEncoding(Optional<String> responseEncoding) {
        this.responseEncoding = responseEncoding;
    }

    public void setReturnAffectedObjects(Optional<BigInteger> returnAffectedObjects) {
        this.returnAffectedObjects = returnAffectedObjects;
    }

    public void setReturnCellProperties(Optional<Boolean> returnCellProperties) {
        this.returnCellProperties = returnCellProperties;
    }

    public void setRoles(Optional<String> roles) {
        this.roles = roles;
    }

    public void setSafetyOptions(Optional<BigInteger> safetyOptions) {
        this.safetyOptions = safetyOptions;
    }

    public void setSecuredCellValue(Optional<BigInteger> securedCellValue) {
        this.securedCellValue = securedCellValue;
    }

    public void setServerName(Optional<String> serverName) {
        this.serverName = serverName;
    }

    public void setShowHiddenCubes(Optional<Boolean> showHiddenCubes) {
        this.showHiddenCubes = showHiddenCubes;
    }

    public void setSqlQueryMode(Optional<String> sqlQueryMode) {
        this.sqlQueryMode = sqlQueryMode;
    }

    public void setSqlSupport(Optional<BigInteger> sqlSupport) {
        this.sqlSupport = sqlSupport;
    }

    public void setSspropInitAppName(Optional<String> sspropInitAppName) {
        this.sspropInitAppName = sspropInitAppName;
    }

    public void setSspropInitPacketsize(Optional<BigInteger> sspropInitPacketsize) {
        this.sspropInitPacketsize = sspropInitPacketsize;
    }

    public void setSspropInitWsid(Optional<String> sspropInitWsid) {
        this.sspropInitWsid = sspropInitWsid;
    }

    public void setStateSupport(Optional<String> stateSupport) {
        this.stateSupport = stateSupport;
    }

    public void setTimeout(Optional<BigInteger> timeout) {
        this.timeout = timeout;
    }

    public void setTransactionDDL(Optional<BigInteger> transactionDDL) {
        this.transactionDDL = transactionDDL;
    }

    public void setUpdateIsolationLevel(Optional<BigInteger> updateIsolationLevel) {
        this.updateIsolationLevel = updateIsolationLevel;
    }

    public void setUserName(Optional<String> userName) {
        this.userName = userName;
    }

    public void setVisualMode(Optional<BigInteger> visualMode) {
        this.visualMode = visualMode;
    }

    public Optional<Boolean> showHiddenCubes() {
        return showHiddenCubes;
    }

    public Optional<String> sqlQueryMode() {
        return sqlQueryMode;
    }

    public Optional<BigInteger> sqlSupport() {
        return sqlSupport;
    }

    public Optional<String> sspropInitAppName() {
        return sspropInitAppName;
    }

    public Optional<BigInteger> sspropInitPacketsize() {
        return sspropInitPacketsize;
    }

    public Optional<String> sspropInitWsid() {
        return sspropInitWsid;
    }

    public Optional<String> stateSupport() {
        return stateSupport;
    }

    public Optional<BigInteger> timeout() {
        return timeout;
    }

    public Optional<BigInteger> transactionDDL() {
        return transactionDDL;
    }

    public Optional<BigInteger> updateIsolationLevel() {
        return updateIsolationLevel;
    }

    public Optional<String> userName() {
        return userName;
    }

    public Optional<BigInteger> visualMode() {
        return visualMode;
    }

    public Optional<Boolean> deep() {
        return deep;
    }

    public Optional<Boolean> emitInvisibleMembers() {
        return emitInvisibleMembers;
    }

    @Override
    public Optional<String> tableFields() {
        return tableFields;
    }

    @Override
    public Optional<Boolean> advancedFlag() {
        return advancedFlag;
    }

    public void setDeep(Optional<Boolean> deep) {
        this.deep = deep;
    }

    public void setByname(String name, String value) {
        switch (name) {
        case "Catalog":
            setCatalog(Optional.ofNullable(value));
            break;
        case "AxisFormat":
            setAxisFormat(Optional.ofNullable(AxisFormat.fromValue(value)));
            break;
        // TODO
        }
    }
}
