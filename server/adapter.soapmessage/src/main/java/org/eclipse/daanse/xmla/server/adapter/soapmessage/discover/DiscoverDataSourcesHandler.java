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
import org.eclipse.daanse.xmla.api.common.enums.AuthenticationModeEnum;
import org.eclipse.daanse.xmla.api.common.enums.ProviderTypeEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRequest;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.discover.datasources.DiscoverDataSourcesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.datasources.DiscoverDataSourcesRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DiscoverDataSourcesHandler implements DiscoverHandler {

    private final DiscoverService discoverService;

    public DiscoverDataSourcesHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DiscoverDataSourcesRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DiscoverDataSourcesRequest request = new DiscoverDataSourcesRequestR(properties, restrictions);
        List<DiscoverDataSourcesResponseRow> rows = discoverService.dataSources(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DiscoverDataSourcesRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new DiscoverDataSourcesRestrictionsR(m.get(ROW.DATA_SOURCE_NAME),
                Optional.ofNullable(m.get(ROW.DATA_SOURCE_DESCRIPTION)), Optional.ofNullable(m.get(ROW.URL)),
                Optional.ofNullable(m.get(ROW.DATA_SOURCE_INFO)), m.get(ROW.PROVIDER_NAME),
                Optional.ofNullable(ProviderTypeEnum.fromValue(m.get(ROW.PROVIDER_TYPE))),
                Optional.ofNullable(AuthenticationModeEnum.fromValue(m.get(ROW.AUTHENTICATION_MODE))));
    }

    private void writeResponse(List<DiscoverDataSourcesResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DiscoverDataSourcesResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "DataSourceName", "xsd:string", null);
        SoapUtil.addElement(s, "DataSourceDescription", "xsd:string", "0");
        SoapUtil.addElement(s, "URL", "xsd:string", "0");
        SoapUtil.addElement(s, "DataSourceInfo", "xsd:string", "0");
        SoapUtil.addElement(s, "ProviderName", "xsd:string", "0");
        SoapUtil.addElement(s, "ProviderType", "xsd:string", "0");
        SoapUtil.addElement(s, "AuthenticationMode", "xsd:string", null);
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DiscoverDataSourcesResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        SoapUtil.addChildElement(row, ROW.QN_DATA_SOURCE_NAME, r.dataSourceName());
        r.dataSourceDescription().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATA_SOURCE_DESCRIPTION, v));
        r.url().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_URL, v));
        r.dataSourceInfo().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_DATA_SOURCE_INFO, v));
        SoapUtil.addChildElement(row, ROW.QN_PROVIDER_NAME, r.providerName());
        r.providerType().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_PROVIDER_TYPE, v.name()));
        r.authenticationMode().ifPresent(v -> SoapUtil.addChildElement(row, ROW.QN_AUTHENTICATION_MODE, v.getValue()));
    }
}
