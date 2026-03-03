/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.api.discover.mdschema.inputdatasources;

import java.time.LocalDateTime;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.common.enums.DatasourceTypeEnum;

/**
 * This schema rowset describes each hierarchy within a particular dimension.
 */
public interface MdSchemaInputDatasourcesResponseRow {

    /**
     * @return The name of the catalog.
     */
    Optional<String> catalogName();

    /**
     * @return The name of the schema.
     */
    Optional<String> schemaName();

    /**
     * @return The name of the data source object.
     */
    Optional<String> datasourceName();

    /**
     * @return The type of the data source. Valid values include the
     *     following:
     *         Relational
     *         Olap
     */
    Optional<DatasourceTypeEnum> datasourceType();

    /**
     * @return The date that the data source was created.
     */
    Optional<LocalDateTime> createdOn();

    /**
     * @return The date and time that the data source was last
     *    modified.
     */
    Optional<LocalDateTime> lastSchemaUpdate();

    /**
     * @return The description of the action.
     */
    Optional<String> description();

    /**
     * @return The timeout of the data source in seconds.
     */
    Optional<String> timeout();

    /**
     * @return The external data source name.
     */
    Optional<String> dbmsName();

    /**
     * @return The external data source version.
     */
    Optional<String> dbmsVersion();
}
