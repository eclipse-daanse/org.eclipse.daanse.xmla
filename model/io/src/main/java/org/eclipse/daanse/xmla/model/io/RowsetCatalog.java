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
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.daanse.xmla.model.rowset.core.RowsetCorePackage;
import org.eclipse.daanse.xmla.model.rowset.relational.RowsetRelationalPackage;
import org.eclipse.daanse.xmla.model.rowset.multidimensional.RowsetMultidimensionalPackage;
import org.eclipse.daanse.xmla.model.rowset.mining.RowsetMiningPackage;
import org.eclipse.daanse.xmla.model.rowset.server.RowsetServerPackage;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;

/**
 * Maps a Discover request type to the EClass that models its row, and back.
 * <p>
 * Built by reading the {@code requestType} annotation off the rowset packages,
 * so it cannot fall behind the model.
 * <p>
 * The packages are named in {@link #MODELS}, rather than discovered: EMF's
 * global registry cannot be enumerated. {@link #use(Collection)} overrides that
 * list.
 */
public final class RowsetCatalog {

    /**
     * The annotation source carrying the XMLA and OLE DB facts that EMF has no
     * idiom for.
     */
    public static final String ANNOTATION = "https://www.daanse.org/spec/xmla/rowset/1.0";

    /**
     * One package per kind of rowset. Each holds its rowsets and the classes saying
     * what may restrict them; the annotation tells those apart, not the package.
     */
    private static final List<EPackage> MODELS = List.of(RowsetCorePackage.eINSTANCE, RowsetRelationalPackage.eINSTANCE,
            RowsetMultidimensionalPackage.eINSTANCE, RowsetMiningPackage.eINSTANCE, RowsetServerPackage.eINSTANCE);

    private static volatile Map<String, EClass> BY_REQUEST_TYPE = null;

    private static volatile Map<EClass, String> BY_ECLASS = null;
    private static volatile Map<String, EClass> RESTRICTIONS_BY_REQUEST_TYPE = null;
    private static volatile List<EEnum> ENUMERATORS = null;

    private RowsetCatalog() {
        // static access only
    }

    /**
     * Takes the packages describing rowsets, and describes what they say.
     * <p>
     * Every class is read and the annotations decide: {@code requestType} makes it
     * a row, {@code role="restrictions"} beside it makes it what may restrict one.
     * A package holding neither contributes nothing rather than being an error.
     *
     * @throws IllegalArgumentException if no package describes a request type at
     *                                  all, which is a wiring mistake rather than
     *                                  an empty model
     */
    public static void use(Collection<EPackage> packages) {
        Map<String, EClass> byRequestType = new LinkedHashMap<>();
        Map<EClass, String> byEClass = new LinkedHashMap<>();
        Map<String, EClass> restrictionsByRequestType = new LinkedHashMap<>();
        List<EEnum> enumerators = new ArrayList<>();
        for (EPackage ePackage : packages) {
            for (EClassifier classifier : ePackage.getEClassifiers()) {
                if (classifier instanceof EEnum eEnum && "enumerator".equals(detail(eEnum, "role"))) {
                    enumerators.add(eEnum);
                    continue;
                }
                if (!(classifier instanceof EClass eClass)) {
                    continue;
                }
                String requestType = detail(eClass, "requestType");
                if (requestType == null) {
                    continue;
                }
                if ("restrictions".equals(detail(eClass, "role"))) {
                    restrictionsByRequestType.put(requestType, eClass);
                } else {
                    byRequestType.put(requestType, eClass);
                    byEClass.put(eClass, requestType);
                }
            }
        }
        if (byRequestType.isEmpty()) {
            throw new IllegalArgumentException("none of the " + packages.size()
                    + " package(s) describes a rowset: a catalogue that knows no request type would "
                    + "answer 'no such rowset' to everything, which is not the same as an empty model");
        }
        // BY_REQUEST_TYPE last: rowsets() gates on it without the lock, so a
        // thread that sees it set must already see the other three.
        BY_ECLASS = Collections.unmodifiableMap(byEClass);
        RESTRICTIONS_BY_REQUEST_TYPE = Collections.unmodifiableMap(restrictionsByRequestType);
        ENUMERATORS = List.copyOf(enumerators);
        BY_REQUEST_TYPE = Collections.unmodifiableMap(byRequestType);
    }

    /**
     * The enumerations {@code DISCOVER_ENUMERATORS} reports, in model order. Found
     * by the {@code role="enumerator"} annotation, like everything else here.
     */
    public static List<EEnum> enumerators() {
        rowsets();
        return ENUMERATORS;
    }

    /**
     * The rowsets, reading {@link #MODELS} the first time anybody asks. A host that
     * wants other packages calls {@link #use(Collection)} before the first read.
     */
    private static Map<String, EClass> rowsets() {
        Map<String, EClass> known = BY_REQUEST_TYPE;
        if (known == null) {
            synchronized (RowsetCatalog.class) {
                if (BY_REQUEST_TYPE == null) {
                    use(MODELS);
                }
                known = BY_REQUEST_TYPE;
            }
        }
        return known;
    }

    public static Optional<EClass> forRequestType(String requestType) {
        return Optional.ofNullable(rowsets().get(requestType));
    }

