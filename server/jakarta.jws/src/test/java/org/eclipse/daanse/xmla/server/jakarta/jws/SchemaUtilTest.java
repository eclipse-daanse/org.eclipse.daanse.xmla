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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import java.util.List;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DiscoverResponse;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Return;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Rowset;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.discover.DiscoverPropertiesResponseRowXml;
import org.eclipse.daanse.xmla.server.jakarta.jws.SchemaUtil;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xsd.Schema;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

class SchemaUtilTest {
    @Test
    void testSchemaGen() throws Exception {
        DiscoverResponse response = new DiscoverResponse();
        Return ret = new Return();
        response.setReturn(ret);
        Rowset rowset = new Rowset();
        DiscoverPropertiesResponseRowXml props = new DiscoverPropertiesResponseRowXml();
        props.setPropertyName("My Name");
        rowset.setRow(List.of(props));
        ret.setValue(rowset);
        System.out.println("========[ GEN SCHEMA ]=============");
        Schema generateSchema = SchemaUtil.generateSchema("urn:schemas-microsoft-com:xml-analysis:rowset",
                DiscoverPropertiesResponseRowXml.class);
        rowset.setSchema(generateSchema);
        System.out.println("=========[ DiscoverResponse ]============");
        JAXBContext context = JAXBContext.newInstance(DiscoverResponse.class, Return.class, Rowset.class,
                DiscoverPropertiesResponseRowXml.class, Schema.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(response, System.out);
    }

}
