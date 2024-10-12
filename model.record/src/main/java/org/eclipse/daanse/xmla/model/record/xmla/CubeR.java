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

import org.eclipse.daanse.xmla.api.xmla.Action;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Cube;
import org.eclipse.daanse.xmla.api.xmla.CubeDimension;
import org.eclipse.daanse.xmla.api.xmla.CubePermission;
import org.eclipse.daanse.xmla.api.xmla.CubeStorageModeEnumType;
import org.eclipse.daanse.xmla.api.xmla.DataSourceViewBinding;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Kpi;
import org.eclipse.daanse.xmla.api.xmla.MdxScript;
import org.eclipse.daanse.xmla.api.xmla.MeasureGroup;
import org.eclipse.daanse.xmla.api.xmla.Perspective;
import org.eclipse.daanse.xmla.api.xmla.ProactiveCaching;
import org.eclipse.daanse.xmla.api.xmla.Translation;

public record CubeR(String name, String id, Instant createdTimestamp, Instant lastSchemaUpdate, String description,
        List<Annotation> annotations, BigInteger language, String collation, List<Translation> translations,
        List<CubeDimension> dimensions, List<CubePermission> cubePermissions, List<MdxScript> mdxScripts,
        List<Perspective> perspectives, String state, String defaultMeasure, Boolean visible,
        List<MeasureGroup> measureGroups, DataSourceViewBinding source, String aggregationPrefix,
        BigInteger processingPriority, Cube.StorageMode storageMode, String processingMode,
        String scriptCacheProcessingMode, String scriptErrorHandlingMode, String daxOptimizationMode,
        ProactiveCaching proactiveCaching, List<Kpi> kpis, ErrorConfiguration errorConfiguration, List<Action> actions,
        String storageLocation, Long estimatedRows, Instant lastProcessed) implements Cube {

    public record StorageMode(CubeStorageModeEnumType value, String valuens) implements Cube.StorageMode {

    }
}
