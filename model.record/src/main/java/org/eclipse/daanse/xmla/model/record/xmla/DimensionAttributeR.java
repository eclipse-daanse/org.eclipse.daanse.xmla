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
package org.eclipse.daanse.xmla.model.record.xmla;

import java.math.BigInteger;
import java.util.List;

import org.eclipse.daanse.xmla.api.engine300.AttributeHierarchyProcessingState;
import org.eclipse.daanse.xmla.api.engine300.DimensionAttributeVisualizationProperties;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.AttributeRelationship;
import org.eclipse.daanse.xmla.api.xmla.AttributeTranslation;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.DataItem;
import org.eclipse.daanse.xmla.api.xmla.DimensionAttribute;
import org.eclipse.daanse.xmla.api.xmla.DimensionAttributeTypeEnumType;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record DimensionAttributeR(String name, String id, String description, DimensionAttribute.Type type,
        String usage, Binding source, Long estimatedCount, List<DataItem> keyColumns, DataItem nameColumn,
        DataItem valueColumn, List<AttributeTranslation> translations,
        List<AttributeRelationship> attributeRelationships, String discretizationMethod,
        BigInteger discretizationBucketCount, String rootMemberIf, String orderBy, String defaultMember,
        String orderByAttributeID, DataItem skippedLevelsColumn, String namingTemplate, String membersWithData,
        String membersWithDataCaption, List<Translation> namingTemplateTranslations, DataItem customRollupColumn,
        DataItem customRollupPropertiesColumn, DataItem unaryOperatorColumn, Boolean attributeHierarchyOrdered,
        Boolean memberNamesUnique, Boolean isAggregatable, Boolean attributeHierarchyEnabled,
        String attributeHierarchyOptimizedState, Boolean attributeHierarchyVisible,
        String attributeHierarchyDisplayFolder, Boolean keyUniquenessGuarantee, String groupingBehavior,
        String instanceSelection, List<Annotation> annotations, String processingState,
        AttributeHierarchyProcessingState attributeHierarchyProcessingState,
        DimensionAttributeVisualizationProperties visualizationProperties, String extendedType)
        implements DimensionAttribute {

    public record Type(DimensionAttributeTypeEnumType value, String valuens) implements DimensionAttribute.Type {

    }

}
