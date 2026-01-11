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

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.discover.enumerators.DiscoverEnumeratorsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.enumerators.DiscoverEnumeratorsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DiscoverEnumeratorsHandler implements DiscoverHandler {

    private static final String ENUM_NAME = "EnumName";

    private final DiscoverService discoverService;

    public DiscoverEnumeratorsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DiscoverEnumeratorsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DiscoverEnumeratorsRequest request = new DiscoverEnumeratorsRequestR(properties, restrictions);
        List<DiscoverEnumeratorsResponseRow> rows = discoverService.discoverEnumerators(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DiscoverEnumeratorsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new DiscoverEnumeratorsRestrictionsR(Optional.ofNullable(m.get(ENUM_NAME)));
    }

    private void writeResponse(List<DiscoverEnumeratorsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DiscoverEnumeratorsResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement ct1 = SoapUtil.addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "row");
        SOAPElement s1 = SoapUtil.addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e1 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e1.setAttribute("sql:field", "EnumName");
        s1e1.setAttribute("name", "EnumName");
        s1e1.setAttribute("type", "xsd:string");

        SOAPElement s1e2 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e2.setAttribute("sql:field", "EnumDescription");
        s1e2.setAttribute("name", "EnumDescription");
        s1e2.setAttribute("type", "xsd:string");
        s1e2.setAttribute("minOccurs", "0");

        SOAPElement s1e3 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e3.setAttribute("sql:field", "EnumType");
        s1e3.setAttribute("name", "EnumType");
        s1e3.setAttribute("type", "xsd:string");

        SOAPElement s1e4 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e4.setAttribute("sql:field", "ElementName");
        s1e4.setAttribute("name", "ElementName");
        s1e4.setAttribute("type", "xsd:string");

        SOAPElement s1e5 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e5.setAttribute("sql:field", "ElementDescription");
        s1e5.setAttribute("name", "ElementDescription");
        s1e5.setAttribute("type", "xsd:string");
        s1e5.setAttribute("minOccurs", "0");

        SOAPElement s1e6 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e6.setAttribute("sql:field", "ElementValue");
        s1e6.setAttribute("name", "ElementValue");
        s1e6.setAttribute("type", "xsd:string");
        s1e6.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DiscoverEnumeratorsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        SoapUtil.addChildElement(row, ROW.QN_ENUM_NAME, r.enumName());
        r.enumDescription().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ENUM_DESCRIPTION, v));
        SoapUtil.addChildElement(row, ROW.QN_ENUM_TYPE, r.enumType());
        SoapUtil.addChildElement(row, ROW.QN_ELEMENT_NAME, r.elementName());
        r.elementDescription().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ELEMENT_DESCRIPTION, v));
        r.elementValue().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ELEMENT_VALUE, v));
    }
}
