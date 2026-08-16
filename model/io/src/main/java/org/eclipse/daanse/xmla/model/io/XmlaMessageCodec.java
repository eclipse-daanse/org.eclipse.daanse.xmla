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

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.daanse.xmla.model.exception.XmlaExceptionPackage;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Reads and writes whole XMLA messages. The façade both the server and a client
 * use.
 * <p>
 * Rows are handed in as an {@link Iterator} and read back as one, so neither
 * side has to hold a whole result set in memory.
 */
public final class XmlaMessageCodec {

    private static final XMLInputFactory INPUT = HardenedXml.input();
    private static final XMLOutputFactory OUTPUT = HardenedXml.output();

    private XmlaMessageCodec() {
        // static access only
    }

    /**
     * Writes a Discover request: the envelope, then the request read off the model.
     */
    public static void writeDiscoverRequest(OutputStream target, List<EObject> headers, Discover discover)
            throws XMLStreamException {
        XMLStreamWriter out = OUTPUT.createXMLStreamWriter(target, StandardCharsets.UTF_8.name());
        SoapEnvelopeWriter.write(out, headers, new SoapEnvelopeWriter.BodyWriter() {
            @Override
            public void write(XMLStreamWriter body) throws XMLStreamException {
                DiscoverRequestWriter.write(body, discover);
            }
        });
    }

    /**
     * Writes an Execute request: the envelope, then the command and its properties.
     */
    public static void writeExecuteRequest(OutputStream target, List<EObject> headers,
            org.eclipse.daanse.xmla.model.xmla.Command command,
            org.eclipse.daanse.xmla.model.xmla.PropertyList properties) throws XMLStreamException {
        XMLStreamWriter out = OUTPUT.createXMLStreamWriter(target, StandardCharsets.UTF_8.name());
        SoapEnvelopeWriter.write(out, headers, new SoapEnvelopeWriter.BodyWriter() {
            @Override
            public void write(XMLStreamWriter body) throws XMLStreamException {
                ExecuteRequestWriter.write(body, command, properties);
            }
        });
    }

    /**
     * Reads an Execute response: the MDDataset of a statement or the tabular RowSet
     * of a DMV query, told apart by the namespace of {@code <root>}.
     *
     * @return the payload, or {@code null} for the empty result every other command
     *         answers
     */
    public static EObject readExecuteResponse(InputStream source) throws XMLStreamException {
        return readExecuteResult(source).payload();
    }

    /**
     * An Execute response: the payload and whatever the server reported alongside
     * it.
     *
     * @param payload  an MdDataset or a RowSet, or {@code null} for the empty
     *                 result
     * @param messages the in-band {@code <Messages>}, or {@code null}
     */
    public record ExecuteResult(EObject payload, EObject messages) {

        /**
         * Whether the server reported something inside this otherwise successful
         * response.
         */
        public boolean hasMessages() {
            return messages != null;
        }
    }

    /**
     * As {@link #readExecuteResponse}, keeping what the server reported in band.
     * <p>
     * A command answers the empty result whether it worked or not; the
     * {@code <Messages>} is the only difference.
     */
    public static ExecuteResult readExecuteResult(InputStream source) throws XMLStreamException {
        SoapEnvelopeReader.Envelope envelope = SoapEnvelopeReader.read(source);
        XMLStreamReader in = envelope.cursor();
        EcoreXmlReader reader = new EcoreXmlReader(EcoreXmlReader.Unknown.SKIP);
        EObject payload = null;
        EObject messages = null;

        while (in.hasNext()) {
            if (in.next() != XMLStreamConstants.START_ELEMENT) {
                continue;
            }
            String name = in.getLocalName();
            String namespace = in.getNamespaceURI();
            if ("Messages".equals(name) && XmlaNamespaces.EXCEPTION.equals(namespace)) {
                messages = reader.read(in, XmlaExceptionPackage.eINSTANCE.getMessages());
            } else if ("root".equals(name) && payload == null) {
                if (XmlaNamespaces.MDDATASET.equals(namespace)) {
                    payload = reader.read(in,
                            org.eclipse.daanse.xmla.model.mddataset.MdDatasetPackage.eINSTANCE.getMdDataset());
                } else if (XmlaNamespaces.ROWSET.equals(namespace)) {
                    payload = reader.read(in,
                            org.eclipse.daanse.xmla.model.mddataset.MdDatasetPackage.eINSTANCE.getRowSet());
                }
                // The empty namespace: nothing to read, and <Messages> may still follow inside.
            }
        }
        return new ExecuteResult(payload, messages);
    }

