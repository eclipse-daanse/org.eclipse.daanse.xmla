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
package org.eclipse.daanse.xmla.spi;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.osgi.annotation.versioning.ConsumerType;

/**
 * One schema rowset, as a service.
 * <p>
 * The whiteboard: every rowset a server answers is a {@code RowsetProvider}
 * registered with the {@link #PROPERTY_REQUEST_TYPE} service property naming
 * its rowset, and the hosting connector dispatches by that property alone.
 * Registering a service adds the rowset - to the dispatch and to
 * {@code DISCOVER_SCHEMA_ROWSETS}, which is generated from the registrations -
 * unregistering removes it, and a request for a rowset nothing provides is
 * refused in a live server's words. Two providers are the exception to
 * "optional": {@code DISCOVER_PROPERTIES} and {@code DISCOVER_SCHEMA_ROWSETS}
 * are what a client needs to talk to the server at all, and a host does not
 * come up without them.
 * <p>
 * Providers are stateless: everything about the call - the request, the caller,
 * and the way to the backend - arrives in the {@link RowsetScope}. A provider
 * registered with {@code PROTOTYPE} scope still gets its own instance per
 * registration, because the whiteboard takes instances through
 * {@code ComponentServiceObjects}.
 *
 * @param <B> the backend handle the hosting connector passes in the scope
 */
@ConsumerType
public interface RowsetProvider<B> {

    /** The mandatory service property naming the rowset this provider answers. */
    String PROPERTY_REQUEST_TYPE = "xmla.rowset.requestType";

    List<EObject> rows(RowsetScope<B> scope);
}
