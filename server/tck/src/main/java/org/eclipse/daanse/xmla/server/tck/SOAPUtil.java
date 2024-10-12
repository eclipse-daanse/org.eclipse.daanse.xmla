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
package org.eclipse.daanse.xmla.server.tck;

import static org.eclipse.daanse.xmla.server.tck.XMLUtil.pretty;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

public class SOAPUtil {
    private static final Logger logger = LoggerFactory.getLogger(SOAPUtil.class);

    private SOAPUtil() {
        // constructor
    }

    public static SOAPMessage callSoapWebService(String soapEndpointUrl, Optional<String> oSoapAction,
            Consumer<SOAPMessage> consumer) {
        try {

            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage message = messageFactory.createMessage();

            consumer.accept(message);

            MimeHeaders headers = message.getMimeHeaders();
            oSoapAction.ifPresent(actionName -> headers.addHeader("SOAPAction", actionName));
            message.saveChanges();

            /* Print the request message, just for debugging purposes */
            logger.error("Request SOAP Message:\n");
            String errorStr = pretty(string(message));
            logger.error(errorStr);

            // Create SOAP Connection

            SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = connectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage response = connection.call(message, soapEndpointUrl);

            // Print the SOAP Response

            logger.debug("Response SOAP Message:\n");
            String str = pretty(string(response));
            logger.debug(str);

            connection.close();

            return response;
        } catch (Exception e) {
            fail("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n",
                    e);
            return null;
        }
    }

    public static String string(SOAPMessage soapMessage) throws SOAPException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        soapMessage.writeTo(baos);
        return new String(baos.toByteArray());

    }

    public static Consumer<SOAPMessage> envelop(String xmlString) {

        return plainSoapMessage -> {

            try {
                SOAPPart soapPart = plainSoapMessage.getSOAPPart();
                plainSoapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "false");
                SOAPEnvelope envelope = soapPart.getEnvelope();
                // SOAP Body
                SOAPBody soapBody = envelope.getBody();
                soapBody.addDocument(XMLUtil.stringToDocument(xmlString));

            } catch (SOAPException e) {
                fail(e);
            }
        };
    }
}
