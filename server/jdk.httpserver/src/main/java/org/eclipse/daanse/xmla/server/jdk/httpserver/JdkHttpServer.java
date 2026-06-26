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
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaApiAdapter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
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

    @ObjectClassDefinition
    @interface Config {
        int port() default 8090;
        String contextPath() default "/xmla";
        int backlog() default 0;
        int maxThreads() default 50;
        int stopDelaySeconds() default 5;
    }

    @Activate
    public void activate(Config config) throws SOAPException, IOException {
    	LOGGER.debug("Starting JDK HTTP server");
        wsAdapter = new XmlaApiAdapter(xmlaService);
        server = HttpServer.create(new InetSocketAddress(config.port()), config.backlog());
        server.createContext(config.contextPath(), new XmlaSoapHttpHandler(wsAdapter));
        server.setExecutor(new ThreadPoolExecutor(
                0, config.maxThreads(), 60L, TimeUnit.SECONDS,
                new SynchronousQueue<>()));
        server.start();
        LOGGER.debug("JDK HTTP server started on port {}", config.port());
    }

    @Deactivate
    public void deactivate(Config config) {
        LOGGER.debug("Stopping JDK HTTP server");
        if (server != null) {
            server.stop(config.stopDelaySeconds());
            server = null;
        }
        LOGGER.debug("JDK HTTP server stopped");
    }

};
