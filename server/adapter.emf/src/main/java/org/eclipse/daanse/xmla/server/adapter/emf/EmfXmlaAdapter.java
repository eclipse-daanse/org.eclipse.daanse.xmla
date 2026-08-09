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
import org.eclipse.daanse.xmla.api.auth.InbandAuthenticator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serves an XMLA request from a byte stream, with the model doing the
 * describing.
 * <p>
 * Nothing is buffered on either side. The request is read up to the first body
 * element and then streamed; the response is written straight to the output as
 * the rows are produced. The model is the only description involved — the
 * request reaches the {@link XmlaConnector} as a {@link Discover} or an
 * {@link Execute}, the connector answers with EObjects, and those are what go
 * on the wire.
 * <p>
 * Three properties worth naming:
 * <ul>
 * <li>a request type it has no model for is not guessed: {@link RowsetCatalog}
 * decides, and an unknown type becomes the fault a Microsoft server returns for
 * the same request;</li>
 * <li>{@link AuthenticationRequiredException} is <em>not</em> a SOAP fault. It
 * flies before the first byte by construction and propagates to the transport,
 * which turns it into the {@code 401} challenge;</li>
 * <li>no failure is swallowed. Anything else thrown before the first byte
 * becomes a SOAP fault; after the first byte the response is partly on the
 * wire, so it is logged and the stream abandoned.</li>
 * </ul>
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
     * supported.
     * <p>
     * The code the specification gives for an Authenticate this server cannot
     * perform: no {@link InbandAuthenticator} is registered, or the handshake
     * failed.
     */
    private static final Long ERROR_AUTHENTICATION_UNSUPPORTED = 3238920194L;
    private static final String ERROR_SOURCE = "Daanse XMLA";

    /**
     * Everything this server can negotiate through {@code ProtocolCapabilities}.
     * <p>
     * Empty: binary XML and compression are the capabilities the header exists for,
     * and neither is implemented. Echoing one would have the client switch to an
     * encoding this server cannot read.
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
        boolean started = false;
        try {
            started = serve(source, target, context);
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
        }
    }

    /**
     * @return whether the response has begun, so the caller knows a fault is still
     *         possible
     */
    private boolean serve(InputStream source, OutputStream target, XmlaRequest context) throws XMLStreamException {
        SoapEnvelopeReader.Envelope envelope = SoapEnvelopeReader.read(source);
        XmlaRequest request = applySessionHeaders(envelope.headers(), context);

        // SOAP 1.1: a header block carrying mustUnderstand="1" that the receiver does
        // not
        // understand must be refused, not ignored.
        String notUnderstood = firstNotUnderstood(envelope.headers());
        if (notUnderstood != null) {
            writeFault(target, SoapFaultWriter.Kind.MUST_UNDERSTAND, "the header " + notUnderstood
                    + " carries mustUnderstand but this " + "server does not implement it", null);
            return false;
        }

        QName body = envelope.bodyElement();
        if (body == null) {
            writeFault(target, SoapFaultWriter.Kind.CLIENT, "the SOAP body is empty", null);
            return false;
        }
        if (EXT.equals(body.getNamespaceURI()) && "Authenticate".equals(body.getLocalPart())) {
            return authenticate(envelope, target, request);
        }
        if (!XmlaNamespaces.XMLA.equals(body.getNamespaceURI())) {
            writeFault(target, SoapFaultWriter.Kind.CLIENT, "the body element " + body + " is not an XMLA request",
                    null);
            return false;
        }

        if ("Discover".equals(body.getLocalPart())) {
            return discover(envelope, target, request);
        }
        if ("Execute".equals(body.getLocalPart())) {
            return execute(envelope, target, request);
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
        if (!policy.allowsDiscover(requestType, request.isAnonymous())) {
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
            // The one rowset a server can answer about itself: request types, restrictions
            // in
            // order, and the mask over their ordinals are all in the model. A connector
            // that
            // answers this rowset itself is left alone.
            rows = RowsetCatalog.schemaRowsets();
        }

        XmlaMessageCodec.writeDiscoverResponse(target, responseHeaders(request, envelope.headers()), rowEClass.get(),
                rows.iterator());
        return true;
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

        if (!policy.allowsExecute(request.isAnonymous())) {
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
     * One round of the specification's SSPI handshake.
     * <p>
     * The client's blob goes to the {@link InbandAuthenticator}; the answer is
     * either another blob ({@code AuthenticateResponse}, handshake continues), the
     * final blob with the peer established (the authenticator remembers the session
     * it authenticated), or the specification's own error for an authentication
     * this server cannot perform — which is also the answer when no authenticator
     * is registered at all. Credentials in the body are exactly as trustworthy as
     * the session id they ride on, which is why the result binds to the session,
     * not the connection.
     */
    private boolean authenticate(SoapEnvelopeReader.Envelope envelope, OutputStream target, XmlaRequest request)
            throws XMLStreamException {
        if (inband == null) {
            writeFault(target, SoapFaultWriter.Kind.SERVER,
                    "the authentication method is not supported by this endpoint", ERROR_AUTHENTICATION_UNSUPPORTED);
            return false;
        }
        Authenticate handshake = (Authenticate) new EcoreXmlReader(EcoreXmlReader.Unknown.SKIP).read(envelope.cursor(),
                ExtPackage.eINSTANCE.getAuthenticate());
        byte[] blob = handshake.getSspiHandshake();

        InbandAuthenticator.Result outcome = inband.authenticate(blob == null ? new byte[0] : blob, request);
        byte[] answer;
        if (outcome instanceof InbandAuthenticator.Result.Continue next) {
            answer = next.sspiBlob();
        } else if (outcome instanceof InbandAuthenticator.Result.Done done) {
            answer = done.sspiBlob();
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

    private void writeFault(OutputStream target, SoapFaultWriter.Kind kind, String message, Long errorCode) {
        try {
            SoapFaultWriter.write(target, kind, message, errorCode, message, ERROR_SOURCE);
        } catch (XMLStreamException e) {
            LOGGER.error("could not write the SOAP fault for: {}", message, e);
        }
    }

    // --- sessions ---

    /**
     * Applies whichever session header the client sent, as the specification
     * describes.
     * <p>
     * {@code BeginSession} asks the handler for a new id; {@code Session} is
     * honoured only if the handler still recognises the id — a rejected one is
     * treated as no session rather than echoed back, because the echo would tell
     * the client its expired session is still good; {@code EndSession} closes it.
     * Without a handler the endpoint is stateless and every request stands alone.
     */
    private XmlaRequest applySessionHeaders(List<EObject> headers, XmlaRequest request) {
        if (sessions == null) {
            return request;
        }
        for (EObject header : headers) {
            if (header instanceof SessionHeader sessionHeader) {
                String sessionId = sessionHeader.getSessionId();
                if (sessions.checkSession(sessionId, request)) {
                    return request.withSession(sessionId);
                }
                return request;
            }
            if (header instanceof BeginSessionHeader) {
                return sessions.beginSession(request).map(request::withSession).orElse(request);
            }
            if (header instanceof EndSessionHeader endSessionHeader) {
                sessions.endSession(endSessionHeader.getSessionId(), request);
                return request;
            }
        }
        return request;
    }

    /**
     * The headers to answer with: the session id, and the capabilities actually
     * agreed.
     * <p>
     * The spec is explicit that a successful negotiation is the server echoing back
     * the same {@code ProtocolCapabilities} element. Echoing an empty one says
     * "nothing was negotiated", which is true here and is what a client needs to
     * hear before it decides whether to keep using plain XML - answering nothing at
     * all leaves it guessing.
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
