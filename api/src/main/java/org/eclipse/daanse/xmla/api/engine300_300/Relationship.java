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

/**
 * The Relationship complex type specifies a relationship between in-memory Dimensions.
 */
public interface Relationship {

    /**
     * @return The object ID string.
     */
    String id();

    /**
     * @return Provides a hint to client applications that this Relationship is not to be exposed.
     */
    boolean visible();

    /**
     * @return A collection of properties that defines the characteristics of the Relationship-End.
     */
    RelationshipEnd fromRelationshipEnd();

    /**
     * @return A collection of properties that defines the characteristics of the Relationship-End.
     */
    RelationshipEnd toRelationshipEnd();
}
