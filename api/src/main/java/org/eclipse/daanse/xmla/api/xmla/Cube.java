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

public interface Cube {

    String name();

    String id();

    Instant createdTimestamp();

    Instant lastSchemaUpdate();

    String description();

    List<Annotation> annotations();

    BigInteger language();

    String collation();

    List<Translation> translations();

    List<CubeDimension> dimensions();

    List<CubePermission> cubePermissions();

    List<MdxScript> mdxScripts();

    List<Perspective> perspectives();

    String state();

    String defaultMeasure();

    Boolean visible();

    List<MeasureGroup> measureGroups();

    DataSourceViewBinding source();

    String aggregationPrefix();

    BigInteger processingPriority();

    Cube.StorageMode storageMode();

    String processingMode();

    String scriptCacheProcessingMode();

    String scriptErrorHandlingMode();

    String daxOptimizationMode();

    ProactiveCaching proactiveCaching();

    List<Kpi> kpis();

    ErrorConfiguration errorConfiguration();

    List<Action> actions();

    String storageLocation();

    Long estimatedRows();

    Instant lastProcessed();

    interface StorageMode {

        CubeStorageModeEnumType value();

        String valuens();
    }
}
