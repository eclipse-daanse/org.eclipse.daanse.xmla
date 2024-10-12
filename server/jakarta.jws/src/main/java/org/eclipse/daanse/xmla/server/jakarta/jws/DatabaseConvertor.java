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
*/
package org.eclipse.daanse.xmla.server.jakarta.jws;

import static org.eclipse.daanse.xmla.server.jakarta.jws.AnnotationConvertor.convertAnnotationList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.CommandConvertor.convertImpersonationInfo;
import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertToInstant;
import static org.eclipse.daanse.xmla.server.jakarta.jws.CubeConvertor.convertTranslationList;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Account;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Cube;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.Database;
import org.eclipse.daanse.xmla.api.xmla.DatabasePermission;
import org.eclipse.daanse.xmla.api.xmla.Dimension;
import org.eclipse.daanse.xmla.api.xmla.MiningStructure;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.model.record.xmla.AccountR;
import org.eclipse.daanse.xmla.model.record.xmla.DatabasePermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.DatabaseR;

public class DatabaseConvertor {

    private DatabaseConvertor() {
    }
    public static Database convertDatabase(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Database database) {
        if (database != null) {
            return new DatabaseR(database.getName(),
                database.getID(),
                convertToInstant(database.getCreatedTimestamp()),
                convertToInstant(database.getLastSchemaUpdate()),
                database.getDescription(),
                convertAnnotationList(database.getAnnotations() == null ?
                    null : database.getAnnotations()),
                convertToInstant(database.getLastUpdate()),
                database.getState(),
                database.getReadWriteMode(),
                database.getDbStorageLocation(),
                database.getAggregationPrefix(),
                database.getProcessingPriority(),
                database.getEstimatedSize(),
                convertToInstant(database.getLastProcessed()),
                database.getLanguage(),
                database.getCollation(),
                database.isVisible(),
                database.getMasterDataSourceID(),
                convertImpersonationInfo(database.getDataSourceImpersonationInfo()),
                convertAccountList(database.getAccounts()),
                convertDataSourceList(database.getDataSources()),
                convertDataSourceViewList(database.getDataSourceViews()),
                convertDimensionList(database.getDimensions()),
                convertCubeList(database.getCubes()),
                convertMiningStructureList(database.getMiningStructures()),
                convertRoleList(database.getRoles()),
                convertAssemblyList(database.getAssemblies()),
                convertDatabasePermissionList(database.getDatabasePermissions()),
                convertTranslationList(database.getTranslations()),
                database.getStorageEngineUsed(),
                database.getImagePath(),
                database.getImageUrl(),
                database.getImageUniqueID(),
                database.getImageVersion(),
                database.getToken(),
                database.getCompatibilityLevel(),
                database.getDirectQueryMode());
        }
        return null;
    }

    private static List<DatabasePermission> convertDatabasePermissionList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DatabasePermission> list) {
        if (list != null) {
            return list.stream().map(DatabaseConvertor::convertDatabasePermission).toList();
        }
        return List.of();
    }

    private static DatabasePermission convertDatabasePermission(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DatabasePermission databasePermission) {
        if (databasePermission != null) {
            return new DatabasePermissionR(Optional.ofNullable(databasePermission.isAdminister()),
                databasePermission.getName(),
                Optional.ofNullable(databasePermission.getID()),
                Optional.ofNullable(convertToInstant(databasePermission.getCreatedTimestamp())),
                Optional.ofNullable(convertToInstant(databasePermission.getLastSchemaUpdate())),
                Optional.ofNullable(databasePermission.getDescription()),
                Optional.ofNullable(convertAnnotationList(databasePermission.getAnnotations() == null ? null : databasePermission.getAnnotations())),
                databasePermission.getRoleID(),
                Optional.ofNullable(databasePermission.isProcess()),
                Optional.ofNullable(ReadDefinitionEnum.fromValue(databasePermission.getReadDefinition())),
                Optional.ofNullable(ReadWritePermissionEnum.fromValue(databasePermission.getRead())),
                Optional.ofNullable(ReadWritePermissionEnum.fromValue(databasePermission.getWrite())));
        }
        return null;
    }

    public static List<Assembly> convertAssemblyList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Assembly> list) {
        if (list != null) {
            return list.stream().map(MajorObjectConvertor::convertAssembly).toList();
        }
        return List.of();
    }

    private static List<Role> convertRoleList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Role> list) {
        if (list != null) {
            return list.stream().map(RoleConvertor::convertRole).toList();
        }
        return List.of();
    }

    private static List<MiningStructure> convertMiningStructureList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningStructure> list) {
        if (list != null) {
            return list.stream().map(MiningStructureConvertor::convertMiningStructure).toList();
        }
        return List.of();
    }

    private static List<Cube> convertCubeList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Cube> list) {
        if (list != null) {
            return list.stream().map(CubeConvertor::convertCube).toList();
        }
        return List.of();
    }

    private static List<Dimension> convertDimensionList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Dimension> list) {
        if (list != null) {
            return list.stream().map(DimensionConvertor::convertDimension).toList();
        }
        return List.of();
    }

    private static List<DataSourceView> convertDataSourceViewList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataSourceView> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertDataSourceView).toList();
        }
        return List.of();
    }

    private static List<DataSource> convertDataSourceList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataSource> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertDataSource).toList();
        }
        return List.of();
    }

    private static List<Account> convertAccountList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Account> list) {
        if (list != null) {
            return list.stream().map(DatabaseConvertor::convertAccount).toList();
        }
        return List.of();
    }

    private static Account convertAccount(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Account account) {
        if (account != null) {
            return new AccountR(account.getAccountType(),
                account.getAggregationFunction(),
                account.getAliases(),
                convertAnnotationList(account.getAnnotations()));
        }
        return null;
    }

    public static List<Database> convertDatabaseList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Database> list) {
        if (list != null) {
            return list.stream().map(DatabaseConvertor::convertDatabase).toList();
        }
        return List.of();
    }
}
