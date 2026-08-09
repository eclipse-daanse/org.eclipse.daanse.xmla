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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

/**
 * Reads one element subtree into an {@link EObject}, driven by
 * {@code ExtendedMetaData}.
 * <p>
 * An element the model does not know is handled by an explicit {@link Unknown}
 * policy, and a value that will not parse throws with the element name and the
 * position in the document.
 */
public final class EcoreXmlReader {

    /** What to do with an element the target EClass has no feature for. */
    public enum Unknown {
        /**
         * Fail. The right default for a request: a typo should not be silently ignored.
         */
        FAIL,
        /**
         * Skip the subtree. Used for the inline {@code <xsd:schema>} that precedes the
         * rows.
         */
        SKIP
    }

    static {
        // A generated EPackage registers itself when its class initialises, and until
        // it does the registry cannot be asked about it. msxmla contributes
        // NormTupleSet to mddataset's SetType group and nothing else here would touch
        // it.
        org.eclipse.daanse.xmla.model.msxmla.MsXmlaPackage.eINSTANCE.getName();
    }

    /** Feature lookup per EClass, built once from the model. */
    private static final Map<EClass, Map<String, EStructuralFeature>> ELEMENTS = new ConcurrentHashMap<>();
    private static final Map<EClass, Map<String, EStructuralFeature>> ATTRIBUTES = new ConcurrentHashMap<>();

    private final Unknown unknown;

    public EcoreXmlReader(Unknown unknown) {
        this.unknown = unknown;
    }

