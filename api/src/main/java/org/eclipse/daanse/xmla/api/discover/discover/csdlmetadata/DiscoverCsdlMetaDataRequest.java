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
package org.eclipse.daanse.xmla.api.discover.discover.csdlmetadata;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DISCOVER_CSDL_METADATA;

@Operation(name = DISCOVER_CSDL_METADATA, guid = "87B86062-21C3-460F-B4F8-5BE98394F13B")
public interface DiscoverCsdlMetaDataRequest {

    Properties properties();

    DiscoverCsdlMetaDataRestrictions restrictions();

}
