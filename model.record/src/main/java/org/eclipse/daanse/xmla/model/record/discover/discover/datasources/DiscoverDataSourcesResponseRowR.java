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
package org.eclipse.daanse.xmla.model.record.discover.discover.datasources;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.AuthenticationModeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ProviderTypeEnum;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesResponseRow;

public record DiscoverDataSourcesResponseRowR(String dataSourceName, Optional<String> dataSourceDescription,
        Optional<String> url, Optional<String> dataSourceInfo, String providerName,
        Optional<ProviderTypeEnum> providerType, Optional<AuthenticationModeEnum> authenticationMode)
        implements DiscoverDataSourcesResponseRow {
}
