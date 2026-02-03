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

import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.AGGREGATION_DESIGN_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ALLOW_CREATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ALTER;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ASSEMBLY_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CANCEL;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CLEAR_CACHE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CUBE_PERMISSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATABASE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATABASE_PERMISSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_SOURCE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_SOURCE_PERMISSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_SOURCE_VIEW_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DIMENSION_PERMISSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MDX_SCRIPT_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MEASURE_GROUP_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MINING_MODEL_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MINING_MODEL_PERMISSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MINING_STRUCTURE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.MINING_STRUCTURE_PERMISSION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.OBJECT_DEFINITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.OBJECT_EXPANSION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PARTITION_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PERSPECTIVE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.SERVER_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.STATEMENT;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.TRACE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.nodeListToMap;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBigInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.MajorObject;
import org.eclipse.daanse.xmla.api.xmla.ObjectExpansion;
import org.eclipse.daanse.xmla.api.xmla.ObjectReference;
import org.eclipse.daanse.xmla.api.xmla.Scope;
import org.eclipse.daanse.xmla.model.record.xmla.AlterR;
import org.eclipse.daanse.xmla.model.record.xmla.CancelR;
import org.eclipse.daanse.xmla.model.record.xmla.ClearCacheR;
import org.eclipse.daanse.xmla.model.record.xmla.ObjectReferenceR;
import org.eclipse.daanse.xmla.model.record.xmla.StatementR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaParseException;
import org.w3c.dom.NodeList;

import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPElement;

/**
 * Parser for XMLA Command elements. Handles Statement, Alter, Cancel, and
 * ClearCache commands.
 */
public class CommandParser {

    // Command-specific constants (not in Constants.java)
    private static final String OBJECT_REFERENCE = "Object";
    private static final String COMMAND_SCOPE = "Scope";
    private static final String CONNECTION_ID = "ConnectionID";
    private static final String SESSION_ID = "SessionID";
    private static final String SPID = "SPID";
    private static final String CANCEL_ASSOCIATED = "CancelAssociated";

    private CommandParser() {
        // utility class
    }

    /**
     * Parse a Command from a SOAPElement.
     */
    public static Command commandToCommand(SOAPElement element, MajorObjectParser majorObjectParser) {
        Iterator<Node> nodeIterator = element.getChildElements();
        while (nodeIterator.hasNext()) {
            Node n = nodeIterator.next();
            if (n instanceof SOAPElement) {
                Command command = getCommand(n, majorObjectParser);
                if (command != null) {
                    return command;
                }
            }
        }
        return null;
    }

    public static Command getCommand(NodeList nl, MajorObjectParser majorObjectParser) {
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node n = nl.item(i);
            Command command = getCommand(n, majorObjectParser);
            if (command != null) {
                return command;
            }
        }
        throw new XmlaParseException("Illegal command");
    }

    static Command getCommand(org.w3c.dom.Node n, MajorObjectParser majorObjectParser) {
        if (matchesLocalName(n, STATEMENT)) {
            return new StatementR(n.getTextContent());
        }
        if (matchesLocalName(n, ALTER)) {
            return getAlterCommand(n.getChildNodes(), majorObjectParser);
        }
        if (matchesLocalName(n, CLEAR_CACHE)) {
            return getClearCacheCommand(n.getChildNodes());
        }
        if (matchesLocalName(n, CANCEL)) {
            return getCancelCommand(n.getChildNodes());
        }
        return null;
    }

    static Command getCancelCommand(NodeList nl) {
        Map<String, String> map = nodeListToMap(nl);
        BigInteger connectionID = toBigInteger(map.get(CONNECTION_ID));
        String sessionID = map.get(SESSION_ID);
        BigInteger spid = toBigInteger(map.get(SPID));
        Boolean cancelAssociated = toBoolean(map.get(CANCEL_ASSOCIATED));

        return new CancelR(connectionID, sessionID, spid, cancelAssociated);
    }

    static Command getClearCacheCommand(NodeList nl) {
        ObjectReference object = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (matchesLocalName(node, OBJECT_REFERENCE)) {
                object = getObjectReference(node.getChildNodes());
                break;
            }
        }
        return new ClearCacheR(object);
    }

    static Command getAlterCommand(NodeList nl, MajorObjectParser majorObjectParser) {
        ObjectReference object = null;
        MajorObject objectDefinition = null;
        Scope scope = null;
        Boolean allowCreate = null;
        ObjectExpansion objectExpansion = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (matchesLocalName(node, OBJECT_REFERENCE)) {
                    object = getObjectReference(node.getChildNodes());
                }
                if (matchesLocalName(node, OBJECT_DEFINITION)) {
                    objectDefinition = majorObjectParser.getMajorObject(node.getChildNodes());
                }
                if (matchesLocalName(node, COMMAND_SCOPE)) {
                    scope = Scope.fromValue(node.getTextContent());
                }
                if (matchesLocalName(node, ALLOW_CREATE)) {
                    allowCreate = toBoolean(node.getTextContent());
                }
                if (matchesLocalName(node, OBJECT_EXPANSION)) {
                    objectExpansion = ObjectExpansion.fromValue(node.getTextContent());
                }
            }
        }
        return new AlterR(object, objectDefinition, scope, allowCreate, objectExpansion);
    }

    static ObjectReference getObjectReference(NodeList nl) {
        Map<String, String> map = nodeListToMap(nl);
        return new ObjectReferenceR(map.get(SERVER_ID), map.get(DATABASE_ID), map.get(ROLE_ID), map.get(TRACE_ID),
                map.get(ASSEMBLY_ID), map.get(DIMENSION_ID), map.get(DIMENSION_PERMISSION_ID), map.get(DATA_SOURCE_ID),
                map.get(DATA_SOURCE_PERMISSION_ID), map.get(DATABASE_PERMISSION_ID), map.get(DATA_SOURCE_VIEW_ID),
                map.get(CUBE_ID), map.get(MINING_STRUCTURE_ID), map.get(MEASURE_GROUP_ID), map.get(PERSPECTIVE_ID),
                map.get(CUBE_PERMISSION_ID), map.get(MDX_SCRIPT_ID), map.get(PARTITION_ID),
                map.get(AGGREGATION_DESIGN_ID), map.get(MINING_MODEL_ID), map.get(MINING_MODEL_PERMISSION_ID),
                map.get(MINING_STRUCTURE_PERMISSION_ID));
    }

    /**
     * Functional interface for parsing MajorObject. This allows CommandParser to
     * delegate complex object parsing back to the main parser.
     */
    @FunctionalInterface
    public interface MajorObjectParser {
        MajorObject getMajorObject(NodeList nl);
    }
}
