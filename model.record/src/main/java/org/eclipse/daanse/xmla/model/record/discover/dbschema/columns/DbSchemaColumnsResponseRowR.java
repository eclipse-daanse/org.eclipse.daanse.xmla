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
package org.eclipse.daanse.xmla.model.record.discover.dbschema.columns;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ColumnFlagsEnum;
import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;

public record DbSchemaColumnsResponseRowR(Optional<String> tableCatalog, Optional<String> tableSchema,
        Optional<String> tableName, Optional<String> columnName, Optional<Integer> columnGuid,
        Optional<Integer> columnPropId, Optional<Integer> ordinalPosition, Optional<Boolean> columnHasDefault,
        Optional<String> columnDefault, Optional<ColumnFlagsEnum> columnFlags, Optional<Boolean> isNullable,
        Optional<Integer> dataType, Optional<Integer> typeGuid, Optional<Integer> characterMaximum,
        Optional<Integer> characterOctetLength, Optional<Integer> numericPrecision, Optional<Integer> numericScale,
        Optional<Integer> dateTimePrecision, Optional<String> characterSetCatalog, Optional<String> characterSetSchema,
        Optional<String> characterSetName, Optional<String> collationCatalog, Optional<String> collationSchema,
        Optional<String> collationName, Optional<String> domainCatalog, Optional<String> domainSchema,
        Optional<String> domainName, Optional<String> description, Optional<ColumnOlapTypeEnum> columnOlapType)
        implements DbSchemaColumnsResponseRow {

}
