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

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;

public interface DbSchemaTablesInfoRestrictions {

    String RESTRICTIONS_TABLE_CATALOG = "TABLE_CATALOG";
    String RESTRICTIONS_TABLE_SCHEMA = "TABLE_SCHEMA";
    String RESTRICTIONS_TABLE_NAME = "TABLE_NAME";
    String RESTRICTIONS_TABLE_TYPE = "TABLE_TYPE";

    /**
     * @return Catalog name. NULL if the provider does not support
     */
    @Restriction(name = RESTRICTIONS_TABLE_CATALOG, type = XSD_STRING)
    Optional<String> catalogName();

    /**
     * Unqualified schema name. NULL if the provider does not support schemas.
     */
    @Restriction(name = RESTRICTIONS_TABLE_SCHEMA, type = XSD_STRING)
    Optional<String> schemaName();

    /**
     * @return Table name
     */
    @Restriction(name = RESTRICTIONS_TABLE_NAME, type = XSD_STRING)
    String tableName();

    /**
     * @return Table type. One of the following or a provider-specific value: ALIAS, TABLE, SYNONYM,
     *         SYSTEM TABLE, VIEW, GLOBAL " TEMPORARY, LOCAL TEMPORARY, EXTERNAL TABLE, SYSTEM VIEW
     */
    @Restriction(name = RESTRICTIONS_TABLE_TYPE, type = XSD_STRING)
    TableTypeEnum tableType();
}
