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
package org.eclipse.daanse.xmla.api.discover.discover.datasources;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DISCOVER_DATASOURCES;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = DISCOVER_DATASOURCES, guid = "06C03D41-F66D-49F3-B1B8-987F7AF4CF18")
public interface DiscoverDataSourcesRequest {

    Properties properties();

    DiscoverDataSourcesRestrictions restrictions();

}
