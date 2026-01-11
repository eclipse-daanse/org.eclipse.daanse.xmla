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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getList;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getNodeType;
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
        return getList(nl, CUBE2, childNodes -> getCube(childNodes, commandParser));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (NAME.equals(nodeName)) {
                    name = node.getTextContent();
                }
                if (ID.equals(nodeName)) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(nodeName)) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(nodeName)) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(nodeName)) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (LANGUAGE.equals(nodeName)) {
                    language = toBigInteger(node.getTextContent());
                }
                if (COLLATION.equals(nodeName)) {
                    collation = node.getTextContent();
                }
                if (TRANSLATIONS.equals(nodeName)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (DIMENSIONS.equals(nodeName)) {
                    dimensions = getCubeDimensionList(node.getChildNodes());
                }
                if ("CubePermissions".equals(nodeName)) {
                    cubePermissions = getCubePermissionList(node.getChildNodes());
                }
                if ("MdxScripts".equals(nodeName)) {
                    mdxScripts = getMdxScriptList(node.getChildNodes(), commandParser);
                }
                if ("Perspectives".equals(nodeName)) {
                    perspectives = PerspectiveConverter.getPerspectiveList(node.getChildNodes());
                }
                if (STATE.equals(nodeName)) {
                    state = node.getTextContent();
                }
                if ("DefaultMeasure".equals(nodeName)) {
                    defaultMeasure = node.getTextContent();
                }
                if (VISIBLE.equals(nodeName)) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("MeasureGroups".equals(nodeName)) {
                    measureGroups = getMeasureGroupList(node.getChildNodes());
                }
                if (SOURCE.equals(nodeName)) {
                    source = getDataSourceViewBinding(node.getChildNodes());
                }
                if (AGGREGATION_PREFIX.equals(nodeName)) {
                    aggregationPrefix = node.getTextContent();
                }
                if (PROCESSING_PRIORITY.equals(nodeName)) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (STORAGE_MODE.equals(nodeName)) {
                    storageMode = getCubeStorageMode(node.getChildNodes());
                }
                if (PROCESSING_MODE.equals(nodeName)) {
                    processingMode = node.getTextContent();
                }
                if ("ScriptCacheProcessingMode".equals(nodeName)) {
                    scriptCacheProcessingMode = node.getTextContent();
                }
                if ("ScriptErrorHandlingMode".equals(nodeName)) {
                    scriptErrorHandlingMode = node.getTextContent();
                }
                if ("DaxOptimizationMode".equals(nodeName)) {
                    daxOptimizationMode = node.getTextContent();
                }
                if (PROACTIVE_CACHING.equals(nodeName)) {
                    proactiveCaching = CommonConverter.getProactiveCaching(node.getChildNodes());
                }
                if ("Kpis".equals(nodeName)) {
                    kpis = getKpiList(node.getChildNodes());
                }
                if (ERROR_CONFIGURATION.equals(nodeName)) {
                    errorConfiguration = CommonConverter.getErrorConfiguration(node.getChildNodes());
                }
                if ("Actions".equals(nodeName)) {
                    actions = ActionConverter.getActionList(node.getChildNodes());
                }
                if (STORAGE_LOCATION.equals(nodeName)) {
                    storageLocation = node.getTextContent();
                }
                if (ESTIMATED_ROWS.equals(nodeName)) {
                    estimatedRows = toLong(node.getTextContent());
                }
                if (LAST_PROCESSED.equals(nodeName)) {
                    lastProcessed = toInstant(node.getTextContent());
                }
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
        return getList(nl, "CubeDimension", childNodes -> getCubeDimension(childNodes));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (ID.equals(nodeName)) {
                    id = node.getTextContent();
                }
                if (NAME.equals(nodeName)) {
                    name = node.getTextContent();
                }
                if (DESCRIPTION.equals(nodeName)) {
                    description = node.getTextContent();
                }
                if (TRANSLATIONS.equals(nodeName)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (DIMENSION_ID.equals(nodeName)) {
                    dimensionID = node.getTextContent();
                }
                if (VISIBLE.equals(nodeName)) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("AllMemberAggregationUsage".equals(nodeName)) {
                    allMemberAggregationUsage = node.getTextContent();
                }
                if ("HierarchyUniqueNameStyle".equals(nodeName)) {
                    hierarchyUniqueNameStyle = node.getTextContent();
                }
                if ("MemberUniqueNameStyle".equals(nodeName)) {
                    memberUniqueNameStyle = node.getTextContent();
                }
                if (ATTRIBUTES.equals(nodeName)) {
                    attributes = getCubeAttributeList(node.getChildNodes());
                }
                if (HIERARCHIES.equals(nodeName)) {
                    hierarchies = getCubeHierarchyList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new CubeDimensionR(id, name, description, translations, dimensionID, visible, allMemberAggregationUsage,
                hierarchyUniqueNameStyle, memberUniqueNameStyle, attributes, hierarchies, annotations);
    }

    public static List<CubeAttribute> getCubeAttributeList(NodeList nl) {
        return getList(nl, ATTRIBUTE, childNodes -> getCubeAttribute(childNodes));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (ATTRIBUTE_ID.equals(nodeName)) {
                    attributeID = node.getTextContent();
                }
                if ("AggregationUsage".equals(nodeName)) {
                    aggregationUsage = node.getTextContent();
                }
                if ("AttributeHierarchyOptimizedState".equals(nodeName)) {
                    attributeHierarchyOptimizedState = node.getTextContent();
                }
                if ("AttributeHierarchyEnabled".equals(nodeName)) {
                    attributeHierarchyEnabled = toBoolean(node.getTextContent());
                }
                if (ATTRIBUTE_HIERARCHY_VISIBLE.equals(nodeName)) {
                    attributeHierarchyVisible = toBoolean(node.getTextContent());
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new CubeAttributeR(attributeID, aggregationUsage, attributeHierarchyOptimizedState,
                attributeHierarchyEnabled, attributeHierarchyVisible, annotations);
    }

    public static List<CubeHierarchy> getCubeHierarchyList(NodeList nl) {
        return getList(nl, HIERARCHY, childNodes -> getCubeHierarchy(childNodes));
    }

    public static CubeHierarchy getCubeHierarchy(NodeList nl) {
        String hierarchyID = null;
        String optimizedState = null;
        Boolean visible = null;
        Boolean enabled = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                String nodeName = node.getNodeName();
                if ("HierarchyID".equals(nodeName)) {
                    hierarchyID = node.getTextContent();
                }
                if ("OptimizedState".equals(nodeName)) {
                    optimizedState = node.getTextContent();
                }
                if (VISIBLE.equals(nodeName)) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("Enabled".equals(nodeName)) {
                    enabled = toBoolean(node.getTextContent());
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new CubeHierarchyR(hierarchyID, optimizedState, visible, enabled, annotations);
    }

    public static List<CubePermission> getCubePermissionList(NodeList nl) {
        return getList(nl, "CubePermission", childNodes -> getCubePermission(childNodes));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (NAME.equals(nodeName)) {
                    name = node.getTextContent();
                }
                if (ID.equals(nodeName)) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(nodeName)) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(nodeName)) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(nodeName)) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (ROLE_ID.equals(nodeName)) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(nodeName)) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(nodeName)) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(nodeName)) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(nodeName)) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if ("ReadSourceData".equals(nodeName)) {
                    readSourceData = node.getTextContent();
                }
                if ("DimensionPermissions".equals(nodeName)) {
                    dimensionPermissions = getCubeDimensionPermissionList(node.getChildNodes());
                }
                if ("CellPermissions".equals(nodeName)) {
                    cellPermissions = getCellPermissionList(node.getChildNodes());
                }
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
        return getList(nl, "CellPermission", childNodes -> getCellPermission(childNodes));
    }

    public static CellPermission getCellPermission(NodeList nl) {
        AccessEnum access = null;
        String description = null;
        String expression = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                String nodeName = node.getNodeName();
                if ("Access".equals(nodeName)) {
                    access = AccessEnum.fromValue(node.getTextContent());
                }
                if (DESCRIPTION.equals(nodeName)) {
                    description = node.getTextContent();
                }
                if (EXPRESSION.equals(nodeName)) {
                    expression = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new CellPermissionR(Optional.ofNullable(access), Optional.ofNullable(description),
                Optional.ofNullable(expression), Optional.ofNullable(annotations));
    }

    public static List<CubeDimensionPermission> getCubeDimensionPermissionList(NodeList nl) {
        return getList(nl, "DimensionPermission", childNodes -> getCubeDimensionPermission(childNodes));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (CUBE_DIMENSION_ID.equals(nodeName)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (DESCRIPTION.equals(nodeName)) {
                    description = node.getTextContent();
                }
                if ("Read".equals(nodeName)) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(nodeName)) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if ("AttributePermissions".equals(nodeName)) {
                    attributePermissions = DimensionConverter.getAttributePermissionList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new CubeDimensionPermissionR(cubeDimensionID, Optional.ofNullable(description),
                Optional.ofNullable(read), Optional.ofNullable(write), Optional.ofNullable(attributePermissions),
                Optional.ofNullable(annotations));
    }

    public static List<Kpi> getKpiList(NodeList nl) {
        return getList(nl, "Kpi", childNodes -> getKpi(childNodes));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (NAME.equals(nodeName)) {
                    name = node.getTextContent();
                }
                if (ID.equals(nodeName)) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(nodeName)) {
                    description = node.getTextContent();
                }
                if (TRANSLATIONS.equals(nodeName)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (DISPLAY_FOLDER.equals(nodeName)) {
                    displayFolder = node.getTextContent();
                }
                if ("AssociatedMeasureGroupID".equals(nodeName)) {
                    associatedMeasureGroupID = node.getTextContent();
                }
                if (VALUE.equals(nodeName)) {
                    value = node.getTextContent();
                }
                if ("Goal".equals(nodeName)) {
                    goal = node.getTextContent();
                }
                if ("Status".equals(nodeName)) {
                    status = node.getTextContent();
                }
                if ("Trend".equals(nodeName)) {
                    trend = node.getTextContent();
                }
                if ("Weight".equals(nodeName)) {
                    weight = node.getTextContent();
                }
                if ("TrendGraphic".equals(nodeName)) {
                    trendGraphic = node.getTextContent();
                }
                if ("StatusGraphic".equals(nodeName)) {
                    statusGraphic = node.getTextContent();
                }
                if ("CurrentTimeMember".equals(nodeName)) {
                    currentTimeMember = node.getTextContent();
                }
                if ("ParentKpiID".equals(nodeName)) {
                    parentKpiID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new KpiR(name, id, description, translations, displayFolder, associatedMeasureGroupID, value, goal,
                status, trend, weight, trendGraphic, statusGraphic, currentTimeMember, parentKpiID, annotations);
    }

    public static List<MeasureGroup> getMeasureGroupList(NodeList nl) {
        return getList(nl, MEASURE_GROUP, childNodes -> getMeasureGroup(childNodes));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (LAST_PROCESSED.equals(nodeName)) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (TRANSLATIONS.equals(nodeName)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if ("Type".equals(nodeName)) {
                    type = node.getTextContent();
                }
                if (STATE.equals(nodeName)) {
                    state = node.getTextContent();
                }
                if (MEASURES.equals(nodeName)) {
                    measures = getMeasureList(node.getChildNodes());
                }
                if ("DataAggregation".equals(nodeName)) {
                    dataAggregation = node.getTextContent();
                }
                if (SOURCE.equals(nodeName)) {
                    source = getMeasureGroupBinding(node.getChildNodes());
                }
                if (STORAGE_MODE.equals(nodeName)) {
                    storageMode = getMeasureGroupStorageMode(node.getChildNodes());
                }
                if (STORAGE_LOCATION.equals(nodeName)) {
                    storageLocation = node.getTextContent();
                }
                if ("IgnoreUnrelatedDimensions".equals(nodeName)) {
                    ignoreUnrelatedDimensions = toBoolean(node.getTextContent());
                }
                if (PROACTIVE_CACHING.equals(nodeName)) {
                    proactiveCaching = CommonConverter.getProactiveCaching(node.getChildNodes());
                }
                if (ESTIMATED_ROWS.equals(nodeName)) {
                    estimatedRows = toLong(node.getTextContent());
                }
                if (ERROR_CONFIGURATION.equals(nodeName)) {
                    errorConfiguration = CommonConverter.getErrorConfiguration(node.getChildNodes());
                }
                if (ESTIMATED_SIZE.equals(nodeName)) {
                    estimatedSize = toLong(node.getTextContent());
                }
                if (PROCESSING_MODE.equals(nodeName)) {
                    processingMode = node.getTextContent();
                }
                if (DIMENSIONS.equals(nodeName)) {
                    dimensions = getMeasureGroupDimensionList(node.getChildNodes());
                }
                if ("Partitions".equals(nodeName)) {
                    partitions = PartitionConverter.getPartitionList(node.getChildNodes());
                }
                if (AGGREGATION_PREFIX.equals(nodeName)) {
                    aggregationPrefix = node.getTextContent();
                }
                if (PROCESSING_PRIORITY.equals(nodeName)) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if ("AggregationDesigns".equals(nodeName)) {
                    aggregationDesigns = getAggregationDesignList(node.getChildNodes());
                }
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (DATA_SOURCE_ID.equals(nodeName)) {
                    dataSourceID = node.getTextContent();
                }
                if (CUBE_ID.equals(nodeName)) {
                    cubeID = node.getTextContent();
                }
                if (MEASURE_GROUP_ID.equals(nodeName)) {
                    measureGroupID = node.getTextContent();
                }
                if ("Persistence".equals(nodeName)) {
                    persistence = PersistenceEnum.fromValue(node.getTextContent());
                }
                if ("RefreshPolicy".equals(nodeName)) {
                    refreshPolicy = RefreshPolicyEnum.fromValue(node.getTextContent());
                }
                if ("RefreshInterval".equals(nodeName)) {
                    refreshInterval = toDuration(node.getTextContent());
                }
                if ("Filter".equals(nodeName)) {
                    filter = node.getTextContent();
                }
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
            if ((node != null) && (DIMENSIONS.equals(node.getNodeName()))) {
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (CUBE_DIMENSION_ID.equals(nodeName)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(nodeName)) {
                    source = (MeasureGroupDimensionBinding) BindingConverter
                            .getMeasureGroupDimensionBinding(node.getChildNodes());
                }
                if ("CaseCubeDimensionID".equals(nodeName)) {
                    caseCubeDimensionID = node.getTextContent();
                }
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (CUBE_DIMENSION_ID.equals(nodeName)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(nodeName)) {
                    source = (MeasureGroupDimensionBinding) BindingConverter
                            .getMeasureGroupDimensionBinding(node.getChildNodes());
                }
                if ("ShareDimensionStorage".equals(nodeName)) {
                    shareDimensionStorage = node.getTextContent();
                }
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (CUBE_DIMENSION_ID.equals(nodeName)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(nodeName)) {
                    source = (MeasureGroupDimensionBinding) BindingConverter
                            .getMeasureGroupDimensionBinding(node.getChildNodes());
                }
                if ("IntermediateCubeDimensionID".equals(nodeName)) {
                    intermediateCubeDimensionID = node.getTextContent();
                }
                if ("IntermediateGranularityAttributeID".equals(nodeName)) {
                    intermediateGranularityAttributeID = node.getTextContent();
                }
                if ("Materialization".equals(nodeName)) {
                    materialization = node.getTextContent();
                }
                if ("ProcessingState".equals(nodeName)) {
                    processingState = node.getTextContent();
                }
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (CUBE_DIMENSION_ID.equals(nodeName)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(nodeName)) {
                    source = (MeasureGroupDimensionBinding) BindingConverter
                            .getMeasureGroupDimensionBinding(node.getChildNodes());
                }
                if ("Cardinality".equals(nodeName)) {
                    cardinality = node.getTextContent();
                }
                if (ATTRIBUTES.equals(nodeName)) {
                    attributes = getMeasureGroupAttributeList(node.getChildNodes());
                }
            }
        }
        return new RegularMeasureGroupDimensionR(cubeDimensionID, annotations, source, cardinality, attributes);
    }

    public static List<MeasureGroupAttribute> getMeasureGroupAttributeList(NodeList nl) {
        return getList(nl, ATTRIBUTE, childNodes -> getMeasureGroupAttribute(childNodes));
    }

    public static MeasureGroupAttribute getMeasureGroupAttribute(NodeList nl) {
        String attributeID = null;
        List<DataItem> keyColumns = null;
        String type = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                String nodeName = node.getNodeName();
                if (ATTRIBUTE_ID.equals(nodeName)) {
                    attributeID = node.getTextContent();
                }
                if (KEY_COLUMNS.equals(nodeName)) {
                    keyColumns = getKeyColumnList(node.getChildNodes());
                }
                if ("Type".equals(nodeName)) {
                    type = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new MeasureGroupAttributeR(attributeID, keyColumns, type, annotations);
    }

    private static List<DataItem> getKeyColumnList(NodeList nl) {
        List<DataItem> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && (KEY_COLUMN.equals(node.getNodeName()))) {
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (CUBE_DIMENSION_ID.equals(nodeName)) {
                    cubeDimensionID = node.getTextContent();
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (SOURCE.equals(nodeName)) {
                    source = (MeasureGroupDimensionBinding) BindingConverter
                            .getMeasureGroupDimensionBinding(node.getChildNodes());
                }
                if (MEASURE_GROUP_ID.equals(nodeName)) {
                    measureGroupID = node.getTextContent();
                }
                if ("DirectSlice".equals(nodeName)) {
                    directSlice = node.getTextContent();
                }
            }
        }
        return new ManyToManyMeasureGroupDimensionR(cubeDimensionID, annotations, source, measureGroupID, directSlice);
    }

    public static List<Measure> getMeasureList(NodeList nl) {
        return getList(nl, MEASURE, childNodes -> getMeasure(childNodes));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if (NAME.equals(nodeName)) {
                    name = node.getTextContent();
                }
                if (ID.equals(nodeName)) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(nodeName)) {
                    description = node.getTextContent();
                }
                if ("AggregateFunction".equals(nodeName)) {
                    aggregateFunction = node.getTextContent();
                }
                if ("DataType".equals(nodeName)) {
                    dataType = node.getTextContent();
                }
                if (SOURCE.equals(nodeName)) {
                    source = CommonConverter.getDataItem(node.getChildNodes());
                }
                if (VISIBLE.equals(nodeName)) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("MeasureExpression".equals(nodeName)) {
                    measureExpression = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(nodeName)) {
                    displayFolder = node.getTextContent();
                }
                if ("FormatString".equals(nodeName)) {
                    formatString = node.getTextContent();
                }
                if ("BackColor".equals(nodeName)) {
                    backColor = node.getTextContent();
                }
                if ("ForeColor".equals(nodeName)) {
                    foreColor = node.getTextContent();
                }
                if ("FontName".equals(nodeName)) {
                    fontName = node.getTextContent();
                }
                if ("FontSize".equals(nodeName)) {
                    fontSize = node.getTextContent();
                }
                if ("FontFlags".equals(nodeName)) {
                    fontFlags = node.getTextContent();
                }
                if (TRANSLATIONS.equals(nodeName)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (ANNOTATIONS.equals(nodeName)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new MeasureR(name, id, description, aggregateFunction, dataType, source, visible, measureExpression,
                displayFolder, formatString, backColor, foreColor, fontName, fontSize, fontFlags, translations,
                annotations);
    }

    public static List<MdxScript> getMdxScriptList(NodeList nl, Function<NodeList, Command> commandParser) {
        return getList(nl, "MdxScript", childNodes -> getMdxScript(childNodes, commandParser));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if ("Commands".equals(nodeName)) {
                    commands = getCommandList(node.getChildNodes(), commandParser);
                }
                if ("DefaultScript".equals(nodeName)) {
                    defaultScript = toBoolean(node.getTextContent());
                }
                if ("CalculationProperties".equals(nodeName)) {
                    calculationProperties = getCalculationPropertyList(node.getChildNodes());
                }
            }
        }
        return new MdxScriptR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, commands,
                defaultScript, calculationProperties);
    }

    public static List<Command> getCommandList(NodeList nl, Function<NodeList, Command> commandParser) {
        return getList(nl, COMMAND, commandParser);
    }

    public static List<CalculationProperty> getCalculationPropertyList(NodeList nl) {
        return getList(nl, "CalculationProperty", childNodes -> getCalculationProperty(childNodes));
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if ("CalculationReference".equals(nodeName)) {
                    calculationReference = node.getTextContent();
                }
                if ("CalculationType".equals(nodeName)) {
                    calculationType = node.getTextContent();
                }
                if (TRANSLATIONS.equals(nodeName)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (DESCRIPTION.equals(nodeName)) {
                    description = node.getTextContent();
                }
                if (VISIBLE.equals(nodeName)) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("SolveOrder".equals(nodeName)) {
                    solveOrder = toBigInteger(node.getTextContent());
                }
                if ("FormatString".equals(nodeName)) {
                    formatString = node.getTextContent();
                }
                if ("ForeColor".equals(nodeName)) {
                    foreColor = node.getTextContent();
                }
                if ("BackColor".equals(nodeName)) {
                    backColor = node.getTextContent();
                }
                if ("FontName".equals(nodeName)) {
                    fontName = node.getTextContent();
                }
                if ("FontSize".equals(nodeName)) {
                    fontSize = node.getTextContent();
                }
                if ("FontFlags".equals(nodeName)) {
                    fontFlags = node.getTextContent();
                }
                if ("NonEmptyBehavior".equals(nodeName)) {
                    nonEmptyBehavior = node.getTextContent();
                }
                if ("AssociatedMeasureGroupID".equals(nodeName)) {
                    associatedMeasureGroupID = node.getTextContent();
                }
                if (DISPLAY_FOLDER.equals(nodeName)) {
                    displayFolder = node.getTextContent();
                }
                if (LANGUAGE.equals(nodeName)) {
                    language = toBigInteger(node.getTextContent());
                }
                if (VISUALIZATION_PROPERTIES.equals(nodeName)) {
                    visualizationProperties = getCalculationPropertiesVisualizationProperties(node.getChildNodes());
                }
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
            if (node != null) {
                String nodeName = node.getNodeName();
                if ("FolderPosition".equals(nodeName)) {
                    folderPosition = toBigInteger(node.getTextContent());
                }
                if ("ContextualNameRule".equals(nodeName)) {
                    contextualNameRule = node.getTextContent();
                }
                if ("Alignment".equals(nodeName)) {
                    alignment = node.getTextContent();
                }
                if ("IsFolderDefault".equals(nodeName)) {
                    isFolderDefault = toBoolean(node.getTextContent());
                }
                if ("IsRightToLeft".equals(nodeName)) {
                    isRightToLeft = toBoolean(node.getTextContent());
                }
                if ("SortDirection".equals(nodeName)) {
                    sortDirection = node.getTextContent();
                }
                if ("Units".equals(nodeName)) {
                    units = node.getTextContent();
                }
                if ("Width".equals(nodeName)) {
                    width = toBigInteger(node.getTextContent());
                }
                if ("IsDefaultMeasure".equals(nodeName)) {
                    isDefaultMeasure = toBoolean(node.getTextContent());
                }
                if ("DefaultDetailsPosition".equals(nodeName)) {
                    defaultDetailsPosition = toBigInteger(node.getTextContent());
                }
                if ("SortPropertiesPosition".equals(nodeName)) {
                    sortPropertiesPosition = toBigInteger(node.getTextContent());
                }
                if ("IsSimpleMeasure".equals(nodeName)) {
                    isSimpleMeasure = toBoolean(node.getTextContent());
                }
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
            if (node != null) {
                if (DATA_SOURCE_VIEW_ID.equals(node.getNodeName())) {
                    dataSourceViewID = node.getTextContent();
                }
            }
        }
        return new DataSourceViewBindingR(dataSourceViewID);
    }

    static List<AggregationDesign> getAggregationDesignList(NodeList nl) {
        List<AggregationDesign> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("AggregationDesigns".equals(node.getNodeName()))) {
                list.add(AggregationConverter.getAggregationDesign(node.getChildNodes(),
                        CommonConverter::getAnnotationList));
            }
        }
        return list;
    }
}
