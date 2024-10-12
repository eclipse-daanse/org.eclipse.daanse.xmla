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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.ext.Authenticate;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.ext.AuthenticateResponse;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.BeginSession;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Discover;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.EndSession;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Execute;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ExecuteResponse;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Session;

import jakarta.xml.ws.Holder;

public interface WsAdapter {

    AuthenticateResponse authenticate(Authenticate authenticate);

    DiscoverResponse discover(Discover parameters, Holder<Session> session, BeginSession beginSession,
            EndSession endSession);

    ExecuteResponse execute(Execute parameters, Holder<Session> session, BeginSession beginSession,
            EndSession endSession);

}
