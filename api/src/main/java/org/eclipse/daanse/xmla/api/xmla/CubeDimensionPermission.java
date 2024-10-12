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
 * The CubeDimensionPermission complex type represents permissions for a CubeDimension.
 */
public interface CubeDimensionPermission {

    /**
     * @return The ID of the CubeDimension. For the Measures dimension, this string MUST be set to
     *         "Measures".
     */
    String cubeDimensionID();

    /**
     * @return The object description.
     */
    Optional<String> description();

    /**
     * @return Specifies whether the role has permission to read metadata or data from the
     *         CubeDimension.
     */
    Optional<ReadWritePermissionEnum> read();

    /**
     * @return Specifies whether the role has permission to write to the CubeDimension.
     */
    Optional<ReadWritePermissionEnum> write();

    /**
     * @return A collection of AttributePermission objects.
     */
    Optional<List<AttributePermission>> attributePermissions();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();
}
