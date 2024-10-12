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
package org.eclipse.daanse.xmla.server.tck;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import jakarta.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xmlunit.assertj3.XmlAssert;

import jakarta.xml.soap.SOAPMessage;

public class XMLUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLUtil.class);
    private XMLUtil() {
        //constructor
    }

    public static XmlAssert createAssert(SOAPMessage response) throws SOAPException, IOException, TransformerException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.writeTo(baos);
        String result = new String(baos.toByteArray());
        XmlAssert xmlAssert = XmlAssert.assertThat(result);
        LOGGER.debug(result);
        String prettyResult = XMLUtil.pretty(result);
        LOGGER.debug(prettyResult);
        HashMap<String, String> nsMap = new HashMap<>();
        nsMap.put("SOAP", "http://schemas.xmlsoap.org/soap/envelope/");
        nsMap.put("msxmla", "urn:schemas-microsoft-com:xml-analysis");
        nsMap.put("rowset", "urn:schemas-microsoft-com:xml-analysis:rowset");
        nsMap.put("mddataset", "urn:schemas-microsoft-com:xml-analysis:mddataset");
        nsMap.put("engine", "http://schemas.microsoft.com/analysisservices/2003/engine");
        nsMap.put("engine200", "http://schemas.microsoft.com/analysisservices/2010/engine/200");
        nsMap.put("empty", "urn:schemas-microsoft-com:xml-analysis:empty");
        nsMap.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");

        xmlAssert = xmlAssert.withNamespaceContext(nsMap);
        return xmlAssert;
    }

    public static String pretty(String xmlData) throws TransformerException {
        return pretty(xmlData, 2);
    }

    public static String pretty(String xmlData, int indent) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        transformerFactory.setAttribute("indent-number", indent);

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StringWriter stringWriter = new StringWriter();
        StreamResult xmlOutput = new StreamResult(stringWriter);

        Source xmlInput = new StreamSource(new StringReader(xmlData));
        transformer.transform(xmlInput, xmlOutput);

        return xmlOutput.getWriter()
            .toString();
    }

    public static Document stringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setNamespaceAware(true);

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
            fail(e);
        }
        return null;
    }
}
