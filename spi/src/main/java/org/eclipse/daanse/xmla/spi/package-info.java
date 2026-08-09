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
 * The contract between an XMLA transport and whatever answers it.
 * <p>
 * There is exactly one description of the protocol in this repository - the
 * Ecore models - and this package is where that stops being an implementation
 * detail and becomes the interface: a backend receives the {@code Discover} or
 * {@code Execute} as the model read it off the wire and answers with the
 * EObjects the model will write back. No records, no converters, no second
 * transcription that could drift from the first.
 * <p>
 * {@link org.eclipse.daanse.xmla.spi.XmlaConnector} is the core; sessions and
 * authentication are separate, optional contracts, because a backend that has
 * no state and no users should not have to say so in thirty methods.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("1.0.0")
package org.eclipse.daanse.xmla.spi;
