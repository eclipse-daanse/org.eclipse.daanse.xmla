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
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaDimensionsHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public MdSchemaDimensionsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaDimensionsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaDimensionsRequest request = new MdSchemaDimensionsRequestR(properties, restrictions);
        List<MdSchemaDimensionsResponseRow> rows = discoverService.mdSchemaDimensions(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaDimensionsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaDimensionsRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.DIMENSION_NAME)), Optional.ofNullable(m.get(ROW.DIMENSION_UNIQUE_NAME)),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(ROW.CUBE_SOURCE))),
                Optional.ofNullable(VisibilityEnum.fromValue(m.get(ROW.DIMENSION_VISIBILITY))));
    }

    private void writeResponse(List<MdSchemaDimensionsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaDimensionsResponseRow row : rows) {
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
        SoapUtil.addElement(s, "DIMENSION_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "DIMENSION_GUID", "uuid", "0");
        SoapUtil.addElement(s, "DIMENSION_CAPTION", "xsd:string", null);
        SoapUtil.addElement(s, "DIMENSION_ORDINAL", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "DIMENSION_TYPE", "xsd:short", null);
        SoapUtil.addElement(s, "DIMENSION_CARDINALITY", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "DEFAULT_HIERARCHY", "xsd:string", null);
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "IS_VIRTUAL", "xsd:boolean", "0");
        SoapUtil.addElement(s, "IS_READWRITE", "xsd:boolean", "0");
        SoapUtil.addElement(s, "DIMENSION_UNIQUE_SETTINGS", "xsd:int", "0");
        SoapUtil.addElement(s, "DIMENSION_MASTER_UNIQUE_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", "0");
        SoapUtil.addElement(s, "HIERARCHIES", null, "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaDimensionsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.dimensionName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_UNIQUE_NAME, v));
        r.dimensionGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_GUID, String.valueOf(v)));
        r.dimensionCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_CAPTION, v));
        r.dimensionOptional()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_ORDINAL, String.valueOf(v)));
        r.dimensionType()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_TYPE, String.valueOf(v.getValue())));
        r.dimensionCardinality()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_CARDINALITY, String.valueOf(v)));
        r.defaultHierarchy().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DEFAULT_HIERARCHY, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.isVirtual().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_VIRTUAL, String.valueOf(v)));
        r.isReadWrite().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_READWRITE, String.valueOf(v)));
        r.dimensionUniqueSetting().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_UNIQUE_SETTINGS, String.valueOf(v.getValue())));
        r.dimensionMasterName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_MASTER_NAME, v));
        r.dimensionIsVisible()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_IS_VISIBLE, String.valueOf(v)));
    }
}
