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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.common.enums.ObjectExpansionEnum;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataRequest;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.discover.xmlmetadata.DiscoverXmlMetaDataRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.xmlmetadata.DiscoverXmlMetaDataRestrictionsR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaSoapException;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROW;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverConstants.ROWSET;
import org.xml.sax.SAXException;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DiscoverXmlMetaDataHandler implements DiscoverHandler {

    private static final String OBJECT_TYPE = "ObjectType";
    private static final String DATABASE_ID = "DatabaseID";
    private static final String DIMENSION_ID = "DimensionID";
    private static final String CUBE_ID = "CubeID";
    private static final String MEASURE_GROUP_ID = "MeasureGroupID";
    private static final String PARTITION_ID = "PartitionID";
    private static final String PERSPECTIVE_ID = "PerspectiveID";
    private static final String DIMENSION_PERMISSION_ID = "DimensionPermissionID";
    private static final String ROLE_ID = "RoleID";
    private static final String DATABASE_PERMISSION_ID = "DatabasePermissionID";
    private static final String MINING_MODEL_ID = "MiningModelID";
    private static final String MINING_MODEL_PERMISSION_ID = "MiningModelPermissionID";
    private static final String DATA_SOURCE_ID = "DataSourceID";
    private static final String MINING_STRUCTURE_ID = "MiningStructureID";
    private static final String AGGREGATION_DESIGN_ID = "AggregationDesignID";
    private static final String TRACE_ID = "TraceID";
    private static final String MINING_STRUCTURE_PERMISSION_ID = "MiningStructurePermissionID";
    private static final String CUBE_PERMISSION_ID = "CubePermissionID";
    private static final String ASSEMBLY_ID = "AssemblyID";
    private static final String MDX_SCRIPT_ID = "MdxScriptID";
    private static final String DATA_SOURCE_VIEW_ID = "DataSourceViewID";
    private static final String DATA_SOURCE_PERMISSION_ID = "DataSourcePermissionID";
    private static final String OBJECT_EXPANSION = "ObjectExpansion";

    private final DiscoverService discoverService;

    public DiscoverXmlMetaDataHandler(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @Override
    public void handle(RequestMetaData metaData, PropertiesR properties, SOAPElement restrictionElement,
            SOAPBody responseBody) throws SOAPException {
        DiscoverXmlMetaDataRestrictionsR restrictions = parseRestrictions(restrictionElement);
        DiscoverXmlMetaDataRequest request = new DiscoverXmlMetaDataRequestR(properties, restrictions);
        List<DiscoverXmlMetaDataResponseRow> rows = discoverService.xmlMetaData(request, metaData);
        writeResponse(rows, responseBody);
    }

    private DiscoverXmlMetaDataRestrictionsR parseRestrictions(SOAPElement restriction) {
        Map<String, String> m = DiscoverDispatcher.getRestrictionMap(restriction);
        return new DiscoverXmlMetaDataRestrictionsR(Optional.ofNullable(m.get(OBJECT_TYPE)),
                Optional.ofNullable(m.get(DATABASE_ID)), Optional.ofNullable(m.get(DIMENSION_ID)),
                Optional.ofNullable(m.get(CUBE_ID)), Optional.ofNullable(m.get(MEASURE_GROUP_ID)),
                Optional.ofNullable(m.get(PARTITION_ID)), Optional.ofNullable(m.get(PERSPECTIVE_ID)),
                Optional.ofNullable(m.get(DIMENSION_PERMISSION_ID)), Optional.ofNullable(m.get(ROLE_ID)),
                Optional.ofNullable(m.get(DATABASE_PERMISSION_ID)), Optional.ofNullable(m.get(MINING_MODEL_ID)),
                Optional.ofNullable(m.get(MINING_MODEL_PERMISSION_ID)), Optional.ofNullable(m.get(DATA_SOURCE_ID)),
                Optional.ofNullable(m.get(MINING_STRUCTURE_ID)), Optional.ofNullable(m.get(AGGREGATION_DESIGN_ID)),
                Optional.ofNullable(m.get(TRACE_ID)), Optional.ofNullable(m.get(MINING_STRUCTURE_PERMISSION_ID)),
                Optional.ofNullable(m.get(CUBE_PERMISSION_ID)), Optional.ofNullable(m.get(ASSEMBLY_ID)),
                Optional.ofNullable(m.get(MDX_SCRIPT_ID)), Optional.ofNullable(m.get(DATA_SOURCE_VIEW_ID)),
                Optional.ofNullable(m.get(DATA_SOURCE_PERMISSION_ID)),
                Optional.ofNullable(ObjectExpansionEnum.fromValue(m.get(OBJECT_EXPANSION))));
    }

    private void writeResponse(List<DiscoverXmlMetaDataResponseRow> rows, SOAPBody body) throws SOAPException {
        SOAPElement root = addRoot(body);
        for (DiscoverXmlMetaDataResponseRow row : rows) {
            addResponseRow(root, row);
        }
    }

    private SOAPElement addRoot(SOAPBody body) throws SOAPException {
        SOAPElement seRoot = SoapUtil.prepareRootElement(body);
        SOAPElement schema = SoapUtil.fillRoot(seRoot);

        SOAPElement el1complexType = SoapUtil.addChildElement(schema, Constants.XSD.QN_COMPLEX_TYPE);
        el1complexType.setAttribute("name", "xmlDocument");
        SOAPElement sequence = SoapUtil.addChildElement(el1complexType, Constants.XSD.QN_SEQUENCE);
        SoapUtil.addChildElement(sequence, Constants.XSD.QN_ANY);

        SOAPElement s = SoapUtil.prepareSequenceElement(schema);
        SoapUtil.addElement(s, "METADATA", "xmlDocument", null);
        return seRoot;
    }

    private void addResponseRow(SOAPElement root, DiscoverXmlMetaDataResponseRow r) throws SOAPException {
        SOAPElement row = root.addChildElement(ROWSET.QN_ROW);
        addChildElementTextNode(row, r.metaData());
    }

    private void addChildElementTextNode(SOAPElement element, String valueOfChild) {
        try {
            SOAPElement createdChild = element.addChildElement(ROW.QN_META_DATA);
            createdChild.setAttribute("xmlns", "http://schemas.microsoft.com/analysisservices/2003/engine");
            createdChild.setAttribute("xmlns:ddl2", "http://schemas.microsoft.com/analysisservices/2003/engine/2");
            createdChild.setAttribute("xmlns:ddl2_2", "http://schemas.microsoft.com/analysisservices/2003/engine/2/2");
            createdChild.setAttribute("xmlns:ddl100", "http://schemas.microsoft.com/analysisservices/2008/engine/100");
            createdChild.setAttribute("xmlns:ddl100_100",
                    "http://schemas.microsoft.com/analysisservices/2008/engine/100/100");
            createdChild.setAttribute("xmlns:ddl200", "http://schemas.microsoft.com/analysisservices/2010/engine/200");
            createdChild.setAttribute("xmlns:ddl200_200",
                    "http://schemas.microsoft.com/analysisservices/2010/engine/200/200");
            createdChild.setAttribute("xmlns:ddl300", "http://schemas.microsoft.com/analysisservices/2011/engine/300");
            createdChild.setAttribute("xmlns:ddl300_300",
                    "http://schemas.microsoft.com/analysisservices/2011/engine/300/300");
            createdChild.setAttribute("xmlns:ddl400", "http://schemas.microsoft.com/analysisservices/2012/engine/400");
            createdChild.setAttribute("xmlns:ddl400_400",
                    "http://schemas.microsoft.com/analysisservices/2012/engine/400/400");
            if (valueOfChild != null && !valueOfChild.isEmpty()) {
                SOAPElement se = SoapUtil.xmlStringToSoapElement(valueOfChild, createdChild);
                createdChild.addChildElement(se);
            }
        } catch (SOAPException | SAXException | IOException | ParserConfigurationException e) {
            throw new XmlaSoapException("addChildElementTextNode error", e);
        }
    }
}
