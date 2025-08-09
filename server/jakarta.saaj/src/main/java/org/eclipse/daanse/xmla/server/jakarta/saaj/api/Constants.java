/*
* Copyright (c) 2024 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.server.jakarta.saaj.api;

import org.osgi.framework.Bundle;

/**
 * Constants of this {@link Bundle}.
 */
public class Constants {

    private Constants() {
    }

    /**
     * Constant for the {@link org.osgi.framework.Constants#SERVICE_PID} of the XMLA Servlet Service.
     */
    public static final String PID_XMLA_SERVLET = "daanse.xmla.server.jakarta.saaj.XmlaServlet";

    /**
     * Constant for the reference attribute name for the XMLA service. This is used as a service filter
     * for selecting the XMLA service.
     */
    public static final String REFERENCE_XMLA_SERVICE = "xmlaService";

    /**
     * Constant for the OSGi HTTP Whiteboard servlet pattern property. Service property specifying the
     * request mappings for a Servlet service. This allows specifying paths or patterns that the servlet
     * should listen to according to the Jakarta Servlet specification.
     */
    public static final String SERVLET_PATTERN_PROPERTY = "osgi.http.whiteboard.servlet.pattern";

    /**
     * Default value for the servlet pattern property.
     */
    public static final String DEFAULT_SERVLET_PATTERN = "/xmla";

    /**
     * Constant for the OSGi HTTP Whiteboard context select property. Service property referencing a
     * ServletContextHelper service. For servlet, listener, servlet filter, or resource services, this
     * service property refers to the associated ServletContextHelper service. The value of this
     * property is a filter expression which is matched against the service registration properties of
     * the ServletContextHelper service. If this service property is not specified, the default context
     * is used.
     */
    public static final String SERVLET_CONTEXT_SELECT_PROPERTY = "osgi.http.whiteboard.context.select";

    /**
     * Constant for the OSGi HTTP Whiteboard servlet name property. Service property specifying the
     * servlet name of a Servlet service. The servlet is registered with this name and the name can be
     * used as a reference to the servlet for filtering or request dispatching. This name is in addition
     * used as the value for the ServletConfig.getServletName() method. If this service property is not
     * specified, the fully qualified name of the service object's class is used as the servlet name.
     * Servlet names should be unique among all servlet services associated with a single
     * ServletContextHelper.
     */
    public static final String SERVLET_NAME_PROPERTY = "osgi.http.whiteboard.servlet.name";

    /**
     * Default value for the servlet name property.
     */
    public static final String DEFAULT_SERVLET_NAME = "XMLA Servlet";

}
