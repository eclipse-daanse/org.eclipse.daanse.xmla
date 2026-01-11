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
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaLevelsHandler implements DiscoverHandler {

    private static final String DIMENSION_VISIBILITY = "DIMENSION_VISIBILITY";

    private final DiscoverService discoverService;

    public MdSchemaLevelsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaLevelsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaLevelsRequest request = new MdSchemaLevelsRequestR(properties, restrictions);
        List<MdSchemaLevelsResponseRow> rows = discoverService.mdSchemaLevels(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaLevelsRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaLevelsRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.DIMENSION_UNIQUE_NAME)),
                Optional.ofNullable(m.get(ROW.HIERARCHY_UNIQUE_NAME)), Optional.ofNullable(m.get(ROW.LEVEL_NAME)),
                Optional.ofNullable(m.get(ROW.LEVEL_UNIQUE_NAME)), Optional.empty(), Optional.empty(),
                Optional.ofNullable(VisibilityEnum.fromValue(m.get(DIMENSION_VISIBILITY))));
    }

    private void writeResponse(List<MdSchemaLevelsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaLevelsResponseRow row : rows) {
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
        SoapUtil.addElement(s, "DIMENSION_UNIQUE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "LEVEL_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "LEVEL_UNIQUE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "LEVEL_GUID", "uuid", "0");
        SoapUtil.addElement(s, "LEVEL_CAPTION", "xsd:string", null);
        SoapUtil.addElement(s, "LEVEL_NUMBER", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "LEVEL_CARDINALITY", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "LEVEL_TYPE", "xsd:int", null);
        SoapUtil.addElement(s, "CUSTOM_ROLLUP_SETTINGS", "xsd:int", null);
        SoapUtil.addElement(s, "LEVEL_UNIQUE_SETTINGS", "xsd:int", null);
        SoapUtil.addElement(s, "LEVEL_IS_VISIBLE", "xsd:boolean", null);
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "LEVEL_ORIGIN", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "LEVEL_VISIBILITY", "xsd:unsignedShort", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaLevelsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_UNIQUE_NAME, v));
        r.hierarchyUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_UNIQUE_NAME, v));
        r.levelName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_NAME, v));
        r.levelUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_UNIQUE_NAME, v));
        r.levelGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_GUID, String.valueOf(v)));
        r.levelCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_CAPTION, v));
        r.levelNumber().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_NUMBER, String.valueOf(v)));
        r.levelCardinality().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_CARDINALITY, String.valueOf(v)));
        r.levelType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_TYPE, String.valueOf(v.getValue())));
        r.customRollupSetting().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_CUSTOM_ROLLUP_SETTINGS, String.valueOf(v.getValue())));
        r.levelUniqueSettings().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_UNIQUE_SETTINGS, String.valueOf(v.getValue())));
        r.levelIsVisible().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_IS_VISIBLE, String.valueOf(v)));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.levelOrigin()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_ORIGIN, String.valueOf(v.getValue())));
    }
}
