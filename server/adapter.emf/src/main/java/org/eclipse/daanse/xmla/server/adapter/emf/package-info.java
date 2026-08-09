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
 * EMF-based XMLA server adapter.
 *
 * <p>
 * Serves a Discover request from a byte stream:
 * {@link org.eclipse.daanse.xmla.server.adapter.emf.EmfXmlaApiAdapter} reads
 * the envelope, {@code DiscoverRequests} builds the api request record from the
 * restrictions, and {@code RowConverters} turns the answer into the row objects
 * the model describes. The last two are generated from the model and the api,
 * so a column can only be lost by a change that stops the build.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("0.0.1")
package org.eclipse.daanse.xmla.server.adapter.emf;
