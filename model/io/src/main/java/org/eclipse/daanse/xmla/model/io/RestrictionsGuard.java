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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.RestrictionEntry;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

/**
 * Every Discover's restrictions, validated against the model's metadata before
 * anything is dispatched.
 * <p>
 * The restrictions classes state per request type which restrictions exist,
 * what type each one has, and which are required - so one guard covers every
 * rowset with no code per rowset. The refusals are the live servers' own, word
 * for word:
 * <ul>
 * <li>an unknown restriction answers <em>"XML for Analysis parser: The
 * restriction, X, is not recognized by the server."</em> as a client fault —
 * including on the rowsets that accept no restrictions at all;</li>
 * <li>a value the restriction's type cannot parse answers <em>"The following
 * system error occurred:&nbsp; Type mismatch."</em> as a server fault, double
 * space and all;</li>
 * <li>a missing {@code [Required]} restriction answers <em>"XML for Analysis
 * parser: The X restriction is required but is missing from the
 * request."</em></li>
 * </ul>
 */
public final class RestrictionsGuard {

    /** One refusal: which SOAP fault kind, and the server's words for it. */
    public record Refusal(SoapFaultWriter.Kind kind, String message) {
    }

    private RestrictionsGuard() {
        // static access only
    }

    /**
     * @return the refusal a live server would answer, or empty when the request is
     *         fine
     */
    public static Optional<Refusal> validate(Discover discover) {
        String requestType = discover.getRequestType().getLiteral();
        Map<String, EStructuralFeature> known = knownRestrictions(requestType);

        if (discover.getRestrictions() != null) {
            for (RestrictionEntry entry : discover.getRestrictions().getRestrictionList()) {
                EStructuralFeature feature = known.get(entry.getName());
                if (feature == null) {
                    return Optional
                            .of(new Refusal(SoapFaultWriter.Kind.CLIENT, "XML for Analysis parser: The restriction, "
                                    + entry.getName() + ", is not recognized by the server."));
                }
                if (!parses(feature, entry.getValue())) {
                    return Optional.of(new Refusal(SoapFaultWriter.Kind.SERVER,
                            "The following system error occurred:  Type mismatch. "));
                }
            }
        }

        for (String required : RowsetCatalog.requiredRestrictionsOf(requestType)) {
            if (!has(discover, required)) {
                return Optional.of(new Refusal(SoapFaultWriter.Kind.CLIENT, "XML for Analysis parser: The " + required
                        + " restriction is required but is missing from the request."));
            }
        }
        return Optional.empty();
    }

    private static Map<String, EStructuralFeature> knownRestrictions(String requestType) {
        Map<String, EStructuralFeature> known = new LinkedHashMap<>();
        Optional<EClass> restrictions = RowsetCatalog.restrictionsClassFor(requestType);
        if (restrictions.isPresent()) {
            for (EStructuralFeature feature : restrictions.get().getEStructuralFeatures()) {
                String name = ExtendedMetaData.INSTANCE.getName(feature);
                known.put(name == null || name.isEmpty() ? feature.getName() : name, feature);
            }
        }
        return known;
    }

    /**
     * Whether the restriction's declared type accepts the value. A string
     * restriction accepts anything; the numeric and boolean ones are where a live
     * server answers its type mismatch, and where a connector would otherwise meet
     * the parse failure mid-answer.
     */
    private static boolean parses(EStructuralFeature feature, String value) {
        if (!(feature.getEType() instanceof EDataType dataType) || String.class.equals(dataType.getInstanceClass())) {
            return true;
        }
        try {
            EcoreUtil.createFromString(dataType, value == null ? "" : value);
            return true;
        } catch (RuntimeException notThatType) {
            return false;
        }
    }

    private static boolean has(Discover discover, String name) {
        if (discover.getRestrictions() == null) {
            return false;
        }
        for (RestrictionEntry entry : discover.getRestrictions().getRestrictionList()) {
            if (name.equals(entry.getName())) {
                return true;
            }
        }
        return false;
    }
}
