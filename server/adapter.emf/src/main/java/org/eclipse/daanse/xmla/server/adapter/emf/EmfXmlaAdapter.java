/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.server.adapter.emf;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.daanse.xmla.model.ext.Authenticate;
import org.eclipse.daanse.xmla.model.ext.AuthenticateResponse;
import org.eclipse.daanse.xmla.model.ext.ExtFactory;
import org.eclipse.daanse.xmla.model.ext.ExtPackage;
import org.eclipse.daanse.xmla.model.io.DiscoverRequestReader;
import org.eclipse.daanse.xmla.model.io.EcoreXmlReader;
import org.eclipse.daanse.xmla.model.io.EcoreXmlWriter;
import org.eclipse.daanse.xmla.model.io.ExecuteRequestReader;
import org.eclipse.daanse.xmla.model.io.RestrictionsGuard;
import org.eclipse.daanse.xmla.model.io.RowsetCatalog;
import org.eclipse.daanse.xmla.model.io.SoapEnvelopeReader;
import org.eclipse.daanse.xmla.model.io.SoapEnvelopeWriter;
import org.eclipse.daanse.xmla.model.io.SoapFaultWriter;
import org.eclipse.daanse.xmla.model.io.UnknownCommandException;
import org.eclipse.daanse.xmla.model.io.UnknownRequestTypeException;
import org.eclipse.daanse.xmla.model.io.XmlaMessageCodec;
import org.eclipse.daanse.xmla.model.io.XmlaNamespaces;
import org.eclipse.daanse.xmla.model.soap.BeginSessionHeader;
import org.eclipse.daanse.xmla.model.soap.EndSessionHeader;
import org.eclipse.daanse.xmla.model.soap.ProtocolCapabilitiesHeader;
import org.eclipse.daanse.xmla.model.soap.SessionHeader;
import org.eclipse.daanse.xmla.model.soap.SoapFactory;
import org.eclipse.daanse.xmla.model.soap.UnknownHeader;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.daanse.xmla.api.AuthenticationRequiredException;
import org.eclipse.daanse.xmla.api.XmlaConnector;
import org.eclipse.daanse.xmla.api.XmlaRequest;
import org.eclipse.daanse.xmla.api.XmlaSessionHandler;
import org.eclipse.daanse.xmla.api.auth.AuthenticatedIdentity;
import org.eclipse.daanse.xmla.api.auth.InbandAuthenticator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serves an XMLA request from a byte stream, with the model doing the
 * describing.
 * <p>
 * Nothing is buffered on either side: the request is streamed from the first
 * body element, the response written as the rows are produced. The request
 * reaches the {@link XmlaConnector} as a {@link Discover} or an {@link Execute}
 * and the EObjects it answers with go on the wire.
 * <p>
 * A request type with no model is not guessed - {@link RowsetCatalog} decides.
 * {@link AuthenticationRequiredException} is not a SOAP fault: it propagates to
 * the transport, which turns it into the {@code 401} challenge. Anything else
 * thrown before the first byte becomes a fault; after it, the response is partly
 * written, so the failure is logged and the stream abandoned.
 */
