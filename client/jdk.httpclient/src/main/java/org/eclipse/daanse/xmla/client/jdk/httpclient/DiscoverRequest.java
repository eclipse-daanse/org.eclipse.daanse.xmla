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
package org.eclipse.daanse.xmla.client.jdk.httpclient;

import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.Properties;
import org.eclipse.daanse.xmla.model.xmla.PropertyList;
import org.eclipse.daanse.xmla.model.xmla.RequestTypeEnum;
import org.eclipse.daanse.xmla.model.xmla.RestrictionEntry;
import org.eclipse.daanse.xmla.model.xmla.Restrictions;
import org.eclipse.daanse.xmla.model.xmla.XmlaFactory;

/**
 * Builds the {@link Discover} the client sends.
 * <p>
 * The request type is a {@link RequestTypeEnum} rather than a string, so asking
 * for a rowset that does not exist is a compile error instead of a SOAP fault.
 * Restrictions stay strings — there the wire really is a name/value pair, and
 * which names are legal depends on the request type, which is what
 * {@code DISCOVER_SCHEMA_ROWSETS} exists to tell a client at runtime.
 *
 * <pre>{@code
 * Discover request = DiscoverRequest.of(RequestTypeEnum.DBSCHEMA_TABLES).restrict("TABLE_SCHEMA", "dbo")
 *         .property("Catalog", "FoodMart").build();
 * }</pre>
 */
public final class DiscoverRequest {

    private final Discover discover;

    private DiscoverRequest(RequestTypeEnum requestType) {
        this.discover = XmlaFactory.eINSTANCE.createDiscover();
        this.discover.setRequestType(requestType);
        Restrictions restrictions = XmlaFactory.eINSTANCE.createRestrictions();
        this.discover.setRestrictions(restrictions);
        Properties properties = XmlaFactory.eINSTANCE.createProperties();
        properties.setPropertyList(XmlaFactory.eINSTANCE.createPropertyList());
        this.discover.setProperties(properties);
    }

    public static DiscoverRequest of(RequestTypeEnum requestType) {
        return new DiscoverRequest(requestType);
    }

    /**
     * A request built from a typed restrictions object — the command produced from
     * what was set.
     * <p>
     * The request type is read off the object's EClass
     * ({@code MdschemaMembersRestrictions} names MDSCHEMA_MEMBERS), and every
     * {@code eIsSet} feature becomes one {@code <RestrictionList>} entry under its
     * wire name, in the order the RestrictionsMask is defined over. The setters
     * show what a server can be asked to filter by, typed.
     */
    public static DiscoverRequest of(org.eclipse.emf.ecore.EObject restrictions) {
        String requestType = org.eclipse.daanse.xmla.model.io.RestrictionsBinder.requestTypeOf(restrictions)
                .orElseThrow(() -> new IllegalArgumentException(restrictions.eClass().getName()
                        + " does not name a request type; only the model's <X>Restrictions " + "classes do"));
        DiscoverRequest request = new DiscoverRequest(RequestTypeEnum.getByName(requestType));
        request.discover.getRestrictions().getRestrictionList()
                .addAll(org.eclipse.daanse.xmla.model.io.RestrictionsBinder.entries(restrictions));
        return request;
    }

    /** Adds one {@code <RestrictionList>} child. */
    public DiscoverRequest restrict(String column, String value) {
        RestrictionEntry entry = XmlaFactory.eINSTANCE.createRestrictionEntry();
        entry.setName(column);
        entry.setValue(value);
        discover.getRestrictions().getRestrictionList().add(entry);
        return this;
    }

    /**
     * Sets one {@code <PropertyList>} child by its wire name.
     *
     * @throws IllegalArgumentException if the model has no such property
     */
    public DiscoverRequest property(String name, String value) {
        PropertyList list = discover.getProperties().getPropertyList();
        var feature = list.eClass().getEStructuralFeature(Character.toLowerCase(name.charAt(0)) + name.substring(1));
        if (feature == null) {
            throw new IllegalArgumentException("the model has no PropertyList property named " + name);
        }
        list.eSet(feature, org.eclipse.emf.ecore.util.EcoreUtil
                .createFromString((org.eclipse.emf.ecore.EDataType) feature.getEType(), value));
        return this;
    }

    /** The most common one, spelled out because nearly every request sets it. */
    public DiscoverRequest catalog(String catalog) {
        discover.getProperties().getPropertyList().setCatalog(catalog);
        return this;
    }

    public Discover build() {
        return discover;
    }
}
