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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.PropertyConverter;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaParseException;

import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

/**
 * Dispatcher for XMLA Discover operations. Routes Discover requests to the
 * appropriate handler based on request type.
 */
public class DiscoverDispatcher {

    // Request type constants
    private static final String MDSCHEMA_FUNCTIONS = "MDSCHEMA_FUNCTIONS";
    private static final String MDSCHEMA_DIMENSIONS = "MDSCHEMA_DIMENSIONS";
    private static final String MDSCHEMA_CUBES = "MDSCHEMA_CUBES";
    private static final String MDSCHEMA_ACTIONS = "MDSCHEMA_ACTIONS";
    private static final String MDSCHEMA_HIERARCHIES = "MDSCHEMA_HIERARCHIES";
    private static final String MDSCHEMA_LEVELS = "MDSCHEMA_LEVELS";
    private static final String MDSCHEMA_MEASUREGROUP_DIMENSIONS = "MDSCHEMA_MEASUREGROUP_DIMENSIONS";
    private static final String MDSCHEMA_MEASURES = "MDSCHEMA_MEASURES";
    private static final String MDSCHEMA_MEMBERS = "MDSCHEMA_MEMBERS";
    private static final String MDSCHEMA_PROPERTIES = "MDSCHEMA_PROPERTIES";
    private static final String MDSCHEMA_SETS = "MDSCHEMA_SETS";
    private static final String MDSCHEMA_KPIS = "MDSCHEMA_KPIS";
    private static final String MDSCHEMA_MEASUREGROUPS = "MDSCHEMA_MEASUREGROUPS";
    private static final String DBSCHEMA_TABLES = "DBSCHEMA_TABLES";
    private static final String DBSCHEMA_CATALOGS = "DBSCHEMA_CATALOGS";
    private static final String DBSCHEMA_COLUMNS = "DBSCHEMA_COLUMNS";
    private static final String DBSCHEMA_PROVIDER_TYPES = "DBSCHEMA_PROVIDER_TYPES";
    private static final String DBSCHEMA_SCHEMATA = "DBSCHEMA_SCHEMATA";
    private static final String DBSCHEMA_SOURCE_TABLES = "DBSCHEMA_SOURCE_TABLES";
    private static final String DBSCHEMA_TABLES_INFO = "DBSCHEMA_TABLES_INFO";
    private static final String DISCOVER_LITERALS = "DISCOVER_LITERALS";
    private static final String DISCOVER_KEYWORDS = "DISCOVER_KEYWORDS";
    private static final String DISCOVER_ENUMERATORS = "DISCOVER_ENUMERATORS";
    private static final String DISCOVER_SCHEMA_ROWSETS = "DISCOVER_SCHEMA_ROWSETS";
    private static final String DISCOVER_PROPERTIES = "DISCOVER_PROPERTIES";
    private static final String DISCOVER_DATASOURCES = "DISCOVER_DATASOURCES";
    private static final String DISCOVER_XML_METADATA = "DISCOVER_XML_METADATA";
    private static final String DISCOVER_CSDL_METADATA = "DISCOVER_CSDL_METADATA";

    private final Map<String, DiscoverHandler> handlers;

    public DiscoverDispatcher(DiscoverService discoverService) {
        this.handlers = initHandlers(discoverService);
    }

