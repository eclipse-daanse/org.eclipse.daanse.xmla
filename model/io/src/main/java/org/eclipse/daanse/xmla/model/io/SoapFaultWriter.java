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
            writeUnqualified(body, "faultcode", faultCode(kind, errorCode));
            writeUnqualified(body, "faultstring", faultString);

            // Only with a code. A client reads the ErrorCode attribute unconditionally -
            // ADOMD runs XmlConvert.ToUInt32 straight on it and throws on null, so an
            // <Error> without one replaces our message with the client's own parse
            // failure. A fault needs either this element or a faultstring, and the
            // faultstring above is always written, so leaving it out is the honest
            // option: better no Error element than one carrying an invented code.
            if (errorCode != null) {
                body.writeStartElement("detail");
                body.setPrefix(XmlaNamespaces.EXCEPTION_PREFIX, XmlaNamespaces.EXCEPTION);
                body.writeStartElement(XmlaNamespaces.EXCEPTION, "Error");
                body.writeNamespace(XmlaNamespaces.EXCEPTION_PREFIX, XmlaNamespaces.EXCEPTION);
                body.writeAttribute("ErrorCode", Long.toUnsignedString(errorCode));
                body.writeAttribute("Description", description == null ? faultString : description);
                body.writeAttribute("Source", source == null ? "" : source);
                body.writeAttribute("HelpFile", "");
                body.writeEndElement();
                body.writeEndElement();
            }

            body.writeEndElement();
        });
    }

    /**
     * The fault code as Analysis Services writes it.
     * <p>
     * A fault that carries a number has {@code XMLAnalysisError.0x} and the number in lower-case hexadecimal as its
     * code, unprefixed: Analysis Services writes every such fault in this form and no other, e.g.
     * {@code XMLAnalysisError.0xc1140002} beside {@code ErrorCode="3239313410"}. A fault without a number keeps SOAP's
     * own {@code Client}/{@code Server}, since an invented number would be worse, and so does a header nobody
     * understood (SOAP's {@code MustUnderstand}). ADOMD.NET does not read the code (it parses the {@code Error}
     * element), so the form matters to clients that compare answers, not to ADOMD.
     */
    static String faultCode(Kind kind, Long errorCode) {
        if (errorCode == null || kind == Kind.MUST_UNDERSTAND) {
            return XmlaNamespaces.SOAP_ENV_PREFIX + ":" + kind.code;
        }
        return "XMLAnalysisError.0x" + Long.toHexString(errorCode);
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
