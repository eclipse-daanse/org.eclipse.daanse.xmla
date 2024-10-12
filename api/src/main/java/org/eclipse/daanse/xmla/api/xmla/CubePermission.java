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
 * The CubePermission complex type represents permissions for a Cube.
 */
public interface CubePermission extends Permission {

    /**
     * @return Specifies whether the role has permission to read the underlying source data in the Cube.
     */
    Optional<String> readSourceData();

    /**
     * @return A collection of CubeDimensionPermission objects.
     */
    Optional<List<CubeDimensionPermission>> dimensionPermissions();

    /**
     * @return A collection of CellPermission objects.
     */
    Optional<List<CellPermission>> cellPermissions();

}
