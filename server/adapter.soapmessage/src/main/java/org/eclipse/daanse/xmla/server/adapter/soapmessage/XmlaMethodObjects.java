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

import javax.xml.namespace.QName;

import jakarta.xml.soap.SOAPElement;

public enum XmlaMethodObjects {
    DISCOVER(new QName("urn:schemas-microsoft-com:xml-analysis", "Discover"));

    private QName qName;

    private XmlaMethodObjects(QName qName) {

        this.qName = qName;
    }

    boolean matches(SOAPElement element) {
        return qName.equals(element.getElementQName());

    }

}
