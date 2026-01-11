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
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies.MdSchemaHierarchiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies.MdSchemaHierarchiesRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaHierarchiesHandler implements DiscoverHandler {

    private static final String CUBE_SOURCE = "CUBE_SOURCE";
    private static final String HIERARCHY_VISIBILITY = "HIERARCHY_VISIBILITY";

    private final DiscoverService discoverService;

    public MdSchemaHierarchiesHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaHierarchiesRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaHierarchiesRequest request = new MdSchemaHierarchiesRequestR(properties, restrictions);
        List<MdSchemaHierarchiesResponseRow> rows = discoverService.mdSchemaHierarchies(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaHierarchiesRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaHierarchiesRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.DIMENSION_UNIQUE_NAME)), Optional.ofNullable(m.get(ROW.HIERARCHY_NAME)),
                Optional.ofNullable(m.get(ROW.HIERARCHY_UNIQUE_NAME)),
                Optional.ofNullable(toInt(m.get(ROW.HIERARCHY_ORIGIN))),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
                Optional.ofNullable(VisibilityEnum.fromValue(m.get(HIERARCHY_VISIBILITY))));
    }

    private void writeResponse(List<MdSchemaHierarchiesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaHierarchiesResponseRow row : rows) {
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
        SoapUtil.addElement(s, "HIERARCHY_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "HIERARCHY_UNIQUE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "HIERARCHY_GUID", "uuid", "0");
        SoapUtil.addElement(s, "HIERARCHY_CAPTION", "xsd:string", null);
        SoapUtil.addElement(s, "DIMENSION_TYPE", "xsd:short", null);
        SoapUtil.addElement(s, "HIERARCHY_CARDINALITY", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "DEFAULT_MEMBER", "xsd:string", "0");
        SoapUtil.addElement(s, "ALL_MEMBER", "xsd:string", "0");
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "STRUCTURE", "xsd:short", null);
        SoapUtil.addElement(s, "IS_VIRTUAL", "xsd:boolean", null);
        SoapUtil.addElement(s, "IS_READWRITE", "xsd:boolean", null);
        SoapUtil.addElement(s, "DIMENSION_UNIQUE_SETTINGS", "xsd:int", null);
        SoapUtil.addElement(s, "DIMENSION_IS_VISIBLE", "xsd:boolean", null);
        SoapUtil.addElement(s, "HIERARCHY_ORDINAL", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "DIMENSION_IS_SHARED", "xsd:boolean", null);
        SoapUtil.addElement(s, "HIERARCHY_IS_VISIBLE", "xsd:boolean", null);
        SoapUtil.addElement(s, "HIERARCHY_ORIGIN", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "HIERARCHY_DISPLAY_FOLDER", "xsd:string", "0");
        SoapUtil.addElement(s, "CUBE_SOURCE", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "HIERARCHY_VISIBILITY", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "PARENT_CHILD", "xsd:boolean", "0");
        SoapUtil.addElement(s, "LEVELS", null, "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaHierarchiesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_UNIQUE_NAME, v));
        r.hierarchyName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_NAME, v));
        r.hierarchyUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_UNIQUE_NAME, v));
        r.hierarchyGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_GUID, String.valueOf(v)));
        r.hierarchyCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_CAPTION, v));
        r.dimensionType()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_TYPE, String.valueOf(v.getValue())));
        r.hierarchyCardinality()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_CARDINALITY, String.valueOf(v)));
        r.defaultMember().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DEFAULT_MEMBER, v));
        r.allMember().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ALL_MEMBER, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.structure().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_STRUCTURE, String.valueOf(v.getValue())));
        r.isVirtual().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_VIRTUAL, String.valueOf(v)));
        r.isReadWrite().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_READWRITE, String.valueOf(v)));
        r.dimensionUniqueSettings().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_UNIQUE_SETTINGS, String.valueOf(v.getValue())));
        r.dimensionMasterUniqueName()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_MASTER_UNIQUE_NAME, v));
        r.dimensionIsVisible()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_IS_VISIBLE, String.valueOf(v)));
        r.hierarchyOrdinal().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_ORDINAL, String.valueOf(v)));
        r.dimensionIsShared()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_IS_SHARED, String.valueOf(v)));
        r.hierarchyIsVisible()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_IS_VISIBLE, String.valueOf(v)));
        r.hierarchyOrigin()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_ORIGIN, String.valueOf(v.getValue())));
        r.hierarchyDisplayFolder().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_DISPLAY_FOLDER, v));
        r.instanceSelection()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_INSTANCE_SELECTION, String.valueOf(v.getValue())));
        r.groupingBehavior()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_GROUPING_BEHAVIOR, String.valueOf(v.getValue())));
        r.structureType()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_STRUCTURE_TYPE, String.valueOf(v.getValue())));
    }

    private static Integer toInt(String s) {
        if (s == null) {
            return null;
        }
        return Integer.valueOf(s);
    }
}
