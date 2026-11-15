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

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The model can carry every rowset Analysis Services 2017 offers, in the form it answers with.
 * <p>
 * It is checked against answers of Analysis Services 2017 in multidimensional mode that lie outside this repository,
 * see {@link RowsetAnswers}; without them the tests are skipped. They are the {@code DISCOVER_SCHEMA_ROWSETS} rows (77
 * rowsets, with GUID, restrictions and mask) and, for the 71 rowsets that answer with rows or an empty result, the
 * inline schema of the answer -- names and types only, no data. A rowset the server refuses in that mode (Power BI,
 * tabular) has no schema there and is held to its row in {@code DISCOVER_SCHEMA_ROWSETS}.
 * <p>
 * What is checked is what a reader of the answer depends on: every column of the answer is a feature of the rowset's
 * row class, in the same order, nested columns in the nested class, and the type this model writes into its own
 * answer schema is the one Analysis Services writes. The model may know more -- columns and restrictions of later
 * versions of [MS-SSAS] -- since a client that reads by name is not hurt by them; it may not know less, because a reader
 * that fails on an unknown column fails on the whole answer.
 */
class EveryRowsetOfAnalysisServicesIsInTheModelTest {

    private static final String XSD = "http://www.w3.org/2001/XMLSchema";
    private static final String SQL = "urn:schemas-microsoft-com:xml-sql";
    private static final String ROWSET = "urn:schemas-microsoft-com:xml-analysis:rowset";

    /** A rowset as Analysis Services lists it in DISCOVER_SCHEMA_ROWSETS. */
    record Listed(String name, String guid, List<String[]> restrictions, String mask) {

        @Override
        public String toString() {
            return name;
        }
    }

    /** The answer in the file of that name, or {@code null} when there is no such file. */
    private static Document parsed(String name) throws Exception {
        Path file = RowsetAnswers.file(name);
        if (!Files.isRegularFile(file)) {
            return null;
        }
        try (InputStream in = Files.newInputStream(file)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            return factory.newDocumentBuilder().parse(in);
        }
    }

    private static List<Element> children(Element parent, String namespace, String name) {
        List<Element> found = new ArrayList<>();
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element element && namespace.equals(element.getNamespaceURI())
                    && name.equals(element.getLocalName())) {
                found.add(element);
            }
        }
        return found;
    }

    private static String text(Element parent, String name) {
        List<Element> found = children(parent, ROWSET, name);
        return found.isEmpty() ? null : found.get(0).getTextContent();
    }

    static Stream<Listed> listed() throws Exception {
        RowsetAnswers.assumePresent("DISCOVER_SCHEMA_ROWSETS.rows.xml");
        Element root = parsed("DISCOVER_SCHEMA_ROWSETS.rows.xml").getDocumentElement();
        List<Listed> rowsets = new ArrayList<>();
        for (Element row : children(root, ROWSET, "row")) {
            List<String[]> restrictions = new ArrayList<>();
            for (Element restriction : children(row, ROWSET, "Restrictions")) {
                restrictions.add(new String[] { text(restriction, "Name"), text(restriction, "Type") });
            }
            rowsets.add(new Listed(text(row, "SchemaName"), text(row, "SchemaGuid"), restrictions,
                    text(row, "RestrictionsMask")));
        }
        return rowsets.stream();
    }

    @Test
    void analysisServicesListsSeventySevenRowsets() throws Exception {
        assertThat(listed()).hasSize(77);
    }

    @ParameterizedTest
    @MethodSource("listed")
    void aRowsetAnalysisServicesListsIsModelledWithItsGuidAndRestrictions(Listed rowset) {
        Optional<EClass> row = RowsetCatalog.forRequestType(rowset.name());
        assertThat(row).as(rowset.name() + " has a row class").isPresent();
        assertThat(RowsetCatalog.guidOf(row.get()).map(guid -> guid.toUpperCase(Locale.ROOT)))
                .as(rowset.name() + " GUID").contains(rowset.guid().toUpperCase(Locale.ROOT));

        List<RowsetCatalog.Restriction> modelled = RowsetCatalog.restrictionsOf(row.get());
        List<String> names = modelled.stream().map(RowsetCatalog.Restriction::name).toList();
        List<String> listedNames = rowset.restrictions().stream().map(r -> r[0]).toList();
        assertThat(names).as(rowset.name() + " restrictions").containsSubsequence(listedNames);
        for (String[] restriction : rowset.restrictions()) {
            RowsetCatalog.Restriction match = modelled.get(names.indexOf(restriction[0]));
            assertThat(match.type()).as(rowset.name() + " restriction " + restriction[0] + " type")
                    .isEqualTo(restriction[1]);
        }
        if (names.equals(listedNames)) {
            assertThat(RowsetCatalog.restrictionsMaskOf(row.get()).toString()).as(rowset.name() + " mask")
                    .isEqualTo(rowset.mask());
        }
    }

    static Stream<String> answered() throws Exception {
        List<String> withSchema = new ArrayList<>();
        for (Listed rowset : listed().toList()) {
            if (parsed(rowset.name() + ".xsd") != null) {
                withSchema.add(rowset.name());
            }
        }
        return withSchema.stream();
    }

    @Test
    void seventyOneRowsetsAnsweredWithASchema() throws Exception {
        assertThat(answered()).hasSize(71);
    }

    @ParameterizedTest
    @MethodSource("answered")
    void everyColumnOfTheAnswerIsAColumnOfTheModelInItsOrderAndType(String requestType) throws Exception {
        Element schema = parsed(requestType + ".xsd").getDocumentElement();
        Element row = children(schema, XSD, "complexType").stream().filter(t -> "row".equals(t.getAttribute("name")))
                .findFirst().orElseThrow();
        EClass rowClass = RowsetCatalog.forRequestType(requestType).orElseThrow();

        List<String> problems = new ArrayList<>();
        compare(requestType, children(row, XSD, "sequence").get(0), rowClass, problems);

        assertThat(problems).as(requestType).isEmpty();
    }

    /** The columns of one sequence against one class, nested sequences against the class their feature holds. */
    private static void compare(String path, Element sequence, EClass modelled, List<String> problems) {
        List<EStructuralFeature> features = modelled.getEAllStructuralFeatures();
        List<String> names = features.stream().map(f -> ExtendedMetaData.INSTANCE.getName(f)).toList();
        int last = -1;
        for (Element column : children(sequence, XSD, "element")) {
            String name = column.hasAttributeNS(SQL, "field") ? column.getAttributeNS(SQL, "field")
                    : column.getAttribute("name");
            int at = names.indexOf(name);
            if (at < 0) {
                problems.add(path + "/" + name + " is not in the model");
                continue;
            }
            if (at < last) {
                problems.add(path + "/" + name + " stands before " + names.get(last) + " in the model");
            }
            last = Math.max(last, at);
            EStructuralFeature feature = features.get(at);
            List<Element> nested = children(column, XSD, "complexType");
            if (!nested.isEmpty()) {
                if (feature instanceof EReference reference) {
                    compare(path + "/" + name, children(nested.get(0), XSD, "sequence").get(0),
                            reference.getEReferenceType(), problems);
                } else {
                    problems.add(path + "/" + name + " is nested in the answer, a value in the model");
                }
                continue;
            }
            String answered = column.getAttribute("type");
            String written = RowsetSchemaWriter.xsdTypeOf(feature);
            if (!answered.isEmpty() && !answered.equals(written)) {
                problems.add(path + "/" + name + " is " + answered + " in the answer, " + written + " in the model");
            }
        }
    }
}
