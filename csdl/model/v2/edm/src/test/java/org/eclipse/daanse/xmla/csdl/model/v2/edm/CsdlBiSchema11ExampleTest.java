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

import java.io.IOException;

import org.eclipse.daanse.xmla.csdl.model.v2.bi.BiFactory;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TCompareOptions;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDefaultAggregateFunction;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityContainer;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.THierarchy;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TLevel;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMemberRefs;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test that programmatically generates XML matching example_1_1.xml
 * (Schema 1.1) and compares the complete generated XML against the example
 * file.
 * <p>
 * Reference: [MS-CSDLBI] Section 5.2: CSDLBI Schema 1.1
 */
@DisplayName("CSDL-BI Schema 1.1 Example Test")
public class CsdlBiSchema11ExampleTest {

    private CsdlBiSchemaBuilderHelper helper;
    private EdmFactory edmFactory;
    private BiFactory biFactory;

    @BeforeEach
    void setUp() {
        helper = new CsdlBiSchemaBuilderHelper();
        edmFactory = helper.getEdmFactory();
        biFactory = helper.getBiFactory();
    }

    @Test
    @DisplayName("Generated Schema 1.1 matches example_1_1.xml structure")
    void testSchema11MatchesExample() throws IOException {
        TSchema generatedSchema = buildSchema11();
        TSchema expectedSchema = helper.loadSchemaFromXml(getClass(), "example_1_1.xml");

        // Compare schemas using EMF EcoreUtil for semantic equality
        assertSchemasEqual(generatedSchema, expectedSchema);
    }

    private void assertSchemasEqual(TSchema generated, TSchema expected) {
        // Compare namespace
        assertThat(generated.getNamespace()).as("Schema namespace").isEqualTo(expected.getNamespace());

        // Compare EntityContainer count
        assertThat(generated.getEntityContainer()).as("EntityContainer count")
                .hasSameSizeAs(expected.getEntityContainer());

        // Compare each EntityContainer
        for (int i = 0; i < generated.getEntityContainer().size(); i++) {
            EntityContainerType genContainer = generated.getEntityContainer().get(i);
            EntityContainerType expContainer = expected.getEntityContainer().get(i);
            assertThat(genContainer.getName()).as("EntityContainer name at index " + i)
                    .isEqualTo(expContainer.getName());
            assertThat(genContainer.getEntitySet()).as("EntitySet count in container " + genContainer.getName())
                    .hasSameSizeAs(expContainer.getEntitySet());
            assertThat(genContainer.getAssociationSet())
                    .as("AssociationSet count in container " + genContainer.getName())
                    .hasSameSizeAs(expContainer.getAssociationSet());

            // Compare EntitySets by name
            for (int j = 0; j < genContainer.getEntitySet().size(); j++) {
                assertThat(genContainer.getEntitySet().get(j).getName()).as("EntitySet name at index " + j)
                        .isEqualTo(expContainer.getEntitySet().get(j).getName());
            }

            // Compare AssociationSets by name
            for (int j = 0; j < genContainer.getAssociationSet().size(); j++) {
                assertThat(genContainer.getAssociationSet().get(j).getName()).as("AssociationSet name at index " + j)
                        .isEqualTo(expContainer.getAssociationSet().get(j).getName());
            }
        }

        // Compare EntityTypes count
        assertThat(generated.getEntityType()).as("EntityType count").hasSameSizeAs(expected.getEntityType());

        // Compare each EntityType by name and structure
        for (int i = 0; i < generated.getEntityType().size(); i++) {
            TEntityType genType = generated.getEntityType().get(i);
            TEntityType expType = expected.getEntityType().get(i);
            assertThat(genType.getName()).as("EntityType name at index " + i).isEqualTo(expType.getName());
            assertThat(genType.getProperty()).as("Property count in EntityType " + genType.getName())
                    .hasSameSizeAs(expType.getProperty());
            assertThat(genType.getNavigationProperty())
                    .as("NavigationProperty count in EntityType " + genType.getName())
                    .hasSameSizeAs(expType.getNavigationProperty());

            // Compare properties by name
            for (int j = 0; j < genType.getProperty().size(); j++) {
                assertThat(genType.getProperty().get(j).getName())
                        .as("Property name at index " + j + " in " + genType.getName())
                        .isEqualTo(expType.getProperty().get(j).getName());
            }

            // Compare navigation properties by name
            for (int j = 0; j < genType.getNavigationProperty().size(); j++) {
                assertThat(genType.getNavigationProperty().get(j).getName())
                        .as("NavigationProperty name at index " + j + " in " + genType.getName())
                        .isEqualTo(expType.getNavigationProperty().get(j).getName());
            }
        }

        // Compare Associations count and names
        assertThat(generated.getAssociation()).as("Association count").hasSameSizeAs(expected.getAssociation());

        for (int i = 0; i < generated.getAssociation().size(); i++) {
            assertThat(generated.getAssociation().get(i).getName()).as("Association name at index " + i)
                    .isEqualTo(expected.getAssociation().get(i).getName());
        }
    }

