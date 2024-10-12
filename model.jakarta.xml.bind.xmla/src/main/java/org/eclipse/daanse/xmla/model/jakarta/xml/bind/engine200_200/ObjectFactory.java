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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine200_200;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.eclipse.daanse.xmla.ws.eng200_200 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    public static final String NAMESPACE_URI = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200";
    private static final QName _StorageEngineUsed_QNAME = new QName(NAMESPACE_URI, "StorageEngineUsed");
    private static final QName _ImagePath_QNAME = new QName(NAMESPACE_URI, "ImagePath");
    private static final QName _ImageUrl_QNAME = new QName(NAMESPACE_URI, "ImageUrl");
    private static final QName _ImageUniqueID_QNAME = new QName(NAMESPACE_URI, "ImageUniqueID");
    private static final QName _ImageVersion_QNAME = new QName(NAMESPACE_URI, "ImageVersion");
    private static final QName _Token_QNAME = new QName(NAMESPACE_URI, "Token");
    private static final QName _ProcessingRecommendation_QNAME = new QName(NAMESPACE_URI, "ProcessingRecommendation");
    private static final QName _ProcessingState_QNAME = new QName(NAMESPACE_URI, "ProcessingState");
    private static final QName _ShareDimensionStorage_QNAME = new QName(NAMESPACE_URI, "ShareDimensionStorage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for
     * package: org.eclipse.daanse.xmla.ws.eng200_200
     *
     */
    public ObjectFactory() {
        // constructor
    }

    /**
     * Create an instance of {@link RowNumberBinding }
     *
     */
    public RowNumberBinding createRowNumberBinding() {
        return new RowNumberBinding();
    }

    /**
     * Create an instance of {@link ExpressionBinding }
     *
     */
    public ExpressionBinding createExpressionBinding() {
        return new ExpressionBinding();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "StorageEngineUsed")
    public JAXBElement<String> createStorageEngineUsed(String value) {
        return new JAXBElement<>(_StorageEngineUsed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ImagePath")
    public JAXBElement<String> createImagePath(String value) {
        return new JAXBElement<>(_ImagePath_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ImageUrl")
    public JAXBElement<String> createImageUrl(String value) {
        return new JAXBElement<>(_ImageUrl_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ImageUniqueID")
    public JAXBElement<String> createImageUniqueID(String value) {
        return new JAXBElement<>(_ImageUniqueID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ImageVersion")
    public JAXBElement<String> createImageVersion(String value) {
        return new JAXBElement<>(_ImageVersion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "Token")
    public JAXBElement<String> createToken(String value) {
        return new JAXBElement<>(_Token_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ProcessingRecommendation")
    public JAXBElement<String> createProcessingRecommendation(String value) {
        return new JAXBElement<>(_ProcessingRecommendation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ProcessingState")
    public JAXBElement<String> createProcessingState(String value) {
        return new JAXBElement<>(_ProcessingState_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200", name = "ShareDimensionStorage")
    public JAXBElement<String> createShareDimensionStorage(String value) {
        return new JAXBElement<>(_ShareDimensionStorage_QNAME, String.class, null, value);
    }

}
