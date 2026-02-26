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
package org.eclipse.daanse.xmla.server.tck;

import java.io.IOException;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.test.common.dictionary.Dictionaries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
@RequireConfigurationAdmin
public class SaajTestSetup {
    private Logger LOGGER = LoggerFactory.getLogger(SaajTestSetup.class);
    @Reference
    ConfigurationAdmin ca;

    @Activate
    void activate() throws IOException {
        Configuration c = ca.getFactoryConfiguration("daanse.xmla.server.jakarta.saaj.XmlaServlet", "0815", "?");

        c.update(Dictionaries.dictionaryOf("osgi.http.whiteboard.servlet.name", "TestXmlaServlet",
                "osgi.http.whiteboard.servlet.pattern", "/xmla"));
        LOGGER.debug("TestSetup activated and configuration created.");
    }
}
