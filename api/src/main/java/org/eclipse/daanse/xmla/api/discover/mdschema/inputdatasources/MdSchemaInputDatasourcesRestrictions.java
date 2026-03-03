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

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;
import org.eclipse.daanse.xmla.api.common.enums.DatasourceTypeEnum;

public interface MdSchemaInputDatasourcesRestrictions {

    String RESTRICTIONS_CATALOG_NAME = "CATALOG_NAME";
    String RESTRICTIONS_SCHEMA_NAME = "SCHEMA_NAME";
    String RESTRICTIONS_DATASOURCE_NAME = "DATASOURCE_NAME";
    String RESTRICTIONS_DATASOURCE_TYPE = "DATASOURCE_TYPE";
    /**
     * @return The name of the catalog.
     */
    @Restriction(name = RESTRICTIONS_CATALOG_NAME, type = XSD_STRING, order = 0)
    Optional<String> catalogName();

    /**
     * @return The name of the schema.
     */
    @Restriction(name = RESTRICTIONS_SCHEMA_NAME, type = XSD_STRING, order = 1)
    Optional<String> schemaName();

    /**
     * @return The name of the data source object.
     */
    @Restriction(name = RESTRICTIONS_DATASOURCE_NAME, type = XSD_STRING, order = 2)
    Optional<DatasourceTypeEnum> datasourceName();

    /**
     * @return The name of the data source object.
     */
    @Restriction(name = RESTRICTIONS_DATASOURCE_TYPE, type = XSD_STRING, order = 3)
    Optional<DatasourceTypeEnum> datasourceType();

}
