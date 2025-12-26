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
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityContainer;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntitySet;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TEntityType;
import org.eclipse.daanse.xmla.csdl.model.v2.bi.TProperty;
import org.eclipse.daanse.xmla.csdl.model.v2.edm.util.EdmResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CsdlBiSerializationTest {

    private ResourceSet resourceSet;
    private EdmFactory edmFactory;
    private BiFactory biFactory;

    @BeforeEach
    void setUp() {
        resourceSet = new ResourceSetImpl();

        // Register the EDM resource factory that respects ExtendedMetaData
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new EdmResourceFactoryImpl());

        // Register packages
        resourceSet.getPackageRegistry().put(EdmPackage.eNS_URI, EdmPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(BiPackage.eNS_URI, BiPackage.eINSTANCE);

        edmFactory = EdmFactory.eINSTANCE;
        biFactory = BiFactory.eINSTANCE;
    }

    @Test
    void testEdmEntityContainerSerialization() throws IOException {
        EntityContainerType container = edmFactory.createEntityContainerType();
        container.setName("SalesModel");

        EntitySetType entitySet = edmFactory.createEntitySetType();
        entitySet.setName("Products");
        entitySet.setEntityType("Model.Product");
        container.getEntitySet().add(entitySet);

        String xml = serializeToXml(container);
        System.out.println("=== EDM EntityContainer (ohne BI) ===");
        System.out.println(xml);
        System.out.println();
    }

    @Test
    void testBiEntityContainerSerialization() throws IOException {
        TEntityContainer biContainer = biFactory.createTEntityContainer();
        biContainer.setCaption("Verkaufsmodell");
        biContainer.setCulture("de-DE");

        String xml = serializeToXml(biContainer);
        System.out.println("=== BI EntityContainer ===");
        System.out.println(xml);
        System.out.println();
    }

    @Test
    void testBiEntitySetSerialization() throws IOException {
        TEntitySet biEntitySet = biFactory.createTEntitySet();
        biEntitySet.setCaption("Produkte");
        biEntitySet.setHidden(false);

        String xml = serializeToXml(biEntitySet);
        System.out.println("=== BI EntitySet ===");
        System.out.println(xml);
        System.out.println();
    }

    @Test
    void testBiEntityTypeSerialization() throws IOException {
        TEntityType entityType = biFactory.createTEntityType();
        entityType.setContents("Regular");

        String xml = serializeToXml(entityType);
        System.out.println("=== BI EntityType ===");
        System.out.println(xml);
        System.out.println();
    }

    @Test
    void testEdmWithBiEntityContainer() throws IOException {
        EntityContainerType container = edmFactory.createEntityContainerType();
        container.setName("SalesModel");

        TEntityContainer biContainer = biFactory.createTEntityContainer();
        biContainer.setCaption("Verkaufsmodell");
        biContainer.setCulture("de-DE");

        container.setBiEntityContainer(biContainer);

        EntitySetType entitySet = edmFactory.createEntitySetType();
        entitySet.setName("Products");
        entitySet.setEntityType("Model.Product");

        TEntitySet biEntitySet = biFactory.createTEntitySet();
        biEntitySet.setCaption("Produkte");
        biEntitySet.setHidden(false);

        entitySet.setBiEntitySet(biEntitySet);

        container.getEntitySet().add(entitySet);

        String xml = serializeToXml(container);
        System.out.println("=== EDM EntityContainer MIT BI-Annotationen ===");
        System.out.println(xml);
        System.out.println();
    }

    @Test
    void testEdmEntityTypeWithBi() throws IOException {
        org.eclipse.daanse.xmla.csdl.model.v2.edm.TEntityType productType = edmFactory.createTEntityType();
        productType.setName("Product");

        TEntityType biEntityType = biFactory.createTEntityType();
        biEntityType.setContents("Regular");

        productType.setBiEntityType(biEntityType);

        TEntityProperty nameProperty = edmFactory.createTEntityProperty();
        nameProperty.setName("Name");
        nameProperty.setType("Edm.String");

        TProperty biProperty = biFactory.createTProperty();
        biProperty.setCaption("Produktname");
        biProperty.setHidden(false);

        nameProperty.setBiProperty(biProperty);

        productType.getProperty().add(nameProperty);

        String xml = serializeToXml(productType);
        System.out.println("=== EDM EntityType MIT BI-Annotationen ===");
        System.out.println(xml);
        System.out.println();
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
        container.setBiEntityContainer(biContainer);

        EntitySetType entitySet = edmFactory.createEntitySetType();
        entitySet.setName("Products");
        entitySet.setEntityType("Model.Product");

        TEntitySet biEntitySet = biFactory.createTEntitySet();
        biEntitySet.setCaption("Produkte");
        entitySet.setBiEntitySet(biEntitySet);

        container.getEntitySet().add(entitySet);

        EntitySetType entitySet2 = edmFactory.createEntitySetType();
        entitySet2.setName("Orders");
        entitySet2.setEntityType("Model.Order");

        TEntitySet biEntitySet2 = biFactory.createTEntitySet();
        biEntitySet2.setCaption("Bestellungen");
        entitySet2.setBiEntitySet(biEntitySet2);

        container.getEntitySet().add(entitySet2);

        schema.getEntityContainer().add(container);

        org.eclipse.daanse.xmla.csdl.model.v2.edm.TEntityType productType = edmFactory.createTEntityType();
        productType.setName("Product");

        TEntityType biProductType = biFactory.createTEntityType();
        biProductType.setContents("Regular");
        productType.setBiEntityType(biProductType);

        TEntityProperty idProperty = edmFactory.createTEntityProperty();
        idProperty.setName("ProductId");
        idProperty.setType("Edm.Int32");
        idProperty.setNullable(false);
        productType.getProperty().add(idProperty);

        TEntityProperty nameProperty = edmFactory.createTEntityProperty();
        nameProperty.setName("Name");
        nameProperty.setType("Edm.String");

        TProperty biNameProperty = biFactory.createTProperty();
        biNameProperty.setCaption("Produktname");
        biNameProperty.setHidden(false);
        nameProperty.setBiProperty(biNameProperty);

        productType.getProperty().add(nameProperty);

        schema.getEntityType().add(productType);

        String xml = serializeToXml(schema);
        System.out.println("=== CSDL Schema MIT BI-Annotationen (komplett) ===");
        System.out.println(xml);
        System.out.println();
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
