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
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.model.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.daanse.xmla.model.mddataset.CellProperty;
import org.eclipse.daanse.xmla.model.mddataset.MdDatasetFactory;
import org.eclipse.daanse.xmla.model.mddataset.MemberType;
import org.junit.jupiter.api.Test;

/**
 * A typed member property on an axis names its type as {@code xsi:type}, as Analysis Services writes MEMBER_VALUE of
 * a date: {@code <MEMBER_VALUE xsi:type="xsd:dateTime">2024-03-01T00:00:00</MEMBER_VALUE>}. Written as a plain
 * {@code type} attribute, a client read the value as text.
 */
class AMemberPropertyNamesItsTypeAsXsiTypeTest {

    @Test
    void theTypeOfAMemberValueIsAnXsiType() throws Exception {
        MdDatasetFactory factory = MdDatasetFactory.eINSTANCE;
        MemberType member = factory.createMemberType();
        member.setHierarchy("[Date].[Date]");
        CellProperty value = factory.createCellProperty();
        value.setTagName("MEMBER_VALUE");
        value.setValue("2024-03-01T00:00:00");
        value.setType("xsd:dateTime");
        member.getAny().add(value);

        StringWriter text = new StringWriter();
        XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(text);
        out.setDefaultNamespace(XmlaNamespaces.MDDATASET);
        out.writeStartElement(XmlaNamespaces.MDDATASET, "root");
        out.writeDefaultNamespace(XmlaNamespaces.MDDATASET);
        out.writeNamespace(XmlaNamespaces.XSI_PREFIX, XmlaNamespaces.XSI);
        new EcoreXmlWriter(XmlaNamespaces.MDDATASET).write(out, member, "Member");
        out.writeEndElement();
        out.close();

        assertThat(text.toString()).contains("<MEMBER_VALUE xsi:type=\"xsd:dateTime\">2024-03-01T00:00:00</MEMBER_VALUE>");
    }
}
