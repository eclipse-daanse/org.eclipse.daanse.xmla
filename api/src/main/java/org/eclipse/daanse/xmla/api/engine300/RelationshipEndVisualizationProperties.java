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

public interface RelationshipEndVisualizationProperties {

    /**
     * @return Provides a hint to client applications to suggest the position that this relationship
     *         might hold among the other elements that share the same display folder. Default value -1
     */
    BigInteger folderPosition();

    /**
     * @return Provides a hint to client applications to suggest how to create unambiguous names for
     *         this attribute. The following values are allowed: Element "None" – Use the attribute
     *         name. "Context" – Use the incoming relationship name. "Merge" – Attending to language
     *         grammar, concatenate the incoming relationship name and the attribute name. Default value
     *         "None"
     */
    String contextualNameRule();

    /**
     * @return Provides a hint to client applications to suggest the position that this relationship
     *         might hold among the other elements that can be used to summarize a record. Default value
     *         -1
     */
    BigInteger defaultDetailsPosition();

    /**
     * @return Provides a hint to client applications to suggest the position that this relationship
     *         might hold among the other user- readable elements that uniquely identify the record.
     *         Default value -1
     */
    BigInteger displayKeyPosition();

    /**
     * @return Provides a hint to client applications to suggest the position that this relationship
     *         might hold among the other common elements that can be used to identify the record.
     *         Default value -1
     */
    BigInteger commonIdentifierPosition();

    /**
     * @return An indication if this measure is automatically used to summarize the dimension. Default
     *         value false
     */
    Boolean isDefaultMeasure();

    /**
     * @return An indication if this image is used to represent a record. Default value false
     */
    Boolean isDefaultImage();

    /**
     * @return Provides the ability to place this relationship in the Sort Properties collection of the
     *         Dimension. Default value -1
     */
    BigInteger sortPropertiesPosition();

}
