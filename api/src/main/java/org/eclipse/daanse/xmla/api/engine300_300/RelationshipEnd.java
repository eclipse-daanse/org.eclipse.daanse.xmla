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
package org.eclipse.daanse.xmla.api.engine300_300;

import java.util.List;

import org.eclipse.daanse.xmla.api.engine300.RelationshipEndVisualizationProperties;

public interface RelationshipEnd {

    /**
     * @return Identifies one end of a one-to-many relationship
     */
    String role();

    /**
     * @return Indicates whether the RelationshipEnd is at the "one" side or the "many" side of a
     *         relationship. The enumeration values are: One – This is the primary key end. Many – This
     *         is the foreign key end.
     */
    String multiplicity();

    /**
     * @return The Dimension associated with this end of the relationship.
     */
    String dimensionID();

    /**
     * @return A collection of DimensionAttribute complex types that denote the columns that are
     *         participating in this key.
     */
    List<String> attributes();

    /**
     * @return A collection of Translation objects.
     */
    List<RelationshipEndTranslation> translations();

    /**
     * @return A collection of properties that can be used by tools to specify the set of visualization
     *         and enhanced formatting information of the Relationship.
     */
    RelationshipEndVisualizationProperties visualizationProperties();

}
