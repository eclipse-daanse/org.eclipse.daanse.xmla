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
package org.eclipse.daanse.xmla.model.record.discover.mdschema.actions;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ActionTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CoordinateTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.InvocationEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRestrictions;

public record MdSchemaActionsRestrictionsR(Optional<String> catalogName, Optional<String> schemaName, String cubeName,
        Optional<String> actionName, Optional<ActionTypeEnum> actionType, Optional<String> coordinate,
        CoordinateTypeEnum coordinateType, InvocationEnum invocation, Optional<CubeSourceEnum> cubeSource)
        implements MdSchemaActionsRestrictions {
}
