/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 */
package org.eclipse.daanse.xmla.csdl.model.v2.edm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.daanse.xmla.csdl.model.v2.bi.BiFactory;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.BiPackage;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.KpiGoalType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.KpiStatusType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.SourceType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TAlignment;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TAssociationSet;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TContextualNameRule;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDefaultAggregateFunction;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntitySet;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.THierarchy;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TKpi;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TLevel;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMeasure;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMemberRef;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMemberRefs;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TProperty;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TPropertyRefs;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TSortDirection;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TStability;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TState;
import org.eclipse.daanse.xmla.csdl.model.v2.edm.util.EdmResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * Helper class for building CSDL-BI schemas programmatically for testing.
 * Provides factory methods and utilities for creating schema structures that
 * match the MS-CSDLBI specification examples.
 */
public class CsdlBiSchemaBuilderHelper {

    private final ResourceSet resourceSet;
    private final EdmFactory edmFactory;
    private final BiFactory biFactory;

    public CsdlBiSchemaBuilderHelper() {
        resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new EdmResourceFactoryImpl());
        resourceSet.getPackageRegistry().put(EdmPackage.eNS_URI, EdmPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(BiPackage.eNS_URI, BiPackage.eINSTANCE);
        edmFactory = EdmFactory.eINSTANCE;
        biFactory = BiFactory.eINSTANCE;
    }

    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public EdmFactory getEdmFactory() {
        return edmFactory;
    }

    public BiFactory getBiFactory() {
        return biFactory;
    }

