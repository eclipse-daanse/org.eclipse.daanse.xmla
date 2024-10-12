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
package org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.DimensionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.DimensionUniqueSettingEnum;
import org.eclipse.daanse.xmla.api.common.enums.GroupingBehaviorEnum;
import org.eclipse.daanse.xmla.api.common.enums.HierarchyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.InstanceSelectionEnum;
import org.eclipse.daanse.xmla.api.common.enums.StructureEnum;
import org.eclipse.daanse.xmla.api.common.enums.StructureTypeEnum;

/**
 * This schema rowset describes each hierarchy within a particular dimension.
 */
public interface MdSchemaHierarchiesResponseRow {

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
     * @return The unique name of the dimension.
     */
    Optional<String> dimensionUniqueName();

    /**
     * @return The name of the hierarchy. This column MAY be blank if there is only a single hierarchy
     *         in the dimension.
     */
    Optional<String> hierarchyName();

    /**
     * @return The unique name of the hierarchy.
     */
    Optional<String> hierarchyUniqueName();

    /**
     * @return The GUID of the hierarchy.
     */
    Optional<Integer> hierarchyGuid();

    /**
     * @return The caption of the hierarchy.
     */
    Optional<String> hierarchyCaption();

    /**
     * @return The type of the dimension. 0 - UNKNOWN 1 - TIME 2 - MEASURE 3 - OTHER 5 - QUANTITATIVE 6
     *         - ACCOUNTS 7 - CUSTOMERS 8 - PRODUCTS 9 - SCENARIO 10 - UTILITY 11 - CURRENCY 12 - RATES
     *         13 - CHANNEL 14 - PROMOTION 15 - ORGANIZATION 16 - BILL_OF_MATERIALS 17 - GEOGRAPHY
     */
    Optional<DimensionTypeEnum> dimensionType();

    /**
     * @return The number of members in the hierarchy.
     */
    Optional<Integer> hierarchyCardinality();

    /**
     * @return The default member for this hierarchy.
     */
    Optional<String> defaultMember();

    /**
     * @return The member name at the highest level of the hierarchy.
     */
    Optional<String> allMember();

    /**
     * @return A description of the hierarchy.
     */
    Optional<String> description();

    /**
     * @return The structure of the hierarchy. Valid values are defined in the following table. 0 -
     *         Hierarchy is a fully balanced structure. 1 - Hierarchy is a ragged balanced structure. 2
     *         - Hierarchy is an unbalanced structure. 3 - Hierarchy is a network structure. For more
     *         information, see the definitions for balanced hierarchy and unbalanced hierarchy in
     *         section 1.1.
     */
    Optional<StructureEnum> structure();

    /**
     * @return When true, indicates that the hierarchy is a virtual hierarchy; otherwise false.
     */
    Optional<Boolean> isVirtual();

    /**
     * @return When true, indicates that write back to the hierarchy is enabled; otherwise false.
     */
    Optional<Boolean> isReadWrite();

    /**
     * @return A list of values that specifies which columns contain unique values: 0x00000001 - Member
     *         key columns establish uniqueness. 0x00000002 - Member name columns establish uniqueness.
     */
    Optional<DimensionUniqueSettingEnum> dimensionUniqueSettings();

    /**
     * @return The unique name of the master dimension.
     */
    Optional<String> dimensionMasterUniqueName();

    /**
     * @return When true, indicates that the dimension is visible; otherwise false.
     */
    Optional<Boolean> dimensionIsVisible();

    /**
     * @return The ordinal number of the hierarchy across all hierarchies of the dimension.
     */
    Optional<Integer> hierarchyOrdinal();

    /**
     * @return When true, indicates that the dimension is shared; otherwise false.
     */
    Optional<Boolean> dimensionIsShared();

    /**
     * @return When true, indicates that the hierarchy is visible; otherwise false.
     */
    Optional<Boolean> hierarchyIsVisible();

    /**
     * @return A bitmask that determines the source of the hierarchy. 0x0001 - Identifies user-defined
     *         hierarchies. 0x0002 - Identifies attribute hierarchies. 0x0004 - Identifies key attribute
     *         hierarchies. 0x0008 - Identifies attributes with no attribute hierarchies. 0x0003 - The
     *         default restriction value.
     */
    Optional<HierarchyOriginEnum> hierarchyOrigin();

    /**
     * @return Display folder for the hierarchy.
     */
    Optional<String> hierarchyDisplayFolder();

    /**
     * @return A list of values that provides a hint to the client application about how to display the
     *         hierarchy values. Valid values include the following: 0 - NONE (No hint is suggested.) 1
     *         - DROPDOWN type of display is suggested. 2 - LIST type of display is suggested. 3 -
     *         FILTERED LIST type of display is suggested. 4 - MANDATORY FILTER type of display is
     *         suggested
     */
    Optional<InstanceSelectionEnum> instanceSelection();

    /**
     * @return Recommends to client applications how to build queries within the hierarchy. Valid values
     *         include the following: 1 - Client applications are encouraged to group by each member of
     *         the hierarchy. 2 - Client applications are discouraged from grouping by each member of
     *         the hierarchy.
     */
    Optional<GroupingBehaviorEnum> groupingBehavior();

    /**
     * @return Indicates the type of hierarchy. Valid values include the following: Natural Unnatural
     *         Unknown
     */
    Optional<StructureTypeEnum> structureType();
}
