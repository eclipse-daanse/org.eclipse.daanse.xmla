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

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.AGGREGATION_PREFIX;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ANNOTATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_HIERARCHY_VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COLLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COMMAND;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE2;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_SOURCE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_SOURCE_VIEW_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DISPLAY_FOLDER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ERROR_CONFIGURATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ESTIMATED_ROWS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ESTIMATED_SIZE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.EXPRESSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.HIERARCHIES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.HIERARCHY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.KEY_COLUMN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.KEY_COLUMNS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LANGUAGE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_PROCESSED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_GROUP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_GROUP_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROACTIVE_CACHING;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESSING_MODE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESSING_PRIORITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.READ_DEFINITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SOURCE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STORAGE_LOCATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STORAGE_MODE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VISUALIZATION_PROPERTIES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.WRITE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getAttribute;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getListByLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getNodeType;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBigInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toDuration;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toLong;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.daanse.xmla.api.engine300.CalculationPropertiesVisualizationProperties;
import org.eclipse.daanse.xmla.api.xmla.AccessEnum;
import org.eclipse.daanse.xmla.api.xmla.Action;
import org.eclipse.daanse.xmla.api.xmla.AggregationDesign;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.AttributePermission;
import org.eclipse.daanse.xmla.api.xmla.CalculationProperty;
import org.eclipse.daanse.xmla.api.xmla.CellPermission;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.Cube;
import org.eclipse.daanse.xmla.api.xmla.CubeAttribute;
import org.eclipse.daanse.xmla.api.xmla.CubeDimension;
import org.eclipse.daanse.xmla.api.xmla.CubeDimensionPermission;
import org.eclipse.daanse.xmla.api.xmla.CubeHierarchy;
import org.eclipse.daanse.xmla.api.xmla.CubePermission;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.DataSourceViewBinding;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Kpi;
import org.eclipse.daanse.xmla.api.xmla.MdxScript;
import org.eclipse.daanse.xmla.api.xmla.Measure;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroup;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupAttribute;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupBinding;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimensionBinding;
import org.eclipse.daanse.xmla.api.xmla.Partition;
import org.eclipse.daanse.xmla.api.xmla.PersistenceEnum;
import org.eclipse.daanse.xmla.api.xmla.Perspective;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.api.xmla.RefreshPolicyEnum;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.model.record.engine300.CalculationPropertiesVisualizationPropertiesR;
import org.eclipse.daanse.xmla.model.record.xmla.CalculationPropertyR;
import org.eclipse.daanse.xmla.model.record.xmla.CellPermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.CubeAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.CubeDimensionPermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.CubeDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.CubeHierarchyR;
import org.eclipse.daanse.xmla.model.record.xmla.CubePermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.CubeR;
import org.eclipse.daanse.xmla.model.record.xmla.DataMiningMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourceViewBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DegenerateMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.KpiR;
import org.eclipse.daanse.xmla.model.record.xmla.ManyToManyMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.MdxScriptR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureGroupAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureGroupBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureGroupR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureR;
import org.eclipse.daanse.xmla.model.record.xmla.ReferenceMeasureGroupDimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.RegularMeasureGroupDimensionR;
import org.w3c.dom.NodeList;

/**
 * Converter for Cube-related XML parsing. Handles Cube, MeasureGroup, Measure,
 * Kpi, CubeDimension, and related types.
 */
public class CubeConverter {

    private CubeConverter() {
        // Utility class
    }

    public static List<Cube> getCubeList(NodeList nl, Function<NodeList, Command> commandParser) {
        return getListByLocalName(nl, CUBE2, childNodes -> getCube(childNodes, commandParser));
    }

