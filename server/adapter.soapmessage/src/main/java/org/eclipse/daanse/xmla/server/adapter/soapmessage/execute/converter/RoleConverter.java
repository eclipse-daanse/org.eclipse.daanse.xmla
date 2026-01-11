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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Member;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.model.record.xmla.MemberR;
import org.eclipse.daanse.xmla.model.record.xmla.RoleR;
import org.w3c.dom.NodeList;

/**
 * Converter for Role and Member types from XML NodeList.
 */
public class RoleConverter {

    private RoleConverter() {
        // utility class
    }

    public static List<Role> getRoleList(NodeList nl, String elementName) {
        List<Role> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && (elementName.equals(node.getNodeName()))) {
                list.add(getRole(node.getChildNodes()));
            }
        }
        return list;
    }

    public static Role getRole(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        List<Member> members = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if (ID.equals(node.getNodeName())) {
                    id = node.getTextContent();
                }
                if (CREATED_TIMESTAMP.equals(node.getNodeName())) {
                    createdTimestamp = toInstant(node.getTextContent());
                }
                if (LAST_SCHEMA_UPDATE.equals(node.getNodeName())) {
                    lastSchemaUpdate = toInstant(node.getTextContent());
                }
                if (DESCRIPTION.equals(node.getNodeName())) {
                    description = node.getTextContent();
                }
                if (ANNOTATIONS.equals(node.getNodeName())) {
                    annotations = CommonConverter.getAnnotationList(node.getChildNodes());
                }
                if ("Members".equals(node.getNodeName())) {
                    members = getMemberList(node.getChildNodes());
                }
            }
        }
        return new RoleR(name, Optional.ofNullable(id), Optional.ofNullable(createdTimestamp),
                Optional.ofNullable(lastSchemaUpdate), Optional.ofNullable(description),
                Optional.ofNullable(annotations), Optional.ofNullable(members));
    }

    public static List<Member> getMemberList(NodeList nl) {
        List<Member> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("Member".equals(node.getNodeName()))) {
                list.add(getMember(node.getChildNodes()));
            }
        }
        return list;
    }

    public static Member getMember(NodeList nl) {
        String name = null;
        String sid = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (NAME.equals(node.getNodeName())) {
                    name = node.getTextContent();
                }
                if ("Sid".equals(node.getNodeName())) {
                    sid = node.getTextContent();
                }
            }
        }
        return new MemberR(Optional.ofNullable(name), Optional.ofNullable(sid));
    }
}
