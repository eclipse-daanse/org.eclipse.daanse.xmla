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
package org.eclipse.daanse.xmla.api.discover.discover.schemarowsets;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DISCOVER_SCHEMA_ROWSETS;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = DISCOVER_SCHEMA_ROWSETS, guid = "EEA0302B-7922-4992-8991-0E605D0E5593")
public interface DiscoverSchemaRowsetsRequest {

    Properties properties();

    DiscoverSchemaRowsetsRestrictions restrictions();

}
