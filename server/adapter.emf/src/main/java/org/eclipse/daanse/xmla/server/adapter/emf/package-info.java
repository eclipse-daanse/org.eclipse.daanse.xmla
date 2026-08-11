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
 * {@link org.eclipse.daanse.xmla.server.adapter.emf.EmfXmlaAdapter} reads one SOAP
 * envelope off a stream and writes one back: session headers first, then identity, then
 * the body dispatched to the connector, whose rows are already the EObjects the model
 * describes. There is no record layer and no converter between.
 * {@link org.eclipse.daanse.xmla.server.adapter.emf.AccessPolicy} decides what an
 * anonymous caller may ask for.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("0.0.1")
package org.eclipse.daanse.xmla.server.adapter.emf;
