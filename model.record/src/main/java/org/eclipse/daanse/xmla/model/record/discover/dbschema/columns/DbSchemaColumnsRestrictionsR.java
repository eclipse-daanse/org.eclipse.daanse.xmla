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

import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRestrictions;

public record DbSchemaColumnsRestrictionsR(Optional<String> tableCatalog, Optional<String> tableSchema,
        Optional<String> tableName, Optional<String> columnName, Optional<ColumnOlapTypeEnum> columnOlapType)
        implements DbSchemaColumnsRestrictions {

}
