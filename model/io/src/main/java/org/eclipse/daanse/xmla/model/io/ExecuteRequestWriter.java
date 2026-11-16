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
import org.eclipse.emf.ecore.EAnnotation;
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

    /**
     * The namespace a command is written in, as the model states it: every ASSL command carries
     * {@code elementNamespace} = the engine namespace in its Daanse annotation, and is written there with everything
     * under it ({@code <Create xmlns=".../2003/engine"><ObjectDefinition><Trace>}); {@code Statement} carries none and
     * stays in XMLA. The model puts all commands in one package, so the namespace has to be said per command, and saying
     * it in the model lets every writer of the model -- this one and the TypeScript client's -- read it from the same
     * place. Analysis Services 2017 refuses an ASSL command in the XMLA namespace: "The Create element ... (namespace
     * urn:schemas-microsoft-com:xml-analysis) cannot appear under Envelope/Body/Execute/Command".
     */
    static String namespaceOf(Command command) {
        EAnnotation daanse = command.eClass().getEAnnotation(EcoreXmlWriter.DAANSE_XMLA);
        String stated = daanse == null ? null : daanse.getDetails().get("elementNamespace");
        return stated == null ? XmlaNamespaces.XMLA : stated;
    }

    /**
     * Writes one command alone, its element in its own namespace with everything under it: the text a server trace
     * reports for the command (Command Begin and End), as Analysis Services writes it there,
     * {@code <Create xmlns="http://schemas.microsoft.com/analysisservices/2003/engine">…</Create>}.
     */
    public static void writeCommand(XMLStreamWriter out, Command command) throws XMLStreamException {
        new EcoreXmlWriter(namespaceOf(command)).write(out, command, ExtendedMetaData.INSTANCE.getName(command.eClass()));
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
            new EcoreXmlWriter(namespaceOf(command)).write(out, command,
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
