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
*   Stefan Bischof (bipolis.org)
// Licensed to Julian Hyde under one or more contributor license
// agreements. See the NOTICE file distributed with this work for
// additional information regarding copyright ownership.
//
// Julian Hyde licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except in
// compliance with the License. You may obtain a copy of the License at:
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
*/
package org.eclipse.daanse.xmla.api;

import java.util.List;
import java.util.Set;

/**
 * Enumerated value that belongs to a set of constants in the XML for Analysis
 * (XMLA) specification.
 *
 * Every {@code enum} E that implements this interface also has a method to
 * get the of all its values:
 *
 * public static Dictionary&lt;E&gt; getDictionary();
 *
 * Here is a collection of enum classes and the prefix used to generate
 * their XMLA constant names.
 *
 *
 * @author jhyde
 */
public interface XmlaConstant {
    /**
     * Returns the name of this constant as specified by XMLA.
     *
     * <p>Often the name is an enumeration-specific prefix plus the name of
     * the Java enum constant. For example,
     * Dimension.Type has
     * prefix "MD_DIMTYPE_", and therefore this method returns
     * "MD_DIMTYPE_PRODUCTS" for the enum constant
     * Dimension.Type#PRODUCTS.
     *
     * @return ordinal code as specified by XMLA.
     */
    String xmlaName();

    /**
     * Returns the description of this constant.
     *
     * @return Description of this constant.
     */
    String getDescription();

    /**
     * Returns the code of this constant as specified by XMLA.
     *
     * <p>For example, the XMLA specification says that the ordinal of
     * MD_DIMTYPE_PRODUCTS is 8, and therefore this method returns 8
     * for Dimension.Type#PRODUCTS.
     *
     * @return ordinal code as specified by XMLA.
     */
    int xmlaOrdinal();

    interface Dictionary<E extends Enum<E> & XmlaConstant> {

        /**
         * Returns the enumeration value with the given ordinal in the XMLA
         * specification, or null if there is no such.
         *
         * @param xmlaOrdinal XMLA ordinal
         * @return Enumeration value
         */
        E forOrdinal(int xmlaOrdinal);

        /**
         * Returns the enumeration value with the given name in the XMLA
         * specification, or null if there is no such.
         *
         * @param xmlaName XMLA name
         * @return Enumeration value
         */
        E forName(String xmlaName);

        /**
         * Creates a set of values by parsing a mask.
         *
         * @param xmlaOrdinalMask Bit mask
         * @return Set of E values
         */
        Set<E> forMask(int xmlaOrdinalMask);

        /**
         * Converts a set of enum values to an integer by logical OR-ing their
         * codes.
         *
         * @param set Set of enum values
         * @return Bitmap representing set of enum values
         */
        int toMask(Set<E> set);

        /**
         * Returns all values of the enum.
         *
         * <p>This method may be more efficient than
         * {@link Class#getEnumConstants()} because the latter is required to
         * create a new array every call to prevent corruption.
         *
         * @return List of enum values
         */
        List<E> getValues();

        /**
         * Returns the class that the enum values belong to.
         *
         * @return enum class
         */
        Class<E> getEnumClass();
    }
}

// End XmlaConstant.java
