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
 * The TableNotification complex type represents a table notification for proactive caching.
 */
public interface TableNotification {

    /**
     * @return The name of the table.
     */
    String dbTableName();

    /**
     * @return The name of the schema.
     */
    Optional<String> dbSchemaName();

}
