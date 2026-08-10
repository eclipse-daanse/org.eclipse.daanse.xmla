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
package org.eclipse.daanse.xmla.server.whiteboard.servlet.api;

import org.osgi.framework.Bundle;

/**
 * Constants of this {@link Bundle}.
 */
public class Constants {

    private Constants() {
    }

    /**
     * Constant for the {@link org.osgi.framework.Constants#SERVICE_PID} of the XMLA
     * servlet. Where the endpoint answers is the whiteboard's business, so a
     * configuration carries {@code osgi.http.whiteboard.servlet.pattern} alongside
     * the properties defined here.
     */
    public static final String PID_SERVLET = "org.eclipse.daanse.xmla.server.whiteboard.servlet.XmlaServlet";

    /** Constant for the property that restricts anonymous callers. */
    public static final String SERVLET_PROPERTY_REQUIRE_PRINCIPAL = "requirePrincipal";

    /** Constant for the property naming the rowsets anonymous callers keep. */
    public static final String SERVLET_PROPERTY_ANONYMOUS_ROWSETS = "anonymousRowsets";

}
