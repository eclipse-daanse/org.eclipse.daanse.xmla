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

/**
 * Every namespace and prefix the XMLA wire format uses, in one place.
 * <p>
 * The prefixes are the ones a real Analysis Services instance emits. They carry
 * no meaning to a conforming parser, but clients exist that key off them, so
 * reproducing them costs nothing and avoids a class of interoperability
 * surprise.
 */
public final class XmlaNamespaces {

    public static final String SOAP_ENV = "http://schemas.xmlsoap.org/soap/envelope/";
    public static final String SOAP_ENV_PREFIX = "soap";

    public static final String XMLA = "urn:schemas-microsoft-com:xml-analysis";
    /** The SOAPAction header values the specification names for the two verbs. */
    public static final String SOAP_ACTION_DISCOVER = XMLA + ":Discover";
    public static final String SOAP_ACTION_EXECUTE = XMLA + ":Execute";
    public static final String ROWSET = "urn:schemas-microsoft-com:xml-analysis:rowset";
    public static final String MDDATASET = "urn:schemas-microsoft-com:xml-analysis:mddataset";
    public static final String EMPTY = "urn:schemas-microsoft-com:xml-analysis:empty";
    public static final String MULTIPLE_RESULTS = "http://schemas.microsoft.com/analysisservices/2003/xmla-multipleresults";
    public static final String EXCEPTION = "urn:schemas-microsoft-com:xml-analysis:exception";
    public static final String EXCEPTION_PREFIX = "EX";

    public static final String ENGINE = "http://schemas.microsoft.com/analysisservices/2003/engine";
    public static final String MSXMLA = "http://schemas.microsoft.com/analysisservices/2003/xmla";
    public static final String EXT = "http://schemas.microsoft.com/analysisservices/2003/ext";

    public static final String XSD = "http://www.w3.org/2001/XMLSchema";
    public static final String XSD_PREFIX = "xsd";
    public static final String XSI = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String XSI_PREFIX = "xsi";

    /**
     * The namespace of the {@code sql:field} attribute that accompanies every
     * column declaration in an inline schema. It never carries an element, only
     * that one attribute, which is why no Ecore models it.
     */
    public static final String SQL = "urn:schemas-microsoft-com:xml-sql";
    public static final String SQL_PREFIX = "sql";

    private XmlaNamespaces() {
        // constants only
    }
}
