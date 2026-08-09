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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.PropertyList;
import org.eclipse.daanse.xmla.model.xmla.RestrictionEntry;

/**
 * Writes a {@code <Discover>} request from the model.
 */
public final class DiscoverRequestWriter {

    private DiscoverRequestWriter() {
        // static access only
    }

    /** Writes the {@code <Discover>} element and everything below it. */
    public static void write(XMLStreamWriter out, Discover discover) throws XMLStreamException {
        // setDefaultNamespace must precede writeStartElement: the writer resolves the
        // element's prefix when the element is written, not when the declaration
        // follows it.
        out.setDefaultNamespace(XmlaNamespaces.XMLA);
        out.writeStartElement(XmlaNamespaces.XMLA, "Discover");
        out.writeDefaultNamespace(XmlaNamespaces.XMLA);

        out.writeStartElement(XmlaNamespaces.XMLA, "RequestType");
        out.writeCharacters(discover.getRequestType().getLiteral());
        out.writeEndElement();

        out.writeStartElement(XmlaNamespaces.XMLA, "Restrictions");
        out.writeStartElement(XmlaNamespaces.XMLA, "RestrictionList");
        if (discover.getRestrictions() != null) {
            for (RestrictionEntry entry : discover.getRestrictions().getRestrictionList()) {
                out.writeStartElement(XmlaNamespaces.XMLA, entry.getName());
                if (entry.getValue() != null) {
                    out.writeCharacters(entry.getValue());
                }
                out.writeEndElement();
            }
        }
        out.writeEndElement();
        out.writeEndElement();

        out.writeStartElement(XmlaNamespaces.XMLA, "Properties");
        PropertyList properties = discover.getProperties() == null ? null : discover.getProperties().getPropertyList();
        if (properties == null) {
            // <PropertyList/> is required even when empty; leaving it out makes msmdsrv
            // reject the request rather than fall back to its defaults.
            out.writeEmptyElement(XmlaNamespaces.XMLA, "PropertyList");
        } else {
            new EcoreXmlWriter(XmlaNamespaces.XMLA).write(out, properties, "PropertyList");
        }
        out.writeEndElement();

        out.writeEndElement(); // Discover
    }
}
