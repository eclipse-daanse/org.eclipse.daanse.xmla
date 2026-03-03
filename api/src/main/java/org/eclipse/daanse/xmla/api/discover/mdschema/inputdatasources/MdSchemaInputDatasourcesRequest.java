/*
* Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.api.discover.mdschema.inputdatasources;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.MDSCHEMA_INPUT_DATASOURCES;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = MDSCHEMA_INPUT_DATASOURCES, guid = "d54b1b45-b226-4988-be89-13cb95f777ff")
public interface MdSchemaInputDatasourcesRequest {

    Properties properties();

    MdSchemaInputDatasourcesRestrictions restrictions();

}
