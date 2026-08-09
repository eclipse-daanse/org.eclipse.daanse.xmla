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

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;

/**
 * Writes a SOAP envelope whose body is produced by a callback.
 * <p>
 * The body is a callback rather than an object so that a response can be
 * streamed: a Discover over {@code MDSCHEMA_MEMBERS} may run to hundreds of
 * thousands of rows, and materialising them all before writing the first byte
 * is the largest allocation this server would ever make.
 */
public final class SoapEnvelopeWriter {

    /** Writes the contents of {@code <soap:Body>}. */
    @FunctionalInterface
    public interface BodyWriter {
        void write(XMLStreamWriter out) throws XMLStreamException;
    }

    private SoapEnvelopeWriter() {
        // static access only
    }

    /**
     * @param headers header blocks to write, may be empty; each is serialized from
     *                its own model
     */
    public static void write(XMLStreamWriter out, List<EObject> headers, BodyWriter body) throws XMLStreamException {
        out.writeStartDocument("UTF-8", "1.0");
        out.setPrefix(XmlaNamespaces.SOAP_ENV_PREFIX, XmlaNamespaces.SOAP_ENV);
        out.writeStartElement(XmlaNamespaces.SOAP_ENV, "Envelope");
        out.writeNamespace(XmlaNamespaces.SOAP_ENV_PREFIX, XmlaNamespaces.SOAP_ENV);

        out.writeStartElement(XmlaNamespaces.SOAP_ENV, "Header");
        for (EObject header : headers) {
            // Not every header is in the XMLA namespace: ProtocolCapabilities belongs
            // to the engine namespace, and a client looks for it there.
            new EcoreXmlWriter(namespaceOf(header)).write(out, header, wireNameOf(header));
        }
        out.writeEndElement();

        out.writeStartElement(XmlaNamespaces.SOAP_ENV, "Body");
        body.write(out);
        out.writeEndElement();

        out.writeEndElement();
        out.writeEndDocument();
        out.flush();
    }

    /**
     * The namespace a header block belongs to, taken from the {@code namespace}
     * detail the model states, or the XMLA namespace when it states none.
     * <p>
     * Read off the annotation rather than through
     * {@code ExtendedMetaData.getNamespace(EClassifier)}, which falls back to the
     * EPackage's own nsURI. That fallback is wrong here: the soap package's nsURI
     * is the SOAP envelope namespace, so {@code <Session>} — which belongs to the
     * XMLA namespace — would be written as {@code <soap:Session>} and no client
     * would recognise it.
     */
    private static String namespaceOf(EObject header) {
        EAnnotation annotation = header.eClass()
                .getEAnnotation(org.eclipse.emf.ecore.util.ExtendedMetaData.ANNOTATION_URI);
        String namespace = annotation == null ? null : annotation.getDetails().get("namespace");
        return namespace == null || namespace.isEmpty() || "##targetNamespace".equals(namespace) ? XmlaNamespaces.XMLA
                : namespace;
    }

    /**
     * The element name a header block takes on the wire, from its own
     * ExtendedMetaData.
     */
    private static String wireNameOf(EObject header) {
        String name = org.eclipse.emf.ecore.util.ExtendedMetaData.INSTANCE.getName(header.eClass());
        return name == null || name.isEmpty() ? header.eClass().getName() : name;
    }
}
