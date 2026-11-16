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
package org.eclipse.daanse.xmla.model.io;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * Where in a request an object was read: the path of its element from the envelope down, its namespace, the line and
 * column where it starts and ends, and the same for each of its simple child elements. A refusal of what the object
 * says names the place as Analysis Services does: "at line 1, column 332 … under
 * Envelope/Body/Execute/Command/Create/ObjectDefinition/Trace/Events/Event/EventID".
 * <p>
 * The reader attaches it while it reads a command; an object built in code carries none.
 */
public final class ElementLocation extends AdapterImpl {

    /**
     * One place in the request.
     *
     * @param path      the element names from the envelope down to the element, joined by {@code /}
     * @param namespace the element's namespace
     * @param line      the line, from 1
     * @param column    the column, from 1
     */
    public record Position(String path, String namespace, int line, int column) {
    }

    private final Position start;
    private Position end;
    private final Map<String, Position> children = new LinkedHashMap<>();

    ElementLocation(Position start) {
        this.start = start;
    }

    /** Where the object read from a request was, if it was read from one. */
    public static Optional<ElementLocation> of(EObject object) {
        if (object == null) {
            return Optional.empty();
        }
        return object.eAdapters().stream().filter(ElementLocation.class::isInstance).map(ElementLocation.class::cast)
                .findFirst();
    }

    /** Where the element starts. */
    public Position start() {
        return start;
    }

    /** Where it ends; empty while it is being read. */
    public Optional<Position> end() {
        return Optional.ofNullable(end);
    }

    /** Where a simple child element of that name starts, if the object had one. */
    public Optional<Position> child(String elementName) {
        return Optional.ofNullable(children.get(elementName));
    }

    void end(Position at) {
        this.end = at;
    }

    void child(String elementName, Position at) {
        children.putIfAbsent(elementName, at);
    }

    @Override
    public boolean isAdapterForType(Object type) {
        return type == ElementLocation.class;
    }
}
