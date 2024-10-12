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
package org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;

/**
 * This schema rowset identifies the (base) data types supported by the server.
 */
public interface DbSchemaSourceTablesResponseRow {

    /**
     * @return Catalog name. NULL if the provider does not support catalogs.
     */
    Optional<String> catalogName();

    /**
     * @return Unqualified schema name. NULL if the provider does not support schemas.
     */
    Optional<String> schemaName();

    /**
     * @return Table name
     */
    String tableName();

    /**
     * @return Table type. One of the following or a provider-specific value: ALIAS, TABLE, SYNONYM,
     *         SYSTEM TABLE, VIEW, GLOBAL " TEMPORARY, LOCAL TEMPORARY, EXTERNAL TABLE, SYSTEM VIEW
     */
    TableTypeEnum tableType();
}
