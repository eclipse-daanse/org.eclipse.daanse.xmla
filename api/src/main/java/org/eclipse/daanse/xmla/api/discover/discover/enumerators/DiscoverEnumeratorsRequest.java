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
package org.eclipse.daanse.xmla.api.discover.discover.enumerators;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DISCOVER_ENUMERATORS;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = DISCOVER_ENUMERATORS, guid = "55A9E78B-ACCB-45B4-95A6-94C5065617A7")
public interface DiscoverEnumeratorsRequest {

    Properties properties();

    DiscoverEnumeratorsRestrictions restrictions();

}
