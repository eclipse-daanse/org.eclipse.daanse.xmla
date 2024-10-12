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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import static org.eclipse.daanse.xmla.server.jakarta.jws.AnnotationConvertor.convertAnnotationList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertToInstant;
import static org.eclipse.daanse.xmla.server.jakarta.jws.DatabaseConvertor.convertAssemblyList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.DatabaseConvertor.convertDatabaseList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.RoleConvertor.convertRoleList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.TraceConvertor.convertTraceList;

import java.util.List;

import org.eclipse.daanse.xmla.api.xmla.Server;
import org.eclipse.daanse.xmla.api.xmla.ServerProperty;
import org.eclipse.daanse.xmla.model.record.xmla.ServerPropertyR;
import org.eclipse.daanse.xmla.model.record.xmla.ServerR;

public class ServerConvertor {

    private ServerConvertor() {
    }
    public static Server convertServer(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Server server) {
        if (server != null) {
            return new ServerR(server.getName(),
                server.getID(),
                convertToInstant(server.getCreatedTimestamp()),
                convertToInstant(server.getLastSchemaUpdate()),
                server.getDescription(),
                convertAnnotationList(server.getAnnotations() == null ? null : server.getAnnotations()),
                server.getProductName(),
                server.getEdition(),
                server.getEditionID(),
                server.getVersion(),
                server.getServerMode(),
                server.getProductLevel(),
                server.getDefaultCompatibilityLevel(),
                server.getSupportedCompatibilityLevels(),
                convertDatabaseList(server.getDatabases()),
                convertAssemblyList(server.getAssemblies()),
                convertTraceList(server.getTraces()),
                convertRoleList(server.getRoles()),
                convertServerPropertyList(server.getServerProperties()));
        }
        return null;
    }

    private static List<ServerProperty> convertServerPropertyList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ServerProperty> list) {
        if (list != null) {
            return list.stream().map(ServerConvertor::convertServerProperty).toList();
        }
        return List.of();

    }

    private static ServerProperty convertServerProperty(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ServerProperty serverProperty) {
        if (serverProperty != null) {
            return new ServerPropertyR(serverProperty.getName(),
                serverProperty.getValue(),
                serverProperty.isRequiresRestart(),
                serverProperty.getPendingValue(),
                serverProperty.getDefaultValue(),
                serverProperty.isDisplayFlag(),
                serverProperty.getType());
        }
        return null;
    }

}
