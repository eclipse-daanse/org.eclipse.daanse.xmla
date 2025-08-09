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
package org.eclipse.daanse.xmla.server.jakarta.saaj.impl;

import org.eclipse.daanse.xmla.server.jakarta.saaj.api.Constants;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = XmlaServletOCD.L10N_OCD_NAME, description = XmlaServletOCD.L10N_OCD_DESCRIPTION, localization = XmlaServletOCD.OCD_LOCALIZATION)
public interface XmlaServletOCD extends ServletOCD {

    String OCD_LOCALIZATION = "OSGI-INF/l10n/org.eclipse.daanse.xmla.server.jakarta.saaj.ocd";
    String L10N_PREFIX = "%";
    String L10N_POSTFIX_DESCRIPTION = ".description";
    String L10N_POSTFIX_NAME = ".name";

    String L10N_OCD_NAME = L10N_PREFIX + "ocd.xmlaservlet" + L10N_POSTFIX_NAME;
    String L10N_OCD_DESCRIPTION = L10N_PREFIX + "ocd.xmlaservlet" + L10N_POSTFIX_DESCRIPTION;

    String L10N_XMLA_SERVICE_TARGET_NAME = L10N_PREFIX + Constants.REFERENCE_XMLA_SERVICE + ".target"
            + L10N_POSTFIX_NAME;
    String L10N_XMLA_SERVICE_TARGET_DESCRIPTION = L10N_PREFIX + Constants.REFERENCE_XMLA_SERVICE + ".target"
            + L10N_POSTFIX_DESCRIPTION;

    @AttributeDefinition(name = L10N_XMLA_SERVICE_TARGET_NAME, description = L10N_XMLA_SERVICE_TARGET_DESCRIPTION)
    String xmlaService_target();
}
