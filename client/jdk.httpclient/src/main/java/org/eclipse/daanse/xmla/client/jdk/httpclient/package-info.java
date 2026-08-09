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

/**
 * EMF-based XMLA client.
 *
 * <p>
 * Builds a Discover request from the Ecore model, posts it over
 * {@link java.net.http.HttpClient}, and reads the answer back into row objects
 * of the same {@link org.eclipse.emf.ecore.EClass} the server wrote from.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("0.0.1")
package org.eclipse.daanse.xmla.client.jdk.httpclient;
