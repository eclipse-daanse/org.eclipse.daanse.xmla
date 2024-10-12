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
import java.util.Optional;

/**
 * This complex type represents a CubeDimension in a Perspective.
 */
public interface PerspectiveDimension {

    /**
     * @return The ID of a CubeDimension.
     */
    String cubeDimensionID();

    /**
     * @return A collection of objects of type PerspectiveAttribute that is included in this
     *         PerspectiveDimension.
     */
    Optional<List<PerspectiveAttribute>> attributes();

    /**
     * @return A collection of objects of type PerspectiveHierarchy that is included in this
     *         PerspectiveDimension.
     */
    Optional<List<PerspectiveHierarchy>> hierarchies();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();
}
