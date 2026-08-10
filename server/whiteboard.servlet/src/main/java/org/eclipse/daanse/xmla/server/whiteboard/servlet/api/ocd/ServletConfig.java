/*
* Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.server.whiteboard.servlet.api.ocd;

import org.eclipse.daanse.xmla.server.whiteboard.servlet.api.Constants;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * What the XMLA servlet is configured with. Host, port and path are not here:
 * they belong to the whiteboard, which the {@code osgi.http.whiteboard.*}
 * properties of the same configuration address.
 */
@ObjectClassDefinition(name = ServletConfig.L10N_OCD_SERVLET_NAME, description = ServletConfig.L10N_OCD_SERVLET_DESCRIPTION, localization = ServletConfig.OCD_LOCALIZATION)
public interface ServletConfig {

    String OCD_LOCALIZATION = "OSGI-INF/l10n/org.eclipse.daanse.xmla.server.whiteboard.servlet.ocd";
    String L10N_PREFIX = "%";
    String L10N_POSTFIX_NAME = ".name";
    String L10N_POSTFIX_DESCRIPTION = ".description";

    String L10N_OCD_SERVLET_NAME = L10N_PREFIX + "ocd" + ".servlet" + L10N_POSTFIX_NAME;
    String L10N_OCD_SERVLET_DESCRIPTION = L10N_PREFIX + "ocd" + ".servlet" + L10N_POSTFIX_DESCRIPTION;

    String L10N_REQUIRE_PRINCIPAL_NAME = L10N_PREFIX + Constants.SERVLET_PROPERTY_REQUIRE_PRINCIPAL + L10N_POSTFIX_NAME;
    String L10N_REQUIRE_PRINCIPAL_DESCRIPTION = L10N_PREFIX + Constants.SERVLET_PROPERTY_REQUIRE_PRINCIPAL
            + L10N_POSTFIX_DESCRIPTION;

    String L10N_CONTAINER_ROLES_NAME = "%containerRoles.name";
    String L10N_CONTAINER_ROLES_DESCRIPTION = "%containerRoles.description";
    String L10N_ANONYMOUS_ROWSETS_NAME = L10N_PREFIX + Constants.SERVLET_PROPERTY_ANONYMOUS_ROWSETS + L10N_POSTFIX_NAME;
    String L10N_ANONYMOUS_ROWSETS_DESCRIPTION = L10N_PREFIX + Constants.SERVLET_PROPERTY_ANONYMOUS_ROWSETS
            + L10N_POSTFIX_DESCRIPTION;

    boolean DEFAULT_REQUIRE_PRINCIPAL = false;

    /** What a client needs before it can authenticate meaningfully. */
    String[] DEFAULT_ANONYMOUS_ROWSETS = { "DISCOVER_PROPERTIES", "DISCOVER_DATASOURCES", "DISCOVER_SCHEMA_ROWSETS",
            "DISCOVER_ENUMERATORS", "DISCOVER_KEYWORDS", "DISCOVER_LITERALS" };

    @AttributeDefinition(name = L10N_REQUIRE_PRINCIPAL_NAME, description = L10N_REQUIRE_PRINCIPAL_DESCRIPTION, defaultValue = DEFAULT_REQUIRE_PRINCIPAL
            + "")
    default boolean requirePrincipal() {
        return DEFAULT_REQUIRE_PRINCIPAL;
    }

    @AttributeDefinition(name = L10N_ANONYMOUS_ROWSETS_NAME, description = L10N_ANONYMOUS_ROWSETS_DESCRIPTION, defaultValue = {
            "DISCOVER_PROPERTIES", "DISCOVER_DATASOURCES", "DISCOVER_SCHEMA_ROWSETS", "DISCOVER_ENUMERATORS",
            "DISCOVER_KEYWORDS", "DISCOVER_LITERALS" })
    default String[] anonymousRowsets() {
        return DEFAULT_ANONYMOUS_ROWSETS;
    }

    /**
     * The roles to ask the container about for a caller it authenticated.
     * <p>
     * The Servlet API can only answer whether a caller holds a named role, never
     * which roles they hold, so a role the deployment does not name here cannot be
     * discovered at all.
     */
    @AttributeDefinition(name = L10N_CONTAINER_ROLES_NAME, description = L10N_CONTAINER_ROLES_DESCRIPTION, required = false)
    default String[] containerRoles() {
        return new String[0];
    }
}
