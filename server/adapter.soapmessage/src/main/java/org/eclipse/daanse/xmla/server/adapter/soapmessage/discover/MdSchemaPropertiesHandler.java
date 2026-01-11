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
import org.eclipse.daanse.xmla.api.common.enums.PropertyContentTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyOriginEnum;
import org.eclipse.daanse.xmla.api.common.enums.PropertyTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.VisibilityEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.properties.MdSchemaPropertiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.properties.MdSchemaPropertiesRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaPropertiesHandler implements DiscoverHandler {

    private static final String CUBE_SOURCE = "CUBE_SOURCE";
    private static final String PROPERTY_NAME2 = "PROPERTY_NAME";
    private static final String PROPERTY_TYPE = "PROPERTY_TYPE";
    private static final String PROPERTY_CONTENT_TYPE = "PROPERTY_CONTENT_TYPE";
    private static final String PROPERTY_ORIGIN = "PROPERTY_ORIGIN";
    private static final String PROPERTY_VISIBILITY = "PROPERTY_VISIBILITY";

    private final DiscoverService discoverService;

    public MdSchemaPropertiesHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaPropertiesRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaPropertiesRequest request = new MdSchemaPropertiesRequestR(properties, restrictions);
        List<MdSchemaPropertiesResponseRow> rows = discoverService.mdSchemaProperties(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaPropertiesRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaPropertiesRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.DIMENSION_UNIQUE_NAME)),
                Optional.ofNullable(m.get(ROW.HIERARCHY_UNIQUE_NAME)),
                Optional.ofNullable(m.get(ROW.LEVEL_UNIQUE_NAME)), Optional.ofNullable(m.get(ROW.MEMBER_UNIQUE_NAME)),
                Optional.ofNullable(m.get(PROPERTY_NAME2)),
                Optional.ofNullable(PropertyTypeEnum.fromValue(m.get(PROPERTY_TYPE))),
                Optional.ofNullable(PropertyContentTypeEnum.fromValue(m.get(PROPERTY_CONTENT_TYPE))),
                Optional.ofNullable(PropertyOriginEnum.fromValue(m.get(PROPERTY_ORIGIN))),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
                Optional.ofNullable(VisibilityEnum.fromValue(m.get(PROPERTY_VISIBILITY))));
    }

    private void writeResponse(List<MdSchemaPropertiesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaPropertiesResponseRow row : rows) {
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
        SoapUtil.addElement(s, "LEVEL_UNIQUE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "MEMBER_UNIQUE_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "PROPERTY_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "PROPERTY_CAPTION", "xsd:string", null);
        SoapUtil.addElement(s, "PROPERTY_TYPE", "xsd:short", null);
        SoapUtil.addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        SoapUtil.addElement(s, "PROPERTY_CONTENT_TYPE", "xsd:short", "0");
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "PROPERTY_ORIGIN", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "PROPERTY_VISIBILITY", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "CHARACTER_MAXIMUM_LENGTH", "xsd:unsignedInt", "0");
        SoapUtil.addElement(s, "SQL_COLUMN_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "LANGUAGE", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "PROPERTY_ATTRIBUTE_HIERARCHY_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "PROPERTY_CARDINALITY", "xsd:string", "0");
        SoapUtil.addElement(s, "MIME_TYPE", "xsd:string", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaPropertiesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_UNIQUE_NAME, v));
        r.hierarchyUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_UNIQUE_NAME, v));
        r.levelUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_UNIQUE_NAME, v));
        r.propertyName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PROPERTY_NAME, v));
        r.propertyCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PROPERTY_CAPTION, v));
        r.propertyType()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PROPERTY_TYPE, String.valueOf(v.getValue())));
        r.dataType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATA_TYPE, String.valueOf(v.getValue())));
        r.propertyContentType().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_PROPERTY_CONTENT_TYPE, String.valueOf(v.getValue())));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.sqlColumnName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SQL_COLUMN_NAME, v));
        r.propertyOrigin()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PROPERTY_ORIGIN, String.valueOf(v.getValue())));
        r.propertyIsVisible().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_PROPERTY_VISIBILITY, String.valueOf(v.getValue())));
    }
}