    /**
     * Writes a Discover response: the envelope, the inline schema derived from
     * {@code rowEClass}, then the rows.
     */
    public static void writeDiscoverResponse(OutputStream target, List<EObject> headers, EClass rowEClass,
            Iterator<EObject> rows) throws XMLStreamException {
        writeDiscoverResponse(target, headers, rowEClass, rows, null);
    }

    /**
     * As above, and reporting an error inside a successful response: HTTP 200, a
     * well-formed {@code <root>} with its inline schema, then an empty
     * {@code <Exception/>} and {@code <Messages>}. Unlike a fault this is not an
     * either/or - rows and a message travel together.
     *
     * @param messages a {@code Messages} EObject, or {@code null} when nothing went
     *                 wrong
     */
    public static void writeDiscoverResponse(OutputStream target, List<EObject> headers, EClass rowEClass,
            Iterator<EObject> rows, EObject messages) throws XMLStreamException {
        XMLStreamWriter out = OUTPUT.createXMLStreamWriter(target, StandardCharsets.UTF_8.name());
        SoapEnvelopeWriter.write(out, headers, body -> {
            // setDefaultNamespace must precede writeStartElement: the writer resolves the
            // element's prefix at the moment it is written, not when the declaration
            // follows.
            body.setDefaultNamespace(XmlaNamespaces.XMLA);
            body.writeStartElement(XmlaNamespaces.XMLA, "DiscoverResponse");
            body.writeDefaultNamespace(XmlaNamespaces.XMLA);
            body.writeStartElement(XmlaNamespaces.XMLA, "return");

            body.setDefaultNamespace(XmlaNamespaces.ROWSET);
            body.writeStartElement(XmlaNamespaces.ROWSET, "root");
            body.writeDefaultNamespace(XmlaNamespaces.ROWSET);
            body.writeNamespace(XmlaNamespaces.XSI_PREFIX, XmlaNamespaces.XSI);
            // DISCOVER_SCHEMA_ROWSETS writes type names as element content -
            // <Type>xsd:string</Type> - and it writes them after the inline schema, whose
            // own xsd binding has gone out of scope by then. A client that resolves the
            // prefix for real finds nothing and stops. Binding it here costs one
            // attribute; SSAS binds it on this element too.
            body.writeNamespace(XmlaNamespaces.XSD_PREFIX, XmlaNamespaces.XSD);
            body.writeNamespace(XmlaNamespaces.EXCEPTION_PREFIX, XmlaNamespaces.EXCEPTION);

            RowsetSchemaWriter.write(body, rowEClass);

            EcoreXmlWriter rowWriter = new EcoreXmlWriter(XmlaNamespaces.ROWSET);
            while (rows.hasNext()) {
                rowWriter.write(body, rows.next(), "row");
            }

            writeMessages(body, messages);

            body.writeEndElement(); // root
            body.writeEndElement(); // return
            body.writeEndElement(); // DiscoverResponse
        });
    }

    /**
     * The in-band {@code <Messages>}, preceded by {@code <Exception/>} only where
     * something failed: an {@code <Error>} gets the marker, a lone
     * {@code <Warning>} does not.
     */
    private static void writeMessages(XMLStreamWriter out, EObject messages) throws XMLStreamException {
        if (messages == null) {
            return;
        }
        EStructuralFeature error = messages.eClass().getEStructuralFeature("error");
        boolean failed = error != null && !((List<?>) messages.eGet(error)).isEmpty();
        if (failed) {
            out.writeEmptyElement(XmlaNamespaces.EXCEPTION, "Exception");
        }
        new EcoreXmlWriter(XmlaNamespaces.EXCEPTION).write(out, messages, "Messages");
    }

    /**
     * Writes an Execute response.
     * <p>
     * There is no inline schema: the shape of an MDDataset is fixed by the
     * specification. Which of the two forms comes back - an {@code MDDataset} or a
     * {@code RowSet} - follows from the object handed in, not from a flag.
     *
     * @param result an MdDataset or a RowSet; {@code null} for a command that
     *               returns nothing, which is answered with the empty result the
     *               specification defines
     */
    public static void writeExecuteResponse(OutputStream target, List<EObject> headers, EObject result)
            throws XMLStreamException {
        writeExecuteResponse(target, headers, result, null);
    }

