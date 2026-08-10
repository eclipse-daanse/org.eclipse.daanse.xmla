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

import org.eclipse.daanse.xmla.model.xmla.Command;
import org.eclipse.daanse.xmla.model.xmla.PropertyList;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

/**
 * Writes an {@code <Execute>} request.
 * <p>
 * Writes an Execute request from the model. version of the same thing: a
 * command is an EObject and {@link EcoreXmlWriter} writes any of them from the
 * model, so there is nothing here per command. That is the whole difference
 * between the two clients — one needed twenty-two generated serializers, this
 * needs none.
 */
public final class ExecuteRequestWriter {

    private ExecuteRequestWriter() {
        // static access only
    }

    /** Writes the {@code <Execute>} element: the command, then the properties. */
    public static void write(XMLStreamWriter out, Command command, PropertyList properties) throws XMLStreamException {
        out.setDefaultNamespace(XmlaNamespaces.XMLA);
        out.writeStartElement(XmlaNamespaces.XMLA, "Execute");
        out.writeDefaultNamespace(XmlaNamespaces.XMLA);

        out.writeStartElement(XmlaNamespaces.XMLA, "Command");
        if (command != null) {
            // The element is the command's own name - <Statement>, <Alter> - not the
            // feature's, which is what a server dispatches on.
            new EcoreXmlWriter(XmlaNamespaces.XMLA).write(out, command,
                    ExtendedMetaData.INSTANCE.getName(command.eClass()));
        }
        out.writeEndElement();

        out.writeStartElement(XmlaNamespaces.XMLA, "Properties");
        if (properties == null) {
            // <PropertyList/> is required even when empty; leaving it out makes msmdsrv
            // reject the request.
            out.writeEmptyElement(XmlaNamespaces.XMLA, "PropertyList");
        } else {
            new EcoreXmlWriter(XmlaNamespaces.XMLA).write(out, properties, "PropertyList");
        }
        out.writeEndElement();

        out.writeEndElement();
    }
}
