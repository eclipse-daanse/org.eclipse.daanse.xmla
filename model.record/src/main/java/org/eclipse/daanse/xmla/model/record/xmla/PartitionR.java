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
package org.eclipse.daanse.xmla.model.record.xmla;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

import org.eclipse.daanse.xmla.api.xmla.AggregationInstance;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.DataSourceViewBinding;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Partition;
import org.eclipse.daanse.xmla.api.xmla.PartitionCurrentStorageModeEnumType;
import org.eclipse.daanse.xmla.api.xmla.PartitionStorageModeEnumType;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.TabularBinding;

public record PartitionR(String name, String id, Instant createdTimestamp, Instant lastSchemaUpdate, String description,
        List<Annotation> annotations, TabularBinding source, BigInteger processingPriority, String aggregationPrefix,
        Partition.StorageMode storageMode, String processingMode, ErrorConfiguration errorConfiguration,
        String storageLocation, String remoteDatasourceID, String slice, ProactiveCaching proactiveCaching, String type,
        Long estimatedSize, Long estimatedRows, Partition.CurrentStorageMode currentStorageMode,
        String aggregationDesignID, List<AggregationInstance> aggregationInstances,
        DataSourceViewBinding aggregationInstanceSource, Instant lastProcessed, String state,
        Integer stringStoresCompatibilityLevel, Integer currentStringStoresCompatibilityLevel, String directQueryUsage)
        implements Partition {

    public record CurrentStorageMode(PartitionCurrentStorageModeEnumType value, String valuens)
            implements Partition.CurrentStorageMode {

    }

    public record StorageMode(PartitionStorageModeEnumType value, String valuens) implements Partition.StorageMode {

    }
}
