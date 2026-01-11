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
import org.eclipse.daanse.xmla.api.common.enums.TableTypeEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DbSchemaTablesInfoHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public DbSchemaTablesInfoHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DbSchemaTablesInfoRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DbSchemaTablesInfoRequest request = new DbSchemaTablesInfoRequestR(properties, restrictions);
        List<DbSchemaTablesInfoResponseRow> rows = discoverService.dbSchemaTablesInfo(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DbSchemaTablesInfoRestrictionsR parseRestrictions(SOAPElement restrictionElement) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restrictionElement);
        return new DbSchemaTablesInfoRestrictionsR(Optional.ofNullable(m.get(ROW.TABLE_CATALOG)),
                Optional.ofNullable(m.get(ROW.TABLE_SCHEMA)), m.get(ROW.TABLE_NAME),
                TableTypeEnum.fromValue(m.get(ROW.TABLE_TYPE)));
    }

    private void writeResponse(List<DbSchemaTablesInfoResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DbSchemaTablesInfoResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "TABLE_CATALOG", "xsd:string", "0");
        SoapUtil.addElement(s, "TABLE_SCHEMA", "xsd:string", "0");
        SoapUtil.addElement(s, "TABLE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "TABLE_TYPE", "xsd:string", null);
        SoapUtil.addElement(s, "TABLE_GUID", "uuid", "0");
        SoapUtil.addElement(s, "BOOKMARKS", "xsd:boolean", null);
        SoapUtil.addElement(s, "BOOKMARK_TYPE", "xsd:int", "0");
        SoapUtil.addElement(s, "BOOKMARK_DATATYPE", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "BOOKMARK_MAXIMUM_LENGTH", "xsd:unsignedInt", "0");
        SoapUtil.addElement(s, "BOOKMARK_INFORMATION", "xsd:unsignedInt", "0");
        SoapUtil.addElement(s, "TABLE_VERSION", "xsd:long", "0");
        SoapUtil.addElement(s, "CARDINALITY", "xsd:unsignedLong", null);
        SoapUtil.addElement(s, "DESCRIPTION", "xsd:string", "0");
        SoapUtil.addElement(s, "TABLE_PROPID", "xsd:unsignedInt", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DbSchemaTablesInfoResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_CATALOG, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_SCHEMA, v));
        SoapUtil.addChildElement(row, ROW.QN_TABLE_NAME, r.tableName());
        SoapUtil.addChildElement(row, ROW.QN_TABLE_TYPE, r.tableType());
        r.tableGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_GUID, String.valueOf(v)));
        r.bookmarks().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_BOOKMARKS, String.valueOf(v)));
        r.bookmarkType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_BOOKMARK_TYPE, String.valueOf(v)));
        r.bookmarkDataType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_BOOKMARK_DATATYPE, String.valueOf(v)));
        r.bookmarkMaximumLength()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_BOOKMARK_MAXIMUM_LENGTH, String.valueOf(v)));
        r.bookmarkInformation()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_BOOKMARK_INFORMATION, String.valueOf(v)));
        r.tableVersion().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_VERSION, String.valueOf(v)));
        r.cardinality().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CARDINALITY, String.valueOf(v)));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.tablePropId().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_PROP_ID, String.valueOf(v)));
    }
}