    public static Cube getCube(NodeList nl, Function<NodeList, Command> commandParser) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        BigInteger language = null;
        String collation = null;
        List<Translation> translations = null;
        List<CubeDimension> dimensions = null;
        List<CubePermission> cubePermissions = null;
        List<MdxScript> mdxScripts = null;
        List<Perspective> perspectives = null;
        String state = null;
        String defaultMeasure = null;
        Boolean visible = null;
        List<MeasureGroup> measureGroups = null;
        DataSourceViewBinding source = null;
        String aggregationPrefix = null;
        BigInteger processingPriority = null;
        Cube.StorageMode storageMode = null;
        String processingMode = null;
        String scriptCacheProcessingMode = null;
        String scriptErrorHandlingMode = null;
        String daxOptimizationMode = null;
        ProactiveCaching proactiveCaching = null;
        List<Kpi> kpis = null;
        ErrorConfiguration errorConfiguration = null;
        List<Action> actions = null;
        String storageLocation = null;
        Long estimatedRows = null;
        Instant lastProcessed = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, NAME)) {
                name = node.getTextContent();
            }
            if (matchesLocalName(node, ID)) {
                id = node.getTextContent();
            }
            if (matchesLocalName(node, CREATED_TIMESTAMP)) {
                createdTimestamp = toInstant(node.getTextContent());
            }
            if (matchesLocalName(node, LAST_SCHEMA_UPDATE)) {
                lastSchemaUpdate = toInstant(node.getTextContent());
            }
            if (matchesLocalName(node, DESCRIPTION)) {
                description = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
            if (matchesLocalName(node, LANGUAGE)) {
                language = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, COLLATION)) {
                collation = node.getTextContent();
            }
            if (matchesLocalName(node, TRANSLATIONS)) {
                translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
            }
            if (matchesLocalName(node, DIMENSIONS)) {
                dimensions = getCubeDimensionList(node.getChildNodes());
            }
            if (matchesLocalName(node, "CubePermissions")) {
                cubePermissions = getCubePermissionList(node.getChildNodes());
            }
            if (matchesLocalName(node, "MdxScripts")) {
                mdxScripts = getMdxScriptList(node.getChildNodes(), commandParser);
            }
            if (matchesLocalName(node, "Perspectives")) {
                perspectives = PerspectiveConverter.getPerspectiveList(node.getChildNodes());
            }
            if (matchesLocalName(node, STATE)) {
                state = node.getTextContent();
            }
            if (matchesLocalName(node, "DefaultMeasure")) {
                defaultMeasure = node.getTextContent();
            }
            if (matchesLocalName(node, VISIBLE)) {
                visible = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "MeasureGroups")) {
                measureGroups = getMeasureGroupList(node.getChildNodes());
            }
            if (matchesLocalName(node, SOURCE)) {
                source = getDataSourceViewBinding(node.getChildNodes());
            }
            if (matchesLocalName(node, AGGREGATION_PREFIX)) {
                aggregationPrefix = node.getTextContent();
            }
            if (matchesLocalName(node, PROCESSING_PRIORITY)) {
                processingPriority = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, STORAGE_MODE)) {
                storageMode = getCubeStorageMode(node.getChildNodes());
            }
            if (matchesLocalName(node, PROCESSING_MODE)) {
                processingMode = node.getTextContent();
            }
            if (matchesLocalName(node, "ScriptCacheProcessingMode")) {
                scriptCacheProcessingMode = node.getTextContent();
            }
            if (matchesLocalName(node, "ScriptErrorHandlingMode")) {
                scriptErrorHandlingMode = node.getTextContent();
            }
            if (matchesLocalName(node, "DaxOptimizationMode")) {
                daxOptimizationMode = node.getTextContent();
            }
            if (matchesLocalName(node, PROACTIVE_CACHING)) {
                proactiveCaching = CommonConverter.getProactiveCaching(node.getChildNodes());
            }
            if (matchesLocalName(node, "Kpis")) {
                kpis = getKpiList(node.getChildNodes());
            }
            if (matchesLocalName(node, ERROR_CONFIGURATION)) {
                errorConfiguration = CommonConverter.getErrorConfiguration(node.getChildNodes());
            }
            if (matchesLocalName(node, "Actions")) {
                actions = ActionConverter.getActionList(node.getChildNodes());
            }
            if (matchesLocalName(node, STORAGE_LOCATION)) {
                storageLocation = node.getTextContent();
            }
            if (matchesLocalName(node, ESTIMATED_ROWS)) {
                estimatedRows = toLong(node.getTextContent());
            }
            if (matchesLocalName(node, LAST_PROCESSED)) {
                lastProcessed = toInstant(node.getTextContent());
            }
        }
        return new CubeR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, language, collation,
                translations, dimensions, cubePermissions, mdxScripts, perspectives, state, defaultMeasure, visible,
                measureGroups, source, aggregationPrefix, processingPriority, storageMode, processingMode,
                scriptCacheProcessingMode, scriptErrorHandlingMode, daxOptimizationMode, proactiveCaching, kpis,
                errorConfiguration, actions, storageLocation, estimatedRows, lastProcessed);
    }

    public static Cube.StorageMode getCubeStorageMode(NodeList nl) {
        String valuens = null;
        String value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                valuens = getAttribute(node.getAttributes(), "valuens");
                value = node.getTextContent();
            }
        }
        return new CubeR.StorageMode(org.eclipse.daanse.xmla.api.xmla.CubeStorageModeEnumType.fromValue(value),
                valuens);
    }

    public static List<CubeDimension> getCubeDimensionList(NodeList nl) {
        return getListByLocalName(nl, "CubeDimension", childNodes -> getCubeDimension(childNodes));
    }

    public static CubeDimension getCubeDimension(NodeList nl) {
        String id = null;
        String name = null;
        String description = null;
        List<Translation> translations = null;
        String dimensionID = null;
        Boolean visible = null;
        String allMemberAggregationUsage = null;
        String hierarchyUniqueNameStyle = null;
        String memberUniqueNameStyle = null;
        List<CubeAttribute> attributes = null;
        List<CubeHierarchy> hierarchies = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ID)) {
                id = node.getTextContent();
            }
            if (matchesLocalName(node, NAME)) {
                name = node.getTextContent();
            }
            if (matchesLocalName(node, DESCRIPTION)) {
                description = node.getTextContent();
            }
            if (matchesLocalName(node, TRANSLATIONS)) {
                translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
            }
            if (matchesLocalName(node, DIMENSION_ID)) {
                dimensionID = node.getTextContent();
            }
            if (matchesLocalName(node, VISIBLE)) {
                visible = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "AllMemberAggregationUsage")) {
                allMemberAggregationUsage = node.getTextContent();
            }
            if (matchesLocalName(node, "HierarchyUniqueNameStyle")) {
                hierarchyUniqueNameStyle = node.getTextContent();
            }
            if (matchesLocalName(node, "MemberUniqueNameStyle")) {
                memberUniqueNameStyle = node.getTextContent();
            }
            if (matchesLocalName(node, ATTRIBUTES)) {
                attributes = getCubeAttributeList(node.getChildNodes());
            }
            if (matchesLocalName(node, HIERARCHIES)) {
                hierarchies = getCubeHierarchyList(node.getChildNodes());
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
        }
        return new CubeDimensionR(id, name, description, translations, dimensionID, visible, allMemberAggregationUsage,
                hierarchyUniqueNameStyle, memberUniqueNameStyle, attributes, hierarchies, annotations);
    }

    public static List<CubeAttribute> getCubeAttributeList(NodeList nl) {
        return getListByLocalName(nl, ATTRIBUTE, childNodes -> getCubeAttribute(childNodes));
    }

    public static CubeAttribute getCubeAttribute(NodeList nl) {
        String attributeID = null;
        String aggregationUsage = null;
        String attributeHierarchyOptimizedState = null;
        Boolean attributeHierarchyEnabled = null;
        Boolean attributeHierarchyVisible = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ATTRIBUTE_ID)) {
                attributeID = node.getTextContent();
            }
            if (matchesLocalName(node, "AggregationUsage")) {
                aggregationUsage = node.getTextContent();
            }
            if (matchesLocalName(node, "AttributeHierarchyOptimizedState")) {
                attributeHierarchyOptimizedState = node.getTextContent();
            }
            if (matchesLocalName(node, "AttributeHierarchyEnabled")) {
                attributeHierarchyEnabled = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, ATTRIBUTE_HIERARCHY_VISIBLE)) {
                attributeHierarchyVisible = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
        }
        return new CubeAttributeR(attributeID, aggregationUsage, attributeHierarchyOptimizedState,
                attributeHierarchyEnabled, attributeHierarchyVisible, annotations);
    }

    public static List<CubeHierarchy> getCubeHierarchyList(NodeList nl) {
        return getListByLocalName(nl, HIERARCHY, childNodes -> getCubeHierarchy(childNodes));
    }

    public static CubeHierarchy getCubeHierarchy(NodeList nl) {
        String hierarchyID = null;
        String optimizedState = null;
        Boolean visible = null;
        Boolean enabled = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "HierarchyID")) {
                hierarchyID = node.getTextContent();
            }
            if (matchesLocalName(node, "OptimizedState")) {
                optimizedState = node.getTextContent();
            }
            if (matchesLocalName(node, VISIBLE)) {
                visible = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "Enabled")) {
                enabled = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
        }
        return new CubeHierarchyR(hierarchyID, optimizedState, visible, enabled, annotations);
    }

    public static List<CubePermission> getCubePermissionList(NodeList nl) {
        return getListByLocalName(nl, "CubePermission", childNodes -> getCubePermission(childNodes));
    }

    public static CubePermission getCubePermission(NodeList nl) {
        String readSourceData = null;
        List<CubeDimensionPermission> dimensionPermissions = null;
        List<CellPermission> cellPermissions = null;
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, NAME)) {
                name = node.getTextContent();
            }
            if (matchesLocalName(node, ID)) {
                id = node.getTextContent();
            }
            if (matchesLocalName(node, CREATED_TIMESTAMP)) {
                createdTimestamp = toInstant(node.getTextContent());
            }
            if (matchesLocalName(node, LAST_SCHEMA_UPDATE)) {
                lastSchemaUpdate = toInstant(node.getTextContent());
            }
            if (matchesLocalName(node, DESCRIPTION)) {
                description = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
            if (matchesLocalName(node, ROLE_ID)) {
                roleID = node.getTextContent();
            }
            if (matchesLocalName(node, PROCESS)) {
                process = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, READ_DEFINITION)) {
                readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
            }
            if (matchesLocalName(node, "Read")) {
                read = ReadWritePermissionEnum.fromValue(node.getTextContent());
            }
            if (matchesLocalName(node, WRITE)) {
                write = ReadWritePermissionEnum.fromValue(node.getTextContent());
            }
            if (matchesLocalName(node, "ReadSourceData")) {
                readSourceData = node.getTextContent();
            }
            if (matchesLocalName(node, "DimensionPermissions")) {
                dimensionPermissions = getCubeDimensionPermissionList(node.getChildNodes());
            }
            if (matchesLocalName(node, "CellPermissions")) {
                cellPermissions = getCellPermissionList(node.getChildNodes());
            }
        }
        return new CubePermissionR(Optional.ofNullable(readSourceData), Optional.ofNullable(dimensionPermissions),
                Optional.ofNullable(cellPermissions), name, Optional.ofNullable(id),
                Optional.ofNullable(createdTimestamp), Optional.ofNullable(lastSchemaUpdate),
                Optional.ofNullable(description), Optional.ofNullable(annotations), roleID,
                Optional.ofNullable(process), Optional.ofNullable(readDefinition), Optional.ofNullable(read),
                Optional.ofNullable(write));
    }

    public static List<CellPermission> getCellPermissionList(NodeList nl) {
        return getListByLocalName(nl, "CellPermission", childNodes -> getCellPermission(childNodes));
    }

    public static CellPermission getCellPermission(NodeList nl) {
        AccessEnum access = null;
        String description = null;
        String expression = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "Access")) {
                access = AccessEnum.fromValue(node.getTextContent());
            }
            if (matchesLocalName(node, DESCRIPTION)) {
                description = node.getTextContent();
            }
            if (matchesLocalName(node, EXPRESSION)) {
                expression = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
        }
        return new CellPermissionR(Optional.ofNullable(access), Optional.ofNullable(description),
                Optional.ofNullable(expression), Optional.ofNullable(annotations));
    }

    public static List<CubeDimensionPermission> getCubeDimensionPermissionList(NodeList nl) {
        return getListByLocalName(nl, "DimensionPermission", childNodes -> getCubeDimensionPermission(childNodes));
    }

    public static CubeDimensionPermission getCubeDimensionPermission(NodeList nl) {
        String cubeDimensionID = null;
        String description = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;
        List<AttributePermission> attributePermissions = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                cubeDimensionID = node.getTextContent();
            }
            if (matchesLocalName(node, DESCRIPTION)) {
                description = node.getTextContent();
            }
            if (matchesLocalName(node, "Read")) {
                read = ReadWritePermissionEnum.fromValue(node.getTextContent());
            }
            if (matchesLocalName(node, WRITE)) {
                write = ReadWritePermissionEnum.fromValue(node.getTextContent());
            }
            if (matchesLocalName(node, "AttributePermissions")) {
                attributePermissions = DimensionConverter.getAttributePermissionList(node.getChildNodes());
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
        }
        return new CubeDimensionPermissionR(cubeDimensionID, Optional.ofNullable(description),
                Optional.ofNullable(read), Optional.ofNullable(write), Optional.ofNullable(attributePermissions),
                Optional.ofNullable(annotations));
    }

    public static List<Kpi> getKpiList(NodeList nl) {
        return getListByLocalName(nl, "Kpi", childNodes -> getKpi(childNodes));
    }

    public static Kpi getKpi(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        List<Translation> translations = null;
        String displayFolder = null;
        String associatedMeasureGroupID = null;
        String value = null;
        String goal = null;
        String status = null;
        String trend = null;
        String weight = null;
        String trendGraphic = null;
        String statusGraphic = null;
        String currentTimeMember = null;
        String parentKpiID = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, NAME)) {
                name = node.getTextContent();
            }
            if (matchesLocalName(node, ID)) {
                id = node.getTextContent();
            }
            if (matchesLocalName(node, DESCRIPTION)) {
                description = node.getTextContent();
            }
            if (matchesLocalName(node, TRANSLATIONS)) {
                translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
            }
            if (matchesLocalName(node, DISPLAY_FOLDER)) {
                displayFolder = node.getTextContent();
            }
            if (matchesLocalName(node, "AssociatedMeasureGroupID")) {
                associatedMeasureGroupID = node.getTextContent();
            }
            if (matchesLocalName(node, VALUE)) {
                value = node.getTextContent();
            }
            if (matchesLocalName(node, "Goal")) {
                goal = node.getTextContent();
            }
            if (matchesLocalName(node, "Status")) {
                status = node.getTextContent();
            }
            if (matchesLocalName(node, "Trend")) {
                trend = node.getTextContent();
            }
            if (matchesLocalName(node, "Weight")) {
                weight = node.getTextContent();
            }
            if (matchesLocalName(node, "TrendGraphic")) {
                trendGraphic = node.getTextContent();
            }
            if (matchesLocalName(node, "StatusGraphic")) {
                statusGraphic = node.getTextContent();
            }
            if (matchesLocalName(node, "CurrentTimeMember")) {
                currentTimeMember = node.getTextContent();
            }
            if (matchesLocalName(node, "ParentKpiID")) {
                parentKpiID = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
        }
        return new KpiR(name, id, description, translations, displayFolder, associatedMeasureGroupID, value, goal,
                status, trend, weight, trendGraphic, statusGraphic, currentTimeMember, parentKpiID, annotations);
    }

    public static List<MeasureGroup> getMeasureGroupList(NodeList nl) {
        return getListByLocalName(nl, MEASURE_GROUP, childNodes -> getMeasureGroup(childNodes));
    }

    public static MeasureGroup getMeasureGroup(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        Instant lastProcessed = null;
        List<Translation> translations = null;
        String type = null;
        String state = null;
        List<Measure> measures = null;
        String dataAggregation = null;
        MeasureGroupBinding source = null;
        MeasureGroup.StorageMode storageMode = null;
        String storageLocation = null;
        Boolean ignoreUnrelatedDimensions = null;
        ProactiveCaching proactiveCaching = null;
        Long estimatedRows = null;
        ErrorConfiguration errorConfiguration = null;
        Long estimatedSize = null;
        String processingMode = null;
        List<org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension> dimensions = null;
        List<Partition> partitions = null;
        String aggregationPrefix = null;
        BigInteger processingPriority = null;
        List<AggregationDesign> aggregationDesigns = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, LAST_PROCESSED)) {
                lastProcessed = toInstant(node.getTextContent());
            }
            if (matchesLocalName(node, TRANSLATIONS)) {
                translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
            }
            if (matchesLocalName(node, "Type")) {
                type = node.getTextContent();
            }
            if (matchesLocalName(node, STATE)) {
                state = node.getTextContent();
            }
            if (matchesLocalName(node, MEASURES)) {
                measures = getMeasureList(node.getChildNodes());
            }
            if (matchesLocalName(node, "DataAggregation")) {
                dataAggregation = node.getTextContent();
            }
            if (matchesLocalName(node, SOURCE)) {
                source = getMeasureGroupBinding(node.getChildNodes());
            }
            if (matchesLocalName(node, STORAGE_MODE)) {
                storageMode = getMeasureGroupStorageMode(node.getChildNodes());
            }
            if (matchesLocalName(node, STORAGE_LOCATION)) {
                storageLocation = node.getTextContent();
            }
            if (matchesLocalName(node, "IgnoreUnrelatedDimensions")) {
                ignoreUnrelatedDimensions = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, PROACTIVE_CACHING)) {
                proactiveCaching = CommonConverter.getProactiveCaching(node.getChildNodes());
            }
            if (matchesLocalName(node, ESTIMATED_ROWS)) {
                estimatedRows = toLong(node.getTextContent());
            }
            if (matchesLocalName(node, ERROR_CONFIGURATION)) {
                errorConfiguration = CommonConverter.getErrorConfiguration(node.getChildNodes());
            }
            if (matchesLocalName(node, ESTIMATED_SIZE)) {
                estimatedSize = toLong(node.getTextContent());
            }
            if (matchesLocalName(node, PROCESSING_MODE)) {
                processingMode = node.getTextContent();
            }
            if (matchesLocalName(node, DIMENSIONS)) {
                dimensions = getMeasureGroupDimensionList(node.getChildNodes());
            }
            if (matchesLocalName(node, "Partitions")) {
                partitions = PartitionConverter.getPartitionList(node.getChildNodes());
            }
            if (matchesLocalName(node, AGGREGATION_PREFIX)) {
                aggregationPrefix = node.getTextContent();
            }
            if (matchesLocalName(node, PROCESSING_PRIORITY)) {
                processingPriority = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, "AggregationDesigns")) {
                aggregationDesigns = getAggregationDesignList(node.getChildNodes());
            }
        }
        return new MeasureGroupR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, lastProcessed,
                translations, type, state, measures, dataAggregation, source, storageMode, storageLocation,
                ignoreUnrelatedDimensions, proactiveCaching, estimatedRows, errorConfiguration, estimatedSize,
                processingMode, dimensions, partitions, aggregationPrefix, processingPriority, aggregationDesigns);
    }

    public static MeasureGroup.StorageMode getMeasureGroupStorageMode(NodeList nl) {
        String valuens = null;
        String value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                valuens = getAttribute(node.getAttributes(), "valuens");
                value = node.getTextContent();
            }
        }
        return new MeasureGroupR.StorageMode(
                org.eclipse.daanse.xmla.api.xmla.MeasureGroupStorageModeEnumType.fromValue(value), valuens);
    }

    public static MeasureGroupBinding getMeasureGroupBinding(NodeList nl) {
        String dataSourceID = null;
        String cubeID = null;
        String measureGroupID = null;
        PersistenceEnum persistence = null;
        RefreshPolicyEnum refreshPolicy = null;
        Duration refreshInterval = null;
        String filter = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, DATA_SOURCE_ID)) {
                dataSourceID = node.getTextContent();
            }
            if (matchesLocalName(node, CUBE_ID)) {
                cubeID = node.getTextContent();
            }
            if (matchesLocalName(node, MEASURE_GROUP_ID)) {
                measureGroupID = node.getTextContent();
            }
            if (matchesLocalName(node, "Persistence")) {
                persistence = PersistenceEnum.fromValue(node.getTextContent());
            }
            if (matchesLocalName(node, "RefreshPolicy")) {
                refreshPolicy = RefreshPolicyEnum.fromValue(node.getTextContent());
            }
            if (matchesLocalName(node, "RefreshInterval")) {
                refreshInterval = toDuration(node.getTextContent());
            }
            if (matchesLocalName(node, "Filter")) {
                filter = node.getTextContent();
            }
        }
        return new MeasureGroupBindingR(dataSourceID, cubeID, measureGroupID, Optional.ofNullable(persistence),
                Optional.ofNullable(refreshPolicy), Optional.ofNullable(refreshInterval), Optional.ofNullable(filter));
    }

    public static List<org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension> getMeasureGroupDimensionList(
            NodeList nl) {
        List<org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, DIMENSIONS)) {
                list.add(getMeasureGroupDimension(node.getChildNodes(), getNodeType(node)));
            }
        }
        return list;
    }

    public static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getMeasureGroupDimension(NodeList nl,
            String type) {
        if ("ManyToManyMeasureGroupDimension".equals(type)) {
            return getManyToManyMeasureGroupDimension(nl);
        }
        if ("RegularMeasureGroupDimension".equals(type)) {
            return getRegularMeasureGroupDimension(nl);
        }
        if ("ReferenceMeasureGroupDimension".equals(type)) {
            return getReferenceMeasureGroupDimension(nl);
        }
        if ("DegenerateMeasureGroupDimension".equals(type)) {
            return getDegenerateMeasureGroupDimension(nl);
        }
        if ("DataMiningMeasureGroupDimension".equals(type)) {
            return getDataMiningMeasureGroupDimension(nl);
        }
        return null;
    }

    public static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getDataMiningMeasureGroupDimension(
            NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String caseCubeDimensionID = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                cubeDimensionID = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
            if (matchesLocalName(node, SOURCE)) {
                source = (MeasureGroupDimensionBinding) BindingConverter
                        .getMeasureGroupDimensionBinding(node.getChildNodes());
            }
            if (matchesLocalName(node, "CaseCubeDimensionID")) {
                caseCubeDimensionID = node.getTextContent();
            }
        }
        return new DataMiningMeasureGroupDimensionR(cubeDimensionID, annotations, source, caseCubeDimensionID);
    }

    public static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getDegenerateMeasureGroupDimension(
            NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String shareDimensionStorage = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                cubeDimensionID = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
            if (matchesLocalName(node, SOURCE)) {
                source = (MeasureGroupDimensionBinding) BindingConverter
                        .getMeasureGroupDimensionBinding(node.getChildNodes());
            }
            if (matchesLocalName(node, "ShareDimensionStorage")) {
                shareDimensionStorage = node.getTextContent();
            }
        }
        return new DegenerateMeasureGroupDimensionR(cubeDimensionID, annotations, source, shareDimensionStorage);
    }

    public static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getReferenceMeasureGroupDimension(
            NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String intermediateCubeDimensionID = null;
        String intermediateGranularityAttributeID = null;
        String materialization = null;
        String processingState = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                cubeDimensionID = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
            if (matchesLocalName(node, SOURCE)) {
                source = (MeasureGroupDimensionBinding) BindingConverter
                        .getMeasureGroupDimensionBinding(node.getChildNodes());
            }
            if (matchesLocalName(node, "IntermediateCubeDimensionID")) {
                intermediateCubeDimensionID = node.getTextContent();
            }
            if (matchesLocalName(node, "IntermediateGranularityAttributeID")) {
                intermediateGranularityAttributeID = node.getTextContent();
            }
            if (matchesLocalName(node, "Materialization")) {
                materialization = node.getTextContent();
            }
            if (matchesLocalName(node, "ProcessingState")) {
                processingState = node.getTextContent();
            }
        }
        return new ReferenceMeasureGroupDimensionR(cubeDimensionID, annotations, source, intermediateCubeDimensionID,
                intermediateGranularityAttributeID, materialization, processingState);
    }

    public static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getRegularMeasureGroupDimension(NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String cardinality = null;
        List<MeasureGroupAttribute> attributes = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                cubeDimensionID = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
            if (matchesLocalName(node, SOURCE)) {
                source = (MeasureGroupDimensionBinding) BindingConverter
                        .getMeasureGroupDimensionBinding(node.getChildNodes());
            }
            if (matchesLocalName(node, "Cardinality")) {
                cardinality = node.getTextContent();
            }
            if (matchesLocalName(node, ATTRIBUTES)) {
                attributes = getMeasureGroupAttributeList(node.getChildNodes());
            }
        }
        return new RegularMeasureGroupDimensionR(cubeDimensionID, annotations, source, cardinality, attributes);
    }

    public static List<MeasureGroupAttribute> getMeasureGroupAttributeList(NodeList nl) {
        return getListByLocalName(nl, ATTRIBUTE, childNodes -> getMeasureGroupAttribute(childNodes));
    }

    public static MeasureGroupAttribute getMeasureGroupAttribute(NodeList nl) {
        String attributeID = null;
        List<DataItem> keyColumns = null;
        String type = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ATTRIBUTE_ID)) {
                attributeID = node.getTextContent();
            }
            if (matchesLocalName(node, KEY_COLUMNS)) {
                keyColumns = getKeyColumnList(node.getChildNodes());
            }
            if (matchesLocalName(node, "Type")) {
                type = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
        }
        return new MeasureGroupAttributeR(attributeID, keyColumns, type, annotations);
    }

    private static List<DataItem> getKeyColumnList(NodeList nl) {
        List<DataItem> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, KEY_COLUMN)) {
                list.add(CommonConverter.getDataItem(node.getChildNodes()));
            }
        }
        return list;
    }

    public static org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension getManyToManyMeasureGroupDimension(
            NodeList nl) {
        String cubeDimensionID = null;
        List<Annotation> annotations = null;
        MeasureGroupDimensionBinding source = null;
        String measureGroupID = null;
        String directSlice = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, CUBE_DIMENSION_ID)) {
                cubeDimensionID = node.getTextContent();
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
            if (matchesLocalName(node, SOURCE)) {
                source = (MeasureGroupDimensionBinding) BindingConverter
                        .getMeasureGroupDimensionBinding(node.getChildNodes());
            }
            if (matchesLocalName(node, MEASURE_GROUP_ID)) {
                measureGroupID = node.getTextContent();
            }
            if (matchesLocalName(node, "DirectSlice")) {
                directSlice = node.getTextContent();
            }
        }
        return new ManyToManyMeasureGroupDimensionR(cubeDimensionID, annotations, source, measureGroupID, directSlice);
    }

    public static List<Measure> getMeasureList(NodeList nl) {
        return getListByLocalName(nl, MEASURE, childNodes -> getMeasure(childNodes));
    }

    public static Measure getMeasure(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String aggregateFunction = null;
        String dataType = null;
        DataItem source = null;
        Boolean visible = null;
        String measureExpression = null;
        String displayFolder = null;
        String formatString = null;
        String backColor = null;
        String foreColor = null;
        String fontName = null;
        String fontSize = null;
        String fontFlags = null;
        List<Translation> translations = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, NAME)) {
                name = node.getTextContent();
            }
            if (matchesLocalName(node, ID)) {
                id = node.getTextContent();
            }
            if (matchesLocalName(node, DESCRIPTION)) {
                description = node.getTextContent();
            }
            if (matchesLocalName(node, "AggregateFunction")) {
                aggregateFunction = node.getTextContent();
            }
            if (matchesLocalName(node, "DataType")) {
                dataType = node.getTextContent();
            }
            if (matchesLocalName(node, SOURCE)) {
                source = CommonConverter.getDataItem(node.getChildNodes());
            }
            if (matchesLocalName(node, VISIBLE)) {
                visible = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "MeasureExpression")) {
                measureExpression = node.getTextContent();
            }
            if (matchesLocalName(node, DISPLAY_FOLDER)) {
                displayFolder = node.getTextContent();
            }
            if (matchesLocalName(node, "FormatString")) {
                formatString = node.getTextContent();
            }
            if (matchesLocalName(node, "BackColor")) {
                backColor = node.getTextContent();
            }
            if (matchesLocalName(node, "ForeColor")) {
                foreColor = node.getTextContent();
            }
            if (matchesLocalName(node, "FontName")) {
                fontName = node.getTextContent();
            }
            if (matchesLocalName(node, "FontSize")) {
                fontSize = node.getTextContent();
            }
            if (matchesLocalName(node, "FontFlags")) {
                fontFlags = node.getTextContent();
            }
            if (matchesLocalName(node, TRANSLATIONS)) {
                translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
            }
            if (matchesLocalName(node, ANNOTATIONS)) {
                annotations = CommonConverter.getAnnotationList(node.getChildNodes());
            }
        }
        return new MeasureR(name, id, description, aggregateFunction, dataType, source, visible, measureExpression,
                displayFolder, formatString, backColor, foreColor, fontName, fontSize, fontFlags, translations,
                annotations);
    }

    public static List<MdxScript> getMdxScriptList(NodeList nl, Function<NodeList, Command> commandParser) {
        return getListByLocalName(nl, "MdxScript", childNodes -> getMdxScript(childNodes, commandParser));
    }

    public static MdxScript getMdxScript(NodeList nl, Function<NodeList, Command> commandParser) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        List<Command> commands = null;
        Boolean defaultScript = null;
        List<CalculationProperty> calculationProperties = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "Commands")) {
                commands = getCommandList(node.getChildNodes(), commandParser);
            }
            if (matchesLocalName(node, "DefaultScript")) {
                defaultScript = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "CalculationProperties")) {
                calculationProperties = getCalculationPropertyList(node.getChildNodes());
            }
        }
        return new MdxScriptR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, commands,
                defaultScript, calculationProperties);
    }

    public static List<Command> getCommandList(NodeList nl, Function<NodeList, Command> commandParser) {
        return getListByLocalName(nl, COMMAND, commandParser);
    }

    public static List<CalculationProperty> getCalculationPropertyList(NodeList nl) {
        return getListByLocalName(nl, "CalculationProperty", childNodes -> getCalculationProperty(childNodes));
    }

    public static CalculationProperty getCalculationProperty(NodeList nl) {
        String calculationReference = null;
        String calculationType = null;
        List<Translation> translations = null;
        String description = null;
        Boolean visible = null;
        BigInteger solveOrder = null;
        String formatString = null;
        String foreColor = null;
        String backColor = null;
        String fontName = null;
        String fontSize = null;
        String fontFlags = null;
        String nonEmptyBehavior = null;
        String associatedMeasureGroupID = null;
        String displayFolder = null;
        BigInteger language = null;
        CalculationPropertiesVisualizationProperties visualizationProperties = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "CalculationReference")) {
                calculationReference = node.getTextContent();
            }
            if (matchesLocalName(node, "CalculationType")) {
                calculationType = node.getTextContent();
            }
            if (matchesLocalName(node, TRANSLATIONS)) {
                translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
            }
            if (matchesLocalName(node, DESCRIPTION)) {
                description = node.getTextContent();
            }
            if (matchesLocalName(node, VISIBLE)) {
                visible = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "SolveOrder")) {
                solveOrder = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, "FormatString")) {
                formatString = node.getTextContent();
            }
            if (matchesLocalName(node, "ForeColor")) {
                foreColor = node.getTextContent();
            }
            if (matchesLocalName(node, "BackColor")) {
                backColor = node.getTextContent();
            }
            if (matchesLocalName(node, "FontName")) {
                fontName = node.getTextContent();
            }
            if (matchesLocalName(node, "FontSize")) {
                fontSize = node.getTextContent();
            }
            if (matchesLocalName(node, "FontFlags")) {
                fontFlags = node.getTextContent();
            }
            if (matchesLocalName(node, "NonEmptyBehavior")) {
                nonEmptyBehavior = node.getTextContent();
            }
            if (matchesLocalName(node, "AssociatedMeasureGroupID")) {
                associatedMeasureGroupID = node.getTextContent();
            }
            if (matchesLocalName(node, DISPLAY_FOLDER)) {
                displayFolder = node.getTextContent();
            }
            if (matchesLocalName(node, LANGUAGE)) {
                language = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, VISUALIZATION_PROPERTIES)) {
                visualizationProperties = getCalculationPropertiesVisualizationProperties(node.getChildNodes());
            }
        }
        return new CalculationPropertyR(calculationReference, calculationType, translations, description, visible,
                solveOrder, formatString, foreColor, backColor, fontName, fontSize, fontFlags, nonEmptyBehavior,
                associatedMeasureGroupID, displayFolder, language, visualizationProperties);
    }

    public static CalculationPropertiesVisualizationProperties getCalculationPropertiesVisualizationProperties(
            NodeList nl) {
        BigInteger folderPosition = null;
        String contextualNameRule = null;
        String alignment = null;
        Boolean isFolderDefault = null;
        Boolean isRightToLeft = null;
        String sortDirection = null;
        String units = null;
        BigInteger width = null;
        Boolean isDefaultMeasure = null;
        BigInteger defaultDetailsPosition = null;
        BigInteger sortPropertiesPosition = null;
        Boolean isSimpleMeasure = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "FolderPosition")) {
                folderPosition = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, "ContextualNameRule")) {
                contextualNameRule = node.getTextContent();
            }
            if (matchesLocalName(node, "Alignment")) {
                alignment = node.getTextContent();
            }
            if (matchesLocalName(node, "IsFolderDefault")) {
                isFolderDefault = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "IsRightToLeft")) {
                isRightToLeft = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "SortDirection")) {
                sortDirection = node.getTextContent();
            }
            if (matchesLocalName(node, "Units")) {
                units = node.getTextContent();
            }
            if (matchesLocalName(node, "Width")) {
                width = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, "IsDefaultMeasure")) {
                isDefaultMeasure = toBoolean(node.getTextContent());
            }
            if (matchesLocalName(node, "DefaultDetailsPosition")) {
                defaultDetailsPosition = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, "SortPropertiesPosition")) {
                sortPropertiesPosition = toBigInteger(node.getTextContent());
            }
            if (matchesLocalName(node, "IsSimpleMeasure")) {
                isSimpleMeasure = toBoolean(node.getTextContent());
            }
        }
        return new CalculationPropertiesVisualizationPropertiesR(folderPosition, contextualNameRule, alignment,
                isFolderDefault, isRightToLeft, sortDirection, units, width, isDefaultMeasure, defaultDetailsPosition,
                sortPropertiesPosition, isSimpleMeasure);
    }

    public static DataSourceViewBinding getDataSourceViewBinding(NodeList nl) {
        String dataSourceViewID = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, DATA_SOURCE_VIEW_ID)) {
                dataSourceViewID = node.getTextContent();
            }
        }
        return new DataSourceViewBindingR(dataSourceViewID);
    }

    static List<AggregationDesign> getAggregationDesignList(NodeList nl) {
        List<AggregationDesign> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "AggregationDesigns")) {
                list.add(AggregationConverter.getAggregationDesign(node.getChildNodes(),
                        CommonConverter::getAnnotationList));
            }
        }
        return list;
    }
}
