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

import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.daanse.xmla.model.rowset.core.RowsetCorePackage;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

/**
 * Writes the inline {@code <xsd:schema>} that precedes the rows of a Discover
 * response, from the EClass that models the row.
 * <p>
 * This is metadata-as-data: the schema describes the shape of the rows that
 * follow, and a client uses it to type the values. It is derived from the
 * model, so the declaration and the rows stay in step.
 * <p>
 * The type of a column is resolved in a fixed order, and <strong>there is no
 * fallback</strong>: a feature whose type cannot be determined fails loudly,
 * because a default of {@code xsd:string} would let an untyped model produce a
 * plausible-looking schema.
 */
public final class RowsetSchemaWriter {

    /** The uuid pattern, taken from the model rather than repeated here. */
    private static final String UUID_TYPE = "uuid";
    private static final String XML_DOCUMENT_TYPE = "xmlDocument";

    /** EMF's XMLType datatype names, to the XSD type the wire uses for each. */
    private static final Map<String, String> XMLTYPE_TO_XSD = Map.ofEntries(Map.entry("String", "xsd:string"),
            Map.entry("BooleanObject", "xsd:boolean"), Map.entry("Boolean", "xsd:boolean"),
            Map.entry("ByteObject", "xsd:byte"), Map.entry("ShortObject", "xsd:short"),
            Map.entry("IntObject", "xsd:int"), Map.entry("Int", "xsd:int"), Map.entry("Integer", "xsd:integer"),
            Map.entry("LongObject", "xsd:long"), Map.entry("Long", "xsd:long"),
            Map.entry("UnsignedByteObject", "xsd:unsignedByte"), Map.entry("UnsignedShortObject", "xsd:unsignedShort"),
            Map.entry("UnsignedIntObject", "xsd:unsignedInt"), Map.entry("UnsignedInt", "xsd:unsignedInt"),
            Map.entry("UnsignedLong", "xsd:unsignedLong"), Map.entry("FloatObject", "xsd:float"),
            Map.entry("DoubleObject", "xsd:double"), Map.entry("Decimal", "xsd:decimal"),
            Map.entry("Base64Binary", "xsd:base64Binary"), Map.entry("DateTime", "xsd:dateTime"),
            Map.entry("Date", "xsd:date"), Map.entry("Time", "xsd:time"), Map.entry("AnyType", "xsd:anyType"),
            Map.entry("AnyURI", "xsd:anyURI"));

    private RowsetSchemaWriter() {
        // static access only
    }

    /**
     * The XSD type for one column.
     *
     * @throws IllegalStateException if the model does not determine it — better a
     *                               build failure than a column silently typed as
     *                               text
     */
    public static String xsdTypeOf(EStructuralFeature feature) {
        String override = detail(feature.getEAnnotation(RowsetCatalog.ANNOTATION), "xsdType");
        if (override != null) {
            return override;
        }
        if (feature instanceof EReference) {
            return null; // a nested rowset: the schema inlines its complexType instead
        }
        EClassifier type = feature.getEType();
        if (type instanceof EEnum) {
            String enumType = detail(type.getEAnnotation(RowsetCatalog.ANNOTATION), "xsdType");
            return enumType != null ? enumType : "xsd:string";
        }
        String name = type.getName();
        String mapped = XMLTYPE_TO_XSD.get(name);
        if (mapped != null) {
            return mapped;
        }
        // A datatype local to the rowset model: its ExtendedMetaData name is the wire
        // type, and it lives in the rowset namespace rather than the XSD one, so it
        // has no prefix.
        String local = ExtendedMetaData.INSTANCE.getName(type);
        if (local != null && !local.isEmpty()) {
            return local;
        }
        throw new IllegalStateException("no XSD type for feature " + feature.getEContainingClass().getName() + "."
                + feature.getName() + " of type " + name + "; add an xsdType detail or map the datatype");
    }

    /** The element name this feature takes on the wire. */
    public static String wireNameOf(EStructuralFeature feature) {
        String name = ExtendedMetaData.INSTANCE.getName(feature);
        return name == null || name.isEmpty() ? feature.getName() : name;
    }

    /**
     * Writes the schema for {@code rowEClass}: the {@code root} element
     * declaration, the {@code uuid} and {@code xmlDocument} helper types, and the
     * {@code row} complexType.
     * <p>
     * Both helper types are always declared, whether or not a column uses them —
     * that is what a live Analysis Services instance does, and a client that
     * pre-parses the schema can rely on their presence.
     */

    public static void write(XMLStreamWriter out, EClass rowEClass) throws XMLStreamException {
        out.writeStartElement(XmlaNamespaces.XSD_PREFIX, "schema", XmlaNamespaces.XSD);
        out.writeNamespace(XmlaNamespaces.XSD_PREFIX, XmlaNamespaces.XSD);
        out.writeNamespace(XmlaNamespaces.SQL_PREFIX, XmlaNamespaces.SQL);
        out.writeDefaultNamespace(XmlaNamespaces.ROWSET);
        out.writeAttribute("targetNamespace", XmlaNamespaces.ROWSET);
        out.writeAttribute("elementFormDefault", "qualified");

        writeRootDeclaration(out);
        writeUuidType(out);
        writeXmlDocumentType(out);
        writeRowType(out, rowEClass);

        out.writeEndElement();
    }

    private static void writeRootDeclaration(XMLStreamWriter out) throws XMLStreamException {
        xsd(out, "element");
        out.writeAttribute("name", "root");
        xsd(out, "complexType");
        xsd(out, "sequence");
        out.writeAttribute("minOccurs", "0");
        out.writeAttribute("maxOccurs", "unbounded");
        xsd(out, "element");
        out.writeAttribute("name", "row");
        out.writeAttribute("type", "row");
        out.writeEndElement(); // element row
        out.writeEndElement(); // sequence
        out.writeEndElement(); // complexType
        out.writeEndElement(); // element root
    }

