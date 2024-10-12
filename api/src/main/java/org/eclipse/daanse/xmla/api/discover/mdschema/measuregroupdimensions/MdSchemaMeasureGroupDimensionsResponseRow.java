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
package org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.DimensionCardinalityEnum;

/**
 * This schema rowset enumerates the dimensions of measure groups.
 */
public interface MdSchemaMeasureGroupDimensionsResponseRow {

    /**
     * @return The name of the database.
     */
    Optional<String> catalogName();

    /**
     * @return The name of the schema.
     */
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    Optional<String> cubeName();

    /**
     * @return The name of the measure group.
     */
    Optional<String> measureGroupName();

    /**
     * @return The number of instances a measure in the measure group can have for a single dimension
     *         member. Possible values include:
     */
    Optional<String> measureGroupCardinality();

    /**
     * The unique name for the dimension.
     */
    Optional<String> dimensionUniqueName();

    /**
     * @return The number of instances a dimension member can have for a single instance of a measure
     *         group measure. Possible values include: ONE MANY
     */
    Optional<DimensionCardinalityEnum> dimensionCardinality();

    /**
     * @return When true, indicates that hierarchies in the dimension are visible; otherwise false.
     */
    Optional<Boolean> dimensionIsVisible();

    /**
     * @return When true, indicates that the dimension is a fact dimension; otherwise false.
     */
    Optional<Boolean> dimensionIsFactDimension();

    /**
     * @return A list of dimensions for the reference dimension. The column name of the nested row is
     *         "MeasureGroupDimension". For information on nested rowsets, see section 2.2.4.1.3.1.1.
     */
    Optional<List<MeasureGroupDimension>> dimensionPath();

    /**
     * @return The unique name of the attribute hierarchy that represents the granularity of the
     *         dimension.
     */
    Optional<String> dimensionGranularity();

}
