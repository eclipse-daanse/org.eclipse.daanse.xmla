/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter;

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ANNOTATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_HIERARCHY_VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ATTRIBUTE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CAPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COLLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CONTEXTUAL_NAME_RULE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DEFAULT_DETAILS_POSITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DEFAULT_MEMBER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DISPLAY_FOLDER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ERROR_CONFIGURATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.FOLDER_POSITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.HIERARCHIES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.HIERARCHY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.KEY_COLUMN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.KEY_COLUMNS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LANGUAGE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_PROCESSED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MINING_MODEL_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROACTIVE_CACHING;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESSING_MODE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESSING_PRIORITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESSING_STATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.READ_DEFINITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE2;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SORT_PROPERTIES_POSITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SOURCE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STORAGE_MODE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VALUENS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VISUALIZATION_PROPERTIES;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.WRITE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getAttribute;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getListByLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getNodeType;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBigInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toLong;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.engine300.AttributeHierarchyProcessingState;
import org.eclipse.daanse.xmla.api.engine300.DimensionAttributeVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.HierarchyVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300.RelationshipEndVisualizationProperties;
import org.eclipse.daanse.xmla.api.engine300_300.Relationship;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEnd;
import org.eclipse.daanse.xmla.api.engine300_300.RelationshipEndTranslation;
import org.eclipse.daanse.xmla.api.engine300_300.Relationships;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.AttributePermission;
import org.eclipse.daanse.xmla.api.xmla.AttributeRelationship;
import org.eclipse.daanse.xmla.api.xmla.AttributeTranslation;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.Dimension;
import org.eclipse.daanse.xmla.api.xmla.DimensionAttribute;
import org.eclipse.daanse.xmla.api.xmla.DimensionAttributeTypeEnumType;
import org.eclipse.daanse.xmla.api.xmla.DimensionPermission;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Hierarchy;
import org.eclipse.daanse.xmla.api.xmla.Level;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.api.xmla.UnknownMemberEnumType;
import org.eclipse.daanse.xmla.model.record.engine300.DimensionAttributeVisualizationPropertiesR;
import org.eclipse.daanse.xmla.model.record.engine300.HierarchyVisualizationPropertiesR;
import org.eclipse.daanse.xmla.model.record.engine300.RelationshipEndVisualizationPropertiesR;
import org.eclipse.daanse.xmla.model.record.engine300_300.RelationshipEndR;
import org.eclipse.daanse.xmla.model.record.engine300_300.RelationshipEndTranslationR;
import org.eclipse.daanse.xmla.model.record.engine300_300.RelationshipR;
import org.eclipse.daanse.xmla.model.record.engine300_300.RelationshipsR;
import org.eclipse.daanse.xmla.model.record.xmla.AttributePermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.DimensionAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.DimensionPermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.DimensionR;
import org.eclipse.daanse.xmla.model.record.xmla.HierarchyR;
import org.eclipse.daanse.xmla.model.record.xmla.LevelR;
import org.w3c.dom.NodeList;

/**
 * Converter for Dimension-related XML parsing. Handles Dimension,
 * DimensionAttribute, Hierarchy, Level, and related types.
 */
public class DimensionConverter {

    private DimensionConverter() {
        // utility class
    }

    public static List<Dimension> getDimensionList(NodeList nl) {
        return getListByLocalName(nl, DIMENSION, childNodes -> getDimension(childNodes));
    }

