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
package org.eclipse.daanse.xmla.model.record.discover.dbschema.providertypes;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.SearchableEnum;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesResponseRow;

public record DbSchemaProviderTypesResponseRowR(Optional<String> typeName, Optional<LevelDbTypeEnum> dataType,
        Optional<Integer> columnSize, Optional<String> literalPrefix, Optional<String> literalSuffix,
        Optional<String> createParams, Optional<Boolean> isNullable, Optional<Boolean> caseSensitive,
        Optional<SearchableEnum> searchable, Optional<Boolean> unsignedAttribute, Optional<Boolean> fixedPrecScale,
        Optional<Boolean> autoUniqueValue, Optional<String> localTypeName, Optional<Integer> minimumScale,
        Optional<Integer> maximumScale, Optional<Integer> guid, Optional<String> typeLib, Optional<String> version,
        Optional<Boolean> isLong, Optional<Boolean> bestMatch, Optional<Boolean> isFixedLength)
        implements DbSchemaProviderTypesResponseRow {

}