    public String serializeSchemaToXml(TSchema schema) throws IOException {
        Resource resource = resourceSet.createResource(URI.createURI("temp.xml"));
        DocumentRoot root = edmFactory.createDocumentRoot();
        root.setSchema(schema);
        resource.getContents().add(root);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Map<String, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_FORMATTED, Boolean.TRUE);
        options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.FALSE);

        resource.save(baos, options);

        resource.getContents().clear();
        resourceSet.getResources().remove(resource);

        return baos.toString("UTF-8");
    }

    public String loadExpectedXml(Class<?> testClass, String resourceName) throws IOException {
        try (InputStream is = testClass.getResourceAsStream(resourceName)) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public TSchema loadSchemaFromXml(Class<?> testClass, String resourceName) throws IOException {
        try (InputStream is = testClass.getResourceAsStream(resourceName)) {
            Resource resource = resourceSet.createResource(URI.createURI(resourceName));
            resource.load(is, null);
            DocumentRoot root = (DocumentRoot) resource.getContents().get(0);
            return root.getSchema();
        }
    }

    public String normalizeWhitespace(String xml) {
        return xml.replaceAll(">[ \\t\\n\\r]+<", "><").replaceAll("[ \\t]+", " ").trim();
    }

    /**
     * Normalizes XML for comparison by: 1. Removing whitespace between elements 2.
     * Removing namespace prefixes (edm:, bi:, etc.) from element names 3. Removing
     * namespace declarations 4. Sorting attributes alphabetically within each
     * element This allows semantic comparison of XML documents that differ only in
     * namespace prefix usage and attribute ordering.
     */
    public String normalizeXmlForComparison(String xml) {
        // Remove XML declaration
        xml = xml.replaceAll("<\\?xml[^?]*\\?>", "");
        // Remove whitespace between elements
        xml = xml.replaceAll(">[ \\t\\n\\r]+<", "><");
        // Remove extra whitespace
        xml = xml.replaceAll("[ \\t]+", " ");
        // Remove namespace declarations (xmlns:xxx="...")
        xml = xml.replaceAll("\\s*xmlns:[^=]+=\"[^\"]*\"", "");
        xml = xml.replaceAll("\\s*xmlns=\"[^\"]*\"", "");
        // Remove namespace prefixes from element/attribute names (e.g., edm:Schema ->
        // Schema)
        xml = xml.replaceAll("<([a-zA-Z_][a-zA-Z0-9_]*):([a-zA-Z_][a-zA-Z0-9_]*)", "<$2");
        xml = xml.replaceAll("</([a-zA-Z_][a-zA-Z0-9_]*):([a-zA-Z_][a-zA-Z0-9_]*)", "</$2");
        // Remove prefixes from attributes
        xml = xml.replaceAll("([a-zA-Z_][a-zA-Z0-9_]*):([a-zA-Z_][a-zA-Z0-9_]*)=", "$2=");
        return xml.trim();
    }

    public void setBiVersion(TSchema schema, String version) {
        // Add bi:Version attribute to the schema using the anyAttribute FeatureMap
        // The bi:Version attribute uses the BI namespace URI
        org.eclipse.emf.ecore.EStructuralFeature versionFeature = org.eclipse.emf.ecore.util.ExtendedMetaData.INSTANCE
                .demandFeature(BiPackage.eNS_URI, "Version", false);
        schema.getAnyAttribute().add(versionFeature, version);
    }

    public void addEntitySet(EntityContainerType container, String name, String entityType, String docSummary,
            Boolean hidden) {
        EntitySetType entitySet = edmFactory.createEntitySetType();
        entitySet.setName(name);
        entitySet.setEntityType(entityType);

        if (docSummary != null) {
            TDocumentation doc = edmFactory.createTDocumentation();
            TText summary = edmFactory.createTText();
            summary.getMixed().add(FeatureMapUtil.createRawTextEntry(docSummary));
            doc.setSummary(summary);
            entitySet.setDocumentation(doc);
        }

        TEntitySet biEntitySet = biFactory.createTEntitySet();
        if (hidden != null && hidden) {
            biEntitySet.setHidden(true);
        }
        entitySet.setBiEntitySet(biEntitySet);
        container.getEntitySet().add(entitySet);
    }

    public void addAssociationSet(EntityContainerType container, String name, String association, String entitySet1,
            String entitySet2, TState state, Boolean hidden) {
        AssociationSetType assocSet = edmFactory.createAssociationSetType();
        assocSet.setName(name);
        assocSet.setAssociation(association);

        EndType end1 = edmFactory.createEndType();
        end1.setEntitySet(entitySet1);
        assocSet.getEnd().add(end1);

        EndType end2 = edmFactory.createEndType();
        end2.setEntitySet(entitySet2);
        assocSet.getEnd().add(end2);

        TAssociationSet biAssocSet = biFactory.createTAssociationSet();
        if (state != null) {
            biAssocSet.setState(state);
        }
        if (hidden != null && hidden) {
            biAssocSet.setHidden(true);
        }
        // Add bi:AssociationSet to the any FeatureMap
        assocSet.getAny().add(BiPackage.eINSTANCE.getDocumentRoot_AssociationSet(), biAssocSet);

        container.getAssociationSet().add(assocSet);
    }

    public void addAssociation(TSchema schema, String name, String role1, String type1, String mult1, String role2,
            String type2, String mult2) {
        TAssociation assoc = edmFactory.createTAssociation();
        assoc.setName(name);

        TAssociationEnd end1 = edmFactory.createTAssociationEnd();
        end1.setRole(role1);
        end1.setType(type1);
        end1.setMultiplicity(TMultiplicity.get(mult1));
        assoc.getEnd().add(end1);

        TAssociationEnd end2 = edmFactory.createTAssociationEnd();
        end2.setRole(role2);
        end2.setType(type2);
        end2.setMultiplicity(TMultiplicity.get(mult2));
        assoc.getEnd().add(end2);

        schema.getAssociation().add(assoc);
    }

    public TEntityType createEntityType(String name) {
        TEntityType entityType = edmFactory.createTEntityType();
        entityType.setName(name);

        TEntityKeyElement key = edmFactory.createTEntityKeyElement();
        TPropertyRef keyRef = edmFactory.createTPropertyRef();
        keyRef.setName("RowNumber");
        key.getPropertyRef().add(keyRef);
        entityType.setKey(key);

        return entityType;
    }

    public void addMemberRef(TMemberRefs refs, String name) {
        TMemberRef ref = biFactory.createTMemberRef();
        ref.setName(name);
        refs.getMemberRef().add(ref);
    }

    public void addRowNumberProperty(TEntityType entityType) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName("RowNumber");
        prop.setType("Int64");
        prop.setNullable(false);

        TProperty biProp = biFactory.createTProperty();
        biProp.setHidden(true);
        biProp.setContents("RowNumber");
        biProp.setStability(TStability.ROW_NUMBER);
        prop.setBiProperty(biProp);

        entityType.getProperty().add(prop);
    }

    public void addSimpleProperty(TEntityType entityType, String name, String type, boolean nullable) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType(type);
        if (!nullable) {
            prop.setNullable(false);
        }

        TProperty biProp = biFactory.createTProperty();
        prop.setBiProperty(biProp);

        entityType.getProperty().add(prop);
    }

    public void addSimpleStringProperty(TEntityType entityType, String name) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType("String");
        prop.setMaxLength(TMax.MAX);
        prop.setUnicode(true);
        prop.setFixedLength(false);

        TProperty biProp = biFactory.createTProperty();
        prop.setBiProperty(biProp);

        entityType.getProperty().add(prop);
    }

    public void addStringPropertyNotNull(TEntityType entityType, String name) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType("String");
        prop.setMaxLength(TMax.MAX);
        prop.setUnicode(true);
        prop.setFixedLength(false);
        prop.setNullable(false);

        TProperty biProp = biFactory.createTProperty();
        prop.setBiProperty(biProp);

        entityType.getProperty().add(prop);
    }

    public void addDecimalProperty(TEntityType entityType, String name, int precision, int scale) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType("Decimal");
        prop.setPrecision(BigInteger.valueOf(precision));
        prop.setScale(BigInteger.valueOf(scale));

        TProperty biProp = biFactory.createTProperty();
        prop.setBiProperty(biProp);

        entityType.getProperty().add(prop);
    }

    public void addPropertyWithCaption(TEntityType entityType, String name, String type, String caption, boolean hidden,
            String docSummary, String orderByProperty) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType(type);

        if (docSummary != null) {
            TDocumentation doc = edmFactory.createTDocumentation();
            TText summary = edmFactory.createTText();
            summary.getMixed().add(FeatureMapUtil.createRawTextEntry(docSummary));
            doc.setSummary(summary);
            prop.setDocumentation(doc);
        }

        TProperty biProp = biFactory.createTProperty();
        if (caption != null)
            biProp.setCaption(caption);
        if (hidden)
            biProp.setHidden(true);

        if (orderByProperty != null) {
            TPropertyRefs orderBy = biFactory.createTPropertyRefs();
            org.eclipse.daanse.xmla.csdl.model.v2.bi.TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName(orderByProperty);
            orderBy.getPropertyRef().add(propRef);
            biProp.setOrderBy(orderBy);
        }

        prop.setBiProperty(biProp);
        entityType.getProperty().add(prop);
    }

    public void addPropertyWithExtendedBi(TEntityType entityType, String name, String type, String contextualNameRule,
            String alignment, String units, String sortDirection, boolean isRightToLeft,
            TDefaultAggregateFunction aggFunc) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType(type);
        prop.setMaxLength(TMax.MAX);
        prop.setUnicode(true);
        prop.setFixedLength(false);

        TProperty biProp = biFactory.createTProperty();
        if (contextualNameRule != null) {
            biProp.setContextualNameRule(TContextualNameRule.get(contextualNameRule));
        }
        if (alignment != null) {
            biProp.setAlignment(TAlignment.get(alignment));
        }
        if (units != null) {
            biProp.setUnits(units);
        }
        if (sortDirection != null) {
            biProp.setSortDirection(TSortDirection.get(sortDirection));
        }
        biProp.setIsRightToLeft(isRightToLeft);
        if (aggFunc != null) {
            biProp.setDefaultAggregateFunction(aggFunc);
        }

        prop.setBiProperty(biProp);
        entityType.getProperty().add(prop);
    }

    public void addPropertyWithCaptionAndRef(TEntityType entityType, String name, String type, String caption,
            String referenceName) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType(type);

        TProperty biProp = biFactory.createTProperty();
        if (caption != null)
            biProp.setCaption(caption);
        if (referenceName != null)
            biProp.setReferenceName(referenceName);

        prop.setBiProperty(biProp);
        entityType.getProperty().add(prop);
    }

    public void addStringPropertyWithCaptionAndRef(TEntityType entityType, String name, boolean nullable,
            String caption, String referenceName) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType("String");
        prop.setMaxLength(TMax.MAX);
        prop.setUnicode(true);
        prop.setFixedLength(false);
        if (!nullable) {
            prop.setNullable(false);
        }

        TProperty biProp = biFactory.createTProperty();
        if (caption != null)
            biProp.setCaption(caption);
        if (referenceName != null)
            biProp.setReferenceName(referenceName);

        prop.setBiProperty(biProp);
        entityType.getProperty().add(prop);
    }

    public void addNavigationProperty(TEntityType entityType, String name, String relationship, String fromRole,
            String toRole, TContextualNameRule contextualNameRule) {
        TNavigationProperty navProp = edmFactory.createTNavigationProperty();
        navProp.setName(name);
        navProp.setRelationship(relationship);
        navProp.setFromRole(fromRole);
        navProp.setToRole(toRole);

        org.eclipse.daanse.xmla.csdl.model.v2.bi.TNavigationProperty biNavProp = biFactory.createTNavigationProperty();
        if (contextualNameRule != null)
            biNavProp.setContextualNameRule(contextualNameRule);

        // Add bi:NavigationProperty to the any FeatureMap
        navProp.getAny().add(BiPackage.eINSTANCE.getDocumentRoot_NavigationProperty(), biNavProp);

        entityType.getNavigationProperty().add(navProp);
    }

    public void addNavigationPropertyWithDoc(TEntityType entityType, String name, String relationship, String fromRole,
            String toRole, String docSummary, String caption, TContextualNameRule contextualNameRule) {
        TNavigationProperty navProp = edmFactory.createTNavigationProperty();
        navProp.setName(name);
        navProp.setRelationship(relationship);
        navProp.setFromRole(fromRole);
        navProp.setToRole(toRole);

        if (docSummary != null) {
            TDocumentation doc = edmFactory.createTDocumentation();
            TText summary = edmFactory.createTText();
            summary.getMixed().add(FeatureMapUtil.createRawTextEntry(docSummary));
            doc.setSummary(summary);
            navProp.setDocumentation(doc);
        }

        org.eclipse.daanse.xmla.csdl.model.v2.bi.TNavigationProperty biNavProp = biFactory.createTNavigationProperty();
        if (caption != null)
            biNavProp.setCaption(caption);
        if (contextualNameRule != null)
            biNavProp.setContextualNameRule(contextualNameRule);

        // Add bi:NavigationProperty to the any FeatureMap
        navProp.getAny().add(BiPackage.eINSTANCE.getDocumentRoot_NavigationProperty(), biNavProp);

        entityType.getNavigationProperty().add(navProp);
    }

    public void addNavigationPropertySimple(TEntityType entityType, String name, String relationship, String fromRole,
            String toRole) {
        TNavigationProperty navProp = edmFactory.createTNavigationProperty();
        navProp.setName(name);
        navProp.setRelationship(relationship);
        navProp.setFromRole(fromRole);
        navProp.setToRole(toRole);

        org.eclipse.daanse.xmla.csdl.model.v2.bi.TNavigationProperty biNavProp = biFactory.createTNavigationProperty();
        // Add bi:NavigationProperty to the any FeatureMap
        navProp.getAny().add(BiPackage.eINSTANCE.getDocumentRoot_NavigationProperty(), biNavProp);

        entityType.getNavigationProperty().add(navProp);
    }

    public void addMeasureProperty(TEntityType entityType, String name, String type, String caption,
            TContextualNameRule contextualNameRule, TAlignment alignment, String formatString, String units,
            TSortDirection sortDirection, boolean isRightToLeft, boolean isSimpleMeasure, String docSummary) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType(type);

        if (docSummary != null) {
            TDocumentation doc = edmFactory.createTDocumentation();
            TText summary = edmFactory.createTText();
            summary.getMixed().add(FeatureMapUtil.createRawTextEntry(docSummary));
            doc.setSummary(summary);
            prop.setDocumentation(doc);
        }

        TMeasure measure = biFactory.createTMeasure();
        if (caption != null)
            measure.setCaption(caption);
        if (contextualNameRule != null)
            measure.setContextualNameRule(contextualNameRule);
        if (alignment != null)
            measure.setAlignment(alignment);
        if (formatString != null)
            measure.setFormatString(formatString);
        if (units != null)
            measure.setUnits(units);
        if (sortDirection != null)
            measure.setSortDirection(sortDirection);
        measure.setIsRightToLeft(isRightToLeft);
        measure.setIsSimpleMeasure(isSimpleMeasure);

        prop.setBiMeasure(measure);
        entityType.getProperty().add(prop);
    }

    public void addMeasurePropertySimple(TEntityType entityType, String name, String type, int precision, int scale,
            String caption, String referenceName, String formatString) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType(type);
        prop.setPrecision(BigInteger.valueOf(precision));
        prop.setScale(BigInteger.valueOf(scale));

        TMeasure measure = biFactory.createTMeasure();
        if (caption != null)
            measure.setCaption(caption);
        if (referenceName != null)
            measure.setReferenceName(referenceName);
        if (formatString != null)
            measure.setFormatString(formatString);

        prop.setBiMeasure(measure);
        entityType.getProperty().add(prop);
    }

    public void addMeasurePropertyWithKpi(TEntityType entityType, String name, String type, int precision, int scale,
            String caption, String referenceName, String formatString, String docSummary, String statusGraphic,
            String goalProp, String statusProp) {
        TEntityProperty prop = edmFactory.createTEntityProperty();
        prop.setName(name);
        prop.setType(type);
        prop.setPrecision(BigInteger.valueOf(precision));
        prop.setScale(BigInteger.valueOf(scale));

        if (docSummary != null) {
            TDocumentation doc = edmFactory.createTDocumentation();
            TText summary = edmFactory.createTText();
            summary.getMixed().add(FeatureMapUtil.createRawTextEntry(docSummary));
            doc.setSummary(summary);
            prop.setDocumentation(doc);
        }

        TMeasure measure = biFactory.createTMeasure();
        if (caption != null)
            measure.setCaption(caption);
        if (referenceName != null)
            measure.setReferenceName(referenceName);
        if (formatString != null)
            measure.setFormatString(formatString);

        TKpi kpi = biFactory.createTKpi();
        if (statusGraphic != null)
            kpi.setStatusGraphic(statusGraphic);

        KpiGoalType kpiGoal = biFactory.createKpiGoalType();
        org.eclipse.daanse.xmla.csdl.model.v2.bi.TPropertyRef goalRef = biFactory.createTPropertyRef();
        goalRef.setName(goalProp);
        kpiGoal.setPropertyRef(goalRef);
        kpi.setKpiGoal(kpiGoal);

        KpiStatusType kpiStatus = biFactory.createKpiStatusType();
        org.eclipse.daanse.xmla.csdl.model.v2.bi.TPropertyRef statusRef = biFactory.createTPropertyRef();
        statusRef.setName(statusProp);
        kpiStatus.setPropertyRef(statusRef);
        kpi.setKpiStatus(kpiStatus);

        measure.setKpi(kpi);
        prop.setBiMeasure(measure);
        entityType.getProperty().add(prop);
    }

    public THierarchy createHierarchy(String name, String caption, String referenceName, String docSummary) {
        THierarchy hierarchy = biFactory.createTHierarchy();
        hierarchy.setName(name);
        if (caption != null)
            hierarchy.setCaption(caption);
        if (referenceName != null)
            hierarchy.setReferenceName(referenceName);

        if (docSummary != null) {
            org.eclipse.daanse.xmla.csdl.model.v2.bi.TDocumentation hierDoc = biFactory.createTDocumentation();
            hierDoc.setSummary(docSummary);
            hierarchy.setDocumentation(hierDoc);
        }

        return hierarchy;
    }

    public TLevel createLevel(String name, String sourceProperty) {
        TLevel level = biFactory.createTLevel();
        level.setName(name);

        if (sourceProperty != null) {
            SourceType source = biFactory.createSourceType();
            org.eclipse.daanse.xmla.csdl.model.v2.bi.TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName(sourceProperty);
            source.setPropertyRef(propRef);
            level.setSource(source);
        }

        return level;
    }
}
