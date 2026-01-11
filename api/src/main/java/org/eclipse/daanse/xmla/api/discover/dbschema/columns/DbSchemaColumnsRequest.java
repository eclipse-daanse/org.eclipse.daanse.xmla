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
package org.eclipse.daanse.xmla.api.discover.dbschema.columns;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DBSCHEMA_COLUMNS;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = DBSCHEMA_COLUMNS, guid = "C8B52214-5CF3-11CE-ADE5-00AA0044773D")
public interface DbSchemaColumnsRequest {

    Properties properties();

    DbSchemaColumnsRestrictions restrictions();

}
