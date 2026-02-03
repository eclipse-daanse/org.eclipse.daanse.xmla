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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter;

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ANNOTATIONS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE2;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.VALUE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toLong;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.Database;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.api.xmla.Server;
import org.eclipse.daanse.xmla.api.xmla.ServerProperty;
import org.eclipse.daanse.xmla.api.xmla.Trace;
import org.eclipse.daanse.xmla.model.record.xmla.ServerPropertyR;
import org.eclipse.daanse.xmla.model.record.xmla.ServerR;
import org.w3c.dom.NodeList;

/**
 * Converter for Server and ServerProperty types from XML NodeList.
 */
public class ServerConverter {

    private ServerConverter() {
        // utility class
    }

    public static Server getServer(NodeList nl, Function<NodeList, Command> commandParser,
            Function<NodeList, List<Assembly>> assemblySupplier) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String productName = null;
        String edition = null;
        Long editionID = null;
        String version = null;
        String serverMode = null;
        String productLevel = null;
        Long defaultCompatibilityLevel = null;
        String supportedCompatibilityLevels = null;
        List<Database> databases = null;
        List<Assembly> assemblies = null;
        List<Trace> traces = null;
        List<Role> roles = null;
        List<ServerProperty> serverProperties = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, ID)) {
                    id = node.getTextContent();
                }
                if (matchesLocalName(node, CREATED_TIMESTAMP)) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (matchesLocalName(node, LAST_SCHEMA_UPDATE)) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (matchesLocalName(node, DESCRIPTION)) {
                    description = node.getTextContent();
                }
                if (matchesLocalName(node, ANNOTATIONS)) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if (matchesLocalName(node, "ProductName")) {
                    productName = node.getTextContent();
                }
                if (matchesLocalName(node, "Edition")) {
                    edition = node.getTextContent();
                }
                if (matchesLocalName(node, "EditionID")) {
                    editionID = toLong(node.getTextContent());
                }
                if (matchesLocalName(node, "Version")) {
                    version = node.getTextContent();
                }
                if (matchesLocalName(node, "ServerMode")) {
                    serverMode = node.getTextContent();
                }
                if (matchesLocalName(node, "ProductLevel")) {
                    productLevel = node.getTextContent();
                }
                if (matchesLocalName(node, "DefaultCompatibilityLevel")) {
                    defaultCompatibilityLevel = toLong(node.getTextContent());
                }
                if (matchesLocalName(node, "SupportedCompatibilityLevels")) {
                    supportedCompatibilityLevels = node.getTextContent();
                }
                if (matchesLocalName(node, "Databases")) {
                    databases = DatabaseConverter.getDatabaseList(node.getChildNodes(), commandParser,
                            assemblySupplier);
                }
                if (matchesLocalName(node, "Assemblies")) {
                    assemblies = assemblySupplier.apply(node.getChildNodes());
                }
                if (matchesLocalName(node, "Traces")) {
                    traces = TraceEventConverter.getTraceList(node.getChildNodes());
                }
                if (matchesLocalName(node, "Roles")) {
                    roles = RoleConverter.getRoleList(node.getChildNodes(), ROLE2);
                }
                if (matchesLocalName(node, "ServerProperties")) {
                    serverProperties = getServerPropertyList(node.getChildNodes());
                }
            }
        }
        return new ServerR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, productName, edition,
                editionID, version, serverMode, productLevel, defaultCompatibilityLevel, supportedCompatibilityLevels,
                databases, assemblies, traces, roles, serverProperties);
    }

    public static List<ServerProperty> getServerPropertyList(NodeList nl) {
        List<ServerProperty> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, "ServerProperty")) {
                list.add(getServerProperty(node.getChildNodes()));
            }
        }
        return list;
    }

    public static ServerProperty getServerProperty(NodeList nl) {
        String name = null;
        String value = null;
        Boolean requiresRestart = null;
        java.lang.Object pendingValue = null;
        java.lang.Object defaultValue = null;
        Boolean displayFlag = null;
        String type = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, NAME)) {
                    name = node.getTextContent();
                }
                if (matchesLocalName(node, VALUE)) {
                    value = node.getTextContent();
                }
                if (matchesLocalName(node, "RequiresRestart")) {
                    requiresRestart = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "PendingValue")) {
                    pendingValue = node.getTextContent();
                }
                if (matchesLocalName(node, "DefaultValue")) {
                    defaultValue = node.getTextContent();
                }
                if (matchesLocalName(node, "DisplayFlag")) {
                    displayFlag = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, "Type")) {
                    type = node.getTextContent();
                }
            }
        }
        return new ServerPropertyR(name, value, requiresRestart, pendingValue, defaultValue, displayFlag, type);
    }
}
