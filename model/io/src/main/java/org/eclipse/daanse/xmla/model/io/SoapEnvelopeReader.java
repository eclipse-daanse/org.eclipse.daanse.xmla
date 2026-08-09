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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.eclipse.daanse.xmla.model.soap.SoapFactory;
import org.eclipse.daanse.xmla.model.soap.SoapPackage;
import org.eclipse.daanse.xmla.model.soap.UnknownHeader;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Reads a SOAP envelope up to the first child of the body, and no further.
 * <p>
 * Header blocks are small and are materialised. The body is not: the reader is
 * handed back still positioned on the body's first element, so the caller can
 * dispatch on its name and then keep streaming. A request carrying a large
 * {@code <Statement>}, or a response carrying a million rows, is never held in
 * memory as a document.
 */
public final class SoapEnvelopeReader {

    /**
     * What was read, plus the live cursor.
     *
     * @param headers     header blocks that were recognised, in document order
     * @param bodyElement the QName of the body's first child, or {@code null} for
     *                    an empty body
     * @param cursor      positioned on that element's START_ELEMENT
     */
    public record Envelope(List<EObject> headers, QName bodyElement, XMLStreamReader cursor) {
    }

    /**
     * The header blocks this implementation knows, by element name.
     * <p>
     * By local name rather than by qualified name: these seven sit in five
     * different namespaces and no two share a local name, so the namespace adds
     * nothing to the lookup while making the map fail on a client that binds a
     * different one for the same block.
     * <p>
     * Every block a real client sends belongs here. Nothing declares any of them
     * optional, so falling through to {@link UnknownHeader} is a failure mode
     * rather than a policy: an unknown block keeps its raw text and loses its
     * attributes.
     */
    private static final Map<String, EClass> HEADERS = Map.of("Session", SoapPackage.eINSTANCE.getSessionHeader(),
            "BeginSession", SoapPackage.eINSTANCE.getBeginSessionHeader(), "EndSession",
            SoapPackage.eINSTANCE.getEndSessionHeader(), "ProtocolCapabilities",
            SoapPackage.eINSTANCE.getProtocolCapabilitiesHeader(),
            // Sent by SQL Server Management Studio.
            "Version", SoapPackage.eINSTANCE.getVersionHeader(), "NamespaceCompatibility",
            SoapPackage.eINSTANCE.getNamespaceCompatibilityHeader(),
            // Sent by Excel's MSOLAP provider, and by no other known client.
            "BeginGetSessionToken", SoapPackage.eINSTANCE.getBeginGetSessionTokenHeader());

    private static final XMLInputFactory INPUT = hardened();

    private SoapEnvelopeReader() {
        // static access only
    }

    public static Envelope read(InputStream source) throws XMLStreamException {
        return read(INPUT.createXMLStreamReader(source));
    }

    public static Envelope read(XMLStreamReader in) throws XMLStreamException {
        List<EObject> headers = new ArrayList<>();
        // Header blocks may carry a namespace this implementation does not model, so an
        // unrecognised child is skipped rather than refused.
        EcoreXmlReader reader = new EcoreXmlReader(EcoreXmlReader.Unknown.SKIP);

        while (in.hasNext()) {
            if (in.next() != XMLStreamConstants.START_ELEMENT) {
                continue;
            }
            String name = in.getLocalName();
            String namespace = in.getNamespaceURI();

            if (XmlaNamespaces.SOAP_ENV.equals(namespace)) {
                if ("Envelope".equals(name) || "Header".equals(name)) {
                    continue; // descend
                }
                if ("Body".equals(name)) {
                    return new Envelope(headers, firstBodyElement(in), in);
                }
                continue;
            }
            EClass headerType = HEADERS.get(name);
            if (headerType != null) {
                headers.add(reader.read(in, headerType));
            } else {
                // A header this implementation does not model is still read, not skipped:
                // SOAP 1.1 requires a receiver that does not understand a block carrying
                // mustUnderstand="1" to answer a MustUnderstand fault, which it cannot do
                // about a block it threw away.
                headers.add(unknown(in, namespace, name));
            }
        }
        throw new XmlaCodecException("no <soap:Body> in the message");
    }

    /**
     * Captures a header block this implementation has no model for, keeping enough
     * to answer about it.
     */
    private static EObject unknown(XMLStreamReader in, String namespace, String name) throws XMLStreamException {
        UnknownHeader header = SoapFactory.eINSTANCE.createUnknownHeader();
        header.setNamespaceUri(namespace);
        header.setLocalName(name);
        header.setMustUnderstand(mustUnderstand(in));
        header.setRaw(XmlFragment.read(in));
        return header;
    }

    /** {@code soap:mustUnderstand}, in any of xsd:boolean's four lexical forms. */
    private static boolean mustUnderstand(XMLStreamReader in) {
        String value = in.getAttributeValue(XmlaNamespaces.SOAP_ENV, "mustUnderstand");
        if (value == null) {
            return false;
        }
        String trimmed = value.trim();
        return "1".equals(trimmed) || "true".equals(trimmed);
    }

    private static QName firstBodyElement(XMLStreamReader in) throws XMLStreamException {
        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                return in.getName();
            }
            if (event == XMLStreamConstants.END_ELEMENT) {
                return null; // an empty body is legal, if useless
            }
        }
        return null;
    }

    private static XMLInputFactory hardened() {
        return HardenedXml.input();
    }
}
