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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.model.xmla.Discover;
import org.eclipse.daanse.xmla.model.xmla.RestrictionEntry;
import org.eclipse.daanse.xmla.model.xmla.XmlaFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

/**
 * Between the wire's name/value pairs and the typed restrictions object.
 * <p>
 * A Discover carries its restrictions as {@code <RestrictionList>} children
 * whose element names are the restriction columns. The model states, per
 * rowset, what those may be: one {@code <X>Restrictions} EClass, a feature per
 * restriction, the wire name in {@code ExtendedMetaData}, the type on the
 * feature. This class is the two directions between those forms — a server
 * binds what arrived, a client turns what it set into entries. Only
 * {@code eIsSet} features travel, which is what makes "not set" and "set to
 * empty" distinguishable.
 */
public final class RestrictionsBinder {

    private RestrictionsBinder() {
        // static access only
    }

    /**
     * The typed restrictions object for a Discover, or empty when the model has no
     * restrictions class for its request type.
     * <p>
     * An entry naming no feature is skipped rather than refused: servers ignore
     * restrictions they do not know, and this binder is not the place to be
     * stricter than the protocol.
     */
    public static Optional<EObject> bind(Discover request) {
        Optional<EClass> restrictionsClass = RowsetCatalog.restrictionsClassFor(request.getRequestType().getLiteral());
        if (restrictionsClass.isEmpty()) {
            return Optional.empty();
        }
        EObject bound = EcoreUtil.create(restrictionsClass.get());
        if (request.getRestrictions() != null) {
            for (RestrictionEntry entry : request.getRestrictions().getRestrictionList()) {
                EStructuralFeature feature = featureFor(restrictionsClass.get(), entry.getName());
                if (feature != null && entry.getValue() != null) {
                    bound.eSet(feature, EcoreUtil.createFromString((org.eclipse.emf.ecore.EDataType) feature.getEType(),
                            entry.getValue()));
                }
            }
        }
        return Optional.of(bound);
    }

    /**
     * The wire entries for a restrictions object: one per {@code eIsSet} feature,
     * in feature order — which is the order the RestrictionsMask is defined over.
     */
    public static List<RestrictionEntry> entries(EObject restrictions) {
        List<RestrictionEntry> result = new ArrayList<>();
        for (EStructuralFeature feature : restrictions.eClass().getEStructuralFeatures()) {
            if (!restrictions.eIsSet(feature)) {
                continue;
            }
            RestrictionEntry entry = XmlaFactory.eINSTANCE.createRestrictionEntry();
            entry.setName(ExtendedMetaData.INSTANCE.getName(feature));
            entry.setValue(EcoreUtil.convertToString((org.eclipse.emf.ecore.EDataType) feature.getEType(),
                    restrictions.eGet(feature)));
            result.add(entry);
        }
        return result;
    }

    /** The request type a restrictions class belongs to. */
    public static Optional<String> requestTypeOf(EObject restrictions) {
        org.eclipse.emf.ecore.EAnnotation annotation = restrictions.eClass().getEAnnotation(RowsetCatalog.ANNOTATION);
        return annotation == null ? Optional.empty() : Optional.ofNullable(annotation.getDetails().get("requestType"));
    }

    private static EStructuralFeature featureFor(EClass restrictionsClass, String wireName) {
        for (EStructuralFeature feature : restrictionsClass.getEStructuralFeatures()) {
            if (wireName.equals(ExtendedMetaData.INSTANCE.getName(feature))) {
                return feature;
            }
        }
        return null;
    }
}
