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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import static org.eclipse.daanse.xmla.server.jakarta.jws.AnnotationConvertor.convertAnnotationList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.BindingConvertor.convertBinding;
import static org.eclipse.daanse.xmla.server.jakarta.jws.CommandConvertor.convertErrorConfiguration;
import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertToInstant;
import static org.eclipse.daanse.xmla.server.jakarta.jws.CubeConvertor.convertMeasureGroupBinding;
import static org.eclipse.daanse.xmla.server.jakarta.jws.CubeConvertor.convertTranslationList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.DataItemConvertor.convertDataItem;
import static org.eclipse.daanse.xmla.server.jakarta.jws.DataItemConvertor.convertDataItemList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.MiningModelConvertor.convertMiningModelingFlagList;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.MiningModel;
import org.eclipse.daanse.xmla.api.xmla.MiningStructure;
import org.eclipse.daanse.xmla.api.xmla.MiningStructureColumn;
import org.eclipse.daanse.xmla.api.xmla.MiningStructurePermission;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.model.record.xmla.MiningStructurePermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningStructureR;
import org.eclipse.daanse.xmla.model.record.xmla.ScalarMiningStructureColumnR;
import org.eclipse.daanse.xmla.model.record.xmla.TableMiningStructureColumnR;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ScalarMiningStructureColumn;

public class MiningStructureConvertor {

    private MiningStructureConvertor() {
    }

    public static MiningStructure convertMiningStructure(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningStructure miningStructure) {
        if (miningStructure != null) {
            return new MiningStructureR(miningStructure.getName(),
                Optional.ofNullable(miningStructure.getID()),
                Optional.ofNullable(convertToInstant(miningStructure.getCreatedTimestamp())),
                Optional.ofNullable(convertToInstant(miningStructure.getLastSchemaUpdate())),
                Optional.ofNullable(miningStructure.getDescription()),
                Optional.ofNullable(convertAnnotationList(miningStructure.getAnnotations() == null ? null :
                    miningStructure.getAnnotations())),
                Optional.ofNullable(convertBinding(miningStructure.getSource())),
                Optional.ofNullable(convertToInstant(miningStructure.getLastProcessed())),
                Optional.ofNullable(convertTranslationList(miningStructure.getTranslations())),
                Optional.ofNullable(miningStructure.getLanguage()),
                Optional.ofNullable(miningStructure.getCollation()),
                Optional.ofNullable(convertErrorConfiguration(miningStructure.getErrorConfiguration())),
                Optional.ofNullable(miningStructure.getCacheMode()),
                Optional.ofNullable(miningStructure.getHoldoutMaxPercent()),
                Optional.ofNullable(miningStructure.getHoldoutMaxCases()),
                Optional.ofNullable(miningStructure.getHoldoutSeed()),
                Optional.ofNullable(miningStructure.getHoldoutActualSize()),
                convertMiningStructureColumnList(miningStructure.getColumns()),
                Optional.ofNullable(miningStructure.getState()),
                Optional.ofNullable(convertMiningStructurePermissionList(miningStructure.getMiningStructurePermissions())),
                Optional.ofNullable(convertMiningModelList(miningStructure.getMiningModels())));
        }
        return null;
    }

