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

public interface MeasureGroup {

    String name();

    String id();

    Instant createdTimestamp();

    Instant lastSchemaUpdate();

    String description();

    List<Annotation> annotations();

    Instant lastProcessed();

    List<Translation> translations();

    String type();

    String state();

    List<Measure> measures();

    String dataAggregation();

    MeasureGroupBinding source();

    MeasureGroup.StorageMode storageMode();

    String storageLocation();

    Boolean ignoreUnrelatedDimensions();

    ProactiveCaching proactiveCaching();

    Long estimatedRows();

    ErrorConfiguration errorConfiguration();

    Long estimatedSize();

    String processingMode();

    List<MeasureGroupDimension> dimensions();

    List<Partition> partitions();

    String aggregationPrefix();

    BigInteger processingPriority();

    List<AggregationDesign> aggregationDesigns();

    interface StorageMode {

        MeasureGroupStorageModeEnumType value();

        String valuens();

    }
}
