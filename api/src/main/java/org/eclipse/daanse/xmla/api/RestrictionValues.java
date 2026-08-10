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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.PropertyList;
import org.eclipse.daanse.xmla.model.xmla.RestrictionEntry;

/**
 * The restrictions and properties of a Discover, as the model carries them.
 * <p>
 * The bridge had one generated request record per rowset, each restriction a
 * typed accessor. The model carries the same information as a list of
 * name/value entries — {@code <RestrictionList>}'s children are named after the
 * restriction column — so this is the lookup that replaces every one of those
 * record types: ask by the column name the specification uses.
 */
public final class RestrictionValues {

    private final Discover request;

    public RestrictionValues(Discover request) {
        this.request = request;
    }

    /**
     * The single value of a restriction, or empty. When a client repeats the
     * restriction, the last instance is applied - [MS-SSAS] 3.1.4.2.2.1.1 says so
     * in words.
     */
    public Optional<String> value(String name) {
        if (request.getRestrictions() == null) {
            return Optional.empty();
        }
        Optional<String> found = Optional.empty();
        for (RestrictionEntry entry : request.getRestrictions().getRestrictionList()) {
            if (name.equals(entry.getName())) {
                found = Optional.ofNullable(entry.getValue());
            }
        }
        return found;
    }

    /** Every value of a restriction a client sent more than once. */
    public List<String> values(String name) {
        List<String> found = new ArrayList<>();
        if (request.getRestrictions() == null) {
            return found;
        }
        for (RestrictionEntry entry : request.getRestrictions().getRestrictionList()) {
            if (name.equals(entry.getName()) && entry.getValue() != null) {
                found.add(entry.getValue());
            }
        }
        return found;
    }

    /** The {@code <Catalog>} property, which most rowsets scope by. */
    public Optional<String> catalogProperty() {
        PropertyList properties = request.getProperties() == null ? null : request.getProperties().getPropertyList();
        if (properties == null || properties.getCatalog() == null || properties.getCatalog().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(properties.getCatalog());
    }

    /** The property list itself, or {@code null} when the client sent none. */
    public PropertyList properties() {
        return request.getProperties() == null ? null : request.getProperties().getPropertyList();
    }
}
