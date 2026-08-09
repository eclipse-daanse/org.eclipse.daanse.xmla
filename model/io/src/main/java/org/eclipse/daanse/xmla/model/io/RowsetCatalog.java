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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.daanse.xmla.model.rowset.RowsetPackage;
import org.eclipse.daanse.xmla.model.rowset.restrictions.RestrictionsPackage;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;

/**
 * Maps a Discover request type to the EClass that models its row, and back.
 * <p>
 * Built by scanning {@link RowsetPackage} for the {@code requestType}
 * annotation, so it cannot fall behind the model.
 */
public final class RowsetCatalog {

    /**
     * The annotation source carrying the XMLA and OLE DB facts that EMF has no
     * idiom for.
     */
    public static final String ANNOTATION = "https://www.daanse.org/spec/xmla/rowset/1.0";

    private static final Map<String, EClass> BY_REQUEST_TYPE;
    private static final Map<EClass, String> BY_ECLASS;
    private static final Map<String, EClass> RESTRICTIONS_BY_REQUEST_TYPE;

    static {
        Map<String, EClass> byRequestType = new LinkedHashMap<>();
        Map<EClass, String> byEClass = new LinkedHashMap<>();
        for (EClassifier classifier : RowsetPackage.eINSTANCE.getEClassifiers()) {
            if (!(classifier instanceof EClass eClass)) {
                continue;
            }
            String requestType = detail(eClass, "requestType");
            if (requestType != null) {
                byRequestType.put(requestType, eClass);
                byEClass.put(eClass, requestType);
            }
        }
        Map<String, EClass> restrictionsByRequestType = new LinkedHashMap<>();
        for (EClassifier classifier : RestrictionsPackage.eINSTANCE.getEClassifiers()) {
            if (!(classifier instanceof EClass eClass)) {
                continue;
            }
            String requestType = detail(eClass, "requestType");
            if (requestType != null && "restrictions".equals(detail(eClass, "role"))) {
                restrictionsByRequestType.put(requestType, eClass);
            }
        }
        BY_REQUEST_TYPE = Collections.unmodifiableMap(byRequestType);
        BY_ECLASS = Collections.unmodifiableMap(byEClass);
        RESTRICTIONS_BY_REQUEST_TYPE = Collections.unmodifiableMap(restrictionsByRequestType);
    }

    private RowsetCatalog() {
        // static access only
    }

    public static Optional<EClass> forRequestType(String requestType) {
        return Optional.ofNullable(BY_REQUEST_TYPE.get(requestType));
    }

    public static Optional<String> requestTypeOf(EClass eClass) {
        return Optional.ofNullable(BY_ECLASS.get(eClass));
    }

    /** Every request type the model describes, in model order. */
    public static Set<String> requestTypes() {
        return BY_REQUEST_TYPE.keySet();
    }

    /**
     * The OLE DB schema GUID, as reported by {@code DISCOVER_SCHEMA_ROWSETS}.
     * Absent for the rowsets no specification assigns one to.
     */
    public static Optional<String> guidOf(EClass eClass) {
        return Optional.ofNullable(detail(eClass, "guid"));
    }

    /**
     * Where this rowset's column list comes from: {@code MS-SSAS-251031},
     * {@code OLEDB-APPENDIX-B}, {@code INFERRED} or {@code PROPRIETARY}.
     */
    public static Optional<String> sourceOf(EClass eClass) {
        return Optional.ofNullable(detail(eClass, "source"));
    }

    /** Whether a {@code DiscoverService} method exists for this rowset. */
    public static boolean isServed(EClass eClass) {
        return Boolean.parseBoolean(detail(eClass, "served"));
    }

    /**
     * The restrictions EClass for a request type — what a request may be restricted
     * by, as a class a client can instantiate and set. Empty for the two rowsets no
     * server advertises restrictions for.
     */
    public static Optional<EClass> restrictionsClassFor(String requestType) {
        return Optional.ofNullable(RESTRICTIONS_BY_REQUEST_TYPE.get(requestType));
    }

    /**
     * The restrictions [MS-SSAS] marks {@code [Required]} for this request type, by
     * wire name.
     * <p>
     * Five rowsets carry them, and the live servers enforce them - MDSCHEMA_ACTIONS
     * without a CUBE_NAME answers a parse fault, not an empty rowset. The flag sits
     * on the restrictions feature under the project annotation.
     */
    public static List<String> requiredRestrictionsOf(String requestType) {
        EClass restrictions = RESTRICTIONS_BY_REQUEST_TYPE.get(requestType);
        if (restrictions == null) {
            return List.of();
        }
        List<String> required = new java.util.ArrayList<>();
        for (org.eclipse.emf.ecore.EStructuralFeature feature : restrictions.getEStructuralFeatures()) {
            org.eclipse.emf.ecore.EAnnotation annotation = feature.getEAnnotation(ANNOTATION);
            if (annotation != null && "true".equals(annotation.getDetails().get("required"))) {
                String name = org.eclipse.emf.ecore.util.ExtendedMetaData.INSTANCE.getName(feature);
                required.add(name == null || name.isEmpty() ? feature.getName() : name);
            }
        }
        return required;
    }

