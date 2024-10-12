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
package org.eclipse.daanse.xmla.api.discover.mdschema.sets;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;
import org.eclipse.daanse.xmla.api.common.enums.SetEvaluationContextEnum;

/**
 * This Discover element describes any sets that are currently defined in a database, including
 * session- scoped sets.
 */
public interface MdSchemaSetsResponseRow {

    /**
     * @return The name of the database.
     */
    Optional<String> catalogName();

    /**
     * @return The name of the schema.
     */
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    Optional<String> cubeName();

    /**
     * The name of the set, as specified in the CREATE SET statement.
     */
    Optional<String> setName();

    /**
     * The scope of the set. The set can be a session-defined set or a global-defined set. This column
     * can have one of the following values: 1 - Global 2 – Session
     */
    Optional<ScopeEnum> scope();

    /**
     * @return A description of the set.
     */
    Optional<String> description();

    /**
     * @return The expression for the set.
     */
    Optional<String> expression();

    /**
     * @return A comma-delimited list of hierarchies included in the set.
     */
    Optional<String> dimension();

    /**
     * @return A caption associated with the set.
     */
    Optional<String> setCaption();

    /**
     * @return The display folder.
     */
    Optional<String> setDisplayFolder();

    /**
     * @return The context for the set. The set can be static or dynamic. This column can have one of
     *         the following values: 1 - STATIC 2 – DYNAMIC
     */
    Optional<SetEvaluationContextEnum> setEvaluationContext();

}
