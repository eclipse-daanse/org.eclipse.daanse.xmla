/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.daanse.xmla.server.adapter.soapmessage.execute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.TabularBinding;
import org.eclipse.daanse.xmla.model.record.xmla.AttributeBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DSVTableBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.DimensionBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.MeasureBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.QueryBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.RowBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.TableBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.TimeBindingR;
import org.eclipse.daanse.xmla.server.adapter.soapmessage.execute.converter.BindingConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class BindingConverterTest {

    private DocumentBuilder documentBuilder;

    @BeforeEach
    void setUp() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();
    }

    private Document parseXml(String xml) throws Exception {
        return documentBuilder.parse(new InputSource(new StringReader(xml)));
    }

    @Nested
    class RowBindingTests {

        @Test
        void getRowBinding_withTableID() throws Exception {
            String xml = "<RowBinding><TableID>table1</TableID></RowBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Binding binding = BindingConverter.getRowBinding(nl);

            assertNotNull(binding);
            assertInstanceOf(RowBindingR.class, binding);
            assertEquals("table1", ((RowBindingR) binding).tableID());
        }
    }

    @Nested
    class MeasureBindingTests {

        @Test
        void getMeasureBinding_withMeasureID() throws Exception {
            String xml = "<MeasureBinding><MeasureID>measure1</MeasureID></MeasureBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Binding binding = BindingConverter.getMeasureBinding(nl);

            assertNotNull(binding);
            assertInstanceOf(MeasureBindingR.class, binding);
            assertEquals("measure1", ((MeasureBindingR) binding).measureID());
        }
    }

    @Nested
    class AttributeBindingTests {

        @Test
        void getAttributeBinding_withAttributeID() throws Exception {
            String xml = "<AttributeBinding><AttributeID>attr1</AttributeID></AttributeBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Binding binding = BindingConverter.getAttributeBinding(nl);

            assertNotNull(binding);
            assertInstanceOf(AttributeBindingR.class, binding);
            assertEquals("attr1", ((AttributeBindingR) binding).attributeID());
        }
    }

    @Nested
    class DimensionBindingTests {

        @Test
        void getDimensionBinding_withDataSourceIDAndDimensionID() throws Exception {
            String xml = "<DimensionBinding><DataSourceID>ds1</DataSourceID><DimensionID>dim1</DimensionID></DimensionBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Binding binding = BindingConverter.getDimensionBinding(nl);

            assertNotNull(binding);
            assertInstanceOf(DimensionBindingR.class, binding);
            DimensionBindingR dimBinding = (DimensionBindingR) binding;
            assertEquals("ds1", dimBinding.dataSourceID());
            assertEquals("dim1", dimBinding.dimensionID());
        }
    }

    @Nested
    class TimeBindingTests {

        @Test
        void getTimeBinding_withCalendarDates() throws Exception {
            String xml = "<TimeBinding><CalendarStartDate>2024-01-01T00:00:00Z</CalendarStartDate></TimeBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            Binding binding = BindingConverter.getTimeBinding(nl);

            assertNotNull(binding);
            assertInstanceOf(TimeBindingR.class, binding);
        }
    }

    @Nested
    class TabularBindingTests {

        @Test
        void getTableBinding_withDataSourceIDAndTableName() throws Exception {
            String xml = "<TableBinding><DataSourceID>ds1</DataSourceID><DbTableName>MyTable</DbTableName></TableBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            TabularBinding binding = BindingConverter.getTableBinding(nl);

            assertNotNull(binding);
            assertInstanceOf(TableBindingR.class, binding);
            TableBindingR tableBinding = (TableBindingR) binding;
            assertEquals("MyTable", tableBinding.dbTableName());
        }

        @Test
        void getQueryBinding_withQueryDefinition() throws Exception {
            String xml = "<QueryBinding><QueryDefinition>SELECT * FROM test</QueryDefinition></QueryBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            TabularBinding binding = BindingConverter.getQueryBinding(nl);

            assertNotNull(binding);
            assertInstanceOf(QueryBindingR.class, binding);
            assertEquals("SELECT * FROM test", ((QueryBindingR) binding).queryDefinition());
        }

        @Test
        void getDSVTableBinding_withTableID() throws Exception {
            String xml = "<DSVTableBinding><TableID>dsvTable1</TableID></DSVTableBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            TabularBinding binding = BindingConverter.getDSVTableBinding(nl);

            assertNotNull(binding);
            assertInstanceOf(DSVTableBindingR.class, binding);
            assertEquals("dsvTable1", ((DSVTableBindingR) binding).tableID());
        }

        @Test
        void getTabularBinding_dispatchesCorrectly() throws Exception {
            String xml = "<TableBinding><DbTableName>Test</DbTableName></TableBinding>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            TabularBinding binding = BindingConverter.getTabularBinding(nl, "TableBinding");

            assertInstanceOf(TableBindingR.class, binding);
        }
    }

    @Nested
    class SimpleBindingTests {

        @Test
        void getRowNumberBinding_returnsInstance() {
            Binding binding = BindingConverter.getRowNumberBinding();
            assertNotNull(binding);
        }

        @Test
        void getInheritedBinding_returnsInstance() {
            Binding binding = BindingConverter.getInheritedBinding();
            assertNotNull(binding);
        }

        @Test
        void getTimeAttributeBinding_returnsInstance() {
            Binding binding = BindingConverter.getTimeAttributeBinding();
            assertNotNull(binding);
        }
    }

    @Nested
    class GroupTests {

        @Test
        void getGroup_withNameAndMembers() throws Exception {
            String xml = "<Group><Name>Group1</Name><Members><Member>m1</Member><Member>m2</Member></Members></Group>";
            Document doc = parseXml(xml);
            NodeList nl = doc.getDocumentElement().getChildNodes();

            var group = BindingConverter.getGroup(nl);

            assertNotNull(group);
            assertEquals("Group1", group.name());
            assertTrue(group.members().isPresent());
            assertEquals(2, group.members().get().size());
        }
    }
}
