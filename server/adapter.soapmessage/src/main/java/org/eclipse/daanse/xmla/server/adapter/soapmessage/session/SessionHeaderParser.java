/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.session;

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInteger;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.BeginSession;
import org.eclipse.daanse.xmla.api.xmla.EndSession;
import org.eclipse.daanse.xmla.api.xmla.Session;
import org.eclipse.daanse.xmla.model.record.xmla.BeginSessionR;
import org.eclipse.daanse.xmla.model.record.xmla.EndSessionR;
import org.eclipse.daanse.xmla.model.record.xmla.SessionR;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import jakarta.xml.soap.SOAPElement;

/**
 * Parser for SOAP header session elements. Handles Session, BeginSession, and
 * EndSession parsing.
 */
public class SessionHeaderParser {

    private static final String XMLA_NS = "urn:schemas-microsoft-com:xml-analysis";

    private SessionHeaderParser() {
        // utility class
    }

    public static Optional<Session> getSession(SOAPElement soapHeader) {
        NodeList nl = soapHeader.getElementsByTagNameNS(XMLA_NS, "Session");
        if (nl != null && nl.getLength() > 0) {
            NamedNodeMap nnm = nl.item(0).getAttributes();
            org.w3c.dom.Node n = nnm.getNamedItem("SessionId");
            org.w3c.dom.Node n1 = nnm.getNamedItem("mustUnderstand");
            String sId = null;
            Integer mustUnderstand = null;
            if (n != null) {
                sId = n.getNodeValue();
            }
            if (n1 != null) {
                mustUnderstand = toInteger(n1.getNodeValue());
            }
            return Optional.of(new SessionR(sId, mustUnderstand));
        }
        return Optional.empty();
    }

    public static Optional<EndSession> getEndSession(SOAPElement soapHeader) {
        NodeList nl = soapHeader.getElementsByTagNameNS(XMLA_NS, "EndSession");
        if (nl != null && nl.getLength() > 0) {
            NamedNodeMap nnm = nl.item(0).getAttributes();
            org.w3c.dom.Node n = nnm.getNamedItem("SessionId");
            org.w3c.dom.Node n1 = nnm.getNamedItem("mustUnderstand");
            String sId = null;
            Integer mustUnderstand = null;
            if (n != null) {
                sId = n.getNodeValue();
            }
            if (n1 != null) {
                mustUnderstand = toInteger(n1.getNodeValue());
            }
            return Optional.of(new EndSessionR(sId, mustUnderstand));
        }
        return Optional.empty();
    }

    public static Optional<BeginSession> getBeginSession(SOAPElement soapHeader) {
        NodeList nl = soapHeader.getElementsByTagNameNS(XMLA_NS, "BeginSession");
        if (nl != null && nl.getLength() > 0) {
            NamedNodeMap nnm = nl.item(0).getAttributes();
            org.w3c.dom.Node n = nnm.getNamedItem("mustUnderstand");
            Integer mustUnderstand = null;
            if (n != null) {
                mustUnderstand = toInteger(n.getNodeValue());
            }
            return Optional.of(new BeginSessionR(mustUnderstand));
        }
        return Optional.empty();
    }
}
