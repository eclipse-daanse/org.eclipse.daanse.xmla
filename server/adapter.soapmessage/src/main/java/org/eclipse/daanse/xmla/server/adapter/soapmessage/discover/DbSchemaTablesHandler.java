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

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DbSchemaTablesHandler implements DiscoverHandler {

    private static final String TABLE_CATALOG = "TABLE_CATALOG";
    private static final String TABLE_SCHEMA = "TABLE_SCHEMA";
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String TABLE_TYPE = "TABLE_TYPE";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private final DiscoverService discoverService;

    public DbSchemaTablesHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DbSchemaTablesRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DbSchemaTablesRequest request = new DbSchemaTablesRequestR(properties, restrictions);
        List<DbSchemaTablesResponseRow> rows = discoverService.dbSchemaTables(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DbSchemaTablesRestrictionsR parseRestrictions(SOAPElement restrictionElement) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restrictionElement);
        return new DbSchemaTablesRestrictionsR(Optional.ofNullable(m.get(TABLE_CATALOG)),
                Optional.ofNullable(m.get(TABLE_SCHEMA)), Optional.ofNullable(m.get(TABLE_NAME)),
                Optional.ofNullable(m.get(TABLE_TYPE)));
    }

    private void writeResponse(List<DbSchemaTablesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DbSchemaTablesResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);

        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SOAPElement se1 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se1.setAttribute("sql:field", "TABLE_CATALOG");
        se1.setAttribute("name", "TABLE_CATALOG");
        se1.setAttribute("type", "xsd:string");

        SOAPElement se2 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se2.setAttribute("sql:field", "TABLE_SCHEMA");
        se2.setAttribute("name", "TABLE_SCHEMA");
        se2.setAttribute("type", "xsd:string");
        se2.setAttribute("minOccurs", "0");

        SOAPElement se3 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se3.setAttribute("sql:field", "TABLE_NAME");
        se3.setAttribute("name", "TABLE_NAME");
        se3.setAttribute("type", "xsd:string");

        SOAPElement se4 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se4.setAttribute("sql:field", "TABLE_TYPE");
        se4.setAttribute("name", "TABLE_TYPE");
        se4.setAttribute("type", "xsd:string");

        SOAPElement se5 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se5.setAttribute("sql:field", "TABLE_GUID");
        se5.setAttribute("name", "TABLE_GUID");
        se5.setAttribute("type", "uuid");
        se5.setAttribute("minOccurs", "0");

        SOAPElement se6 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se6.setAttribute("sql:field", "DESCRIPTION");
        se6.setAttribute("name", "DESCRIPTION");
        se6.setAttribute("type", "xsd:string");
        se6.setAttribute("minOccurs", "0");

        SOAPElement se7 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se7.setAttribute("sql:field", "TABLE_PROPID");
        se7.setAttribute("name", "TABLE_PROPID");
        se7.setAttribute("type", "xsd:unsignedInt");
        se7.setAttribute("minOccurs", "0");

        SOAPElement se8 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se8.setAttribute("sql:field", "DATE_CREATED");
        se8.setAttribute("name", "DATE_CREATED");
        se8.setAttribute("type", "xsd:dateTime");
        se8.setAttribute("minOccurs", "0");

        SOAPElement se9 = SoapUtil.addChildElement(s, Constants.XSD.QN_ELEMENT);
        se9.setAttribute("sql:field", "DATE_MODIFIED");
        se9.setAttribute("name", "DATE_MODIFIED");
        se9.setAttribute("type", "xsd:dateTime");
        se9.setAttribute("minOccurs", "0");

        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DbSchemaTablesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.tableCatalog().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_CATALOG, v));
        r.tableSchema().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_SCHEMA, v));
        r.tableName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_NAME, v));
        r.tableType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_TYPE, v));
        r.tableGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_GUID, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.tablePropId().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_PROP_ID, String.valueOf(v)));
        r.dateCreated().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATE_CREATED, v.format(formatter)));
        r.dateModified().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATE_MODIFIED, v.format(formatter)));
    }
}
