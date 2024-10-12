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

import java.util.Optional;

/**
 *
 */
public interface Annotation {

    /**
     * @return This element SHOULD<92> be in a style that references the vendor's XML namespace, to
     *         prevent collisions in sharing of information contained in other Annotation objects. The
     *         Name element MUST be unique within the collection of Annotations that is contained within
     *         an individual object.
     */
    String name();

    /**
     * @return This element determines the way in which Annotation objects are exposed. By default,
     *         Annotation objects are exposed only in DISCOVER_XML_METADATA, and are not visible to
     *         client software. If Visibility is set to SchemaRowset, then Annotation object information
     *         is exposed as a column by schema rowset requests.
     */
    Optional<String> visibility();

    /**
     * @return The content of the Annotation.
     */
    Optional<java.lang.Object> value();
}
