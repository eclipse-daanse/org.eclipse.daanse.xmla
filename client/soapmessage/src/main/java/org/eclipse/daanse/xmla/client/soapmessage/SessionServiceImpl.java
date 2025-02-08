/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation.
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

import java.util.Optional;

import org.eclipse.daanse.xmla.api.UserPrincipal;
import org.eclipse.daanse.xmla.api.session.SessionService;
import org.eclipse.daanse.xmla.api.xmla.BeginSession;
import org.eclipse.daanse.xmla.api.xmla.EndSession;
import org.eclipse.daanse.xmla.api.xmla.Session;

public class SessionServiceImpl implements SessionService {

    @Override
    public Optional<Session> beginSession(BeginSession beginSession, UserPrincipal userPrincipal) {
        return Optional.empty();
    }

    @Override
    public boolean checkSession(Session session, UserPrincipal userPrincipal) {
        return true;
    }

    @Override
    public void endSession(EndSession endSession, UserPrincipal userPrincipal) {

    }

}
