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
 * This complex type represents a column in a MiningModel.
 */
public interface MiningModelColumn {

    /**
     * @return The object name.
     */
    String name();

    /**
     * @return The object ID string.
     */
    Optional<String> id();

    /**
     * @return The object description.
     */
    Optional<String> description();

    /**
     * @return The ID of the source column in the parent structure.
     */
    Optional<String> sourceColumnID();

    /**
     * @return Specifies the usage for this column within the MiningModel. If the column that is
     *         referenced by the SourceColumnID value is a key column, the Usage element MUST be set to
     *         "Key".
     */
    Optional<String> usage();

    /**
     * @return A string that contains a valid DMX filter to be applied to nested table columns. An empty
     *         string or missing element implies no filter. This element is empty for non-table columns.
     */
    Optional<String> filter();

    /**
     * @return A collection of Translation objects.
     */
    Optional<List<Translation>> translations();

    /**
     * @return A set of Column objects. This is a nesting of this same type. It is used only for nested
     *         tables.
     */
    Optional<List<MiningModelColumn>> columns();

    /**
     * @return A collection of ModelingFlag objects.
     */
    Optional<List<MiningModelingFlag>> modelingFlags();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();
}
