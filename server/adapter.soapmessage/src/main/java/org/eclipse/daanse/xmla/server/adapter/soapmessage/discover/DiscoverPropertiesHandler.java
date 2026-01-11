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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.discover;

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROPERTY_NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.RESTRICTION_LIST;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.discover.properties.DiscoverPropertiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.properties.DiscoverPropertiesRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;
import org.w3c.dom.NodeList;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DiscoverPropertiesHandler implements DiscoverHandler {

    private static final String UUID_VALUE = "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}";

    private final DiscoverService discoverService;

    public DiscoverPropertiesHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DiscoverPropertiesRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DiscoverPropertiesRequest request = new DiscoverPropertiesRequestR(properties, restrictions);
        List<DiscoverPropertiesResponseRow> rows = discoverService.discoverProperties(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DiscoverPropertiesRestrictionsR parseRestrictions(SOAPElement restriction) {
        NodeList nodeList = restriction.getElementsByTagName(RESTRICTION_LIST);
        List<String> pnList = new ArrayList<>();
        if (nodeList != null && nodeList.getLength() > 0 && nodeList.item(0) instanceof SOAPElement sEl) {
            pnList = getValuesByTag(sEl, PROPERTY_NAME);
        }
        return new DiscoverPropertiesRestrictionsR(pnList);
    }

    private List<String> getValuesByTag(SOAPElement el, String tagName) {
        NodeList nodeList = el.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return getValues(nodeList.item(0).getChildNodes());
        }
        return List.of();
    }

    private List<String> getValues(NodeList nl) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node n = nl.item(i);
            String val = n.getTextContent().trim();
            if (val.length() > 0) {
                result.add(val);
            }
        }
        return result;
    }

    private void writeResponse(List<DiscoverPropertiesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DiscoverPropertiesResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPElement body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns", "urn:schemas-microsoft-com:xml-analysis:rowset");
        seRoot.setAttribute("xmlns:EX", "urn:schemas-microsoft-com:xml-analysis:exception");
        SOAPElement schema = SoapUtil.addChildElement(seRoot, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        schema.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        schema.setAttribute("xmlns", "urn:schemas-microsoft-com:xml-analysis:rowset");
        schema.setAttribute("targetNamespace", ROWSET.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("elementFormDefault", "qualified");
        SOAPElement el = SoapUtil.addChildElement(schema, Constants.XSD.QN_ELEMENT);
        el.setAttribute("name", "root");
        SOAPElement ct = SoapUtil.addChildElement(el, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement s = SoapUtil.addChildElement(ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement se = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se.setAttribute("name", "row");
        se.setAttribute("type", "row");
        se.setAttribute("minOccurs", "0");
        se.setAttribute("maxOccurs", "unbounded");

        SOAPElement st = SoapUtil.addChildElement(schema, Constants.XSD.QN_SIMPLE_TYPE);
        st.setAttribute("name", "uuid");
        SOAPElement r = SoapUtil.addChildElement(st, Constants.XSD.QN_RESTRICTION);
        r.setAttribute("base", "xsd:string");
        SOAPElement p = SoapUtil.addChildElement(r, Constants.XSD.QN_PATTERN);
        p.setAttribute("value", UUID_VALUE);

        SOAPElement ct2 = SoapUtil.addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct2.setAttribute("name", "row");
        SOAPElement s2 = SoapUtil.addChildElement(ct2, Constants.XSD.QN_SEQUENCE);
        SOAPElement s2e1 = SoapUtil.addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e1.setAttribute("sql:field", "PropertyName");
        s2e1.setAttribute("name", "PropertyName");
        s2e1.setAttribute("type", "xsd:string");

        SOAPElement s2e2 = SoapUtil.addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e2.setAttribute("sql:field", "PropertyDescription");
        s2e2.setAttribute("name", "PropertyDescription");
        s2e2.setAttribute("type", "xsd:string");

        SOAPElement s2e3 = SoapUtil.addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e3.setAttribute("sql:field", "PropertyType");
        s2e3.setAttribute("name", "PropertyType");
        s2e3.setAttribute("type", "xsd:string");

        SOAPElement s2e4 = SoapUtil.addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e4.setAttribute("sql:field", "PropertyAccessType");
        s2e4.setAttribute("name", "PropertyAccessType");
        s2e4.setAttribute("type", "xsd:string");

        SOAPElement s2e5 = SoapUtil.addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e5.setAttribute("sql:field", "IsRequired");
        s2e5.setAttribute("name", "IsRequired");
        s2e5.setAttribute("type", "xsd:boolean");

        SOAPElement s2e6 = SoapUtil.addChildElement(s2, Constants.XSD.QN_ELEMENT);
        s2e6.setAttribute("sql:field", "Value");
        s2e6.setAttribute("name", "Value");
        s2e6.setAttribute("type", "xsd:string");

        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DiscoverPropertiesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        SoapUtil.addChildElement(row, ROW.QN_PROPERTY_NAME_LC, r.propertyName());
        r.propertyDescription().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PROPERTY_DESCRIPTION, v));
        r.propertyType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PROPERTY_TYPE_LC, v));
        SoapUtil.addChildElement(row, ROW.QN_PROPERTY_ACCESS_TYPE, r.propertyAccessType().getValue());
        r.required().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_REQUIRED, String.valueOf(v)));
        r.value().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_VALUE, v));
    }
}
