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
package org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs;

import java.time.LocalDateTime;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ClientCacheRefreshPolicyEnum;
import org.eclipse.daanse.xmla.api.common.enums.TypeEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;

public record DbSchemaCatalogsResponseRowR(Optional<String> catalogName, Optional<String> description,
        Optional<String> roles, Optional<LocalDateTime> dateModified, Optional<Integer> compatibilityLevel,
        Optional<TypeEnum> type, Optional<Integer> version, Optional<String> databaseId,
        Optional<LocalDateTime> dateQueried, Optional<Boolean> currentlyUsed, Optional<Double> popularity,
        Optional<Double> weightedPopularity, Optional<ClientCacheRefreshPolicyEnum> clientCacheRefreshPolicy)
        implements DbSchemaCatalogsResponseRow {
}
