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
*/
package org.eclipse.daanse.xmla.server.jakarta.saaj.impl;

import org.eclipse.daanse.xmla.server.jakarta.saaj.api.Constants;
import org.osgi.service.metatype.annotations.AttributeDefinition;

public interface ServletOCD {

    String L10N_SERVLET_NAME_NAME = XmlaServletOCD.L10N_PREFIX + Constants.SERVLET_NAME_PROPERTY
            + XmlaServletOCD.L10N_POSTFIX_NAME;
    String L10N_SERVLET_NAME_DESCRIPTION = XmlaServletOCD.L10N_PREFIX + Constants.SERVLET_NAME_PROPERTY
            + XmlaServletOCD.L10N_POSTFIX_DESCRIPTION;

    String L10N_SERVLET_CONTEXT_SELECT_NAME = XmlaServletOCD.L10N_PREFIX + Constants.SERVLET_CONTEXT_SELECT_PROPERTY
            + XmlaServletOCD.L10N_POSTFIX_NAME;

    String L10N_SERVLET_CONTEXT_SELECT_DESCRIPTION = XmlaServletOCD.L10N_PREFIX
            + Constants.SERVLET_CONTEXT_SELECT_PROPERTY + XmlaServletOCD.L10N_POSTFIX_DESCRIPTION;

    String L10N_SERVLET_PATTERN_NAME = XmlaServletOCD.L10N_PREFIX + Constants.SERVLET_PATTERN_PROPERTY
            + XmlaServletOCD.L10N_POSTFIX_NAME;
    String L10N_SERVLET_PATTERN_DESCRIPTION = XmlaServletOCD.L10N_PREFIX + Constants.SERVLET_PATTERN_PROPERTY
            + XmlaServletOCD.L10N_POSTFIX_DESCRIPTION;

    @AttributeDefinition(name = L10N_SERVLET_NAME_NAME, description = L10N_SERVLET_NAME_DESCRIPTION, defaultValue = Constants.DEFAULT_SERVLET_NAME)
    default String osgi_http_whiteboard_servlet_name() {
        return Constants.DEFAULT_SERVLET_NAME;
    }

    @AttributeDefinition(name = L10N_SERVLET_PATTERN_NAME, description = L10N_SERVLET_PATTERN_DESCRIPTION, defaultValue = Constants.DEFAULT_SERVLET_PATTERN)
    default String osgi_http_whiteboard_servlet_pattern() {
        return Constants.DEFAULT_SERVLET_PATTERN;
    }

    @AttributeDefinition(name = L10N_SERVLET_CONTEXT_SELECT_NAME, description = L10N_SERVLET_CONTEXT_SELECT_DESCRIPTION, required = false)
    String osgi_http_whiteboard_context_select();

}
