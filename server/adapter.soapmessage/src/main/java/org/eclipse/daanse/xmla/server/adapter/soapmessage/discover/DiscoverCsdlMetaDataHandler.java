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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.discover.csdlmetadata.DiscoverCsdlMetaDataRequest;
import org.eclipse.daanse.xmla.api.discover.discover.csdlmetadata.DiscoverCsdlMetaDataResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.discover.csdlmetadata.DiscoverCsdlMetaDataRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.csdlmetadata.DiscoverCsdlMetaDataRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaSoapException;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;
import org.xml.sax.SAXException;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DiscoverCsdlMetaDataHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public DiscoverCsdlMetaDataHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DiscoverCsdlMetaDataRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DiscoverCsdlMetaDataRequest request = new DiscoverCsdlMetaDataRequestR(properties, restrictions);
        List<DiscoverCsdlMetaDataResponseRow> rows = discoverService.csdlMetaData(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DiscoverCsdlMetaDataRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new DiscoverCsdlMetaDataRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.PERSPECTIVE_NAME)), Optional.ofNullable(m.get(ROW.VERSION)));
    }

    private void writeResponse(List<DiscoverCsdlMetaDataResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DiscoverCsdlMetaDataResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement el1complexType = SoapUtil.addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        el1complexType.setAttribute("name", "xmlDocument");
        SOAPElement sequence = SoapUtil.addChildElement(el1complexType, Constants.XSD.QN_SEQUENCE);
        SoapUtil.addChildElement(sequence, Constants.XSD.QN_ANY);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "METADATA", "xmlDocument", null);
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DiscoverCsdlMetaDataResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        addChildElementTextNode(row, r.metaData());
    }

    private void addChildElementTextNode(SOAPElement element, String valueOfChild) {
        try {
            SOAPElement createdChild = element.addChildElement(ROW.QN_META_DATA);
            createdChild.setAttribute("xmlns", "http://schemas.microsoft.com/analysisservices/2003/engine");
            createdChild.setAttribute("xmlns:ddl2", "http://schemas.microsoft.com/analysisservices/2003/engine/2");
            createdChild.setAttribute("xmlns:ddl2_2", "http://schemas.microsoft.com/analysisservices/2003/engine/2/2");
            createdChild.setAttribute("xmlns:ddl100", "http://schemas.microsoft.com/analysisservices/2008/engine/100");
            createdChild.setAttribute("xmlns:ddl100_100",
                    "http://schemas.microsoft.com/analysisservices/2008/engine/100/100");
            createdChild.setAttribute("xmlns:ddl200", "http://schemas.microsoft.com/analysisservices/2010/engine/200");
            createdChild.setAttribute("xmlns:ddl200_200",
                    "http://schemas.microsoft.com/analysisservices/2010/engine/200/200");
            createdChild.setAttribute("xmlns:ddl300", "http://schemas.microsoft.com/analysisservices/2011/engine/300");
            createdChild.setAttribute("xmlns:ddl300_300",
                    "http://schemas.microsoft.com/analysisservices/2011/engine/300/300");
            createdChild.setAttribute("xmlns:ddl400", "http://schemas.microsoft.com/analysisservices/2012/engine/400");
            createdChild.setAttribute("xmlns:ddl400_400",
                    "http://schemas.microsoft.com/analysisservices/2012/engine/400/400");
            if (valueOfChild != null && !valueOfChild.isEmpty()) {
                SOAPElement se = SoapUtil.xmlStringToSoapElement(valueOfChild, createdChild);
                createdChild.addChildElement(se);
            }
        } catch (SOAPException | SAXException | IOException | ParserConfigurationException e) {
            throw new XmlaSoapException("addChildElementTextNode error", e);
        }
    }
}
