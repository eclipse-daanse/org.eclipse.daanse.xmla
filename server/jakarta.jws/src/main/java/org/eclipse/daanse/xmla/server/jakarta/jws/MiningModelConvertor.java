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
import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertToInstant;
import static org.eclipse.daanse.xmla.server.jakarta.jws.CubeConvertor.convertTranslationList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.DataItemConvertor.convertDataItem;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.AlgorithmParameter;
import org.eclipse.daanse.xmla.api.xmla.AttributeTranslation;
import org.eclipse.daanse.xmla.api.xmla.FoldingParameters;
import org.eclipse.daanse.xmla.api.xmla.MiningModel;
import org.eclipse.daanse.xmla.api.xmla.MiningModelColumn;
import org.eclipse.daanse.xmla.api.xmla.MiningModelPermission;
import org.eclipse.daanse.xmla.api.xmla.MiningModelingFlag;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.model.record.xmla.AlgorithmParameterR;
import org.eclipse.daanse.xmla.model.record.xmla.AttributeTranslationR;
import org.eclipse.daanse.xmla.model.record.xmla.FoldingParametersR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningModelColumnR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningModelPermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningModelR;
import org.eclipse.daanse.xmla.model.record.xmla.MiningModelingFlagR;

public class MiningModelConvertor {

    private MiningModelConvertor() {
    }

    public static MiningModel convertMiningModel(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningModel miningModel) {
        if (miningModel != null) {
            return new MiningModelR(miningModel.getName(),
                Optional.ofNullable(miningModel.getID()),
                Optional.ofNullable(convertToInstant(miningModel.getCreatedTimestamp())),
                Optional.ofNullable(convertToInstant(miningModel.getLastSchemaUpdate())),
                Optional.ofNullable(miningModel.getDescription()),
                Optional.ofNullable(convertAnnotationList(miningModel.getAnnotations() == null ? null :
                    miningModel.getAnnotations())),
                miningModel.getAlgorithm(),
                Optional.ofNullable(convertToInstant(miningModel.getLastProcessed())),
                Optional.ofNullable(convertAlgorithmParameterList(miningModel.getAlgorithmParameters() == null ? null :
                    miningModel.getAlgorithmParameters())),
                Optional.ofNullable(miningModel.isAllowDrillThrough()),
                Optional.ofNullable(convertAttributeTranslationList(miningModel.getTranslations() == null ? null :
                    miningModel.getTranslations())),
                Optional.ofNullable(convertMiningModelColumnList(miningModel.getColumns())),
                Optional.ofNullable(miningModel.getState()),
                Optional.ofNullable(convertFoldingParameters(miningModel.getFoldingParameters())),
                Optional.ofNullable(miningModel.getFilter()),
                Optional.ofNullable(convertMiningModelPermissionList(miningModel.getMiningModelPermissions())),
                Optional.ofNullable(miningModel.getLanguage()),
                Optional.ofNullable(miningModel.getCollation()));
        }
        return null;
    }

