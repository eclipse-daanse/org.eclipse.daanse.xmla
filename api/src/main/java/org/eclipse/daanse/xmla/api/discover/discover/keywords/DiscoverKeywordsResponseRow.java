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
package org.eclipse.daanse.xmla.api.discover.discover.keywords;

/**
 * This schema rowset returns information about keywords that are reserved by the XMLA server. If
 * you call the Discover method with the DISCOVER_KEYWORDS enumeration value in the RequestType
 * element, the Discover method returns the DISCOVER_KEYWORDS rowset.
 */
public interface DiscoverKeywordsResponseRow {

    /**
     * @return The keyword string.
     */
    String keyword();

}