    public static Optional<String> requestTypeOf(EClass eClass) {
        rowsets();
        return Optional.ofNullable(BY_ECLASS.get(eClass));
    }

    /** Every request type the model describes, in model order. */
    public static Set<String> requestTypes() {
        return rowsets().keySet();
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
     * {@code MS-SSAS-T-20210406}, {@code OLEDB-APPENDIX-B}, {@code INFERRED},
     * {@code PROPRIETARY} or {@code OBSERVED}.
     * <p>
     * {@code OBSERVED} is the weakest: no specification describes the rowset, and
     * the columns are those a real server answered with. The {@code observedFrom}
     * detail beside it names the recorded answer.
     */
    public static Optional<String> sourceOf(EClass eClass) {
        return Optional.ofNullable(detail(eClass, "source"));
    }

    /**
     * The restrictions EClass for a request type — what a request may be restricted
     * by, as a class a client can instantiate and set. Empty for the two rowsets no
     * server advertises restrictions for.
     */
    public static Optional<EClass> restrictionsClassFor(String requestType) {
        rowsets();
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
        rowsets();
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
     * {@code DISCOVER_SCHEMA_ROWSETS} is a bitmask over exactly these positions.
     * The list follows the feature order of the rowset's restrictions EClass, which
     * is the order a live server states in the rowset carrying the mask rather than
     * the specification's prose table - the two differ for ten rowsets, among them
     * MDSCHEMA_CUBES and MDSCHEMA_MEMBERS.
     */
    public static List<Restriction> restrictionsOf(EClass eClass) {
        rowsets();
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
     * {@code unsignedLong} on the wire, so a {@code BigInteger}: the mask is
     * defined over every restriction a rowset has, and that count is not bounded by
     * sixty-three.
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
        return schemaRowsets(rowsets().keySet());
    }

    /**
     * The same self-description, restricted to the request types a server actually
     * serves, so that it announces no rowset it cannot answer.
     */
    public static List<EObject> schemaRowsets(java.util.Set<String> requestTypes) {
        List<EObject> rows = new ArrayList<>();
        for (Map.Entry<String, EClass> entry : rowsets().entrySet()) {
            if (!requestTypes.contains(entry.getKey())) {
                continue;
            }
            // The rowset that describes rowsets is itself one of them, so it is
            // looked up the same way as any other rather than named in code.
            EClass row = rowsets().get("DISCOVER_SCHEMA_ROWSETS");
            if (row == null) {
                throw new IllegalStateException("the catalogue has no DISCOVER_SCHEMA_ROWSETS, so it "
                        + "cannot state what it holds; the packages given to use() are incomplete");
            }
            EObject described = EcoreUtil.create(row);
            described.eSet(row.getEStructuralFeature("schemaName"), entry.getKey());
            guidOf(entry.getValue()).filter(guid -> !guid.isEmpty())
                    .ifPresent(guid -> described.eSet(row.getEStructuralFeature("schemaGuid"), guid));
            described.eSet(row.getEStructuralFeature("restrictionsMask"), restrictionsMaskOf(entry.getValue()));
            provenanceOf(entry.getValue())
                    .ifPresent(text -> described.eSet(row.getEStructuralFeature("description"), text));

            // And the type of one restriction is what the feature holding them says
            // it is, which is the model stating it once instead of twice.
            EStructuralFeature restrictions = row.getEStructuralFeature("restrictions");
            EClass restriction = (EClass) restrictions.getEType();
            @SuppressWarnings("unchecked")
            List<EObject> into = (List<EObject>) described.eGet(restrictions);
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
     * Where a rowset comes from, as the {@code Description} column states it.
     * <p>
     * Most of what this server offers is not named by [MS-SSAS]: of the 34
     * relational rowsets only four are, the rest being OLE DB's own, carried over
     * so a consumer speaking OLE DB finds them under the name and GUID it knows.
     * {@code Description} is where that difference belongs - not the name, which is
     * what a client searches by.
     *
     * @return the text, or empty when the model records no source
     */
    private static Optional<String> provenanceOf(EClass rowset) {
        String source = detail(rowset, "source");
        if (source == null || source.isEmpty()) {
            return Optional.empty();
        }
        String url = detail(rowset, "specUrl");
        String text = switch (source) {
        case "OLEDB-APPENDIX-B" -> "OLE DB schema rowset (Appendix B), offered beyond what [MS-SSAS] names";
        case "PROPRIETARY" -> "Provider-specific rowset, named by neither [MS-SSAS] nor OLE DB";
        case "INFERRED" -> "Inferred from a related rowset; not separately documented";
        default -> "[MS-SSAS] " + source;
        };
        return Optional.of(url == null || url.isEmpty() ? text : text + " — " + url);
    }

    /**
     * One restriction of one rowset: where it sits, what it is called, how it is
     * typed.
     */
    public record Restriction(int ordinal, String name, String type) {
    }

    private static String detail(org.eclipse.emf.ecore.EModelElement element, String key) {
        EAnnotation annotation = element.getEAnnotation(ANNOTATION);
        return annotation == null ? null : annotation.getDetails().get(key);
    }
}
