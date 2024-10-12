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
package org.eclipse.daanse.xmla.api.discover.discover.properties;

import org.eclipse.daanse.xmla.api.common.enums.AccessEnum;

import java.util.Optional;

/**
 * This schema rowset returns a list of information and values about the properties that are
 * supported by the server for the specified data source.
 */
public interface DiscoverPropertiesResponseRow {

    /**
     * @return The name of the property.
     */
    String propertyName();

    /**
     * @return A description of the property.
     */
    Optional<String> propertyDescription();

    /**
     * @return The XSD data type of the property.
     */
    Optional<String> propertyType();

    /**
     * @return The access for the property. The value can be Read, Write, or ReadWrite.
     */
    AccessEnum propertyAccessType();

    /**
     * @return When true, indicates that a property is required; otherwise false.
     */
    Optional<Boolean> required();

    /**
     * @return The current value of the property.
     */
    Optional<String> value();

}
