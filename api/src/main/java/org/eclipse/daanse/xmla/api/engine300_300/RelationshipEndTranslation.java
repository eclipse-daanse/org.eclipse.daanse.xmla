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

import org.eclipse.daanse.xmla.api.xmla.Annotation;

public interface RelationshipEndTranslation {

    /**
     * @return The locale ID of the language. For more details on locale identifiers, see [MS-LCID].
     */
    long language();

    /**
     * @return The caption of the object in the language represented by the Language element.
     */
    String caption();

    /**
     * @return The caption of a collection of objects.
     */
    String collectionCaption();

    /**
     * @return The object description.
     */
    String description();

    /**
     * @return The folder in which the object is displayed.
     */
    String displayFolder();

    /**
     * @return A collection of Annotation objects.
     */
    List<Annotation> annotations();
}
