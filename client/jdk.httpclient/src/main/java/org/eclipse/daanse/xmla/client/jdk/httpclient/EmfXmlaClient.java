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
package org.eclipse.daanse.xmla.client.jdk.httpclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.xml.stream.XMLStreamException;

import org.eclipse.daanse.xmla.model.io.RowsetCatalog;
import org.eclipse.daanse.xmla.model.io.XmlaCodecException;
import org.eclipse.daanse.xmla.model.io.XmlaFaultException;
import org.eclipse.daanse.xmla.model.io.SoapFaultReader;
import org.eclipse.daanse.xmla.model.io.UnknownRequestTypeException;
import org.eclipse.daanse.xmla.model.io.XmlaNamespaces;
import org.eclipse.daanse.xmla.model.io.XmlaMessageCodec;
import org.eclipse.daanse.xmla.model.soap.EndSessionHeader;
import org.eclipse.daanse.xmla.model.soap.SessionHeader;
import org.eclipse.daanse.xmla.model.soap.SoapFactory;
import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.daanse.xmla.model.xmla.Command;
import org.eclipse.daanse.xmla.model.xmla.PropertyList;

/**
 * An XMLA client that speaks in {@link EObject}s.
 * <p>
 * A request goes in as a {@link Discover} built from the model and comes back
 * as row EObjects of the {@link EClass} the model names for that request type -
 * the same EClass the server wrote from. Nothing in between is a string the
 * caller has to assemble or parse: client and server share one description of
 * what a rowset is.
 * <p>
 * The transport is {@link HttpClient} and nothing else. No SAAJ, no JAX-WS, no
 * DOM.
 */
public final class EmfXmlaClient {

    private static final String SOAP_ACTION = "\"urn:schemas-microsoft-com:xml-analysis:Discover\"";

    private static final String EXECUTE_ACTION = "\"urn:schemas-microsoft-com:xml-analysis:Execute\"";

    /**
     * Identifies this client to the server, and to whatever sits in front of it.
     * <p>
     * Not decoration. {@code HttpClient} otherwise sends
     * {@code Java-http-client/<version>}, which web application firewalls in front
     * of public Analysis Services endpoints answer with 403 while accepting any
     * other value.
     * <p>
     * It is also the only thing a {@code DiscoverService} can use to tell one
     * client from another - {@code RequestMetaData.userAgent()} is read from this
     * header.
     */
    private static final String USER_AGENT = "Daanse-XMLA/1.0";

    private final URI endpoint;
    private final HttpClient http;
    private final String authorization;
    private final Duration timeout;
    private final List<EObject> requestHeaders;
    private final String userAgent;

    private EmfXmlaClient(URI endpoint, HttpClient http, String authorization, Duration timeout,
            List<EObject> requestHeaders) {
        this(endpoint, http, authorization, timeout, requestHeaders, USER_AGENT);
    }

    private EmfXmlaClient(URI endpoint, HttpClient http, String authorization, Duration timeout,
            List<EObject> requestHeaders, String userAgent) {
        this.endpoint = endpoint;
        this.http = http;
        this.authorization = authorization;
        this.timeout = timeout;
        this.requestHeaders = requestHeaders;
        this.userAgent = userAgent;
    }

    /**
     * The same client identifying itself differently.
     * <p>
     * Worth setting to something naming the application: it is what the server logs
     * and what a {@code DiscoverService} sees, and some deployments route on it.
     */
    public EmfXmlaClient identifyingAs(String otherUserAgent) {
        return new EmfXmlaClient(endpoint, http, authorization, timeout, requestHeaders, otherUserAgent);
    }

    /** A client that sends no credentials. */
    public static EmfXmlaClient anonymous(URI endpoint) {
        return new EmfXmlaClient(endpoint, HttpClient.newHttpClient(), null, Duration.ofMinutes(2), List.of());
    }

    /**
     * A client that sends HTTP BASIC credentials.
     * <p>
     * The header is set on every request rather than left to
     * {@link java.net.Authenticator}: {@code HttpClient}'s authenticator only
     * answers a 401 challenge, so the first request of every exchange would be sent
     * anonymously and rejected. Against a server that answers 401 without a
     * challenge — which is what a misconfigured proxy in front of msmdpump does —
     * it would never authenticate at all.
     */
    public static EmfXmlaClient basic(URI endpoint, String user, String password) {
        String token = Base64.getEncoder().encodeToString((user + ':' + password).getBytes(StandardCharsets.UTF_8));
        return new EmfXmlaClient(endpoint, HttpClient.newHttpClient(), "Basic " + token, Duration.ofMinutes(2),
                List.of());
    }

    /** The same client with a different request timeout. */
    public EmfXmlaClient withTimeout(Duration other) {
        return new EmfXmlaClient(endpoint, http, authorization, other, requestHeaders, userAgent);
    }

    /**
     * A client whose every request asks the server to open a session.
     * <p>
     * The server answers with a {@code <Session>} header carrying the id it
     * assigned; read it from {@link #exchange} and hand it to {@link #inSession}.
     * Sessions matter because without one a server is free to forget everything
     * between requests, and a client that pages through a large result across
     * several {@code Discover} calls needs it not to.
     */
    public EmfXmlaClient beginningSession() {
        return withHeader(SoapFactory.eINSTANCE.createBeginSessionHeader());
    }

    /** A client whose every request carries this session id. */
    public EmfXmlaClient inSession(String sessionId) {
        SessionHeader header = SoapFactory.eINSTANCE.createSessionHeader();
        header.setSessionId(sessionId);
        return withHeader(header);
    }