    public static Dimension getDimension(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        Binding source = null;
        String miningModelID = null;
        String type = null;
        Dimension.UnknownMember unknownMember = null;
        String mdxMissingMemberMode = null;
        ErrorConfiguration errorConfiguration = null;
        String storageMode = null;
        Boolean writeEnabled = null;
        BigInteger processingPriority = null;
        Instant lastProcessed = null;
        List<DimensionPermission> dimensionPermissions = null;
        String dependsOnDimensionID = null;
        BigInteger language = null;
        String collation = null;
        String unknownMemberName = null;
        List<Translation> unknownMemberTranslations = null;
        String state = null;
        ProactiveCaching proactiveCaching = null;
        String processingMode = null;
        String processingGroup = null;
        Dimension.CurrentStorageMode currentStorageMode = null;
        List<Translation> translations = null;
        List<DimensionAttribute> attributes = null;
        String attributeAllMemberName = null;
        List<Translation> attributeAllMemberTranslations = null;
        List<Hierarchy> hierarchies = null;
        String processingRecommendation = null;
        Relationships relationships = null;
        Integer stringStoresCompatibilityLevel = null;
        Integer currentStringStoresCompatibilityLevel = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
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
                if (matchesLocalName(node, SOURCE)) {
                    source = BindingConverter.getBinding(node.getChildNodes(), getNodeType(node));
                }
                if (matchesLocalName(node, MINING_MODEL_ID)) {
                    miningModelID = node.getTextContent();
                }
                if (matchesLocalName(node, "Type")) {
                    type = node.getTextContent();
                }
                if (matchesLocalName(node, "UnknownMember")) {
                    unknownMember = getDimensionUnknownMember(node.getChildNodes());
                }
                if (matchesLocalName(node, "MdxMissingMemberMode")) {
                    mdxMissingMemberMode = node.getTextContent();
                }
                if (matchesLocalName(node, ERROR_CONFIGURATION)) {
                    errorConfiguration = CommonConverter.getErrorConfiguration(node.getChildNodes());
                }
                if (matchesLocalName(node, STORAGE_MODE)) {
                    storageMode = node.getTextContent();
                }
                if (matchesLocalName(node, "WriteEnabled")) {
                    writeEnabled = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, PROCESSING_PRIORITY)) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, LAST_PROCESSED)) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (matchesLocalName(node, "DimensionPermissions")) {
                    dimensionPermissions = getDimensionPermissionList(node.getChildNodes());
                }
                if (matchesLocalName(node, "DependsOnDimensionID")) {
                    dependsOnDimensionID = node.getTextContent();
                }
                if (matchesLocalName(node, LANGUAGE)) {
                    language = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, COLLATION)) {
                    collation = node.getTextContent();
                }
                if (matchesLocalName(node, "UnknownMemberName")) {
                    unknownMemberName = node.getTextContent();
                }
                if (matchesLocalName(node, "UnknownMemberTranslations")) {
                    unknownMemberTranslations = CommonConverter.getTranslationList(node.getChildNodes(),
                            "UnknownMemberTranslation");
                }
                if (matchesLocalName(node, STATE)) {
                    state = node.getTextContent();
                }
                if (matchesLocalName(node, PROACTIVE_CACHING)) {
                    proactiveCaching = CommonConverter.getProactiveCaching(node.getChildNodes());
                }
                if (matchesLocalName(node, PROCESSING_MODE)) {
                    processingMode = node.getTextContent();
                }
                if (matchesLocalName(node, "ProcessingGroup")) {
                    processingGroup = node.getTextContent();
                }
                if (matchesLocalName(node, "CurrentStorageMode")) {
                    processingGroup = node.getTextContent();
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (matchesLocalName(node, ATTRIBUTES)) {
                    attributes = getDimensionAttributeList(node.getChildNodes());
                }
                if (matchesLocalName(node, "AttributeAllMemberName")) {
                    attributeAllMemberName = node.getTextContent();
                }
                if (matchesLocalName(node, "AttributeAllMemberTranslations")) {
                    attributeAllMemberTranslations = CommonConverter.getTranslationList(node.getChildNodes(),
                            "AttributeAllMemberTranslation");
                }
                if (matchesLocalName(node, HIERARCHIES)) {
                    hierarchies = getHierarchyList(node.getChildNodes());
                }
                if (matchesLocalName(node, "ProcessingRecommendation")) {
                    processingRecommendation = node.getTextContent();
                }
                if (matchesLocalName(node, "Relationships")) {
                    relationships = getRelationships(node.getChildNodes());
                }
                if (matchesLocalName(node, "StringStoresCompatibilityLevel")) {
                    stringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "CurrentStringStoresCompatibilityLevel")) {
                    currentStringStoresCompatibilityLevel = toInteger(node.getTextContent());
                }
            }
        }
        return new DimensionR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, source,
                miningModelID, type, unknownMember, mdxMissingMemberMode, errorConfiguration, storageMode, writeEnabled,
                processingPriority, lastProcessed, dimensionPermissions, dependsOnDimensionID, language, collation,
                unknownMemberName, unknownMemberTranslations, state, proactiveCaching, processingMode, processingGroup,
                currentStorageMode, translations, attributes, attributeAllMemberName, attributeAllMemberTranslations,
                hierarchies, processingRecommendation, relationships, stringStoresCompatibilityLevel,
                currentStringStoresCompatibilityLevel);
    }

    public static List<DimensionPermission> getDimensionPermissionList(NodeList nl) {
        return getListByLocalName(nl, "DimensionPermission", childNodes -> getDimensionPermission(childNodes));
    }

    public static DimensionPermission getDimensionPermission(NodeList nl) {
        List<AttributePermission> attributePermissions = null;
        String allowedRowsExpression = null;
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
                if (matchesLocalName(node, "AttributePermissions")) {
                    attributePermissions = getAttributePermissionList(node.getChildNodes());
                }
                if (matchesLocalName(node, "AllowedRowsExpression")) {
                    allowedRowsExpression = node.getTextContent();
                }
            }
        }

        return new DimensionPermissionR(Optional.ofNullable(attributePermissions),
                Optional.ofNullable(allowedRowsExpression), name, Optional.ofNullable(id),
                Optional.ofNullable(createdTimestamp), Optional.ofNullable(lastSchemaUpdate),
                Optional.ofNullable(description), Optional.ofNullable(annotations), roleID,
                Optional.ofNullable(process), Optional.ofNullable(readDefinition), Optional.ofNullable(read),
                Optional.ofNullable(write));
    }

    public static List<AttributePermission> getAttributePermissionList(NodeList nl) {
        return getListByLocalName(nl, "AttributePermission", childNodes -> getAttributePermission(childNodes));
    }

    public static AttributePermission getAttributePermission(NodeList nl) {
        String attributeID = null;
        String description = null;
        String defaultMember = null;
        String visualTotals = null;
        String allowedSet = null;
        String deniedSet = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ATTRIBUTE_ID)) {
                    attributeID = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, DEFAULT_MEMBER)) {
                    defaultMember = node.getTextContent();
                }
                if (matchesLocalName(node, "VisualTotals")) {
                    visualTotals = node.getTextContent();
                }
                if (matchesLocalName(node, "AllowedSet")) {
                    allowedSet = node.getTextContent();
                }
                if (matchesLocalName(node, "DeniedSet")) {
                    deniedSet = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AttributePermissionR(attributeID, Optional.ofNullable(description),
                Optional.ofNullable(defaultMember), Optional.ofNullable(visualTotals), Optional.ofNullable(allowedSet),
                Optional.ofNullable(deniedSet), Optional.ofNullable(annotations));
    }

    public static Dimension.UnknownMember getDimensionUnknownMember(NodeList nl) {
        UnknownMemberEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = UnknownMemberEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
            }
        }
        return new DimensionR.UnknownMember(value, valuens);
    }

    public static List<DimensionAttribute> getDimensionAttributeList(NodeList nl) {
        return getListByLocalName(nl, ATTRIBUTE, childNodes -> getDimensionAttribute(childNodes));
    }

    public static DimensionAttribute getDimensionAttribute(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        DimensionAttribute.Type type = null;
        String usage = null;
        Binding source = null;
        Long estimatedCount = null;
        List<DataItem> keyColumns = null;
        DataItem nameColumn = null;
        DataItem valueColumn = null;
        List<AttributeTranslation> translations = null;
        List<AttributeRelationship> attributeRelationships = null;
        String discretizationMethod = null;
        BigInteger discretizationBucketCount = null;
        String rootMemberIf = null;
        String orderBy = null;
        String defaultMember = null;
        String orderByAttributeID = null;
        DataItem skippedLevelsColumn = null;
        String namingTemplate = null;
        String membersWithData = null;
        String membersWithDataCaption = null;
        List<Translation> namingTemplateTranslations = null;
        DataItem customRollupColumn = null;
        DataItem customRollupPropertiesColumn = null;
        DataItem unaryOperatorColumn = null;
        Boolean attributeHierarchyOrdered = null;
        Boolean memberNamesUnique = null;
        Boolean isAggregatable = null;
        Boolean attributeHierarchyEnabled = null;
        String attributeHierarchyOptimizedState = null;
        Boolean attributeHierarchyVisible = null;
        String attributeHierarchyDisplayFolder = null;
        Boolean keyUniquenessGuarantee = null;
        String groupingBehavior = null;
        String instanceSelection = null;
        List<Annotation> annotations = null;
        String processingState = null;
        AttributeHierarchyProcessingState attributeHierarchyProcessingState = null;
        DimensionAttributeVisualizationProperties visualizationProperties = null;
        String extendedType = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, "Type")) {
                    type = getDimensionAttributeType(node.getChildNodes());
                }
                if (matchesLocalName(node, "Usage")) {
                    usage = node.getTextContent();
                }
                if (matchesLocalName(node, SOURCE)) {
                    source = BindingConverter.getBinding(node.getChildNodes(), getNodeType(node));
                }
                if (matchesLocalName(node, "EstimatedCount")) {
                    estimatedCount = toLong(node.getTextContent());
                }
                if (matchesLocalName(node, KEY_COLUMNS)) {
                    keyColumns = CommonConverter.getDataItemList(node.getChildNodes(), KEY_COLUMN);
                }
                if (matchesLocalName(node, "NameColumn")) {
                    nameColumn = CommonConverter.getDataItem(node.getChildNodes());
                }
                if (matchesLocalName(node, "ValueColumn")) {
                    valueColumn = CommonConverter.getDataItem(node.getChildNodes());
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getAttributeTranslationList(node.getChildNodes());
                }
                if (matchesLocalName(node, "AttributeRelationships")) {
                    translations = getAttributeRelationshipsList(node.getChildNodes());
                }
                if (matchesLocalName(node, "DiscretizationMethod")) {
                    discretizationMethod = node.getTextContent();
                }
                if (matchesLocalName(node, "DiscretizationBucketCount")) {
                    discretizationBucketCount = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "RootMemberIf")) {
                    rootMemberIf = node.getTextContent();
                }
                if (matchesLocalName(node, "OrderBy")) {
                    orderBy = node.getTextContent();
                }
                if (matchesLocalName(node, DEFAULT_MEMBER)) {
                    defaultMember = node.getTextContent();
                }
                if (matchesLocalName(node, "OrderByAttributeID")) {
                    orderByAttributeID = node.getTextContent();
                }
                if (matchesLocalName(node, "SkippedLevelsColumn")) {
                    skippedLevelsColumn = CommonConverter.getDataItem(node.getChildNodes());
                }
                if (matchesLocalName(node, "NamingTemplate")) {
                    namingTemplate = node.getTextContent();
                }
                if (matchesLocalName(node, "MembersWithData")) {
                    membersWithData = node.getTextContent();
                }
                if (matchesLocalName(node, "MembersWithDataCaption")) {
                    membersWithDataCaption = node.getTextContent();
                }
                if (matchesLocalName(node, "NamingTemplateTranslations")) {
                    namingTemplateTranslations = getNamingTemplateTranslationList(node.getChildNodes());
                }
                if (matchesLocalName(node, "CustomRollupColumn")) {
                    customRollupColumn = CommonConverter.getDataItem(node.getChildNodes());
                }
                if (matchesLocalName(node, "CustomRollupPropertiesColumn")) {
                    customRollupPropertiesColumn = CommonConverter.getDataItem(node.getChildNodes());
                }
                if (matchesLocalName(node, "unaryOperatorColumn")) {
                    unaryOperatorColumn = CommonConverter.getDataItem(node.getChildNodes());
                }
                if (matchesLocalName(node, "AttributeHierarchyOrdered")) {
                    attributeHierarchyOrdered = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "MemberNamesUnique")) {
                    memberNamesUnique = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "IsAggregatable")) {
                    isAggregatable = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "AttributeHierarchyEnabled")) {
                    attributeHierarchyEnabled = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "AttributeHierarchyOptimizedState")) {
                    attributeHierarchyOptimizedState = node.getTextContent();
                }
                if (matchesLocalName(node, ATTRIBUTE_HIERARCHY_VISIBLE)) {
                    attributeHierarchyVisible = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "AttributeHierarchyDisplayFolder")) {
                    attributeHierarchyDisplayFolder = node.getTextContent();
                }
                if (matchesLocalName(node, "KeyUniquenessGuarantee")) {
                    keyUniquenessGuarantee = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "GroupingBehavior")) {
                    groupingBehavior = node.getTextContent();
                }
                if (matchesLocalName(node, "InstanceSelection")) {
                    instanceSelection = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, PROCESSING_STATE)) {
                    processingState = node.getTextContent();
                }
                if (matchesLocalName(node, "AttributeHierarchyProcessingState")) {
                    attributeHierarchyProcessingState = AttributeHierarchyProcessingState
                            .fromValue(node.getTextContent());
                }
                if (matchesLocalName(node, VISUALIZATION_PROPERTIES)) {
                    visualizationProperties = getDimensionAttributeVisualizationProperties(node.getChildNodes());
                }
                if (matchesLocalName(node, "ExtendedType")) {
                    extendedType = node.getTextContent();
                }
            }
        }
        return new DimensionAttributeR(name, id, description, type, usage, source, estimatedCount, keyColumns,
                nameColumn, valueColumn, translations, attributeRelationships, discretizationMethod,
                discretizationBucketCount, rootMemberIf, orderBy, defaultMember, orderByAttributeID,
                skippedLevelsColumn, namingTemplate, membersWithData, membersWithDataCaption,
                namingTemplateTranslations, customRollupColumn, customRollupPropertiesColumn, unaryOperatorColumn,
                attributeHierarchyOrdered, memberNamesUnique, isAggregatable, attributeHierarchyEnabled,
                attributeHierarchyOptimizedState, attributeHierarchyVisible, attributeHierarchyDisplayFolder,
                keyUniquenessGuarantee, groupingBehavior, instanceSelection, annotations, processingState,
                attributeHierarchyProcessingState, visualizationProperties, extendedType);
    }

    public static DimensionAttributeVisualizationProperties getDimensionAttributeVisualizationProperties(NodeList nl) {
        BigInteger folderPosition = null;
        String contextualNameRule = null;
        String alignment = null;
        Boolean isFolderDefault = null;
        Boolean isRightToLeft = null;
        String sortDirection = null;
        String units = null;
        BigInteger width = null;
        BigInteger defaultDetailsPosition = null;
        BigInteger commonIdentifierPosition = null;
        BigInteger sortPropertiesPosition = null;
        BigInteger displayKeyPosition = null;
        Boolean isDefaultImage = null;
        String defaultAggregateFunction = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, FOLDER_POSITION)) {
                    folderPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, CONTEXTUAL_NAME_RULE)) {
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
                if (matchesLocalName(node, DEFAULT_DETAILS_POSITION)) {
                    defaultDetailsPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, SORT_PROPERTIES_POSITION)) {
                    sortPropertiesPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "CommonIdentifierPosition")) {
                    commonIdentifierPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "DisplayKeyPosition")) {
                    displayKeyPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "IsDefaultImage")) {
                    isDefaultImage = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "DefaultAggregateFunction")) {
                    defaultAggregateFunction = node.getTextContent();
                }
            }
        }
        return new DimensionAttributeVisualizationPropertiesR(folderPosition, contextualNameRule, alignment,
                isFolderDefault, isRightToLeft, sortDirection, units, width, defaultDetailsPosition,
                commonIdentifierPosition, sortPropertiesPosition, displayKeyPosition, isDefaultImage,
                defaultAggregateFunction);
    }

    public static List<Translation> getNamingTemplateTranslationList(NodeList nl) {
        return getListByLocalName(nl, "NamingTemplateTranslation", childNodes -> getTranslation(childNodes));
    }

    public static List<AttributeTranslation> getAttributeRelationshipsList(NodeList nl) {
        return getListByLocalName(nl, "AttributeRelationship", childNodes -> getAttributeRelationship(childNodes));
    }

    private static AttributeTranslation getAttributeRelationship(NodeList nl) {
        return CommonConverter.getAttributeTranslationList(nl).isEmpty() ? null
                : CommonConverter.getAttributeTranslationList(nl).get(0);
    }

    private static Translation getTranslation(NodeList nl) {
        return CommonConverter.getTranslationList(nl, TRANSLATION).isEmpty() ? null
                : CommonConverter.getTranslationList(nl, TRANSLATION).get(0);
    }

    public static DimensionAttribute.Type getDimensionAttributeType(NodeList nl) {
        DimensionAttributeTypeEnumType value = null;
        String valuens = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                value = DimensionAttributeTypeEnumType.fromValue(node.getTextContent());
                valuens = getAttribute(node.getAttributes(), VALUENS);
                break;
            }
        }
        return new DimensionAttributeR.Type(value, valuens);
    }

    public static List<Hierarchy> getHierarchyList(NodeList nl) {
        return getListByLocalName(nl, HIERARCHY, childNodes -> getHierarchy(childNodes));
    }

    public static Hierarchy getHierarchy(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String processingState = null;
        String structureType = null;
        String displayFolder = null;
        List<Translation> translations = null;
        String allMemberName = null;
        List<Translation> allMemberTranslations = null;
        Boolean memberNamesUnique = null;
        String memberKeysUnique = null;
        Boolean allowDuplicateNames = null;
        List<Level> levels = null;
        List<Annotation> annotations = null;
        HierarchyVisualizationProperties visualizationProperties = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, PROCESSING_STATE)) {
                    processingState = node.getTextContent();
                }
                if (matchesLocalName(node, "StructureType")) {
                    structureType = node.getTextContent();
                }
                if (matchesLocalName(node, DISPLAY_FOLDER)) {
                    displayFolder = node.getTextContent();
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (matchesLocalName(node, "AllMemberName")) {
                    allMemberName = node.getTextContent();
                }
                if (matchesLocalName(node, "AllMemberTranslations")) {
                    allMemberTranslations = CommonConverter.getTranslationList(node.getChildNodes(),
                            "AllMemberTranslation");
                }
                if (matchesLocalName(node, "MemberNamesUnique")) {
                    memberNamesUnique = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "MemberKeysUnique")) {
                    memberKeysUnique = node.getTextContent();
                }
                if (matchesLocalName(node, "AllowDuplicateNames")) {
                    allowDuplicateNames = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "Levels")) {
                    levels = getLevelList(node.getChildNodes());
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, VISUALIZATION_PROPERTIES)) {
                    visualizationProperties = getHierarchyVisualizationProperties(node.getChildNodes());
                }
            }
        }
        return new HierarchyR(name, id, description, processingState, structureType, displayFolder, translations,
                allMemberName, allMemberTranslations, memberNamesUnique, memberKeysUnique, allowDuplicateNames, levels,
                annotations, visualizationProperties);
    }

    public static HierarchyVisualizationProperties getHierarchyVisualizationProperties(NodeList nl) {
        String contextualNameRule = null;
        BigInteger folderPosition = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, CONTEXTUAL_NAME_RULE)) {
                    contextualNameRule = node.getTextContent();
                }
                if (matchesLocalName(node, FOLDER_POSITION)) {
                    folderPosition = toBigInteger(node.getTextContent());
                }
            }
        }
        return new HierarchyVisualizationPropertiesR(contextualNameRule, folderPosition);
    }

    public static List<Level> getLevelList(NodeList nl) {
        return getListByLocalName(nl, "Level", childNodes -> getLevel(childNodes));
    }

    public static Level getLevel(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String sourceAttributeID = null;
        String hideMemberIf = null;
        List<Translation> translations = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, "SourceAttributeID")) {
                    sourceAttributeID = node.getTextContent();
                }
                if (matchesLocalName(node, "HideMemberIf")) {
                    hideMemberIf = node.getTextContent();
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new LevelR(name, id, description, sourceAttributeID, hideMemberIf, translations, annotations);
    }

    public static Relationships getRelationships(NodeList nl) {
        List<Relationship> relationship = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "Relationship")) {
                relationship.add(getRelationship(node.getChildNodes()));
                break;
            }
        }
        return new RelationshipsR(relationship);
    }

    public static Relationship getRelationship(NodeList nl) {
        String id = null;
        boolean visible = false;
        RelationshipEnd fromRelationshipEnd = null;
        RelationshipEnd toRelationshipEnd = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, VISIBLE)) {
                    visible = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "FromRelationshipEnd")) {
                    fromRelationshipEnd = getRelationshipEnd(node.getChildNodes());
                }
                if (matchesLocalName(node, "ToRelationshipEnd")) {
                    toRelationshipEnd = getRelationshipEnd(node.getChildNodes());
                }
            }
        }
        return new RelationshipR(id, visible, fromRelationshipEnd, toRelationshipEnd);
    }

    public static RelationshipEnd getRelationshipEnd(NodeList nl) {
        String role = null;
        String multiplicity = null;
        String dimensionID = null;
        List<String> attributes = null;
        List<RelationshipEndTranslation> translations = null;
        RelationshipEndVisualizationProperties visualizationProperties = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, ROLE2)) {
                    role = node.getTextContent();
                }
                if (matchesLocalName(node, "Multiplicity")) {
                    multiplicity = node.getTextContent();
                }
                if (matchesLocalName(node, DIMENSION_ID)) {
                    dimensionID = node.getTextContent();
                }
                if (matchesLocalName(node, ATTRIBUTES)) {
                    attributes = getRelationshipEndAttributes(node.getChildNodes());
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = getRelationshipEndTranslations(node.getChildNodes());
                }
                if (matchesLocalName(node, VISUALIZATION_PROPERTIES)) {
                    visualizationProperties = getRelationshipEndVisualizationProperties(node.getChildNodes());
                }
            }
        }
        return new RelationshipEndR(role, multiplicity, dimensionID, attributes, translations, visualizationProperties);
    }

    public static RelationshipEndVisualizationProperties getRelationshipEndVisualizationProperties(NodeList nl) {
        BigInteger folderPosition = null;
        String contextualNameRule = null;
        BigInteger defaultDetailsPosition = null;
        BigInteger displayKeyPosition = null;
        BigInteger commonIdentifierPosition = null;
        Boolean isDefaultMeasure = null;
        Boolean isDefaultImage = null;
        BigInteger sortPropertiesPosition = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, FOLDER_POSITION)) {
                    folderPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, CONTEXTUAL_NAME_RULE)) {
                    contextualNameRule = node.getTextContent();
                }
                if (matchesLocalName(node, DEFAULT_DETAILS_POSITION)) {
                    defaultDetailsPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "DisplayKeyPosition")) {
                    displayKeyPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "CommonIdentifierPosition")) {
                    commonIdentifierPosition = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "IsDefaultMeasure")) {
                    isDefaultMeasure = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "IsDefaultImage")) {
                    isDefaultImage = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, SORT_PROPERTIES_POSITION)) {
                    sortPropertiesPosition = toBigInteger(node.getTextContent());
                }
            }
        }
        return new RelationshipEndVisualizationPropertiesR(folderPosition, contextualNameRule, defaultDetailsPosition,
                displayKeyPosition, commonIdentifierPosition, isDefaultMeasure, isDefaultImage, sortPropertiesPosition);
    }

    public static List<RelationshipEndTranslation> getRelationshipEndTranslations(NodeList nl) {
        List<RelationshipEndTranslation> translations = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, TRANSLATION)) {
                translations.add(getRelationshipEndTranslation(node.getChildNodes()));
            }
        }
        return translations;
    }

    public static RelationshipEndTranslation getRelationshipEndTranslation(NodeList nl) {
        long language = 0;
        String caption = null;
        String collectionCaption = null;
        String description = null;
        String displayFolder = null;
        List<Annotation> annotations = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, LANGUAGE)) {
                    language = toLong(node.getTextContent());
                }
                if (matchesLocalName(node, CAPTION)) {
                    caption = node.getTextContent();
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, DISPLAY_FOLDER)) {
                    displayFolder = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, "CollectionCaption")) {
                    collectionCaption = node.getTextContent();
                }
            }
        }
        return new RelationshipEndTranslationR(language, caption, collectionCaption, description, displayFolder,
                annotations);
    }

    public static List<String> getRelationshipEndAttributes(NodeList nl) {
        List<String> attributes = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ATTRIBUTE)) {
                attributes.add(getAttributeId(node.getChildNodes()));
            }
        }
        return attributes;
    }

    public static String getAttributeId(NodeList nl) {
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, ATTRIBUTE_ID)) {
                return node.getTextContent();
            }
        }
        return null;
    }
}
