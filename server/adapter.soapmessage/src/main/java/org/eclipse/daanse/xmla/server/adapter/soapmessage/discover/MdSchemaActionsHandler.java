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
import org.eclipse.daanse.xmla.api.common.enums.CoordinateTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.InvocationEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.actions.MdSchemaActionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.actions.MdSchemaActionsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaActionsHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public MdSchemaActionsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaActionsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaActionsRequest request = new MdSchemaActionsRequestR(properties, restrictions);
        List<MdSchemaActionsResponseRow> rows = discoverService.mdSchemaActions(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaActionsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaActionsRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), m.get(ROW.CUBE_NAME),
                Optional.ofNullable(m.get(ROW.ACTION_NAME)),
                // Optional.ofNullable(ActionTypeEnum.fromValue(m.get(ACTION_TYPE))), //wrong
                // value from excel
                Optional.empty(), Optional.ofNullable(m.get(ROW.COORDINATE)),
                CoordinateTypeEnum.fromValue(m.get(ROW.COORDINATE_TYPE)),
                m.get(ROW.INVOCATION) == null ? null : InvocationEnum.fromValue(m.get(ROW.INVOCATION)),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(ROW.CUBE_SOURCE))));
    }

    private void writeResponse(List<MdSchemaActionsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaActionsResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "CATALOG_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "SCHEMA_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "CUBE_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "ACTION_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "ACTION_TYPE", "xsd:int", "0");
        SoapUtil.addElement(s, "COORDINATE", "xsd:string", "0");
        SoapUtil.addElement(s, "COORDINATE_TYPE", "xsd:int", "0");
        SoapUtil.addElement(s, "ACTION_CAPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "CONTENT", "xsd:string", "0");
        SoapUtil.addElement(s, "APPLICATION", "xsd:string", "0");
        SoapUtil.addElement(s, "INVOCATION", "xsd:int", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaActionsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, r.cubeName());
        r.actionName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ACTION_NAME, v));
        r.actionType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ACTION_TYPE, String.valueOf(v.getValue())));
        SoapUtil.addChildElement(row, ROW.QN_COORDINATE, r.coordinate());
        SoapUtil.addChildElement(row, ROW.QN_COORDINATE_TYPE, String.valueOf(r.coordinateType().getValue()));
        r.actionCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ACTION_CAPTION, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.content().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CONTENT, v));
        r.application().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_APPLICATION, v));
        r.invocation().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_INVOCATION, String.valueOf(v.getValue())));
    }
}
