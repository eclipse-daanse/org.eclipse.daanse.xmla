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

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.eclipse.daanse.xmla.model.xmla.Command;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.daanse.xmla.model.xmla.Parameter;
import org.eclipse.daanse.xmla.model.xmla.Parameters;
import org.eclipse.daanse.xmla.model.xmla.PropertyList;
import org.eclipse.daanse.xmla.model.xmla.XmlaFactory;
import org.eclipse.daanse.xmla.model.xmla.XmlaPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

/**
 * Reads an {@code <Execute>} request into the model.
 * <p>
 * The counterpart to {@link ExecuteRequestWriter}, and as short for the same
 * reason: a command is an EObject and {@link EcoreXmlReader} reads any of them
 * from the model, so there is nothing here per command.
 * <p>
 * Which command it is decides everything downstream, so the element's own name
 * is what selects the type rather than a list kept here. A name the model does
 * not describe is refused by that name — see {@link UnknownCommandException} —
 * instead of being read as "no command".
 */
public final class ExecuteRequestReader {

    private ExecuteRequestReader() {
        // static access only
    }

    /**
     * @param in positioned on the {@code <Execute>} START_ELEMENT; left on its
     *           END_ELEMENT
     * @throws UnknownCommandException if the command names no type in this model
     */
    public static Execute read(XMLStreamReader in) throws XMLStreamException {
        Execute execute = XmlaFactory.eINSTANCE.createExecute();

        int depth = 0;
        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.END_ELEMENT) {
                if (depth == 0) {
                    break; // </Execute>
                }
                depth--;
                continue;
            }
            if (event != XMLStreamConstants.START_ELEMENT) {
                continue;
            }
            String name = in.getLocalName();
            if ("Command".equals(name) || "Parameters".equals(name) || "Properties".equals(name)) {
                depth++;
                continue;
            }
            if ("PropertyList".equals(name)) {
                PropertyList properties = (PropertyList) new EcoreXmlReader(EcoreXmlReader.Unknown.SKIP).read(in,
                        XmlaPackage.eINSTANCE.getPropertyList());
                execute.setProperties(XmlaFactory.eINSTANCE.createProperties());
                execute.getProperties().setPropertyList(properties);
                continue;
            }
            if ("Parameter".equals(name)) {
                if (execute.getParameters() == null) {
                    execute.setParameters(XmlaFactory.eINSTANCE.createParameters());
                }
                execute.getParameters().getParameter().add(readParameter(in));
                continue;
            }
            if (execute.getCommand() == null) {
                execute.setCommand(readCommand(in, name));
                continue;
            }
            depth++;
        }

        if (execute.getCommand() == null) {
            throw new XmlaCodecException("the Execute request carries no command");
        }
        return execute;
    }

    private static Command readCommand(XMLStreamReader in, String name) throws XMLStreamException {
        EClassifier classifier = XmlaPackage.eINSTANCE.getEClassifier(name);
        if (!(classifier instanceof EClass eClass)) {
            throw new UnknownCommandException(name);
        }
        EObject read = new EcoreXmlReader(EcoreXmlReader.Unknown.SKIP).read(in, eClass);
        if (!(read instanceof Command command)) {
            // A type this package has that is not a command: <Statement> and <Alter>
            // are, <PropertyList> is not.
            throw new UnknownCommandException(name);
        }
        return command;
    }

    private static Parameter readParameter(XMLStreamReader in) throws XMLStreamException {
        Parameter parameter = XmlaFactory.eINSTANCE.createParameter();
        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.END_ELEMENT) {
                break; // </Parameter>
            }
            if (event == XMLStreamConstants.START_ELEMENT) {
                if ("Name".equals(in.getLocalName())) {
                    parameter.setName(in.getElementText());
                } else if ("Value".equals(in.getLocalName())) {
                    parameter.setValue(in.getElementText());
                } else {
                    skipSubtree(in);
                }
            }
        }
        return parameter;
    }

    private static void skipSubtree(XMLStreamReader in) throws XMLStreamException {
        int depth = 1;
        while (depth > 0 && in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                depth++;
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                depth--;
            }
        }
    }
}
