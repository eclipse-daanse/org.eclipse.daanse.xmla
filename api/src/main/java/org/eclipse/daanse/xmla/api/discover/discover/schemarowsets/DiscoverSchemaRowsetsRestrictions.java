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
*/
package org.eclipse.daanse.xmla.api.discover.discover.schemarowsets;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;

public interface DiscoverSchemaRowsetsRestrictions {
    String RESTRICTIONS_SCHEMA_NAME = "SchemaName";

    @Restriction(name = RESTRICTIONS_SCHEMA_NAME, type = XSD_STRING)
    Optional<String> schemaName();

}
