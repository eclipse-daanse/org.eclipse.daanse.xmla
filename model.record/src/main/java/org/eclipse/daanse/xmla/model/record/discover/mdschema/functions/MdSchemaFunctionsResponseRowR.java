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
package org.eclipse.daanse.xmla.model.record.discover.mdschema.functions;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.DirectQueryPushableEnum;
import org.eclipse.daanse.xmla.api.common.enums.InterfaceNameEnum;
import org.eclipse.daanse.xmla.api.common.enums.OriginEnum;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.ParameterInfo;

public record MdSchemaFunctionsResponseRowR(Optional<String> functionalName, Optional<String> description,
        String parameterList, Optional<Integer> returnType, Optional<OriginEnum> origin,
        Optional<InterfaceNameEnum> interfaceName, Optional<String> libraryName, Optional<String> dllName,
        Optional<String> helpFile, Optional<String> helpContext, Optional<String> object, Optional<String> caption,
        Optional<List<ParameterInfo>> parameterInfo, Optional<DirectQueryPushableEnum> directQueryPushable)
        implements MdSchemaFunctionsResponseRow {

}
