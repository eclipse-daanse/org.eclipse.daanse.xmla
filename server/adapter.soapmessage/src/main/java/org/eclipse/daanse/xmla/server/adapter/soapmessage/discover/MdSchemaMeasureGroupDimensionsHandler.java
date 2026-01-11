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
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MeasureGroupDimension;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaMeasureGroupDimensionsHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public MdSchemaMeasureGroupDimensionsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaMeasureGroupDimensionsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaMeasureGroupDimensionsRequest request = new MdSchemaMeasureGroupDimensionsRequestR(properties,
                restrictions);
        List<MdSchemaMeasureGroupDimensionsResponseRow> rows = discoverService.mdSchemaMeasureGroupDimensions(request,
                metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaMeasureGroupDimensionsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaMeasureGroupDimensionsRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.DIMENSION_UNIQUE_NAME)),
                Optional.ofNullable(m.get(ROW.MEASUREGROUP_NAME)),
                Optional.ofNullable(VisibilityEnum.fromValue(m.get(ROW.DIMENSION_VISIBILITY))));
    }

    private void writeResponse(List<MdSchemaMeasureGroupDimensionsResponseRow> rows, SOAPBody body)
            throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaMeasureGroupDimensionsResponseRow row : rows) {
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
        SoapUtil.addElement(s, "MEASUREGROUP_CARDINALITY", "xsd:string", "0");
        SoapUtil.addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "DIMENSION_CARDINALITY", "xsd:string", "0");
        SoapUtil.addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", "0");
        SoapUtil.addElement(s, "DIMENSION_IS_FACT_DIMENSION", "xsd:boolean", "0");
        SoapUtil.addElement(s, "DIMENSION_PATH", "xsd:string", "0");
        SoapUtil.addElement(s, "DIMENSION_GRANULARITY", "xsd:string", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaMeasureGroupDimensionsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));

        r.measureGroupName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASUREGROUP_NAME, v));
        r.measureGroupCardinality().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEASUREGROUP_CARDINALITY, v));
        r.dimensionUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_UNIQUE_NAME, v));
        r.dimensionCardinality().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_CARDINALITY, v.name()));
        r.dimensionIsVisible()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_IS_VISIBLE, String.valueOf(v)));
        r.dimensionIsFactDimension()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_IS_FACT_DIMENSION, String.valueOf(v)));
        r.dimensionPath().ifPresent(v -> addMeasureGroupDimensionList(row, v));
        r.dimensionGranularity().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_GRANULARITY, v));
    }

    private void addMeasureGroupDimensionList(SOAPElement el, List<MeasureGroupDimension> list) {
        if (list != null) {
            SOAPElement e = SoapUtil.addChildElement(el, ROW.QN_DIMENSION_PATH);
            list.forEach(it -> addMeasureGroupDimension(e, it));
        }
    }

    private void addMeasureGroupDimension(SOAPElement el, MeasureGroupDimension it) {
        SoapUtil.addChildElement(el, ROW.QN_MEASURE_GROUP_DIMENSION, it.measureGroupDimension());
    }
}
