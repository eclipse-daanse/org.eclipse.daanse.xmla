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
package org.eclipse.daanse.xmla.model.rowset.tabular;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.daanse.xmla.model.rowset.tabular.restrictions.RestrictionsTabularPackage;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.jupiter.api.Test;

/**
 * What the tabular model has to look like for a TOM client to survive reading
 * it.
 * <p>
 * This package is built but deliberately not registered in
 * {@code RowsetCatalog.MODELS}: nothing answers a {@code TMSCHEMA_*} request
 * yet, and a rowset announced but unanswerable is worse than one never
 * mentioned. So the catalogue's own checks do not reach these classes, and this
 * is what does instead — reading the packages directly.
 * <p>
 * The requirements here are not style. A TOM client asks for all forty-eight
 * rowsets in one batch and correlates the answers <em>by position</em>, so one
 * rowset missing shifts every following one; and it then looks two of them up by
 * the {@code name} attribute of the answer's root element, which is the singular
 * object type rather than the request type.
 */
class TabularModelShapeTest {

    private static final String DAANSE = "https://www.daanse.org/spec/xmla/rowset/1.0";
    private static final String EMD = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";

    /** What a TOM client of 19.114.12 asks for, and therefore what must be here. */
    private static final int ROWSETS = 48;

    /**
     * The one {@code *ID} that is not an object reference.
     * <p>
     * A member's identifier at its identity provider — a string by nature, and the
     * client reads it as one. Every other {@code *ID} is an {@code ObjectId} the
     * client casts to {@code ulong} without checking.
     */
    private static final String NOT_AN_OBJECT_ID = "MemberID";

    private static Map<String, String> details(EModelElement element, String source) {
        EAnnotation annotation = element.getEAnnotation(source);
        return annotation == null ? Map.of() : annotation.getDetails().map();
    }

    private static Map<String, EClass> rowsetsOf(EPackage ePackage, boolean restrictions) {
        Map<String, EClass> found = new LinkedHashMap<>();
        for (EClassifier classifier : ePackage.getEClassifiers()) {
            Map<String, String> daanse = details(classifier, DAANSE);
            String requestType = daanse.get("requestType");
            if (requestType != null && restrictions == "restrictions".equals(daanse.get("role"))) {
                found.put(requestType, (EClass) classifier);
            }
        }
        return found;
    }

    @Test
    void allFortyEightRowsetsAreThere() {
        Map<String, EClass> rows = rowsetsOf(RowsetTabularPackage.eINSTANCE, false);

        assertThat(rows).as("a TOM client correlates the batch by position; one rowset short "
                + "and every answer after it lands on the wrong object type").hasSize(ROWSETS);
    }

    @Test
    void everyRowsetHasItsRestrictions() {
        Map<String, EClass> rows = rowsetsOf(RowsetTabularPackage.eINSTANCE, false);
        Map<String, EClass> restrictions = rowsetsOf(RestrictionsTabularPackage.eINSTANCE, true);

        assertThat(restrictions.keySet()).as("without a restrictions class a rowset has no "
                + "RestrictionsMask, and DISCOVER_SCHEMA_ROWSETS cannot describe it")
                .containsExactlyInAnyOrderElementsOf(rows.keySet());
    }

    /**
     * The root element's {@code name}, which AMO needs and no specification
     * mentions.
     */
    @Test
    void everyRowsetNamesItsWireRootAndTheNamesAreDistinct() {
        Map<String, EClass> rows = rowsetsOf(RowsetTabularPackage.eINSTANCE, false);
        Map<String, String> byRootName = new LinkedHashMap<>();
        List<String> missing = new ArrayList<>();
        List<String> clashing = new ArrayList<>();

        for (Map.Entry<String, EClass> rowset : rows.entrySet()) {
            String rootName = details(rowset.getValue(), DAANSE).get("rowsetName");
            if (rootName == null) {
                missing.add(rowset.getKey());
            } else {
                String previous = byRootName.put(rootName, rowset.getKey());
                if (previous != null) {
                    clashing.add(rootName + ": " + previous + " and " + rowset.getKey());
                }
            }
        }

        assertThat(missing).as("AMO reads the table name off the root element's name attribute "
                + "and then asks for Tables[\"Model\"] and Tables[\"Partition\"] by it").isEmpty();
        assertThat(clashing).as("two rowsets under one root name would collide in the client's DataSet").isEmpty();
    }

    @Test
    void everyRowsetIsPlacedInTheBatchAndTheOrderIsGapless() {
        List<Integer> order = new ArrayList<>();
        for (EClass rowset : rowsetsOf(RowsetTabularPackage.eINSTANCE, false).values()) {
            String at = details(rowset, DAANSE).get("discoverOrder");
            if (at != null) {
                order.add(Integer.valueOf(at));
            }
        }

        assertThat(order).as("the position in the client's batch is the only thing that ties an answer "
                + "to a request; it has to be recorded, and it has to be 0..47 without a hole")
                .containsExactlyInAnyOrderElementsOf(java.util.stream.IntStream.range(0, ROWSETS).boxed().toList());
    }

    @Test
    void everyIdentifierIsAnUnsignedLong() {
        List<String> wrong = new ArrayList<>();
        for (EPackage ePackage : List.of(RowsetTabularPackage.eINSTANCE, RestrictionsTabularPackage.eINSTANCE)) {
            for (EClassifier classifier : ePackage.getEClassifiers()) {
                if (!(classifier instanceof EClass eClass)) {
                    continue;
                }
                for (EStructuralFeature feature : eClass.getEStructuralFeatures()) {
                    String name = details(feature, EMD).get("name");
                    if (name == null || !name.endsWith("ID") || NOT_AN_OBJECT_ID.equals(name)) {
                        continue;
                    }
                    String type = feature.getEType().getName();
                    if (!"UnsignedLong".equals(type)) {
                        wrong.add(eClass.getName() + "." + name + " is " + type);
                    }
                }
            }
        }

        assertThat(wrong).as("the client casts every object identifier to ulong unchecked; anything "
                + "else is an InvalidCastException nobody catches").isEmpty();
    }

    /**
     * {@code Version} is read outside the ordinary column loop and is the one column
     * whose absence is a hard throw rather than a default.
     */
    @Test
    void theModelRowsetCarriesVersion() {
        EClass model = rowsetsOf(RowsetTabularPackage.eINSTANCE, false).get("TMSCHEMA_MODEL");

        assertThat(model).isNotNull();
        assertThat(model.getEStructuralFeatures()).as("a TMSCHEMA_MODEL without a Version column is "
                + "ResponseFormatException(Exception_NoVersionColumnInRowset), not a missing value")
                .anyMatch(feature -> "Version".equals(details(feature, EMD).get("name")));
    }
}
