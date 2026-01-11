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
package org.eclipse.daanse.xmla.api.discover.discover.keywords;

import static org.eclipse.daanse.xmla.api.common.properties.OperationNames.DISCOVER_KEYWORDS;

import org.eclipse.daanse.xmla.api.annotation.Operation;
import org.eclipse.daanse.xmla.api.discover.Properties;

@Operation(name = DISCOVER_KEYWORDS, guid = "1426C443-4CDD-4A40-8F45-572FAB9BBAA1")
public interface DiscoverKeywordsRequest {

    Properties properties();

    DiscoverKeywordsRestrictions restrictions();

}
