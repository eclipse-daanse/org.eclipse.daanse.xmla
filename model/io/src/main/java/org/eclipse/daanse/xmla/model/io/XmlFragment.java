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

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * Reads and writes the content of an {@code xmlDocument} column.
 * <p>
 * The spec types a handful of columns as {@code xmlDocument} — an
 * {@code xsd:any} — and real servers use it for exactly that:
 * {@code DISCOVER_XML_METADATA} answers with an entire {@code <Server>}
 * definition inside a single column. There is no useful Java type for
 * "arbitrary XML", so the model stores the fragment as its serialised form and
 * this class is the boundary that keeps that honest.
 * <p>
 * Both directions go through a parser rather than through string handling.
 * Writing the stored text with {@code writeCharacters} would escape it into
 * {@code &lt;Server&gt;} and turn a document into a paragraph; concatenating it
 * into the output unescaped would let a value decide the structure of the
 * message, which is an injection, not a serialisation.
 */
final class XmlFragment {

    private static final XMLInputFactory INPUT = hardened();
    private static final XMLOutputFactory OUTPUT = HardenedXml.output();

    private static final String WRAPPER = "xmlDocument";

    private XmlFragment() {
        // static access only
    }

    /**
     * Captures everything inside the element the reader is positioned on.
     * <p>
     * The captured text has to stand alone: a fragment is cut out of a larger
     * document, and a prefix it uses may be declared on an ancestor that is not
     * coming with it — SSAS writes {@code <xars:METADATA>} with {@code xars} and a
     * dozen {@code ddl*} prefixes declared on the response root. So a prefix that
     * is used but not declared within the fragment is declared where it is first
     * used, from the reader's own context. Without this the value reads fine and
     * round-trips fine as a string, but cannot be parsed the next time it is
     * written out.
     *
     * @param in positioned on the START_ELEMENT; left on the matching END_ELEMENT
     * @return the children as XML, or the element's text if it has none
     */
    static String read(XMLStreamReader in) throws XMLStreamException {
        StringWriter text = new StringWriter();
        XMLStreamWriter out = OUTPUT.createXMLStreamWriter(text);
        Bindings bindings = new Bindings();
        int depth = 0;

        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.END_ELEMENT) {
                if (depth == 0) {
                    break;
                }
                depth--;
                out.writeEndElement();
                bindings.pop();
            } else if (event == XMLStreamConstants.START_ELEMENT) {
                depth++;
                bindings.push();
                copyStart(in, out, bindings);
            } else if (event == XMLStreamConstants.CHARACTERS || event == XMLStreamConstants.CDATA) {
                out.writeCharacters(in.getText());
            }
        }
        out.flush();
        // A column that holds plain text comes back as that text; on the wire the two
        // are the same.
        return text.toString();
    }

    /**
     * Which prefixes the fragment itself has declared, scope by scope.
     * <p>
     * Kept by hand rather than asked of the writer: {@code writeNamespace} on the
     * JDK's plain writer records nothing, so its {@code NamespaceContext} answers
     * as if nothing had been declared and every check against it would re-declare
     * on every element.
     */
    private static final class Bindings {

        private final java.util.ArrayDeque<java.util.Map<String, String>> scopes = new java.util.ArrayDeque<>();

        void push() {
            scopes.push(new java.util.HashMap<>());
        }

        void pop() {
            scopes.pop();
        }

        boolean covers(String prefix, String namespace) {
            for (java.util.Map<String, String> scope : scopes) {
                String bound = scope.get(prefix);
                if (bound != null) {
                    return bound.equals(namespace);
                }
            }
            return false;
        }

        void note(String prefix, String namespace) {
            scopes.peek().put(prefix, namespace);
        }
    }

    /**
     * Writes a captured fragment back as element content.
     * <p>
     * Text that is not XML is written as text, because that is what it is: several
     * columns are typed {@code xmlDocument} by the spec and used for a plain string
     * by every server.
     */
    static void write(XMLStreamWriter out, String fragment) throws XMLStreamException {
        if (fragment == null || fragment.isEmpty()) {
            return;
        }
        if (fragment.indexOf('<') < 0) {
            out.writeCharacters(fragment);
            return;
        }
        XMLStreamReader in = INPUT
                .createXMLStreamReader(new StringReader('<' + WRAPPER + '>' + fragment + "</" + WRAPPER + '>'));
        int depth = 0;
        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                depth++;
                if (depth > 1) {
                    copyStart(in, out, null);
                }
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                if (depth > 1) {
                    out.writeEndElement();
                }
                depth--;
            } else if (event == XMLStreamConstants.CHARACTERS || event == XMLStreamConstants.CDATA) {
                out.writeCharacters(in.getText());
            }
        }
        in.close();
    }

    /**
     * @param bindings the fragment's own declarations so far, when capturing with
     *                 {@link #read} — {@code null} when writing into a live
     *                 document, whose writer knows its own context
     */
    private static void copyStart(XMLStreamReader in, XMLStreamWriter out, Bindings bindings)
            throws XMLStreamException {
        String namespace = in.getNamespaceURI();
        String prefix = in.getPrefix() == null ? "" : in.getPrefix();
        if (namespace == null || namespace.isEmpty()) {
            out.writeStartElement(in.getLocalName());
        } else {
            out.writeStartElement(prefix, in.getLocalName(), namespace);
        }
        for (int i = 0; i < in.getNamespaceCount(); i++) {
            String declared = in.getNamespacePrefix(i);
            out.writeNamespace(declared == null ? "" : declared, in.getNamespaceURI(i));
            if (bindings != null) {
                bindings.note(declared == null ? "" : declared, in.getNamespaceURI(i));
            }
        }
        // A prefix the fragment uses may be declared on an ancestor that did not come
        // with it - a DDL document declares its engine prefixes once, on the root.
        // Carrying only the declarations on the element itself produces text that
        // cannot be parsed again.
        declareIfUnbound(out, bindings, prefix, namespace);
        for (int i = 0; i < in.getAttributeCount(); i++) {
            String attributeNamespace = in.getAttributeNamespace(i);
            if (attributeNamespace == null || attributeNamespace.isEmpty()) {
                out.writeAttribute(in.getAttributeLocalName(i), in.getAttributeValue(i));
                continue;
            }
            declareIfUnbound(out, bindings, in.getAttributePrefix(i), attributeNamespace);
            out.writeAttribute(attributeNamespace, in.getAttributeLocalName(i), in.getAttributeValue(i));
        }
    }

    private static void declareIfUnbound(XMLStreamWriter out, Bindings bindings, String prefix, String namespace)
            throws XMLStreamException {
        if (namespace == null || namespace.isEmpty()) {
            return;
        }
        String safe = prefix == null ? "" : prefix;
        if (bindings != null) {
            // Capturing: the writer's own context records nothing, so the fragment's
            // declarations are tracked by hand.
            if (!bindings.covers(safe, namespace)) {
                out.writeNamespace(safe, namespace);
                bindings.note(safe, namespace);
            }
            return;
        }
        String bound = out.getNamespaceContext().getPrefix(namespace);
        if (bound == null) {
            out.writeNamespace(safe, namespace);
        }
    }

    private static XMLInputFactory hardened() {
        // The fragment came off the wire, so it gets the same treatment as the message.
        return HardenedXml.input();
    }
}
