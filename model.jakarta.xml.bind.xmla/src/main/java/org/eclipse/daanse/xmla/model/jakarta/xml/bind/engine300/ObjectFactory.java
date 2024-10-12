/*
* Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300;

import javax.xml.namespace.QName;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine.ImpersonationInfo;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.eclipse.daanse.xmla.ws.eng300 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    public static final String NAMESPACE_URI = "http://schemas.microsoft.com/analysisservices/2011/engine/300";

    private static final QName _DataEmbeddingStyle_QNAME = new QName(NAMESPACE_URI, "DataEmbeddingStyle");
    private static final QName _QueryImpersonationInfo_QNAME = new QName(NAMESPACE_URI, "QueryImpersonationInfo");
    private static final QName _QueryHints_QNAME = new QName(NAMESPACE_URI, "QueryHints");
    private static final QName _StringStoresCompatibilityLevel_QNAME = new QName(NAMESPACE_URI,
            "StringStoresCompatibilityLevel");
    private static final QName _CurrentStringStoresCompatibilityLevel_QNAME = new QName(NAMESPACE_URI,
            "CurrentStringStoresCompatibilityLevel");
    private static final QName _ProcessingState_QNAME = new QName(NAMESPACE_URI, "ProcessingState");
    private static final QName _StructureType_QNAME = new QName(NAMESPACE_URI, "StructureType");
    private static final QName _ServerMode_QNAME = new QName(NAMESPACE_URI, "ServerMode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for
     * package: org.eclipse.daanse.xmla.ws.eng300
     *
     */
    public ObjectFactory() {
        // constructor
    }

    /**
     * Create an instance of {@link RelationshipEndVisualizationProperties }
     *
     */
    public RelationshipEndVisualizationProperties createRelationshipEndVisualizationProperties() {
        return new RelationshipEndVisualizationProperties();
    }

    /**
     * Create an instance of {@link DimensionAttributeVisualizationProperties }
     *
     */
    public DimensionAttributeVisualizationProperties createDimensionAttributeVisualizationProperties() {
        return new DimensionAttributeVisualizationProperties();
    }

    /**
     * Create an instance of {@link HierarchyVisualizationProperties }
     *
     */
    public HierarchyVisualizationProperties createHierarchyVisualizationProperties() {
        return new HierarchyVisualizationProperties();
    }

    /**
     * Create an instance of {@link CalculationPropertiesVisualizationProperties }
     *
     */
    public CalculationPropertiesVisualizationProperties createCalculationPropertiesVisualizationProperties() {
        return new CalculationPropertiesVisualizationProperties();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "DataEmbeddingStyle")
    public JAXBElement<String> createDataEmbeddingStyle(String value) {
        return new JAXBElement<>(_DataEmbeddingStyle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImpersonationInfo }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link ImpersonationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "QueryImpersonationInfo")
    public JAXBElement<ImpersonationInfo> createQueryImpersonationInfo(ImpersonationInfo value) {
        return new JAXBElement<>(_QueryImpersonationInfo_QNAME, ImpersonationInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "QueryHints")
    public JAXBElement<String> createQueryHints(String value) {
        return new JAXBElement<>(_QueryHints_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "StringStoresCompatibilityLevel")
    public JAXBElement<Integer> createStringStoresCompatibilityLevel(Integer value) {
        return new JAXBElement<>(_StringStoresCompatibilityLevel_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "CurrentStringStoresCompatibilityLevel")
    public JAXBElement<Integer> createCurrentStringStoresCompatibilityLevel(Integer value) {
        return new JAXBElement<>(_CurrentStringStoresCompatibilityLevel_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "ProcessingState")
    public JAXBElement<String> createProcessingState(String value) {
        return new JAXBElement<>(_ProcessingState_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "StructureType")
    public JAXBElement<String> createStructureType(String value) {
        return new JAXBElement<>(_StructureType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300", name = "ServerMode")
    public JAXBElement<String> createServerMode(String value) {
        return new JAXBElement<>(_ServerMode_QNAME, String.class, null, value);
    }

}
