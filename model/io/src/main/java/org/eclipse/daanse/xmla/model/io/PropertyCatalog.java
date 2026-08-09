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
package org.eclipse.daanse.xmla.model.io;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.xmla.model.xmla.XmlaPackage;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

/**
 * The XMLA properties, read from the model.
 * <p>
 * DISCOVER_PROPERTIES answers, for every property, its name, type, access and
 * default. Those facts sit on the {@code PropertyList} features of the xmla
 * model - wire name and type in the EMF metadata, access and default in the
 * {@code property/1.0} annotation the specification document was read into,
 * description in the GenModel documentation. This class only reads.
 * <p>
 * A feature without the annotation carries no specification facts and is not a
 * catalog entry: the request reader still accepts it, but a server cannot
 * describe what the document does not describe. Features marked
 * {@code extension} are vendor properties beyond the document, carried
 * knowingly.
 */
public final class PropertyCatalog {

    /** The annotation source carrying the property facts EMF has no idiom for. */
    public static final String ANNOTATION = "https://www.daanse.org/spec/xmla/property/1.0";

    /**
     * One XMLA property: wire name, the {@code PropertyType} token the servers
     * send, the {@code PropertyAccessType} token, the specification's default, the
     * specification's description.
     */
    public record Property(String name, String type, String access, Optional<String> defaultValue, String description) {
    }

    private static final Map<String, Property> BY_NAME;

    static {
        Map<String, Property> byName = new LinkedHashMap<>();
        for (EStructuralFeature feature : XmlaPackage.eINSTANCE.getPropertyList().getEAllStructuralFeatures()) {
            EAnnotation annotation = feature.getEAnnotation(ANNOTATION);
            if (annotation == null) {
                continue;
            }
            String name = ExtendedMetaData.INSTANCE.getName(feature);
            String access = annotation.getDetails().get("access");
            if (access == null) {
                throw new IllegalStateException("the property " + name + " is annotated but carries no access");
            }
            Optional<String> defaultValue = Optional.ofNullable(annotation.getDetails().get("default"));
            String description = EcoreUtil.getDocumentation(feature);
            byName.put(name, new Property(name, typeOf(feature, name), access, defaultValue,
                    description == null ? "" : description));
        }
        BY_NAME = Collections.unmodifiableMap(byName);
    }

    private PropertyCatalog() {
        // static access only
    }

    /**
     * Every property the model states facts for, in the model's order, keyed by
     * wire name.
     */
    public static Map<String, Property> properties() {
        return BY_NAME;
    }

    public static Optional<Property> byName(String wireName) {
        return Optional.ofNullable(BY_NAME.get(wireName));
    }

    /**
     * The {@code PropertyType} vocabulary is the one the live servers send: string,
     * int, boolean, double, long. A datatype outside it is a modelling error and
     * fails loudly.
     */
    private static String typeOf(EStructuralFeature feature, String name) {
        EClassifier type = feature.getEType();
        if (type == EcorePackage.Literals.ESTRING) {
            return "string";
        }
        if (type == EcorePackage.Literals.EINTEGER_OBJECT) {
            return "int";
        }
        if (type == EcorePackage.Literals.EBOOLEAN_OBJECT) {
            return "boolean";
        }
        if (type == EcorePackage.Literals.EDOUBLE_OBJECT) {
            return "double";
        }
        if (type == EcorePackage.Literals.ELONG_OBJECT) {
            return "long";
        }
        throw new IllegalStateException(
                "the property " + name + " has the datatype " + type.getName() + ", which no server vocabulary covers");
    }
}
