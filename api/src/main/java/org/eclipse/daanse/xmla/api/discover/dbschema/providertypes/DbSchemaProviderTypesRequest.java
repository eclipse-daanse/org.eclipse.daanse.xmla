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
package org.eclipse.daanse.xmla.api.discover.dbschema.providertypes;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DBSCHEMA_PROVIDER_TYPES;

@Operation(name = DBSCHEMA_PROVIDER_TYPES, guid = "C8B5222C-5CF3-11CE-ADE5-00AA0044773D")
public interface DbSchemaProviderTypesRequest {

    Properties properties();

    DbSchemaProviderTypesRestrictions restrictions();

}
