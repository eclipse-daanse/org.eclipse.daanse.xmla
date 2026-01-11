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
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaMeasureGroupsHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public MdSchemaMeasureGroupsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaMeasureGroupsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaMeasureGroupsRequest request = new MdSchemaMeasureGroupsRequestR(properties, restrictions);
        List<MdSchemaMeasureGroupsResponseRow> rows = discoverService.mdSchemaMeasureGroups(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaMeasureGroupsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaMeasureGroupsRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.MEASUREGROUP_NAME)));
    }

    private void writeResponse(List<MdSchemaMeasureGroupsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaMeasureGroupsResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "CATALOG_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "CUBE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "IS_WRITE_ENABLED", "xsd:boolean", null);
        SoapUtil.addElement(s, "MEASUREGROUP_CAPTION", "xsd:string", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaMeasureGroupsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.measureGroupName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASUREGROUP_NAME, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.isWriteEnabled().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_WRITE_ENABLED, String.valueOf(v)));
        r.measureGroupCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASUREGROUP_CAPTION, v));
    }
}
