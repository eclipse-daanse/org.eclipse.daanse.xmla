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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine100_100;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.eclipse.daanse.xmla.ws.eng100_100 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    public static final String HTTP_SCHEMAS_2008_ENGINE_100_100 = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100";
    private static final QName _DbStorageLocation_QNAME = new QName(HTTP_SCHEMAS_2008_ENGINE_100_100,
            "DbStorageLocation");
    private static final QName _HoldoutMaxPercent_QNAME = new QName(HTTP_SCHEMAS_2008_ENGINE_100_100,
            "HoldoutMaxPercent");
    private static final QName _HoldoutMaxCases_QNAME = new QName(HTTP_SCHEMAS_2008_ENGINE_100_100, "HoldoutMaxCases");
    private static final QName _HoldoutSeed_QNAME = new QName(HTTP_SCHEMAS_2008_ENGINE_100_100, "HoldoutSeed");
    private static final QName _HoldoutActualSize_QNAME = new QName(HTTP_SCHEMAS_2008_ENGINE_100_100,
            "HoldoutActualSize");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for
     * package: org.eclipse.daanse.xmla.ws.eng100_100
     *
     */
    public ObjectFactory() {
        // constructor
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100", name = "DbStorageLocation")
    public JAXBElement<String> createDbStorageLocation(String value) {
        return new JAXBElement<>(_DbStorageLocation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100", name = "HoldoutMaxPercent")
    public JAXBElement<Integer> createHoldoutMaxPercent(Integer value) {
        return new JAXBElement<>(_HoldoutMaxPercent_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100", name = "HoldoutMaxCases")
    public JAXBElement<Integer> createHoldoutMaxCases(Integer value) {
        return new JAXBElement<>(_HoldoutMaxCases_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100", name = "HoldoutSeed")
    public JAXBElement<Integer> createHoldoutSeed(Integer value) {
        return new JAXBElement<>(_HoldoutSeed_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100/100", name = "HoldoutActualSize")
    public JAXBElement<Integer> createHoldoutActualSize(Integer value) {
        return new JAXBElement<>(_HoldoutActualSize_QNAME, Integer.class, null, value);
    }

}