    private static List<MiningModel> convertMiningModelList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningModel> list) {
        if (list != null) {
            return list.stream().map(MiningModelConvertor::convertMiningModel).toList();
        }
        return List.of();
    }

    private static List<MiningStructurePermission> convertMiningStructurePermissionList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningStructurePermission> list) {
        if (list != null) {
            return list.stream().map(MiningStructureConvertor::convertMiningStructurePermission).toList();
        }
        return List.of();
    }

    private static MiningStructurePermission convertMiningStructurePermission(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningStructurePermission miningStructurePermission) {
        if (miningStructurePermission != null) {
            return new MiningStructurePermissionR(Optional.ofNullable(miningStructurePermission.isAllowDrillThrough()),
                miningStructurePermission.getName(),
                Optional.ofNullable(miningStructurePermission.getID()),
                Optional.ofNullable(convertToInstant(miningStructurePermission.getCreatedTimestamp())),
                Optional.ofNullable(convertToInstant(miningStructurePermission.getLastSchemaUpdate())),
                Optional.ofNullable(miningStructurePermission.getDescription()),
                Optional.ofNullable(convertAnnotationList(miningStructurePermission.getAnnotations() == null ? null : miningStructurePermission.getAnnotations())),
                miningStructurePermission.getRoleID(),
                Optional.ofNullable(miningStructurePermission.isProcess()),
                Optional.ofNullable(ReadDefinitionEnum.fromValue(miningStructurePermission.getReadDefinition())),
                Optional.ofNullable(ReadWritePermissionEnum.fromValue(miningStructurePermission.getRead())),
                Optional.ofNullable(ReadWritePermissionEnum.fromValue(miningStructurePermission.getWrite())));
        }
        return null;
    }

    private static List<MiningStructureColumn> convertMiningStructureColumnList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningStructureColumn> list) {
        if (list != null) {
            return list.stream().map(MiningStructureConvertor::convertMiningStructureColumn).toList();
        }
        return List.of();
    }

    private static MiningStructureColumn convertMiningStructureColumn(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningStructureColumn miningStructureColumn) {
        if (miningStructureColumn != null) {
            if (miningStructureColumn instanceof org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ScalarMiningStructureColumn scalarMiningStructureColumn) {
                return convertScalarMiningStructureColumn(scalarMiningStructureColumn);
            }
            if (miningStructureColumn instanceof org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.TableMiningStructureColumn tableMiningStructureColumn) {
                return convertTableMiningStructureColumn(tableMiningStructureColumn);
            }
        }
        return null;
    }

    private static MiningStructureColumn convertScalarMiningStructureColumn(ScalarMiningStructureColumn scalarMiningStructureColumn) {
        return new ScalarMiningStructureColumnR(scalarMiningStructureColumn.getName(),
            Optional.ofNullable(scalarMiningStructureColumn.getID()),
            Optional.ofNullable(scalarMiningStructureColumn.getDescription()),
            Optional.ofNullable(scalarMiningStructureColumn.getType()),
            Optional.ofNullable(convertAnnotationList(scalarMiningStructureColumn.getAnnotations())),
            Optional.ofNullable(scalarMiningStructureColumn.isIsKey()),
            Optional.ofNullable(convertBinding(scalarMiningStructureColumn.getSource())),
            Optional.ofNullable(scalarMiningStructureColumn.getDistribution()),
            Optional.ofNullable(convertMiningModelingFlagList(scalarMiningStructureColumn.getModelingFlags())),
            scalarMiningStructureColumn.getContent(),
            Optional.ofNullable(scalarMiningStructureColumn.getClassifiedColumns()),
            Optional.ofNullable(scalarMiningStructureColumn.getDiscretizationMethod()),
            Optional.ofNullable(scalarMiningStructureColumn.getDiscretizationBucketCount()),
            Optional.ofNullable(convertDataItemList(scalarMiningStructureColumn.getKeyColumns())),
            Optional.ofNullable(convertDataItem(scalarMiningStructureColumn.getNameColumn())),
            Optional.ofNullable(convertTranslationList(scalarMiningStructureColumn.getTranslations())));
    }

    private static MiningStructureColumn convertTableMiningStructureColumn(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.TableMiningStructureColumn tableMiningStructureColumn) {
        return new TableMiningStructureColumnR(
            Optional.ofNullable(convertDataItemList(tableMiningStructureColumn.getForeignKeyColumns())),
            Optional.ofNullable(convertMeasureGroupBinding(tableMiningStructureColumn.getSourceMeasureGroup())),
            Optional.ofNullable(convertMiningStructureColumnList(tableMiningStructureColumn.getColumns())),
            Optional.ofNullable(convertTranslationList(tableMiningStructureColumn.getTranslations())));
    }

}
