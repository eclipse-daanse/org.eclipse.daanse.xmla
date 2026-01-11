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

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ALLOW_DRILL_THROUGH;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ANNOTATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COLLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COLUMNS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ERROR_CONFIGURATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.FILTER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.KEY_COLUMN;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.KEY_COLUMNS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LANGUAGE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_PROCESSED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.READ_DEFINITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SOURCE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.WRITE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getList;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getNodeType;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getStringList;
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

import org.eclipse.daanse.xmla.api.xmla.AlgorithmParameter;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.AttributeTranslation;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.FoldingParameters;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupBinding;
import org.eclipse.daanse.xmla.api.xmla.MiningModel;
import org.eclipse.daanse.xmla.api.xmla.MiningModelColumn;
import org.eclipse.daanse.xmla.api.xmla.MiningModelPermission;
import org.eclipse.daanse.xmla.api.xmla.MiningModelingFlag;
import org.eclipse.daanse.xmla.api.xmla.MiningStructure;
import org.eclipse.daanse.xmla.api.xmla.MiningStructureColumn;
import org.eclipse.daanse.xmla.api.xmla.MiningStructurePermission;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.model.record.xmla.AlgorithmParameterR;
import org.eclipse.daanse.xmla.model.record.xmla.FoldingParametersR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningModelColumnR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningModelPermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningModelR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningModelingFlagR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningStructurePermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningStructureR;
import org.eclipse.daanse.xmla.model.record.xmla.ScalarMiningStructureColumnR;
import org.eclipse.daanse.xmla.model.record.xmla.TableMiningStructureColumnR;
import org.w3c.dom.NodeList;

/**
 * Converter for Mining-related XMLA types. Handles MiningStructure,
 * MiningModel, and related types.
 */
public class MiningConverter {

    private MiningConverter() {
        // utility class
    }

    public static List<MiningStructure> getMiningStructureList(NodeList nl) {
        return getList(nl, "MiningStructure", childNl -> getMiningStructure(childNl));
    }

    public static MiningStructure getMiningStructure(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        Binding source = null;
        Instant lastProcessed = null;
        List<Translation> translations = null;
        BigInteger language = null;
        String collation = null;
        ErrorConfiguration errorConfiguration = null;
        String cacheMode = null;
        Integer holdoutMaxPercent = null;
        Integer holdoutMaxCases = null;
        Integer holdoutSeed = null;
        Integer holdoutActualSize = null;
        List<MiningStructureColumn> columns = null;
        String state = null;
        List<MiningStructurePermission> miningStructurePermissions = null;
        List<MiningModel> miningModels = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (SOURCE.equals(node.getNodeName())) {
                    source = BindingConverter.getBinding(node.getChildNodes(), getNodeType(node));
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toBigInteger(node.getTextContent());
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
                if (ERROR_CONFIGURATION.equals(node.getNodeName())) {
                    errorConfiguration = CommonConverter.getErrorConfiguration(node.getChildNodes());
                }
                if ("CacheMode".equals(node.getNodeName())) {
                    cacheMode = node.getTextContent();
                }
                if ("HoldoutMaxPercent".equals(node.getNodeName())) {
                    holdoutMaxPercent = toInteger(node.getTextContent());
                }
                if ("HoldoutMaxCases".equals(node.getNodeName())) {
                    holdoutMaxCases = toInteger(node.getTextContent());
                }
                if ("HoldoutSeed".equals(node.getNodeName())) {
                    holdoutSeed = toInteger(node.getTextContent());
                }
                if ("HoldoutActualSize".equals(node.getNodeName())) {
                    holdoutSeed = toInteger(node.getTextContent());
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getMiningStructureColumnList(node.getChildNodes());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("MiningStructurePermissions".equals(node.getNodeName())) {
                    miningStructurePermissions = getMiningStructurePermissionList(node.getChildNodes());
                }
                if ("MiningModels".equals(node.getNodeName())) {
                    miningModels = getMiningModelList(node.getChildNodes());
                }
            }
        }
        return new MiningStructureR(name, Optional.ofNullable(id), Optional.ofNullable(createdTimestamp),
                Optional.ofNullable(lastSchemaUpdate), Optional.ofNullable(description),
                Optional.ofNullable(annotations), Optional.ofNullable(source), Optional.ofNullable(lastProcessed),
                Optional.ofNullable(translations), Optional.ofNullable(language), Optional.ofNullable(collation),
                Optional.ofNullable(errorConfiguration), Optional.ofNullable(cacheMode),
                Optional.ofNullable(holdoutMaxPercent), Optional.ofNullable(holdoutMaxCases),
                Optional.ofNullable(holdoutSeed), Optional.ofNullable(holdoutActualSize), columns,
                Optional.ofNullable(state), Optional.ofNullable(miningStructurePermissions),
                Optional.ofNullable(miningModels));
    }

