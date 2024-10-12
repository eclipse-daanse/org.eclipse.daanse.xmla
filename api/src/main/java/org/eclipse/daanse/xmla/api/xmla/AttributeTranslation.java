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
 * This complex type represents a translation of a DimensionAttribute in a specific language. The
 * AttributeTranslation type is an extension of the Translation type, and includes all its elements.
 */
public interface AttributeTranslation {

    /**
     * @return The locale ID of the language. For more details on locale identifiers, see [MS-LCID].
     */
    long language();

    /**
     * @return The caption of the object in the language represented by the Language element.
     */
    Optional<String> caption();

    /**
     * @return The description for the object.
     */
    Optional<String> description();

    /**
     * @return The folder in which the object is displayed.
     */
    Optional<String> displayFolder();

    /**
     * @return A collection of Annotation objects.
     */
    Optional<List<Annotation>> annotations();

    /**
     * @return The source column for the attribute member captions.
     */
    Optional<DataItem> captionColumn();

    /**
     * @return The caption template for data members. This applies only if Usage is set to Parent in the
     *         DimensionAttribute.
     */
    Optional<String> membersWithDataCaption();
}