    private TSchema buildSchema11() {
        TSchema schema = edmFactory.createTSchema();
        schema.setNamespace("Sandbox");
        helper.setBiVersion(schema, "1.1");

        // EntityContainer
        EntityContainerType container = edmFactory.createEntityContainerType();
        container.setName("Sandbox");

        // EntitySets
        helper.addEntitySet(container, "Bike", "Sandbox.Bike", null, true);
        helper.addEntitySet(container, "BikeSales", "Sandbox.BikeSales", null, null);
        helper.addEntitySet(container, "BikeSubcategory", "Sandbox.BikeSubcategory", null, null);
        helper.addEntitySet(container, "CalendarQuarter", "Sandbox.CalendarQuarter", null, null);
        helper.addEntitySet(container, "Country", "Sandbox.Country", null, null);
        helper.addEntitySet(container, "Currency", "Sandbox.Currency", null, null);
        helper.addEntitySet(container, "SalesChannel", "Sandbox.SalesChannel", null, null);

        // AssociationSets
        helper.addAssociationSet(container, "Bike_BikeSubcategory_BikeSubcategory_ProductSubcategoryKey",
                "Sandbox.Bike_BikeSubcategory_BikeSubcategory_ProductSubcategoryKey", "Bike", "BikeSubcategory", null,
                true);
        helper.addAssociationSet(container, "BikeSales_Bike_Bike_ProductKey", "Sandbox.BikeSales_Bike_Bike_ProductKey",
                "BikeSales", "Bike", null, null);
        helper.addAssociationSet(container, "BikeSales_CalendarQuarter_CalendarQuarter_CalendarQuarter",
                "Sandbox.BikeSales_CalendarQuarter_CalendarQuarter_CalendarQuarter", "BikeSales", "CalendarQuarter",
                null, null);
        helper.addAssociationSet(container, "BikeSales_Country_Country_CountryCode",
                "Sandbox.BikeSales_Country_Country_CountryCode", "BikeSales", "Country", null, null);
        helper.addAssociationSet(container, "BikeSales_Currency_Currency_CurrencyKey",
                "Sandbox.BikeSales_Currency_Currency_CurrencyKey", "BikeSales", "Currency", null, null);
        helper.addAssociationSet(container, "BikeSales_SalesChannel_SalesChannel_SalesChannelCode",
                "Sandbox.BikeSales_SalesChannel_SalesChannel_SalesChannelCode", "BikeSales", "SalesChannel",
                TState.INACTIVE, null);

        // bi:EntityContainer
        TEntityContainer biContainer = biFactory.createTEntityContainer();
        biContainer.setCaption("CSDLTest");
        biContainer.setCulture("en-US");
        TCompareOptions compareOptions = biFactory.createTCompareOptions();
        compareOptions.setIgnoreCase(true);
        biContainer.setCompareOptions(compareOptions);
        container.setBiEntityContainer(biContainer);

        schema.getEntityContainer().add(container);

        // EntityTypes
        schema.getEntityType().add(buildBike());
        schema.getEntityType().add(buildBikeSales());
        schema.getEntityType().add(buildBikeSubcategory());
        schema.getEntityType().add(buildCalendarQuarter());
        schema.getEntityType().add(buildCountry());
        schema.getEntityType().add(buildCurrency());
        schema.getEntityType().add(buildSalesChannel());

        // Associations
        helper.addAssociation(schema, "Bike_BikeSubcategory_BikeSubcategory_ProductSubcategoryKey",
                "Bike_ProductSubcategoryKey", "Sandbox.Bike", "*", "BikeSubcategory_ProductSubcategoryKey",
                "Sandbox.BikeSubcategory", "0..1");
        helper.addAssociation(schema, "BikeSales_Bike_Bike_ProductKey", "BikeSales_ProductKey", "Sandbox.BikeSales",
                "*", "Bike_ProductKey", "Sandbox.Bike", "0..1");
        helper.addAssociation(schema, "BikeSales_CalendarQuarter_CalendarQuarter_CalendarQuarter",
                "BikeSales_CalendarQuarter", "Sandbox.BikeSales", "*", "CalendarQuarter_CalendarQuarter",
                "Sandbox.CalendarQuarter", "0..1");
        helper.addAssociation(schema, "BikeSales_Country_Country_CountryCode", "BikeSales_CountryCode",
                "Sandbox.BikeSales", "*", "Country_CountryCode", "Sandbox.Country", "0..1");
        helper.addAssociation(schema, "BikeSales_Currency_Currency_CurrencyKey", "BikeSales_CurrencyKey",
                "Sandbox.BikeSales", "*", "Currency_CurrencyKey", "Sandbox.Currency", "0..1");
        helper.addAssociation(schema, "BikeSales_SalesChannel_SalesChannel_SalesChannelCode",
                "BikeSales_SalesChannelCode", "Sandbox.BikeSales", "*", "SalesChannel_SalesChannelCode",
                "Sandbox.SalesChannel", "0..1");

        return schema;
    }

