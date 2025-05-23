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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300_300;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.eclipse.daanse.xmla.ws.eng300_300 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    public static final String NAMESPACE_URI = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300";
    private static final QName _DirectQueryMode_QNAME = new QName(NAMESPACE_URI, "DirectQueryMode");
    private static final QName _AllowedRowsExpression_QNAME = new QName(NAMESPACE_URI, "AllowedRowsExpression");
    private static final QName _DirectQueryUsage_QNAME = new QName(NAMESPACE_URI, "DirectQueryUsage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for
     * package: org.eclipse.daanse.xmla.ws.eng300_300
     *
     */
    public ObjectFactory() {
        // constructor
    }

    /**
     * Create an instance of {@link RelationshipEndTranslation }
     *
     */
    public RelationshipEndTranslation createRelationshipEndTranslation() {
        return new RelationshipEndTranslation();
    }

    /**
     * Create an instance of {@link RelationshipEnd }
     *
     */
    public RelationshipEnd createRelationshipEnd() {
        return new RelationshipEnd();
    }

    /**
     * Create an instance of {@link RelationshipEnd.Attributes }
     *
     */
    public RelationshipEnd.Attributes createRelationshipEndAttributes() {
        return new RelationshipEnd.Attributes();
    }

    /**
     * Create an instance of {@link XEvent }
     *
     */
    public XEvent createXEvent() {
        return new XEvent();
    }

    /**
     * Create an instance of {@link Relationship }
     *
     */
    public Relationship createRelationship() {
        return new Relationship();
    }

    /**
     * Create an instance of {@link Relationships }
     *
     */
    public Relationships createRelationships() {
        return new Relationships();
    }

    /**
     * Create an instance of {@link RelationshipEnd.Translations }
     *
     */
    public RelationshipEnd.Translations createRelationshipEndTranslations() {
        return new RelationshipEnd.Translations();
    }

    /**
     * Create an instance of {@link RelationshipEnd.Attributes.Attribute }
     *
     */
    public RelationshipEnd.Attributes.Attribute createRelationshipEndAttributesAttribute() {
        return new RelationshipEnd.Attributes.Attribute();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300", name = "DirectQueryMode")
    public JAXBElement<String> createDirectQueryMode(String value) {
        return new JAXBElement<>(_DirectQueryMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300", name = "AllowedRowsExpression")
    public JAXBElement<String> createAllowedRowsExpression(String value) {
        return new JAXBElement<>(_AllowedRowsExpression_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/analysisservices/2011/engine/300/300", name = "DirectQueryUsage")
    public JAXBElement<String> createDirectQueryUsage(String value) {
        return new JAXBElement<>(_DirectQueryUsage_QNAME, String.class, null, value);
    }

}
