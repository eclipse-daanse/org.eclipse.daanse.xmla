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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

/**
 * Writes an {@link EObject} as XML, driven entirely by its
 * {@code ExtendedMetaData}.
 * <p>
 * Two behaviours of the XMLA wire format fall out of this rather than being
 * special-cased:
 * <ul>
 * <li>a feature that is not set produces <em>no element at all</em>, which is
 * how a NULL column is written;</li>
 * <li>a feature set to the empty string produces an empty element,
 * {@code <DESCRIPTION/>}, which clients distinguish from NULL.</li>
 * </ul>
 */
public final class EcoreXmlWriter {

    /**
     * The XSD types a client reads as text, and the only ones an empty element may
     * stand in. {@code uuid} is deliberately absent although it is a string
     * restriction: an empty one is still not a GUID.
     */
    private static final java.util.Set<String> TEXT_XSD_TYPES = java.util.Set.of("xsd:string", "xsd:anyType",
            "xsd:anyURI");

    private final String namespace;

    /**
     * @param namespace the namespace the object's elements belong to, used where
     *                  the model says {@code ##targetNamespace}
     */
    public EcoreXmlWriter(String namespace) {
        this.namespace = namespace;
    }

    /** Writes {@code object} as an element named {@code elementName}. */
    public void write(XMLStreamWriter out, EObject object, String elementName) throws XMLStreamException {
        boolean declare = out.getPrefix(namespace) == null;
        if (declare) {
            // Nothing has bound this namespace yet — usually because this element is the
            // outermost one the caller writes. Make it the default, as a live server does.
            out.setDefaultNamespace(namespace);
        }
        out.writeStartElement(namespace, elementName);
        if (declare) {
            out.writeDefaultNamespace(namespace);
        }
        writeAttributes(out, object);
        writeSimpleContent(out, object);
        writeElements(out, object);
        out.writeEndElement();
    }

    /**
     * Writes only the children, for a caller that has already opened the element.
     */
    public void writeContent(XMLStreamWriter out, EObject object) throws XMLStreamException {
        writeAttributes(out, object);
        writeSimpleContent(out, object);
        writeElements(out, object);
    }

    private void writeAttributes(XMLStreamWriter out, EObject object) throws XMLStreamException {
        for (EStructuralFeature feature : object.eClass().getEAllStructuralFeatures()) {
            if (!isAttribute(feature) || !object.eIsSet(feature) || isElementName(feature)) {
                continue;
            }
            String value = asString(feature, object.eGet(feature));
            if (value == null) {
                continue;
            }
            // An attribute is unqualified unless the model says otherwise - that is the XSD
            // default, and it is right for the rowset columns. The exception is
            // soap:mustUnderstand, which SOAP 1.1 requires to be qualified; writing it bare
            // means the receiver does not see it at all.
            String attributeNamespace = qualifiedNamespaceOf(feature);
            if (attributeNamespace == null) {
                out.writeAttribute(RowsetSchemaWriter.wireNameOf(feature), value);
            } else {
                if (out.getPrefix(attributeNamespace) == null) {
                    out.writeNamespace("mu", attributeNamespace);
                }
                out.writeAttribute(attributeNamespace, RowsetSchemaWriter.wireNameOf(feature), value);
            }
        }
    }

    /**
     * The element's own text - {@code <Value xsi:type="xsd:int">42</Value>}.
     * <p>
     * Written before any child element, so the two writers put mixed content in the
     * same order.
     */
    private void writeSimpleContent(XMLStreamWriter out, EObject object) throws XMLStreamException {
        for (EStructuralFeature feature : object.eClass().getEAllStructuralFeatures()) {
            if (object.eIsSet(feature) && isSimpleContent(feature)) {
                String text = asString(feature, object.eGet(feature));
                if (text != null) {
                    out.writeCharacters(text);
                }
            }
        }
    }

