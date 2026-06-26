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
package org.eclipse.daanse.xmla.server.jdk.httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeader;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractSoapHttpHandler implements HttpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSoapHttpHandler.class);
    private static final MessageFactory MF = createMessageFactory();
    private static final String HEADER_DELIMITER = ",";


    public AbstractSoapHttpHandler() throws SOAPException {
    }

    protected abstract SOAPMessage onMessage(SOAPMessage soapRequestMessage);

    @Override
    public final void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            exchange.close();
            return;
        }

        try {
            SOAPMessage requestMessage = createSoapRequest(exchange);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("SOAPMessage in:\n{}", toString(requestMessage));
            }

            SOAPMessage responseMessage = onMessage(requestMessage);

            if (responseMessage != null) {
                writeSoapResponse(exchange, responseMessage);
            } else {
                exchange.sendResponseHeaders(204, -1);
            }
        } catch (Exception ex) {
            LOGGER.error("Error processing SOAP request", ex);
            exchange.sendResponseHeaders(500, -1);
        } finally {
            exchange.close();
        }
    }

    private SOAPMessage createSoapRequest(HttpExchange exchange) throws IOException, SOAPException {
        MimeHeaders mimeHeaders = getMimeHeadersFromExchange(exchange);
        try (InputStream requestStream = exchange.getRequestBody()) {
            return MF.createMessage(mimeHeaders, requestStream);
        }
    }

    private void writeSoapResponse(HttpExchange exchange, SOAPMessage responseMessage)
            throws SOAPException, IOException {

        if (responseMessage.saveRequired()) {
            responseMessage.saveChanges();
        }

        setMimeHeadersToExchange(exchange, responseMessage.getMimeHeaders());


        exchange.sendResponseHeaders(200, 0);
        try (OutputStream os = exchange.getResponseBody()) {
            responseMessage.writeTo(os);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SOAPMessage out:\n{}", toString(responseMessage));
        }
    }

    private static MimeHeaders getMimeHeadersFromExchange(HttpExchange exchange) {
        MimeHeaders mimeHeaders = new MimeHeaders();
        for (Map.Entry<String, List<String>> entry : exchange.getRequestHeaders().entrySet()) {
            for (String value : entry.getValue()) {
                mimeHeaders.addHeader(entry.getKey(), value);
            }
        }
        return mimeHeaders;
    }

    private static void setMimeHeadersToExchange(HttpExchange exchange, MimeHeaders mimeHeaders) {
        Headers resHeaders = exchange.getResponseHeaders();
        Iterator<MimeHeader> it = mimeHeaders.getAllHeaders();

        while (it.hasNext()) {
            MimeHeader header = it.next();
            String[] values = mimeHeaders.getHeader(header.getName());

            if (values.length == 1) {
                resHeaders.set(header.getName(), header.getValue());
            } else {
                StringBuilder sb = new StringBuilder();
                boolean first = true;
                for (String value : values) {
                    if (first) {
                        first = false;
                    } else {
                        sb.append(',');
                    }
                    sb.append(value);
                }
                resHeaders.set(header.getName(), sb.toString());
            }
        }
    }

    private static String toString(SOAPMessage msg) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            msg.writeTo(baos);
            return baos.toString();
        } catch (Exception e) {
            return "<unable to serialise SOAPMessage>";
        }
    }

    private static MessageFactory createMessageFactory() {
        try {
            return MessageFactory.newInstance();
        } catch (SOAPException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

}