    /** A client whose next request asks the server to close this session. */
    public EmfXmlaClient endingSession(String sessionId) {
        EndSessionHeader header = SoapFactory.eINSTANCE.createEndSessionHeader();
        header.setSessionId(sessionId);
        return withHeader(header);
    }

    private EmfXmlaClient withHeader(EObject header) {
        return new EmfXmlaClient(endpoint, http, authorization, timeout, List.of(header), userAgent);
    }

    /** The session id the server assigned, or empty if it opened none. */
    public static Optional<String> sessionIdOf(XmlaMessageCodec.Response response) {
        for (EObject header : response.headers()) {
            if (header instanceof SessionHeader session) {
                return Optional.ofNullable(session.getSessionId());
            }
        }
        return Optional.empty();
    }

    /**
     * Sends a Discover request and returns its rows.
     *
     * @return the rows, typed by the {@link EClass} the model names for the request
     *         type
     * @throws XmlaCodecException if the server answered a fault, a non-200 status,
     *                            or a request type the model does not know
     */
    public List<EObject> discover(Discover request) throws IOException, InterruptedException {
        return exchange(request).rows();
    }

    /**
     * Sends a Discover request and reads the rows as {@code rowEClass}.
     * <p>
     * The explicit form exists for reading a foreign server's answer to a rowset
     * this model types differently, where the caller rather than the catalogue
     * knows what the bytes contain.
     */
    public List<EObject> discover(Discover request, EClass rowEClass) throws IOException, InterruptedException {
        return exchange(request, rowEClass).rows();
    }

    /**
     * As {@link #discover}, but keeping the response headers too — the only way to
     * learn the session id the server assigned.
     */
    public XmlaMessageCodec.Response exchange(Discover request) throws IOException, InterruptedException {
        String requestType = request.getRequestType().getLiteral();
        return exchange(request, RowsetCatalog.forRequestType(requestType)
                .orElseThrow(() -> new UnknownRequestTypeException(requestType)));
    }

    /** As {@link #exchange(Discover)}, reading the rows as {@code rowEClass}. */
    public XmlaMessageCodec.Response exchange(Discover request, EClass rowEClass)
            throws IOException, InterruptedException {
        byte[] bytes = post(serialise(request), XmlaNamespaces.SOAP_ACTION_DISCOVER);
        try {
            return XmlaMessageCodec.readResponse(new ByteArrayInputStream(bytes), rowEClass);
        } catch (XMLStreamException e) {
            throw new XmlaCodecException("cannot read the response from " + endpoint, e);
        }
    }

    /**
     * Sends an Execute command and reads what it answers, in-band messages
     * included.
     * <p>
     * A command is an EObject and the writer writes any of them from the model -
     * nothing here is per command. A command answers the empty result whether it
     * worked or not, so what the server had to say arrives in band and comes back
     * with the payload.
     *
     * @return the MDDataset a statement answers, the tabular RowSet a DMV query
     *         answers, or a {@code null} payload for the empty result every other
     *         command gives - with whatever {@code <Messages>} the server put in
     *         band
     */
    public XmlaMessageCodec.ExecuteResult execute(Command command) throws IOException, InterruptedException {
        return execute(command, null);
    }

    /** As {@link #execute(Command)}, with a property list on the request. */
    public XmlaMessageCodec.ExecuteResult execute(Command command, PropertyList properties)
            throws IOException, InterruptedException {
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        try {
            XmlaMessageCodec.writeExecuteRequest(target, requestHeaders, command, properties);
        } catch (XMLStreamException e) {
            throw new XmlaCodecException("cannot write the Execute request", e);
        }
        byte[] bytes = post(target.toByteArray(), XmlaNamespaces.SOAP_ACTION_EXECUTE);
        try {
            return XmlaMessageCodec.readExecuteResult(new ByteArrayInputStream(bytes));
        } catch (XMLStreamException e) {
            throw new XmlaCodecException("cannot read the response from " + endpoint, e);
        }
    }

    /**
     * One HTTP exchange: the request bytes out, the response bytes back - after the
     * status check and with a SOAP fault already thrown as the refusal it is.
     */
    private byte[] post(byte[] body, String soapAction) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder(endpoint).timeout(timeout)
                .header("Content-Type", "text/xml; charset=utf-8").header("User-Agent", userAgent)
                .header("SOAPAction", soapAction).POST(HttpRequest.BodyPublishers.ofByteArray(body));
        if (authorization != null) {
            builder.header("Authorization", authorization);
        }
        HttpResponse<InputStream> response = http.send(builder.build(), HttpResponse.BodyHandlers.ofInputStream());
        try (InputStream in = response.body()) {
            if (response.statusCode() != 200) {
                throw new XmlaCodecException(endpoint + " answered HTTP " + response.statusCode() + ": " + read(in));
            }
            // The fault has to be recognised before the payload is read: a fault body
            // carries
            // no rows, so parsing it as a response would quietly yield an empty result and
            // look like a server with nothing to report.
            byte[] bytes = in.readAllBytes();
            SoapFaultReader.failIfFault(bytes);
            return bytes;
        }
    }

    private byte[] serialise(Discover request) {
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        try {
            XmlaMessageCodec.writeDiscoverRequest(target, requestHeaders, request);
        } catch (XMLStreamException e) {
            throw new XmlaCodecException("cannot write the Discover request", e);
        }
        return target.toByteArray();
    }

    private static String read(InputStream in) throws IOException {
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }
}
