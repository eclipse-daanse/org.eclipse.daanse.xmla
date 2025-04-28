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
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
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
import java.util.StringTokenizer;

public abstract class AbstractSoapHttpHandler implements HttpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSoapHttpHandler.class);
    private static final String HEADER_DELIMITER = ",";

    protected final MessageFactory messageFactory;
    protected final SOAPConnection soapConnection;

    public AbstractSoapHttpHandler() throws SOAPException {
        this.messageFactory = MessageFactory.newInstance();
        this.soapConnection = SOAPConnectionFactory.newInstance().createConnection();
        LOGGER.debug("MessageFactory: {} – SOAPConnection: {}", messageFactory, soapConnection);
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
            return messageFactory.createMessage(mimeHeaders, requestStream);
        }
    }

    private void writeSoapResponse(HttpExchange exchange, SOAPMessage responseMessage)
            throws SOAPException, IOException {

        if (responseMessage.saveRequired()) {
            responseMessage.saveChanges();
        }

        setMimeHeadersToExchange(exchange, responseMessage.getMimeHeaders());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        responseMessage.writeTo(baos);
        byte[] payload = baos.toByteArray();

        exchange.sendResponseHeaders(200, payload.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(payload);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SOAPMessage out:\n{}", toString(responseMessage));
        }
    }

    private static MimeHeaders getMimeHeadersFromExchange(HttpExchange exchange) {
        Headers reqHeaders = exchange.getRequestHeaders();
        MimeHeaders mimeHeaders = new MimeHeaders();

        for (Map.Entry<String, List<String>> entry : reqHeaders.entrySet()) {
            String headerName = entry.getKey();
            for (String rawValue : entry.getValue()) {
                StringTokenizer tokenizer = new StringTokenizer(rawValue, HEADER_DELIMITER);
                while (tokenizer.hasMoreTokens()) {
                    mimeHeaders.addHeader(headerName, tokenizer.nextToken().trim());
                }
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
}
