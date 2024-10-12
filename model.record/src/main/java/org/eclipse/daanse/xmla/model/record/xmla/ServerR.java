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
package org.eclipse.daanse.xmla.model.record.xmla;

import java.time.Instant;
import java.util.List;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.api.xmla.Assembly;
import org.eclipse.daanse.xmla.api.xmla.Database;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.api.xmla.Server;
import org.eclipse.daanse.xmla.api.xmla.ServerProperty;
import org.eclipse.daanse.xmla.api.xmla.Trace;

public record ServerR(String name, String id, Instant createdTimestamp, Instant lastSchemaUpdate, String description,
        List<Annotation> annotations, String productName, String edition, Long editionID, String version,
        String serverMode, String productLevel, Long defaultCompatibilityLevel, String supportedCompatibilityLevels,
        List<Database> databases, List<Assembly> assemblies, List<Trace> traces, List<Role> roles,
        List<ServerProperty> serverProperties) implements Server {

}
