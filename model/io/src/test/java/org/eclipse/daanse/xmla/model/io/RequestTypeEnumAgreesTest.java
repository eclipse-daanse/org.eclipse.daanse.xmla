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
package org.eclipse.daanse.xmla.model.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.daanse.xmla.model.xmla.RequestTypeEnum;
import org.junit.jupiter.api.Test;

/**
 * Two lists that have to agree, and did not.
 * <p>
 * A rowset needs an entry in two places to be usable: an EClass in a rowset
 * model, which decides the columns and the inline schema, and a literal in
 * {@link RequestTypeEnum}, which is how the request is parsed off the wire.
 * Neither implies the other, and only this test forces them together.
 * <p>
 * A rowset with a model class, a GUID, restrictions, a provider and a row in
 * {@code DISCOVER_SCHEMA_ROWSETS} but no enum literal is announced to every
 * client and refused to every client that takes the offer up — a promise made
 * and broken, where saying nothing would have cost nothing.
 */
class RequestTypeEnumAgreesTest {

    @Test
    void everyRowsetInTheCatalogueCanBeAskedFor() {
        Set<String> onTheWire = RequestTypeEnum.VALUES.stream().map(RequestTypeEnum::getLiteral)
                .collect(Collectors.toSet());

        Set<String> unaskable = RowsetCatalog.requestTypes().stream().filter(name -> !onTheWire.contains(name))
                .collect(Collectors.toCollection(java.util.TreeSet::new));

        assertThat(unaskable).as("these are modelled, announced in DISCOVER_SCHEMA_ROWSETS, and rejected "
                + "when a client asks for them, because the request type never parses").isEmpty();
    }

    /**
     * The other direction is allowed, and deliberately so.
     * <p>
     * The enum is the vocabulary of the protocol; the catalogue is what this build
     * happens to model. A literal without a model class parses and is then refused
     * as unsupported, which is the truthful answer — unlike the reverse.
     */
    @Test
    void theEnumMayNameMoreThanIsModelled() {
        Set<String> modelled = RowsetCatalog.requestTypes();

        assertThat(RequestTypeEnum.VALUES).as("sanity: the enum is populated at all").isNotEmpty();
        assertThat(RequestTypeEnum.VALUES.stream().map(RequestTypeEnum::getLiteral))
                .as("and it covers what is modelled").containsAll(modelled);
    }
}
