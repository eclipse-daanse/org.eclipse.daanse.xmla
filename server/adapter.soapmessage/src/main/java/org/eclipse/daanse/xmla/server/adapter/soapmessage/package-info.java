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
 *   Sergei Semenkov - initial
 */

/**
 * SOAP message adapter for XMLA server implementations.
 *
 * <p>
 * This package provides the core adapter layer between XMLA API calls and SOAP
 * message format. It handles serialization, deserialization, and protocol
 * adaptation for XML for Analysis (XMLA) operations.
 *
 * <h2>Core Components</h2>
 * <ul>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaApiAdapter}
 * - Main entry point, routes SOAP requests to appropriate handlers</li>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.SoapUtil} -
 * SOAP message building utilities</li>
 * </ul>
 *
 * <h2>Constants</h2>
 * <ul>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.Constants} -
 * All constants (legacy, for backwards compatibility)</li>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.NamespaceConstants}
 * - XML namespace URIs and prefixes</li>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaElementConstants}
 * - XMLA element name strings</li>
 * </ul>
 *
 * <h2>Exceptions</h2>
 * <ul>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaSoapException}
 * - SOAP operation failures</li>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.XmlaParseException}
 * - XML parsing and command errors</li>
 * </ul>
 *
 * <h2>Subpackages</h2>
 * <ul>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage} - XML
 * parsing utilities</li>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.discover} -
 * Discover operation handlers</li>
 * <li>{@link org.eclipse.daanse.xmla.server.adapter.soapmessage.execute} -
 * Execute operation handlers</li>
 * </ul>
 *
 * @see org.eclipse.daanse.xmla.api
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("0.0.1")
package org.eclipse.daanse.xmla.server.adapter.soapmessage;
