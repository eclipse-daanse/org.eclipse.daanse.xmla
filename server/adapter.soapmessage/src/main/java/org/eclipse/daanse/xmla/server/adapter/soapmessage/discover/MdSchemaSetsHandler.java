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
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.ScopeEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.sets.MdSchemaSetsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.sets.MdSchemaSetsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaSetsHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public MdSchemaSetsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaSetsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaSetsRequest request = new MdSchemaSetsRequestR(properties, restrictions);
        List<MdSchemaSetsResponseRow> rows = discoverService.mdSchemaSets(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaSetsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaSetsRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.SET_NAME)), Optional.ofNullable(ScopeEnum.fromValue(m.get(ROW.SCOPE))),
                Optional.ofNullable(m.get(ROW.SET_CAPTION)),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(ROW.CUBE_SOURCE))),
                Optional.ofNullable(m.get(ROW.HIERARCHY_UNIQUE_NAME)));
    }

    private void writeResponse(List<MdSchemaSetsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaSetsResponseRow row : rows) {
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
        SoapUtil.addElement(s, "SET_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "SCOPE", "xsd:int", null);
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "EXPRESSION", "xsd:string", "0");
        SoapUtil.addElement(s, "DIMENSIONS", "xsd:string", "0");
        SoapUtil.addElement(s, "SET_CAPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "SET_DISPLAY_FOLDER", "xsd:string", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaSetsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.setName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SET_NAME, v));
        r.scope().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCOPE, String.valueOf(v.getValue())));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.expression().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_EXPRESSION, v));
        r.dimension().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSIONS, v));
        r.setCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SET_CAPTION, v));
        r.setDisplayFolder().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SET_DISPLAY_FOLDER, v));
    }
}