    /**
     * The restrictions this rowset accepts, in the order their ordinal gives them.
     * <p>
     * The order is not presentation: {@code RestrictionsMask} in
     * {@code DISCOVER_SCHEMA_ROWSETS} is a bitmask over exactly these positions, so
     * a client reading the mask and a server writing it have to be counting the
     * same list. That list is the feature order of the rowset's restrictions
     * EClass; the wire name is each feature's {@code ExtendedMetaData} name and the
     * XSD type follows from its datatype - the class is the single statement of all
     * three. The order is the one the live servers state in the very rowset that
     * carries the mask, not the specification's prose table, whose column order is
     * a reading convenience; the two differ for ten rowsets including
     * MDSCHEMA_CUBES and MDSCHEMA_MEMBERS.
     */
    public static List<Restriction> restrictionsOf(EClass eClass) {
        String requestType = BY_ECLASS.get(eClass);
        if (requestType == null) {
            return List.of();
        }
        EClass restrictions = RESTRICTIONS_BY_REQUEST_TYPE.get(requestType);
        if (restrictions == null) {
            return List.of();
        }
        List<Restriction> result = new ArrayList<>();
        int ordinal = 0;
        for (org.eclipse.emf.ecore.EStructuralFeature feature : restrictions.getEStructuralFeatures()) {
            result.add(new Restriction(ordinal++, org.eclipse.emf.ecore.util.ExtendedMetaData.INSTANCE.getName(feature),
                    RowsetSchemaWriter.xsdTypeOf(feature)));
        }
        return Collections.unmodifiableList(result);
    }

    /**
     * The {@code RestrictionsMask} for a rowset: bit <em>n</em> set for restriction
     * <em>n</em>.
     * <p>
     * {@code unsignedLong} on the wire, so a {@code BigInteger} — a rowset with
     * more than sixty-three restrictions is not hypothetical, DISCOVER_XML_METADATA
     * has twenty-seven and the mask is defined over all of them.
     */
    public static BigInteger restrictionsMaskOf(EClass eClass) {
        BigInteger mask = BigInteger.ZERO;
        for (Restriction restriction : restrictionsOf(eClass)) {
            mask = mask.setBit(restriction.ordinal());
        }
        return mask;
    }

    /**
     * DISCOVER_SCHEMA_ROWSETS, computed from the model.
     * <p>
     * This is the rowset by which a server states what it can be asked and how: for
     * each rowset the restrictions it honours, in order, and a mask whose bit
     * <em>n</em> says restriction <em>n</em> is supported. Every part of that is in
     * the model - which is why it can be derived rather than kept.
     */
    public static List<EObject> schemaRowsets() {
        return schemaRowsets(BY_REQUEST_TYPE.keySet());
    }

    /**
     * The same self-description, restricted to the request types a server actually
     * serves.
     * <p>
     * A server that answers a rowset it never announced is a nuisance; one that
     * announces a rowset it does not answer is worse, because a client believes it.
     * With the rowsets registered as services, the served set is known exactly -
     * and everything the row needs beyond the name is in the model: the GUID, the
     * restrictions with their names and XSD types, and the mask over their
     * ordinals.
     */
    public static List<EObject> schemaRowsets(java.util.Set<String> requestTypes) {
        List<EObject> rows = new ArrayList<>();
        for (Map.Entry<String, EClass> entry : BY_REQUEST_TYPE.entrySet()) {
            if (!requestTypes.contains(entry.getKey())) {
                continue;
            }
            EClass row = RowsetPackage.eINSTANCE.getDiscoverSchemaRowsetsRow();
            EObject described = EcoreUtil.create(row);
            described.eSet(row.getEStructuralFeature("schemaName"), entry.getKey());
            guidOf(entry.getValue()).filter(guid -> !guid.isEmpty())
                    .ifPresent(guid -> described.eSet(row.getEStructuralFeature("schemaGuid"), guid));
            described.eSet(row.getEStructuralFeature("restrictionsMask"), restrictionsMaskOf(entry.getValue()));

            EClass restriction = RowsetPackage.eINSTANCE.getSchemaRowsetRestriction();
            @SuppressWarnings("unchecked")
            List<EObject> into = (List<EObject>) described.eGet(row.getEStructuralFeature("restrictions"));
            for (Restriction each : restrictionsOf(entry.getValue())) {
                EObject one = EcoreUtil.create(restriction);
                one.eSet(restriction.getEStructuralFeature("name"), each.name());
                one.eSet(restriction.getEStructuralFeature("type"), each.type());
                into.add(one);
            }
            rows.add(described);
        }
        return rows;
    }

    /**
     * One restriction of one rowset: where it sits, what it is called, how it is
     * typed.
     */
    public record Restriction(int ordinal, String name, String type) {
    }

    private static String detail(EClass eClass, String key) {
        EAnnotation annotation = eClass.getEAnnotation(ANNOTATION);
        return annotation == null ? null : annotation.getDetails().get(key);
    }
}
