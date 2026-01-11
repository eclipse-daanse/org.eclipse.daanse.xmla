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
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.discover.literals.DiscoverLiteralsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.literals.DiscoverLiteralsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DiscoverLiteralsHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public DiscoverLiteralsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DiscoverLiteralsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DiscoverLiteralsRequest request = new DiscoverLiteralsRequestR(properties, restrictions);
        List<DiscoverLiteralsResponseRow> rows = discoverService.discoverLiterals(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DiscoverLiteralsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new DiscoverLiteralsRestrictionsR(Optional.ofNullable(m.get(ROW.LITERAL_NAME)));
    }

    private void writeResponse(List<DiscoverLiteralsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DiscoverLiteralsResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SOAPElement se1 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se1.setAttribute("sql:field", "LiteralName");
        se1.setAttribute("name", "LiteralName");
        se1.setAttribute("type", "xsd:string");

        SOAPElement se2 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se2.setAttribute("sql:field", "LiteralValue");
        se2.setAttribute("name", "LiteralValue");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se3.setAttribute("sql:field", "LiteralInvalidChars");
        se3.setAttribute("name", "LiteralInvalidChars");
        se3.setAttribute("type", "xsd:string");
        se3.setAttribute("minOccurs", "0");

        SOAPElement se4 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se4.setAttribute("sql:field", "LiteralInvalidStartingChars");
        se4.setAttribute("name", "LiteralInvalidStartingChars");
        se4.setAttribute("type", "xsd:string");
        se4.setAttribute("minOccurs", "0");

        SOAPElement se5 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se5.setAttribute("sql:field", "LiteralMaxLength");
        se5.setAttribute("name", "LiteralMaxLength");
        se5.setAttribute("type", "xsd:int");
        se5.setAttribute("minOccurs", "0");

        SOAPElement se6 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se6.setAttribute("sql:field", "LiteralNameEnumValue");
        se6.setAttribute("name", "LiteralNameEnumValue");
        se6.setAttribute("type", "xsd:int");
        se6.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DiscoverLiteralsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        SoapUtil.addChildElement(row, ROW.QN_LITERAL_NAME, r.literalName());
        SoapUtil.addChildElement(row, ROW.QN_LITERAL_VALUE, r.literalValue());
        SoapUtil.addChildElement(row, ROW.QN_LITERAL_INVALID_CHARS, r.literalInvalidChars());
        SoapUtil.addChildElement(row, ROW.QN_LITERAL_INVALID_STARTING_CHARS, r.literalInvalidStartingChars());
        SoapUtil.addChildElement(row, ROW.QN_LITERAL_MAX_LENGTH, String.valueOf(r.literalMaxLength()));
        SoapUtil.addChildElement(row, ROW.QN_LITERAL_NAME_ENUM_VALUE,
                String.valueOf(r.literalNameEnumValue().getValue()));
    }
}
