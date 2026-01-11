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
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.discover.keywords.DiscoverKeywordsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.keywords.DiscoverKeywordsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DiscoverKeywordsHandler implements DiscoverHandler {

    private static final String KEYWORD = "Keyword";

    private final DiscoverService discoverService;

    public DiscoverKeywordsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DiscoverKeywordsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DiscoverKeywordsRequest request = new DiscoverKeywordsRequestR(properties, restrictions);
        List<DiscoverKeywordsResponseRow> rows = discoverService.discoverKeywords(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DiscoverKeywordsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new DiscoverKeywordsRestrictionsR(Optional.ofNullable(m.get(KEYWORD)));
    }

    private void writeResponse(List<DiscoverKeywordsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DiscoverKeywordsResponseRow row : rows) {
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
        s1e1.setAttribute("sql:field", "Keyword");
        s1e1.setAttribute("name", "Keyword");
        s1e1.setAttribute("type", "xsd:string");

        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DiscoverKeywordsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        SoapUtil.addChildElement(row, ROW.QN_KEYWORD, r.keyword());
    }
}
