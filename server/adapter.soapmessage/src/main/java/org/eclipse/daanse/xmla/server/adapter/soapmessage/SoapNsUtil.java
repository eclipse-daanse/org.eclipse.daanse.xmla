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
*/
package org.eclipse.daanse.xmla.server.adapter.soapmessage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;

public class SoapNsUtil {

    private static final String HTTP_SCHEMAS_XMLSOAP_ORG_SOAP_ENVELOPE = "http://schemas.xmlsoap.org/soap/envelope";

    private SoapNsUtil() {
        // constructor
    }

    public static Map<String, String> nsMap(SOAPElement soapElement) {
        boolean isEnvelop = soapElement instanceof SOAPEnvelope;
        Map<String, String> nsMap = new HashMap<>();
        Iterator<String> nsPrefixIterator = soapElement.getNamespacePrefixes();
        while (nsPrefixIterator.hasNext()) {
            String prefix = nsPrefixIterator.next();
            String nsUri = soapElement.getNamespaceURI(prefix);

            if (isEnvelop && !nsUri.startsWith(HTTP_SCHEMAS_XMLSOAP_ORG_SOAP_ENVELOPE)) {
                // filter SOAP-ENV ns
                nsMap.put(prefix, nsUri);
            }
        }

        return nsMap;
    }

}
