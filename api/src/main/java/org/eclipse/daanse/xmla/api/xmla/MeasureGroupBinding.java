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

import java.time.Duration;
import java.util.Optional;

/**
 * The MeasureGroupBinding complex type represents a binding to a MeasureGroup.
 */
public interface MeasureGroupBinding extends Binding {

    /**
     * @return The ID of the DataSource.
     */
    String dataSourceID();

    /**
     * @return The ID of the Cube.
     */
    String cubeID();

    /**
     * @return The ID of the MeasureGroup.
     */
    String measureGroupID();

    /**
     * @return Determines which parts of the bound source data are dynamic and are checked for updates
     *         using the frequency that is specified by the RefreshPolicy element. Enumeration values
     *         are as follows:
     *
     *         NotPersisted - Source metadata, members, and data are all dynamic. Metadata - Source
     *         metadata is static, but members and data are dynamic. All – Source metadata, members, and
     *         data are all static.
     */
    Optional<PersistenceEnum> persistence();

    /**
     * @return Determines how often the dynamic part of the dimension or measure group (as specified by
     *         the Persistence element) is checked for changes. Enumeration values are as follows:
     *         ByQuery - Every query checks to determine whether the source data has changed.
     *         ByInterval - Source data is checked for changes only at the interval that is specified
     *         by the RefreshInterval element.
     */
    Optional<RefreshPolicyEnum> refreshPolicy();

    /**
     * @return A duration that specifies the interval at which the dynamic part of the dimension or
     *         measure group is refreshed. The value -1 second is interpreted to mean infinite.
     */
    Optional<Duration> refreshInterval();

    /**
     * @return An MDX expression that specifies how to filter the source data.
     */
    Optional<String> filter();
}
