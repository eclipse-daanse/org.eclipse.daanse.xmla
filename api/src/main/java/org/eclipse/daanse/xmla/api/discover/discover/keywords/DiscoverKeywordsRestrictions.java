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

import org.eclipse.daanse.xmla.api.annotation.Restriction;

import java.util.Optional;

import static org.eclipse.daanse.xmla.api.common.properties.XsdType.XSD_STRING;

public interface DiscoverKeywordsRestrictions {
    String RESTRICTIONS_KEYWORD = "Keyword";

    /**
     * @return The keyword string.
     */
    @Restriction(name = RESTRICTIONS_KEYWORD, type = XSD_STRING)
    Optional<String> keyword();

}
