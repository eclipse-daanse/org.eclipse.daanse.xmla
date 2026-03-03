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
package org.eclipse.daanse.xmla.api.discover.mdschema.measures;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;

public interface MdSchemaMeasuresRestrictions {

    String RESTRICTIONS_CATALOG_NAME = "CATALOG_NAME";
    String RESTRICTIONS_SCHEMA_NAME = "SCHEMA_NAME";
    String RESTRICTIONS_CUBE_NAME = "CUBE_NAME";
    String RESTRICTIONS_MEASURE_NAME = "MEASURE_NAME";
    String RESTRICTIONS_MEASURE_UNIQUE_NAME = "MEASURE_UNIQUE_NAME";
    String RESTRICTIONS_MEASUREGROUP_NAME = "MEASUREGROUP_NAME";

    /**
     * @return The name of the database.
     */
    @Restriction(name = RESTRICTIONS_CATALOG_NAME, type = XSD_STRING, order = 0)
    Optional<String> catalogName();

    /**
     * @return The name of the schema.
     */
    @Restriction(name = RESTRICTIONS_SCHEMA_NAME, type = XSD_STRING, order = 1)
    Optional<String> schemaName();

    /**
     * @return The name of the cube.
     */
    @Restriction(name = RESTRICTIONS_CUBE_NAME, type = XSD_STRING, order = 2)
    Optional<String> cubeName();

    /**
     * @return The name of the measure.
     */
    @Restriction(name = RESTRICTIONS_MEASURE_NAME, type = XSD_STRING, order = 3)
    Optional<String> measureName();

    /**
     * The unique name of the measure
     */
    @Restriction(name = RESTRICTIONS_MEASURE_UNIQUE_NAME, type = XSD_STRING, order = 4)
    Optional<String> measureUniqueName();

    /**
     * The name of the measure group to which the measure belongs.
     */
    @Restriction(name = RESTRICTIONS_MEASUREGROUP_NAME, type = XSD_STRING, order = 5)
    Optional<String> measureGroupName();
}
