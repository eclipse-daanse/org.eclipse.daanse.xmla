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
 * The DimensionPermission complex type represents permissions for a Dimension.
 */
public interface DimensionPermission extends Permission {

    /**
     * @return A collection of AttributePermission objects.
     */
    Optional<List<AttributePermission>> attributePermissions();

    /**
     * @return This string is to contain a DAX Boolean expression that evaluates to TRUE for the rows in
     *         the table that are allowed. This is equivalent to saying that the table expression that
     *         returns the allowed rows is FILTER (ALL (Table), AllowedRowsExpression). Element
     *         Read- Only If the DAX Boolean expression is empty, it is treated as TRUE. If the DAX
     *         Boolean expression evaluates to an error, it is treated as FALSE for those rows and
     *         permission is not allowed.
     */
    Optional<String> allowedRowsExpression();
}
