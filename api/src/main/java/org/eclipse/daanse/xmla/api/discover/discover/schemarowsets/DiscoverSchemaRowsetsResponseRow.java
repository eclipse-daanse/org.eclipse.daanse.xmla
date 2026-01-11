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
package org.eclipse.daanse.xmla.api.discover.discover.schemarowsets;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Restriction;

/**
 * This schema rowset returns the names, restrictions, description, and other information for all
 * Discover requests.
 */
public interface DiscoverSchemaRowsetsResponseRow {

    /**
     * @return The name of the Discover request.
     */
    String schemaName();

    /**
     * @return The GUID of the Discover request.
     */
    Optional<String> schemaGuid();

    /**
     * @return The restrictions supported by the Discover request.
     */
    Optional<List<Restriction>> restrictions();

    /**
     * @return The description of the Discover request.
     */
    Optional<String> description();

    /**
     * @return The lowest N bits set to 1, where N is the number of restrictions.
     */
    Optional<Long> restrictionsMask();

}
