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
package org.eclipse.daanse.xmla.server.adapter.emf;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.daanse.xmla.model.xmla.RowsetCell;
import org.eclipse.daanse.xmla.model.xmla.RowsetColumn;
import org.eclipse.daanse.xmla.model.xmla.RowsetResult;
import org.eclipse.daanse.xmla.model.xmla.RowsetRow;
import org.eclipse.daanse.xmla.model.xmla.XmlaFactory;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;

/**
 * What each {@code <root>} must declare.
 * <p>
 * A response names types as QNames in two places a namespace-aware client
 * resolves for real: {@code DISCOVER_SCHEMA_ROWSETS} writes
 * {@code <Type>xsd:string</Type>} as element content, and a variant cell carries
 * {@code xsi:type="xsd:int"}. Both are written after the inline schema has
 * closed, so the schema's own {@code xsd} binding is out of scope by then. The
 * prefix has to be bound on the root or it resolves to nothing.
 */
class RootNamespaceTest {

    private static final String XSD = "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"";
    private static final String XSI = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
    private static final String EX = "urn:schemas-microsoft-com:xml-analysis:exception";

    private static final String ENVELOPE = """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap:Body>%s</soap:Body></soap:Envelope>""";

    private static final String DISCOVER = """
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <RequestType>DISCOVER_SCHEMA_ROWSETS</RequestType>\
            <Restrictions><RestrictionList/></Restrictions>\
            <Properties><PropertyList/></Properties></Discover>""";

    /** A rowset the stub connector has no rows for, and no fallback fills. */
    private static final String DISCOVER_NOBODY_ANSWERS = """
            <Discover xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <RequestType>DBSCHEMA_TRUSTEE</RequestType>\
            <Restrictions><RestrictionList/></Restrictions>\
            <Properties><PropertyList/></Properties></Discover>""";

    private static final String EXECUTE = """
            <Execute xmlns="urn:schemas-microsoft-com:xml-analysis">\
            <Command><Statement>SELECT FROM [Sales]</Statement></Command>\
            <Properties><PropertyList><Format>Tabular</Format></PropertyList></Properties></Execute>""";

    /** A one-column, one-row tabular result — enough to reach the rowset root. */
    private static RowsetResult tabularResult() {
        XmlaFactory factory = XmlaFactory.eINSTANCE;
        RowsetResult result = factory.createRowsetResult();
        result.setSchemaIncluded(true);

        RowsetColumn column = factory.createRowsetColumn();
        column.setField("MEASURE");
        column.setName("MEASURE");
        column.setXsdType("xsd:int");
        result.getColumns().add(column);

        RowsetCell cell = factory.createRowsetCell();
        cell.setName("MEASURE");
        cell.setValue("42");
        RowsetRow row = factory.createRowsetRow();
        row.getCells().add(cell);
        result.getRows().add(row);
        return result;
    }

    private static String send(String body, EObject executeResult) {
        XmlaConnector connector = new XmlaConnector() {

            @Override
            public List<EObject> discover(Discover request, XmlaRequest context) {
                return List.of();
            }

            @Override
            public EObject execute(Execute request, XmlaRequest context) {
                return executeResult;
            }
        };
        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        new EmfXmlaAdapter(connector, null, null).handle(
                new ByteArrayInputStream(ENVELOPE.formatted(body).getBytes(StandardCharsets.UTF_8)), answer,
                XmlaRequest.anonymous());
        return answer.toString(StandardCharsets.UTF_8);
    }

    /** The element text between {@code <root} and the first {@code >}. */
    private static String rootTag(String message) {
        int start = message.indexOf("<root");
        assertThat(start).as("the answer carries a root element:%n%s", message).isNotNegative();
        return message.substring(start, message.indexOf('>', start));
    }

    @Test
    void theDiscoverRootBindsXsd() {
        String root = rootTag(send(DISCOVER, null));

        assertThat(root).as("DISCOVER_SCHEMA_ROWSETS writes <Type>xsd:string</Type> as content").contains(XSD);
        assertThat(root).contains(XSI);
        assertThat(root).as("in-band messages are written into this root").contains(EX);
    }

    @Test
    void theTabularExecuteRootBindsXsd() {
        String root = rootTag(send(EXECUTE, tabularResult()));

        assertThat(root).as("a variant cell names its own type as xsi:type=\"xsd:int\"").contains(XSD);
        assertThat(root).contains(XSI);
        assertThat(root).as("in-band messages are written into this root too").contains(EX);
    }

    @Test
    void anEmptyExecuteRootStaysEmpty() {
        String answer = send(EXECUTE, null);

        // A client checks this root is genuinely empty and refuses anything else, so
        // nothing beyond the namespace declarations belongs here.
        assertThat(rootTag(answer)).contains("urn:schemas-microsoft-com:xml-analysis:empty");
        assertThat(answer).doesNotContain("<row");
    }

    /**
     * The other half of the rule above: {@code empty} belongs to Execute alone.
     * <p>
     * A Discover with nothing to say still answers with a rowset root and its inline
     * schema. The difference is not cosmetic. AMO reads a batch of Discovers and
     * names each answer's table from the {@code name} attribute of its root; an
     * {@code empty} root carries no name, the count of names then disagrees with the
     * count of tables, and it abandons naming for the whole batch rather than for
     * the one answer. Zero rows and nothing to report are different things, and only
     * one of them is true here.
     */
    @Test
    void aDiscoverWithNoRowsIsStillARowset() {
        String answer = send(DISCOVER_NOBODY_ANSWERS, null);

        assertThat(rootTag(answer)).as("a rowset with no rows, not an empty result")
                .contains("urn:schemas-microsoft-com:xml-analysis:rowset");
        assertThat(answer).as("the columns are what the client reads even when there are no values")
                .contains("complexType");
        assertThat(answer).doesNotContain("xml-analysis:empty");
    }

    /**
     * Announce what can be answered, not what can be modelled.
     * <p>
     * A connector that leaves {@code DISCOVER_SCHEMA_ROWSETS} to the transport can
     * still say what it reaches. Where it does, the transport must not offer the
     * rest: a client that asks for an announced rowset and gets a fault has been
     * misled, and it had no way to know in advance.
     */
    @Test
    void aConnectorThatSaysWhatItServesIsTakenAtItsWord() {
        XmlaConnector narrow = new XmlaConnector() {

            @Override
            public List<EObject> discover(Discover request, XmlaRequest context) {
                return List.of();
            }

            @Override
            public EObject execute(Execute request, XmlaRequest context) {
                return null;
            }

            @Override
            public java.util.Set<String> served() {
                return java.util.Set.of("DISCOVER_SCHEMA_ROWSETS", "MDSCHEMA_CUBES");
            }
        };

        ByteArrayOutputStream answer = new ByteArrayOutputStream();
        new EmfXmlaAdapter(narrow, null, null).handle(
                new ByteArrayInputStream(ENVELOPE.formatted(DISCOVER).getBytes(StandardCharsets.UTF_8)), answer,
                XmlaRequest.anonymous());
        String message = answer.toString(StandardCharsets.UTF_8);

        assertThat(message).contains("<SchemaName>DISCOVER_SCHEMA_ROWSETS</SchemaName>")
                .contains("<SchemaName>MDSCHEMA_CUBES</SchemaName>");
        assertThat(message).as("this one is in the model and not in the connector's reach")
                .doesNotContain("<SchemaName>DBSCHEMA_TRUSTEE</SchemaName>");
    }
}
