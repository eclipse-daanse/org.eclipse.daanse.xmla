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
package org.eclipse.daanse.xmla.client.soapmessage;

import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;

import org.w3c.dom.NodeList;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;

public class SoapClient {

    private static final String XMLA_NS = "urn:schemas-microsoft-com:xml-analysis";
    private static final String SESSION_ID_ATTRIBUTE = "SessionId";

    public SoapClient(String soapEndpointUrl) {
        this.soapEndpointUrl = soapEndpointUrl;
    }

    private String soapEndpointUrl;
    /** XMLA session state: id assigned by the server after BeginSession. */
    private volatile String sessionId;
    private volatile boolean beginSessionRequested;
    private volatile boolean endSessionRequested;
    public static final Logger logger = LoggerFactory.getLogger(SoapClient.class);

    /** Next request carries a BeginSession header; the returned SessionId is kept. */
    public void requestBeginSession() {
        this.beginSessionRequested = true;
    }

    /** Next request carries an EndSession header; the session id is dropped afterwards. */
    public void requestEndSession() {
        this.endSessionRequested = true;
    }

    /** The XMLA session id assigned by the server, or null when sessionless. */
    public String sessionId() {
        return sessionId;
    }

    private void applySessionHeader(SOAPMessage message) throws SOAPException {
        SOAPHeader header = message.getSOAPHeader();
        if (header == null) {
            header = message.getSOAPPart().getEnvelope().addHeader();
        }
        if (endSessionRequested && sessionId != null) {
            SOAPHeaderElement element = header.addHeaderElement(new QName(XMLA_NS, "EndSession"));
            element.addAttribute(new QName(SESSION_ID_ATTRIBUTE), sessionId);
            endSessionRequested = false;
            sessionId = null;
        } else if (sessionId != null) {
            SOAPHeaderElement element = header.addHeaderElement(new QName(XMLA_NS, "Session"));
            element.addAttribute(new QName(SESSION_ID_ATTRIBUTE), sessionId);
        } else if (beginSessionRequested) {
            header.addHeaderElement(new QName(XMLA_NS, "BeginSession"));
            beginSessionRequested = false;
        }
    }

    private void captureSessionId(SOAPMessage response) {
        try {
            if (response == null || response.getSOAPHeader() == null) {
                return;
            }
            NodeList sessions = response.getSOAPHeader().getElementsByTagNameNS(XMLA_NS, "Session");
            if (sessions != null && sessions.getLength() > 0) {
                var attribute = sessions.item(0).getAttributes().getNamedItem(SESSION_ID_ATTRIBUTE);
                if (attribute != null && attribute.getNodeValue() != null) {
                    this.sessionId = attribute.getNodeValue();
                }
            }
        } catch (SOAPException e) {
            logger.debug("Could not read session header from response: {}", e.getMessage());
        }
    }

    public SOAPMessage callSoapWebService(Optional<String> oSoapAction, Consumer<SOAPMessage> consumer)
            throws SOAPException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();

        consumer.accept(message);
        applySessionHeader(message);

        MimeHeaders headers = message.getMimeHeaders();
        oSoapAction.ifPresent(actionName -> headers.addHeader("SOAPAction", actionName));
        message.saveChanges();

        /* Print the request message, just for debugging purposes */
        logger.error("Request SOAP Message:");

        // Create SOAP Connection

        SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = connectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        SOAPMessage response = connection.call(message, soapEndpointUrl);
        captureSessionId(response);

        // Print the SOAP Response

        logger.debug("Response SOAP Message:");

        connection.close();

        return response;

    }

}
