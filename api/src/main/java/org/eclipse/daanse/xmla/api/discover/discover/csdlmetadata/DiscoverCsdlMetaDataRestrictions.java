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
package org.eclipse.daanse.xmla.api.discover.discover.csdlmetadata;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.annotation.Restriction;

public interface DiscoverCsdlMetaDataRestrictions {

    @Restriction(name = "CATALOG_NAME", type = XSD_STRING, order = 0)
    Optional<String> catalogName();

    @Restriction(name = "PERSPECTIVE_NAME", type = XSD_STRING, order = 1)
    Optional<String> perspectiveName();

    @Restriction(name = "VERSION", type = XSD_STRING, order = 2)
    Optional<String> version();
}
