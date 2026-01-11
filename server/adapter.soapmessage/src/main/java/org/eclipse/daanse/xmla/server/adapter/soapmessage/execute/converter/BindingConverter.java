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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter;

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_SOURCE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_SOURCE_VIEW_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.EXPRESSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.FILTER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ORDINAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.REFRESH_INTERVAL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TABLE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getList;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getStringList;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBigInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toDuration;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInteger;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.AttributeBindingTypeEnum;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.FiscalYearNameEnum;
import org.eclipse.daanse.xmla.api.xmla.Group;
import org.eclipse.daanse.xmla.api.xmla.IncrementalProcessingNotification;
import org.eclipse.daanse.xmla.api.xmla.PersistenceEnum;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCachingBinding;
import org.eclipse.daanse.xmla.api.xmla.RefreshPolicyEnum;
import org.eclipse.daanse.xmla.api.xmla.ReportingWeekToMonthPatternEnum;
import org.eclipse.daanse.xmla.api.xmla.TabularBinding;
import org.eclipse.daanse.xmla.model.record.engine200_200.ExpressionBindingR;
import org.eclipse.daanse.xmla.model.record.engine200_200.RowNumberBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.AttributeBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.CalculatedMeasureBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.ColumnBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.CubeAttributeBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.CubeDimensionBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DSVTableBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourceViewBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DimensionBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.GroupR;
import org.eclipse.daanse.xmla.model.record.xmla.IncrementalProcessingNotificationR;
import org.eclipse.daanse.xmla.model.record.xmla.InheritedBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureGroupDimensionBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.ProactiveCachingBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.ProactiveCachingIncrementalProcessingBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.QueryBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.RowBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.TableBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.TimeAttributeBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.TimeBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.UserDefinedGroupBindingR;
import org.w3c.dom.NodeList;

/**
 * Converter for Binding-related XMLA types. Handles all Binding variants
 * including TabularBinding and ProactiveCachingBinding.
 */
public class BindingConverter {

    private BindingConverter() {
        // utility class
    }

    /**
     * Functional interface for column binding parsing dependency.
     */
    @FunctionalInterface
    public interface ColumnBindingParser {
        Binding getColumnBinding(NodeList nl);
    }

    /**
     * Functional interface for MeasureGroupBinding parsing dependency.
     */
    @FunctionalInterface
    public interface MeasureGroupBindingParser {
        Binding getMeasureGroupBinding(NodeList nl);
    }

    /**
     * Simplified getBinding without callbacks. For ColumnBinding and
     * MeasureGroupBinding, use the full version with callbacks.
     */
    public static Binding getBinding(NodeList nl, String type) {
        if ("ColumnBinding".equals(type)) {
            return getColumnBinding(nl);
        }
        if ("RowBinding".equals(type)) {
            return getRowBinding(nl);
        }
        if ("DataSourceViewBinding".equals(type)) {
            return getDataSourceViewBinding(nl);
        }
        if ("AttributeBinding".equals(type)) {
            return getAttributeBinding(nl);
        }
        if ("UserDefinedGroupBinding".equals(type)) {
            return getUserDefinedGroupBinding(nl);
        }
        if ("MeasureBinding".equals(type)) {
            return getMeasureBinding(nl);
        }
        if ("CubeAttributeBinding".equals(type)) {
            return getCubeAttributeBinding(nl);
        }
        if ("DimensionBinding".equals(type)) {
            return getDimensionBinding(nl);
        }
        if ("CubeDimensionBinding".equals(type)) {
            return getCubeDimensionBinding(nl);
        }
        if ("MeasureGroupBinding".equals(type)) {
            // MeasureGroupBinding requires CubeConverter - use full version with callback
            return null;
        }
        if ("MeasureGroupDimensionBinding".equals(type)) {
            return getMeasureGroupDimensionBinding(nl);
        }
        if ("TimeBinding".equals(type)) {
            return getTimeBinding(nl);
        }
        if ("TimeAttributeBinding".equals(type)) {
            return getTimeAttributeBinding();
        }
        if ("InheritedBinding".equals(type)) {
            return getInheritedBinding();
        }
        if ("CalculatedMeasureBinding".equals(type)) {
            return getCalculatedMeasureBinding(nl);
        }
        if ("RowNumberBinding".equals(type)) {
            return getRowNumberBinding();
        }
        if ("ExpressionBinding".equals(type)) {
            return getExpressionBinding(nl);
        }
        return null;
    }