    private static List<MiningModelPermission> convertMiningModelPermissionList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningModelPermission> list) {
        if (list != null) {
            return list.stream().map(MiningModelConvertor::convertMiningModelPermission).toList();
        }
        return List.of();
    }

    private static MiningModelPermission convertMiningModelPermission(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningModelPermission miningModelPermission) {
        if (miningModelPermission != null) {
            return new MiningModelPermissionR(Optional.ofNullable(miningModelPermission.isAllowDrillThrough()),
                Optional.ofNullable(miningModelPermission.isAllowBrowsing()),
                miningModelPermission.getName(),
                Optional.ofNullable(miningModelPermission.getID()),
                Optional.ofNullable(convertToInstant(miningModelPermission.getCreatedTimestamp())),
                Optional.ofNullable(convertToInstant(miningModelPermission.getLastSchemaUpdate())),
                Optional.ofNullable(miningModelPermission.getDescription()),
                Optional.ofNullable(convertAnnotationList(miningModelPermission.getAnnotations() == null ? null :
                    miningModelPermission.getAnnotations())),
                miningModelPermission.getRoleID(),
                Optional.ofNullable(miningModelPermission.isProcess()),
                Optional.ofNullable(ReadDefinitionEnum.fromValue(miningModelPermission.getReadDefinition())),
                Optional.ofNullable(ReadWritePermissionEnum.fromValue(miningModelPermission.getRead())),
                Optional.ofNullable(ReadWritePermissionEnum.fromValue(miningModelPermission.getWrite())));
        }
        return null;
    }

    private static List<MiningModelColumn> convertMiningModelColumnList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningModelColumn> list) {
        if (list != null) {
            return list.stream().map(MiningModelConvertor::convertMiningModelColumn).toList();
        }
        return List.of();
    }

    private static MiningModelColumn convertMiningModelColumn(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningModelColumn miningModelColumn) {
        if (miningModelColumn != null) {
            return new MiningModelColumnR(miningModelColumn.getName(),
                Optional.ofNullable(miningModelColumn.getID()),
                Optional.ofNullable(miningModelColumn.getDescription()),
                Optional.ofNullable(miningModelColumn.getSourceColumnID()),
                Optional.ofNullable(miningModelColumn.getUsage()),
                Optional.ofNullable(miningModelColumn.getFilter()),
                Optional.ofNullable(convertTranslationList(miningModelColumn.getTranslations())),
                Optional.ofNullable(convertMiningModelColumnList(miningModelColumn.getColumns())),
                Optional.ofNullable(convertMiningModelingFlagList(miningModelColumn.getModelingFlags())),
                Optional.ofNullable(convertAnnotationList(miningModelColumn.getAnnotations())));
        }
        return null;

    }

    public static List<MiningModelingFlag> convertMiningModelingFlagList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningModelingFlag> list) {
        if (list != null) {
            return list.stream().map(MiningModelConvertor::convertMiningModelingFlag).toList();
        }
        return List.of();
    }

    private static MiningModelingFlag convertMiningModelingFlag(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MiningModelingFlag miningModelingFlag) {
        if (miningModelingFlag != null) {
            return new MiningModelingFlagR(Optional.ofNullable(miningModelingFlag.getModelingFlag()));
        }
        return null;
    }

    public static List<AttributeTranslation> convertAttributeTranslationList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.AttributeTranslation> list) {
        if (list != null) {
            return list.stream().map(MiningModelConvertor::convertAttributeTranslation).toList();
        }
        return List.of();
    }

    private static AttributeTranslation convertAttributeTranslation(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.AttributeTranslation attributeTranslation) {
        if (attributeTranslation != null) {
            return new AttributeTranslationR(attributeTranslation.getLanguage(),
                Optional.ofNullable(attributeTranslation.getCaption()),
                Optional.ofNullable(attributeTranslation.getDescription()),
                Optional.ofNullable(attributeTranslation.getDisplayFolder()),
                Optional.ofNullable(convertAnnotationList(attributeTranslation.getAnnotations())),
                Optional.ofNullable(convertDataItem(attributeTranslation.getCaptionColumn())),
                Optional.ofNullable(attributeTranslation.getMembersWithDataCaption()));
        }
        return null;
    }

    private static List<AlgorithmParameter> convertAlgorithmParameterList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.AlgorithmParameter> list) {
        if (list != null) {
            return list.stream().map(MiningModelConvertor::convertAlgorithmParameter).toList();
        }
        return List.of();
    }

    private static AlgorithmParameter convertAlgorithmParameter(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.AlgorithmParameter algorithmParameter) {
        if (algorithmParameter != null) {
            return new AlgorithmParameterR(algorithmParameter.getName(),
                algorithmParameter.getValue());
        }
        return null;
    }

    private static FoldingParameters convertFoldingParameters(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.FoldingParameters foldingParameters) {
        if (foldingParameters != null) {
            return new FoldingParametersR(foldingParameters.getFoldIndex(),
                foldingParameters.getFoldCount(),
                Optional.ofNullable(foldingParameters.getFoldMaxCases()),
                Optional.ofNullable(foldingParameters.getFoldTargetAttribute()));
        }
        return null;
    }
}
