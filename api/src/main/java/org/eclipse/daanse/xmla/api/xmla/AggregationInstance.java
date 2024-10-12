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

/**
 * This complex type represents an aggregation instance in a partition.
 */
public interface AggregationInstance {

    /**
     * @return The object ID string.
     */
    String id();

    /**
     * @return The object name.
     */
    String name();

    /**
     * @return The type of aggregation stored in the partition. The enumeration values are the
     *         following: IndexedView: The aggregation is stored in an indexed view. Table: The
     *         aggregation is stored in a table. UserDefined: The aggregation is user-defined.
     */
    String aggregationType();

    /**
     * @return The table name that is used if it is different than the name that is associated with
     *         column binding. This permits a single aggregation table to be defined in the
     *         DataSourceView for multiple partitions instead of one per partition.
     */
    TabularBinding source();

    /**
     * @return A collection of objects of type AggregationInstanceDimension.
     */
    List<AggregationInstanceDimension> dimensions();

    /**
     * @return A collection of objects of type AggregationInstanceMeasure.
     */
    List<AggregationInstanceMeasure> measures();

    /**
     * @return A collection of Annotation objects.
     */
    List<Annotation> annotations();

    /**
     * @return The object description.
     */
    String description();

}
