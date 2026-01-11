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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFactory;

/**
 * Utility class for basic SOAP operations and Discover response handling.
 */
public class SoapUtil {

    private static final String UUID_VALUE = "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}";
    private static final Logger LOGGER = LoggerFactory.getLogger(SoapUtil.class);

    private SoapUtil() {
        // utility class
    }

    public static SOAPElement prepareSequenceElement(SOAPElement schema) throws SOAPException {
        SOAPElement ct = addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct.setAttribute("name", "row");
        return addChildElement(ct, Constants.XSD.QN_SEQUENCE);
    }

    public static SOAPElement prepareRootElement(SOAPElement body) throws SOAPException {
        SOAPElement seDiscoverResponse = body.addChildElement(Constants.MSXMLA.QN_DISCOVER_RESPONSE);
        SOAPElement seReturn = seDiscoverResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        return seReturn.addChildElement(DiscoverConstants.ROWSET.QN_ROOT);
    }

    public static void addElement(SOAPElement s, String name, String type, String minOccurs) {
        SOAPElement se = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se.setAttribute("sql:field", name);
        se.setAttribute("name", name);
        if (type != null) {
            se.setAttribute("type", type);
        }
        if (minOccurs != null) {
            se.setAttribute("minOccurs", minOccurs);
        }
    }

    public static SOAPElement fillRoot(SOAPElement root) {
        root.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        root.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        SOAPElement schema = addChildElement(root, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        schema.setAttribute("xmlns", DiscoverConstants.ROWSET.NS_URN);
        schema.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("targetNamespace", DiscoverConstants.ROWSET.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");

        SOAPElement el = addChildElement(schema, Constants.XSD.QN_ELEMENT);
        el.setAttribute("name", "root");
        SOAPElement ct = addChildElement(el, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement s = addChildElement(ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement se = addChildElement(s, Constants.XSD.QN_ELEMENT);
        se.setAttribute("name", "row");
        se.setAttribute("type", "row");
        se.setAttribute("minOccurs", "0");
        se.setAttribute("maxOccurs", "unbounded");

        SOAPElement st = addChildElement(schema, Constants.XSD.QN_SIMPLE_TYPE);
        st.setAttribute("name", "uuid");
        SOAPElement r = addChildElement(st, Constants.XSD.QN_RESTRICTION);
        r.setAttribute("base", "xsd:string");
        SOAPElement p = addChildElement(r, Constants.XSD.QN_PATTERN);
        p.setAttribute("value", UUID_VALUE);
        return schema;
    }

    public static SOAPElement xmlStringToSoapElement(String xmlFragment, SOAPElement context)
            throws SAXException, IOException, ParserConfigurationException, SOAPException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        // XXE prevention
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(xmlFragment)));

        Element root = doc.getDocumentElement();

        Node imported = context.getOwnerDocument().importNode(root, true);

        if (imported instanceof SOAPElement) {
            return (SOAPElement) imported;
        } else {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPElement newElement = soapFactory.createElement(root.getNodeName(), "", root.getNamespaceURI());

            for (int i = 0; i < root.getAttributes().getLength(); i++) {
                Node attr = root.getAttributes().item(i);
                newElement.addAttribute(soapFactory.createName(attr.getNodeName()), attr.getNodeValue());
            }

            Node child = root.getFirstChild();
            while (child != null) {
                Node importedChild = context.getOwnerDocument().importNode(child, true);
                newElement.appendChild(importedChild);
                child = child.getNextSibling();
            }

            return newElement;
        }
    }

    public static SOAPElement addChildElement(SOAPElement element, QName qNameOfChild, String valueOfChild) {
        try {
            SOAPElement createdChild = element.addChildElement(qNameOfChild);
            createdChild.setTextContent(valueOfChild);
            return createdChild;

        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", qNameOfChild);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }

    public static SOAPElement addChildElement(SOAPElement element, QName qNameOfChild) {
        try {
            return element.addChildElement(qNameOfChild);
        } catch (SOAPException e) {
            LOGGER.error("addChildElement {} error", qNameOfChild);
            throw new XmlaSoapException("addChildElement error", e);
        }
    }
}
