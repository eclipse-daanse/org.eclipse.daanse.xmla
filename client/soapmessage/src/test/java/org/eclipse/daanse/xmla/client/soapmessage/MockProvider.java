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

import java.io.IOException;
import java.io.StringReader;
import java.util.function.Consumer;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPPart;
//import org.eclipse.daanse.jakarta.xml.ws.api.whiteboard.annotations.RequireSoapWhiteboard;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.Provider;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.ServiceMode;
import jakarta.xml.ws.WebServiceProvider;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.jupiter.api.Assertions.fail;

@WebServiceProvider()
@ServiceMode(value = Service.Mode.MESSAGE)
//@RequireSoapWhiteboard
public class MockProvider implements Provider<SOAPMessage> {
    String response;

    public MockProvider(String response) {
        this.response = response;
    }

    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        try {
            request.writeTo(System.out);
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }
        return getSOAPMessageResponse(consumer(response));
    }

    private SOAPMessage getSOAPMessageResponse(Consumer<SOAPMessage> consumer) {
        try {
            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage message = mf.createMessage();
            consumer.accept(message);
            message.saveChanges();
            return message;
        } catch (SOAPException e) {
            e.printStackTrace();
            fail(e);
        }
        return null;
    }

    private Consumer<SOAPMessage> consumer(String xmlString) {

        return plainSoapMessage -> {

            try {
                SOAPPart soapPart = plainSoapMessage.getSOAPPart();
                plainSoapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "false");
                SOAPEnvelope envelope = soapPart.getEnvelope();
                // SOAP Body
                SOAPBody soapBody = envelope.getBody();
                soapBody.addDocument(stringToDocument(xmlString));

            } catch (SOAPException e) {
                fail(e);
            }
        };
    }

    private static Document stringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setNamespaceAware(true);

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
            fail(e);
        }
        return null;
    }

}
