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
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DbSchemaSourceTablesHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public DbSchemaSourceTablesHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DbSchemaSourceTablesRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DbSchemaSourceTablesRequest request = new DbSchemaSourceTablesRequestR(properties, restrictions);
        List<DbSchemaSourceTablesResponseRow> rows = discoverService.dbSchemaSourceTables(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DbSchemaSourceTablesRestrictionsR parseRestrictions(SOAPElement restrictionElement) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restrictionElement);
        return new DbSchemaSourceTablesRestrictionsR(Optional.ofNullable(m.get(ROW.TABLE_CATALOG)),
                Optional.ofNullable(m.get(ROW.TABLE_SCHEMA)), m.get(ROW.TABLE_NAME),
                TableTypeEnum.fromValue(m.get(ROW.TABLE_TYPE)));
    }

    private void writeResponse(List<DbSchemaSourceTablesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DbSchemaSourceTablesResponseRow row : rows) {
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
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DbSchemaSourceTablesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_CATALOG, v));
        r.schemaName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_SCHEMA, v));
        SoapUtil.addChildElement(row, ROW.QN_TABLE_NAME, r.tableName());
        SoapUtil.addChildElement(row, ROW.QN_TABLE_TYPE, r.tableType().getValue());
    }
}