    private void writeElements(XMLStreamWriter out, EObject object) throws XMLStreamException {
        for (EStructuralFeature feature : object.eClass().getEAllStructuralFeatures()) {
            if (isAttribute(feature) || !object.eIsSet(feature)) {
                continue;
            }
            if (isSimpleContent(feature)) {
                continue; // written before the children, see writeSimpleContent
            }
            String name = RowsetSchemaWriter.wireNameOf(feature);
            Object value = object.eGet(feature);
            if (feature.isMany()) {
                // ASSL wraps its collections: <Annotations> holds the <Annotation>s. The
                // wrapper carries nothing itself, which is why the model flattens it to a
                // list, but it is on the wire.
                String wrapper = wrapperNameOf(feature);
                Iterable<?> elements = (Iterable<?>) value;
                if (wrapper != null && !((java.util.Collection<?>) value).isEmpty()) {
                    out.writeStartElement(namespace, wrapper);
                }
                for (Object element : elements) {
                    writeOne(out, feature, name, element);
                }
                if (wrapper != null && !((java.util.Collection<?>) value).isEmpty()) {
                    out.writeEndElement();
                }
            } else if (value != null) {
                writeOne(out, feature, name, value);
            }
        }
    }

    /**
     * The element a many-valued feature is wrapped in, or {@code null} for a bare
     * repetition.
     * <p>
     * Both forms are real. A rowset repeats {@code <ProviderType>} with nothing
     * around it; ASSL puts its {@code <Annotation>}s inside an
     * {@code <Annotations>}. The model says which.
     */
    static String wrapperNameOf(EStructuralFeature feature) {
        EAnnotation daanse = feature.getEAnnotation(DAANSE_XMLA);
        return daanse == null ? null : daanse.getDetails().get("wrapperElementName");
    }

    private void writeOne(XMLStreamWriter out, EStructuralFeature feature, String name, Object value)
            throws XMLStreamException {
        String elementNamespace = qualifiedNamespaceOf(feature);
        if (feature instanceof EReference && value instanceof EObject nested) {
            String childName = elementNameOf(feature, nested, name);
            if (elementNamespace == null) {
                write(out, nested, childName);
            } else {
                // Reached across a namespace boundary - a WarningColumn stays in the engine
                // namespace wherever it appears.
                new EcoreXmlWriter(elementNamespace).write(out, nested, childName);
            }
            return;
        }
        String text = asString(feature, value);
        if (text == null || text.isEmpty()) {
            if (!isTextColumn(feature)) {
                // An empty element is not an empty value to a client that has been told the
                // column is a number, a date or a uuid: it parses "" against that type, and
                // ADOMD stores the resulting FormatException as the cell's value instead of
                // throwing. Leaving the element out is what says NULL.
                return;
            }
            // <DESCRIPTION/>, not <DESCRIPTION></DESCRIPTION>. The two are the same XML
            // to a parser, but SSAS writes the short one and responses are compared byte
            // for byte.
            out.writeEmptyElement(namespace, name);
            return;
        }
        out.writeStartElement(namespace, name);
        if (EcoreXmlReader.isXmlDocument(feature)) {
            // The column holds a document, not a string. Escaping it would turn a <Server>
            // definition into the literal text "&lt;Server&gt;".
            XmlFragment.write(out, text);
        } else {
            out.writeCharacters(text);
        }
        out.writeEndElement();
    }

    /**
     * Whether an empty value may travel as an empty element - only where the client
     * reads the column as text.
     * <p>
     * For a number, a date or a {@code uuid} an empty element is not an empty value
     * but unparseable input: a client keeps the parse failure as the cell's value
     * rather than throwing, so the row arrives looking valid and holding an error.
     * Absence is what both sides read as NULL. An untyped column stays, being read
     * as text anyway.
     */
    private static boolean isTextColumn(EStructuralFeature feature) {
        String type = RowsetSchemaWriter.xsdTypeOrNull(feature);
        return type == null || TEXT_XSD_TYPES.contains(type);
    }

