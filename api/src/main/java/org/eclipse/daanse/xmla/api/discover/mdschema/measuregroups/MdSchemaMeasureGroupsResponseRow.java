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
package org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups;

import java.util.Optional;

/**
 * This schema rowset describes the measure groups within a database.
 */
public interface MdSchemaMeasureGroupsResponseRow {

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
     * @return A description of the member.
     */
    Optional<String> description();

    /**
     * @return When true, indicates that the measure group is write- enabled; otherwise false. Returns a
     *         value of true if the measure group is write- enabled.
     */
    Optional<Boolean> isWriteEnabled();

    /**
     * @return The caption for the measure group.
     */
    Optional<String> measureGroupCaption();
}
