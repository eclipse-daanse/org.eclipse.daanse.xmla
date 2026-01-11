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

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DBSCHEMA_SOURCE_TABLES;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = DBSCHEMA_SOURCE_TABLES, guid = "8c3f5858-2742-4976-9d65-eb4d493c693e")
public interface DbSchemaSourceTablesRequest {

    Properties properties();

    DbSchemaSourceTablesRestrictions restrictions();

}
