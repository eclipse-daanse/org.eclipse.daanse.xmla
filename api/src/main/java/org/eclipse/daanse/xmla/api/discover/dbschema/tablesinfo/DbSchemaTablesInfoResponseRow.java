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
package org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo;

import java.util.Optional;

/**
 * This schema rowset identifies the (base) data types supported by the server.
 */
public interface DbSchemaTablesInfoResponseRow {

    /**
     * @return Catalog name
     */
    Optional<String> catalogName();

    /**
     * Schema name
     */
    Optional<String> schemaName();

    /**
     * @return Table name
     */
    String tableName();

    /**
     * @return Table type
     */
    String tableType();

    /**
     * @return GUID that uniquely identifies the table. Providers that do not use GUIDs to identify
     *         tables should return NULL in this column.
     */
    Optional<Integer> tableGuid();

    /**
     * @return Whether this table supports bookmarks. Allways is false.
     */
    Optional<Boolean> bookmarks();

    /**
     * @return Default bookmark type supported on this table.
     */
    Optional<Integer> bookmarkType();

    /**
     * @return The indicator of the bookmark's native data type.
     */
    Optional<Integer> bookmarkDataType();

    /**
     * @return Maximum length of the bookmark in bytes.
     */
    Optional<Integer> bookmarkMaximumLength();

    /**
     * @return A bitmask specifying additional information about bookmarks over the rowset.
     */
    Optional<Integer> bookmarkInformation();

    /**
     * @return Version number for this table or NULL if the provider does not support returning table
     *         version information.
     */
    Optional<Long> tableVersion();

    /**
     * @return Cardinality (number of rows) of the table.
     */
    Optional<Long> cardinality();

    /**
     * @return Human-readable description of the table.
     */
    Optional<String> description();

    /**
     * @return Property ID of the table. Return null.
     */
    Optional<Integer> tablePropId();
}
