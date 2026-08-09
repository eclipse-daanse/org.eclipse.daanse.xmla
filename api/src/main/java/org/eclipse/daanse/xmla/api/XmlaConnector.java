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
package org.eclipse.daanse.xmla.api;

import java.util.List;

import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Execute;
import org.eclipse.emf.ecore.EObject;

/**
 * What a backend implements to answer XMLA.
 * <p>
 * One method per XMLA verb rather than one per rowset, because the request type
 * is <em>in</em> the request: {@link Discover#getRequestType()} is a
 * {@code RequestTypeEnum} covering all 103 rowsets, so a rowset added to the
 * model needs nothing added here.
 * <p>
 * Authentication is not this interface's concern - the transport establishes
 * who is asking and says so in the {@link XmlaRequest}. What <em>is</em> this
 * interface's concern is whether an anonymous caller may see the answer: a
 * connector that will not serve a rowset without a principal throws
 * {@link AuthenticationRequiredException}, and the transport turns that into
 * the challenge its registered mechanisms can honour.
 * {@code DISCOVER_PROPERTIES} and {@code DISCOVER_DATASOURCES} are
 * conventionally answered anonymously - a client probes a server before it logs
 * in.
 */
public interface XmlaConnector {

    /**
     * The rows of one Discover, in the order they should reach the client.
     * <p>
     * Every row must be an instance of the EClass the model gives for the request
     * type — that EClass decides the element names and the inline schema the
     * transport writes. An empty list means the client asked about something that
     * exists and has no rows; failure is a thrown exception, never an empty list.
     *
     * @throws AuthenticationRequiredException if this rowset is not served
     *                                         anonymously and the request carries
     *                                         no principal
     */
    List<EObject> discover(Discover request, XmlaRequest context);

    /**
     * The result of one Execute, or {@code null} for a command that produces none.
     * <p>
     * An MDX {@code Statement} answers with a {@code Mddataset} or a
     * {@code Rowset}; Alter, Process, Backup and the rest answer with nothing, and
     * {@code null} is how they say so: the transport then writes the {@code empty}
     * root. A command this connector will not run must throw rather than return
     * {@code null}, which reads as success.
     *
     * @throws AuthenticationRequiredException if commands are not run anonymously
     *                                         and the request carries no principal
     */
    EObject execute(Execute request, XmlaRequest context);
}
