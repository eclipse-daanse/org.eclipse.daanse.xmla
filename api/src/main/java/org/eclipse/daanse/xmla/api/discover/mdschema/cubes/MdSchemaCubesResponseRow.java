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
package org.eclipse.daanse.xmla.api.discover.mdschema.cubes;

import java.time.LocalDateTime;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.PreferredQueryPatternsEnum;

/**
 * This schema rowset describes the structure of cubes within a database. Perspectives are also
 * returned in this schema.
 */
public interface MdSchemaCubesResponseRow {

    /**
     * @return The catalog name.
     */
    String catalogName();

    /**
     * @return The name of the schema.
     */
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    Optional<String> cubeName();

    /**
     * @return The type of the cube. Valid values are: CUBE DIMENSION
     */
    Optional<CubeTypeEnum> cubeType();

    /**
     * @return The GUID of the cube.
     */
    Optional<Integer> cubeGuid();

    /**
     * @return TimeThe date and time the cube was created.
     */
    Optional<LocalDateTime> createdOn();

    /**
     * @return The date and time that the cube schema was last updated.
     */
    Optional<LocalDateTime> lastSchemaUpdate();

    /**
     * @return The name of the user who last updated the cube’s schema.
     */
    Optional<String> schemaUpdatedBy();

    /**
     * @return TimeThe date and time that the cube was last processed.
     */
    Optional<LocalDateTime> lastDataUpdate();

    /**
     * @return The name of the user who last updated the data of the cube.
     */
    Optional<String> dataUpdateDBy();

    /**
     * @return A description of the cube.
     */
    Optional<String> description();

    /**
     * @return When true, indicates that the cube has drillthrough enabled; otherwise, false.
     */
    Optional<Boolean> isDrillThroughEnabled();

    /**
     * @return When true, indicates that the cube can be used in a linked cube; otherwise false.
     */
    Optional<Boolean> isLinkable();

    /**
     * @return When true, indicates that the cube is write-enabled; otherwise false.
     */
    Optional<Boolean> isWriteEnabled();

    /**
     * @return When true, indicates that SQL can be used on the cube; otherwise false.
     */
    Optional<Boolean> isSqlEnabled();

    /**
     * @return The caption of the cube.
     *
     */
    Optional<String> cubeCaption();

    /**
     * @return The name of the source cube if this cube is a perspective cube.
     */
    Optional<String> baseCubeName();

    /**
     * @return A bitmask with one of these valid values: 0x01-Cube 0x02-Dimension
     */
    Optional<CubeSourceEnum> cubeSource();

    /**
     * @return A bitmask that describes query pattern client applications can utilize for higher
     *         performance. Valid values are:
     *
     *         0x00 – Use CrossJoin function to create symmetric sets on an axis. This is the default
     *         value for the 0th bit when Analysis Services is running in Traditional mode.
     *
     *         0x01 – Use DrillDownMember to create a more restrictive, asymmetric axis. This is the
     *         default value for the 0th bit when a server that is running Analysis Services is running
     *         in VertiPaq mode.
     */
    Optional<PreferredQueryPatternsEnum> preferredQueryPatterns();

}
