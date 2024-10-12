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
package org.eclipse.daanse.xmla.api.xmla;

import java.util.List;
import java.util.Optional;

/**
 * This complex type represents a nested table column in the MiningStructure.
 * TableMiningStructureColumn extends MiningStructureColumn.
 */
public non-sealed interface TableMiningStructureColumn extends MiningStructureColumn {

    /**
     * @return A collection of DataItem objects that binds to foreign keys for this column. The Source
     *         element within the DataItem MUST be of type ColumnBinding.
     */
    Optional<List<DataItem>> foreignKeyColumns();

    /**
     * @return An optional binding to a MeasureGroup if DataSourceID for MiningStructure is OLAP.
     */
    Optional<MeasureGroupBinding> sourceMeasureGroup();

    /**
     * @return A collection of bindings to MiningStructureColumns. MiningStructureColumns can be nested
     *         within each other, but it is only recursive to one level. That is, a set of scalar
     *         columns can be specified here, but included columns cannot have included columns nested
     *         within them.
     */
    Optional<List<MiningStructureColumn>> columns();

    /**
     * @return A collection of Translation objects.
     */
    Optional<List<Translation>> translations();
}
