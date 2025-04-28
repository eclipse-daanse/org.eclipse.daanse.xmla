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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executors;

import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaApiAdapter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpServer;

import jakarta.xml.soap.SOAPException;

@Component(scope = ServiceScope.PROTOTYPE, immediate = true)
public class JdkHttpServer {

    private static Logger LOGGER = LoggerFactory.getLogger(JdkHttpServer.class);
    private XmlaApiAdapter wsAdapter;
    private HttpServer server = null;

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    private XmlaService xmlaService;

    @Activate
    public void activate(Map<String, Object> map) throws SOAPException, IOException {
        LOGGER.debug("Starting JDK HTTP server");
        wsAdapter = new XmlaApiAdapter(xmlaService);
        XmlaSoapHttpHandler xmlaHandler = new XmlaSoapHttpHandler(wsAdapter);
        // Register the handler with the HTTP server

        server = HttpServer.create(new InetSocketAddress(8090), 0);
        server.createContext("/xmla", xmlaHandler);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        LOGGER.debug("JDK HTTP server started on port 8090");
    }

    @Deactivate
    public void deativate() {
        LOGGER.debug("Stopping JDK HTTP server");
        server.stop(0);
        LOGGER.debug("JDK HTTP server stopped");
    }

};
