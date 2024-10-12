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

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * This complex type represents a scalar column in the MiningStructure. ScalarMiningStructureColumn
 * extends the base class MiningStructureColumn.
 */
public non-sealed interface ScalarMiningStructureColumn extends MiningStructureColumn {

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
     * @return Contains the data type of the element.
     */
    Optional<String> type();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();

    /**
     * @return When true, indicates that the column provides the key of the case; otherwise, false. One
     *         or more columns MAY be designated as the key. At least one column MUST be designated as
     *         the key.
     */
    Optional<Boolean> isKey();

    /**
     * @return The data source for this column, if the mining structure is bound to OLAP objects. Source
     *         is of type "Binding". This element MAY be empty for mining structures based on relational
     *         data, but for OLAP-based mining structures, one of the following derived classes MUST be
     *         used: AttributeBinding, CubeAttributeBinding, or MeasureBinding.
     */
    Optional<Binding> source();

    /**
     * @return An extensible enumeration, such as Normal, Uniform, and LogNormal.
     */
    Optional<String> distribution();

    /**
     * @return A collection of MiningModelingFlag objects. If non-empty, the only supported string value
     *         is "NotNull".
     */
    Optional<List<MiningModelingFlag>> modelingFlags();

    /**
     * @return An enumeration that describes the type of content represented by a mining structure
     *         column. All values might not necessarily be supported by all algorithms.
     */
    String content();

    /**
     * @return A string collection of the ID for any columns classified by this column.
     */
    Optional<List<String>> classifiedColumns();

    /**
     * @return Defines the method to be used for discretization. Current values supported for this
     *         string element are as follows: "Automatic" – The algorithm chooses the best technique
     *         among EqualAreas, Thresholds, and Clusters. "EqualAreas" – For continuous values,
     *         specifies that the area that represents the distribution of each bucket is equal.
     *         "Thresholds" - For continuous variables, specifies that bucket thresholds are based on
     *         inflection points of the distribution curve. "Clusters" - Finds buckets by single
     *         dimension clustering by using the K-Means algorithm.
     */
    Optional<String> discretizationMethod();

    /**
     * @return The number of buckets in which to discretize.
     */
    Optional<BigInteger> discretizationBucketCount();

    /**
     * @return The data source for this column, for mining structures bound to relational data (or
     *         unbound). Collection of objects of type DataItem to bind to values of this column. The
     *         Source element within the DataItem MUST be of type ColumnBinding.
     */
    Optional<List<DataItem>> keyColumns();

    /**
     * @return An optional column binding containing the name of the key values (in the KeyColumns
     *         element). NameColumn is of type DataItem. The Source element within the DataItem MUST be
     *         of type ColumnBinding.
     */
    Optional<DataItem> nameColumn();

    /**
     * @return A collection of Translation objects.
     */
    Optional<List<Translation>> translations();
}