public class EmfXmlaAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmfXmlaAdapter.class);

    private static final XMLOutputFactory OUTPUT = XMLOutputFactory.newInstance();

    /** The ext namespace, where Authenticate lives. */
    private static final String EXT = "http://schemas.microsoft.com/analysisservices/2003/ext";

    /** What Analysis Services returns for a request type it does not recognise. */
    private static final Long ERROR_UNKNOWN_REQUEST_TYPE = 3238789172L;

    /**
     * {@code XMLAnalysisError.0xC10E0002}: the authentication method is not
     * supported - no {@link InbandAuthenticator} is registered, or the handshake
     * failed.
     */
    private static final Long ERROR_AUTHENTICATION_UNSUPPORTED = 3238920194L;
    private static final String ERROR_SOURCE = "Daanse XMLA";

    /**
     * What {@code ProtocolCapabilities} may negotiate: nothing. Binary XML and
     * compression are what the header exists for, and echoing one would have the
     * client switch to an encoding this server cannot read.
     */
    private static final List<String> SUPPORTED_CAPABILITIES = List.of();

    private final XmlaConnector connector;
    private final XmlaSessionHandler sessions;
    private final InbandAuthenticator inband;
    private final AccessPolicy policy;

    /**
     * @param sessions {@code null} for a stateless endpoint: no session is ever
     *                 opened and no session header echoed, which the specification
     *                 allows
     * @param inband   {@code null} when no in-band security package is available:
     *                 an {@code Authenticate} is then refused with the
     *                 specification's own error
     */
    public EmfXmlaAdapter(XmlaConnector connector, XmlaSessionHandler sessions, InbandAuthenticator inband) {
        this(connector, sessions, inband, AccessPolicy.OPEN);
    }

    public EmfXmlaAdapter(XmlaConnector connector, XmlaSessionHandler sessions, InbandAuthenticator inband,
            AccessPolicy policy) {
        this.connector = connector;
        this.sessions = sessions;
        this.inband = inband;
        this.policy = policy;
    }

    /** A stateless, transport-authenticated endpoint. */
    public EmfXmlaAdapter(XmlaConnector connector) {
        this(connector, null, null);
    }

    /**
     * Reads one request and writes one response.
     *
     * @throws AuthenticationRequiredException if the connector refuses the
     *                                         anonymous request — the transport
     *                                         answers the challenge, not this class
     */
    public void handle(InputStream source, OutputStream target, XmlaRequest context) {
        Exchange exchange = new Exchange(context);
        boolean started = false;
        try {
            started = serve(source, target, exchange);
        } catch (XMLStreamException | RuntimeException e) {
            if (e instanceof AuthenticationRequiredException refused) {
                // Flies before the first byte by construction; the transport owns the 401.
                throw refused;
            }
            if (started) {
                // Part of the response is already on the wire, so a fault written now would
                // produce a document that is neither.
                LOGGER.error("failed after the response had started; abandoning it", e);
                return;
            }
            LOGGER.debug("answering with a SOAP fault", e);
            String message = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
            writeFault(target, SoapFaultWriter.Kind.SERVER, message, null);
        } finally {
            if (!started && exchange.minted != null && sessions != null) {
                // The response never went out, so the client was never told this id and could
                // never end it.
                sessions.endSession(exchange.minted, context);
            }
        }
    }

    /** What one message did that must be undone if it never reaches the client. */
    private static final class Exchange {

        private final XmlaRequest incoming;
        private String minted;
        /**
         * The session an {@code EndSession} header asks to close, held until the
         * request is allowed to be served. Ending it while reading the headers would
         * mean a request that is about to be refused had already had its effect.
         */
        private String ending;

        private Exchange(XmlaRequest incoming) {
            this.incoming = incoming;
        }
    }

    /**
     * @return whether the response has begun, so the caller knows a fault is still
     *         possible
     */
    private boolean serve(InputStream source, OutputStream target, Exchange exchange) throws XMLStreamException {
        SoapEnvelopeReader.Envelope envelope = SoapEnvelopeReader.read(source);

        // SOAP 1.1: a header block carrying mustUnderstand="1" that the receiver does
        // not understand must be refused and the message not processed further - so
        // this
        // decides before any session is opened, honoured or ended.
        String notUnderstood = firstNotUnderstood(envelope.headers());
        if (notUnderstood != null) {
            writeFault(target, SoapFaultWriter.Kind.MUST_UNDERSTAND, "the header " + notUnderstood
                    + " carries mustUnderstand but this " + "server does not implement it", null);
            return false;
        }

        XmlaRequest request;
        try {
            request = restoreIdentity(applySessionHeaders(envelope.headers(), exchange));
        } catch (org.eclipse.daanse.xmla.api.XmlaRefusedException refused) {
            writeFault(target, faultKindOf(refused), refused.getMessage(), null);
            return false;
        }

        QName body = envelope.bodyElement();
        if (body == null) {
            writeFault(target, SoapFaultWriter.Kind.CLIENT, "the SOAP body is empty", null);
            return false;
        }
        if (EXT.equals(body.getNamespaceURI()) && "Authenticate".equals(body.getLocalPart())) {
            return authenticate(envelope, target, request, exchange);
        }
        if (!XmlaNamespaces.XMLA.equals(body.getNamespaceURI())) {
            writeFault(target, SoapFaultWriter.Kind.CLIENT, "the body element " + body + " is not an XMLA request",
                    null);
            return false;
        }

        if ("Discover".equals(body.getLocalPart())) {
            boolean started = discover(envelope, target, request);
            endPending(exchange, request);
            return started;
        }
        if ("Execute".equals(body.getLocalPart())) {
            boolean started = execute(envelope, target, request);
            endPending(exchange, request);
            return started;
        }
        writeFault(target, SoapFaultWriter.Kind.CLIENT,
                "the body element " + body.getLocalPart() + " is neither Discover nor Execute", null);
        return false;
    }

    /**
     * The name of the first header block that demands understanding and does not
     * get it.
     *
     * @return {@code null} if every such block is understood
     */
    private static String firstNotUnderstood(List<EObject> headers) {
        for (EObject header : headers) {
            if (header instanceof UnknownHeader unknown && unknown.isMustUnderstand()) {
                return "{" + unknown.getNamespaceUri() + "}" + unknown.getLocalName();
            }
            if (header instanceof ProtocolCapabilitiesHeader capabilities) {
                for (String wanted : capabilities.getCapability()) {
                    if (!SUPPORTED_CAPABILITIES.contains(wanted) && capabilities.isMustUnderstand()) {
                        return "ProtocolCapabilities/" + wanted;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @return whether the response has begun, so the caller knows a fault is still
     *         possible
     */
    private boolean discover(SoapEnvelopeReader.Envelope envelope, OutputStream target, XmlaRequest request)
            throws XMLStreamException {
        Discover discover;
        try {
            discover = DiscoverRequestReader.read(envelope.cursor());
        } catch (UnknownRequestTypeException unknown) {
            writeFault(target, SoapFaultWriter.Kind.CLIENT, unknown.getMessage(), ERROR_UNKNOWN_REQUEST_TYPE);
            return false;
        }

        String requestType = discover.getRequestType().getLiteral();
        if (!policy.allowsDiscover(requestType, !request.isAuthenticated())) {
            throw new AuthenticationRequiredException("the rowset " + requestType + " is not served anonymously");
        }
        // Every restriction validated against the model's metadata before dispatch. The
        // per-rowset tables are normative ([MS-SSAS] 3.1.4.2.2.1.1), and a required
        // restriction omitted means the request fails.
        Optional<RestrictionsGuard.Refusal> refusal = RestrictionsGuard.validate(discover);
        if (refusal.isPresent()) {
            writeFault(target, refusal.get().kind(), refusal.get().message(), null);
            return false;
        }
        Optional<EClass> rowEClass = RowsetCatalog.forRequestType(requestType);
        if (rowEClass.isEmpty()) {
            // The enumeration and the catalogue are both built from the model, so this
            // cannot
            // happen while they agree; the alternative is writing rows against a null
            // EClass.
            writeFault(target, SoapFaultWriter.Kind.CLIENT, "the request type " + requestType + " is not supported",
                    ERROR_UNKNOWN_REQUEST_TYPE);
            return false;
        }

        List<EObject> rows;
        try {
            rows = connector.discover(discover, request);
        } catch (org.eclipse.daanse.xmla.api.XmlaRefusedException refused) {
            writeFault(target, faultKindOf(refused), refused.getMessage(), null);
            return false;
        }
        if (rows == null) {
            rows = List.of();
        }
        if (rows.isEmpty() && "DISCOVER_SCHEMA_ROWSETS".equals(requestType)) {
            // The one rowset a server answers about itself, and the model holds all of
            // it: request types, restrictions in order, the mask over their ordinals. A
            // connector that answers it itself is left alone; one that says what it
            // serves gets that announced, and the whole model is the fallback rather
            // than the goal - a rowset announced and then refused is a promise broken
            // on the next request.
            java.util.Set<String> served = connector.served();
            rows = served.isEmpty() ? RowsetCatalog.schemaRowsets() : RowsetCatalog.schemaRowsets(served);
        }
        if ("DISCOVER_DATASOURCES".equals(requestType)) {
            statePolicyInAuthenticationMode(rows);
        }

        XmlaMessageCodec.writeDiscoverResponse(target, responseHeaders(request, envelope.headers()), rowEClass.get(),
                rows.iterator());
        return true;
    }

    /** The value of AuthenticationMode when the transport demands a login. */
    private static final String AUTHENTICATED = "Authenticated";

    /**
     * Makes DISCOVER_DATASOURCES say what this endpoint demands.
     * <p>
     * [MS-SSAS] on the column: {@code Unauthenticated} means "no user ID or
     * password has to be sent", {@code Authenticated} that they "MUST be included".
     * A connector is handed a caller, not a policy, so it states the open case and
     * this layer corrects it. Since both this rowset and DISCOVER_PROPERTIES are
     * answered anonymously - a client has to ask before it knows whom to introduce
     * itself as - the column is the only thing announcing that a challenge comes.
     */
    private void statePolicyInAuthenticationMode(List<EObject> rows) {
        if (!policy.requirePrincipal()) {
            return;
        }
        for (EObject row : rows) {
            EStructuralFeature mode = row.eClass().getEStructuralFeature("authenticationMode");
            if (mode != null && !mode.isMany()) {
                row.eSet(mode, AUTHENTICATED);
            }
        }
    }

    /** @return whether the response has begun */
    private boolean execute(SoapEnvelopeReader.Envelope envelope, OutputStream target, XmlaRequest request)
            throws XMLStreamException {
        Execute execute;
        try {
            execute = ExecuteRequestReader.read(envelope.cursor());
        } catch (UnknownCommandException unknown) {
            writeFault(target, SoapFaultWriter.Kind.CLIENT, unknown.getMessage(), null);
            return false;
        }

        if (!policy.allowsExecute(!request.isAuthenticated())) {
            throw new AuthenticationRequiredException("commands are not run anonymously");
        }

        EObject result;
        try {
            result = connector.execute(execute, request);
        } catch (org.eclipse.daanse.xmla.api.XmlaRefusedException refused) {
            writeFault(target, faultKindOf(refused), refused.getMessage(), null);
            return false;
        }
        XmlaMessageCodec.writeExecuteResponse(target, responseHeaders(request, envelope.headers()), result);
        return true;
    }

    /** A connector's deliberate refusal becomes the SOAP fault it means. */
    private static SoapFaultWriter.Kind faultKindOf(org.eclipse.daanse.xmla.api.XmlaRefusedException refused) {
        return refused.side() == org.eclipse.daanse.xmla.api.XmlaRefusedException.Side.SERVER
                ? SoapFaultWriter.Kind.SERVER
                : SoapFaultWriter.Kind.CLIENT;
    }

    // --- the in-band Authenticate handshake ---

    /**
     * One round of the specification's security-token handshake.
     * <p>
     * The client's token goes to the {@link InbandAuthenticator}, which answers
     * with another token, the final one with the peer established, or the error for
     * an authentication this server cannot perform.
     * <p>
     * The identity binds to the session, there being no connection to bind it to
     * over HTTP. A client without one gets a session minted before the first round,
     * so a multi-round handshake has a stable key, and carried back in the
     * {@code <Session>} header. With no session handler the handshake is refused
     * rather than silently forgotten.
     */
    private boolean authenticate(SoapEnvelopeReader.Envelope envelope, OutputStream target, XmlaRequest incoming,
            Exchange exchange) throws XMLStreamException {
        if (inband == null || sessions == null) {
            writeFault(target, SoapFaultWriter.Kind.SERVER,
                    "the authentication method is not supported by this endpoint", ERROR_AUTHENTICATION_UNSUPPORTED);
            return false;
        }
        XmlaRequest request = incoming;
        if (request.sessionId() == null) {
            Optional<String> opened = sessions.beginSession(request);
            opened.ifPresent(sessionId -> exchange.minted = sessionId);
            request = opened.map(request::withSession).orElse(request);
        }
        if (request.sessionId() == null) {
            writeFault(target, SoapFaultWriter.Kind.SERVER,
                    "the authentication method is not supported by this endpoint", ERROR_AUTHENTICATION_UNSUPPORTED);
            return false;
        }

        Authenticate handshake = (Authenticate) new EcoreXmlReader(EcoreXmlReader.Unknown.SKIP).read(envelope.cursor(),
                ExtPackage.eINSTANCE.getAuthenticate());
        byte[] token = handshake.getSspiHandshake();

        InbandAuthenticator.Result outcome = inband.authenticate(token == null ? new byte[0] : token, request);
        byte[] answer;
        if (outcome instanceof InbandAuthenticator.Result.Continue next) {
            answer = next.token();
        } else if (outcome instanceof InbandAuthenticator.Result.Done done) {
            try {
                sessions.bindIdentity(request.sessionId(), done.identity());
            } catch (org.eclipse.daanse.xmla.api.XmlaRefusedException refused) {
                // A caller running the handshake against a session that is gone or is
                // somebody else's.
                writeFault(target, faultKindOf(refused), refused.getMessage(), ERROR_AUTHENTICATION_UNSUPPORTED);
                return false;
            }
            answer = done.token();
        } else {
            String reason = ((InbandAuthenticator.Result.Refused) outcome).reason();
            writeFault(target, SoapFaultWriter.Kind.CLIENT, reason, ERROR_AUTHENTICATION_UNSUPPORTED);
            return false;
        }

        AuthenticateResponse response = ExtFactory.eINSTANCE.createAuthenticateResponse();
        response.setReturn(ExtFactory.eINSTANCE.createAuthenticateResponseReturn());
        response.getReturn().setSspiHandshake(answer == null ? new byte[0] : answer);

        XMLStreamWriter out = OUTPUT.createXMLStreamWriter(target, "UTF-8");
        SoapEnvelopeWriter.write(out, responseHeaders(request, envelope.headers()),
                body -> new EcoreXmlWriter(EXT).write(body, response, "AuthenticateResponse"));
        out.close();
        return true;
    }

    /**
     * Ties this request and its session together in whichever direction is missing.
     * <p>
     * A caller who proved who they are owns the session: the identity binds to it,
     * so a later caller who merely knows the id cannot step in - and a backend
     * keeps per-session state under it, opened with the roles of whoever opened it.
     * The other direction serves the in-band handshake, which has no connection to
     * hold what it established. A stand-in identity proves nothing, so a session
     * carrying a real one wins over it.
     */
    private XmlaRequest restoreIdentity(XmlaRequest request) {
        if (sessions == null || request.sessionId() == null) {
            return request;
        }
        if (request.isAuthenticated()) {
            sessions.bindIdentity(request.sessionId(),
                    new AuthenticatedIdentity(request.principal(), request.roles(), request.claims()));
            return request;
        }
        return sessions.identityOf(request.sessionId()).map(request::withIdentity).orElse(request);
    }

    private void writeFault(OutputStream target, SoapFaultWriter.Kind kind, String message, Long errorCode) {
        try {
            SoapFaultWriter.write(target, kind, message, errorCode, message, ERROR_SOURCE);
        } catch (XMLStreamException e) {
            LOGGER.error("could not write the SOAP fault for: {}", message, e);
        }
    }

    // --- sessions ---

    /**
     * Closes the session an {@code EndSession} header asked for, now that the
     * request has been served rather than refused. A request that never got past
     * the access policy leaves by exception and does not reach this.
     */
    private void endPending(Exchange exchange, XmlaRequest request) {
        if (exchange.ending != null && sessions != null) {
            sessions.endSession(exchange.ending, request);
            exchange.ending = null;
        }
    }

    private XmlaRequest applySessionHeaders(List<EObject> headers, Exchange exchange) {
        XmlaRequest request = exchange.incoming;
        if (sessions == null) {
            return request;
        }
        boolean beginRequested = false;
        for (EObject header : headers) {
            if (header instanceof SessionHeader sessionHeader) {
                String sessionId = sessionHeader.getSessionId();
                if (!sessions.checkSession(sessionId, request)) {
                    throw org.eclipse.daanse.xmla.api.XmlaRefusedException.invalidSession(sessionId);
                }
                request = request.withSession(sessionId);
            } else if (header instanceof EndSessionHeader endSessionHeader) {
                String sessionId = endSessionHeader.getSessionId();
                if (!sessions.checkSession(sessionId, request)) {
                    throw org.eclipse.daanse.xmla.api.XmlaRefusedException.invalidSession(sessionId);
                }
                // Noted, not done: a caller who is then refused must still find the session
                // there on the retry. A client that waits for a 401 before it sends
                // credentials - which is every HTTP client - would otherwise end its own
                // session with the attempt that was rejected.
                exchange.ending = sessionId;
                request = request.withSession(null);
            } else if (header instanceof BeginSessionHeader) {
                beginRequested = true;
            }
        }
        if (beginRequested && request.sessionId() == null) {
            String opened = sessions.beginSession(request)
                    .orElseThrow(org.eclipse.daanse.xmla.api.XmlaRefusedException::sessionNotOpened);
            exchange.minted = opened;
            request = request.withSession(opened);
        }
        return request;
    }

    /**
     * The headers to answer with: the session id, and the capabilities actually
     * agreed.
     * <p>
     * A successful negotiation is the server echoing the same
     * {@code ProtocolCapabilities} element back. An empty one says "nothing was
     * negotiated", which is true here; answering nothing at all leaves the client
     * guessing whether to keep to plain XML.
     */
    private List<EObject> responseHeaders(XmlaRequest request, List<EObject> requested) {
        List<EObject> headers = new ArrayList<>(2);
        if (request.sessionId() != null) {
            SessionHeader header = SoapFactory.eINSTANCE.createSessionHeader();
            header.setSessionId(request.sessionId());
            headers.add(header);
        }
        for (EObject header : requested) {
            if (header instanceof ProtocolCapabilitiesHeader asked) {
                ProtocolCapabilitiesHeader agreed = SoapFactory.eINSTANCE.createProtocolCapabilitiesHeader();
                for (String wanted : asked.getCapability()) {
                    if (SUPPORTED_CAPABILITIES.contains(wanted)) {
                        agreed.getCapability().add(wanted);
                    }
                }
                headers.add(agreed);
                break;
            }
        }
        return headers;
    }
}
