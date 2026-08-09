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

import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.PropertyList;
import org.eclipse.daanse.xmla.model.xmla.RequestTypeEnum;
import org.eclipse.daanse.xmla.model.xmla.RestrictionEntry;
import org.eclipse.daanse.xmla.model.xmla.Restrictions;
import org.eclipse.daanse.xmla.model.xmla.XmlaFactory;
import org.eclipse.daanse.xmla.model.xmla.XmlaPackage;

public final class DiscoverRequestReader {

    private DiscoverRequestReader() {
        // static access only
    }

    /**
     * @param in positioned on the {@code <Discover>} START_ELEMENT; left on its
     *           END_ELEMENT
     * @throws UnknownRequestTypeException if the request type names no rowset in
     *                                     this model
     */
    public static Discover read(XMLStreamReader in) throws XMLStreamException {
        Discover discover = XmlaFactory.eINSTANCE.createDiscover();
        Restrictions restrictions = XmlaFactory.eINSTANCE.createRestrictions();
        discover.setRestrictions(restrictions);
        boolean typed = false;

        int depth = 0;
        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.END_ELEMENT) {
                if (depth == 0) {
                    break; // </Discover>
                }
                depth--;
                continue;
            }
            if (event != XMLStreamConstants.START_ELEMENT) {
                continue;
            }
            switch (in.getLocalName()) {
            case "RequestType" -> {
                discover.setRequestType(requestType(in.getElementText().trim()));
                typed = true;
            }
            case "RestrictionList" -> readRestrictions(in, restrictions);
            case "PropertyList" -> {
                PropertyList properties = (PropertyList) new EcoreXmlReader(EcoreXmlReader.Unknown.SKIP).read(in,
                        XmlaPackage.eINSTANCE.getPropertyList());
                discover.setProperties(XmlaFactory.eINSTANCE.createProperties());
                discover.getProperties().setPropertyList(properties);
            }
            // <Restrictions> and <Properties> only wrap the two lists above, and an
            // unrecognised child is not this reader's to refuse.
            default -> depth++;
            }
        }

        if (!typed) {
            throw new XmlaCodecException("the Discover request carries no <RequestType>");
        }
        return discover;
    }

    private static RequestTypeEnum requestType(String text) {
        if (text.isEmpty()) {
            throw new XmlaCodecException("the Discover request carries an empty <RequestType>");
        }
        RequestTypeEnum known = RequestTypeEnum.get(text);
        if (known == null) {
            throw new UnknownRequestTypeException(text);
        }
        return known;
    }

    private static void readRestrictions(XMLStreamReader in, Restrictions into) throws XMLStreamException {
        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.END_ELEMENT) {
                return; // </RestrictionList>
            }
            if (event == XMLStreamConstants.START_ELEMENT) {
                readRestrictionValues(in, in.getLocalName(), into);
            }
        }
    }

    /**
     * One restriction, in either of the two forms a client sends.
     * <p>
     * SQL Server Management Studio writes several values by repeating the element:
     * {@code <PropertyName>a</PropertyName><PropertyName>b</PropertyName>}. Excel
     * writes them as a set inside one:
     * {@code <PropertyName><Value>a</Value><Value>b</Value></PropertyName>}.
     * <p>
     * Both mean the same thing and both become one entry per value, so a caller
     * cannot tell which client it is talking to — which is the point. Reading this
     * with {@code getElementText()} throws on the second form, so a real Excel
     * connect was refused with a parse error before its first
     * {@code DISCOVER_PROPERTIES} was answered.
     */
    private static void readRestrictionValues(XMLStreamReader in, String name, Restrictions into)
            throws XMLStreamException {
        StringBuilder text = new StringBuilder();
        int depth = 0;
        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.CHARACTERS || event == XMLStreamConstants.CDATA) {
                text.append(in.getText());
            } else if (event == XMLStreamConstants.START_ELEMENT) {
                // A wrapped value. Anything the text accumulated so far is whitespace between
                // the wrapper and its children.
                text.setLength(0);
                depth++;
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                String value = text.toString().trim();
                text.setLength(0);
                if (depth == 0) {
                    // </PropertyName>: the unwrapped form, unless children already supplied it.
                    if (!value.isEmpty()) {
                        add(into, name, value);
                    }
                    return;
                }
                depth--;
                add(into, name, value);
            }
        }
    }

    private static void add(Restrictions into, String name, String value) {
        RestrictionEntry entry = XmlaFactory.eINSTANCE.createRestrictionEntry();
        entry.setName(name);
        entry.setValue(value);
        into.getRestrictionList().add(entry);
    }
}