    // Main Binding dispatcher with callbacks (for complex cases)
    public static Binding getBinding(NodeList nl, String type, ColumnBindingParser columnParser,
            MeasureGroupBindingParser mgParser) {
        if ("ColumnBinding".equals(type)) {
            return columnParser.getColumnBinding(nl);
        }
        if ("RowBinding".equals(type)) {
            return getRowBinding(nl);
        }
        if ("DataSourceViewBinding".equals(type)) {
            return getDataSourceViewBinding(nl);
        }
        if ("AttributeBinding".equals(type)) {
            return getAttributeBinding(nl);
        }
        if ("UserDefinedGroupBinding".equals(type)) {
            return getUserDefinedGroupBinding(nl);
        }
        if ("MeasureBinding".equals(type)) {
            return getMeasureBinding(nl);
        }
        if ("CubeAttributeBinding".equals(type)) {
            return getCubeAttributeBinding(nl);
        }
        if ("DimensionBinding".equals(type)) {
            return getDimensionBinding(nl);
        }
        if ("CubeDimensionBinding".equals(type)) {
            return getCubeDimensionBinding(nl);
        }
        if ("MeasureGroupBinding".equals(type)) {
            return mgParser.getMeasureGroupBinding(nl);
        }
        if ("MeasureGroupDimensionBinding".equals(type)) {
            return getMeasureGroupDimensionBinding(nl);
        }
        if ("TimeBinding".equals(type)) {
            return getTimeBinding(nl);
        }
        if ("TimeAttributeBinding".equals(type)) {
            return getTimeAttributeBinding();
        }
        if ("InheritedBinding".equals(type)) {
            return getInheritedBinding();
        }
        if ("CalculatedMeasureBinding".equals(type)) {
            return getCalculatedMeasureBinding(nl);
        }
        if ("RowNumberBinding".equals(type)) {
            return getRowNumberBinding();
        }
        if ("ExpressionBinding".equals(type)) {
            return getExpressionBinding(nl);
        }
        return null;
    }

