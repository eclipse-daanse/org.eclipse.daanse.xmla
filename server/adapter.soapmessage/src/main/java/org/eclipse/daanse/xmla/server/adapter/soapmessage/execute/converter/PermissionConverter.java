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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.PROCESS;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.READ_DEFINITION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ROLE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.WRITE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBoolean;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.DataSourcePermission;
import org.eclipse.daanse.xmla.api.xmla.DatabasePermission;
import org.eclipse.daanse.xmla.api.xmla.Permission;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourcePermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.DatabasePermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.PermissionR;
import org.w3c.dom.NodeList;

/**
 * Converter for Permission and related types from XML NodeList.
 */
public class PermissionConverter {

    private PermissionConverter() {
        // utility class
    }

    public static Permission getPermission(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
            }
        }
        return new PermissionR(name, Optional.ofNullable(id), Optional.ofNullable(createdTimestamp),
                Optional.ofNullable(lastSchemaUpdate), Optional.ofNullable(description),
                Optional.ofNullable(annotations), roleID, Optional.ofNullable(process),
                Optional.ofNullable(readDefinition), Optional.ofNullable(read), Optional.ofNullable(write));
    }

    public static List<DataSourcePermission> getDataSourcePermissionList(NodeList nl) {
        List<DataSourcePermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("DataSourcePermission".equals(node.getNodeName()))) {
                list.add(getDataSourcePermission(node.getChildNodes()));
            }
        }
        return list;
    }

    public static DataSourcePermission getDataSourcePermission(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;

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
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
            }
        }
        return new DataSourcePermissionR(name, Optional.ofNullable(id), Optional.ofNullable(createdTimestamp),
                Optional.ofNullable(lastSchemaUpdate), Optional.ofNullable(description),
                Optional.ofNullable(annotations), roleID, Optional.ofNullable(process),
                Optional.ofNullable(readDefinition), Optional.ofNullable(read), Optional.ofNullable(write));
    }

    public static List<DatabasePermission> getDatabasePermissionList(NodeList nl) {
        List<DatabasePermission> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("DatabasePermission".equals(node.getNodeName()))) {
                list.add(getDatabasePermission(node.getChildNodes()));
            }
        }
        return list;
    }

    public static DatabasePermission getDatabasePermission(NodeList nl) {
        Boolean administer = null;
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String roleID = null;
        Boolean process = null;
        ReadDefinitionEnum readDefinition = null;
        ReadWritePermissionEnum read = null;
        ReadWritePermissionEnum write = null;

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
                if (ROLE_ID.equals(node.getNodeName())) {
                    roleID = node.getTextContent();
                }
                if (PROCESS.equals(node.getNodeName())) {
                    process = toBoolean(node.getTextContent());
                }
                if (READ_DEFINITION.equals(node.getNodeName())) {
                    readDefinition = ReadDefinitionEnum.fromValue(node.getTextContent());
                }
                if ("Read".equals(node.getNodeName())) {
                    read = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if (WRITE.equals(node.getNodeName())) {
                    write = ReadWritePermissionEnum.fromValue(node.getTextContent());
                }
                if ("Administer".equals(node.getNodeName())) {
                    administer = toBoolean(node.getTextContent());
                }
            }
        }
        return new DatabasePermissionR(Optional.ofNullable(administer), name, Optional.ofNullable(id),
                Optional.ofNullable(createdTimestamp), Optional.ofNullable(lastSchemaUpdate),
                Optional.ofNullable(description), Optional.ofNullable(annotations), roleID,
                Optional.ofNullable(process), Optional.ofNullable(readDefinition), Optional.ofNullable(read),
                Optional.ofNullable(write));
    }
}
