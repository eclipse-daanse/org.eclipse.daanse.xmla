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
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ASSEMBLY2;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.CREATED_TIMESTAMP;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.DESCRIPTION;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.ID;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.LAST_SCHEMA_UPDATE;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants.NAME;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.getListByLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.matchesLocalName;
import static org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlNodeHelper.toInstant;

import java.time.Instant;
import java.util.List;

import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.model.record.xmla.AssemblyR;
import org.w3c.dom.NodeList;

/**
 * Converter for Assembly types from XML NodeList.
 */
public class AssemblyConverter {

    private AssemblyConverter() {
        // utility class
    }

    public static List<Assembly> getAssemblyList(NodeList nl) {
        return getListByLocalName(nl, ASSEMBLY2, AssemblyConverter::getAssembly);
    }

    public static Assembly getAssembly(NodeList nl) {
        String id = null;
        String name = null;
        Instant createdTimestamp = null;
        Instant lastSchemaUpdate = null;
        String description = null;
        List<Annotation> annotations = null;
        ImpersonationInfo impersonationInfo = null;
        for (int i = 0; i < nl.getLength(); i++) {
            org.w3c.dom.Node node = nl.item(i);
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
            if (matchesLocalName(node, "ImpersonationInfo")) {
                impersonationInfo = DataSourceConverter.getImpersonationInfo(node.getChildNodes());
            }
        }
        return new AssemblyR(id, name, createdTimestamp, lastSchemaUpdate, description, annotations, impersonationInfo);
    }
}
