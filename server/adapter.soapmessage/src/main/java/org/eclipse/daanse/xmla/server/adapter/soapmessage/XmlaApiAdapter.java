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
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.xml.namespace.QName;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserRolePrincipal;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.catalogs.DbSchemaCatalogsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.columns.DbSchemaColumnsResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.providertypes.DbSchemaProviderTypesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.schemata.DbSchemaSchemataResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.sourcetables.DbSchemaSourceTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tables.DbSchemaTablesResponseRow;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoRequest;
import org.eclipse.daanse.xmla.api.discover.dbschema.tablesinfo.DbSchemaTablesInfoResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesRequest;
import org.eclipse.daanse.xmla.api.discover.discover.datasources.DiscoverDataSourcesResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.enumerators.DiscoverEnumeratorsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.keywords.DiscoverKeywordsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.literals.DiscoverLiteralsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.discover.properties.DiscoverPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsRequest;
import org.eclipse.daanse.xmla.api.discover.discover.schemarowsets.DiscoverSchemaRowsetsResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.csdlmetadata.DiscoverCsdlMetaDataRequest;
import org.eclipse.daanse.xmla.api.discover.discover.csdlmetadata.DiscoverCsdlMetaDataResponseRow;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataRequest;
import org.eclipse.daanse.xmla.api.discover.discover.xmlmetadata.DiscoverXmlMetaDataResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.actions.MdSchemaActionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.cubes.MdSchemaCubesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.functions.MdSchemaFunctionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.hierarchies.MdSchemaHierarchiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.kpis.MdSchemaKpisResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.levels.MdSchemaLevelsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measuregroups.MdSchemaMeasureGroupsResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.measures.MdSchemaMeasuresResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.members.MdSchemaMembersResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.properties.MdSchemaPropertiesResponseRow;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsRequest;
import org.eclipse.daanse.xmla.api.discover.mdschema.sets.MdSchemaSetsResponseRow;
import org.eclipse.daanse.xmla.api.execute.ExecuteParameter;
import org.eclipse.daanse.xmla.api.execute.alter.AlterRequest;
import org.eclipse.daanse.xmla.api.execute.alter.AlterResponse;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelRequest;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelResponse;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheRequest;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheResponse;
import org.eclipse.daanse.xmla.api.execute.statement.StatementRequest;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;
import org.eclipse.daanse.xmla.api.xmla.BeginSession;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.EndSession;
import org.eclipse.daanse.xmla.api.xmla.Session;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs.DbSchemaCatalogsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.catalogs.DbSchemaCatalogsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.columns.DbSchemaColumnsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.providertypes.DbSchemaProviderTypesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.providertypes.DbSchemaProviderTypesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.schemata.DbSchemaSchemataRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.sourcetables.DbSchemaSourceTablesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tables.DbSchemaTablesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoRequestR;
import org.eclipse.daanse.xmla.model.record.discover.dbschema.tablesinfo.DbSchemaTablesInfoRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.csdlmetadata.DiscoverCsdlMetaDataRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.csdlmetadata.DiscoverCsdlMetaDataRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.datasources.DiscoverDataSourcesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.datasources.DiscoverDataSourcesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.enumerators.DiscoverEnumeratorsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.enumerators.DiscoverEnumeratorsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.keywords.DiscoverKeywordsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.keywords.DiscoverKeywordsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.literals.DiscoverLiteralsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.literals.DiscoverLiteralsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.properties.DiscoverPropertiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.properties.DiscoverPropertiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.schemarowsets.DiscoverSchemaRowsetsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.schemarowsets.DiscoverSchemaRowsetsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.discover.xmlmetadata.DiscoverXmlMetaDataRequestR;
import org.eclipse.daanse.xmla.model.record.discover.discover.xmlmetadata.DiscoverXmlMetaDataRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.actions.MdSchemaActionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.actions.MdSchemaActionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.cubes.MdSchemaCubesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.functions.MdSchemaFunctionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.functions.MdSchemaFunctionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies.MdSchemaHierarchiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.hierarchies.MdSchemaHierarchiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.kpis.MdSchemaKpisRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.kpis.MdSchemaKpisRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.levels.MdSchemaLevelsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroupdimensions.MdSchemaMeasureGroupDimensionsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measuregroups.MdSchemaMeasureGroupsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.measures.MdSchemaMeasuresRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.members.MdSchemaMembersRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.members.MdSchemaMembersRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.properties.MdSchemaPropertiesRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.properties.MdSchemaPropertiesRestrictionsR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.sets.MdSchemaSetsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.sets.MdSchemaSetsRestrictionsR;
import org.eclipse.daanse.xmla.model.record.execute.alter.AlterRequestR;
import org.eclipse.daanse.xmla.model.record.execute.cancel.CancelRequestR;
import org.eclipse.daanse.xmla.model.record.execute.clearcache.ClearCacheRequestR;
import org.eclipse.daanse.xmla.model.record.execute.statement.StatementRequestR;
import org.eclipse.daanse.xmla.model.record.xmla.AlterR;
import org.eclipse.daanse.xmla.model.record.xmla.CancelR;
import org.eclipse.daanse.xmla.model.record.xmla.ClearCacheR;
import org.eclipse.daanse.xmla.model.record.xmla.StatementR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

