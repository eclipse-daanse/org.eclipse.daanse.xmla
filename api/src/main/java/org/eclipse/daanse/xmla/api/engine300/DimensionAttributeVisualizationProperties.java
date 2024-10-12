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
 * The DimensionAttributeVisualizationProperties complex type specifies visualization properties for
 * a calculated result. This type is defined in the eng300 namespace.
 */
public interface DimensionAttributeVisualizationProperties {

    /**
     * @return Provides a hint to client applications to suggest the position this attribute might hold
     *         among the other elements that share the same display folder (AttributeTranslation).
     */
    BigInteger folderPosition();

    /**
     * @return Provides a hint to client applications to suggest how to create unambiguous names for
     *         this attribute. The following values are allowed: "None" – Use the attribute name
     *         "Context" – Use the incoming relationship name. "Merge" – Attending to language grammar,
     *         concatenate the incoming relationship name and the attribute name.
     */
    String contextualNameRule();

    /**
     * @return Provides a hint to client applications to suggest how to justify this attribute when
     *         displayed. The following values are allowed: "Default" – Use the alignment that is
     *         appropriate for the attribute’s data type. "Left" – Align left. "Right" – Align right.
     *         "Center" – Center.
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
     *         attribute. The following values are allowed: "Default" – Use the sort direction that is
     *         appropriate for the attribute’s data type. "Ascending" – Sort in ascending order.
     *         "Descending" – Sort in descending order.
     */
    String sortDirection();

    /**
     * @return Provides a hint to client applications that specifies a string to be associated with the
     *         values of this attribute.
     */
    String units();

    /**
     * @return Provides a hint to client applications that suggests the length (in characters) to
     *         reserve to display this attribute.
     */
    BigInteger width();

    /**
     * @return Provides the ability to place this attribute in the Default Details collection of the
     *         Dimension. This collection is an ordered set of DimensionAttribute types,
     *         CalculationProperty types, and Relationship Ends. A positive value indicates
     *         participation in the collection. The collection is sorted in ascending order of this
     *         element.
     */
    BigInteger defaultDetailsPosition();

    /**
     * @return Provides the ability to place this attribute in the Common Identifier collection of the
     *         Dimension. This collection is an ordered set of DimensionAttribute types and Relationship
     *         Ends. Client applications can interpret this collection as a suggestion to use such items
     *         to perform a multi-column sort on this Dimension. A positive value indicates
     *         participation in the collection. The collection is sorted in ascending order of this
     *         element.
     */
    BigInteger commonIdentifierPosition();

    /**
     * @return Provides the ability to place this attribute in the Sort Properties collection of the
     *         Dimension. This collection is an ordered set of DimensionAttribute types,
     *         CalculationProperty types, and Relationship Ends. Client applications can interpret this
     *         collection as a suggestion as to how to perform a multi-column sort on this Dimension. A
     *         positive value specifies participation in the collection. The collection is sorted in
     *         ascending order of this element.
     */
    BigInteger sortPropertiesPosition();

    /**
     * @return Provides the ability to place this attribute in the Display Key collection of the
     *         Dimension. This collection is an ordered set of DimensionAttribute types and Relationship
     *         Ends. Client applications can interpret this collection as a suggestion as to how to
     *         perform a multi-column sort on this Dimension. A positive value indicates participation
     *         in the collection. The collection is sorted in ascending order of this element.
     */
    BigInteger displayKeyPosition();

    /**
     * @return Provides a hint to client applications that this attribute contains an image that is
     *         representative of its Dimension instance.
     */
    Boolean isDefaultImage();

    /**
     * @return Provides a hint to client applications to suggest how to aggregate instances of this
     *         attribute. The following values are allowed: "Default" – Use the function that is
     *         appropriate for the attribute’s data type. "None" – Data in this property is not suited
     *         for aggregation. "Sum" – Aggregate this dimension with Sum. "Min" – Aggregate this
     *         dimension with Min. "Max" – Aggregate this dimension with Max. "Count" – Aggregate this
     *         dimension with Count. "Average" – Aggregate this dimension with Average.
     */
    String defaultAggregateFunction();
}
