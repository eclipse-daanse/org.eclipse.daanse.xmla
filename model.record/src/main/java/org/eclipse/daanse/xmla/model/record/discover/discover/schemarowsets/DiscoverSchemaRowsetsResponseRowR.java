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
package org.eclipse.daanse.xmla.model.record.discover.discover.schemarowsets;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsResponseRow;
import org.eclipse.daanse.xmla.api.xmla.Restriction;

public record DiscoverSchemaRowsetsResponseRowR(String schemaName, Optional<String> schemaGuid,
        Optional<List<Restriction>> restrictions, Optional<String> description, Optional<Long> restrictionsMask)
        implements DiscoverSchemaRowsetsResponseRow {

}
