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
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsResponseRow;
import org.eclipse.daanse.xmla.api.xmla.Restriction;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.discover.schemarowsets.DiscoverSchemaRowsetsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.schemarowsets.DiscoverSchemaRowsetsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DiscoverSchemaRowsetsHandler implements DiscoverHandler {

    private static final String UUID_VALUE = "[0-9a-zA-Z]{8}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{4}-[0-9a-zA-Z]{12}";
    private static final String SCHEMA_NAME_LOW = "SchemaName";

    private final DiscoverService discoverService;

    public DiscoverSchemaRowsetsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DiscoverSchemaRowsetsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DiscoverSchemaRowsetsRequest request = new DiscoverSchemaRowsetsRequestR(properties, restrictions);
        List<DiscoverSchemaRowsetsResponseRow> rows = discoverService.discoverSchemaRowsets(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DiscoverSchemaRowsetsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new DiscoverSchemaRowsetsRestrictionsR(Optional.ofNullable(m.get(SCHEMA_NAME_LOW)));
    }

    private void writeResponse(List<DiscoverSchemaRowsetsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DiscoverSchemaRowsetsResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPElement body) throws SOAPException {
        SOAPElement seDiscoverResponse = body.addChildElement(Constants.MSXMLA.QN_DISCOVER_RESPONSE);
        SOAPElement seReturn = seDiscoverResponse.addChildElement(Constants.MSXMLA.QN_RETURN);
        SOAPElement seRoot = SoapUtil.addChildElement(seReturn, ROWSET.QN_ROOT);
        seRoot.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        seRoot.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        seRoot.setAttribute("xmlns:EX", Constants.EX.NS_URN);
        SOAPElement schema = SoapUtil.addChildElement(seRoot, Constants.XSD.QN_SCHEMA);
        schema.setAttribute("xmlns:xsd", Constants.XSD.NS_URN);
        schema.setAttribute("xmlns", ROWSET.NS_URN);
        schema.setAttribute("xmlns:xsi", Constants.XSI.NS_URN);
        schema.setAttribute("xmlns:sql", Constants.SQL.NS_URN);
        schema.setAttribute("targetNamespace", ROWSET.NS_URN);
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

        SOAPElement ct1 = SoapUtil.addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "row");
        SOAPElement s1 = SoapUtil.addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e1 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e1.setAttribute("sql:field", "SchemaName");
        s1e1.setAttribute("name", "SchemaName");
        s1e1.setAttribute("type", "xsd:string");
        SOAPElement s1e2 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e2.setAttribute("sql:field", "SchemaGuid");
        s1e2.setAttribute("name", "SchemaGuid");
        s1e2.setAttribute("type", "uuid");
        s1e2.setAttribute("minOccurs", "0");
        SOAPElement s1e3 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e3.setAttribute("sql:field", "Restrictions");
        s1e3.setAttribute("name", "Restrictions");
        s1e3.setAttribute("minOccurs", "0");
        s1e3.setAttribute("maxOccurs", "unbounded");
        SOAPElement s1e3ct = SoapUtil.addChildElement(s1e3, Constants.XSD.QN_COMPLEX_TYPE);
        SOAPElement s1e3cts = SoapUtil.addChildElement(s1e3ct, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e3ctse1 = SoapUtil.addChildElement(s1e3cts, Constants.XSD.QN_ELEMENT);
        s1e3ctse1.setAttribute("name", "Name");
        s1e3ctse1.setAttribute("type", "xsd:string");
        s1e3ctse1.setAttribute("sql:field", "Name");
        SOAPElement s1e3ctse2 = SoapUtil.addChildElement(s1e3cts, Constants.XSD.QN_ELEMENT);
        s1e3ctse2.setAttribute("name", "Type");
        s1e3ctse2.setAttribute("type", "xsd:string");
        s1e3ctse2.setAttribute("sql:field", "Type");

        SOAPElement s1e4 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e4.setAttribute("sql:field", "Description");
        s1e4.setAttribute("name", "Description");
        s1e4.setAttribute("type", "xsd:string");
        s1e4.setAttribute("minOccurs", "0");

        SOAPElement s1e5 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e5.setAttribute("sql:field", "RestrictionsMask");
        s1e5.setAttribute("name", "RestrictionsMask");
        s1e5.setAttribute("type", "xsd:unsignedLong");
        s1e5.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DiscoverSchemaRowsetsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME_LC, r.schemaName());
        r.schemaGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_GUID, v));
        r.restrictions().ifPresent(v -> addRestrictionList(row, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION_LC, v));
        r.restrictionsMask().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_RESTRICTIONS_MASK, String.valueOf(v)));
    }

    private void addRestrictionList(SOAPElement el, List<Restriction> list) {
        if (list != null) {
            list.forEach(it -> addRestriction(el, it));
        }
    }

    private void addRestriction(SOAPElement e, Restriction it) {
        if (it != null) {
            SOAPElement el = SoapUtil.addChildElement(e, ROW.QN_RESTRICTIONS);
            SOAPElement name = SoapUtil.addChildElement(el, ROW.QN_NAME_LC);
            name.setTextContent(it.name());
            SOAPElement type = SoapUtil.addChildElement(el, ROW.QN_TYPE_LC);
            type.setTextContent(it.type());
        }
    }
}
