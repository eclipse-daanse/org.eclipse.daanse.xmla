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
 * The XMLA wire codec: SOAP envelopes, Discover requests and responses, rowset
 * rows and the inline schema that describes them, all driven by the Ecore
 * model.
 *
 * <p>
 * EMF owns the model; this package owns the bytes. The generated
 * {@code XMLProcessor} cannot carry this format — the inline
 * {@code <xsd:schema>} is metadata written into the payload, {@code <row>} is
 * polymorphic without {@code xsi:type}, and a large result has to stream — so
 * reading and writing go through StAX with {@code ExtendedMetaData} deciding
 * the names.
 *
 * <p>
 * It depends on no SOAP stack, no {@code api} type and no OSGi service, so a
 * client and a server can share it without either dragging in the other's
 * dependencies.
 */
@org.osgi.annotation.bundle.Export
@org.osgi.annotation.versioning.Version("0.0.1")
package org.eclipse.daanse.xmla.model.io;
