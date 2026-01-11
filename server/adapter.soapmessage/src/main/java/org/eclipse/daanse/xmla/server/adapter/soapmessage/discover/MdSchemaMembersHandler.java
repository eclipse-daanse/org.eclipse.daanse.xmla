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
import org.eclipse.daanse.xmla.api.common.enums.MemberTypeEnum;
import org.eclipse.daanse.xmla.api.common.enums.TreeOpEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.members.MdSchemaMembersRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.members.MdSchemaMembersRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaMembersHandler implements DiscoverHandler {

    private static final String CUBE_SOURCE = "CUBE_SOURCE";
    private static final String TREE_OP = "TREE_OP";

    private final DiscoverService discoverService;

    public MdSchemaMembersHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaMembersRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaMembersRequest request = new MdSchemaMembersRequestR(properties, restrictions);
        List<MdSchemaMembersResponseRow> rows = discoverService.mdSchemaMembers(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaMembersRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaMembersRestrictionsR(Optional.ofNullable(m.get(ROW.CATALOG_NAME)),
                Optional.ofNullable(m.get(ROW.SCHEMA_NAME)), Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(m.get(ROW.DIMENSION_UNIQUE_NAME)),
                Optional.ofNullable(m.get(ROW.HIERARCHY_UNIQUE_NAME)),
                Optional.ofNullable(m.get(ROW.LEVEL_UNIQUE_NAME)), Optional.ofNullable(toInt(m.get(ROW.LEVEL_NUMBER))),
                Optional.ofNullable(m.get(ROW.MEMBER_NAME)), Optional.ofNullable(m.get(ROW.MEMBER_UNIQUE_NAME)),
                Optional.ofNullable(MemberTypeEnum.fromValue(m.get(ROW.MEMBER_TYPE))),
                Optional.ofNullable(m.get(ROW.MEMBER_CAPTION)),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(CUBE_SOURCE))),
                Optional.ofNullable(TreeOpEnum.fromValue(m.get(TREE_OP))));
    }

    private void writeResponse(List<MdSchemaMembersResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaMembersResponseRow row : rows) {
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
        SoapUtil.addElement(s, "LEVEL_NUMBER", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "MEMBER_ORDINAL", "xsd:unsignedInt", "0");
        SoapUtil.addElement(s, "MEMBER_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "MEMBER_UNIQUE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "MEMBER_TYPE", "xsd:int", null);
        SoapUtil.addElement(s, "MEMBER_GUID", "uuid", "0");
        SoapUtil.addElement(s, "MEMBER_CAPTION", "xsd:string", null);
        SoapUtil.addElement(s, "CHILDREN_CARDINALITY", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "PARENT_LEVEL", "xsd:unsignedInt", "0");
        SoapUtil.addElement(s, "PARENT_UNIQUE_NAME", "xsd:string", "0");
        SoapUtil.addElement(s, "PARENT_COUNT", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "TREE_OP", "xsd:int", "0");
        SoapUtil.addElement(s, "DEPTH", "xsd:int", "0");
        SoapUtil.addElement(s, "MEMBER_KEY", "xsd:string", "0");
        SoapUtil.addElement(s, "IS_PLACEHOLDERMEMBER", "xsd:boolean", "0");
        SoapUtil.addElement(s, "IS_DATAMEMBER", "xsd:boolean", "0");
        SoapUtil.addElement(s, "SCOPE", "xsd:int", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaMembersResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);

        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));
        r.dimensionUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DIMENSION_UNIQUE_NAME, v));
        r.hierarchyUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_HIERARCHY_UNIQUE_NAME, v));
        r.levelUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_UNIQUE_NAME, v));
        r.levelNumber().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LEVEL_NUMBER, String.valueOf(v)));
        r.memberOrdinal().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEMBER_ORDINAL, String.valueOf(v)));
        r.memberName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEMBER_NAME, v));
        r.memberUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEMBER_UNIQUE_NAME, v));
        r.memberType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEMBER_TYPE, String.valueOf(v.getValue())));
        r.memberGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEMBER_GUID, String.valueOf(v)));
        r.memberCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEMBER_CAPTION, v));
        r.childrenCardinality()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CHILDREN_CARDINALITY, String.valueOf(v)));
        r.parentLevel().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PARENT_LEVEL, String.valueOf(v)));
        r.parentUniqueName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PARENT_UNIQUE_NAME, v));
        r.parentCount().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PARENT_COUNT, String.valueOf(v)));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.memberKey().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MEMBER_KEY, v));
        r.isPlaceHolderMember()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_PLACEHOLDERMEMBER, String.valueOf(v)));
        r.isDataMember().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_DATAMEMBER, String.valueOf(v)));
        r.scope().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCOPE, String.valueOf(v.getValue())));
    }

    private static Integer toInt(String s) {
        if (s == null) {
            return null;
        }
        return Integer.valueOf(s);
    }
}
