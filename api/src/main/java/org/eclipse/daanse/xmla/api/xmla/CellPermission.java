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
 * The CellPermission complex type represents permissions for the cells in a Cube. A maximum of
 * three CellPermission objects can exist within a CellPermissions collection, one each for the
 * Read, ReadContingent, and ReadWrite values of the Access element.
 */
public interface CellPermission {

    /**
     * @return An enumeration value that indicates the type of access being granted. The enumeration
     *         values are as follows: Read – Read access to the cell is permitted. ReadContingent –
     *         ReadContingent access to the cell is permitted. ReadWrite – ReadWrite access to the cell
     *         is permitted.
     */
    Optional<AccessEnum> access();

    /**
     * @return The object description.
     */
    Optional<String> description();

    /**
     * @return An MDX expression that returns a Boolean.
     */
    Optional<String> expression();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();
}
