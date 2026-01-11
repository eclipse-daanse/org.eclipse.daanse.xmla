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
package org.eclipse.daanse.xmla.api.discover.mdschema.actions;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.MDSCHEMA_ACTIONS;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = MDSCHEMA_ACTIONS, guid = "A07CCD08-8148-11D0-87BB-00C04FC33942")
public interface MdSchemaActionsRequest {

    Properties properties();

    MdSchemaActionsRestrictions restrictions();

}