    /**
     * Reads the element the reader is positioned on into a new instance of
     * {@code target}.
     * <p>
     * On return the reader sits on the matching END_ELEMENT.
     */
    public EObject read(XMLStreamReader in, EClass target) throws XMLStreamException {
        EObject object = target.getEPackage().getEFactoryInstance().create(target);
        readAttributes(in, target, object);
        // A type with simple content keeps the element's own text -
        // <FmtValue>$1,234.00</FmtValue>
        // and <Value xsi:type="xsd:int">42</Value> both hold their value that way.
        EStructuralFeature simple = simpleContentOf(target);
        StringBuilder text = simple == null ? null : new StringBuilder();

        int depth = 0;
        while (in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (depth == 0) {
                    readChild(in, target, object);
                } else {
                    depth++;
                }
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                if (depth == 0) {
                    if (text != null && !text.isEmpty()) {
                        set(object, simple, text.toString(), in);
                    }
                    return object;
                }
                depth--;
            } else if (text != null && depth == 0
                    && (event == XMLStreamConstants.CHARACTERS || event == XMLStreamConstants.CDATA)) {
                text.append(in.getText());
            }
        }
        return object;
    }

    /**
     * The feature holding the element's own text, or {@code null} if it has none.
     */
    private static EStructuralFeature simpleContentOf(EClass target) {
        for (EStructuralFeature feature : target.getEAllStructuralFeatures()) {
            if (ExtendedMetaData.INSTANCE.getFeatureKind(feature) == ExtendedMetaData.SIMPLE_FEATURE) {
                return feature;
            }
        }
        return null;
    }

    private void readAttributes(XMLStreamReader in, EClass target, EObject object) {
        Map<String, EStructuralFeature> byName = attributesOf(target);
        for (int i = 0; i < in.getAttributeCount(); i++) {
            EStructuralFeature feature = byName.get(in.getAttributeLocalName(i));
            if (feature != null) {
                set(object, feature, in.getAttributeValue(i), in);
            }
        }
    }

    private void readChild(XMLStreamReader in, EClass target, EObject object) throws XMLStreamException {
        String name = in.getLocalName();
        if (XmlaNamespaces.XSD.equals(in.getNamespaceURI())) {
            // The inline <xsd:schema> a response carries describes the payload; it is
            // metadata
            // written into the data, never a feature of anything. Skipping it by namespace
            // rather than by policy keeps FAIL meaning what it says for everything else.
            skipSubtree(in);
            return;
        }
        EStructuralFeature feature = elementsOf(target).get(name);
        if (feature == null) {
            // ASSL wraps its collections - <Annotations> around the <Annotation>s. The
            // wrapper
            // holds no value of its own, which is why the model flattens it to a list, but
            // it is
            // on the wire and has to be stepped through rather than treated as unknown.
            EStructuralFeature wrapped = wrappedBy(target, name);
            if (wrapped != null) {
                readWrapped(in, target, object);
                return;
            }
            // Two shapes the element name does not match a feature name, and both are real
            // content rather than something to skip: a polymorphic containment, where the
            // name
            // is the concrete subtype's, and a data-named one, where the name is a value.
            EReference polymorphic = polymorphicFor(target, name);
            if (polymorphic != null) {
                readInto(in, object, polymorphic, subtypeFor(polymorphic, name));
                return;
            }
            EReference named = dataNamedFor(target);
            if (named != null) {
                EObject child = read(in, (EClass) named.getEType());
                EStructuralFeature naming = child.eClass().getEStructuralFeature(nameSource(child.eClass()));
                child.eSet(naming, name);
                add(object, named, child);
                return;
            }
            if (unknown == Unknown.FAIL) {
                throw new XmlaCodecException("element <" + name + "> is not a feature of " + target.getName(), in);
            }
            skipSubtree(in);
            return;
        }
        if (feature instanceof EReference reference) {
            readInto(in, object, reference, (EClass) reference.getEType());
        } else if (isXmlDocument(feature)) {
            // An xmlDocument column carries a document, not text - DISCOVER_XML_METADATA
            // answers
            // with a whole <Server> definition inside one column. getElementText() throws
            // on
            // element content, so the subtree is captured as XML and kept as the column's
            // value.
            set(object, feature, XmlFragment.read(in), in);
        } else {
            set(object, feature, in.getElementText(), in);
        }
    }

    /**
     * The many-valued feature this element wraps, or {@code null} when it wraps
     * nothing.
     */
    private static EStructuralFeature wrappedBy(EClass target, String name) {
        for (EStructuralFeature feature : target.getEAllStructuralFeatures()) {
            if (feature.isMany() && name.equals(EcoreXmlWriter.wrapperNameOf(feature))) {
                return feature;
            }
        }
        return null;
    }

    /**
     * Reads the children of a wrapper as though they had been written without it.
     */
    private void readWrapped(XMLStreamReader in, EClass target, EObject object) throws XMLStreamException {
        int depth = 1;
        while (in.hasNext() && depth > 0) {
            int event = in.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                readChild(in, target, object);
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                depth--;
            }
        }
    }

    /**
     * Whether the feature's type is the model's {@code xmlDocument}, whatever its
     * name here.
     */
    static boolean isXmlDocument(EStructuralFeature feature) {
        return "XmlDocument".equals(feature.getEType().getName());
    }

    private void readInto(XMLStreamReader in, EObject object, EReference reference, EClass as)
            throws XMLStreamException {
        add(object, reference, read(in, as));
    }

    private static void add(EObject object, EStructuralFeature feature, EObject child) {
        if (feature.isMany()) {
            @SuppressWarnings("unchecked")
            var many = (java.util.List<EObject>) object.eGet(feature);
            many.add(child);
        } else {
            object.eSet(feature, child);
        }
    }

    /**
     * A containment whose declared type is abstract and has a subtype called
     * {@code name}.
     * <p>
     * An Axis holds a SetType; what arrives is {@code <Members>}, {@code <Tuples>},
     * {@code <CrossProduct>} or {@code <Union>}. The element name is the only thing
     * that says which - there is no xsi:type on the wire - so it is what the lookup
     * has to use.
     */
    private static EReference polymorphicFor(EClass target, String name) {
        for (EStructuralFeature feature : target.getEAllStructuralFeatures()) {
            if (feature instanceof EReference reference && reference.getEReferenceType().isAbstract()
                    && subtypeFor(reference, name) != null) {
                return reference;
            }
        }
        return null;
    }

    /**
     * The alternative of a group that is named {@code name}, from any package that
     * contributes one.
     * <p>
     * A group is not confined to the package that declares it. {@code SetType}
     * lives in mddataset and four of its five alternatives with it, but
     * {@code NormTupleSet} - what SSAS answers when a client asks for an optimised
     * response, as Excel does - is in the msxmla package.
     * <p>
     * The declared package is searched first because that is where an alternative
     * usually is; the registry is the fallback and is consulted once per name.
     */
    private static EClass subtypeFor(EReference reference, String name) {
        EClass declared = reference.getEReferenceType();
        EClass found = subtypeIn(declared.getEPackage(), declared, name);
        if (found != null) {
            return found;
        }
        for (Object registered : new ArrayList<>(EPackage.Registry.INSTANCE.values())) {
            if (registered instanceof EPackage ePackage && ePackage != declared.getEPackage()) {
                found = subtypeIn(ePackage, declared, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private static EClass subtypeIn(EPackage ePackage, EClass declared, String name) {
        for (EClassifier candidate : ePackage.getEClassifiers()) {
            if (candidate instanceof EClass eClass && !eClass.isAbstract() && declared.isSuperTypeOf(eClass)
                    && name.equals(groupElementNameOf(eClass))) {
                return eClass;
            }
        }
        return null;
    }

    /**
     * A containment whose target names itself from its own data - a CellInfoItem is
     * {@code <FORMATTED_VALUE>} because that is the property the client asked for.
     */
    private static EReference dataNamedFor(EClass target) {
        for (EStructuralFeature feature : target.getEAllStructuralFeatures()) {
            if (feature instanceof EReference reference && nameSource(reference.getEReferenceType()) != null) {
                return reference;
            }
        }
        return null;
    }

    /**
     * The name an alternative takes inside its group, which is not its type's name.
     */
    private static String groupElementNameOf(EClass eClass) {
        EAnnotation daanse = eClass.getEAnnotation(EcoreXmlWriter.DAANSE_XMLA);
        String group = daanse == null ? null : daanse.getDetails().get("groupElementName");
        return group != null && !group.isEmpty() ? group : ExtendedMetaData.INSTANCE.getName(eClass);
    }

    private static String nameSource(EClass eClass) {
        EAnnotation daanse = eClass.getEAnnotation(EcoreXmlWriter.DAANSE_XMLA);
        return daanse == null ? null : daanse.getDetails().get("elementNameFrom");
    }

    private static void skipSubtree(XMLStreamReader in) throws XMLStreamException {
        int depth = 1;
        while (depth > 0 && in.hasNext()) {
            int event = in.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                depth++;
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                depth--;
            }
        }
    }

    private static void set(EObject object, EStructuralFeature feature, String text, XMLStreamReader in) {
        Object value = parse(feature, text, in);
        if (feature.isMany()) {
            @SuppressWarnings("unchecked")
            var many = (java.util.List<Object>) object.eGet(feature);
            many.add(value);
        } else {
            object.eSet(feature, value);
        }
    }

    private static Object parse(EStructuralFeature feature, String text, XMLStreamReader in) {
        EClassifier type = feature.getEType();
        if (isBoolean(type)) {
            // xsd:boolean has four lexical forms: true, false, 1, 0. Clients write
            // soap:mustUnderstand="1", and Boolean.valueOf("1") is false - EMF's own
            // conversion would not fail here, it would invert the meaning.
            return switch (text.trim()) {
            case "true", "1" -> Boolean.TRUE;
            case "false", "0" -> Boolean.FALSE;
            default -> throw new XmlaCodecException(
                    "cannot read '" + text + "' as a boolean " + "for " + RowsetSchemaWriter.wireNameOf(feature), in);
            };
        }
        if ("DateTime".equals(type.getName())) {
            try {
                return LocalDateTime.parse(text);
            } catch (RuntimeException e) {
                throw new XmlaCodecException(
                        "cannot read '" + text + "' as a timestamp for " + RowsetSchemaWriter.wireNameOf(feature), in,
                        e);
            }
        }
        if (feature instanceof EAttribute && type instanceof EDataType dataType) {
            try {
                return dataType.getEPackage().getEFactoryInstance().createFromString(dataType, text);
            } catch (RuntimeException e) {
                throw new XmlaCodecException("cannot read '" + text + "' as " + type.getName() + " for "
                        + RowsetSchemaWriter.wireNameOf(feature), in, e);
            }
        }
        return text;
    }

    private static boolean isBoolean(EClassifier type) {
        String instanceClass = type.getInstanceClassName();
        return "boolean".equals(instanceClass) || "java.lang.Boolean".equals(instanceClass);
    }

    private static Map<String, EStructuralFeature> elementsOf(EClass eClass) {
        return ELEMENTS.computeIfAbsent(eClass, c -> index(c, false));
    }

    private static Map<String, EStructuralFeature> attributesOf(EClass eClass) {
        return ATTRIBUTES.computeIfAbsent(eClass, c -> index(c, true));
    }

    private static Map<String, EStructuralFeature> index(EClass eClass, boolean attributes) {
        Map<String, EStructuralFeature> byName = new HashMap<>();
        for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
            boolean isAttribute = ExtendedMetaData.INSTANCE
                    .getFeatureKind(feature) == ExtendedMetaData.ATTRIBUTE_FEATURE;
            if (isAttribute == attributes) {
                byName.put(RowsetSchemaWriter.wireNameOf(feature), feature);
            }
        }
        return byName;
    }
}
