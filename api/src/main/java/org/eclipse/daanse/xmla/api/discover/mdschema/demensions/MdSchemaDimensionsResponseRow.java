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
package org.eclipse.daanse.xmla.api.discover.mdschema.demensions;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.DimensionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionUniqueSettingEnum;

/**
 * This schema rowset describes the dimensions within a database.
 */
public interface MdSchemaDimensionsResponseRow {

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
     * The name of the dimension.
     */
    Optional<String> dimensionName();

    /**
     * The unique name of the dimension.
     */
    Optional<String> dimensionUniqueName();

    /**
     * @return The GUID of the dimension.
     */
    Optional<Integer> dimensionGuid();

    /**
     * @return The caption of the dimension.
     */
    Optional<String> dimensionCaption();

    /**
     * @return The position of the dimension within the cube.
     */
    Optional<Integer> dimensionOptional();

    /**
     * @return The type of the dimension. Valid values are: 0 - UNKNOWN 1 - TIME 2 - MEASURE 3 - OTHER 5
     *         - QUANTITATIVE 6- ACCOUNTS 7 - CUSTOMERS 8- PRODUCTS 9 - SCENARIO 10- UTILITY 11 -
     *         CURRENCY 12 - RATES 13 - CHANNEL 14 - PROMOTION 15 - ORGANIZATION 16 - BILL OF MATERIALS
     *         17 – GEOGRAPHY
     */
    Optional<DimensionTypeEnum> dimensionType();

    /**
     * @return The number of members in the key attribute.
     */
    Optional<Integer> dimensionCardinality();

    /**
     * @return The default hierarchy of the dimension.
     */
    Optional<String> defaultHierarchy();

    /**
     * @return A description of the dimension.
     */
    Optional<String> description();

    /**
     * @return When true, indicates that the dimension is virtual; otherwise false.
     */
    Optional<Boolean> isVirtual();

    /**
     * @return When true, indicates that the dimension is write-enabled; otherwise false
     */
    Optional<Boolean> isReadWrite();

    /**
     * @return A bitmask that specifies which columns contain unique values: 0x00000001 - Member key
     *         columns establish uniqueness. 0x00000002 - Member name columns establish uniqueness.
     */
    Optional<DimensionUniqueSettingEnum> dimensionUniqueSetting();

    /**
     * @return The name of the master dimension.
     */
    Optional<String> dimensionMasterName();

    /**
     * @return When true, indicates that the dimension is visible in a client application; otherwise
     *         false.
     */
    Optional<Boolean> dimensionIsVisible();
}
