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
 * his complex type represents an action that returns the underlying detail data associated with a
 * cell. DrillThroughAction inherits all elements from Action.
 */
public interface DrillThroughAction extends Action {

    /**
     * @return A Boolean, which, when set to true, sets this DrillThroughAction as the default
     *         DrillThroughAction; otherwise, false.
     */
    Optional<Boolean> defaultAction();

    /**
     * @return A collection of Column objects that define the results to be returned in the
     *         drillthrough. Each column object is of type Binding. However, one of the following
     *         derived classes MUST be used: MeasureBinding CubeAttributeBinding If no columns are
     *         defined, all are returned.
     */
    Optional<List<Binding>> columns();

    /**
     * @return The maximum number of rows that are to be returned in the resulting rowset.
     */
    Optional<Integer> maximumRows();
}
