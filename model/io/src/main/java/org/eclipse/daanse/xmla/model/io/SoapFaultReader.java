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

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Recognises a SOAP fault in a response and throws it as the refusal it is.
 * <p>
 * The pair of {@link SoapFaultWriter}: what that one writes -
 * {@code faultcode}, {@code faultstring}, the {@code EX:Error} detail with its
 * {@code Description} - this one reads, through the same hardened factory every
 * other reader uses.
 */
public final class SoapFaultReader {

    private SoapFaultReader() {
        // static access only
    }

    /**
     * Throws {@link XmlaFaultException} when the bytes carry a SOAP fault; returns
     * silently when they do not. The details are the {@code faultstring} and any
     * {@code Description} attributes of the fault's error details, in document
     * order.
     */
    public static void failIfFault(byte[] response) {
        List<String> details;
        try {
            details = faultDetails(response);
        } catch (XMLStreamException unreadable) {
            // Whether these bytes are a fault is all this reader decides; a message it
            // cannot parse is the codec's complaint to make when it reads the response.
            return;
        }
        if (details == null) {
            return;
        }
        throw new XmlaFaultException(details.isEmpty() ? "the server answered a SOAP fault"
                : "the server answered a SOAP fault: " + String.join(" / ", details), details);
    }

    /** The fault's details, or {@code null} when the response carries no fault. */
    private static List<String> faultDetails(byte[] response) throws XMLStreamException {
        XMLStreamReader in = HardenedXml.input().createXMLStreamReader(new ByteArrayInputStream(response));
        try {
            boolean fault = false;
            List<String> details = new ArrayList<>(2);
            while (in.hasNext()) {
                if (in.next() != XMLStreamConstants.START_ELEMENT) {
                    continue;
                }
                String name = in.getLocalName();
                if ("Fault".equals(name)) {
                    fault = true;
                } else if (fault && "faultstring".equals(name)) {
                    String text = in.getElementText().strip();
                    if (!text.isEmpty()) {
                        details.add(text);
                    }
                } else if (fault) {
                    String description = in.getAttributeValue(null, "Description");
                    if (description != null && !description.isEmpty()) {
                        details.add(description);
                    }
                }
            }
            return fault ? details : null;
        } finally {
            in.close();
        }
    }
}
