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
package org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.DimensionCardinalityEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MeasureGroupDimension;

public record MdSchemaMeasureGroupDimensionsResponseRowR(Optional<String> catalogName, Optional<String> schemaName,
        Optional<String> cubeName, Optional<String> measureGroupName, Optional<String> measureGroupCardinality,
        Optional<String> dimensionUniqueName, Optional<DimensionCardinalityEnum> dimensionCardinality,
        Optional<Boolean> dimensionIsVisible, Optional<Boolean> dimensionIsFactDimension,
        Optional<List<MeasureGroupDimension>> dimensionPath, Optional<String> dimensionGranularity)
        implements MdSchemaMeasureGroupDimensionsResponseRow {

}
