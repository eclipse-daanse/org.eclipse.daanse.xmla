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
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.api;

import java.util.Iterator;
import java.util.List;

/**
 * An Execute result written row by row as the rows come, not at once -- what {@code Subscribe} to a trace answers: an
 * open rowset, one row for each event, until the subscription ends.
 * <p>
 * The transport writes the envelope and the inline schema at once, then each row as {@link #rows()} gives it,
 * flushing after each, and closes the document when the iterator ends. {@code hasNext()} may block while no row is
 * there. The transport closes the stream when it has written the last row or cannot write any more because the client
 * is gone.
 * <p>
 * A column has the name a client reads it by, as {@code sql:field}, and a short element name of its own, as Analysis
 * Services writes a trace's rows: {@code <row><a>10</a><b>6</b><c>SELECT …</c></row>} under
 * {@code <xsd:element sql:field="EventClass" name="a" type="xsd:int"/>}.
 */
public abstract class RowStream extends org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container
        implements AutoCloseable {

    /**
     * One column of the rows.
     *
     * @param field   the name a client reads the column by
     * @param name    the element name of its values
     * @param xsdType its type, e.g. {@code xsd:int}; {@code null} for none
     */
    public record Column(String field, String name, String xsdType) {
    }

    /** The columns, in the order the rows hold their values. */
    public abstract List<Column> columns();

    /** The rows, each a value per column, {@code null} for one it does not have; {@code hasNext()} may block. */
    public abstract Iterator<List<Object>> rows();

    /** Ends what the stream reads from; called once the transport has written its last row. */
    @Override
    public void close() {
        // nothing held by default
    }
}