    public static Binding getExpressionBinding(NodeList nl) {
        String expression = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && (EXPRESSION.equals(node.getNodeName()))) {
                expression = node.getTextContent();
            }
        }
        return new ExpressionBindingR(expression);
    }

    public static Binding getRowNumberBinding() {
        return new RowNumberBindingR();
    }

    public static Binding getCalculatedMeasureBinding(NodeList nl) {
        String measureName = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("MeasureName".equals(node.getNodeName()))) {
                measureName = node.getTextContent();
            }
        }
        return new CalculatedMeasureBindingR(measureName);
    }

    public static Binding getInheritedBinding() {
        return new InheritedBindingR();
    }

    public static Binding getTimeAttributeBinding() {
        return new TimeAttributeBindingR();
    }

    public static Binding getTimeBinding(NodeList nl) {
        Instant calendarStartDate = null;
        Instant calendarEndDate = null;
        Integer firstDayOfWeek = null;
        BigInteger calendarLanguage = null;
        Integer fiscalFirstMonth = null;
        Integer fiscalFirstDayOfMonth = null;
        FiscalYearNameEnum fiscalYearName = null;
        Integer reportingFirstMonth = null;
        String reportingFirstWeekOfMonth = null;
        ReportingWeekToMonthPatternEnum reportingWeekToMonthPattern = null;
        Integer manufacturingFirstMonth = null;
        Integer manufacturingFirstWeekOfMonth = null;
        Integer manufacturingExtraMonthQuarter = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("CalendarStartDate".equals(node.getNodeName())) {
                    calendarStartDate = toInstant(node.getTextContent());
                }
                if ("CalendarEndDate".equals(node.getNodeName())) {
                    calendarEndDate = toInstant(node.getTextContent());
                }
                if ("FirstDayOfWeek".equals(node.getNodeName())) {
                    firstDayOfWeek = toInteger(node.getTextContent());
                }
                if ("CalendarLanguage".equals(node.getNodeName())) {
                    calendarLanguage = toBigInteger(node.getTextContent());
                }
                if ("FiscalFirstMonth".equals(node.getNodeName())) {
                    fiscalFirstMonth = toInteger(node.getTextContent());
                }
                if ("FiscalFirstDayOfMonth".equals(node.getNodeName())) {
                    fiscalFirstDayOfMonth = toInteger(node.getTextContent());
                }
                if ("FiscalYearName".equals(node.getNodeName())) {
                    fiscalYearName = FiscalYearNameEnum.fromValue(node.getTextContent());
                }
                if ("ReportingFirstMonth".equals(node.getNodeName())) {
                    reportingFirstMonth = toInteger(node.getTextContent());
                }
                if ("ReportingFirstWeekOfMonth".equals(node.getNodeName())) {
                    reportingFirstWeekOfMonth = node.getTextContent();
                }
                if ("ReportingWeekToMonthPattern".equals(node.getNodeName())) {
                    reportingWeekToMonthPattern = ReportingWeekToMonthPatternEnum.fromValue(node.getTextContent());
                }
                if ("ManufacturingFirstMonth".equals(node.getNodeName())) {
                    manufacturingFirstMonth = toInteger(node.getTextContent());
                }
                if ("ManufacturingFirstWeekOfMonth".equals(node.getNodeName())) {
                    manufacturingFirstWeekOfMonth = toInteger(node.getTextContent());
                }
                if ("ManufacturingExtraMonthQuarter".equals(node.getNodeName())) {
                    manufacturingExtraMonthQuarter = toInteger(node.getTextContent());
                }
            }
        }
        return new TimeBindingR(calendarStartDate, calendarEndDate, Optional.ofNullable(firstDayOfWeek),
                Optional.ofNullable(calendarLanguage), Optional.ofNullable(fiscalFirstMonth),
                Optional.ofNullable(fiscalFirstDayOfMonth), Optional.ofNullable(fiscalYearName),
                Optional.ofNullable(reportingFirstMonth), Optional.ofNullable(reportingFirstWeekOfMonth),
                Optional.ofNullable(reportingWeekToMonthPattern), Optional.ofNullable(manufacturingFirstMonth),
                Optional.ofNullable(manufacturingFirstWeekOfMonth),
                Optional.ofNullable(manufacturingExtraMonthQuarter));
    }

    public static Binding getMeasureGroupDimensionBinding(NodeList nl) {
        String cubeDimensionID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && (CUBE_DIMENSION_ID.equals(node.getNodeName()))) {
                cubeDimensionID = node.getTextContent();
            }
        }
        return new MeasureGroupDimensionBindingR(cubeDimensionID);
    }

    public static Binding getCubeDimensionBinding(NodeList nl) {
        String dataSourceID = null;
        String cubeID = null;
        String cubeDimensionID = null;
        String filter = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if (CUBE_ID.equals(node.getNodeName())) {
                    cubeID = node.getTextContent();
                }
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = node.getTextContent();
                }
            }
        }
        return new CubeDimensionBindingR(dataSourceID, cubeID, cubeDimensionID, Optional.ofNullable(filter));
    }

    public static Binding getDimensionBinding(NodeList nl) {
        String dataSourceID = null;
        String dimensionID = null;
        PersistenceEnum persistence = null;
        RefreshPolicyEnum refreshPolicy = null;
        Duration refreshInterval = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if (DIMENSION_ID.equals(node.getNodeName())) {
                    dimensionID = node.getTextContent();
                }
                if ("Persistence".equals(node.getNodeName())) {
                    persistence = PersistenceEnum.fromValue(node.getTextContent());
                }
                if ("RefreshPolicy".equals(node.getNodeName())) {
                    refreshPolicy = RefreshPolicyEnum.fromValue(node.getTextContent());
                }
                if (REFRESH_INTERVAL.equals(node.getNodeName())) {
                    refreshInterval = toDuration(node.getTextContent());
                }
            }
        }
        return new DimensionBindingR(dataSourceID, dimensionID, Optional.ofNullable(persistence),
                Optional.ofNullable(refreshPolicy), Optional.ofNullable(refreshInterval));
    }

    public static Binding getCubeAttributeBinding(NodeList nl) {
        String cubeID = null;
        String cubeDimensionID = null;
        String attributeID = null;
        AttributeBindingTypeEnum type = null;
        List<BigInteger> ordinal = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (CUBE_ID.equals(node.getNodeName())) {
                    cubeID = node.getTextContent();
                }
                if (CUBE_DIMENSION_ID.equals(node.getNodeName())) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = AttributeBindingTypeEnum.fromValue(node.getTextContent());
                }
                if (ORDINAL.equals(node.getNodeName())) {
                    ordinal = getOrdinalList(node.getChildNodes());
                }
            }
        }
        return new CubeAttributeBindingR(cubeID, cubeDimensionID, attributeID, type, Optional.ofNullable(ordinal));
    }

    public static List<BigInteger> getOrdinalList(NodeList nl) {
        List<BigInteger> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && (ORDINAL.equals(node.getNodeName()))) {
                list.add(toBigInteger(node.getTextContent()));
            }
        }
        return list;
    }

    public static Binding getMeasureBinding(NodeList nl) {
        String measureID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && (MEASURE_ID.equals(node.getNodeName()))) {
                measureID = node.getTextContent();
            }
        }
        return new MeasureBindingR(measureID);
    }

    public static Binding getUserDefinedGroupBinding(NodeList nl) {
        String attributeID = null;
        List<Group> groups = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if ("Groups".equals(node.getNodeName())) {
                    groups = getGroupList(node.getChildNodes());
                }
            }
        }
        return new UserDefinedGroupBindingR(attributeID, Optional.ofNullable(groups));
    }

    public static List<Group> getGroupList(NodeList nl) {
        return getList(nl, "Group", BindingConverter::getGroup);
    }

    public static Group getGroup(NodeList nl) {
        String name = null;
        List<String> members = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if ("Members".equals(node.getNodeName())) {
                    members = getMemberStringList(node.getChildNodes());
                }
            }
        }
        return new GroupR(name, Optional.ofNullable(members));
    }

    public static List<String> getMemberStringList(NodeList nl) {
        return getStringList(nl, "Member");
    }

    public static Binding getAttributeBinding(NodeList nl) {
        String attributeID = null;
        AttributeBindingTypeEnum type = null;
        Integer ordinal = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ATTRIBUTE_ID.equals(node.getNodeName())) {
                    attributeID = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = AttributeBindingTypeEnum.fromValue(node.getTextContent());
                }
                if (ORDINAL.equals(node.getNodeName())) {
                    ordinal = toInteger(node.getTextContent());
                }
            }
        }
        return new AttributeBindingR(attributeID, type, Optional.ofNullable(ordinal));
    }

    public static Binding getRowBinding(NodeList nl) {
        String tableID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && (TABLE_ID.equals(node.getNodeName()))) {
                tableID = node.getTextContent();
            }
        }
        return new RowBindingR(tableID);
    }

    public static Binding getDataSourceViewBinding(NodeList nl) {
        String dataSourceViewID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("DataSourceViewID".equals(node.getNodeName()))) {
                dataSourceViewID = node.getTextContent();
            }
        }
        return new DataSourceViewBindingR(dataSourceViewID);
    }

    // TabularBinding methods
    public static TabularBinding getTabularBinding(NodeList nl, String type) {
        if ("TableBinding".equals(type)) {
            return getTableBinding(nl);
        }
        if ("QueryBinding".equals(type)) {
            return getQueryBinding(nl);
        }
        if ("DSVTableBinding".equals(type)) {
            return getDSVTableBinding(nl);
        }
        return null;
    }

    public static TabularBinding getDSVTableBinding(NodeList nl) {
        String dataSourceViewID = null;
        String tableID = null;
        String dataEmbeddingStyle = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_VIEW_ID.equals(node.getNodeName())) {
                    dataSourceViewID = node.getTextContent();
                }
                if (TABLE_ID.equals(node.getNodeName())) {
                    tableID = node.getTextContent();
                }
                if ("DataEmbeddingStyle".equals(node.getNodeName())) {
                    dataEmbeddingStyle = node.getTextContent();
                }
            }
        }
        return new DSVTableBindingR(Optional.ofNullable(dataSourceViewID), tableID,
                Optional.ofNullable(dataEmbeddingStyle));
    }

    public static TabularBinding getQueryBinding(NodeList nl) {
        String dataSourceID = null;
        String queryDefinition = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if ("QueryDefinition".equals(node.getNodeName())) {
                    queryDefinition = node.getTextContent();
                }
            }
        }
        return new QueryBindingR(Optional.ofNullable(dataSourceID), queryDefinition);
    }

    public static TabularBinding getTableBinding(NodeList nl) {
        String dataSourceID = null;
        String dbTableName = null;
        String dbSchemaName = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
                if ("DbTableName".equals(node.getNodeName())) {
                    dbTableName = node.getTextContent();
                }
                if ("DbSchemaName".equals(node.getNodeName())) {
                    dbSchemaName = node.getTextContent();
                }
            }
        }
        return new TableBindingR(Optional.ofNullable(dataSourceID), dbTableName, Optional.ofNullable(dbSchemaName));
    }

    // ProactiveCachingBinding methods
    public static ProactiveCachingBinding getProactiveCachingBinding(NodeList nl, String type) {
        if ("ProactiveCachingQueryBinding".equals(type)) {
            return getProactiveCachingQueryBinding();
        }
        if ("ProactiveCachingIncrementalProcessingBinding".equals(type)) {
            return getProactiveCachingIncrementalProcessingBinding(nl);
        }
        return null;
    }

    public static ProactiveCachingBinding getProactiveCachingIncrementalProcessingBinding(NodeList nl) {
        Duration refreshInterval = null;
        List<IncrementalProcessingNotification> incrementalProcessingNotifications = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (REFRESH_INTERVAL.equals(node.getNodeName())) {
                    refreshInterval = toDuration(node.getTextContent());
                }
                if ("IncrementalProcessingNotifications".equals(node.getNodeName())) {
                    incrementalProcessingNotifications = getIncrementalProcessingNotificationList(node.getChildNodes());
                }
            }
        }
        return new ProactiveCachingIncrementalProcessingBindingR(Optional.ofNullable(refreshInterval),
                incrementalProcessingNotifications);
    }

    public static List<IncrementalProcessingNotification> getIncrementalProcessingNotificationList(NodeList nl) {
        return getList(nl, "IncrementalProcessingNotification", BindingConverter::getIncrementalProcessingNotification);
    }

    public static IncrementalProcessingNotification getIncrementalProcessingNotification(NodeList nl) {
        String tableID = null;
        String processingQuery = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (TABLE_ID.equals(node.getNodeName())) {
                    tableID = node.getTextContent();
                }
                if ("ProcessingQuery".equals(node.getNodeName())) {
                    processingQuery = node.getTextContent();
                }
            }
        }
        return new IncrementalProcessingNotificationR(tableID, processingQuery);
    }

    public static ProactiveCachingBinding getProactiveCachingQueryBinding() {
        return new ProactiveCachingBindingR();
    }

    public static Binding getColumnBinding(NodeList nl) {
        String tableID = null;
        String columnID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (TABLE_ID.equals(node.getNodeName())) {
                    tableID = node.getTextContent();
                }
                if ("ColumnID".equals(node.getNodeName())) {
                    columnID = node.getTextContent();
                }
            }
        }
        return new ColumnBindingR(tableID, columnID);
    }
}
