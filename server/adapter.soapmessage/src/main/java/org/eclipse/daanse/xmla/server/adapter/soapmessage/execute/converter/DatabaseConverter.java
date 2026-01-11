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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.COLLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ESTIMATED_SIZE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LANGUAGE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_PROCESSED;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESSING_PRIORITY;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE2;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRANSLATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VISIBLE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getStringList;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBigInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toLong;

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.xmla.Account;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.Cube;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.Database;
import org.eclipse.daanse.xmla.api.xmla.DatabasePermission;
import org.eclipse.daanse.xmla.api.xmla.Dimension;
import org.eclipse.daanse.xmla.api.xmla.MiningStructure;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.api.xmla.Translation;
import org.eclipse.daanse.xmla.model.record.xmla.AccountR;
import org.eclipse.daanse.xmla.model.record.xmla.DatabaseR;
import org.w3c.dom.NodeList;

/**
 * Converter for Database and Account types from XML NodeList.
 */
public class DatabaseConverter {

    private DatabaseConverter() {
        // utility class
    }

    public static List<Database> getDatabaseList(NodeList nl, Function<NodeList, Command> commandParser,
            Function<NodeList, List<Assembly>> assemblySupplier) {
        List<Database> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("Database".equals(node.getNodeName()))) {
                list.add(getDatabase(node.getChildNodes(), commandParser, assemblySupplier));
            }
        }
        return list;
    }

    public static Database getDatabase(NodeList nl, Function<NodeList, Command> commandParser,
            Function<NodeList, List<Assembly>> assemblySupplier) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        Instant lastUpdate = null;
        String state = null;
        String readWriteMode = null;
        String dbStorageLocation = null;
        String aggregationPrefix = null;
        BigInteger processingPriority = null;
        Long estimatedSize = null;
        Instant lastProcessed = null;
        BigInteger language = null;
        String collation = null;
        Boolean visible = null;
        String masterDataSourceID = null;
        ImpersonationInfo dataSourceImpersonationInfo = null;
        List<Account> accounts = null;
        List<DataSource> dataSources = null;
        List<DataSourceView> dataSourceViews = null;
        List<Dimension> dimensions = null;
        List<Cube> cubes = null;
        List<MiningStructure> miningStructures = null;
        List<Role> roles = null;
        List<Assembly> assemblies = null;
        List<DatabasePermission> databasePermissions = null;
        List<Translation> translations = null;
        String storageEngineUsed = null;
        String imagePath = null;
        String imageUrl = null;
        String imageUniqueID = null;
        String imageVersion = null;
        String token = null;
        BigInteger compatibilityLevel = null;
        String directQueryMode = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
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
                if ("LastUpdate".equals(node.getNodeName())) {
                    lastUpdate = toInstant(node.getTextContent());
                }
                if (STATE.equals(node.getNodeName())) {
                    state = node.getTextContent();
                }
                if ("ReadWriteMode".equals(node.getNodeName())) {
                    readWriteMode = node.getTextContent();
                }
                if ("DbStorageLocation".equals(node.getNodeName())) {
                    dbStorageLocation = node.getTextContent();
                }
                if (AGGREGATION_PREFIX.equals(node.getNodeName())) {
                    aggregationPrefix = node.getTextContent();
                }
                if (PROCESSING_PRIORITY.equals(node.getNodeName())) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (ESTIMATED_SIZE.equals(node.getNodeName())) {
                    estimatedSize = toLong(node.getTextContent());
                }
                if (LAST_PROCESSED.equals(node.getNodeName())) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (LANGUAGE.equals(node.getNodeName())) {
                    language = toBigInteger(node.getTextContent());
                }
                if (COLLATION.equals(node.getNodeName())) {
                    collation = node.getTextContent();
                }
                if (VISIBLE.equals(node.getNodeName())) {
                    visible = toBoolean(node.getTextContent());
                }
                if ("MasterDataSourceID".equals(node.getNodeName())) {
                    masterDataSourceID = node.getTextContent();
                }
                if ("DataSourceImpersonationInfo".equals(node.getNodeName())) {
                    dataSourceImpersonationInfo = DataSourceConverter.getImpersonationInfo(node.getChildNodes());
                }
                if ("Accounts".equals(node.getNodeName())) {
                    accounts = getAccountList(node.getChildNodes());
                }
                if ("DataSources".equals(node.getNodeName())) {
                    dataSources = DataSourceConverter.getDataSourceList(node.getChildNodes());
                }
                if ("DataSourceViews".equals(node.getNodeName())) {
                    dataSourceViews = DataSourceConverter.getDataSourceViewList(node.getChildNodes());
                }
                if (DIMENSIONS.equals(node.getNodeName())) {
                    dimensions = DimensionConverter.getDimensionList(node.getChildNodes());
                }
                if ("Cubes".equals(node.getNodeName())) {
                    cubes = CubeConverter.getCubeList(node.getChildNodes(), commandParser);
                }
                if ("MiningStructures".equals(node.getNodeName())) {
                    miningStructures = MiningConverter.getMiningStructureList(node.getChildNodes());
                }
                if ("Roles".equals(node.getNodeName())) {
                    roles = RoleConverter.getRoleList(node.getChildNodes(), ROLE2);
                }
                if ("Assemblies".equals(node.getNodeName())) {
                    assemblies = assemblySupplier.apply(node.getChildNodes());
                }
                if ("DatabasePermissions".equals(node.getNodeName())) {
                    databasePermissions = PermissionConverter.getDatabasePermissionList(node.getChildNodes());
                }
                if (TRANSLATIONS.equals(node.getNodeName())) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if ("StorageEngineUsed".equals(node.getNodeName())) {
                    storageEngineUsed = node.getTextContent();
                }
                if ("ImagePath".equals(node.getNodeName())) {
                    imagePath = node.getTextContent();
                }
                if ("ImageUrl".equals(node.getNodeName())) {
                    imageUrl = node.getTextContent();
                }
                if ("ImageUniqueID".equals(node.getNodeName())) {
                    imageUniqueID = node.getTextContent();
                }
                if ("ImageVersion".equals(node.getNodeName())) {
                    imageVersion = node.getTextContent();
                }
                if ("Token".equals(node.getNodeName())) {
                    token = node.getTextContent();
                }
                if ("CompatibilityLevel".equals(node.getNodeName())) {
                    compatibilityLevel = toBigInteger(node.getTextContent());
                }
                if ("DirectQueryMode".equals(node.getNodeName())) {
                    directQueryMode = node.getTextContent();
                }
            }
        }
        return new DatabaseR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, lastUpdate, state,
                readWriteMode, dbStorageLocation, aggregationPrefix, processingPriority, estimatedSize, lastProcessed,
                language, collation, visible, masterDataSourceID, dataSourceImpersonationInfo, accounts, dataSources,
                dataSourceViews, dimensions, cubes, miningStructures, roles, assemblies, databasePermissions,
                translations, storageEngineUsed, imagePath, imageUrl, imageUniqueID, imageVersion, token,
                compatibilityLevel, directQueryMode);
    }

    public static List<Account> getAccountList(NodeList nl) {
        List<Account> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("Account".equals(node.getNodeName()))) {
                list.add(getAccount(node.getChildNodes()));
            }
        }
        return list;
    }

    public static Account getAccount(NodeList nl) {
        String accountType = null;
        String aggregationFunction = null;
        List<String> aliases = null;
        List<Annotation> annotations = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("AccountType".equals(node.getNodeName())) {
                    accountType = node.getTextContent();
                }
                if ("AggregationFunction".equals(node.getNodeName())) {
                    aggregationFunction = node.getTextContent();
                }
                if ("Aliases".equals(node.getNodeName())) {
                    aliases = getAliasList(node.getChildNodes());
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AccountR(accountType, aggregationFunction, aliases, annotations);
    }

    private static List<String> getAliasList(NodeList nl) {
        return getStringList(nl, "Alias");
    }
}
