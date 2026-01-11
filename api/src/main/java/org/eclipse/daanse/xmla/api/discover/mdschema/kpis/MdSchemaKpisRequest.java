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
package org.eclipse.daanse.xmla.api.discover.mdschema.kpis;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.MDSCHEMA_KPIS;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = MDSCHEMA_KPIS, guid = "2AE44109-ED3D-4842-B16F-B694D1CB0E3F")
public interface MdSchemaKpisRequest {

    Properties properties();

    MdSchemaKpisRestrictions restrictions();

}
