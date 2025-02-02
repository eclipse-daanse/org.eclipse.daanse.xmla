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
package org.eclipse.daanse.xmla.api.discover.discover.properties;

import org.eclipse.daanse.xmla.api.annotation.Restriction;

import java.util.List;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface DiscoverPropertiesRestrictions {
    String RESTRICTIONS_PROPERTY_NAME = "PropertyName";

    @Restriction(name = RESTRICTIONS_PROPERTY_NAME, type = XSD_STRING)
    List<String> propertyName();
}
