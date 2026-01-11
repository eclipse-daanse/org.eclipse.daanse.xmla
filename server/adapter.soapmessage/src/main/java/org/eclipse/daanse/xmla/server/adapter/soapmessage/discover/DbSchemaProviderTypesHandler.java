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
import org.eclipse.daanse.xmla.api.common.enums.LevelDbTypeEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.providertypes.DbSchemaProviderTypesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.providertypes.DbSchemaProviderTypesRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DbSchemaProviderTypesHandler implements DiscoverHandler {

    private static final String DATA_TYPE = "DATA_TYPE";
    private static final String BEST_MATCH = "BEST_MATCH";

    private final DiscoverService discoverService;

    public DbSchemaProviderTypesHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DbSchemaProviderTypesRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DbSchemaProviderTypesRequest request = new DbSchemaProviderTypesRequestR(properties, restrictions);
        List<DbSchemaProviderTypesResponseRow> rows = discoverService.dbSchemaProviderTypes(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DbSchemaProviderTypesRestrictionsR parseRestrictions(SOAPElement restrictionElement) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restrictionElement);
        return new DbSchemaProviderTypesRestrictionsR(Optional.ofNullable(LevelDbTypeEnum.fromValue(m.get(DATA_TYPE))),
                Optional.ofNullable(Boolean.valueOf(m.get(BEST_MATCH))));
    }

    private void writeResponse(List<DbSchemaProviderTypesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DbSchemaProviderTypesResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "TYPE_NAME", "xsd:string", null);
        SoapUtil.addElement(s, "DATA_TYPE", "xsd:unsignedShort", null);
        SoapUtil.addElement(s, "COLUMN_SIZE", "xsd:unsignedInt", null);
        SoapUtil.addElement(s, "LITERAL_PREFIX", "xsd:string", "0");
        SoapUtil.addElement(s, "LITERAL_SUFFIX", "xsd:string", "0");
        SoapUtil.addElement(s, "IS_NULLABLE", "xsd:boolean", "0");
        SoapUtil.addElement(s, "CASE_SENSITIVE", "xsd:boolean", "0");
        SoapUtil.addElement(s, "SEARCHABLE", "xsd:unsignedInt", "0");
        SoapUtil.addElement(s, "UNSIGNED_ATTRIBUTE", "xsd:boolean", "0");
        SoapUtil.addElement(s, "FIXED_PREC_SCALE", "xsd:boolean", "0");
        SoapUtil.addElement(s, "AUTO_UNIQUE_VALUE", "xsd:boolean", "0");
        SoapUtil.addElement(s, "IS_LONG", "xsd:boolean", "0");
        SoapUtil.addElement(s, "BEST_MATCH", "xsd:boolean", "0");
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DbSchemaProviderTypesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        r.typeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TYPE_NAME, v));
        r.dataType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATA_TYPE, String.valueOf(v.getValue())));
        r.columnSize().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_COLUMN_SIZE, String.valueOf(v)));
        r.literalPrefix().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LITERAL_PREFIX, v));
        r.literalSuffix().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LITERAL_SUFFIX, v));
        r.createParams().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CREATE_PARAMS, v));
        r.isNullable().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_NULLABLE, String.valueOf(v)));
        r.caseSensitive().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_CASE_SENSITIVE, String.valueOf(v)));
        r.searchable().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_SEARCHABLE, String.valueOf(v.getValue())));
        r.unsignedAttribute()
                .ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_UNSIGNED_ATTRIBUTE, String.valueOf(v)));
        r.fixedPrecScale().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_FIXED_PREC_SCALE, String.valueOf(v)));
        r.autoUniqueValue().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_AUTO_UNIQUE_VALUE, String.valueOf(v)));
        r.localTypeName().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_LOCAL_TYPE_NAME, v));
        r.minimumScale().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MINIMUM_SCALE, String.valueOf(v)));
        r.maximumScale().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_MAXIMUM_SCALE, String.valueOf(v)));
        r.guid().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_GUID, String.valueOf(v)));
        r.typeLib().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_TYPE_LIB, v));
        r.version().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_VERSION, v));
        r.isLong().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_LONG, String.valueOf(v)));
        r.bestMatch().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_BEST_MATCH, String.valueOf(v)));
        r.isFixedLength().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_IS_FIXEDLENGTH, String.valueOf(v)));
    }
}
