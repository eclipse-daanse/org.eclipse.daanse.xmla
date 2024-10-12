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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * This complex type represents a group of aggregations for the MeasureGroup.
 */
public interface AggregationDesign {

    /**
     * @return The object name.
     */
    String name();

    /**
     * @return The object ID string.
     */
    Optional<String> id();

    /**
     * @return A timestamp for the time that the object was created.
     */
    Optional<Instant> createdTimestamp();

    /**
     * @return A timestamp for the time that the schema was last updated. read only
     */
    Optional<Instant> lastSchemaUpdate();

    /**
     * @return The object description.
     */
    Optional<String> description();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();

    /**
     * @return The estimated average number of rows in the partition for the partitions that share this
     *         design. If this value is not set in the Create command, the system will compute a value.
     */
    Optional<Long> estimatedRows();

    /**
     * @return A collection of Dimension objects.
     */
    Optional<List<AggregationDesignDimension>> dimensions();

    /**
     * @return A collection of Aggregation objects.
     */
    Optional<List<Aggregation>> aggregations();

    /**
     * @return The estimated performance gain of the partition, expressed as a percentage.
     */
    Optional<Integer> estimatedPerformanceGain();

}
