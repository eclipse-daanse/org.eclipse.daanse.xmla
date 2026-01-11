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

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.common.enums.CubeSourceEnum;
import org.eclipse.daanse.xmla.api.common.enums.CubeTypeEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class MdSchemaCubesHandler implements DiscoverHandler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final DiscoverService discoverService;

    public MdSchemaCubesHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        MdSchemaCubesRestrictionsR restrictions = parseRestrictions(restrictionElement);
        MdSchemaCubesRequest request = new MdSchemaCubesRequestR(properties, restrictions);
        List<MdSchemaCubesResponseRow> rows = discoverService.mdSchemaCubes(request, metaData);
        writeResponse(rows, responseBody);
    }

    private MdSchemaCubesRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new MdSchemaCubesRestrictionsR(m.get(ROW.CATALOG_NAME), Optional.ofNullable(m.get(ROW.SCHEMA_NAME)),
                Optional.ofNullable(m.get(ROW.CUBE_NAME)),
                Optional.ofNullable(CubeTypeEnum.fromValue(m.get(ROW.CUBE_TYPE))),
                Optional.ofNullable(m.get(ROW.BASE_CUBE_NAME)),
                Optional.ofNullable(CubeSourceEnum.fromValue(m.get(ROW.CUBE_SOURCE))));
    }

    private void writeResponse(List<MdSchemaCubesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (MdSchemaCubesResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);

        SOAPElement se1 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se1.setAttribute("sql:field", "CATALOG_NAME");
        se1.setAttribute("name", "CATALOG_NAME");
        se1.setAttribute("type", "xsd:string");
        se1.setAttribute("minOccurs", "0");

        SOAPElement se2 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se2.setAttribute("sql:field", "SCHEMA_NAME");
        se2.setAttribute("name", "SCHEMA_NAME");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se3.setAttribute("sql:field", "CUBE_NAME");
        se3.setAttribute("name", "CUBE_NAME");
        se3.setAttribute("type", "xsd:string");

        SOAPElement se4 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se4.setAttribute("sql:field", "CUBE_TYPE");
        se4.setAttribute("name", "CUBE_TYPE");
        se4.setAttribute("type", "xsd:string");

        SOAPElement se5 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se5.setAttribute("sql:field", "CUBE_GUID");
        se5.setAttribute("name", "CUBE_GUID");
        se5.setAttribute("type", "uuid");
        se5.setAttribute("minOccurs", "0");

        SOAPElement se6 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se6.setAttribute("sql:field", "CREATED_ON");
        se6.setAttribute("name", "CREATED_ON");
        se6.setAttribute("type", "xsd:dateTime");
        se6.setAttribute("minOccurs", "0");

        SOAPElement se7 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se7.setAttribute("sql:field", "LAST_SCHEMA_UPDATE");
        se7.setAttribute("name", "LAST_SCHEMA_UPDATE");
        se7.setAttribute("type", "xsd:dateTime");
        se7.setAttribute("minOccurs", "0");

        SOAPElement se8 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se8.setAttribute("sql:field", "SCHEMA_UPDATED_BY");
        se8.setAttribute("name", "SCHEMA_UPDATED_BY");
        se8.setAttribute("type", "xsd:string");
        se8.setAttribute("minOccurs", "0");

        SOAPElement se9 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se9.setAttribute("sql:field", "LAST_DATA_UPDATE");
        se9.setAttribute("name", "LAST_DATA_UPDATE");
        se9.setAttribute("type", "xsd:dateTime");
        se9.setAttribute("minOccurs", "0");

        SOAPElement se10 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se10.setAttribute("sql:field", "DATA_UPDATED_BY");
        se10.setAttribute("name", "DATA_UPDATED_BY");
        se10.setAttribute("type", "xsd:string");
        se10.setAttribute("minOccurs", "0");

        SOAPElement se11 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se11.setAttribute("sql:field", "DESCRIPTION");
        se11.setAttribute("name", "DESCRIPTION");
        se11.setAttribute("type", "xsd:string");
        se11.setAttribute("minOccurs", "0");

        SOAPElement se12 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se12.setAttribute("sql:field", "IS_DRILLTHROUGH_ENABLED");
        se12.setAttribute("name", "IS_DRILLTHROUGH_ENABLED");
        se12.setAttribute("type", "xsd:boolean");

        SOAPElement se13 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se13.setAttribute("sql:field", "IS_LINKABLE");
        se13.setAttribute("name", "IS_LINKABLE");
        se13.setAttribute("type", "xsd:boolean");

        SOAPElement se14 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se14.setAttribute("sql:field", "IS_WRITE_ENABLED");
        se14.setAttribute("name", "IS_WRITE_ENABLED");
        se14.setAttribute("type", "xsd:boolean");

        SOAPElement se15 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se15.setAttribute("sql:field", "IS_SQL_ENABLED");
        se15.setAttribute("name", "IS_SQL_ENABLED");
        se15.setAttribute("type", "xsd:boolean");

        SOAPElement se16 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se16.setAttribute("sql:field", "CUBE_CAPTION");
        se16.setAttribute("name", "CUBE_CAPTION");
        se16.setAttribute("type", "xsd:string");
        se16.setAttribute("minOccurs", "0");

        SOAPElement se17 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se17.setAttribute("sql:field", "BASE_CUBE_NAME");
        se17.setAttribute("name", "BASE_CUBE_NAME");
        se17.setAttribute("type", "xsd:string");
        se17.setAttribute("minOccurs", "0");

        SOAPElement se18 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se18.setAttribute("sql:field", "DIMENSIONS");
        se18.setAttribute("name", "DIMENSIONS");
        se18.setAttribute("minOccurs", "0");

        SOAPElement se19 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se19.setAttribute("sql:field", "SETS");
        se19.setAttribute("name", "SETS");
        se19.setAttribute("minOccurs", "0");

        SOAPElement se20 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se20.setAttribute("sql:field", "MEASURES");
        se20.setAttribute("name", "MEASURES");
        se20.setAttribute("minOccurs", "0");

        SOAPElement se21 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se21.setAttribute("sql:field", "CUBE_SOURCE");
        se21.setAttribute("name", "CUBE_SOURCE");
        se21.setAttribute("type", "xsd:int");
        se21.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private void addResponseRow(SOAPElement root, MdSchemaCubesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, r.catalogName());
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, v));
        r.cubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_NAME, v));

        r.cubeType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_TYPE, v.name()));
        r.cubeGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_GUID, String.valueOf(v)));
        r.createdOn().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CREATED_ON, v.format(formatter)));
        r.lastSchemaUpdate()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LAST_SCHEMA_UPDATE, v.format(formatter)));
        r.schemaUpdatedBy().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SCHEMA_UPDATED_BY, v));
        r.lastDataUpdate().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LAST_DATA_UPDATE, v.format(formatter)));
        r.dataUpdateDBy().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATA_UPDATED_BY, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.isDrillThroughEnabled()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_DRILLTHROUGH_ENABLED, String.valueOf(v)));
        r.isLinkable().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_LINKABLE, String.valueOf(v)));
        r.isWriteEnabled().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_WRITE_ENABLED, String.valueOf(v)));
        r.isSqlEnabled().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_SQL_ENABLED, String.valueOf(v)));
        r.cubeCaption().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_CAPTION, v));
        r.baseCubeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_BASE_CUBE_NAME, v));
        r.cubeSource().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CUBE_SOURCE, String.valueOf(v.getValue())));
        r.preferredQueryPatterns().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_PREFERRED_QUERY_PATTERNS, String.valueOf(v.getValue())));
    }
}
