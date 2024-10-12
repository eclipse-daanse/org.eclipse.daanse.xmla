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
package org.eclipse.daanse.xmla.api.discover.discover.enumerators;

import java.util.Optional;

/**
 * This schema rowset returns a list of names, data types, and enumeration values of enumerators
 * supported by the XMLA Provider for a specific data source.
 */
public interface DiscoverEnumeratorsResponseRow {

    /**
     * @return The name of the enumerator that contains a set of values.
     */
    String enumName();

    /**
     * @return A localizable description of the enumerator.
     */
    Optional<String> enumDescription();

    /**
     * @return The data type of the enumeration values.
     */
    String enumType();

    /**
     * @return The name of one of the value elements in the enumerator set. Example: TDP
     */
    String elementName();

    /**
     * @return This column is optional. A localizable description of the element.
     */
    Optional<String> elementDescription();

    /**
     * @return The value of the element. This is always a string representing a number, and it never has
     *         a leading 0. Example: 1
     */
    Optional<String> elementValue();

}
