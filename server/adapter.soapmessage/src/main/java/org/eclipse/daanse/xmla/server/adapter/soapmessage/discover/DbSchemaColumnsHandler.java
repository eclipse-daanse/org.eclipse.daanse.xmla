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
import org.eclipse.daanse.xmla.api.common.enums.ColumnOlapTypeEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DbSchemaColumnsHandler implements DiscoverHandler {

    private static final String TABLE_CATALOG = "TABLE_CATALOG";
    private static final String TABLE_SCHEMA = "TABLE_SCHEMA";
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String COLUMN_NAME = "COLUMN_NAME";
    private static final String COLUMN_OLAP_TYPE = "COLUMN_OLAP_TYPE";

    private final DiscoverService discoverService;

    public DbSchemaColumnsHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DbSchemaColumnsRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DbSchemaColumnsRequest request = new DbSchemaColumnsRequestR(properties, restrictions);
        List<DbSchemaColumnsResponseRow> rows = discoverService.dbSchemaColumns(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DbSchemaColumnsRestrictionsR parseRestrictions(SOAPElement restrictionElement) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restrictionElement);
        return new DbSchemaColumnsRestrictionsR(Optional.ofNullable(m.get(TABLE_CATALOG)),
                Optional.ofNullable(m.get(TABLE_SCHEMA)), Optional.ofNullable(m.get(TABLE_NAME)),
                Optional.ofNullable(m.get(COLUMN_NAME)),
                Optional.ofNullable(ColumnOlapTypeEnum.fromValue(m.get(COLUMN_OLAP_TYPE))));
    }

    private void writeResponse(List<DbSchemaColumnsResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DbSchemaColumnsResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "TABLE_CATALOG", "xsd:string", null);
        SoapUtil.addElement(s, "TABLE_SCHEMA", "xsd:string", "0");
        SoapUtil.addElement(s, "TABLE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "COLUMN_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "ORDINAL_POSITION", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "COLUMN_HAS_DEFAULT", "xsd:boolean", "0");
        SoapUtil.addElement(s, "COLUMN_FLAGS", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "IS_NULLABLE", "xsd:boolean", null);
        SoapUtil.addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        SoapUtil.addElement(s, "CHARACTER_MAXIMUM_LENGTH", "xsd:unsignedInt", "0");
        SoapUtil.addElement(s, "CHARACTER_OCTET_LENGTH", "xsd:unsignedInt", "0");
        SoapUtil.addElement(s, "NUMERIC_PRECISION", "xsd:unsignedShort", "0");
        SoapUtil.addElement(s, "NUMERIC_SCALE", "xsd:short", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DbSchemaColumnsResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.tableCatalog().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_CATALOG, v));
        r.tableSchema().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_SCHEMA, v));
        r.tableName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TABLE_NAME, v));
        r.columnName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLUMN_NAME, v));
        r.columnGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLUMN_GUID, String.valueOf(v)));
        r.columnPropId().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLUMN_PROPID, String.valueOf(v)));
        r.ordinalPosition().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_ORDINAL_POSITION, String.valueOf(v)));
        r.columnHasDefault()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLUMN_HAS_DEFAULT, String.valueOf(v)));
        r.columnDefault().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLUMN_DEFAULT, v));
        r.columnFlags().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLUMN_FLAG, String.valueOf(v.getValue())));
        r.isNullable().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_NULLABLE, String.valueOf(v)));
        r.dataType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATA_TYPE, String.valueOf(v)));
        r.typeGuid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TYPE_GUID, String.valueOf(v)));
        r.characterMaximum()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CHARACTER_MAXIMUM_LENGTH, String.valueOf(v)));
        r.characterOctetLength()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CHARACTER_OCTET_LENGTH, String.valueOf(v)));
        r.numericPrecision().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_NUMERIC_PRECISION, String.valueOf(v)));
        r.numericScale().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_NUMERIC_SCALE, String.valueOf(v)));
        r.dateTimePrecision()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATETIME_PRECISION, String.valueOf(v)));
        r.characterSetCatalog().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CHARACTER_SET_CATALOG, v));
        r.characterSetSchema().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CHARACTER_SET_SCHEMA, v));
        r.characterSetName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CHARACTER_SET_NAME, v));
        r.collationCatalog().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLLATION_CATALOG, v));
        r.collationSchema().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLLATION_SCHEMA, v));
        r.collationName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLLATION_NAME, v));
        r.domainCatalog().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DOMAIN_CATALOG, v));
        r.domainSchema().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DOMAIN_SCHEMA, v));
        r.domainName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DOMAIN_NAME, v));
        r.description().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DESCRIPTION, v));
        r.columnOlapType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLUMN_OLAP_TYPE, v.name()));
    }
}
