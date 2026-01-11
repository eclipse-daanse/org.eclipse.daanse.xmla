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
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaMeasuresHandler implements DiscoverHandler {

    private static final String CUBE_SOURCE = "CUBE_SOURCE";
    private static final String MEASURE_VISIBILITY = "MEASURE_VISIBILITY";

    private final DiscoverService discoverService;

    public MdSchemaMeasuresHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaMeasuresRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaMeasuresRequest request = new MdSchemaMeasuresRequestR(properties, restrictions);
        List<MdSchemaMeasuresResponseRow> rows = discoverService.mdSchemaMeasures(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaMeasuresRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaMeasuresRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.MEASURE_NAME)), Optional.ofNullable(m.get(ROW.MEASURE_UNIQUE_NAME)),
                Optional.ofNullable(m.get(ROW.MEASUREGROUP_NAME)),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
                Optional.ofNullable(VisibilityEnum.fromValue(m.get(MEASURE_VISIBILITY))));
    }

    private void writeResponse(List<MdSchemaMeasuresResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaMeasuresResponseRow row : rows) {
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
        SoapUtil.addElement(s, "MEASURE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "MEASURE_UNIQUE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "MEASURE_CAPTION", "xsd:string", null);
        SoapUtil.addElement(s, "MEASURE_GUID", "uuid", "0");
        SoapUtil.addElement(s, "MEASURE_AGGREGATOR", "xsd:int", null);
        SoapUtil.addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        SoapUtil.addElement(s, "MEASURE_IS_VISIBLE", "xsd:boolean", null);
        SoapUtil.addElement(s, "LEVELS_LIST", "xsd:string", "0");
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "NUMERIC_PRECISION", "xsd:short", "0");
        SoapUtil.addElement(s, "NUMERIC_SCALE", "xsd:short", "0");
        SoapUtil.addElement(s, "MEASURE_UNITS", "xsd:string", "0");
        SoapUtil.addElement(s, "EXPRESSION", "xsd:string", "0");
        SoapUtil.addElement(s, "MEASURE_NAME_SQL_COLUMN_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "MEASURE_UNQUALIFIED_CAPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "MEASUREGROUP_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "MEASURE_DISPLAY_FOLDER", "xsd:string", "0");
        SoapUtil.addElement(s, "DEFAULT_FORMAT_STRING", "xsd:string", "0");
        SoapUtil.addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "MEASURE_VISIBILITY", "xsd:unsignedShort", "0");
        return seRoot;
    }

    @SuppressWarnings("deprecation")
    private void addResponseRow(SOAPElement root, MdSchemaMeasuresResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.measureName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_NAME, v));
        r.measureUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_UNIQUE_NAME, v));
        r.measureCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_CAPTION, v));
        r.measureGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_GUID, String.valueOf(v)));
        r.measureAggregator()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_AGGREGATOR, String.valueOf(v.getValue())));
        r.dataType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATA_TYPE, String.valueOf(v.getValue())));
        r.numericPrecision().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_NUMERIC_PRECISION, String.valueOf(v)));
        r.numericScale().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_NUMERIC_SCALE, String.valueOf(v)));
        r.measureUnits().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_UNITS, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.expression().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_EXPRESSION, v));
        r.measureIsVisible()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_IS_VISIBLE, String.valueOf(v)));
        r.levelsList().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVELS_LIST, v));
        r.measureNameSqlColumnName()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_NAME_SQL_COLUMN_NAME, v));
        r.measureUnqualifiedCaption()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_UNQUALIFIED_CAPTION, v));
        r.measureGroupName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASUREGROUP_NAME, v));
        r.measureDisplayFolder().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASURE_DISPLAY_FOLDER, v));
        r.defaultFormatString().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DEFAULT_FORMAT_STRING, v));
        r.cubeSource().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_SOURCE, String.valueOf(v.getValue())));
    }
}
