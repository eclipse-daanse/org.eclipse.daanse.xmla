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

import org.eclipse.daanse.xmla.api.xmla.AggregationDesign;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Measure;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroup;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupBinding;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupDimension;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroupStorageModeEnumType;
import org.eclipse.daanse.xmla.api.xmla.Partition;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record MeasureGroupR(String name, String id, Instant createdTimestamp, Instant lastSchemaUpdate,
        String description, List<Annotation> annotations, Instant lastProcessed, List<Translation> translations,
        String type, String state, List<Measure> measures, String dataAggregation, MeasureGroupBinding source,
        MeasureGroup.StorageMode storageMode, String storageLocation, Boolean ignoreUnrelatedDimensions,
        ProactiveCaching proactiveCaching, Long estimatedRows, ErrorConfiguration errorConfiguration,
        Long estimatedSize, String processingMode, List<MeasureGroupDimension> dimensions, List<Partition> partitions,
        String aggregationPrefix, BigInteger processingPriority, List<AggregationDesign> aggregationDesigns)
        implements MeasureGroup {

    public record StorageMode(MeasureGroupStorageModeEnumType value, String valuens)
            implements MeasureGroup.StorageMode {

    }
}
