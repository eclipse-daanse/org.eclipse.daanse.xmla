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
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs.DbSchemaCatalogsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs.DbSchemaCatalogsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DbSchemaCatalogsHandler implements DiscoverHandler {

    private static final String CATALOG_NAME = "CATALOG_NAME";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private final DiscoverService discoverService;

    public DbSchemaCatalogsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DbSchemaCatalogsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DbSchemaCatalogsRequest request = new DbSchemaCatalogsRequestR(properties, restrictions);
        List<DbSchemaCatalogsResponseRow> rows = discoverService.dbSchemaCatalogs(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DbSchemaCatalogsRestrictionsR parseRestrictions(SOAPElement restrictionElement) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restrictionElement);
        return new DbSchemaCatalogsRestrictionsR(Optional.ofNullable(m.get(CATALOG_NAME)));
    }

    private void writeResponse(List<DbSchemaCatalogsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DbSchemaCatalogsResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement ct1 = SoapUtil.addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        ct1.setAttribute("name", "row");
        SOAPElement s1 = SoapUtil.addChildElement(ct1, Constants.XSD.QN_SEQUENCE);
        SOAPElement s1e1 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e1.setAttribute("sql:field", "CATALOG_NAME");
        s1e1.setAttribute("name", "CATALOG_NAME");
        s1e1.setAttribute("type", "xsd:string");

        SOAPElement s1e2 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e2.setAttribute("sql:field", "DESCRIPTION");
        s1e2.setAttribute("name", "DESCRIPTION");
        s1e2.setAttribute("type", "xsd:string");
        s1e2.setAttribute("minOccurs", "0");

        SOAPElement s1e3 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e3.setAttribute("sql:field", "ROLES");
        s1e3.setAttribute("name", "ROLES");
        s1e3.setAttribute("type", "xsd:string");

        SOAPElement s1e4 = SoapUtil.addChildElement(s1, Constants.XSD.QN_ELEMENT);
        s1e4.setAttribute("sql:field", "DATE_MODIFIED");
        s1e4.setAttribute("name", "DATE_MODIFIED");
        s1e4.setAttribute("type", "xsd:dateTime");
        s1e4.setAttribute("minOccurs", "0");
        return seRoot;
    }

    @SuppressWarnings("deprecation")
    private void addResponseRow(SOAPElement root, DbSchemaCatalogsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.catalogName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CATALOG_NAME, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.roles().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ROLES, v));
        r.dateModified().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATE_MODIFIED, v.format(formatter)));
        r.compatibilityLevel()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COMPATIBILITY_LEVEL, String.valueOf(v)));
        r.type().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TYPE, String.valueOf(v.getValue())));
        r.version().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_VERSION, String.valueOf(v)));
        r.databaseId().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATABASE_ID, v));
        r.dateQueried().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATE_QUERIED, v.format(formatter)));
        r.currentlyUsed().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CURRENTLY_USED, String.valueOf(v)));
        r.popularity().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_POPULARITY, String.valueOf(v)));
        r.weightedPopularity()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_WEIGHTEDPOPULARITY, String.valueOf(v)));
        r.clientCacheRefreshPolicy().ifPresent(
                v -> SoapUtil.addChildElement(row, ROW.QN_CLIENTCACHEREFRESHPOLICY, String.valueOf(v.getValue())));
    }
}