    public static List<MiningModel> getMiningModelList(NodeList nl) {
        return getList(nl, "MiningModel", childNl -> getMiningModel(childNl));
    }

    private static List<MiningStructurePermission> getMiningStructurePermissionList(NodeList nl) {
        return getList(nl, "MiningStructurePermission", childNl -> getMiningStructurePermission(childNl));
    }

    private static MiningStructurePermission getMiningStructurePermission(NodeList nl) {
        Boolean allowDrillThrough = null;
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
                if (ALLOW_DRILL_THROUGH.equals(node.getNodeName())) {
                    allowDrillThrough = toBoolean(node.getTextContent());
                }
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
            }
        }
        return new MiningStructurePermissionR(Optional.ofNullable(allowDrillThrough), name, Optional.ofNullable(id),
                Optional.ofNullable(createdTimestamp), Optional.ofNullable(lastSchemaUpdate),
                Optional.ofNullable(description), Optional.ofNullable(annotations), roleID,
                Optional.ofNullable(process), Optional.ofNullable(readDefinition), Optional.ofNullable(read),
                Optional.ofNullable(write));
    }

    private static List<MiningStructureColumn> getMiningStructureColumnList(NodeList nl) {
        List<MiningStructureColumn> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("MiningStructurePermission".equals(node.getNodeName()))) {
                list.add(getMiningStructureColumn(node.getChildNodes(), getNodeType(node)));
            }
        }
        return list;
    }

    private static MiningStructureColumn getMiningStructureColumn(NodeList nl, String type) {
        if ("ScalarMiningStructureColumn".equals(type)) {
            return getScalarMiningStructureColumn(nl);
        }
        if ("TableMiningStructureColumn".equals(type)) {
            return getTableMiningStructureColumn(nl);
        }
        return null;
    }

    private static MiningStructureColumn getTableMiningStructureColumn(NodeList nl) {
        List<DataItem> foreignKeyColumns = null;
        MeasureGroupBinding sourceMeasureGroup = null;
        List<MiningStructureColumn> columns = null;
        List<Translation> translations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("ForeignKeyColumns".equals(node.getNodeName())) {
                    foreignKeyColumns = CommonConverter.getDataItemList(node.getChildNodes(), "ForeignKeyColumn");
                }
                if ("SourceMeasureGroup".equals(node.getNodeName())) {
                    sourceMeasureGroup = CubeConverter.getMeasureGroupBinding(node.getChildNodes());
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getMiningStructureColumnList(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
            }
        }
        return new TableMiningStructureColumnR(Optional.ofNullable(foreignKeyColumns),
                Optional.ofNullable(sourceMeasureGroup), Optional.ofNullable(columns),
                Optional.ofNullable(translations));
    }

    private static MiningStructureColumn getScalarMiningStructureColumn(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String type = null;
        List<Annotation> annotations = null;
        Boolean isKey = null;
        Binding source = null;
        String distribution = null;
        List<MiningModelingFlag> modelingFlags = null;
        String content = null;
        List<String> classifiedColumns = null;
        String discretizationMethod = null;
        BigInteger discretizationBucketCount = null;
        List<DataItem> keyColumns = null;
        DataItem nameColumn = null;
        List<Translation> translations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if ("Type".equals(node.getNodeName())) {
                    type = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if ("IsKey".equals(node.getNodeName())) {
                    isKey = toBoolean(node.getTextContent());
                }
                if (SOURCE.equals(node.getNodeName())) {
                    source = BindingConverter.getBinding(node.getChildNodes(), getNodeType(node));
                }
                if ("Distribution".equals(node.getNodeName())) {
                    distribution = node.getTextContent();
                }
                if ("ModelingFlags".equals(node.getNodeName())) {
                    modelingFlags = getMiningModelingFlagList(node.getChildNodes());
                }
                if ("Content".equals(node.getNodeName())) {
                    content = node.getTextContent();
                }
                if ("ClassifiedColumns".equals(node.getNodeName())) {
                    classifiedColumns = getClassifiedColumnList(node.getChildNodes());
                }
                if ("DiscretizationMethod".equals(node.getNodeName())) {
                    discretizationMethod = node.getTextContent();
                }
                if ("DiscretizationBucketCount".equals(node.getNodeName())) {
                    discretizationBucketCount = toBigInteger(node.getTextContent());
                }
                if (KEY_COLUMNS.equals(node.getNodeName())) {
                    keyColumns = CommonConverter.getDataItemList(node.getChildNodes(), KEY_COLUMN);
                }
                if ("NameColumn".equals(node.getNodeName())) {
                    nameColumn = CommonConverter.getDataItem(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
            }
        }
        return new ScalarMiningStructureColumnR(name, Optional.ofNullable(id), Optional.ofNullable(description),
                Optional.ofNullable(type), Optional.ofNullable(annotations), Optional.ofNullable(isKey),
                Optional.ofNullable(source), Optional.ofNullable(distribution), Optional.ofNullable(modelingFlags),
                content, Optional.ofNullable(classifiedColumns), Optional.ofNullable(discretizationMethod),
                Optional.ofNullable(discretizationBucketCount), Optional.ofNullable(keyColumns),
                Optional.ofNullable(nameColumn), Optional.ofNullable(translations));
    }

    private static List<String> getClassifiedColumnList(NodeList nl) {
        return getStringList(nl, "ClassifiedColumn");
    }

    public static MiningModel getMiningModel(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String algorithm = null;
        Instant lastProcessed = null;
        List<AlgorithmParameter> algorithmParameters = null;
        Boolean allowDrillThrough = null;
        List<AttributeTranslation> translations = null;
        List<MiningModelColumn> columns = null;
        String state = null;
        FoldingParameters foldingParameters = null;
        String filter = null;
        List<MiningModelPermission> miningModelPermissions = null;
        String language = null;
        String collation = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("Algorithm".equals(node.getNodeName())) {
                    algorithm = node.getTextContent();
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if ("AlgorithmParameters".equals(node.getNodeName())) {
                    algorithmParameters = getAlgorithmParameterList(node.getChildNodes());
                }
                if (ALLOW_DRILL_THROUGH.equals(node.getNodeName())) {
                    allowDrillThrough = toBoolean(node.getTextContent());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getAttributeTranslationList(node.getChildNodes());
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getMiningModelColumnList(node.getChildNodes());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("FoldingParameters".equals(node.getNodeName())) {
                    foldingParameters = getFoldingParameters(node.getChildNodes());
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = node.getTextContent();
                }
                if ("MiningModelPermissions".equals(node.getNodeName())) {
                    miningModelPermissions = getMiningModelPermissionList(node.getChildNodes());
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = node.getTextContent();
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
            }
        }
        return new MiningModelR(name, Optional.ofNullable(id), Optional.ofNullable(createdTimestamp),
                Optional.ofNullable(lastSchemaUpdate), Optional.ofNullable(description),
                Optional.ofNullable(annotations), algorithm, Optional.ofNullable(lastProcessed),
                Optional.ofNullable(algorithmParameters), Optional.ofNullable(allowDrillThrough),
                Optional.ofNullable(translations), Optional.ofNullable(columns), Optional.ofNullable(state),
                Optional.ofNullable(foldingParameters), Optional.ofNullable(filter),
                Optional.ofNullable(miningModelPermissions), Optional.ofNullable(language),
                Optional.ofNullable(collation));
    }

    private static List<MiningModelPermission> getMiningModelPermissionList(NodeList nl) {
        return getList(nl, "MiningModelPermission", childNl -> getMiningModelPermission(childNl));
    }

    private static MiningModelPermission getMiningModelPermission(NodeList nl) {
        Boolean allowDrillThrough = null;
        Boolean allowBrowsing = null;
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
                if (ALLOW_DRILL_THROUGH.equals(node.getNodeName())) {
                    allowDrillThrough = toBoolean(node.getTextContent());
                }
                if ("AllowBrowsing".equals(node.getNodeName())) {
                    allowBrowsing = toBoolean(node.getTextContent());
                }
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
            }
        }
        return new MiningModelPermissionR(Optional.ofNullable(allowDrillThrough), Optional.ofNullable(allowBrowsing),
                name, Optional.ofNullable(id), Optional.ofNullable(createdTimestamp),
                Optional.ofNullable(lastSchemaUpdate), Optional.ofNullable(description),
                Optional.ofNullable(annotations), roleID, Optional.ofNullable(process),
                Optional.ofNullable(readDefinition), Optional.ofNullable(read), Optional.ofNullable(write));
    }

    private static FoldingParameters getFoldingParameters(NodeList nl) {
        Integer foldIndex = null;
        Integer foldCount = null;
        Long foldMaxCases = null;
        String foldTargetAttribute = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("FoldIndex".equals(node.getNodeName())) {
                    foldIndex = toInteger(node.getTextContent());
                }
                if ("FoldCount".equals(node.getNodeName())) {
                    foldCount = toInteger(node.getTextContent());
                }
                if ("FoldMaxCases".equals(node.getNodeName())) {
                    foldMaxCases = toLong(node.getTextContent());
                }
                if ("FoldTargetAttribute".equals(node.getNodeName())) {
                    foldTargetAttribute = node.getTextContent();
                }
            }
        }
        return new FoldingParametersR(foldIndex, foldCount, Optional.ofNullable(foldMaxCases),
                Optional.ofNullable(foldTargetAttribute));
    }

    private static List<MiningModelColumn> getMiningModelColumnList(NodeList nl) {
        return getList(nl, "MiningModelColumn", childNl -> getMiningModelColumn(childNl));
    }

    private static MiningModelColumn getMiningModelColumn(NodeList nl) {
        String name = null;
        String id = null;
        String description = null;
        String sourceColumnID = null;
        String usage = null;
        String filter = null;
        List<Translation> translations = null;
        List<MiningModelColumn> columns = null;
        List<MiningModelingFlag> modelingFlags = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if ("SourceColumnID".equals(node.getNodeName())) {
                    sourceColumnID = node.getTextContent();
                }
                if ("Usage".equals(node.getNodeName())) {
                    usage = node.getTextContent();
                }
                if (FILTER.equals(node.getNodeName())) {
                    filter = node.getTextContent();
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (COLUMNS.equals(node.getNodeName())) {
                    columns = getMiningModelColumnList(node.getChildNodes());
                }
                if ("ModelingFlags".equals(node.getNodeName())) {
                    modelingFlags = getMiningModelingFlagList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }

        return new MiningModelColumnR(name, Optional.ofNullable(id), Optional.ofNullable(description),
                Optional.ofNullable(sourceColumnID), Optional.ofNullable(usage), Optional.ofNullable(filter),
                Optional.ofNullable(translations), Optional.ofNullable(columns), Optional.ofNullable(modelingFlags),
                Optional.ofNullable(annotations));
    }

    private static List<MiningModelingFlag> getMiningModelingFlagList(NodeList nl) {
        return getList(nl, "ModelingFlag", MiningConverter::getMiningModelingFlag);
    }

    private static MiningModelingFlag getMiningModelingFlag(NodeList nl) {
        String modelingFlag = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("ModelingFlag".equals(node.getNodeName()))) {
                modelingFlag = node.getTextContent();
                break;
            }
        }
        return new MiningModelingFlagR(Optional.ofNullable(modelingFlag));
    }

    private static List<AlgorithmParameter> getAlgorithmParameterList(NodeList nl) {
        List<AlgorithmParameter> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("AlgorithmParameter".equals(node.getNodeName()))) {
                list.add(getAlgorithmParameter(node.getChildNodes()));
            }
        }
        return list;
    }

    private static AlgorithmParameter getAlgorithmParameter(NodeList nl) {
        String name = null;
        java.lang.Object value = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (VALUE.equals(node.getNodeName())) {
                    value = node.getTextContent();
                }
            }
        }
        return new AlgorithmParameterR(name, value);
    }
}
