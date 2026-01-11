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
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.kpis.MdSchemaKpisRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.kpis.MdSchemaKpisRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaKpisHandler implements DiscoverHandler {

    private static final String CUBE_SOURCE = "CUBE_SOURCE";

    private final DiscoverService discoverService;

    public MdSchemaKpisHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaKpisRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaKpisRequest request = new MdSchemaKpisRequestR(properties, restrictions);
        List<MdSchemaKpisResponseRow> rows = discoverService.mdSchemaKpis(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaKpisRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaKpisRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.KPI_NAME)),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))));
    }

    private void writeResponse(List<MdSchemaKpisResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaKpisResponseRow row : rows) {
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
        SoapUtil.addElement(s, "KPI_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "KPI_CAPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_DISPLAY_FOLDER", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_VALUE", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_GOAL", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_STATUS", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_TREND", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_STATUS_GRAPHIC", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_TREND_GRAPHIC", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_WEIGHT", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_CURRENT_TIME_MEMBER", "xsd:string", "0");
        SoapUtil.addElement(s, "KPI_PARENT_KPI_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "SCOPE", "xsd:int", null);
        SoapUtil.addElement(s, "ANNOTATIONS", "xsd:string", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaKpisResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.measureGroupName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASUREGROUP_NAME, v));
        r.kpiName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_NAME, v));
        r.kpiCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_CAPTION, v));
        r.kpiDescription().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_DESCRIPTION, v));
        r.kpiDisplayFolder().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_DISPLAY_FOLDER, v));
        r.kpiValue().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_VALUE, v));
        r.kpiGoal().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_GOAL, v));
        r.kpiStatus().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_STATUS, v));
        r.kpiTrend().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_TREND, v));
        r.kpiStatusGraphic().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_STATUS_GRAPHIC, v));
        r.kpiTrendGraphic().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_TREND_GRAPHIC, v));
        r.kpiWight().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_WEIGHT, v));
        r.kpiCurrentTimeMember().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_CURRENT_TIME_MEMBER, v));
        r.kpiParentKpiName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_KPI_PARENT_KPI_NAME, v));
        r.scope().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCOPE, String.valueOf(v.getValue())));
        r.annotation().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ANNOTATIONS, v));
    }
}
