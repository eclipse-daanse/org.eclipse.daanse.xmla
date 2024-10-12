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

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DBSCHEMA_TABLES_INFO;

@Operation(name = DBSCHEMA_TABLES_INFO, guid = "c8b522e0-5cf3-11ce-ade5-00aa0044773d")
public interface DbSchemaTablesInfoRequest {

    Properties properties();

    DbSchemaTablesInfoRestrictions restrictions();

}