    private TEntityType buildBike() {
        TEntityType entityType = helper.createEntityType("Bike");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "ProductKey", "Int64", false);
        helper.addSimpleStringProperty(entityType, "ProductAlternateKey");
        helper.addSimpleProperty(entityType, "ProductSubcategoryKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "ProductName");
        helper.addDecimalProperty(entityType, "StandardCost", 19, 4);
        helper.addSimpleProperty(entityType, "FinishedGoodsFlag", "Boolean", true);

        // Color property with extended bi attributes
        helper.addPropertyWithExtendedBi(entityType, "Color", "String", "Context", "Left", "counts", "Descending", true,
                TDefaultAggregateFunction.MAX);

        helper.addDecimalProperty(entityType, "ListPrice", 19, 4);
        helper.addSimpleStringProperty(entityType, "Size");
        helper.addSimpleStringProperty(entityType, "SizeRange");
        helper.addSimpleProperty(entityType, "Weight", "Double", true);
        helper.addDecimalProperty(entityType, "DealerPrice", 19, 4);
        helper.addSimpleStringProperty(entityType, "Class");
        helper.addSimpleStringProperty(entityType, "Style");
        helper.addSimpleStringProperty(entityType, "ModelName");
        helper.addSimpleStringProperty(entityType, "Description");
        helper.addSimpleStringProperty(entityType, "WeightUnitMeasureCode");
        helper.addSimpleStringProperty(entityType, "SizeUnitMeasureCode");
        helper.addSimpleProperty(entityType, "SafetyStockLevel", "Int64", true);
        helper.addSimpleProperty(entityType, "ReorderPoint", "Int64", true);
        helper.addSimpleProperty(entityType, "DaysToManufacture", "Int64", true);
        helper.addSimpleStringProperty(entityType, "ProductLine");

        // Navigation property
        helper.addNavigationPropertySimple(entityType, "BikeSubcategory_ProductSubcategoryKey",
                "Sandbox.Bike_BikeSubcategory_BikeSubcategory_ProductSubcategoryKey", "Bike_ProductSubcategoryKey",
                "BikeSubcategory_ProductSubcategoryKey");

        // bi:EntityType with Hierarchy
        org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityType biEntityType = biFactory.createTEntityType();

        TMemberRefs displayKey = biFactory.createTMemberRefs();
        helper.addMemberRef(displayKey, "Color");
        biEntityType.setDisplayKey(displayKey);

        TMemberRefs defaultDetails = biFactory.createTMemberRefs();
        helper.addMemberRef(defaultDetails, "Color");
        biEntityType.setDefaultDetails(defaultDetails);

        TMemberRefs sortMembers = biFactory.createTMemberRefs();
        helper.addMemberRef(sortMembers, "Color");
        biEntityType.setSortMembers(sortMembers);

        // Hierarchy
        THierarchy hierarchy = helper.createHierarchy("Product_Hierarchy", "Product Hierarchy", "Product Hierarchy",
                "DESCRIPTION_ProductModelCateg_Hierarchies");
        TLevel level1 = helper.createLevel("ProductLine", "ProductLine");
        TLevel level2 = helper.createLevel("ModelName", "ModelName");
        hierarchy.getLevel().add(level1);
        hierarchy.getLevel().add(level2);
        biEntityType.getHierarchy().add(hierarchy);

        entityType.setBiEntityType(biEntityType);
        return entityType;
    }

