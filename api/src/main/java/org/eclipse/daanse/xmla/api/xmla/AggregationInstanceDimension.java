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
 * This complex type represents a CubeDimension in an AggregationInstance.
 */
public interface AggregationInstanceDimension {

    /**
     * @return The ID of the CubeDimension.
     */
    String cubeDimensionID();

    /**
     * @return A collection of objects of type AggregationInstanceAttribute.
     */
    Optional<List<AggregationInstanceAttribute>> attributes();

}