    private static void writeUuidType(XMLStreamWriter out) throws XMLStreamException {
        xsd(out, "simpleType");
        out.writeAttribute("name", UUID_TYPE);
        xsd(out, "restriction");
        out.writeAttribute("base", "xsd:string");
        xsd(out, "pattern");
        out.writeAttribute("value", uuidPattern());
        out.writeEndElement();
        out.writeEndElement();
        out.writeEndElement();
    }

    /**
     * The pattern as the model states it, so it is defined in exactly one place.
     */
    private static String uuidPattern() {
        EClassifier uuid = RowsetCorePackage.eINSTANCE.getEClassifier("Uuid");
        String pattern = uuid == null ? null : detail(uuid.getEAnnotation(ExtendedMetaData.ANNOTATION_URI), "pattern");
        if (pattern == null) {
            throw new IllegalStateException("the rowset model no longer defines the Uuid pattern; the schema writer "
                    + "reads it from there rather than repeating it");
        }
        return pattern;
    }

    private static void writeXmlDocumentType(XMLStreamWriter out) throws XMLStreamException {
        xsd(out, "complexType");
        out.writeAttribute("name", XML_DOCUMENT_TYPE);
        xsd(out, "sequence");
        xsd(out, "any");
        out.writeEndElement();
        out.writeEndElement();
        out.writeEndElement();
    }

    private static void writeRowType(XMLStreamWriter out, EClass rowEClass) throws XMLStreamException {
        xsd(out, "complexType");
        out.writeAttribute("name", "row");
        xsd(out, "sequence");
        for (EStructuralFeature feature : rowEClass.getEAllStructuralFeatures()) {
            writeColumn(out, feature);
        }
        out.writeEndElement();
        out.writeEndElement();
    }

    /** A dynamic column, as a tabular Execute result names them. */
    public record Column(String field, String name, String xsdType) {
    }

    /**
     * The same schema for a dynamic column list — what a DMV projection, a
     * drill-through or a {@code Format=Tabular} answer declares, where no generated
     * row class exists. Every column is optional: an absent element is how the
     * rowset says NULL.
     */
    public static void write(XMLStreamWriter out, java.util.List<Column> columns) throws XMLStreamException {
        out.writeStartElement(XmlaNamespaces.XSD_PREFIX, "schema", XmlaNamespaces.XSD);
        out.writeNamespace(XmlaNamespaces.XSD_PREFIX, XmlaNamespaces.XSD);
        out.writeNamespace(XmlaNamespaces.SQL_PREFIX, XmlaNamespaces.SQL);
        out.writeDefaultNamespace(XmlaNamespaces.ROWSET);
        out.writeAttribute("targetNamespace", XmlaNamespaces.ROWSET);
        out.writeAttribute("elementFormDefault", "qualified");

        writeRootDeclaration(out);
        writeUuidType(out);
        writeXmlDocumentType(out);

        xsd(out, "complexType");
        out.writeAttribute("name", "row");
        xsd(out, "sequence");
        for (Column column : columns) {
            xsd(out, "element");
            out.writeAttribute(XmlaNamespaces.SQL_PREFIX, XmlaNamespaces.SQL, "field", column.field());
            out.writeAttribute("name", column.name());
            if (column.xsdType() != null) {
                out.writeAttribute("type", column.xsdType());
            }
            out.writeAttribute("minOccurs", "0");
            out.writeEndElement();
        }
        out.writeEndElement();
        out.writeEndElement();

        out.writeEndElement();
    }

    private static void writeColumn(XMLStreamWriter out, EStructuralFeature feature) throws XMLStreamException {
        String name = wireNameOf(feature);
        String type = xsdTypeOf(feature);

        xsd(out, "element");
        out.writeAttribute(XmlaNamespaces.SQL_PREFIX, XmlaNamespaces.SQL, "field", name);
        out.writeAttribute("name", name);
        if (type != null) {
            out.writeAttribute("type", type);
        }
        if (feature.getLowerBound() == 0) {
            out.writeAttribute("minOccurs", "0");
        }
        if (feature.isMany()) {
            out.writeAttribute("maxOccurs", "unbounded");
        }
        if (type == null && !isVariantColumn(feature)) {
            // A nested rowset: inline its complexType rather than naming a type.
            xsd(out, "complexType");
            xsd(out, "sequence");
            for (EStructuralFeature child : ((EClass) feature.getEType()).getEAllStructuralFeatures()) {
                writeColumn(out, child);
            }
            out.writeEndElement();
            out.writeEndElement();
        }
        out.writeEndElement();
    }

    /**
     * Whether a column's type varies from row to row, so the schema must not name
     * one.
     * <p>
     * An element declared with no {@code type} is {@code xsd:anyType}, and that is
     * how SSAS declares {@code ATTRIBUTE_VALUE} — the one such column in every
     * rowset the model describes. Each value then carries its own {@code xsi:type}.
     * Naming a type here would make the advertised schema contradict the values
     * written under it.
     */
    public static boolean isVariantColumn(EStructuralFeature feature) {
        return feature instanceof EReference reference && ExtendedMetaData.INSTANCE
                .getContentKind((EClass) reference.getEType()) == ExtendedMetaData.SIMPLE_CONTENT;
    }

    private static void xsd(XMLStreamWriter out, String localName) throws XMLStreamException {
        out.writeStartElement(XmlaNamespaces.XSD_PREFIX, localName, XmlaNamespaces.XSD);
    }

    private static String detail(EAnnotation annotation, String key) {
        return annotation == null ? null : annotation.getDetails().get(key);
    }
}
