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
package org.eclipse.daanse.xmla.client.soapmessage;

import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.api.discover.DiscoverService;
import org.eclipse.daanse.xmla.api.execute.ExecuteService;
import org.eclipse.daanse.xmla.api.session.SessionService;

public class XmlaServiceClientImpl implements XmlaService {

    private DiscoverServiceImpl ds;
    private ExecuteServiceImpl es;

    public XmlaServiceClientImpl(String endPointurl) {
        SoapClient client = new SoapClient(endPointurl);
        ds = new DiscoverServiceImpl(client);
        es = new ExecuteServiceImpl(client);
    }

    @Override
    public DiscoverService discover() {
        return ds;
    }

    @Override
    public ExecuteService execute() {
        return es;
    }

    @Override
    public SessionService session() {
        return null;
    }

}