    /**
     * As above, and reporting in band - the same mechanism a Discover response
     * uses.
     *
     * @param messages a {@code Messages} EObject, or {@code null} when nothing was
     *                 reported
     */
    public static void writeExecuteResponse(OutputStream target, List<EObject> headers, EObject result,
            EObject messages) throws XMLStreamException {
        XMLStreamWriter out = OUTPUT.createXMLStreamWriter(target, StandardCharsets.UTF_8.name());
        SoapEnvelopeWriter.write(out, headers, body -> {
            body.setDefaultNamespace(XmlaNamespaces.XMLA);
            body.writeStartElement(XmlaNamespaces.XMLA, "ExecuteResponse");
            body.writeDefaultNamespace(XmlaNamespaces.XMLA);
            body.writeStartElement(XmlaNamespaces.XMLA, "return");

            // The return element is the specification's four-way choice (3.1.4.3.2.2.1):
            // xmla-ds:root, xmla-rs:root, xmla-e:root or xmla-m:results — decided by what
            // the command produced, not by which command it was, because the same statement
            // answers a dataset or a rowset depending on the Format property.
            if (result == null) {
                writeEmptyResult(body, messages);
            } else if (result instanceof org.eclipse.daanse.xmla.model.xmla.RowsetResult rowset) {
                writeRowsetResult(body, rowset, messages);
            } else if (result instanceof org.eclipse.daanse.xmla.model.multipleresults.Results results) {
                writeBatchResults(body, results);
            } else {
                body.setDefaultNamespace(XmlaNamespaces.MDDATASET);
                body.writeStartElement(XmlaNamespaces.MDDATASET, "root");
                body.writeDefaultNamespace(XmlaNamespaces.MDDATASET);
                body.writeNamespace(XmlaNamespaces.XSI_PREFIX, XmlaNamespaces.XSI);
                // OlapInfo names each column's type as xsd:string, xsd:int and so on, so the
                // prefix has to be bound here. Without it the attribute values point at
                // nothing and a client cannot read the axis - MSOLAP says so and stops.
                body.writeNamespace(XmlaNamespaces.XSD_PREFIX, XmlaNamespaces.XSD);
                new EcoreXmlWriter(XmlaNamespaces.MDDATASET).writeContent(body, result);
                writeMessages(body, messages);
                body.writeEndElement();
            }

            body.writeEndElement(); // return
            body.writeEndElement(); // ExecuteResponse
        });
    }

    /**
     * What a command that produces no data answers with: an empty {@code <root>} in
     * the empty namespace, not an absent one.
     */
    private static void writeEmptyResult(XMLStreamWriter out, EObject messages) throws XMLStreamException {
        out.setDefaultNamespace(XmlaNamespaces.EMPTY);
        out.writeStartElement(XmlaNamespaces.EMPTY, "root");
        out.writeDefaultNamespace(XmlaNamespaces.EMPTY);
        writeMessages(out, messages);
        out.writeEndElement();
    }

    /**
     * A tabular Execute result: {@code xmla-rs:root} with the optional inline
     * schema and one element per cell — an absent cell is how the rowset says NULL,
     * and a nested rowset's cells are written directly inside the column element,
     * Restrictions-style ([MS-SSAS] 2.2.4.1.3.1.1).
     */
    private static void writeRowsetResult(XMLStreamWriter out, org.eclipse.daanse.xmla.model.xmla.RowsetResult rowset,
            EObject messages) throws XMLStreamException {
        out.setDefaultNamespace(XmlaNamespaces.ROWSET);
        out.writeStartElement(XmlaNamespaces.ROWSET, "root");
        out.writeDefaultNamespace(XmlaNamespaces.ROWSET);
        out.writeNamespace(XmlaNamespaces.XSI_PREFIX, XmlaNamespaces.XSI);
        // Bound for the same reason as on the Discover root, and because a variant cell
        // names its own type as xsi:type="xsd:int" - a QName whose prefix must resolve.
        out.writeNamespace(XmlaNamespaces.XSD_PREFIX, XmlaNamespaces.XSD);
        // writeMessages below may emit EX:Exception and EX:Messages into this root.
        out.writeNamespace(XmlaNamespaces.EXCEPTION_PREFIX, XmlaNamespaces.EXCEPTION);

        if (rowset.isSchemaIncluded()) {
            List<RowsetSchemaWriter.Column> columns = new java.util.ArrayList<>();
            for (org.eclipse.daanse.xmla.model.xmla.RowsetColumn column : rowset.getColumns()) {
                columns.add(new RowsetSchemaWriter.Column(column.getField(), column.getName(), column.getXsdType()));
            }
            RowsetSchemaWriter.write(out, columns);
        }
        for (org.eclipse.daanse.xmla.model.xmla.RowsetRow row : rowset.getRows()) {
            out.writeStartElement(XmlaNamespaces.ROWSET, "row");
            for (org.eclipse.daanse.xmla.model.xmla.RowsetCell cell : row.getCells()) {
                writeRowsetCell(out, cell);
            }
            out.writeEndElement();
        }
        writeMessages(out, messages);
        out.writeEndElement();
    }