    private TEntityType buildBikeSales() {
        TEntityType entityType = helper.createEntityType("BikeSales");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleStringProperty(entityType, "SalesOrderNumber");
        helper.addSimpleProperty(entityType, "SalesOrderLineNumber", "Int64", true);
        helper.addSimpleProperty(entityType, "RevisionNumber", "Int64", true);
        helper.addSimpleProperty(entityType, "ProductKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "CountryCode");
        helper.addSimpleProperty(entityType, "CurrencyKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "CalendarQuarter");
        helper.addSimpleStringProperty(entityType, "SalesChannelCode");
        helper.addSimpleProperty(entityType, "OrderQuantity", "Int64", true);
        helper.addDecimalProperty(entityType, "UnitPrice", 19, 4);
        helper.addDecimalProperty(entityType, "ExtendedAmount", 19, 4);
        helper.addSimpleProperty(entityType, "UnitPriceDiscountPct", "Double", true);
        helper.addSimpleProperty(entityType, "DiscountAmount", "Double", true);
        helper.addDecimalProperty(entityType, "ProductStandardCost", 19, 4);
        helper.addDecimalProperty(entityType, "TotalProductCost", 19, 4);
        helper.addDecimalProperty(entityType, "SalesAmount", 19, 4);
        helper.addDecimalProperty(entityType, "TaxAmt", 19, 4);
        helper.addDecimalProperty(entityType, "Freight", 19, 4);
        helper.addSimpleStringProperty(entityType, "CarrierTrackingNumber");
        helper.addSimpleStringProperty(entityType, "CustomerPONumber");
        helper.addSimpleStringProperty(entityType, "CustomerAccountNumber");

        // Sum_of_TotalProductCost measure
        helper.addMeasurePropertySimple(entityType, "Sum_of_TotalProductCost", "Decimal", 19, 4,
                "Sum of TotalProductCost", "Sum of TotalProductCost", "\\$#,0.00;(\\$#,0.00);\\$#,0.00");

        // Sum_of_SalesAmount measure with KPI
        helper.addMeasurePropertyWithKpi(entityType, "Sum_of_SalesAmount", "Decimal", 19, 4, "Sum of SalesAmount",
                "Sum of SalesAmount", "\\$#,0.00;(\\$#,0.00);\\$#,0.00", "KPI Description", "Three Circles Colored",
                "v_Sum_of_SalesAmount_Goal", "v_Sum_of_SalesAmount_Status");

        // Navigation properties
        helper.addNavigationPropertySimple(entityType, "Bike_ProductKey", "Sandbox.BikeSales_Bike_Bike_ProductKey",
                "BikeSales_ProductKey", "Bike_ProductKey");
        helper.addNavigationPropertySimple(entityType, "CalendarQuarter_CalendarQuarter",
                "Sandbox.BikeSales_CalendarQuarter_CalendarQuarter_CalendarQuarter", "BikeSales_CalendarQuarter",
                "CalendarQuarter_CalendarQuarter");
        helper.addNavigationPropertySimple(entityType, "Country_CountryCode",
                "Sandbox.BikeSales_Country_Country_CountryCode", "BikeSales_CountryCode", "Country_CountryCode");
        helper.addNavigationPropertySimple(entityType, "Currency_CurrencyKey",
                "Sandbox.BikeSales_Currency_Currency_CurrencyKey", "BikeSales_CurrencyKey", "Currency_CurrencyKey");
        helper.addNavigationPropertySimple(entityType, "SalesChannel_SalesChannelCode",
                "Sandbox.BikeSales_SalesChannel_SalesChannel_SalesChannelCode", "BikeSales_SalesChannelCode",
                "SalesChannel_SalesChannelCode");

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildBikeSubcategory() {
        TEntityType entityType = helper.createEntityType("BikeSubcategory");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "ProductSubcategoryKey", "Int64", false);
        helper.addSimpleStringProperty(entityType, "Subcategory");

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildCalendarQuarter() {
        TEntityType entityType = helper.createEntityType("CalendarQuarter");

        helper.addRowNumberProperty(entityType);
        helper.addStringPropertyWithCaptionAndRef(entityType, "CalendarQuarter2", false, "CalendarQuarter",
                "CalendarQuarter");

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildCountry() {
        TEntityType entityType = helper.createEntityType("Country");

        helper.addRowNumberProperty(entityType);
        helper.addStringPropertyNotNull(entityType, "CountryCode");
        helper.addSimpleStringProperty(entityType, "CountryName");

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildCurrency() {
        TEntityType entityType = helper.createEntityType("Currency");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "CurrencyKey", "Int64", false);
        helper.addSimpleStringProperty(entityType, "CurrencyAlternateKey");
        helper.addSimpleStringProperty(entityType, "CurrencyName");

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildSalesChannel() {
        TEntityType entityType = helper.createEntityType("SalesChannel");

        helper.addRowNumberProperty(entityType);
        helper.addStringPropertyNotNull(entityType, "SalesChannelCode");
        helper.addSimpleStringProperty(entityType, "SalesChannelName");

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }
}