    /**
     * The element name a contained object takes, which is the feature's own name
     * except in two cases: the feature's type is abstract and the concrete subtype
     * decides (an Axis holds a SetType, and {@code <Members>}, {@code <Tuples>},
     * {@code <CrossProduct>} or {@code <Union>} goes on the wire), or the model
     * says {@code elementNameFrom} and the name is a value the object carries (a
     * CellInfoItem is {@code <FORMATTED_VALUE>}).
     */
    private static String elementNameOf(EStructuralFeature feature, EObject value, String declared) {
        EAnnotation daanse = value.eClass().getEAnnotation(DAANSE_XMLA);
        String from = daanse == null ? null : daanse.getDetails().get("elementNameFrom");
        if (from != null) {
            EStructuralFeature naming = value.eClass().getEStructuralFeature(from);
            Object name = naming == null ? null : value.eGet(naming);
            if (name != null && !name.toString().isEmpty()) {
                return name.toString();
            }
        }
        if (feature.getEType() != value.eClass()) {
            // Inside a group the alternative's element name is not its type's name: the
            // SetType choice calls a SetListType a <CrossProduct>.
            String group = daanse == null ? null : daanse.getDetails().get("groupElementName");
            if (group != null && !group.isEmpty()) {
                return group;
            }
            String own = ExtendedMetaData.INSTANCE.getName(value.eClass());
            if (own != null && !own.isEmpty()) {
                return own;
            }
        }
        return declared;
    }

    /** Where this project states what XSD and ExtendedMetaData have no way to. */
    static final String DAANSE_XMLA = "https://www.daanse.org/spec/xmla/1.0";

    /**
     * The namespace the model gives a feature explicitly, or {@code null} when it
     * gives none and {@code ##targetNamespace} — both of which mean "the namespace
     * of the element around it", which for an attribute is no namespace at all.
     */
    private static String qualifiedNamespaceOf(EStructuralFeature feature) {
        // Read off the annotation, not through ExtendedMetaData.getNamespace, which
        // falls back to the EPackage's nsURI when the model states nothing - and
        // "states nothing" is precisely the case that has to stay unqualified.
        EAnnotation annotation = feature.getEAnnotation(ExtendedMetaData.ANNOTATION_URI);
        String declared = annotation == null ? null : annotation.getDetails().get("namespace");
        if (declared == null || declared.isEmpty() || "##targetNamespace".equals(declared)) {
            return null;
        }
        return declared;
    }

    /**
     * Whether this feature <em>is</em> the element's name.
     * <p>
     * A CellProperty is written as {@code <FmtValue>} because its tagName says so;
     * writing that value again as an attribute would put {@code tagName="FmtValue"}
     * on the wire.
     */
    private static boolean isElementName(EStructuralFeature feature) {
        EAnnotation daanse = feature.getEContainingClass().getEAnnotation(DAANSE_XMLA);
        String from = daanse == null ? null : daanse.getDetails().get("elementNameFrom");
        return from != null && from.equals(feature.getName());
    }

    static boolean isSimpleContent(EStructuralFeature feature) {
        return ExtendedMetaData.INSTANCE.getFeatureKind(feature) == ExtendedMetaData.SIMPLE_FEATURE;
    }

    private static boolean isAttribute(EStructuralFeature feature) {
        return ExtendedMetaData.INSTANCE.getFeatureKind(feature) == ExtendedMetaData.ATTRIBUTE_FEATURE;
    }

    /** Converts a value to its lexical form, preferring EMF's own conversion. */
    private static String asString(EStructuralFeature feature, Object value) {
        EClassifier type = feature.getEType();
        if (feature instanceof EAttribute && type instanceof EDataType dataType) {
            return Lexical.of(dataType, value);
        }
        return Lexical.of(value);
    }
}
