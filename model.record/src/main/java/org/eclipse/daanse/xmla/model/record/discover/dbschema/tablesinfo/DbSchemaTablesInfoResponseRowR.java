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
package org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;

public record DbSchemaTablesInfoResponseRowR(Optional<String> catalogName, Optional<String> schemaName,
        String tableName, String tableType, Optional<Integer> tableGuid, Optional<Boolean> bookmarks,
        Optional<Integer> bookmarkType, Optional<Integer> bookmarkDataType, Optional<Integer> bookmarkMaximumLength,
        Optional<Integer> bookmarkInformation, Optional<Long> tableVersion, Optional<Long> cardinality,
        Optional<String> description, Optional<Integer> tablePropId) implements DbSchemaTablesInfoResponseRow {
}
