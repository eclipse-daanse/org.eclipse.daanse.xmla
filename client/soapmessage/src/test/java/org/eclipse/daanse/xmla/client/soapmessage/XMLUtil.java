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
package org.eclipse.daanse.xmla.client.soapmessage;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import org.xmlunit.assertj3.XmlAssert;

import jakarta.xml.soap.SOAPMessage;

public class XMLUtil {
    public static XmlAssert createAssert(SOAPMessage response) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.writeTo(baos);
        String result = new String(baos.toByteArray());
        XmlAssert xmlAssert = XmlAssert.assertThat(result);

        HashMap<String, String> nsMap = new HashMap<>();
        nsMap.put("SOAP", "http://schemas.xmlsoap.org/soap/envelope/");
        nsMap.put("msxmla", "urn:schemas-microsoft-com:xml-analysis");
        nsMap.put("rowset", "urn:schemas-microsoft-com:xml-analysis:rowset");
        xmlAssert = xmlAssert.withNamespaceContext(nsMap);
        return xmlAssert;
    }
}
