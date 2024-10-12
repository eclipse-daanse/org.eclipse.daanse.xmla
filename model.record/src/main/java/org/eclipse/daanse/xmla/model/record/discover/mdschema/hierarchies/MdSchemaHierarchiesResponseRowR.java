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
package org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.DimensionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionUniqueSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.GroupingBehaviorEnum;
import org.eclipse.daanse.xmla.api.common.enums.HierarchyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.InstanceSelectionEnum;
import org.eclipse.daanse.xmla.api.common.enums.StructureEnum;
import org.eclipse.daanse.xmla.api.common.enums.StructureTypeEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;

public record MdSchemaHierarchiesResponseRowR(Optional<String> catalogName, Optional<String> schemaName,
        Optional<String> cubeName, Optional<String> dimensionUniqueName, Optional<String> hierarchyName,
        Optional<String> hierarchyUniqueName, Optional<Integer> hierarchyGuid, Optional<String> hierarchyCaption,
        Optional<DimensionTypeEnum> dimensionType, Optional<Integer> hierarchyCardinality,
        Optional<String> defaultMember, Optional<String> allMember, Optional<String> description,
        Optional<StructureEnum> structure, Optional<Boolean> isVirtual, Optional<Boolean> isReadWrite,
        Optional<DimensionUniqueSettingEnum> dimensionUniqueSettings, Optional<String> dimensionMasterUniqueName,
        Optional<Boolean> dimensionIsVisible, Optional<Integer> hierarchyOrdinal, Optional<Boolean> dimensionIsShared,
        Optional<Boolean> hierarchyIsVisible, Optional<HierarchyOriginEnum> hierarchyOrigin,
        Optional<String> hierarchyDisplayFolder, Optional<InstanceSelectionEnum> instanceSelection,
        Optional<GroupingBehaviorEnum> groupingBehavior, Optional<StructureTypeEnum> structureType)
        implements MdSchemaHierarchiesResponseRow {

}
