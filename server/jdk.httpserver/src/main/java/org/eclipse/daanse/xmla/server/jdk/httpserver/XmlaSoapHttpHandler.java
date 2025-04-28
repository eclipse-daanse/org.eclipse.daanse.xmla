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
package org.eclipse.daanse.xmla.server.jdk.httpserver;

import java.util.Collections;

import org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaApiAdapter;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

public class XmlaSoapHttpHandler extends AbstractSoapHttpHandler {
    private final XmlaApiAdapter adapter;

    XmlaSoapHttpHandler(XmlaApiAdapter xmlaApiAdapter) throws SOAPException {
        adapter = xmlaApiAdapter;
    }

    @Override
    protected SOAPMessage onMessage(SOAPMessage req) {
        return adapter.handleRequest(req, Collections.emptyMap());
    }
};
