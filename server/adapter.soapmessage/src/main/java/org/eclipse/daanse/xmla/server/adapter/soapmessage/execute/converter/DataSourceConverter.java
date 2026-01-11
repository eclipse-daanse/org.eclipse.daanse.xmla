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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DATA_SOURCE_ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.nodeListToMap;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toBigInteger;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toDuration;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourcePermission;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.DataSourceViewBinding;
import org.eclipse.daanse.xmla.model.record.engine.ImpersonationInfoR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourceR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourceViewBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourceViewR;
import org.w3c.dom.NodeList;

/**
 * Converter for DataSource and DataSourceView types from XML NodeList.
 */
public class DataSourceConverter {

    private DataSourceConverter() {
        // utility class
    }

    public static DataSourceViewBinding getDataSourceViewBinding(NodeList childNodes) {
        Map<String, String> map = nodeListToMap(childNodes);
        return new DataSourceViewBindingR(map.get("DataSourceViewID"));
    }

    public static List<DataSourceView> getDataSourceViewList(NodeList nl) {
        List<DataSourceView> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("DataSourceView".equals(node.getNodeName()))) {
                list.add(getDataSourceView(node.getChildNodes()));
            }
        }
        return list;
    }

    public static DataSourceView getDataSourceView(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String dataSourceID = null;

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
                if (DATA_SOURCE_ID.equals(node.getNodeName())) {
                    dataSourceID = node.getTextContent();
                }
            }
        }
        return new DataSourceViewR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations,
                dataSourceID);
    }

    public static List<DataSource> getDataSourceList(NodeList nl) {
        List<DataSource> list = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if ((node != null) && ("DataSource".equals(node.getNodeName()))) {
                list.add(getDataSource(node.getChildNodes()));
            }
        }
        return list;
    }

    public static DataSource getDataSource(NodeList nl) {
        String name = null;
        String id = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        String managedProvider = null;
        String connectionString = null;
        String connectionStringSecurity = null;
        ImpersonationInfo impersonationInfo = null;
        String isolation = null;
        BigInteger maxActiveConnections = null;
        Duration timeout = null;
        List<DataSourcePermission> dataSourcePermissions = null;
        ImpersonationInfo queryImpersonationInfo = null;
        String queryHints = null;

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
                if ("ManagedProvider".equals(node.getNodeName())) {
                    managedProvider = node.getTextContent();
                }
                if ("ConnectionString".equals(node.getNodeName())) {
                    connectionString = node.getTextContent();
                }
                if ("ConnectionStringSecurity".equals(node.getNodeName())) {
                    connectionStringSecurity = node.getTextContent();
                }
                if ("ImpersonationInfo".equals(node.getNodeName())) {
                    impersonationInfo = getImpersonationInfo(node.getChildNodes());
                }
                if ("Isolation".equals(node.getNodeName())) {
                    isolation = node.getTextContent();
                }
                if ("MaxActiveConnections".equals(node.getNodeName())) {
                    maxActiveConnections = toBigInteger(node.getTextContent());
                }
                if ("Timeout".equals(node.getNodeName())) {
                    timeout = toDuration(node.getTextContent());
                }
                if ("DataSourcePermissions".equals(node.getNodeName())) {
                    dataSourcePermissions = PermissionConverter.getDataSourcePermissionList(node.getChildNodes());
                }
                if ("QueryImpersonationInfo".equals(node.getNodeName())) {
                    queryImpersonationInfo = getImpersonationInfo(node.getChildNodes());
                }
                if ("QueryHints".equals(node.getNodeName())) {
                    queryHints = node.getTextContent();
                }
            }
        }
        return new DataSourceR(name, id, createdTimestamp, lastSchemaUpdate, description, annotations, managedProvider,
                connectionString, connectionStringSecurity, impersonationInfo, isolation, maxActiveConnections, timeout,
                dataSourcePermissions, queryImpersonationInfo, queryHints);
    }

    public static ImpersonationInfo getImpersonationInfo(NodeList nl) {
        String impersonationMode = null;
        String account = null;
        String password = null;
        String impersonationInfoSecurity = null;

        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
            if (node != null) {
                if ("ImpersonationMode".equals(node.getNodeName())) {
                    impersonationMode = node.getTextContent();
                }
                if ("Account".equals(node.getNodeName())) {
                    account = node.getTextContent();
                }
                if ("Password".equals(node.getNodeName())) {
                    password = node.getTextContent();
                }
                if ("ImpersonationInfoSecurity".equals(node.getNodeName())) {
                    impersonationInfoSecurity = node.getTextContent();
                }
            }
        }

        return new ImpersonationInfoR(impersonationMode, Optional.ofNullable(account), Optional.ofNullable(password),
                Optional.ofNullable(impersonationInfoSecurity));
    }
}
