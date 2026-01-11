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
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserRolePrincipal;
import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.api.xmla.Session;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.discover.DiscoverDispatcher;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.ExecuteDispatcher;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.session.SessionDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

/**
 * Main adapter for XMLA SOAP requests. Routes requests to appropriate
 * dispatchers for Session, Discover and Execute operations.
 */
public class XmlaApiAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlaApiAdapter.class);

    private final SessionDispatcher sessionDispatcher;
    private final DiscoverDispatcher discoverDispatcher;
    private final ExecuteDispatcher executeDispatcher;

    public XmlaApiAdapter(XmlaService xmlaService) {
        this.sessionDispatcher = new SessionDispatcher(xmlaService.session());
        this.discoverDispatcher = new DiscoverDispatcher(xmlaService.discover());
        this.executeDispatcher = new ExecuteDispatcher(xmlaService.execute());
    }

    public SOAPMessage handleRequest(SOAPMessage messageRequest, Map<String, Object> headers, Principal principal,
            Function<String, Boolean> isUserInRoleFunction, String url) {
        try {
            SOAPMessage messageResponse = MessageFactory.newInstance().createMessage();
            messageResponse.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
            SOAPPart soapPartResponse = messageResponse.getSOAPPart();
            SOAPEnvelope envelopeResponse = soapPartResponse.getEnvelope();

            UserRolePrincipal userPrincipal = createUserPrincipal(principal, isUserInRoleFunction);

            // Process session headers
            Optional<Session> oSession = sessionDispatcher.processSessionHeaders(messageRequest.getSOAPHeader(),
                    userPrincipal);

            // Add session response header
            SOAPHeader responseHeader = envelopeResponse.getHeader();
            sessionDispatcher.addSessionResponseHeader(responseHeader, oSession);

            RequestMetaData metaData = RequestMetaDataUtils.getRequestMetaData(headers, oSession, url);
            SOAPBody bodyResponse = envelopeResponse.getBody();
            handleBody(messageRequest.getSOAPBody(), bodyResponse, metaData, userPrincipal);
            return messageResponse;
        } catch (SOAPException e) {
            LOGGER.error("handleRequest error", e);
        }
        return null;
    }

    private UserRolePrincipal createUserPrincipal(Principal principal, Function<String, Boolean> isUserInRoleFunction) {
        return new UserRolePrincipal() {
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
    }

    private void handleBody(SOAPBody body, SOAPBody responseBody, RequestMetaData metaData,
            UserRolePrincipal userPrincipal) throws SOAPException {
        SOAPElement node = null;

        Iterator<Node> nodeIterator = body.getChildElements();
        while (nodeIterator.hasNext()) {
            Node nodeN = nodeIterator.next();
            if (nodeN instanceof SOAPElement soapElement) {
                node = soapElement;
                break;
            }
        }

        if (node != null && Constants.MSXMLA.QN_DISCOVER.equals(node.getElementQName())) {
            discoverDispatcher.dispatch(node, responseBody, metaData);
        }

        if (node != null && Constants.MSXMLA.QN_EXECUTE.equals(node.getElementQName())) {
            executeDispatcher.dispatch(node, responseBody, metaData, userPrincipal);
        }
    }
}
