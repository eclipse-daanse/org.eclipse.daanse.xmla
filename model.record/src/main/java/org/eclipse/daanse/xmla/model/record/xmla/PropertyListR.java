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
package org.eclipse.daanse.xmla.model.record.xmla;

import java.math.BigInteger;

import org.eclipse.daanse.xmla.api.xmla.PropertyList;

public record PropertyListR(String dataSourceInfo, BigInteger timeout, String userName, String password,
        Integer localeIdentifier, String catalog, String stateSupport, String content, String format, String axisFormat,
        BigInteger beginRange, BigInteger endRange, String mdxSupport, String providerName, String providerVersion,
        String dbmsVersion, BigInteger providerType, Boolean showHiddenCubes, BigInteger sqlSupport,
        BigInteger transactionDDL, BigInteger maximumRows, String roles, BigInteger visualMode, String effectiveRoles,
        String effectiveUserName, String serverName, BigInteger catalogLocation, String dbpropCatalogTerm,
        BigInteger dbpropCatalogUsage, BigInteger dbpropColumnDefinition, BigInteger dbpropConcatNullBehavior,
        Boolean dbpropDataSourceReadOnly, BigInteger dbpropGroupBy, BigInteger dbpropHeterogeneousTables,
        BigInteger dbpropIdentifierCase, BigInteger dbpropMaxIndexSize, BigInteger dbpropMaxOpenChapters,
        BigInteger dbpropMaxRowSize, Boolean dbpropMaxRowSizeIncludeBlob, BigInteger dbpropMaxTablesInSelect,
        Boolean dbpropMultiTableUpdate, BigInteger dbpropNullCollation, Boolean dbpropOrderByColumnsInSelect,
        BigInteger dbpropOutputParameterAvailable, BigInteger dbpropPersistentIdType,
        BigInteger dbpropPrepareAbortBehavior, BigInteger dbpropPrepareCommitBehavior, String dbpropProcedureTerm,
        BigInteger dbpropQuotedIdentifierCase, BigInteger dbpropSchemaUsage, BigInteger dbpropSqlSupport,
        BigInteger dbpropSubqueries, BigInteger dbpropSupportedTxnDdl, BigInteger mdpropMdxSubqueries,
        BigInteger dbpropSupportedTxnIsoLevels, BigInteger dbpropSupportedTxnIsoRetain, String dbpropTableTerm,
        BigInteger mdpropAggregateCellUpdate, BigInteger mdpropAxes, BigInteger mdpropFlatteningSupport,
        BigInteger mdpropMdxCaseSupport, BigInteger mdpropMdxDescFlags, BigInteger mdpropMdxDrillFunctions,
        BigInteger mdpropMdxFormulas, BigInteger mdpropMdxJoinCubes, BigInteger mdpropMdxMemberFunctions,
        BigInteger mdpropMdxNonMeasureExpressions, BigInteger mdpropMdxNumericFunctions,
        BigInteger mdpropMdxObjQualification, BigInteger mdpropMdxOuterReference, Boolean mdpropMdxQueryByProperty,
        BigInteger mdpropMdxRangeRowset, BigInteger mdpropMdxSetFunctions, BigInteger mdpropMdxSlicer,
        BigInteger mdpropMdxStringCompop, BigInteger mdpropNamedLevels, BigInteger dbpropMsmdMDXCompatibility,
        BigInteger dbpropMsmdSQLCompatibility, BigInteger dbpropMsmdMDXUniqueNameStyle,
        BigInteger dbpropMsmdCachePolicy, BigInteger dbpropMsmdCacheRatio, BigInteger dbpropMsmdCacheMode,
        BigInteger dbpropMsmdCompareCaseSensitiveStringFlags, BigInteger dbpropMsmdCompareCaseNotSensitiveStringFlags,
        Boolean dbpropMsmdFlattened2, BigInteger dbpropInitMode, String sspropInitAppName, String sspropInitWsid,
        BigInteger sspropInitPacketsize, BigInteger readOnlySession, BigInteger securedCellValue,
        BigInteger nonEmptyThreshold, BigInteger safetyOptions, Double dbpropMsmdCacheRatio2,
        String dbpropMsmdUseFormulaCache, BigInteger dbpropMsmdDynamicDebugLimit, String dbpropMsmdDebugMode,
        String dialect, Boolean impactAnalysis, String sqlQueryMode, BigInteger clientProcessID, String cube,
        Boolean returnCellProperties, BigInteger commitTimeout, BigInteger forceCommitTimeout, String executionMode,
        Boolean realTimeOlap, String mdxMissingMemberMode, BigInteger mdpropMdxNamedSets,
        BigInteger dbpropMsmdSubqueries, BigInteger dbpropMsmdAutoExists, String customData,
        Boolean disablePrefetchFacts, BigInteger updateIsolationLevel, BigInteger dbpropMsmdErrorMessageMode,
        BigInteger mdpropMdxDdlExtensions, String responseEncoding, BigInteger memoryLockingMode,
        BigInteger dbpropMsmdOptimizeResponse, String dbpropMsmdActivityID, String dbpropMsmdRequestID,
        BigInteger returnAffectedObjects, BigInteger dbpropMsmdRequestMemoryLimit, String applicationContext)
        implements PropertyList {

}
