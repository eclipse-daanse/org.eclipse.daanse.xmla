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

/**
 * This complex type represents a perspective of a cube.
 */
public interface Perspective {

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
     * @return A collection of Translation objects.
     */
    List<Translation> translations();

    /**
     * @return The default measure for the perspective.
     */
    String defaultMeasure();

    /**
     * @return A collection of PerspectiveDimension objects.
     */
    List<PerspectiveDimension> dimensions();

    /**
     * @return A collection of PerspectiveMeasureGroup objects.
     */
    List<PerspectiveMeasureGroup> measureGroups();

    /**
     * @return A collection of PerspectiveCalculation objects.
     */
    List<PerspectiveCalculation> calculations();

    /**
     * @return A collection of PerspectiveKpi objects.
     */
    List<PerspectiveKpi> kpis();

    /**
     * @return A collection of PerspectiveAction objects.
     */
    List<PerspectiveAction> actions();

}
