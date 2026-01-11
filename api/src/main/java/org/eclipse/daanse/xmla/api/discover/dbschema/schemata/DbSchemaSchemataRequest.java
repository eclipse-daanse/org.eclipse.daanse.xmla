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
package org.eclipse.daanse.xmla.api.discover.dbschema.schemata;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DBSCHEMA_SCHEMATA;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = DBSCHEMA_SCHEMATA, guid = "c8b52225-5cf3-11ce-ade5-00aa0044773d")
public interface DbSchemaSchemataRequest {

    Properties properties();

    DbSchemaSchemataRestrictions restrictions();

}