    private static void writeRowsetCell(XMLStreamWriter out, org.eclipse.daanse.xmla.model.xmla.RowsetCell cell)
            throws XMLStreamException {
        out.writeStartElement(XmlaNamespaces.ROWSET, cell.getName());
        if (!cell.getCells().isEmpty()) {
            for (org.eclipse.daanse.xmla.model.xmla.RowsetCell nested : cell.getCells()) {
                writeRowsetCell(out, nested);
            }
        } else if (cell.getValue() != null) {
            out.writeCharacters(cell.getValue());
        }
        out.writeEndElement();
    }

    /**
     * A Batch answer: {@code xmla-m:results} with one root per command, each root
     * in whichever of the three single-result forms its command produced ([MS-SSAS]
     * 3.1.4.3.2.2.1). An {@code Emptyresult} entry marks a command with nothing to
     * say — a list cannot carry null.
     */
    private static void writeBatchResults(XMLStreamWriter out,
            org.eclipse.daanse.xmla.model.multipleresults.Results results) throws XMLStreamException {
        out.setDefaultNamespace(XmlaNamespaces.MULTIPLE_RESULTS);
        out.writeStartElement(XmlaNamespaces.MULTIPLE_RESULTS, "results");
        out.writeDefaultNamespace(XmlaNamespaces.MULTIPLE_RESULTS);
        for (EObject entry : results.getResults()) {
            if (entry instanceof org.eclipse.daanse.xmla.model.empty.Emptyresult) {
                writeEmptyResult(out, null);
            } else if (entry instanceof org.eclipse.daanse.xmla.model.xmla.RowsetResult rowset) {
                writeRowsetResult(out, rowset, null);
            } else {
                out.setDefaultNamespace(XmlaNamespaces.MDDATASET);
                out.writeStartElement(XmlaNamespaces.MDDATASET, "root");
                out.writeDefaultNamespace(XmlaNamespaces.MDDATASET);
                out.writeNamespace(XmlaNamespaces.XSI_PREFIX, XmlaNamespaces.XSI);
                // As above: the axis types are written as xsd:-qualified names.
                out.writeNamespace(XmlaNamespaces.XSD_PREFIX, XmlaNamespaces.XSD);
                new EcoreXmlWriter(XmlaNamespaces.MDDATASET).writeContent(out, entry);
                out.writeEndElement();
            }
        }
        out.writeEndElement();
    }

    /**
     * A Discover response: the header blocks that were recognised, and the rows.
     *
     * @param headers the response headers - a {@code <Session>} here is how the
     *                server tells a client the id to carry forward
     */
    public record Response(List<EObject> headers, List<EObject> rows, EObject messages) {

        /**
         * Whether the server reported an error inside this otherwise successful
         * response. {@link #rows()} alone cannot say: a refusal and nothing to report
         * are both an empty list.
         */
        public boolean hasMessages() {
            return messages != null;
        }
    }

    /**
     * Reads the rows of a Discover response.
     * <p>
     * The inline schema is skipped rather than parsed: it describes the rows that
     * follow, and the caller already knows their EClass.
     */
    public static List<EObject> readDiscoverResponse(InputStream source, EClass rowEClass) throws XMLStreamException {
        return readResponse(source, rowEClass).rows();
    }

    /** As {@link #readDiscoverResponse}, but keeping the header blocks as well. */
    public static Response readResponse(InputStream source, EClass rowEClass) throws XMLStreamException {
        SoapEnvelopeReader.Envelope envelope = SoapEnvelopeReader.read(source);
        XMLStreamReader in = envelope.cursor();
        EcoreXmlReader reader = new EcoreXmlReader(EcoreXmlReader.Unknown.SKIP);
        List<EObject> rows = new ArrayList<>();
        EObject messages = null;
        while (in.hasNext()) {
            if (in.next() != XMLStreamConstants.START_ELEMENT) {
                continue;
            }
            if ("row".equals(in.getLocalName()) && XmlaNamespaces.ROWSET.equals(in.getNamespaceURI())) {
                rows.add(reader.read(in, rowEClass));
            } else if ("Messages".equals(in.getLocalName()) && XmlaNamespaces.EXCEPTION.equals(in.getNamespaceURI())) {
                // The <Exception/> that precedes this is empty; the Messages carries the
                // reason.
                messages = reader.read(in, XmlaExceptionPackage.eINSTANCE.getMessages());
            }
        }
        return new Response(envelope.headers(), rows, messages);
    }

}
