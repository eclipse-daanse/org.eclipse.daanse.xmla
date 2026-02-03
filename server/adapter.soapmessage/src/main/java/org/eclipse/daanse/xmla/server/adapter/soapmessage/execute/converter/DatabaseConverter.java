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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getStringListByLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
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
            if (matchesLocalName(node, "Database")) {
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
                if (matchesLocalName(node, "LastUpdate")) {
                    lastUpdate = toInstant(node.getTextContent());
                }
                if (matchesLocalName(node, STATE)) {
                    state = node.getTextContent();
                }
                if (matchesLocalName(node, "ReadWriteMode")) {
                    readWriteMode = node.getTextContent();
                }
                if (matchesLocalName(node, "DbStorageLocation")) {
                    dbStorageLocation = node.getTextContent();
                }
                if (matchesLocalName(node, AGGREGATION_PREFIX)) {
                    aggregationPrefix = node.getTextContent();
                }
                if (matchesLocalName(node, PROCESSING_PRIORITY)) {
                    processingPriority = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, ESTIMATED_SIZE)) {
                    estimatedSize = toLong(node.getTextContent());
                }
                if (matchesLocalName(node, LAST_PROCESSED)) {
                    lastProcessed = toInstant(node.getTextContent());
                }
                if (matchesLocalName(node, LANGUAGE)) {
                    language = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, COLLATION)) {
                    collation = node.getTextContent();
                }
                if (matchesLocalName(node, VISIBLE)) {
                    visible = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "MasterDataSourceID")) {
                    masterDataSourceID = node.getTextContent();
                }
                if (matchesLocalName(node, "DataSourceImpersonationInfo")) {
                    dataSourceImpersonationInfo = DataSourceConverter.getImpersonationInfo(node.getChildNodes());
                }
                if (matchesLocalName(node, "Accounts")) {
                    accounts = getAccountList(node.getChildNodes());
                }
                if (matchesLocalName(node, "DataSources")) {
                    dataSources = DataSourceConverter.getDataSourceList(node.getChildNodes());
                }
                if (matchesLocalName(node, "DataSourceViews")) {
                    dataSourceViews = DataSourceConverter.getDataSourceViewList(node.getChildNodes());
                }
                if (matchesLocalName(node, DIMENSIONS)) {
                    dimensions = DimensionConverter.getDimensionList(node.getChildNodes());
                }
                if (matchesLocalName(node, "Cubes")) {
                    cubes = CubeConverter.getCubeList(node.getChildNodes(), commandParser);
                }
                if (matchesLocalName(node, "MiningStructures")) {
                    miningStructures = MiningConverter.getMiningStructureList(node.getChildNodes());
                }
                if (matchesLocalName(node, "Roles")) {
                    roles = RoleConverter.getRoleList(node.getChildNodes(), ROLE2);
                }
                if (matchesLocalName(node, "Assemblies")) {
                    assemblies = assemblySupplier.apply(node.getChildNodes());
                }
                if (matchesLocalName(node, "DatabasePermissions")) {
                    databasePermissions = PermissionConverter.getDatabasePermissionList(node.getChildNodes());
                }
                if (matchesLocalName(node, TRANSLATIONS)) {
                    translations = CommonConverter.getTranslationList(node.getChildNodes(), TRANSLATION);
                }
                if (matchesLocalName(node, "StorageEngineUsed")) {
                    storageEngineUsed = node.getTextContent();
                }
                if (matchesLocalName(node, "ImagePath")) {
                    imagePath = node.getTextContent();
                }
                if (matchesLocalName(node, "ImageUrl")) {
                    imageUrl = node.getTextContent();
                }
                if (matchesLocalName(node, "ImageUniqueID")) {
                    imageUniqueID = node.getTextContent();
                }
                if (matchesLocalName(node, "ImageVersion")) {
                    imageVersion = node.getTextContent();
                }
                if (matchesLocalName(node, "Token")) {
                    token = node.getTextContent();
                }
                if (matchesLocalName(node, "CompatibilityLevel")) {
                    compatibilityLevel = toBigInteger(node.getTextContent());
                }
                if (matchesLocalName(node, "DirectQueryMode")) {
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
            if (matchesLocalName(node, "Account")) {
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
                if (matchesLocalName(node, "AccountType")) {
                    accountType = node.getTextContent();
                }
                if (matchesLocalName(node, "AggregationFunction")) {
                    aggregationFunction = node.getTextContent();
                }
                if (matchesLocalName(node, "Aliases")) {
                    aliases = getAliasList(node.getChildNodes());
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
            }
        }
        return new AccountR(accountType, aggregationFunction, aliases, annotations);
    }

    private static List<String> getAliasList(NodeList nl) {
        return getStringListByLocalName(nl, "Alias");
    }
}
