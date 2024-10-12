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
 * The CalculationPropertiesVisualizationProperties complex type specifies visualization properties
 * for a calculated result. This type is defined in the eng300 namespace.
 */
public interface CalculationPropertiesVisualizationProperties {

    /**
     * @return Provides a hint to client applications to suggest the position that this attribute might
     *         hold among the other elements that share the same display folder
     */
    BigInteger folderPosition();

    /**
     * @return Provides a hint to client applications to suggest how to create unambiguous names for
     *         this attribute. The following values are allowed: Alignment "Default" "None" – Use the
     *         attribute name "Context" – Use the incoming relationship name. "Merge" – Attending to
     *         language grammar, concatenate the incoming relationship name and the attribute name.
     */
    String contextualNameRule();

    /**
     * @return Provides a hint to client applications to suggest how to justify this attribute when
     *         displayed. The following values are allowed: "Default" – Use the alignment appropriate
     *         for the attribute’s data type. "Left" – Align left. "Right" – Align right. "Center" –
     *         Center.
     */
    String alignment();

    /**
     * @return Provides a hint to client applications that this attribute is representative of its
     *         display folder.
     */
    Boolean isFolderDefault();

    /**
     * @return Provides a hint to client applications that this attribute is to be displayed
     *         right-to-left.
     */
    Boolean isRightToLeft();

    /**
     * @return Provides a hint to client applications to suggest how to sort instances of this
     *         attribute. The following values are allowed: "Default" – Use the sort direction
     *         appropriate for the attribute’s data type. "Ascending" – Sort in ascending order.
     *         "Descending" – Sort in descending order.
     */
    String sortDirection();

    /**
     * @return Provides a hint to client applications to suggest a string to be associated with values
     *         of this attribute
     */
    String units();

    /**
     * @return Provides a hint to client applications to suggest the length (in characters) to reserve
     *         to display this attribute.
     */
    BigInteger width();

    /**
     * @return Provides a hint to client applications that this CalculationProperty contains a result
     *         that is uniquely representative of a Dimension instance that the CalculationProperty is
     *         associated with.
     */
    Boolean isDefaultMeasure();

    /**
     * @return Provides the ability to place this attribute in the Default Details collection of the
     *         Dimension. This collection is an ordered set of DimensionAttribute types,
     *         CalculationProperty types, and RelationshipEnd elements. A positive value specifies
     *         participation in the collection. The collection is sorted in ascending order of this
     *         element.
     */
    BigInteger defaultDetailsPosition();

    /**
     * @return Provides the ability to place this attribute in the Sort Properties collection of the
     *         Dimension. This collection is an ordered set of DimensionAttribute types,
     *         CalculationProperty types, and RelationshipEnd elements. Client applications can
     *         interpret this collection as a suggestion for how to perform a multi-column sort on this
     *         Dimension. A positive value specifies participation in the collection. The collection is
     *         sorted in ascending order of this element.
     */
    BigInteger sortPropertiesPosition();

    /**
     * @return Provides a hint to client applications that this CalculationProperty need not be
     *         displayed in clients. For example, a client might mark an automatically generated
     *         calculation as IsSimple so that it remains visible to the client, but is filtered out of
     *         any user views.
     */
    Boolean isSimpleMeasure();

}
