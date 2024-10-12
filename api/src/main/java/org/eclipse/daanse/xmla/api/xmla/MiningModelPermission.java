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

import java.util.Optional;

/**
 * The MiningModelPermission complex type represents permissions for a MiningModel.
 */
public interface MiningModelPermission extends Permission {

    /**
     * @return When true, indicates that drillthrough is allowed on the MiningModel; otherwise, false.
     */
    Optional<Boolean> allowDrillThrough();

    /**
     * @return When true, indicates that browsing is allowed on the object; otherwise, false.
     */
    Optional<Boolean> allowBrowsing();
}
