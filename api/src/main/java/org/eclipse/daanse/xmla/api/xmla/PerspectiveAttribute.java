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
 * This complex type represents an attribute in a PerspectiveDimension.
 */
public interface PerspectiveAttribute {

    /**
     * @return The ID of the attribute.
     */
    String attributeID();

    /**
     * @return When true, specifies whether the attribute hierarchy is visible; otherwise, false.
     */
    Optional<Boolean> attributeHierarchyVisible();

    /**
     * @return An MDX expression specifying the default member for this attribute.
     */
    Optional<String> defaultMember();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();
}
