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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.junit.jupiter.api.Test;

/**
 * The GUIDs a client resolves a rowset by, and why every one of them has to be
 * right.
 * <p>
 * A client knows a rowset by name or by GUID. ADOMD.NET hard-wires
 * twenty-eight; for every other rowset it fills its lookup table from the
 * {@code SchemaGuid} column of our own {@code DISCOVER_SCHEMA_ROWSETS}, and only
 * where that column is present and not empty. Where it is missing,
 * {@code GetSchemaDataSet(Guid)} throws before a request ever reaches us. So a
 * missing GUID is not a blemish: it is a rowset unreachable by half the API.
 * <p>
 * A <em>wrong</em> GUID is worse than a missing one — it points the client at
 * some other rowset, and nothing in a running server reveals it. Hence this
 * test, and hence the {@code guidSource} detail every value carries.
 */
class GuidCatalogTest {

    /** Canonical 8-4-4-4-12. Case is free: the client parses case-insensitively. */
    private static final Pattern SHAPE = Pattern
            .compile("[0-9a-fA-F]{8}(-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}");

    /**
     * The one duplicate that is not ours to fix.
     * <p>
     * A real Analysis Services advertises the same GUID on both of these rows, and
     * ADOMD.NET declares it twice under two field names. Dropping it from one would
     * not make the other reachable — it would only make both unreachable by GUID.
     */
    private static final Set<String> SHARE_ONE_GUID = Set.of("DMSCHEMA_MINING_MODEL_XML",
            "DMSCHEMA_MINING_MODEL_CONTENT_PMML");

    private static Optional<String> detail(EClass eClass, String key) {
        EAnnotation annotation = eClass.getEAnnotation(RowsetCatalog.ANNOTATION);
        return annotation == null ? Optional.empty() : Optional.ofNullable(annotation.getDetails().get(key));
    }

    private static Map<String, EClass> rowsets() {
        Map<String, EClass> all = new LinkedHashMap<>();
        for (String requestType : RowsetCatalog.requestTypes()) {
            RowsetCatalog.forRequestType(requestType).ifPresent(eClass -> all.put(requestType, eClass));
        }
        return all;
    }

    @Test
    void everyRowsetEitherHasAGuidOrSaysWhyNot() {
        List<String> silent = new ArrayList<>();
        for (Map.Entry<String, EClass> rowset : rowsets().entrySet()) {
            boolean has = RowsetCatalog.guidOf(rowset.getValue()).filter(guid -> !guid.isEmpty()).isPresent();
            if (!has && detail(rowset.getValue(), "noGuidBecause").isEmpty()) {
                silent.add(rowset.getKey());
            }
        }

        assertThat(silent).as("a rowset without a GUID is unreachable by GetSchemaDataSet(Guid); "
                + "that may be unavoidable, but it must be written down as noGuidBecause, not left silent")
                .isEmpty();
    }

    @Test
    void everyGuidIsAWellFormedGuid() {
        for (Map.Entry<String, EClass> rowset : rowsets().entrySet()) {
            RowsetCatalog.guidOf(rowset.getValue()).ifPresent(guid -> {
                assertThat(guid).as("%s", rowset.getKey()).matches(SHAPE);
                // Not the same test: the shape admits values UUID.fromString rejects, and
                // the client parses with the equivalent of the latter.
                UUID.fromString(guid);
            });
        }
    }

    @Test
    void noTwoRowsetsShareAGuidExceptTheOnePairThatMust() {
        Map<String, List<String>> byGuid = new LinkedHashMap<>();
        for (Map.Entry<String, EClass> rowset : rowsets().entrySet()) {
            RowsetCatalog.guidOf(rowset.getValue()).ifPresent(
                    guid -> byGuid.computeIfAbsent(guid.toUpperCase(java.util.Locale.ROOT), any -> new ArrayList<>())
                            .add(rowset.getKey()));
        }

        List<List<String>> shared = byGuid.values().stream().filter(names -> names.size() > 1)
                .filter(names -> !SHARE_ONE_GUID.containsAll(names)).toList();

        assertThat(shared).as("two rowsets on one GUID means a client resolving by GUID can only ever "
                + "reach whichever its lookup table wrote last").isEmpty();
    }

    /**
     * Where a GUID came from, for every GUID no specification states.
     * <p>
     * [MS-SSAS] publishes no rowset GUIDs at all — a {@code source=MS-SSAS-*} on a
     * rowset says where its <em>columns</em> come from. So any GUID we carry was
     * either read off a real server, taken from a decompiled client, or taken from
     * OLE DB; and which of the three it was is the only thing that lets a later
     * reader weigh it.
     */
    @Test
    void aGuidNoSpecificationStatesSaysWhereItCameFrom() {
        List<String> unattributed = new ArrayList<>();
        for (Map.Entry<String, EClass> rowset : rowsets().entrySet()) {
            if (RowsetCatalog.guidOf(rowset.getValue()).isEmpty()) {
                continue;
            }
            String source = RowsetCatalog.sourceOf(rowset.getValue()).orElse("");
            boolean fromOleDb = source.startsWith("OLEDB") || source.equals("PROPRIETARY") || source.equals("INFERRED");
            if (!fromOleDb && detail(rowset.getValue(), "guidSource").isEmpty()) {
                unattributed.add(rowset.getKey());
            }
        }

        assertThat(unattributed).as("these carry a GUID that no specification states and no note attributes, "
                + "so nobody can tell whether it is right").isEmpty();
    }
}
