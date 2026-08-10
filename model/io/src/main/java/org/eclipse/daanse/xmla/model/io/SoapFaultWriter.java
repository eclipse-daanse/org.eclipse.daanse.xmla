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

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Writes a SOAP 1.1 fault in the shape Analysis Services uses.
 * <p>
 * Two details that a generic SOAP writer gets wrong here:
 * <ul>
 * <li>the fault's children are <strong>unqualified</strong>. SOAP 1.1 puts
 * {@code faultcode}, {@code faultstring}, {@code faultactor} and {@code detail}
 * in no namespace, unlike every other element of the envelope;</li>
 * <li>the detail carries {@code <Error>} with its data in
 * <strong>attributes</strong>, not child elements — {@code ErrorCode},
 * {@code Description}, {@code Source}, {@code HelpFile}, which is the shape a
 * Microsoft client parses.</li>
 * </ul>
 */
public final class SoapFaultWriter {

    /** Whether the sender or the receiver is at fault, per SOAP 1.1. */
    public enum Kind {
        /** The message was wrong. The sender should not retry it unchanged. */
        CLIENT("Client"),
        /** The message was fine; this end failed. */
        SERVER("Server"),
        /** A mustUnderstand header block was not understood. */
        MUST_UNDERSTAND("MustUnderstand");

        private final String code;

        Kind(String code) {
            this.code = code;
        }
    }

    private static final XMLOutputFactory OUTPUT = HardenedXml.output();

    private SoapFaultWriter() {
        // static access only
    }

    /**
     * @param errorCode the XMLA error code, e.g. {@code 3238789153} for an
     *                  unrecognised request type; may be {@code null}
     * @param source    which component produced the error, as it will appear to the
     *                  user
     */
    public static void write(OutputStream target, Kind kind, String faultString, Long errorCode, String description,
            String source) throws XMLStreamException {
        XMLStreamWriter out = OUTPUT.createXMLStreamWriter(target, StandardCharsets.UTF_8.name());
        SoapEnvelopeWriter.write(out, List.of(), body -> {
            body.writeStartElement(XmlaNamespaces.SOAP_ENV, "Fault");

            // Unqualified on purpose - see the class comment.
            writeUnqualified(body, "faultcode", XmlaNamespaces.SOAP_ENV_PREFIX + ":" + kind.code);
            writeUnqualified(body, "faultstring", faultString);

            body.writeStartElement("detail");
            body.setPrefix(XmlaNamespaces.EXCEPTION_PREFIX, XmlaNamespaces.EXCEPTION);
            body.writeStartElement(XmlaNamespaces.EXCEPTION, "Error");
            body.writeNamespace(XmlaNamespaces.EXCEPTION_PREFIX, XmlaNamespaces.EXCEPTION);
            if (errorCode != null) {
                body.writeAttribute("ErrorCode", Long.toUnsignedString(errorCode));
            }
            body.writeAttribute("Description", description == null ? faultString : description);
            body.writeAttribute("Source", source == null ? "" : source);
            body.writeAttribute("HelpFile", "");
            body.writeEndElement();
            body.writeEndElement();

            body.writeEndElement();
        });
    }

    /**
     * The one-argument form writes the element in no namespace without touching the
     * namespace context. The three-argument form with empty strings looks
     * equivalent but rebinds the default namespace, which then leaves later
     * prefixed elements unresolvable.
     */
    private static void writeUnqualified(XMLStreamWriter out, String name, String text) throws XMLStreamException {
        out.writeStartElement(name);
        if (text != null) {
            out.writeCharacters(text);
        }
        out.writeEndElement();
    }
}
