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
*/
package org.eclipse.daanse.xmla.model.record.discover;

import org.eclipse.daanse.xmla.api.discover.mdschema.functions.ParameterInfo;

public record ParameterInfoR(String name, String description, Boolean optional, Boolean repeatable, Integer repeatGroup)
        implements ParameterInfo {
}
