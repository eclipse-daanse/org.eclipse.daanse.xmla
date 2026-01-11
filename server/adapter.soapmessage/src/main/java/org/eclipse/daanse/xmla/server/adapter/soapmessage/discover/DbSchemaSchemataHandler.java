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

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DbSchemaSchemataHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public DbSchemaSchemataHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DbSchemaSchemataRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DbSchemaSchemataRequest request = new DbSchemaSchemataRequestR(properties, restrictions);
        List<DbSchemaSchemataResponseRow> rows = discoverService.dbSchemaSchemata(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DbSchemaSchemataRestrictionsR parseRestrictions(SOAPElement restrictionElement) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restrictionElement);
        return new DbSchemaSchemataRestrictionsR(m.get(ROW.CATALOG_NAME), m.get(ROW.SCHEMA_NAME),
                m.get(ROW.SCHEMA_OWNER));
    }

    private void writeResponse(List<DbSchemaSchemataResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DbSchemaSchemataResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "CATALOG_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "SCHEMA_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "SCHEMA_OWNER", "xsd:string", null);
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DbSchemaSchemataResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, r.catalogName());
        SoapUtil.addChildElement(row, ROW.QN_SCHEMA_NAME, r.schemaName());
        SoapUtil.addChildElement(row, ROW.QN_SCHEMA_OWNER, r.schemaOwner());
    }
}