public class XmlaApiAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlaApiAdapter.class);

    private XmlaService xmlaService;

    public XmlaApiAdapter(XmlaService xmlaService) {
        this.xmlaService = xmlaService;
    }

    private static final String MDSCHEMA_FUNCTIONS = "MDSCHEMA_FUNCTIONS";
    private static final String MDSCHEMA_DIMENSIONS = "MDSCHEMA_DIMENSIONS";
    private static final String MDSCHEMA_CUBES = "MDSCHEMA_CUBES";
    private static final String MDSCHEMA_ACTIONS = "MDSCHEMA_ACTIONS";
    private static final String DBSCHEMA_TABLES = "DBSCHEMA_TABLES";
    private static final String DISCOVER_LITERALS = "DISCOVER_LITERALS";
    private static final String DISCOVER_KEYWORDS = "DISCOVER_KEYWORDS";
    private static final String DISCOVER_ENUMERATORS = "DISCOVER_ENUMERATORS";
    private static final String DISCOVER_SCHEMA_ROWSETS = "DISCOVER_SCHEMA_ROWSETS";
    private static final String DISCOVER_PROPERTIES = "DISCOVER_PROPERTIES";
    private static final String DBSCHEMA_CATALOGS = "DBSCHEMA_CATALOGS";
    private static final String DISCOVER_DATASOURCES = "DISCOVER_DATASOURCES";
    private static final String DISCOVER_XML_METADATA = "DISCOVER_XML_METADATA";
    private static final String DISCOVER_CSDL_METADATA = "DISCOVER_CSDL_METADATA";
    private static final String DBSCHEMA_COLUMNS = "DBSCHEMA_COLUMNS";
    private static final String DBSCHEMA_PROVIDER_TYPES = "DBSCHEMA_PROVIDER_TYPES";
    private static final String DBSCHEMA_SCHEMATA = "DBSCHEMA_SCHEMATA";
    private static final String DBSCHEMA_SOURCE_TABLES = "DBSCHEMA_SOURCE_TABLES";
    private static final String DBSCHEMA_TABLES_INFO = "DBSCHEMA_TABLES_INFO";
    private static final String MDSCHEMA_HIERARCHIES = "MDSCHEMA_HIERARCHIES";
    private static final String MDSCHEMA_LEVELS = "MDSCHEMA_LEVELS";
    private static final String MDSCHEMA_MEASUREGROUP_DIMENSIONS = "MDSCHEMA_MEASUREGROUP_DIMENSIONS";
    private static final String MDSCHEMA_MEASURES = "MDSCHEMA_MEASURES";
    private static final String MDSCHEMA_MEMBERS = "MDSCHEMA_MEMBERS";
    private static final String MDSCHEMA_PROPERTIES = "MDSCHEMA_PROPERTIES";
    private static final String MDSCHEMA_SETS = "MDSCHEMA_SETS";
    private static final String MDSCHEMA_KPIS = "MDSCHEMA_KPIS";
    private static final String MDSCHEMA_MEASUREGROUPS = "MDSCHEMA_MEASUREGROUPS";
    private static final QName QN_SESSION = new QName("urn:schemas-microsoft-com:xml-analysis", "Session");

    public SOAPMessage handleRequest(SOAPMessage messageRequest, Map<String, Object> headers, Principal principal,
            Function<String, Boolean> isUserInRoleFunction, String url) {
        try {
            SOAPMessage messageResponse = MessageFactory.newInstance().createMessage();
            messageResponse.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
            SOAPPart soapPartResponse = messageResponse.getSOAPPart();
            SOAPEnvelope envelopeResponse = soapPartResponse.getEnvelope();

            // envelopeResponse.addNamespaceDeclaration(Constants.MSXMLA.PREFIX,
            // Constants.MSXMLA.NS_URN);
            // envelopeResponse.addNamespaceDeclaration(Constants.ROWSET.PREFIX,
            // Constants.ROWSET.NS_URN);
            // envelopeResponse.addNamespaceDeclaration(Constants.MDDATASET.PREFIX,
            // Constants.MDDATASET.NS_URN);
            // envelopeResponse.addNamespaceDeclaration(Constants.ENGINE.PREFIX,
            // Constants.ENGINE.NS_URN);
            // envelopeResponse.addNamespaceDeclaration(Constants.ENGINE200.PREFIX,
            // Constants.ENGINE200.NS_URN);
            // envelopeResponse.addNamespaceDeclaration(Constants.EMPTY.PREFIX,
            // Constants.EMPTY.NS_URN);
            // envelopeResponse.addNamespaceDeclaration(Constants.XSI.PREFIX,
            // Constants.XSI.NS_URN);

            UserRolePrincipal userPrincipal = new UserRolePrincipal() {

                @Override
                public String userName() {
                    if (principal == null) {
                        return "";
                    }
                    return principal.getName();
                }

                @Override
                public boolean hasRole(String role) {
                    if (isUserInRoleFunction == null) {
                        return false;
                    }
                    return isUserInRoleFunction.apply(role);
                }
            };
            Optional<Session> oSession = session(messageRequest.getSOAPHeader(), userPrincipal);
            if (oSession.isPresent()) {
                SOAPHeader header = envelopeResponse.getHeader();
                SOAPHeaderElement sessionElement = header.addHeaderElement(QN_SESSION);
                sessionElement.addAttribute(new QName("SessionId"), oSession.get().sessionId());
            } else {
                SOAPHeader header = envelopeResponse.getHeader();
                header.setValue("\n");
            }
            RequestMetaData metaData = RequestMetaDataUtils.getRequestMetaData(headers, oSession, url);
            SOAPBody bodyResponse = envelopeResponse.getBody();
            handleBody(messageRequest.getSOAPBody(), bodyResponse, metaData, userPrincipal);
            return messageResponse;
        } catch (SOAPException e) {
            LOGGER.error("handleRequest error", e);
        }
        return null;
    }

    private Optional<Session> session(SOAPHeader soapRequestHeader, UserRolePrincipal userPrincipal) throws SOAPException {
        Optional<Session> oSession = Convert.getSession(soapRequestHeader);
        if (oSession.isPresent()) {
            boolean checked = xmlaService.session().checkSession(oSession.get(), userPrincipal);
            if (checked) {
                return oSession;
            } else {
                Optional.empty();
            }
        }

        Optional<BeginSession> beginSession = Convert.getBeginSession(soapRequestHeader);
        if (beginSession.isPresent()) {
            return xmlaService.session().beginSession(beginSession.get(), userPrincipal);
        }

        Optional<EndSession> oEndSession = Convert.getEndSession(soapRequestHeader);
        if (oEndSession.isPresent()) {
            xmlaService.session().endSession(oEndSession.get(), userPrincipal);
            return Optional.empty();
        }

        return Optional.empty();
    }

    private void handleBody(SOAPBody body, SOAPBody responseBody, RequestMetaData metaData, UserRolePrincipal userPrincipal)
            throws SOAPException {
        SOAPElement node = null;

        Iterator<Node> nodeIterator = body.getChildElements();
        while (nodeIterator.hasNext()) {
            Node nodeN = nodeIterator.next();
            if (nodeN instanceof SOAPElement soapElement) {
                node = soapElement;
                break;
            }
        }
        if (node != null) {
            printNode(node);
        }

        if (node != null && Constants.MSXMLA.QN_DISCOVER.equals(node.getElementQName())) {

            discover(node, responseBody, metaData);

        }
        if (node != null && Constants.MSXMLA.QN_EXECUTE.equals(node.getElementQName())) {
            execute(node, responseBody, metaData, userPrincipal);
        }

    }

    private void discover(SOAPElement discover, SOAPBody responseBody, RequestMetaData metaData) throws SOAPException {

        String requestType = null;
        PropertiesR properties = null;
        SOAPElement restictions = null;

        Iterator<Node> nodeIterator = discover.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement element) {
                if (requestType == null && Constants.MSXMLA.QN_REQUEST_TYPE.equals(element.getElementQName())) {
                    requestType = element.getTextContent();
                    continue;
                }
                if (restictions == null && Constants.MSXMLA.QN_RESTRICTIONS.equals(element.getElementQName())) {
                    restictions = element;
                    continue;
                }
                if (properties == null && Constants.MSXMLA.QN_PROPERTIES.equals(element.getElementQName())) {
                    properties = Convert.propertiestoProperties(element);
                }
            }
        }

        discover(requestType, metaData, properties, restictions, responseBody);
    }

    private void execute(SOAPElement discover, SOAPBody responseBody, RequestMetaData metaData,
            UserRolePrincipal userPrincipal) throws SOAPException {

        Command command = null;
        PropertiesR properties = null;
        List<ExecuteParameter> parameters = null;

        Iterator<Node> nodeIterator = discover.getChildElements();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if (node instanceof SOAPElement element) {
                if (properties == null && Constants.MSXMLA.QN_PROPERTIES.equals(element.getElementQName())) {
                    properties = Convert.propertiestoProperties(element);
                }
                if (parameters == null && Constants.MSXMLA.QN_PARAMETERS.equals(element.getElementQName())) {
                    parameters = Convert.parametersToParameters(element);
                }
                if (command == null && Constants.MSXMLA.QN_COMMAND.equals(element.getElementQName())) {
                    command = Convert.commandtoCommand(element);
                }
            }
        }

        execute(command, properties, parameters, responseBody, metaData, userPrincipal);
    }

    private void printNode(SOAPElement node) {
        LOGGER.debug(node.getNamespaceURI());
        LOGGER.debug(node.getBaseURI());
        LOGGER.debug(node.getPrefix());
        LOGGER.debug(node.getNodeName());
        LOGGER.debug(node.getLocalName());
        LOGGER.debug(node.getNodeValue());
        LOGGER.debug(node.getTextContent());
        LOGGER.debug(node.getValue());
        String elementQNameStr = node.getElementQName().toString();
        LOGGER.debug(elementQNameStr);
    }

    private void execute(Command command, PropertiesR properties, List<ExecuteParameter> parameters,
            SOAPBody responseBody, RequestMetaData metaData, UserRolePrincipal userPrincipal) throws SOAPException {

        if (command instanceof StatementR statement) {
            handleStatement(metaData, userPrincipal, statement, properties, parameters, responseBody);
        }
        if (command instanceof AlterR alter) {
            handleAlter(metaData, userPrincipal, alter, properties, parameters, responseBody);
        }
        if (command instanceof ClearCacheR clearCache) {
            handleClearCache(metaData, userPrincipal, clearCache, properties, parameters, responseBody);
        }
        if (command instanceof CancelR cancel) {
            handleCancel(metaData, userPrincipal, cancel, properties, parameters, responseBody);
        }
    }

    private void discover(String requestType, RequestMetaData metaData,
            PropertiesR properties, SOAPElement restrictionElement, SOAPBody responseBody) throws SOAPException {

        switch (requestType) {
        case MDSCHEMA_FUNCTIONS ->
            handleMdSchemaFunctions(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_DIMENSIONS ->
            handleMdSchemaDimensions(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_CUBES ->
            handleMdSchemaCubes(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_ACTIONS ->
            handleMdSchemaActions(metaData, properties, restrictionElement, responseBody);
        case DBSCHEMA_TABLES ->
            handleDbSchemaTables(metaData, properties, restrictionElement, responseBody);
        case DISCOVER_LITERALS ->
            handleDiscoverLiterals(metaData, properties, restrictionElement, responseBody);
        case DISCOVER_KEYWORDS ->
            handleDiscoverKeywords(metaData, properties, restrictionElement, responseBody);
        case DISCOVER_ENUMERATORS ->
            handleDiscoverEnumerators(metaData, properties, restrictionElement, responseBody);
        case DISCOVER_SCHEMA_ROWSETS ->
            handleDiscoverSchemaRowsets(metaData, properties, restrictionElement, responseBody);
        case DISCOVER_PROPERTIES ->
            handleDiscoverProperties(metaData, properties, restrictionElement, responseBody);
        case DBSCHEMA_CATALOGS ->
            handleDbSchemaCatalogs(metaData, properties, restrictionElement, responseBody);
        case DISCOVER_DATASOURCES ->
            handleDiscoverDataSources(metaData, properties, restrictionElement, responseBody);
        case DISCOVER_XML_METADATA ->
            handleDiscoverXmlMetaData(metaData, properties, restrictionElement, responseBody);
        case DISCOVER_CSDL_METADATA ->
            handleDiscoverCsdlMetaData(metaData, properties, restrictionElement, responseBody);
        case DBSCHEMA_COLUMNS ->
            handleDbSchemaColumns(metaData, properties, restrictionElement, responseBody);
        case DBSCHEMA_PROVIDER_TYPES ->
            handleDbSchemaProviderTypes(metaData, properties, restrictionElement, responseBody);
        case DBSCHEMA_SCHEMATA ->
            handleDbSchemaSchemata(metaData, properties, restrictionElement, responseBody);
        case DBSCHEMA_SOURCE_TABLES ->
            handleDbSchemaSourceTables(metaData, properties, restrictionElement, responseBody);
        case DBSCHEMA_TABLES_INFO ->
            handleDbSchemaTablesInfo(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_HIERARCHIES ->
            handleMdSchemaHierarchies(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_LEVELS ->
            handleMdSchemaLevels(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_MEASUREGROUP_DIMENSIONS ->
            handleMdSchemaMeasureGroupDimensions(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_MEASURES ->
            handleMdSchemaMeasures(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_MEMBERS ->
            handleMdSchemaMembers(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_PROPERTIES ->
            handleMdSchemaProperties(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_SETS -> handleMdSchemaSets(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_KPIS -> handleMdSchemaKpis(metaData, properties, restrictionElement, responseBody);
        case MDSCHEMA_MEASUREGROUPS ->
            handleMdSchemaMeasureGroups(metaData, properties, restrictionElement, responseBody);
        default -> throw new IllegalArgumentException("Unexpected value: " + requestType);

        }
    }

    private void handleMdSchemaMeasureGroups(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaMeasureGroupsRestrictionsR restrictionsR = Convert.discoverMdSchemaMeasureGroups(restrictionElement);
        MdSchemaMeasureGroupsRequest request = new MdSchemaMeasureGroupsRequestR(propertiesR, restrictionsR);
        List<MdSchemaMeasureGroupsResponseRow> rows = xmlaService.discover().mdSchemaMeasureGroups(request, metaData);

        SoapUtil.toMdSchemaMeasureGroups(rows, body);
    }

    private void handleMdSchemaKpis(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaKpisRestrictionsR restrictionsR = Convert.discoverMdSchemaKpisRestrictions(restrictionElement);
        MdSchemaKpisRequest request = new MdSchemaKpisRequestR(propertiesR, restrictionsR);
        List<MdSchemaKpisResponseRow> rows = xmlaService.discover().mdSchemaKpis(request, metaData);

        SoapUtil.toMdSchemaKpis(rows, body);
    }

    private void handleMdSchemaSets(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaSetsRestrictionsR restrictionsR = Convert.discoverMdSchemaSetsRestrictions(restrictionElement);
        MdSchemaSetsRequest request = new MdSchemaSetsRequestR(propertiesR, restrictionsR);
        List<MdSchemaSetsResponseRow> rows = xmlaService.discover().mdSchemaSets(request, metaData);

        SoapUtil.toMdSchemaSets(rows, body);
    }

    private void handleMdSchemaProperties(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaPropertiesRestrictionsR restrictionsR = Convert
                .discoverMdSchemaPropertiesRestrictions(restrictionElement);
        MdSchemaPropertiesRequest request = new MdSchemaPropertiesRequestR(propertiesR, restrictionsR);
        List<MdSchemaPropertiesResponseRow> rows = xmlaService.discover().mdSchemaProperties(request, metaData);

        SoapUtil.toMdSchemaProperties(rows, body);

    }

    private void handleMdSchemaMembers(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaMembersRestrictionsR restrictionsR = Convert.discoverMdSchemaMembersRestrictions(restrictionElement);
        MdSchemaMembersRequest request = new MdSchemaMembersRequestR(propertiesR, restrictionsR);
        List<MdSchemaMembersResponseRow> rows = xmlaService.discover().mdSchemaMembers(request, metaData);

        SoapUtil.toMdSchemaMembers(rows, body);

    }

    private void handleMdSchemaMeasures(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaMeasuresRestrictionsR restrictionsR = Convert.discoverMdSchemaMeasuresRestrictions(restrictionElement);
        MdSchemaMeasuresRequest request = new MdSchemaMeasuresRequestR(propertiesR, restrictionsR);
        List<MdSchemaMeasuresResponseRow> rows = xmlaService.discover().mdSchemaMeasures(request, metaData);

        SoapUtil.toMdSchemaMeasures(rows, body);

    }

    private void handleMdSchemaMeasureGroupDimensions(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaMeasureGroupDimensionsRestrictionsR restrictionsR = Convert
                .discoverMdSchemaMeasureGroupDimensionsRestrictions(restrictionElement);
        MdSchemaMeasureGroupDimensionsRequest request = new MdSchemaMeasureGroupDimensionsRequestR(propertiesR,
                restrictionsR);
        List<MdSchemaMeasureGroupDimensionsResponseRow> rows = xmlaService.discover()
                .mdSchemaMeasureGroupDimensions(request, metaData);

        SoapUtil.toMdSchemaMeasureGroupDimensions(rows, body);

    }

    private void handleMdSchemaLevels(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaLevelsRestrictionsR restrictionsR = Convert.discoverMdSchemaLevelsRestrictions(restrictionElement);
        MdSchemaLevelsRequest request = new MdSchemaLevelsRequestR(propertiesR, restrictionsR);
        List<MdSchemaLevelsResponseRow> rows = xmlaService.discover().mdSchemaLevels(request, metaData);

        SoapUtil.toMdSchemaLevels(rows, body);
    }

    private void handleMdSchemaHierarchies(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaHierarchiesRestrictionsR restrictionsR = Convert
                .discoverMdSchemaHierarchiesRestrictions(restrictionElement);
        MdSchemaHierarchiesRequest request = new MdSchemaHierarchiesRequestR(propertiesR, restrictionsR);
        List<MdSchemaHierarchiesResponseRow> rows = xmlaService.discover().mdSchemaHierarchies(request, metaData);

        SoapUtil.toMdSchemaHierarchies(rows, body);
    }

    private void handleDbSchemaTablesInfo(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DbSchemaTablesInfoRestrictionsR restrictionsR = Convert.discoverDbSchemaTablesInfo(restrictionElement);
        DbSchemaTablesInfoRequest request = new DbSchemaTablesInfoRequestR(propertiesR, restrictionsR);
        List<DbSchemaTablesInfoResponseRow> rows = xmlaService.discover().dbSchemaTablesInfo(request, metaData);

        SoapUtil.toDbSchemaTablesInfo(rows, body);

    }

    private void handleDbSchemaSourceTables(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DbSchemaSourceTablesRestrictionsR restrictionsR = Convert
                .discoverDbSchemaSourceTablesRestrictions(restrictionElement);
        DbSchemaSourceTablesRequest request = new DbSchemaSourceTablesRequestR(propertiesR, restrictionsR);
        List<DbSchemaSourceTablesResponseRow> rows = xmlaService.discover().dbSchemaSourceTables(request, metaData);

        SoapUtil.toDbSchemaSourceTables(rows, body);

    }

    private void handleDbSchemaSchemata(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DbSchemaSchemataRestrictionsR restrictionsR = Convert.discoverDbSchemaSchemataRestrictions(restrictionElement);
        DbSchemaSchemataRequest request = new DbSchemaSchemataRequestR(propertiesR, restrictionsR);
        List<DbSchemaSchemataResponseRow> rows = xmlaService.discover().dbSchemaSchemata(request, metaData);

        SoapUtil.toDbSchemaSchemata(rows, body);

    }

    private void handleDbSchemaProviderTypes(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DbSchemaProviderTypesRestrictionsR restrictionsR = Convert
                .discoverDbSchemaProviderTypesRestrictions(restrictionElement);
        DbSchemaProviderTypesRequest request = new DbSchemaProviderTypesRequestR(propertiesR, restrictionsR);
        List<DbSchemaProviderTypesResponseRow> rows = xmlaService.discover().dbSchemaProviderTypes(request, metaData);

        SoapUtil.toDbSchemaProviderTypes(rows, body);

    }

    private void handleDbSchemaColumns(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DbSchemaColumnsRestrictionsR restrictionsR = Convert.discoverDbSchemaColumnsRestrictions(restrictionElement);
        DbSchemaColumnsRequest request = new DbSchemaColumnsRequestR(propertiesR, restrictionsR);
        List<DbSchemaColumnsResponseRow> rows = xmlaService.discover().dbSchemaColumns(request, metaData);

        SoapUtil.toDbSchemaColumns(rows, body);

    }

    private void handleDiscoverXmlMetaData(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DiscoverXmlMetaDataRestrictionsR restrictionsR = Convert
                .discoverDiscoverXmlMetaDataRestrictions(restrictionElement);
        DiscoverXmlMetaDataRequest request = new DiscoverXmlMetaDataRequestR(propertiesR, restrictionsR);
        List<DiscoverXmlMetaDataResponseRow> rows = xmlaService.discover().xmlMetaData(request, metaData);

        SoapUtil.toDiscoverXmlMetaData(rows, body);

    }

    private void handleDiscoverCsdlMetaData(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DiscoverCsdlMetaDataRestrictionsR restrictionsR = Convert
                .discoverDiscoverCsdlMetaDataRestrictions(restrictionElement);
        DiscoverCsdlMetaDataRequest request = new DiscoverCsdlMetaDataRequestR(propertiesR, restrictionsR);
        List<DiscoverCsdlMetaDataResponseRow> rows = xmlaService.discover().csdlMetaData(request, metaData);

        SoapUtil.toDiscoverCsdlMetaData(rows, body);

    }

    private void handleDiscoverDataSources(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DiscoverDataSourcesRestrictionsR restrictionsR = Convert
                .discoverDiscoverDataSourcesRestrictions(restrictionElement);
        DiscoverDataSourcesRequest request = new DiscoverDataSourcesRequestR(propertiesR, restrictionsR);
        List<DiscoverDataSourcesResponseRow> rows = xmlaService.discover().dataSources(request, metaData);

        SoapUtil.toDiscoverDataSources(rows, body);

    }

    private void handleDbSchemaCatalogs(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DbSchemaCatalogsRestrictionsR restrictionsR = Convert.discoverDbSchemaCatalogsRestrictions(restrictionElement);
        DbSchemaCatalogsRequest request = new DbSchemaCatalogsRequestR(propertiesR, restrictionsR);
        List<DbSchemaCatalogsResponseRow> rows = xmlaService.discover().dbSchemaCatalogs(request, metaData);

        SoapUtil.toDbSchemaCatalogs(rows, body);

    }

    private void handleDiscoverSchemaRowsets(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DiscoverSchemaRowsetsRestrictionsR restrictionsR = Convert
                .discoverSchemaRowsetsRestrictions(restrictionElement);
        DiscoverSchemaRowsetsRequest request = new DiscoverSchemaRowsetsRequestR(propertiesR, restrictionsR);
        List<DiscoverSchemaRowsetsResponseRow> rows = xmlaService.discover().discoverSchemaRowsets(request, metaData);

        SoapUtil.toDiscoverSchemaRowsets(rows, body);

    }

    private void handleDiscoverEnumerators(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DiscoverEnumeratorsRestrictionsR restrictionsR = Convert.discoverDiscoverEnumerators(restrictionElement);
        DiscoverEnumeratorsRequest request = new DiscoverEnumeratorsRequestR(propertiesR, restrictionsR);
        List<DiscoverEnumeratorsResponseRow> rows = xmlaService.discover().discoverEnumerators(request, metaData);

        SoapUtil.toDiscoverEnumerators(rows, body);

    }

    private void handleDiscoverKeywords(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DiscoverKeywordsRestrictionsR restrictionsR = Convert.discoverKeywordsRestrictions(restrictionElement);
        DiscoverKeywordsRequest request = new DiscoverKeywordsRequestR(propertiesR, restrictionsR);
        List<DiscoverKeywordsResponseRow> rows = xmlaService.discover().discoverKeywords(request, metaData);

        SoapUtil.toDiscoverKeywords(rows, body);

    }

    private void handleDiscoverLiterals(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DiscoverLiteralsRestrictionsR restrictionsR = Convert.discoverLiteralsRestrictions(restrictionElement);
        DiscoverLiteralsRequest request = new DiscoverLiteralsRequestR(propertiesR, restrictionsR);
        List<DiscoverLiteralsResponseRow> rows = xmlaService.discover().discoverLiterals(request, metaData);

        SoapUtil.toDiscoverLiterals(rows, body);

    }

    private void handleDbSchemaTables(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        DbSchemaTablesRestrictionsR restrictionsR = Convert.discoverDbSchemaTablesRestrictions(restrictionElement);
        DbSchemaTablesRequest request = new DbSchemaTablesRequestR(propertiesR, restrictionsR);
        List<DbSchemaTablesResponseRow> rows = xmlaService.discover().dbSchemaTables(request, metaData);

        SoapUtil.toDbSchemaTables(rows, body);

    }

    private void handleMdSchemaActions(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaActionsRestrictionsR restrictionsR = Convert.discoverMdSchemaActionsRestrictions(restrictionElement);
        MdSchemaActionsRequest request = new MdSchemaActionsRequestR(propertiesR, restrictionsR);
        List<MdSchemaActionsResponseRow> rows = xmlaService.discover().mdSchemaActions(request, metaData);

        SoapUtil.toMdSchemaActions(rows, body);
    }

    private void handleMdSchemaCubes(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaCubesRestrictionsR restrictionsR = Convert.discoverMdSchemaCubesRestrictions(restrictionElement);
        MdSchemaCubesRequest request = new MdSchemaCubesRequestR(propertiesR, restrictionsR);
        List<MdSchemaCubesResponseRow> rows = xmlaService.discover().mdSchemaCubes(request, metaData);

        SoapUtil.toMdSchemaCubes(rows, body);
    }

    private void handleMdSchemaDimensions(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {
        MdSchemaDimensionsRestrictionsR restrictionsR = Convert
                .discoverMdSchemaDimensionsRestrictions(restrictionElement);
        MdSchemaDimensionsRequest request = new MdSchemaDimensionsRequestR(propertiesR, restrictionsR);
        List<MdSchemaDimensionsResponseRow> rows = xmlaService.discover().mdSchemaDimensions(request, metaData);

        SoapUtil.toMdSchemaDimensions(rows, body);
    }

    private void handleDiscoverProperties(RequestMetaData metaData,
            PropertiesR propertiesR, SOAPElement restrictionElement, SOAPBody body) throws SOAPException {

        DiscoverPropertiesRestrictionsR restrictionsR = Convert.discoverPropertiesRestrictions(restrictionElement);
        DiscoverPropertiesRequest request = new DiscoverPropertiesRequestR(propertiesR, restrictionsR);
        List<DiscoverPropertiesResponseRow> rows = xmlaService.discover().discoverProperties(request, metaData);

        SoapUtil.toDiscoverProperties(rows, body);
    }

    private void handleMdSchemaFunctions(RequestMetaData metaData, PropertiesR propertiesR,
            SOAPElement restrictionElement, SOAPBody body) throws SOAPException {

        MdSchemaFunctionsRestrictionsR restrictionsR = Convert
                .discoverMdSchemaFunctionsRestrictions(restrictionElement);
        MdSchemaFunctionsRequest request = new MdSchemaFunctionsRequestR(propertiesR, restrictionsR);
        List<MdSchemaFunctionsResponseRow> rows = xmlaService.discover().mdSchemaFunctions(request, metaData);

        SoapUtil.toMdSchemaFunctions(rows, body);
    }

    private void handleStatement(RequestMetaData metaData, UserRolePrincipal userPrincipal, StatementR statement,
            PropertiesR properties, List<ExecuteParameter> parameters, SOAPBody responseBody) throws SOAPException {
        String sessionId = metaData != null && metaData.sessionId() != null && metaData.sessionId().isPresent()
                ? metaData.sessionId().get()
                : null;
        StatementRequest statementRequest = new StatementRequestR(properties, parameters, statement, sessionId);
        StatementResponse statementResponse = xmlaService.execute().statement(statementRequest, metaData,
                userPrincipal);
        SoapUtil.toStatementResponse(statementResponse, responseBody);
    }

    private void handleAlter(RequestMetaData metaData, UserRolePrincipal userPrincipal, AlterR alter,
            PropertiesR properties, List<ExecuteParameter> parameters, SOAPBody responseBody) throws SOAPException {
        AlterRequest alterRequest = new AlterRequestR(properties, parameters, alter);
        AlterResponse alterResponse = xmlaService.execute().alter(alterRequest, metaData, userPrincipal);
        SoapUtil.toAlterResponse(alterResponse, responseBody);
    }

    private void handleClearCache(RequestMetaData metaData, UserRolePrincipal userPrincipal, ClearCacheR clearCache,
            PropertiesR properties, List<ExecuteParameter> parameters, SOAPBody responseBody) throws SOAPException {
        ClearCacheRequest clearCacheRequest = new ClearCacheRequestR(properties, parameters, clearCache);
        ClearCacheResponse clearCacheResponse = xmlaService.execute().clearCache(clearCacheRequest, metaData,
                userPrincipal);
        SoapUtil.toClearCacheResponse(clearCacheResponse, responseBody);
    }

    private void handleCancel(RequestMetaData metaData, UserRolePrincipal userPrincipal, CancelR cancel,
            PropertiesR properties, List<ExecuteParameter> parameters, SOAPBody responseBody) throws SOAPException {
        CancelRequest cancelRequest = new CancelRequestR(properties, parameters, cancel);
        CancelResponse cancelResponse = xmlaService.execute().cancel(cancelRequest, metaData, userPrincipal);
        SoapUtil.toCancelResponse(cancelResponse, responseBody);
    }

}
