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
package org.eclipse.daanse.xmla.server.adapter.soapmessage.session;

import java.util.Optional;

import javax.xml.namespace.QName;

import org.eclipse.daanse.xmla.api.UserRolePrincipal;
import org.eclipse.daanse.xmla.api.session.SessionService;
import org.eclipse.daanse.xmla.api.xmla.BeginSession;
import org.eclipse.daanse.xmla.api.xmla.EndSession;
import org.eclipse.daanse.xmla.api.xmla.Session;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;

/**
 * Dispatcher for XMLA Session operations. Handles BeginSession, Session
 * validation, and EndSession requests.
 */
public class SessionDispatcher {

    private static final QName QN_SESSION = new QName("urn:schemas-microsoft-com:xml-analysis", "Session");

    private final SessionService sessionService;

    public SessionDispatcher(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * Process session headers from the SOAP request.
     * <p>
     * Handles three cases:
     * <ul>
     * <li>Session header present - validates the session</li>
     * <li>BeginSession header present - creates a new session</li>
     * <li>EndSession header present - terminates the session</li>
     * </ul>
     *
     * @param soapRequestHeader the SOAP request header
     * @param userPrincipal     the user principal
     * @return Optional containing the session if active, empty otherwise
     * @throws SOAPException if SOAP processing fails
     */
    public Optional<Session> processSessionHeaders(SOAPElement soapRequestHeader, UserRolePrincipal userPrincipal)
            throws SOAPException {
        // Check for existing session
        Optional<Session> oSession = SessionHeaderParser.getSession(soapRequestHeader);
        if (oSession.isPresent()) {
            boolean checked = sessionService.checkSession(oSession.get(), userPrincipal);
            if (checked) {
                return oSession;
            } else {
                return Optional.empty();
            }
        }

        // Check for begin session request
        Optional<BeginSession> beginSession = SessionHeaderParser.getBeginSession(soapRequestHeader);
        if (beginSession.isPresent()) {
            return sessionService.beginSession(beginSession.get(), userPrincipal);
        }

        // Check for end session request
        Optional<EndSession> oEndSession = SessionHeaderParser.getEndSession(soapRequestHeader);
        if (oEndSession.isPresent()) {
            sessionService.endSession(oEndSession.get(), userPrincipal);
            return Optional.empty();
        }

        return Optional.empty();
    }

    /**
     * Add session header to the SOAP response if a session is active.
     *
     * @param responseHeader the SOAP response header
     * @param session        the active session, or empty if no session
     * @throws SOAPException if SOAP processing fails
     */
    public void addSessionResponseHeader(SOAPHeader responseHeader, Optional<Session> session) throws SOAPException {
        if (session.isPresent()) {
            SOAPHeaderElement sessionElement = responseHeader.addHeaderElement(QN_SESSION);
            sessionElement.addAttribute(new QName("SessionId"), session.get().sessionId());
        } else {
            responseHeader.setValue("\n");
        }
    }
}
