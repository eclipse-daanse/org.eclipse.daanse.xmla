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
package org.eclipse.daanse.xmla.api.engine300;

import java.math.BigInteger;

/**
 * The HierarchyVisualizationProperties complex type defines the properties that can be used by
 * tools to specify the visualization and enhanced formatting information of the Hierarchy. This
 * type is defined in the eng300 namespace.
 */
public interface HierarchyVisualizationProperties {

    /**
     * @return Provides a hint to client applications to suggest hot to create unambiguous names for
     *         this attribute. The following values are allowed: None – Use the hierarchy name. Context
     *         – Use the incoming relationship name. Merge – Attending to language grammar, concatenate
     *         the incoming relationship name and the attribute name.
     */
    String contextualNameRule();

    /**
     * @return Provides a hint to client applications to suggest the position that this attribute might
     *         hold among the other elements that share the same DisplayFolder.
     */
    BigInteger folderPosition();
}
