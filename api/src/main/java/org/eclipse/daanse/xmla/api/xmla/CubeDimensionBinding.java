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
 * The CubeDimensionBinding complex type represents a binding to a CubeDimension.
 */
public interface CubeDimensionBinding extends Binding {

    /**
     * @return The ID of the DataSource.
     */
    String dataSourceID();

    /**
     * @return The ID of the Cube.
     */
    String cubeID();

    /**
     * @return The ID of the CubeDimension.
     */
    String cubeDimensionID();

    /**
     * @return An MDX expression that specifies how to filter the source data.
     */
    Optional<String> filter();

}
