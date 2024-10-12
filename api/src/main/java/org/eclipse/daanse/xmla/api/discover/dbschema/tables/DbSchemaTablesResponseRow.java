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
package org.eclipse.daanse.xmla.api.discover.dbschema.tables;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This schema rowset returns dimensions, measure groups, or schema rowsets exposed as tables.
 */
public interface DbSchemaTablesResponseRow {

    /**
     * @return The name of the database.
     */
    Optional<String> tableCatalog();

    /**
     * @return The name of the schema.
     */
    Optional<String> tableSchema();

    /**
     * @return The name of the table.
     */
    Optional<String> tableName();

    /**
     * @return The type of table: TABLE for measure group. SYSTEM TABLE for dimension. SCHEMA for schema
     *         rowset table.
     */
    Optional<String> tableType();

    /**
     * @return The GUID of the table.
     */
    Optional<String> tableGuid();

    /**
     * @return A description of the object.
     */
    Optional<String> description();

    /**
     * @return The ID of the table.
     */
    Optional<Integer> tablePropId();

    /**
     * @return The date the table was created.
     */
    Optional<LocalDateTime> dateCreated();

    /**
     * @return The date the table was last modified.
     */
    Optional<LocalDateTime> dateModified();
}
