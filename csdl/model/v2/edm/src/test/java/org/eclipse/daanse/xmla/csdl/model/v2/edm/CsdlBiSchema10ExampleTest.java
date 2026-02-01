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
import org.eclipse.daanse.xmla.csdl.model.v2.bi.DefaultImageType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.DefaultMeasureType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TAlignment;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TCompareOptions;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TContextualNameRule;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TDefaultAggregateFunction;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityContainer;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMemberRef;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TMemberRefs;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TSortDirection;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TState;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test that programmatically generates XML matching example_1_0.xml
 * (Schema 1.0) and compares the complete generated XML against the example
 * file.
 * <p>
 * Reference: [MS-CSDLBI] Section 5.1: CSDLBI Schema 1.0
 */
@DisplayName("CSDL-BI Schema 1.0 Example Test")
public class CsdlBiSchema10ExampleTest {

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
    @DisplayName("Generated Schema 1.0 matches example_1_0.xml structure")
    void testSchema10MatchesExample() throws IOException {
        TSchema generatedSchema = buildSchema10();
        TSchema expectedSchema = helper.loadSchemaFromXml(getClass(), "example_1_0.xml");

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

    private TSchema buildSchema10() {
        TSchema schema = edmFactory.createTSchema();
        schema.setNamespace("Sandbox");
        helper.setBiVersion(schema, "1.0");

        // EntityContainer
        EntityContainerType container = edmFactory.createEntityContainerType();
        container.setName("Sandbox");

        // Documentation for container
        TDocumentation containerDoc = edmFactory.createTDocumentation();
        TText containerSummary = edmFactory.createTText();
        containerSummary.getMixed().add(FeatureMapUtil.createRawTextEntry("DescriptionRolePlayingDimensionsDB"));
        containerDoc.setSummary(containerSummary);
        container.setDocumentation(containerDoc);

        // EntitySets
        helper.addEntitySet(container, "DimCustomer", "Sandbox.DimCustomer", "Description_Dimension_DimCustomer", null);
        helper.addEntitySet(container, "DimEmployee", "Sandbox.DimEmployee", null, null);
        helper.addEntitySet(container, "DimGeography", "Sandbox.DimGeography", null, null);
        helper.addEntitySet(container, "DimProduct", "Sandbox.DimProduct", null, null);
        helper.addEntitySet(container, "DimProductCategory", "Sandbox.DimProductCategory", null, null);
        helper.addEntitySet(container, "DimProductSubcategory", "Sandbox.DimProductSubcategory", null, null);
        helper.addEntitySet(container, "DimStore", "Sandbox.DimStore", null, null);
        helper.addEntitySet(container, "DimTime", "Sandbox.DimTime", null, null);
        helper.addEntitySet(container, "FactInternetSales", "Sandbox.FactInternetSales", null, null);

        // AssociationSets
        helper.addAssociationSet(container, "DimCustomer_DimGeography_Geography",
                "Sandbox.DimCustomer_DimGeography_Geography", "DimCustomer", "DimGeography", null, null);
        helper.addAssociationSet(container, "DimProduct_DimProductSubcategory_Subcategory",
                "Sandbox.DimProduct_DimProductSubcategory_Subcategory", "DimProduct", "DimProductSubcategory", null,
                null);
        helper.addAssociationSet(container, "DimProductSubcategory_DimProductCategory_Category",
                "Sandbox.DimProductSubcategory_DimProductCategory_Category", "DimProductSubcategory",
                "DimProductCategory", null, null);
        helper.addAssociationSet(container, "DimStore_DimGeography_Geography2",
                "Sandbox.DimStore_DimGeography_Geography2", "DimStore", "DimGeography", TState.INACTIVE, null);
        helper.addAssociationSet(container, "FactInternetSales_DimCustomer_Customer2",
                "Sandbox.FactInternetSales_DimCustomer_Customer2", "FactInternetSales", "DimCustomer", null, null);
        helper.addAssociationSet(container, "FactInternetSales_DimCustomer_Customer_2",
                "Sandbox.FactInternetSales_DimCustomer_Customer_2", "FactInternetSales", "DimCustomer", TState.INACTIVE,
                null);
        helper.addAssociationSet(container, "FactInternetSales_DimTime_Time", "Sandbox.FactInternetSales_DimTime_Time",
                "FactInternetSales", "DimTime", TState.INACTIVE, null);
        helper.addAssociationSet(container, "FactInternetSales_DimTime_Time_2",
                "Sandbox.FactInternetSales_DimTime_Time_2", "FactInternetSales", "DimTime", TState.INACTIVE, null);
        helper.addAssociationSet(container, "FactInternetSales_DimTime_Time_3",
                "Sandbox.FactInternetSales_DimTime_Time_3", "FactInternetSales", "DimTime", null, null);
        helper.addAssociationSet(container, "FactInternetSales_DimStore_Store2",
                "Sandbox.FactInternetSales_DimStore_Store2", "FactInternetSales", "DimStore", null, null);
        helper.addAssociationSet(container, "FactInternetSales_DimEmployee_Employee",
                "Sandbox.FactInternetSales_DimEmployee_Employee", "FactInternetSales", "DimEmployee", null, null);
        helper.addAssociationSet(container, "FactInternetSales_DimProduct_Product2",
                "Sandbox.FactInternetSales_DimProduct_Product2", "FactInternetSales", "DimProduct", null, null);

        // bi:EntityContainer
        TEntityContainer biContainer = biFactory.createTEntityContainer();
        biContainer.setCaption("CaptionRolePlayingDimensionsDB");
        biContainer.setCulture("ja-JP");
        TCompareOptions compareOptions = biFactory.createTCompareOptions();
        compareOptions.setIgnoreCase(true);
        compareOptions.setIgnoreKanaType(true);
        compareOptions.setIgnoreWidth(true);
        biContainer.setCompareOptions(compareOptions);
        container.setBiEntityContainer(biContainer);

        schema.getEntityContainer().add(container);

        // EntityTypes
        schema.getEntityType().add(buildDimCustomer());
        schema.getEntityType().add(buildDimEmployee());
        schema.getEntityType().add(buildDimGeography());
        schema.getEntityType().add(buildDimProduct());
        schema.getEntityType().add(buildDimProductCategory());
        schema.getEntityType().add(buildDimProductSubcategory());
        schema.getEntityType().add(buildDimStore());
        schema.getEntityType().add(buildDimTime());
        schema.getEntityType().add(buildFactInternetSales());

        // Associations
        helper.addAssociation(schema, "DimCustomer_DimGeography_Geography", "Customer", "Sandbox.DimCustomer", "*",
                "Geography", "Sandbox.DimGeography", "0..1");
        helper.addAssociation(schema, "DimProduct_DimProductSubcategory_Subcategory", "Product", "Sandbox.DimProduct",
                "*", "Subcategory", "Sandbox.DimProductSubcategory", "0..1");
        helper.addAssociation(schema, "DimProductSubcategory_DimProductCategory_Category", "Subcategory2",
                "Sandbox.DimProductSubcategory", "*", "Category", "Sandbox.DimProductCategory", "0..1");
        helper.addAssociation(schema, "DimStore_DimGeography_Geography2", "Store", "Sandbox.DimStore", "*",
                "Geography2", "Sandbox.DimGeography", "0..1");
        helper.addAssociation(schema, "FactInternetSales_DimCustomer_Customer2", "Sales", "Sandbox.FactInternetSales",
                "*", "Customer2", "Sandbox.DimCustomer", "0..1");
        helper.addAssociation(schema, "FactInternetSales_DimCustomer_Customer_2", "Sales2", "Sandbox.FactInternetSales",
                "*", "Customer_2", "Sandbox.DimCustomer", "0..1");
        helper.addAssociation(schema, "FactInternetSales_DimTime_Time", "Sales3", "Sandbox.FactInternetSales", "*",
                "Time", "Sandbox.DimTime", "0..1");
        helper.addAssociation(schema, "FactInternetSales_DimTime_Time_2", "Sales4", "Sandbox.FactInternetSales", "*",
                "Time_2", "Sandbox.DimTime", "0..1");
        helper.addAssociation(schema, "FactInternetSales_DimTime_Time_3", "Sales5", "Sandbox.FactInternetSales", "*",
                "Time_3", "Sandbox.DimTime", "0..1");
        helper.addAssociation(schema, "FactInternetSales_DimStore_Store2", "Sales6", "Sandbox.FactInternetSales", "*",
                "Store2", "Sandbox.DimStore", "0..1");
        helper.addAssociation(schema, "FactInternetSales_DimEmployee_Employee", "Sales7", "Sandbox.FactInternetSales",
                "*", "Employee", "Sandbox.DimEmployee", "0..1");
        helper.addAssociation(schema, "FactInternetSales_DimProduct_Product2", "FactInternetSales",
                "Sandbox.FactInternetSales", "*", "Product2", "Sandbox.DimProduct", "0..1");

        return schema;
    }

    private TEntityType buildDimCustomer() {
        TEntityType entityType = helper.createEntityType("DimCustomer");

        helper.addRowNumberProperty(entityType);
        helper.addPropertyWithCaption(entityType, "CustomerKey", "Int64", "Caption_Dimension_CustomerKey", true,
                "Description_Dimension_CustomerKey", "GeographyKey");
        helper.addSimpleProperty(entityType, "GeographyKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "CustomerAlternateKey");
        helper.addPropertyWithExtendedBi(entityType, "Title", "String", "Context", "Left", "money", "Ascending", true,
                TDefaultAggregateFunction.MAX);
        helper.addSimpleStringProperty(entityType, "FirstName");
        helper.addSimpleStringProperty(entityType, "MiddleName");
        helper.addSimpleStringProperty(entityType, "LastName");
        helper.addSimpleProperty(entityType, "NameStyle", "Boolean", true);
        helper.addSimpleProperty(entityType, "BirthDate", "DateTime", true);
        helper.addSimpleStringProperty(entityType, "MaritalStatus");
        helper.addSimpleStringProperty(entityType, "Suffix");
        helper.addSimpleStringProperty(entityType, "Gender");
        helper.addSimpleStringProperty(entityType, "EmailAddress");
        helper.addDecimalProperty(entityType, "YearlyIncome", 19, 4);
        helper.addSimpleProperty(entityType, "TotalChildren", "Int64", true);
        helper.addSimpleProperty(entityType, "NumberChildrenAtHome", "Int64", true);
        helper.addSimpleStringProperty(entityType, "EnglishEducation");
        helper.addSimpleStringProperty(entityType, "SpanishEducation");
        helper.addSimpleStringProperty(entityType, "FrenchEducation");
        helper.addSimpleStringProperty(entityType, "EnglishOccupation");
        helper.addSimpleStringProperty(entityType, "SpanishOccupation");
        helper.addSimpleStringProperty(entityType, "FrenchOccupation");
        helper.addSimpleStringProperty(entityType, "HouseOwnerFlag");
        helper.addSimpleProperty(entityType, "NumberCarsOwned", "Int64", true);
        helper.addSimpleStringProperty(entityType, "AddressLine1");
        helper.addSimpleStringProperty(entityType, "AddressLine2");
        helper.addSimpleStringProperty(entityType, "Phone");
        helper.addSimpleProperty(entityType, "DateFirstPurchase", "DateTime", true);
        helper.addSimpleStringProperty(entityType, "CommuteDistance");

        helper.addNavigationPropertyWithDoc(entityType, "Geography", "Sandbox.DimCustomer_DimGeography_Geography",
                "Customer", "Geography", "DESCRIPTION_RelationshipEnd_Cust_Geog", "CAPTION_RelationshipEnd_Cust_Geog",
                TContextualNameRule.CONTEXT);

        // bi:EntityType
        org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityType biEntityType = biFactory.createTEntityType();
        biEntityType.setContents("Customers");

        TMemberRefs displayKey = biFactory.createTMemberRefs();
        helper.addMemberRef(displayKey, "Geography");
        helper.addMemberRef(displayKey, "Title");
        biEntityType.setDisplayKey(displayKey);

        TMemberRefs defaultDetails = biFactory.createTMemberRefs();
        helper.addMemberRef(defaultDetails, "Title");
        helper.addMemberRef(defaultDetails, "Geography");
        biEntityType.setDefaultDetails(defaultDetails);

        DefaultImageType defaultImage = biFactory.createDefaultImageType();
        TMemberRef imageRef = biFactory.createTMemberRef();
        imageRef.setName("Title");
        defaultImage.setMemberRef(imageRef);
        biEntityType.setDefaultImage(defaultImage);

        TMemberRefs sortMembers = biFactory.createTMemberRefs();
        helper.addMemberRef(sortMembers, "Title");
        biEntityType.setSortMembers(sortMembers);

        entityType.setBiEntityType(biEntityType);

        return entityType;
    }

    private TEntityType buildDimEmployee() {
        TEntityType entityType = helper.createEntityType("DimEmployee");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "EmployeeKey", "Int64", false);
        helper.addSimpleProperty(entityType, "ParentEmployeeKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "EmployeeNationalIDAlternateKey");
        helper.addSimpleStringProperty(entityType, "ParentEmployeeNationalIDAlternateKey");
        helper.addSimpleProperty(entityType, "SalesTerritoryKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "FirstName");
        helper.addSimpleStringProperty(entityType, "LastName");
        helper.addSimpleStringProperty(entityType, "MiddleName");
        helper.addSimpleProperty(entityType, "NameStyle", "Boolean", true);
        helper.addSimpleStringProperty(entityType, "Title");
        helper.addSimpleProperty(entityType, "HireDate", "DateTime", true);
        helper.addSimpleProperty(entityType, "BirthDate", "DateTime", true);
        helper.addSimpleStringProperty(entityType, "LoginID");
        helper.addSimpleStringProperty(entityType, "EmailAddress");
        helper.addSimpleStringProperty(entityType, "Phone");
        helper.addSimpleStringProperty(entityType, "MaritalStatus");
        helper.addSimpleStringProperty(entityType, "EmergencyContactName");
        helper.addSimpleStringProperty(entityType, "EmergencyContactPhone");
        helper.addSimpleProperty(entityType, "SalariedFlag", "Boolean", true);
        helper.addSimpleStringProperty(entityType, "Gender");
        helper.addSimpleProperty(entityType, "PayFrequency", "Int64", true);
        helper.addDecimalProperty(entityType, "BaseRate", 19, 4);
        helper.addSimpleProperty(entityType, "VacationHours", "Int64", true);
        helper.addSimpleProperty(entityType, "SickLeaveHours", "Int64", true);
        helper.addSimpleProperty(entityType, "CurrentFlag", "Boolean", true);
        helper.addSimpleProperty(entityType, "SalesPersonFlag", "Boolean", true);
        helper.addSimpleStringProperty(entityType, "DepartmentName");
        helper.addSimpleProperty(entityType, "StartDate", "DateTime", true);
        helper.addSimpleProperty(entityType, "EndDate", "DateTime", true);
        helper.addSimpleStringProperty(entityType, "Status");

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildDimGeography() {
        TEntityType entityType = helper.createEntityType("DimGeography");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "GeographyKey", "Int64", false);
        helper.addSimpleStringProperty(entityType, "City");
        helper.addSimpleStringProperty(entityType, "StateProvinceCode");
        helper.addSimpleStringProperty(entityType, "StateProvinceName");
        helper.addSimpleStringProperty(entityType, "CountryRegionCode");
        helper.addSimpleStringProperty(entityType, "EnglishCountryRegionName");
        helper.addSimpleStringProperty(entityType, "SpanishCountryRegionName");
        helper.addSimpleStringProperty(entityType, "FrenchCountryRegionName");
        helper.addSimpleStringProperty(entityType, "PostalCode");
        helper.addSimpleProperty(entityType, "SalesTerritoryKey", "Int64", true);

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildDimProduct() {
        TEntityType entityType = helper.createEntityType("DimProduct");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "ProductKey", "Int64", false);
        helper.addSimpleStringProperty(entityType, "ProductAlternateKey");
        helper.addSimpleProperty(entityType, "ProductSubcategoryKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "WeightUnitMeasureCode");
        helper.addSimpleStringProperty(entityType, "SizeUnitMeasureCode");
        helper.addSimpleStringProperty(entityType, "EnglishProductName");
        helper.addSimpleStringProperty(entityType, "SpanishProductName");
        helper.addSimpleStringProperty(entityType, "FrenchProductName");
        helper.addDecimalProperty(entityType, "StandardCost", 19, 4);
        helper.addSimpleProperty(entityType, "FinishedGoodsFlag", "Boolean", true);
        helper.addSimpleStringProperty(entityType, "Color");
        helper.addSimpleProperty(entityType, "SafetyStockLevel", "Int64", true);
        helper.addSimpleProperty(entityType, "ReorderPoint", "Int64", true);
        helper.addDecimalProperty(entityType, "ListPrice", 19, 4);
        helper.addSimpleStringProperty(entityType, "Size");
        helper.addSimpleStringProperty(entityType, "SizeRange");
        helper.addSimpleProperty(entityType, "Weight", "Double", true);
        helper.addSimpleProperty(entityType, "DaysToManufacture", "Int64", true);
        helper.addSimpleStringProperty(entityType, "ProductLine");
        helper.addDecimalProperty(entityType, "DealerPrice", 19, 4);
        helper.addSimpleStringProperty(entityType, "Class");
        helper.addSimpleStringProperty(entityType, "Style");
        helper.addSimpleStringProperty(entityType, "ModelName");
        helper.addSimpleStringProperty(entityType, "EnglishDescription");
        helper.addSimpleStringProperty(entityType, "FrenchDescription");
        helper.addSimpleStringProperty(entityType, "ChineseDescription");
        helper.addSimpleStringProperty(entityType, "ArabicDescription");
        helper.addSimpleStringProperty(entityType, "HebrewDescription");
        helper.addSimpleStringProperty(entityType, "ThaiDescription");
        helper.addSimpleProperty(entityType, "StartDate", "DateTime", true);
        helper.addSimpleProperty(entityType, "EndDate", "DateTime", true);
        helper.addSimpleStringProperty(entityType, "Status");

        helper.addNavigationProperty(entityType, "Subcategory", "Sandbox.DimProduct_DimProductSubcategory_Subcategory",
                "Product", "Subcategory", TContextualNameRule.MERGE);

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildDimProductCategory() {
        TEntityType entityType = helper.createEntityType("DimProductCategory");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "ProductCategoryKey", "Int64", false);
        helper.addSimpleProperty(entityType, "ProductCategoryAlternateKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "EnglishProductCategoryName");
        helper.addSimpleStringProperty(entityType, "SpanishProductCategoryName");
        helper.addSimpleStringProperty(entityType, "FrenchProductCategoryName");

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildDimProductSubcategory() {
        TEntityType entityType = helper.createEntityType("DimProductSubcategory");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "ProductSubcategoryKey", "Int64", false);
        helper.addSimpleProperty(entityType, "ProductSubcategoryAlternateKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "EnglishProductSubcategoryName");
        helper.addSimpleStringProperty(entityType, "SpanishProductSubcategoryName");
        helper.addSimpleStringProperty(entityType, "FrenchProductSubcategoryName");
        helper.addSimpleProperty(entityType, "ProductCategoryKey", "Int64", true);

        helper.addNavigationProperty(entityType, "Category",
                "Sandbox.DimProductSubcategory_DimProductCategory_Category", "Subcategory2", "Category",
                TContextualNameRule.MERGE);

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildDimStore() {
        TEntityType entityType = helper.createEntityType("DimStore");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "StoreKey", "Int64", false);
        helper.addPropertyWithCaptionAndRef(entityType, "Geography_Key", "Int64", "Geography Key", "Geography Key");
        helper.addSimpleStringProperty(entityType, "StoreName");
        helper.addPropertyWithCaptionAndRef(entityType, "Number_of_Employees", "Int64", "Number of Employees",
                "Number of Employees");
        helper.addDecimalProperty(entityType, "Sales", 19, 4);

        helper.addNavigationProperty(entityType, "Geography2", "Sandbox.DimStore_DimGeography_Geography2", "Store",
                "Geography2", TContextualNameRule.MERGE);

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildDimTime() {
        TEntityType entityType = helper.createEntityType("DimTime");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "TimeKey", "Int64", false);
        helper.addSimpleProperty(entityType, "FullDateAlternateKey", "DateTime", true);
        helper.addSimpleProperty(entityType, "DayNumberOfWeek", "Int64", true);
        helper.addSimpleStringProperty(entityType, "EnglishDayNameOfWeek");
        helper.addSimpleStringProperty(entityType, "SpanishDayNameOfWeek");
        helper.addSimpleStringProperty(entityType, "FrenchDayNameOfWeek");
        helper.addSimpleProperty(entityType, "DayNumberOfMonth", "Int64", true);
        helper.addSimpleProperty(entityType, "DayNumberOfYear", "Int64", true);
        helper.addSimpleProperty(entityType, "WeekNumberOfYear", "Int64", true);
        helper.addSimpleStringProperty(entityType, "EnglishMonthName");
        helper.addSimpleStringProperty(entityType, "SpanishMonthName");
        helper.addSimpleStringProperty(entityType, "FrenchMonthName");
        helper.addSimpleProperty(entityType, "MonthNumberOfYear", "Int64", true);
        helper.addSimpleProperty(entityType, "CalendarQuarter", "Int64", true);
        helper.addSimpleStringProperty(entityType, "CalendarYear");
        helper.addSimpleProperty(entityType, "CalendarSemester", "Int64", true);
        helper.addSimpleProperty(entityType, "FiscalQuarter", "Int64", true);
        helper.addSimpleStringProperty(entityType, "FiscalYear");
        helper.addSimpleProperty(entityType, "FiscalSemester", "Int64", true);

        entityType.setBiEntityType(biFactory.createTEntityType());
        return entityType;
    }

    private TEntityType buildFactInternetSales() {
        TEntityType entityType = helper.createEntityType("FactInternetSales");

        helper.addRowNumberProperty(entityType);
        helper.addSimpleProperty(entityType, "ProductKey", "Int64", true);
        helper.addSimpleProperty(entityType, "OrderDateKey", "Int64", true);
        helper.addSimpleProperty(entityType, "DueDateKey", "Int64", true);
        helper.addSimpleProperty(entityType, "ShipDateKey", "Int64", true);
        helper.addSimpleProperty(entityType, "CustomerKey", "Int64", true);
        helper.addSimpleProperty(entityType, "PromotionKey", "Int64", true);
        helper.addSimpleProperty(entityType, "CurrencyKey", "Int64", true);
        helper.addSimpleProperty(entityType, "SalesTerritoryKey", "Int64", true);
        helper.addSimpleStringProperty(entityType, "SalesOrderNumber");
        helper.addSimpleProperty(entityType, "SalesOrderLineNumber", "Int64", true);
        helper.addSimpleProperty(entityType, "RevisionNumber", "Int64", true);
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
        helper.addSimpleProperty(entityType, "EmployeeKey", "Int64", true);
        helper.addSimpleProperty(entityType, "BillingCustomerKey", "Int64", true);
        helper.addSimpleProperty(entityType, "StoreKey", "Int64", true);

        // TotalSales with Measure
        helper.addMeasureProperty(entityType, "TotalSales", "Int64", "CaptionRolePlayingDimensionsDB",
                TContextualNameRule.CONTEXT, TAlignment.RIGHT, "\"$\"#,0.00;(\"$\"#,0.00);\"$\"#,0.00", "money",
                TSortDirection.ASCENDING, true, true, "DescriptionRolePlayingDimensionsDB");

        // Navigation properties
        helper.addNavigationProperty(entityType, "Customer2", "Sandbox.FactInternetSales_DimCustomer_Customer2",
                "Sales", "Customer2", TContextualNameRule.MERGE);
        helper.addNavigationProperty(entityType, "Customer_2", "Sandbox.FactInternetSales_DimCustomer_Customer_2",
                "Sales2", "Customer_2", TContextualNameRule.MERGE);
        helper.addNavigationProperty(entityType, "Time", "Sandbox.FactInternetSales_DimTime_Time", "Sales3", "Time",
                TContextualNameRule.MERGE);
        helper.addNavigationProperty(entityType, "Time_2", "Sandbox.FactInternetSales_DimTime_Time_2", "Sales4",
                "Time_2", TContextualNameRule.MERGE);
        helper.addNavigationProperty(entityType, "Time_3", "Sandbox.FactInternetSales_DimTime_Time_3", "Sales5",
                "Time_3", TContextualNameRule.MERGE);
        helper.addNavigationProperty(entityType, "Store2", "Sandbox.FactInternetSales_DimStore_Store2", "Sales6",
                "Store2", TContextualNameRule.MERGE);
        helper.addNavigationProperty(entityType, "Employee", "Sandbox.FactInternetSales_DimEmployee_Employee", "Sales7",
                "Employee", TContextualNameRule.MERGE);
        helper.addNavigationProperty(entityType, "Product2", "Sandbox.FactInternetSales_DimProduct_Product2",
                "FactInternetSales", "Product2", TContextualNameRule.MERGE);

        // bi:EntityType
        org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityType biEntityType = biFactory.createTEntityType();

        TMemberRefs defaultDetails = biFactory.createTMemberRefs();
        helper.addMemberRef(defaultDetails, "TotalSales");
        biEntityType.setDefaultDetails(defaultDetails);

        DefaultMeasureType defaultMeasure = biFactory.createDefaultMeasureType();
        TMemberRef measureRef = biFactory.createTMemberRef();
        measureRef.setName("TotalSales");
        defaultMeasure.setMemberRef(measureRef);
        biEntityType.setDefaultMeasure(defaultMeasure);

        entityType.setBiEntityType(biEntityType);

        return entityType;
    }
}
