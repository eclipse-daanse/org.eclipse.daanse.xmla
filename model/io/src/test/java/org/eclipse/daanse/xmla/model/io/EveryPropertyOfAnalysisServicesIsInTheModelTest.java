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
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.model.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.model.xmla.XmlaPackage;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The request properties can carry every property Analysis Services 2017 lists in {@code DISCOVER_PROPERTIES}, with
 * its type and, where the model states one, its access. The rows of that answer (name, type, access and whether a
 * property is required, not the values) lie outside this repository, see {@link RowsetAnswers}; without them the tests
 * are skipped.
 */
class EveryPropertyOfAnalysisServicesIsInTheModelTest {

    private static final String ROWSET = "urn:schemas-microsoft-com:xml-analysis:rowset";
    private static final String PROPERTY = "https://www.daanse.org/spec/xmla/property/1.0";

    private static final String ANSWER = "DISCOVER_PROPERTIES.rows.xml";

    /** The Ecore type each type name of DISCOVER_PROPERTIES is read into. */
    private static final Map<String, String> TYPES = Map.of("string", "EString", "int", "EIntegerObject", "boolean",
            "EBooleanObject", "double", "EDoubleObject", "long", "ELongObject");

    record Listed(String name, String type, String access) {

        @Override
        public String toString() {
            return name;
        }
    }

    private static String text(Element row, String name) {
        for (Node child = row.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element element && ROWSET.equals(element.getNamespaceURI())
                    && name.equals(element.getLocalName())) {
                return element.getTextContent();
            }
        }
        return null;
    }

    static Stream<Listed> listed() throws Exception {
        RowsetAnswers.assumePresent(ANSWER);
        try (InputStream in = Files.newInputStream(RowsetAnswers.file(ANSWER))) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Element root = factory.newDocumentBuilder().parse(in).getDocumentElement();
            List<Listed> properties = new ArrayList<>();
            for (Node child = root.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child instanceof Element row) {
                    properties.add(new Listed(text(row, "PropertyName"), text(row, "PropertyType"),
                            text(row, "PropertyAccessType")));
                }
            }
            return properties.stream();
        }
    }

    @Test
    void analysisServicesLists143Properties() throws Exception {
        assertThat(listed()).hasSize(143);
    }

    @ParameterizedTest
    @MethodSource("listed")
    void aPropertyAnalysisServicesListsIsInTheModelWithItsTypeAndAccess(Listed property) {
        EStructuralFeature feature = XmlaPackage.eINSTANCE.getPropertyList().getEAllStructuralFeatures().stream()
                .filter(f -> property.name().equals(ExtendedMetaData.INSTANCE.getName(f))).findFirst().orElse(null);
        assertThat(feature).as(property.name() + " is a property of the model").isNotNull();
        assertThat(feature.getEType().getName()).as(property.name() + " type").isEqualTo(TYPES.get(property.type()));
        EAnnotation daanse = feature.getEAnnotation(PROPERTY);
        String access = daanse == null ? null : daanse.getDetails().get("access");
        if (access != null) {
            assertThat(access).as(property.name() + " access").isEqualTo(property.access());
        }
    }
}
