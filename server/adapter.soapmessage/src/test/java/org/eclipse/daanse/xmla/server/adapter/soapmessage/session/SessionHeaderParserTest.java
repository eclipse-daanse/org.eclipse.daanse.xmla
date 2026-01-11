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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.BeginSession;
import org.eclipse.daanse.xmla.api.xmla.EndSession;
import org.eclipse.daanse.xmla.api.xmla.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

class SessionHeaderParserTest {

    private static final String XMLA_NS = "urn:schemas-microsoft-com:xml-analysis";

    private SOAPMessage soapMessage;
    private SOAPHeader soapHeader;

    @BeforeEach
    void setUp() throws Exception {
        MessageFactory factory = MessageFactory.newInstance();
        soapMessage = factory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        soapHeader = envelope.getHeader();
    }

    @Nested
    class SessionParsing {

        @Test
        void getSession_withSessionIdAndMustUnderstand() throws Exception {
            SOAPElement sessionElement = soapHeader.addChildElement("Session", "xmla", XMLA_NS);
            sessionElement.setAttribute("SessionId", "session-12345");
            sessionElement.setAttribute("mustUnderstand", "1");

            Optional<Session> result = SessionHeaderParser.getSession(soapHeader);

            assertTrue(result.isPresent());
            Session session = result.get();
            assertEquals("session-12345", session.sessionId());
            assertEquals(Integer.valueOf(1), session.mustUnderstand());
        }

        @Test
        void getSession_withSessionIdOnly() throws Exception {
            SOAPElement sessionElement = soapHeader.addChildElement("Session", "xmla", XMLA_NS);
            sessionElement.setAttribute("SessionId", "my-session");

            Optional<Session> result = SessionHeaderParser.getSession(soapHeader);

            assertTrue(result.isPresent());
            Session session = result.get();
            assertEquals("my-session", session.sessionId());
            assertNull(session.mustUnderstand());
        }

        @Test
        void getSession_noSessionElement() throws Exception {
            Optional<Session> result = SessionHeaderParser.getSession(soapHeader);

            assertFalse(result.isPresent());
        }

        @Test
        void getSession_emptySessionElement() throws Exception {
            soapHeader.addChildElement("Session", "xmla", XMLA_NS);

            Optional<Session> result = SessionHeaderParser.getSession(soapHeader);

            assertTrue(result.isPresent());
            Session session = result.get();
            assertNull(session.sessionId());
            assertNull(session.mustUnderstand());
        }
    }

    @Nested
    class BeginSessionParsing {

        @Test
        void getBeginSession_withMustUnderstand() throws Exception {
            SOAPElement beginSessionElement = soapHeader.addChildElement("BeginSession", "xmla", XMLA_NS);
            beginSessionElement.setAttribute("mustUnderstand", "1");

            Optional<BeginSession> result = SessionHeaderParser.getBeginSession(soapHeader);

            assertTrue(result.isPresent());
            BeginSession beginSession = result.get();
            assertEquals(Integer.valueOf(1), beginSession.mustUnderstand());
        }

        @Test
        void getBeginSession_withoutMustUnderstand() throws Exception {
            soapHeader.addChildElement("BeginSession", "xmla", XMLA_NS);

            Optional<BeginSession> result = SessionHeaderParser.getBeginSession(soapHeader);

            assertTrue(result.isPresent());
            BeginSession beginSession = result.get();
            assertNull(beginSession.mustUnderstand());
        }

        @Test
        void getBeginSession_noBeginSessionElement() throws Exception {
            Optional<BeginSession> result = SessionHeaderParser.getBeginSession(soapHeader);

            assertFalse(result.isPresent());
        }
    }

    @Nested
    class EndSessionParsing {

        @Test
        void getEndSession_withSessionIdAndMustUnderstand() throws Exception {
            SOAPElement endSessionElement = soapHeader.addChildElement("EndSession", "xmla", XMLA_NS);
            endSessionElement.setAttribute("SessionId", "session-to-end");
            endSessionElement.setAttribute("mustUnderstand", "1");

            Optional<EndSession> result = SessionHeaderParser.getEndSession(soapHeader);

            assertTrue(result.isPresent());
            EndSession endSession = result.get();
            assertEquals("session-to-end", endSession.sessionId());
            assertEquals(Integer.valueOf(1), endSession.mustUnderstand());
        }

        @Test
        void getEndSession_withSessionIdOnly() throws Exception {
            SOAPElement endSessionElement = soapHeader.addChildElement("EndSession", "xmla", XMLA_NS);
            endSessionElement.setAttribute("SessionId", "my-ending-session");

            Optional<EndSession> result = SessionHeaderParser.getEndSession(soapHeader);

            assertTrue(result.isPresent());
            EndSession endSession = result.get();
            assertEquals("my-ending-session", endSession.sessionId());
            assertNull(endSession.mustUnderstand());
        }

        @Test
        void getEndSession_noEndSessionElement() throws Exception {
            Optional<EndSession> result = SessionHeaderParser.getEndSession(soapHeader);

            assertFalse(result.isPresent());
        }
    }

    @Nested
    class MultipleHeaders {

        @Test
        void getSession_withOtherElements() throws Exception {
            // Add other header elements that should be ignored
            soapHeader.addChildElement("OtherHeader", "other", "http://other.namespace");
            SOAPElement sessionElement = soapHeader.addChildElement("Session", "xmla", XMLA_NS);
            sessionElement.setAttribute("SessionId", "target-session");
            soapHeader.addChildElement("AnotherHeader", "another", "http://another.namespace");

            Optional<Session> result = SessionHeaderParser.getSession(soapHeader);

            assertTrue(result.isPresent());
            assertEquals("target-session", result.get().sessionId());
        }

        @Test
        void multipleSessionTypes_onlyReturnsRequested() throws Exception {
            // Add all three types
            SOAPElement beginElement = soapHeader.addChildElement("BeginSession", "xmla", XMLA_NS);
            beginElement.setAttribute("mustUnderstand", "1");

            SOAPElement sessionElement = soapHeader.addChildElement("Session", "xmla", XMLA_NS);
            sessionElement.setAttribute("SessionId", "active-session");

            SOAPElement endElement = soapHeader.addChildElement("EndSession", "xmla", XMLA_NS);
            endElement.setAttribute("SessionId", "ending-session");

            // Verify each parser only returns its type
            Optional<BeginSession> beginResult = SessionHeaderParser.getBeginSession(soapHeader);
            Optional<Session> sessionResult = SessionHeaderParser.getSession(soapHeader);
            Optional<EndSession> endResult = SessionHeaderParser.getEndSession(soapHeader);

            assertTrue(beginResult.isPresent());
            assertTrue(sessionResult.isPresent());
            assertTrue(endResult.isPresent());

            assertEquals(Integer.valueOf(1), beginResult.get().mustUnderstand());
            assertEquals("active-session", sessionResult.get().sessionId());
            assertEquals("ending-session", endResult.get().sessionId());
        }
    }
}
