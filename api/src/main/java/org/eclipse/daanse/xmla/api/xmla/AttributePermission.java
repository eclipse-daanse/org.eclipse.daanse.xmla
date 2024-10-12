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
 * The AttributePermission complex type represents permissions for a DimensionAttribute.
 */
public interface AttributePermission {

    /**
     * @return The ID of the attribute. The "Measures" string MUST be used to indicate the Measures
     *         dimension.
     */
    String attributeID();

    /**
     * @return The object description.
     */
    Optional<String> description();

    /**
     * @return An MDX expression that returns the default member for the attribute.
     */
    Optional<String> defaultMember();

    /**
     * @return An MDX expression that if true, specifies whether MDX queries are to return visual totals
     *         for the attribute; otherwise, false.
     */
    Optional<String> visualTotals();

    /**
     * @return An MDX set expression that specifies the set of allowed members for the attribute.
     */
    Optional<String> allowedSet();

    /**
     * @return An MDX set expression that defines the set of denied members for the attribute.
     */
    Optional<String> deniedSet();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();
}
