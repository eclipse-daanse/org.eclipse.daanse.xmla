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
import java.time.Instant;
import java.util.List;

/**
 * The Partition complex type represents a partition of a measure group.
 */
public interface Partition {

    /**
     * @return The object name.
     */
    String name();

    /**
     * @return The object ID string.
     */
    String id();

    /**
     * @return A timestamp for the time that the object was created.
     */
    Instant createdTimestamp();

    /**
     * @return A timestamp for the time that the schema was last updated.
     */
    Instant lastSchemaUpdate();

    /**
     * @return The object description.
     */
    String description();

    /**
     * @return A collection of Annotation objects.
     */
    List<Annotation> annotations();

    /**
     * @return Specifies the source of the partition data.
     */
    TabularBinding source();

    /**
     * @return Integer that determines the priority for processing.
     */
    BigInteger processingPriority();

    /**
     * @return A prefix that is pre-pended to the names of aggregation tables/views.
     */
    String aggregationPrefix();

    /**
     * @return Determines the storage mode for the partition. When the value of StorageMode is
     *         "InMemory", the valuens attribute MUST be used.
     */
    Partition.StorageMode storageMode();

    /**
     * @return A string that specifies whether aggregations and indexes are to be built lazily. For more
     *         information about lazy aggregations, see section 1.1.
     */
    String processingMode();

    /**
     * @return Error configuration settings to handle issues in the source data.
     */
    ErrorConfiguration errorConfiguration();

    /**
     * @return The file system storage location for the partition.
     */
    String storageLocation();

    /**
     * @return Specifies the ID of the OLAP data source that points to the remote server where this
     *         partition is stored.
     */
    String remoteDatasourceID();

    /**
     * @return An MDX expression that defines the slice that is contained in the partition.
     */
    String slice();

    /**
     * @return Proactive caching settings for this partition.
     */
    ProactiveCaching proactiveCaching();

    /**
     * @return Indicates partition type as either Data or Writeback. If Type is set to "Writeback", the
     *         Source element MUST NOT be empty, and all Measure objects for the cube MUST use "Sum" as
     *         the value of the AggregateFunction element.
     */
    String type();

    /**
     * @return The estimated size of the partition in bytes.
     */
    Long estimatedSize();

    /**
     * @return Estimated number of rows.
     */
    Long estimatedRows();

    /**
     * @return The current storage mode of the partition. Used for proactive caching when StorageMode
     *         might transiently change. When the value of StorageMode is "InMemory", the valuens
     *         attribute MUST be used.
     */
    Partition.CurrentStorageMode currentStorageMode();

    /**
     * @return The ID of the AggregationDesign for the partition.
     */
    String aggregationDesignID();

    /**
     * @return A collection of AggregationInstance objects.
     */
    List<AggregationInstance> aggregationInstances();

    /**
     * @return The source of the aggregation instance data.
     */
    DataSourceViewBinding aggregationInstanceSource();

    /**
     * @return The date and time when the partition was last processed.
     */
    Instant lastProcessed();

    /**
     * @return Represents the processing state of the partition. Values include: Processed Unprocessed
     */
    String state();

    /**
     * @return An enumeration value that specifies the string store compatibility level that will be
     *         instituted the next time the object is processed. The valid values are the following:
     *         1050 – Standard string handling. (default) 1100 – Enhanced string handling.<78>
     */
    Integer stringStoresCompatibilityLevel();

    /**
     * @return An enumeration value that specifies the string store compatibility level that is
     *         currently in effect. The interpretation of the values is the same as for
     *         StringStoresCompatibilityLevel. 1050 - default
     */
    Integer currentStringStoresCompatibilityLevel();

    /**
     * @return DirectQueryUsage specifies how a partition is to be queried. Values are: 2.2.4.2.2.13.1
     *         InMemoryWithDirectQuery (default) DirectQueryOnly InMemoryOnly
     */
    String directQueryUsage();

    public interface CurrentStorageMode {

        PartitionCurrentStorageModeEnumType value();

        String valuens();

    }

    public interface StorageMode {

        PartitionStorageModeEnumType value();

        String valuens();
    }

}