    /**
     * Dispatch a Discover SOAP element to the appropriate handler.
     *
     * @param discoverElement the Discover SOAP element
     * @param responseBody    the response body to write to
     * @param metaData        request metadata
     * @throws SOAPException if SOAP processing fails
     */
    public void dispatch(SOAPElement discoverElement, SOAPBody responseBody, RequestMetaData metaData)
            throws SOAPException {

        String requestType = null;
        PropertiesR properties = null;
        SOAPElement restrictions = null;

        Iterator<Node> nodeIterator = discoverElement.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement element) {
                if (requestType == null && Constants.MSXMLA.QN_REQUEST_TYPE.equals(element.getElementQName())) {
                    requestType = element.getTextContent();
                    continue;
                }
                if (restrictions == null && Constants.MSXMLA.QN_RESTRICTIONS.equals(element.getElementQName())) {
                    restrictions = element;
                    continue;
                }
                if (properties == null && Constants.MSXMLA.QN_PROPERTIES.equals(element.getElementQName())) {
                    properties = PropertyConverter.propertiestoProperties(element);
                }
            }
        }

        DiscoverHandler handler = handlers.get(requestType);
        if (handler == null) {
            throw new XmlaParseException("Unknown request type: " + requestType);
        }
        handler.handle(metaData, properties, restrictions, responseBody);
    }

    private Map<String, DiscoverHandler> initHandlers(DiscoverService ds) {
        Map<String, DiscoverHandler> map = new HashMap<>();
        map.put(MDSCHEMA_FUNCTIONS, new MdSchemaFunctionsHandler(ds));
        map.put(MDSCHEMA_DIMENSIONS, new MdSchemaDimensionsHandler(ds));
        map.put(MDSCHEMA_CUBES, new MdSchemaCubesHandler(ds));
        map.put(MDSCHEMA_ACTIONS, new MdSchemaActionsHandler(ds));
        map.put(MDSCHEMA_HIERARCHIES, new MdSchemaHierarchiesHandler(ds));
        map.put(MDSCHEMA_LEVELS, new MdSchemaLevelsHandler(ds));
        map.put(MDSCHEMA_MEASUREGROUP_DIMENSIONS, new MdSchemaMeasureGroupDimensionsHandler(ds));
        map.put(MDSCHEMA_MEASURES, new MdSchemaMeasuresHandler(ds));
        map.put(MDSCHEMA_MEMBERS, new MdSchemaMembersHandler(ds));
        map.put(MDSCHEMA_PROPERTIES, new MdSchemaPropertiesHandler(ds));
        map.put(MDSCHEMA_SETS, new MdSchemaSetsHandler(ds));
        map.put(MDSCHEMA_KPIS, new MdSchemaKpisHandler(ds));
        map.put(MDSCHEMA_MEASUREGROUPS, new MdSchemaMeasureGroupsHandler(ds));
        map.put(DBSCHEMA_TABLES, new DbSchemaTablesHandler(ds));
        map.put(DBSCHEMA_CATALOGS, new DbSchemaCatalogsHandler(ds));
        map.put(DBSCHEMA_COLUMNS, new DbSchemaColumnsHandler(ds));
        map.put(DBSCHEMA_PROVIDER_TYPES, new DbSchemaProviderTypesHandler(ds));
        map.put(DBSCHEMA_SCHEMATA, new DbSchemaSchemataHandler(ds));
        map.put(DBSCHEMA_SOURCE_TABLES, new DbSchemaSourceTablesHandler(ds));
        map.put(DBSCHEMA_TABLES_INFO, new DbSchemaTablesInfoHandler(ds));
        map.put(DISCOVER_LITERALS, new DiscoverLiteralsHandler(ds));
        map.put(DISCOVER_KEYWORDS, new DiscoverKeywordsHandler(ds));
        map.put(DISCOVER_ENUMERATORS, new DiscoverEnumeratorsHandler(ds));
        map.put(DISCOVER_SCHEMA_ROWSETS, new DiscoverSchemaRowsetsHandler(ds));
        map.put(DISCOVER_PROPERTIES, new DiscoverPropertiesHandler(ds));
        map.put(DISCOVER_DATASOURCES, new DiscoverDataSourcesHandler(ds));
        map.put(DISCOVER_XML_METADATA, new DiscoverXmlMetaDataHandler(ds));
        map.put(DISCOVER_CSDL_METADATA, new DiscoverCsdlMetaDataHandler(ds));
        return map;
    }

    /**
     * Parse restriction map from a SOAP element.
     *
     * @param el the SOAP element containing restrictions
     * @return map of restriction name to value
     */
    public static Map<String, String> getRestrictionMap(SOAPElement el) {
        Iterator<Node> nodeIterator = el.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement restrictions
                    && Constants.MSXMLA.QN_RESTRICTION_LIST.equals(restrictions.getElementQName())) {
                return restrictionValues(restrictions);
            }
        }
        return Map.of();
    }

    private static Map<String, String> restrictionValues(SOAPElement restrictionList) {
        Map<String, String> result = new HashMap<>();
        Iterator<Node> nodeIteratorRestrictionList = restrictionList.getChildElements();
        while (nodeIteratorRestrictionList.hasNext()) {
            Node n = nodeIteratorRestrictionList.next();
            if (n instanceof SOAPElement restrictionListElement) {
                String name = restrictionListElement.getLocalName();
                result.put(name, restrictionListElement.getTextContent());
            }
        }
        return result;
    }
}
