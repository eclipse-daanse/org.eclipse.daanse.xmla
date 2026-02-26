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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.daanse.xmla.csdl.model.v2.bi.BiFactory;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.BiPackage;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.DefaultImageType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.DefaultMeasureType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.KpiGoalType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.KpiStatusType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.SourceType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TAggregationKind;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TAssociationSet;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TCompareOptions;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDefaultAggregateFunction;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDirectQueryMode;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDisplayFolder;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDisplayFolders;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDistributiveBy;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDocumentation;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityContainer;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityRef;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntitySet;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TGroupingBehavior;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.THierarchy;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.THierarchyRef;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TKpi;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TLevel;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMeasure;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMemberRef;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMemberRefs;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TNavigationProperty;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TProperty;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TPropertyRef;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TPropertyRefs;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TStability;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TState;
import org.eclipse.daanse.xmla.csdl.model.v2.edm.util.EdmResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for CSDLBI Schema serialization based on MS-CSDLBI specification.
 * <p>
 * Reference: [MS-CSDLBI] Conceptual Schema Definition File Format with Business
 * Intelligence Annotations Section 5.1: CSDLBI Schema 1.0 Section 5.2: CSDLBI
 * Schema 1.1
 */
public class CsdlBiSerializationTest {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CsdlBiSerializationTest.class);
    private ResourceSet resourceSet;
    private EdmFactory edmFactory;
    private BiFactory biFactory;

    @BeforeEach
    void setUp() {
        resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new EdmResourceFactoryImpl());
        resourceSet.getPackageRegistry().put(EdmPackage.eNS_URI, EdmPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(BiPackage.eNS_URI, BiPackage.eINSTANCE);
        edmFactory = EdmFactory.eINSTANCE;
        biFactory = BiFactory.eINSTANCE;
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.0 - TEntityContainer")
    class TEntityContainerTests {

        @Test
        void testEntityContainerWithCompareOptions() throws IOException {
            TEntityContainer biContainer = biFactory.createTEntityContainer();
            biContainer.setCaption("Sales Model");
            biContainer.setCulture("en-US");
            biContainer.setDirectQueryMode(TDirectQueryMode.IN_MEMORY);

            TCompareOptions compareOptions = biFactory.createTCompareOptions();
            compareOptions.setIgnoreCase(true);
            compareOptions.setIgnoreNonSpace(false);
            compareOptions.setIgnoreKanaType(true);
            compareOptions.setIgnoreWidth(false);
            biContainer.setCompareOptions(compareOptions);

            String xml = serializeToXml(biContainer);
            LOGGER.debug("=== TEntityContainer with CompareOptions (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEntityContainerDirectQueryModes() throws IOException {
            for (TDirectQueryMode mode : TDirectQueryMode.VALUES) {
                TEntityContainer biContainer = biFactory.createTEntityContainer();
                biContainer.setCaption("Model - " + mode.getLiteral());
                biContainer.setDirectQueryMode(mode);

                String xml = serializeToXml(biContainer);
                LOGGER.debug("=== TEntityContainer DirectQueryMode: " + mode.getLiteral() + " ===");
                LOGGER.debug(xml);
            }
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.0 - TEntitySet")
    class TEntitySetTests {

        @Test
        void testEntitySetWithAllAttributes() throws IOException {
            TEntitySet biEntitySet = biFactory.createTEntitySet();
            biEntitySet.setCaption("Products");
            biEntitySet.setCollectionCaption("Product Collection");
            biEntitySet.setReferenceName("ProductRef");
            biEntitySet.setHidden(false);
            biEntitySet.setLineageTag("product-lineage-123");
            biEntitySet.setPrivate(false);
            biEntitySet.setShowAsVariationsOnly(true);

            String xml = serializeToXml(biEntitySet);
            LOGGER.debug("=== TEntitySet with all attributes (Schema 1.0) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.0 - TAssociationSet")
    class TAssociationSetTests {

        @Test
        void testAssociationSetActive() throws IOException {
            TAssociationSet assocSet = biFactory.createTAssociationSet();
            assocSet.setState(TState.ACTIVE);
            assocSet.setHidden(false);

            String xml = serializeToXml(assocSet);
            LOGGER.debug("=== TAssociationSet Active (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testAssociationSetInactive() throws IOException {
            TAssociationSet assocSet = biFactory.createTAssociationSet();
            assocSet.setState(TState.INACTIVE);
            assocSet.setHidden(true);

            String xml = serializeToXml(assocSet);
            LOGGER.debug("=== TAssociationSet Inactive (Schema 1.0) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.0 - TEntityType")
    class TEntityTypeTests {

        @Test
        void testEntityTypeWithDisplayKey() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            TMemberRefs displayKey = biFactory.createTMemberRefs();
            TMemberRef memberRef = biFactory.createTMemberRef();
            memberRef.setName("ProductId");
            displayKey.getMemberRef().add(memberRef);
            entityType.setDisplayKey(displayKey);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType with DisplayKey (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEntityTypeWithDefaultDetails() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            TMemberRefs defaultDetails = biFactory.createTMemberRefs();
            TMemberRef memberRef1 = biFactory.createTMemberRef();
            memberRef1.setName("Name");
            TMemberRef memberRef2 = biFactory.createTMemberRef();
            memberRef2.setName("Description");
            defaultDetails.getMemberRef().add(memberRef1);
            defaultDetails.getMemberRef().add(memberRef2);
            entityType.setDefaultDetails(defaultDetails);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType with DefaultDetails (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEntityTypeWithDefaultImage() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            DefaultImageType defaultImage = biFactory.createDefaultImageType();
            TMemberRef imageRef = biFactory.createTMemberRef();
            imageRef.setName("ProductImage");
            defaultImage.setMemberRef(imageRef);
            entityType.setDefaultImage(defaultImage);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType with DefaultImage (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEntityTypeWithDefaultMeasure() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            DefaultMeasureType defaultMeasure = biFactory.createDefaultMeasureType();
            TMemberRef measureRef = biFactory.createTMemberRef();
            measureRef.setName("TotalSales");
            defaultMeasure.setMemberRef(measureRef);
            entityType.setDefaultMeasure(defaultMeasure);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType with DefaultMeasure (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEntityTypeWithSortMembers() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            TMemberRefs sortMembers = biFactory.createTMemberRefs();
            TMemberRef sortRef = biFactory.createTMemberRef();
            sortRef.setName("SortOrder");
            sortMembers.getMemberRef().add(sortRef);
            entityType.setSortMembers(sortMembers);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType with SortMembers (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEntityTypeWithDisplayFolders() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            TDisplayFolders displayFolders = biFactory.createTDisplayFolders();

            TDisplayFolder folder1 = biFactory.createTDisplayFolder();
            folder1.setName("Sales Metrics");
            folder1.setCaption("Sales Metrics Caption");

            TPropertyRef propRef1 = biFactory.createTPropertyRef();
            propRef1.setName("TotalRevenue");
            folder1.getPropertyRef().add(propRef1);

            TDisplayFolder nestedFolder = biFactory.createTDisplayFolder();
            nestedFolder.setName("Regional Sales");
            nestedFolder.setCaption("Regional Sales Caption");
            folder1.getDisplayFolder().add(nestedFolder);

            displayFolders.getDisplayFolder().add(folder1);
            entityType.setDisplayFolders(displayFolders);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType with DisplayFolders (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEntityTypeCalculationGroup() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");
            entityType.setIsCalculationGroup(true);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType IsCalculationGroup (Schema 1.0) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.0 - TProperty")
    class TPropertyTests {

        @Test
        void testPropertyWithOrderBy() throws IOException {
            TProperty biProperty = biFactory.createTProperty();
            biProperty.setCaption("Product Name");
            biProperty.setHidden(false);
            biProperty.setContents("Regular");

            TPropertyRefs orderBy = biFactory.createTPropertyRefs();
            TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName("SortOrder");
            orderBy.getPropertyRef().add(propRef);
            biProperty.setOrderBy(orderBy);

            String xml = serializeToXml(biProperty);
            LOGGER.debug("=== TProperty with OrderBy (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testPropertyWithDefaultAggregateFunction() throws IOException {
            for (TDefaultAggregateFunction aggFunc : TDefaultAggregateFunction.VALUES) {
                TProperty biProperty = biFactory.createTProperty();
                biProperty.setCaption("Amount - " + aggFunc.getLiteral());
                biProperty.setDefaultAggregateFunction(aggFunc);

                String xml = serializeToXml(biProperty);
                LOGGER.debug("=== TProperty DefaultAggregateFunction: " + aggFunc.getLiteral() + " ===");
                LOGGER.debug(xml);
            }
        }

        @Test
        void testPropertyWithGroupingBehavior() throws IOException {
            TProperty biProperty1 = biFactory.createTProperty();
            biProperty1.setCaption("Category");
            biProperty1.setGroupingBehavior(TGroupingBehavior.GROUP_ON_VALUE);

            TProperty biProperty2 = biFactory.createTProperty();
            biProperty2.setCaption("Product Key");
            biProperty2.setGroupingBehavior(TGroupingBehavior.GROUP_ON_ENTITY_KEY);

            String xml1 = serializeToXml(biProperty1);
            String xml2 = serializeToXml(biProperty2);
            LOGGER.debug("=== TProperty GroupingBehavior GroupOnValue ===");
            LOGGER.debug(xml1);
            LOGGER.debug("=== TProperty GroupingBehavior GroupOnEntityKey ===");
            LOGGER.debug(xml2);
        }

        @Test
        void testPropertyWithStability() throws IOException {
            for (TStability stability : TStability.VALUES) {
                TProperty biProperty = biFactory.createTProperty();
                biProperty.setCaption("Column - " + stability.getLiteral());
                biProperty.setStability(stability);

                String xml = serializeToXml(biProperty);
                LOGGER.debug("=== TProperty Stability: " + stability.getLiteral() + " ===");
                LOGGER.debug(xml);
            }
        }

        @Test
        void testPropertyWithAllAttributes() throws IOException {
            TProperty biProperty = biFactory.createTProperty();
            biProperty.setCaption("Full Property");
            biProperty.setHidden(false);
            biProperty.setPrivate(false);
            biProperty.setContents("Regular");
            biProperty.setDefaultAggregateFunction(TDefaultAggregateFunction.SUM);
            biProperty.setGroupingBehavior(TGroupingBehavior.GROUP_ON_VALUE);
            biProperty.setStability(TStability.STABLE);
            biProperty.setAggregateBehavior(true);
            biProperty.setLocaleImpact(false);
            biProperty.setLineageTag("property-lineage-456");
            biProperty.setIsError(false);

            String xml = serializeToXml(biProperty);
            LOGGER.debug("=== TProperty with all attributes (Schema 1.0) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.0 - TMeasure")
    class TMeasureTests {

        @Test
        void testMeasureWithDistributiveBy() throws IOException {
            TMeasure biMeasure = biFactory.createTMeasure();
            biMeasure.setCaption("Total Sales");
            biMeasure.setIsSimpleMeasure(true);
            biMeasure.setContents("Regular");
            biMeasure.setPrivate(false);

            TDistributiveBy distributiveBy = biFactory.createTDistributiveBy();
            distributiveBy.setAggregationKind(TAggregationKind.SUM);

            TEntityRef entityRef = biFactory.createTEntityRef();
            entityRef.setName("SalesTable");
            distributiveBy.setEntityRef(entityRef);

            biMeasure.setDistributiveBy(distributiveBy);

            String xml = serializeToXml(biMeasure);
            LOGGER.debug("=== TMeasure with DistributiveBy (Schema 1.0) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testMeasureAggregationKinds() throws IOException {
            for (TAggregationKind aggKind : TAggregationKind.VALUES) {
                TMeasure biMeasure = biFactory.createTMeasure();
                biMeasure.setCaption("Measure - " + aggKind.getLiteral());

                TDistributiveBy distributiveBy = biFactory.createTDistributiveBy();
                distributiveBy.setAggregationKind(aggKind);
                TEntityRef entityRef = biFactory.createTEntityRef();
                entityRef.setName("FactTable");
                distributiveBy.setEntityRef(entityRef);
                biMeasure.setDistributiveBy(distributiveBy);

                String xml = serializeToXml(biMeasure);
                LOGGER.debug("=== TMeasure AggregationKind: " + aggKind.getLiteral() + " ===");
                LOGGER.debug(xml);
            }
        }

        @Test
        void testMeasureWithAllAttributes() throws IOException {
            TMeasure biMeasure = biFactory.createTMeasure();
            biMeasure.setCaption("Complete Measure");
            biMeasure.setHidden(false);
            biMeasure.setPrivate(false);
            biMeasure.setIsSimpleMeasure(false);
            biMeasure.setContents("SumOfQuantity");
            biMeasure.setLineageTag("measure-lineage-789");
            biMeasure.setIsError(false);

            String xml = serializeToXml(biMeasure);
            LOGGER.debug("=== TMeasure with all attributes (Schema 1.0) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.0 - TNavigationProperty")
    class TNavigationPropertyTests {

        @Test
        void testNavigationProperty() throws IOException {
            TNavigationProperty navProp = biFactory.createTNavigationProperty();
            navProp.setCaption("Related Products");
            navProp.setCollectionCaption("Product Collection");
            navProp.setHidden(false);
            navProp.setReferenceName("ProductNavRef");

            String xml = serializeToXml(navProp);
            LOGGER.debug("=== TNavigationProperty (Schema 1.0) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.1 - THierarchy")
    class THierarchyTests {

        @Test
        void testHierarchyWithLevels() throws IOException {
            THierarchy hierarchy = biFactory.createTHierarchy();
            hierarchy.setName("DateHierarchy");
            hierarchy.setCaption("Date Hierarchy");
            hierarchy.setHidden(false);
            hierarchy.setLineageTag("hierarchy-lineage-001");

            TDocumentation doc = biFactory.createTDocumentation();
            doc.setSummary("Hierarchy for date dimensions");
            hierarchy.setDocumentation(doc);

            TLevel yearLevel = biFactory.createTLevel();
            yearLevel.setName("Year");
            yearLevel.setCaption("Calendar Year");
            yearLevel.setLineageTag("level-year-001");

            TLevel quarterLevel = biFactory.createTLevel();
            quarterLevel.setName("Quarter");
            quarterLevel.setCaption("Calendar Quarter");
            quarterLevel.setLineageTag("level-quarter-001");

            TLevel monthLevel = biFactory.createTLevel();
            monthLevel.setName("Month");
            monthLevel.setCaption("Calendar Month");
            monthLevel.setLineageTag("level-month-001");

            hierarchy.getLevel().add(yearLevel);
            hierarchy.getLevel().add(quarterLevel);
            hierarchy.getLevel().add(monthLevel);

            String xml = serializeToXml(hierarchy);
            LOGGER.debug("=== THierarchy with Levels (Schema 1.1) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testHierarchyLevelWithSource() throws IOException {
            THierarchy hierarchy = biFactory.createTHierarchy();
            hierarchy.setName("ProductHierarchy");
            hierarchy.setCaption("Product Hierarchy");

            TLevel categoryLevel = biFactory.createTLevel();
            categoryLevel.setName("Category");
            categoryLevel.setCaption("Product Category");

            SourceType source = biFactory.createSourceType();
            TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName("CategoryColumn");
            source.setPropertyRef(propRef);
            categoryLevel.setSource(source);

            TDocumentation levelDoc = biFactory.createTDocumentation();
            levelDoc.setSummary("Product category level");
            categoryLevel.setDocumentation(levelDoc);

            hierarchy.getLevel().add(categoryLevel);

            String xml = serializeToXml(hierarchy);
            LOGGER.debug("=== THierarchy Level with Source (Schema 1.1) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.1 - TEntityType with Hierarchy")
    class TEntityTypeWithHierarchyTests {

        @Test
        void testEntityTypeWithHierarchies() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            THierarchy dateHierarchy = biFactory.createTHierarchy();
            dateHierarchy.setName("DateHierarchy");
            dateHierarchy.setCaption("Date Hierarchy");

            TLevel yearLevel = biFactory.createTLevel();
            yearLevel.setName("Year");
            yearLevel.setCaption("Year");
            dateHierarchy.getLevel().add(yearLevel);

            TLevel monthLevel = biFactory.createTLevel();
            monthLevel.setName("Month");
            monthLevel.setCaption("Month");
            dateHierarchy.getLevel().add(monthLevel);

            entityType.getHierarchy().add(dateHierarchy);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType with Hierarchy (Schema 1.1) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEntityTypeWithDisplayFoldersAndHierarchyRef() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            TDisplayFolders displayFolders = biFactory.createTDisplayFolders();

            TDisplayFolder folder = biFactory.createTDisplayFolder();
            folder.setName("Time Dimensions");
            folder.setCaption("Time Dimensions");

            TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName("DateColumn");
            folder.getPropertyRef().add(propRef);

            THierarchyRef hierRef = biFactory.createTHierarchyRef();
            hierRef.setName("DateHierarchy");
            folder.getHierarchyRef().add(hierRef);

            displayFolders.getDisplayFolder().add(folder);
            entityType.setDisplayFolders(displayFolders);

            String xml = serializeToXml(entityType);
            LOGGER.debug("=== TEntityType DisplayFolder with HierarchyRef (Schema 1.1) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("CSDLBI Schema 1.1 - TMeasure with KPI")
    class TMeasureWithKpiTests {

        @Test
        void testMeasureWithKpi() throws IOException {
            TMeasure biMeasure = biFactory.createTMeasure();
            biMeasure.setCaption("Sales Revenue");
            biMeasure.setIsSimpleMeasure(false);
            biMeasure.setContents("SumOfRevenue");

            TKpi kpi = biFactory.createTKpi();
            kpi.setStatusGraphic("ThreeTrafficLights1");

            TDocumentation kpiDoc = biFactory.createTDocumentation();
            kpiDoc.setSummary("Sales KPI for tracking revenue targets");
            kpi.setDocumentation(kpiDoc);

            KpiGoalType kpiGoal = biFactory.createKpiGoalType();
            TPropertyRef goalRef = biFactory.createTPropertyRef();
            goalRef.setName("SalesTarget");
            kpiGoal.setPropertyRef(goalRef);
            kpi.setKpiGoal(kpiGoal);

            KpiStatusType kpiStatus = biFactory.createKpiStatusType();
            TPropertyRef statusRef = biFactory.createTPropertyRef();
            statusRef.setName("SalesStatus");
            kpiStatus.setPropertyRef(statusRef);
            kpi.setKpiStatus(kpiStatus);

            biMeasure.setKpi(kpi);

            String xml = serializeToXml(biMeasure);
            LOGGER.debug("=== TMeasure with KPI (Schema 1.1) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testMeasureWithKpiAndDistributiveBy() throws IOException {
            TMeasure biMeasure = biFactory.createTMeasure();
            biMeasure.setCaption("Profit Margin");
            biMeasure.setIsSimpleMeasure(false);
            biMeasure.setLineageTag("profit-measure-001");

            TKpi kpi = biFactory.createTKpi();
            kpi.setStatusGraphic("FiveArrowsColored");

            KpiGoalType kpiGoal = biFactory.createKpiGoalType();
            TPropertyRef goalRef = biFactory.createTPropertyRef();
            goalRef.setName("ProfitTarget");
            kpiGoal.setPropertyRef(goalRef);
            kpi.setKpiGoal(kpiGoal);

            biMeasure.setKpi(kpi);

            TDistributiveBy distributiveBy = biFactory.createTDistributiveBy();
            distributiveBy.setAggregationKind(TAggregationKind.SUM);
            TEntityRef entityRef = biFactory.createTEntityRef();
            entityRef.setName("FinanceTable");
            distributiveBy.setEntityRef(entityRef);
            biMeasure.setDistributiveBy(distributiveBy);

            String xml = serializeToXml(biMeasure);
            LOGGER.debug("=== TMeasure with KPI and DistributiveBy (Schema 1.1) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("Integration Tests - Complete CSDL with BI Annotations")
    class IntegrationTests {

        @Test
        void testEdmEntityContainerSerialization() throws IOException {
            EntityContainerType container = edmFactory.createEntityContainerType();
            container.setName("SalesModel");

            EntitySetType entitySet = edmFactory.createEntitySetType();
            entitySet.setName("Products");
            entitySet.setEntityType("Model.Product");
            container.getEntitySet().add(entitySet);

            String xml = serializeToXml(container);
            LOGGER.debug("=== EDM EntityContainer (without BI) ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEdmWithBiEntityContainer() throws IOException {
            EntityContainerType container = edmFactory.createEntityContainerType();
            container.setName("SalesModel");

            TEntityContainer biContainer = biFactory.createTEntityContainer();
            biContainer.setCaption("Sales Model");
            biContainer.setCulture("de-DE");
            biContainer.setDirectQueryMode(TDirectQueryMode.IN_MEMORY);

            TCompareOptions compareOptions = biFactory.createTCompareOptions();
            compareOptions.setIgnoreCase(true);
            biContainer.setCompareOptions(compareOptions);

            container.setBiEntityContainer(biContainer);

            EntitySetType entitySet = edmFactory.createEntitySetType();
            entitySet.setName("Products");
            entitySet.setEntityType("Model.Product");

            TEntitySet biEntitySet = biFactory.createTEntitySet();
            biEntitySet.setCaption("Produkte");
            biEntitySet.setHidden(false);
            biEntitySet.setLineageTag("products-lineage");

            entitySet.setBiEntitySet(biEntitySet);
            container.getEntitySet().add(entitySet);

            String xml = serializeToXml(container);
            LOGGER.debug("=== EDM EntityContainer with BI Annotations ===");
            LOGGER.debug(xml);
        }

        @Test
        void testEdmEntityTypeWithBi() throws IOException {
            org.eclipse.daanse.xmla.csdl.model.v2.edm.TEntityType productType = edmFactory.createTEntityType();
            productType.setName("Product");

            TEntityType biEntityType = biFactory.createTEntityType();
            biEntityType.setContents("Regular");

            TMemberRefs displayKey = biFactory.createTMemberRefs();
            TMemberRef keyRef = biFactory.createTMemberRef();
            keyRef.setName("ProductId");
            displayKey.getMemberRef().add(keyRef);
            biEntityType.setDisplayKey(displayKey);

            productType.setBiEntityType(biEntityType);

            TEntityProperty nameProperty = edmFactory.createTEntityProperty();
            nameProperty.setName("Name");
            nameProperty.setType("Edm.String");

            TProperty biProperty = biFactory.createTProperty();
            biProperty.setCaption("Produktname");
            biProperty.setHidden(false);
            biProperty.setDefaultAggregateFunction(TDefaultAggregateFunction.NONE);
            biProperty.setGroupingBehavior(TGroupingBehavior.GROUP_ON_VALUE);
            biProperty.setStability(TStability.STABLE);

            nameProperty.setBiProperty(biProperty);
            productType.getProperty().add(nameProperty);

            String xml = serializeToXml(productType);
            LOGGER.debug("=== EDM EntityType with BI Annotations ===");
            LOGGER.debug(xml);
        }

        @Test
        void testCsdlSchemaWithBiAnnotations() throws IOException {
            TSchema schema = edmFactory.createTSchema();
            schema.setNamespace("Model");
            schema.setAlias("Self");

            EntityContainerType container = edmFactory.createEntityContainerType();
            container.setName("SalesModel");

            TEntityContainer biContainer = biFactory.createTEntityContainer();
            biContainer.setCaption("Verkaufsmodell");
            biContainer.setCulture("de-DE");
            biContainer.setDirectQueryMode(TDirectQueryMode.IN_MEMORY_WITH_DIRECT_QUERY);

            TCompareOptions compareOptions = biFactory.createTCompareOptions();
            compareOptions.setIgnoreCase(true);
            compareOptions.setIgnoreWidth(false);
            biContainer.setCompareOptions(compareOptions);

            container.setBiEntityContainer(biContainer);

            EntitySetType productSet = edmFactory.createEntitySetType();
            productSet.setName("Products");
            productSet.setEntityType("Model.Product");

            TEntitySet biProductSet = biFactory.createTEntitySet();
            biProductSet.setCaption("Produkte");
            biProductSet.setCollectionCaption("Produktsammlung");
            biProductSet.setHidden(false);
            productSet.setBiEntitySet(biProductSet);

            container.getEntitySet().add(productSet);

            EntitySetType salesSet = edmFactory.createEntitySetType();
            salesSet.setName("Sales");
            salesSet.setEntityType("Model.Sales");

            TEntitySet biSalesSet = biFactory.createTEntitySet();
            biSalesSet.setCaption("Verkäufe");
            biSalesSet.setLineageTag("sales-lineage-001");
            salesSet.setBiEntitySet(biSalesSet);

            container.getEntitySet().add(salesSet);
            schema.getEntityContainer().add(container);

            org.eclipse.daanse.xmla.csdl.model.v2.edm.TEntityType productType = edmFactory.createTEntityType();
            productType.setName("Product");

            TEntityType biProductType = biFactory.createTEntityType();
            biProductType.setContents("Regular");

            TMemberRefs displayKey = biFactory.createTMemberRefs();
            TMemberRef keyRef = biFactory.createTMemberRef();
            keyRef.setName("ProductId");
            displayKey.getMemberRef().add(keyRef);
            biProductType.setDisplayKey(displayKey);

            DefaultMeasureType defaultMeasure = biFactory.createDefaultMeasureType();
            TMemberRef measureRef = biFactory.createTMemberRef();
            measureRef.setName("UnitPrice");
            defaultMeasure.setMemberRef(measureRef);
            biProductType.setDefaultMeasure(defaultMeasure);

            TDisplayFolders displayFolders = biFactory.createTDisplayFolders();
            TDisplayFolder productInfoFolder = biFactory.createTDisplayFolder();
            productInfoFolder.setName("Product Info");
            productInfoFolder.setCaption("Product Information");

            TPropertyRef nameRef = biFactory.createTPropertyRef();
            nameRef.setName("Name");
            productInfoFolder.getPropertyRef().add(nameRef);

            TPropertyRef descRef = biFactory.createTPropertyRef();
            descRef.setName("Description");
            productInfoFolder.getPropertyRef().add(descRef);

            displayFolders.getDisplayFolder().add(productInfoFolder);
            biProductType.setDisplayFolders(displayFolders);

            productType.setBiEntityType(biProductType);

            TEntityProperty idProperty = edmFactory.createTEntityProperty();
            idProperty.setName("ProductId");
            idProperty.setType("Edm.Int32");
            idProperty.setNullable(false);

            TProperty biIdProperty = biFactory.createTProperty();
            biIdProperty.setCaption("Produkt-ID");
            biIdProperty.setHidden(true);
            idProperty.setBiProperty(biIdProperty);

            productType.getProperty().add(idProperty);

            TEntityProperty nameProperty = edmFactory.createTEntityProperty();
            nameProperty.setName("Name");
            nameProperty.setType("Edm.String");

            TProperty biNameProperty = biFactory.createTProperty();
            biNameProperty.setCaption("Produktname");
            biNameProperty.setHidden(false);
            biNameProperty.setStability(TStability.STABLE);
            nameProperty.setBiProperty(biNameProperty);

            productType.getProperty().add(nameProperty);

            TEntityProperty priceProperty = edmFactory.createTEntityProperty();
            priceProperty.setName("UnitPrice");
            priceProperty.setType("Edm.Decimal");

            TProperty biPriceProperty = biFactory.createTProperty();
            biPriceProperty.setCaption("Einzelpreis");
            biPriceProperty.setDefaultAggregateFunction(TDefaultAggregateFunction.AVERAGE);
            biPriceProperty.setLineageTag("price-lineage-001");
            priceProperty.setBiProperty(biPriceProperty);

            productType.getProperty().add(priceProperty);

            schema.getEntityType().add(productType);

            String xml = serializeToXml(schema);
            LOGGER.debug("=== Complete CSDL Schema with BI Annotations ===");
            LOGGER.debug(xml);
        }

        @Test
        void testCsdlSchemaWithHierarchiesAndKpi() throws IOException {
            TSchema schema = edmFactory.createTSchema();
            schema.setNamespace("SalesBI");
            schema.setAlias("Self");

            EntityContainerType container = edmFactory.createEntityContainerType();
            container.setName("SalesAnalytics");

            TEntityContainer biContainer = biFactory.createTEntityContainer();
            biContainer.setCaption("Sales Analytics");
            biContainer.setCulture("en-US");
            biContainer.setDirectQueryMode(TDirectQueryMode.DIRECT_QUERY_WITH_IN_MEMORY);
            container.setBiEntityContainer(biContainer);

            schema.getEntityContainer().add(container);

            org.eclipse.daanse.xmla.csdl.model.v2.edm.TEntityType dateType = edmFactory.createTEntityType();
            dateType.setName("Date");

            TEntityType biDateType = biFactory.createTEntityType();
            biDateType.setContents("Time");

            THierarchy dateHierarchy = biFactory.createTHierarchy();
            dateHierarchy.setName("CalendarHierarchy");
            dateHierarchy.setCaption("Calendar");
            dateHierarchy.setLineageTag("calendar-hier-001");

            TDocumentation hierDoc = biFactory.createTDocumentation();
            hierDoc.setSummary("Standard calendar hierarchy");
            dateHierarchy.setDocumentation(hierDoc);

            TLevel yearLevel = biFactory.createTLevel();
            yearLevel.setName("Year");
            yearLevel.setCaption("Year");
            SourceType yearSource = biFactory.createSourceType();
            TPropertyRef yearPropRef = biFactory.createTPropertyRef();
            yearPropRef.setName("CalendarYear");
            yearSource.setPropertyRef(yearPropRef);
            yearLevel.setSource(yearSource);

            TLevel quarterLevel = biFactory.createTLevel();
            quarterLevel.setName("Quarter");
            quarterLevel.setCaption("Quarter");

            TLevel monthLevel = biFactory.createTLevel();
            monthLevel.setName("Month");
            monthLevel.setCaption("Month");

            dateHierarchy.getLevel().add(yearLevel);
            dateHierarchy.getLevel().add(quarterLevel);
            dateHierarchy.getLevel().add(monthLevel);

            biDateType.getHierarchy().add(dateHierarchy);
            dateType.setBiEntityType(biDateType);

            schema.getEntityType().add(dateType);

            org.eclipse.daanse.xmla.csdl.model.v2.edm.TEntityType salesType = edmFactory.createTEntityType();
            salesType.setName("Sales");

            TEntityType biSalesType = biFactory.createTEntityType();
            biSalesType.setContents("Data");

            TDisplayFolders salesFolders = biFactory.createTDisplayFolders();
            TDisplayFolder kpiFolder = biFactory.createTDisplayFolder();
            kpiFolder.setName("KPI Measures");
            kpiFolder.setCaption("KPI Measures");
            salesFolders.getDisplayFolder().add(kpiFolder);
            biSalesType.setDisplayFolders(salesFolders);

            salesType.setBiEntityType(biSalesType);

            TEntityProperty revenueProperty = edmFactory.createTEntityProperty();
            revenueProperty.setName("Revenue");
            revenueProperty.setType("Edm.Decimal");

            TMeasure biRevenueMeasure = biFactory.createTMeasure();
            biRevenueMeasure.setCaption("Total Revenue");
            biRevenueMeasure.setIsSimpleMeasure(false);
            biRevenueMeasure.setLineageTag("revenue-kpi-001");

            TKpi revenueKpi = biFactory.createTKpi();
            revenueKpi.setStatusGraphic("ThreeTrafficLights1");

            TDocumentation kpiDoc = biFactory.createTDocumentation();
            kpiDoc.setSummary("Revenue KPI - tracks actual vs target");
            revenueKpi.setDocumentation(kpiDoc);

            KpiGoalType goal = biFactory.createKpiGoalType();
            TPropertyRef goalRef = biFactory.createTPropertyRef();
            goalRef.setName("RevenueTarget");
            goal.setPropertyRef(goalRef);
            revenueKpi.setKpiGoal(goal);

            KpiStatusType status = biFactory.createKpiStatusType();
            TPropertyRef statusRef = biFactory.createTPropertyRef();
            statusRef.setName("RevenueStatus");
            status.setPropertyRef(statusRef);
            revenueKpi.setKpiStatus(status);

            biRevenueMeasure.setKpi(revenueKpi);

            TDistributiveBy distBy = biFactory.createTDistributiveBy();
            distBy.setAggregationKind(TAggregationKind.SUM);
            TEntityRef entityRef = biFactory.createTEntityRef();
            entityRef.setName("SalesTable");
            distBy.setEntityRef(entityRef);
            biRevenueMeasure.setDistributiveBy(distBy);

            revenueProperty.setBiMeasure(biRevenueMeasure);
            salesType.getProperty().add(revenueProperty);

            schema.getEntityType().add(salesType);

            String xml = serializeToXml(schema);
            LOGGER.debug("=== CSDL Schema with Hierarchies and KPI (Schema 1.1) ===");
            LOGGER.debug(xml);
        }
    }

    @Nested
    @DisplayName("Schema 1.0 Validation (Section 5.1) - Verify serialization matches XSD")
    class Schema10ValidationTests {

        @Test
        void validateTEntityContainerSchema() throws IOException {
            TEntityContainer biContainer = biFactory.createTEntityContainer();
            biContainer.setCaption("Sales Model");
            biContainer.setCulture("en-US");
            biContainer.setDirectQueryMode(TDirectQueryMode.IN_MEMORY);

            TCompareOptions compareOptions = biFactory.createTCompareOptions();
            compareOptions.setIgnoreCase(true);
            compareOptions.setIgnoreNonSpace(false);
            compareOptions.setIgnoreKanaType(true);
            compareOptions.setIgnoreWidth(false);
            biContainer.setCompareOptions(compareOptions);

            String xml = serializeToXml(biContainer);

            assertThat(xml).as("Should use bi: namespace prefix").contains("bi:TEntityContainer");
            assertThat(xml).as("Should have correct BI namespace")
                    .contains("xmlns:bi=\"http://schemas.microsoft.com/sqlbi/2010/10/edm/extensions\"");
            assertThat(xml).as("Should have Caption attribute").contains("Caption=\"Sales Model\"");
            assertThat(xml).as("Should have Culture attribute").contains("Culture=\"en-US\"");
            assertThat(xml).as("Should have DirectQueryMode attribute").contains("DirectQueryMode=\"InMemory\"");
            assertThat(xml).as("Should contain CompareOptions element").contains("<bi:CompareOptions");
            assertThat(xml).as("CompareOptions should have IgnoreCase").contains("IgnoreCase=\"true\"");
            assertThat(xml).as("CompareOptions should have IgnoreKanaType").contains("IgnoreKanaType=\"true\"");
        }

        @Test
        void validateTDirectQueryModeEnumValues() throws IOException {
            String[] expectedValues = { "InMemory", "InMemoryWithDirectQuery", "DirectQueryWithInMemory",
                    "DirectQuery" };

            for (int i = 0; i < TDirectQueryMode.VALUES.size(); i++) {
                TDirectQueryMode mode = TDirectQueryMode.VALUES.get(i);
                TEntityContainer biContainer = biFactory.createTEntityContainer();
                biContainer.setDirectQueryMode(mode);

                String xml = serializeToXml(biContainer);
                assertThat(xml).as("DirectQueryMode should be " + expectedValues[i])
                        .contains("DirectQueryMode=\"" + expectedValues[i] + "\"");
            }
        }

        @Test
        void validateTEntitySetSchema() throws IOException {
            TEntitySet biEntitySet = biFactory.createTEntitySet();
            biEntitySet.setCaption("Products");
            biEntitySet.setCollectionCaption("Product Collection");
            biEntitySet.setReferenceName("ProductRef");
            biEntitySet.setHidden(false);
            biEntitySet.setLineageTag("product-lineage-123");
            biEntitySet.setPrivate(false);
            biEntitySet.setShowAsVariationsOnly(true);

            String xml = serializeToXml(biEntitySet);

            assertThat(xml).as("Should use TEntitySet type").contains("bi:TEntitySet");
            assertThat(xml).as("Should have Caption attribute").contains("Caption=\"Products\"");
            assertThat(xml).as("Should have CollectionCaption attribute")
                    .contains("CollectionCaption=\"Product Collection\"");
            assertThat(xml).as("Should have ReferenceName attribute").contains("ReferenceName=\"ProductRef\"");
            assertThat(xml).as("Should have Hidden attribute").contains("Hidden=\"false\"");
            assertThat(xml).as("Should have LineageTag attribute").contains("LineageTag=\"product-lineage-123\"");
            assertThat(xml).as("Should have Private attribute").contains("Private=\"false\"");
            assertThat(xml).as("Should have ShowAsVariationsOnly attribute").contains("ShowAsVariationsOnly=\"true\"");
        }

        @Test
        void validateTAssociationSetAndTState() throws IOException {
            TAssociationSet activeSet = biFactory.createTAssociationSet();
            activeSet.setState(TState.ACTIVE);
            activeSet.setHidden(false);

            String xmlActive = serializeToXml(activeSet);
            assertThat(xmlActive).as("Should have State=Active").contains("State=\"Active\"");
            assertThat(xmlActive).as("Should have Hidden attribute").contains("Hidden=\"false\"");

            TAssociationSet inactiveSet = biFactory.createTAssociationSet();
            inactiveSet.setState(TState.INACTIVE);
            inactiveSet.setHidden(true);

            String xmlInactive = serializeToXml(inactiveSet);
            assertThat(xmlInactive).as("Should have State=Inactive").contains("State=\"Inactive\"");
            assertThat(xmlInactive).as("Should have Hidden=true").contains("Hidden=\"true\"");
        }

        @Test
        void validateTEntityTypeSchema() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");
            entityType.setIsCalculationGroup(false);

            TMemberRefs displayKey = biFactory.createTMemberRefs();
            TMemberRef keyRef = biFactory.createTMemberRef();
            keyRef.setName("ProductId");
            displayKey.getMemberRef().add(keyRef);
            entityType.setDisplayKey(displayKey);

            DefaultMeasureType defaultMeasure = biFactory.createDefaultMeasureType();
            TMemberRef measureRef = biFactory.createTMemberRef();
            measureRef.setName("TotalSales");
            defaultMeasure.setMemberRef(measureRef);
            entityType.setDefaultMeasure(defaultMeasure);

            String xml = serializeToXml(entityType);

            assertThat(xml).as("Should use TEntityType").contains("bi:TEntityType");
            assertThat(xml).as("Should have Contents attribute").contains("Contents=\"Regular\"");
            assertThat(xml).as("Should have DisplayKey element").contains("<bi:DisplayKey>");
            assertThat(xml).as("Should have MemberRef with Name attribute")
                    .contains("<bi:MemberRef Name=\"ProductId\"");
            assertThat(xml).as("Should have DefaultMeasure element").contains("<bi:DefaultMeasure>");
        }

        @Test
        void validateTDisplayFoldersSchema() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            TDisplayFolders displayFolders = biFactory.createTDisplayFolders();

            TDisplayFolder parentFolder = biFactory.createTDisplayFolder();
            parentFolder.setName("Sales Metrics");
            parentFolder.setCaption("Sales Metrics Caption");

            TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName("TotalRevenue");
            parentFolder.getPropertyRef().add(propRef);

            TDisplayFolder nestedFolder = biFactory.createTDisplayFolder();
            nestedFolder.setName("Regional Sales");
            nestedFolder.setCaption("Regional Sales Caption");
            parentFolder.getDisplayFolder().add(nestedFolder);

            displayFolders.getDisplayFolder().add(parentFolder);
            entityType.setDisplayFolders(displayFolders);

            String xml = serializeToXml(entityType);

            assertThat(xml).as("Should have DisplayFolders element").contains("<bi:DisplayFolders>");
            assertThat(xml).as("Should have DisplayFolder element").contains("<bi:DisplayFolder");
            assertThat(xml).as("Parent folder should have Name attribute").contains("Name=\"Sales Metrics\"");
            assertThat(xml).as("Should have Caption attribute").contains("Caption=\"Sales Metrics Caption\"");
            assertThat(xml).as("Should have PropertyRef element").contains("<bi:PropertyRef Name=\"TotalRevenue\"");
            assertThat(xml).as("Nested folder should have Name attribute").contains("Name=\"Regional Sales\"");
        }

        @Test
        void validateTPropertySchema() throws IOException {
            TProperty biProperty = biFactory.createTProperty();
            biProperty.setCaption("Full Property");
            biProperty.setHidden(false);
            biProperty.setPrivate(false);
            biProperty.setContents("Regular");
            biProperty.setDefaultAggregateFunction(TDefaultAggregateFunction.SUM);
            biProperty.setGroupingBehavior(TGroupingBehavior.GROUP_ON_VALUE);
            biProperty.setStability(TStability.STABLE);
            biProperty.setAggregateBehavior(true);
            biProperty.setLocaleImpact(false);
            biProperty.setLineageTag("property-lineage-456");
            biProperty.setIsError(false);

            TPropertyRefs orderBy = biFactory.createTPropertyRefs();
            TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName("SortOrder");
            orderBy.getPropertyRef().add(propRef);
            biProperty.setOrderBy(orderBy);

            String xml = serializeToXml(biProperty);

            assertThat(xml).as("Should use TProperty type").contains("bi:TProperty");
            assertThat(xml).as("Should have Caption").contains("Caption=\"Full Property\"");
            assertThat(xml).as("Should have Hidden").contains("Hidden=\"false\"");
            assertThat(xml).as("Should have Private").contains("Private=\"false\"");
            assertThat(xml).as("Should have Contents").contains("Contents=\"Regular\"");
            assertThat(xml).as("Should have DefaultAggregateFunction=Sum").contains("DefaultAggregateFunction=\"Sum\"");
            assertThat(xml).as("Should have GroupingBehavior").contains("GroupingBehavior=\"GroupOnValue\"");
            assertThat(xml).as("Should have Stability").contains("Stability=\"Stable\"");
            assertThat(xml).as("Should have LineageTag").contains("LineageTag=\"property-lineage-456\"");
            assertThat(xml).as("Should have OrderBy element").contains("<bi:OrderBy>");
            assertThat(xml).as("Should have PropertyRef in OrderBy").contains("<bi:PropertyRef Name=\"SortOrder\"");
        }

        @Test
        void validateTMeasureAndDistributiveBySchema() throws IOException {
            TMeasure biMeasure = biFactory.createTMeasure();
            biMeasure.setCaption("Total Sales");
            biMeasure.setIsSimpleMeasure(true);
            biMeasure.setContents("Regular");
            biMeasure.setPrivate(false);
            biMeasure.setLineageTag("measure-001");

            TDistributiveBy distributiveBy = biFactory.createTDistributiveBy();
            distributiveBy.setAggregationKind(TAggregationKind.SUM);

            TEntityRef entityRef = biFactory.createTEntityRef();
            entityRef.setName("SalesTable");
            distributiveBy.setEntityRef(entityRef);

            biMeasure.setDistributiveBy(distributiveBy);

            String xml = serializeToXml(biMeasure);

            assertThat(xml).as("Should use TMeasure type").contains("bi:TMeasure");
            assertThat(xml).as("Should have Caption").contains("Caption=\"Total Sales\"");
            assertThat(xml).as("Should have IsSimpleMeasure").contains("IsSimpleMeasure=\"true\"");
            assertThat(xml).as("Should have Contents").contains("Contents=\"Regular\"");
            assertThat(xml).as("Should have LineageTag").contains("LineageTag=\"measure-001\"");
            assertThat(xml).as("Should have DistributiveBy element").contains("<bi:DistributiveBy");
            assertThat(xml).as("Should have AggregationKind=Sum").contains("AggregationKind=\"Sum\"");
            assertThat(xml).as("Should have EntityRef element").contains("<bi:EntityRef Name=\"SalesTable\"");
        }

        @Test
        void validateTAggregationKindEnumValues() throws IOException {
            String[] expectedValues = { "Sum", "Min", "Max", "Count" };

            for (int i = 0; i < TAggregationKind.VALUES.size(); i++) {
                TAggregationKind kind = TAggregationKind.VALUES.get(i);
                TMeasure biMeasure = biFactory.createTMeasure();

                TDistributiveBy distBy = biFactory.createTDistributiveBy();
                distBy.setAggregationKind(kind);
                TEntityRef entityRef = biFactory.createTEntityRef();
                entityRef.setName("Table");
                distBy.setEntityRef(entityRef);
                biMeasure.setDistributiveBy(distBy);

                String xml = serializeToXml(biMeasure);
                assertThat(xml).as("AggregationKind should be " + expectedValues[i])
                        .contains("AggregationKind=\"" + expectedValues[i] + "\"");
            }
        }

        @Test
        void validateTNavigationPropertySchema() throws IOException {
            TNavigationProperty navProp = biFactory.createTNavigationProperty();
            navProp.setCaption("Related Products");
            navProp.setCollectionCaption("Product Collection");
            navProp.setHidden(false);
            navProp.setReferenceName("ProductNavRef");

            String xml = serializeToXml(navProp);

            assertThat(xml).as("Should use TNavigationProperty type").contains("bi:TNavigationProperty");
            assertThat(xml).as("Should have Caption from TMember").contains("Caption=\"Related Products\"");
            assertThat(xml).as("Should have CollectionCaption").contains("CollectionCaption=\"Product Collection\"");
            assertThat(xml).as("Should have Hidden from TMember").contains("Hidden=\"false\"");
            assertThat(xml).as("Should have ReferenceName from TMember").contains("ReferenceName=\"ProductNavRef\"");
        }
    }

    @Nested
    @DisplayName("Schema 1.1 Validation (Section 5.2) - Verify serialization matches XSD")
    class Schema11ValidationTests {

        @Test
        void validateTHierarchySchema() throws IOException {
            THierarchy hierarchy = biFactory.createTHierarchy();
            hierarchy.setName("DateHierarchy");
            hierarchy.setCaption("Date Hierarchy");
            hierarchy.setHidden(false);
            hierarchy.setLineageTag("hierarchy-lineage-001");

            TDocumentation doc = biFactory.createTDocumentation();
            doc.setSummary("Hierarchy for date dimensions");
            hierarchy.setDocumentation(doc);

            TLevel yearLevel = biFactory.createTLevel();
            yearLevel.setName("Year");
            yearLevel.setCaption("Calendar Year");

            TLevel monthLevel = biFactory.createTLevel();
            monthLevel.setName("Month");
            monthLevel.setCaption("Calendar Month");

            hierarchy.getLevel().add(yearLevel);
            hierarchy.getLevel().add(monthLevel);

            String xml = serializeToXml(hierarchy);

            assertThat(xml).as("Should use THierarchy type").contains("bi:THierarchy");
            assertThat(xml).as("Should have required Name attribute").contains("Name=\"DateHierarchy\"");
            assertThat(xml).as("Should have Caption from TMember").contains("Caption=\"Date Hierarchy\"");
            assertThat(xml).as("Should have LineageTag").contains("LineageTag=\"hierarchy-lineage-001\"");
            assertThat(xml).as("Should have Documentation element").contains("<bi:Documentation>");
            assertThat(xml).as("Documentation should have Summary element").contains("<bi:Summary>");
            assertThat(xml).as("Should have Level elements").contains("<bi:Level");
            assertThat(xml).as("First level should have Name=Year").contains("Name=\"Year\"");
            assertThat(xml).as("Second level should have Name=Month").contains("Name=\"Month\"");
        }

        @Test
        void validateTLevelWithSourceSchema() throws IOException {
            THierarchy hierarchy = biFactory.createTHierarchy();
            hierarchy.setName("TestHierarchy");

            TLevel level = biFactory.createTLevel();
            level.setName("Category");
            level.setCaption("Product Category");
            level.setLineageTag("level-001");

            SourceType source = biFactory.createSourceType();
            TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName("CategoryColumn");
            source.setPropertyRef(propRef);
            level.setSource(source);

            TDocumentation levelDoc = biFactory.createTDocumentation();
            levelDoc.setSummary("Product category level");
            level.setDocumentation(levelDoc);

            hierarchy.getLevel().add(level);

            String xml = serializeToXml(hierarchy);

            assertThat(xml).as("Should have Level element").contains("<bi:Level");
            assertThat(xml).as("Level should have Name attribute").contains("Name=\"Category\"");
            assertThat(xml).as("Level should have Caption").contains("Caption=\"Product Category\"");
            assertThat(xml).as("Level should have LineageTag").contains("LineageTag=\"level-001\"");
            assertThat(xml).as("Level should have Source element").contains("<bi:Source>");
            assertThat(xml).as("Source should have PropertyRef with Name")
                    .contains("<bi:PropertyRef Name=\"CategoryColumn\"");
            assertThat(xml).as("Level should have Documentation").contains("<bi:Documentation>");
        }

        @Test
        void validateTHierarchyRefInDisplayFolderSchema() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Regular");

            TDisplayFolders displayFolders = biFactory.createTDisplayFolders();

            TDisplayFolder folder = biFactory.createTDisplayFolder();
            folder.setName("Time Dimensions");
            folder.setCaption("Time Dimensions");

            TPropertyRef propRef = biFactory.createTPropertyRef();
            propRef.setName("DateColumn");
            folder.getPropertyRef().add(propRef);

            THierarchyRef hierRef = biFactory.createTHierarchyRef();
            hierRef.setName("DateHierarchy");
            folder.getHierarchyRef().add(hierRef);

            displayFolders.getDisplayFolder().add(folder);
            entityType.setDisplayFolders(displayFolders);

            String xml = serializeToXml(entityType);

            assertThat(xml).as("Should have DisplayFolder element").contains("<bi:DisplayFolder");
            assertThat(xml).as("Should have PropertyRef").contains("<bi:PropertyRef Name=\"DateColumn\"");
            assertThat(xml).as("Should have HierarchyRef with Name attribute")
                    .contains("<bi:HierarchyRef Name=\"DateHierarchy\"");
        }

        @Test
        void validateTKpiSchema() throws IOException {
            TMeasure biMeasure = biFactory.createTMeasure();
            biMeasure.setCaption("Sales Revenue");
            biMeasure.setIsSimpleMeasure(false);
            biMeasure.setContents("SumOfRevenue");

            TKpi kpi = biFactory.createTKpi();
            kpi.setStatusGraphic("ThreeTrafficLights1");

            TDocumentation kpiDoc = biFactory.createTDocumentation();
            kpiDoc.setSummary("Sales KPI for tracking revenue targets");
            kpi.setDocumentation(kpiDoc);

            KpiGoalType kpiGoal = biFactory.createKpiGoalType();
            TPropertyRef goalRef = biFactory.createTPropertyRef();
            goalRef.setName("SalesTarget");
            kpiGoal.setPropertyRef(goalRef);
            kpi.setKpiGoal(kpiGoal);

            KpiStatusType kpiStatus = biFactory.createKpiStatusType();
            TPropertyRef statusRef = biFactory.createTPropertyRef();
            statusRef.setName("SalesStatus");
            kpiStatus.setPropertyRef(statusRef);
            kpi.setKpiStatus(kpiStatus);

            biMeasure.setKpi(kpi);

            String xml = serializeToXml(biMeasure);

            assertThat(xml).as("Should use TMeasure type").contains("bi:TMeasure");
            assertThat(xml).as("Should have Kpi element").contains("<bi:Kpi");
            assertThat(xml).as("Kpi should have StatusGraphic attribute")
                    .contains("StatusGraphic=\"ThreeTrafficLights1\"");
            assertThat(xml).as("Kpi should have Documentation").contains("<bi:Documentation>");
            assertThat(xml).as("Should have KpiGoal element").contains("<bi:KpiGoal>");
            assertThat(xml).as("Should have KpiStatus element").contains("<bi:KpiStatus>");

            // Verify PropertyRef inside KpiGoal and KpiStatus
            int kpiGoalPos = xml.indexOf("<bi:KpiGoal>");
            int kpiStatusPos = xml.indexOf("<bi:KpiStatus>");
            int goalRefPos = xml.indexOf("Name=\"SalesTarget\"");
            int statusRefPos = xml.indexOf("Name=\"SalesStatus\"");

            assertThat(goalRefPos > kpiGoalPos && goalRefPos < kpiStatusPos)
                    .as("SalesTarget PropertyRef should be inside KpiGoal").isTrue();
            assertThat(statusRefPos > kpiStatusPos).as("SalesStatus PropertyRef should be inside KpiStatus").isTrue();
        }

        @Test
        void validateTMeasureWithKpiAndDistributiveBySchema() throws IOException {
            TMeasure biMeasure = biFactory.createTMeasure();
            biMeasure.setCaption("Profit Margin");
            biMeasure.setIsSimpleMeasure(false);
            biMeasure.setLineageTag("profit-measure-001");

            TKpi kpi = biFactory.createTKpi();
            kpi.setStatusGraphic("FiveArrowsColored");

            KpiGoalType kpiGoal = biFactory.createKpiGoalType();
            TPropertyRef goalRef = biFactory.createTPropertyRef();
            goalRef.setName("ProfitTarget");
            kpiGoal.setPropertyRef(goalRef);
            kpi.setKpiGoal(kpiGoal);

            biMeasure.setKpi(kpi);

            TDistributiveBy distributiveBy = biFactory.createTDistributiveBy();
            distributiveBy.setAggregationKind(TAggregationKind.SUM);
            TEntityRef entityRef = biFactory.createTEntityRef();
            entityRef.setName("FinanceTable");
            distributiveBy.setEntityRef(entityRef);
            biMeasure.setDistributiveBy(distributiveBy);

            String xml = serializeToXml(biMeasure);

            assertThat(xml).as("Should have Caption").contains("Caption=\"Profit Margin\"");
            assertThat(xml).as("Should have LineageTag").contains("LineageTag=\"profit-measure-001\"");
            assertThat(xml).as("Should have Kpi element").contains("<bi:Kpi");
            assertThat(xml).as("Kpi should have StatusGraphic").contains("StatusGraphic=\"FiveArrowsColored\"");
            assertThat(xml).as("Should have DistributiveBy element").contains("<bi:DistributiveBy");
            assertThat(xml).as("DistributiveBy should have AggregationKind").contains("AggregationKind=\"Sum\"");
            assertThat(xml).as("Should have EntityRef").contains("<bi:EntityRef Name=\"FinanceTable\"");

            // Verify element order: Kpi should come before DistributiveBy per XSD sequence
            int kpiPos = xml.indexOf("<bi:Kpi");
            int distByPos = xml.indexOf("<bi:DistributiveBy");
            assertThat(kpiPos < distByPos).as("Kpi element should appear before DistributiveBy as per XSD sequence")
                    .isTrue();
        }

        @Test
        void validateTEntityTypeWithHierarchySchema() throws IOException {
            TEntityType entityType = biFactory.createTEntityType();
            entityType.setContents("Time");

            THierarchy dateHierarchy = biFactory.createTHierarchy();
            dateHierarchy.setName("CalendarHierarchy");
            dateHierarchy.setCaption("Calendar");
            dateHierarchy.setLineageTag("cal-hier-001");

            TLevel yearLevel = biFactory.createTLevel();
            yearLevel.setName("Year");
            yearLevel.setCaption("Year");

            TLevel monthLevel = biFactory.createTLevel();
            monthLevel.setName("Month");
            monthLevel.setCaption("Month");

            dateHierarchy.getLevel().add(yearLevel);
            dateHierarchy.getLevel().add(monthLevel);

            entityType.getHierarchy().add(dateHierarchy);

            String xml = serializeToXml(entityType);

            assertThat(xml).as("Should use TEntityType").contains("bi:TEntityType");
            assertThat(xml).as("Should have Contents attribute").contains("Contents=\"Time\"");
            assertThat(xml).as("Should have Hierarchy element").contains("<bi:Hierarchy");
            assertThat(xml).as("Hierarchy should have Name").contains("Name=\"CalendarHierarchy\"");
            assertThat(xml).as("Hierarchy should have Caption").contains("Caption=\"Calendar\"");
            assertThat(xml).as("Hierarchy should have LineageTag").contains("LineageTag=\"cal-hier-001\"");
            assertThat(xml).as("Hierarchy should have Level elements").contains("<bi:Level");
        }

        @Test
        void validateBiNamespace() throws IOException {
            TEntityContainer biContainer = biFactory.createTEntityContainer();
            biContainer.setCaption("Test");

            String xml = serializeToXml(biContainer);

            assertThat(xml).as("Should have correct BI namespace URI as per XSD targetNamespace")
                    .contains("xmlns:bi=\"http://schemas.microsoft.com/sqlbi/2010/10/edm/extensions\"");
            assertThat(xml).as("Should not use EDM namespace for BI elements")
                    .doesNotContain("xmlns:bi=\"http://schemas.microsoft.com/ado/2008/09/edm\"");
        }
    }

    private String serializeToXml(EObject eObject) throws IOException {
        Resource resource = resourceSet.createResource(URI.createURI("temp.xml"));
        resource.getContents().add(eObject);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Map<String, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_FORMATTED, Boolean.TRUE);
        options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);

        resource.save(baos, options);

        resource.getContents().clear();
        resourceSet.getResources().remove(resource);

        return baos.toString("UTF-8");
    }
}
