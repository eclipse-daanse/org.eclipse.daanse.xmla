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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine300_300.RelationshipEndTranslation;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractTranslation", propOrder = {

})
@XmlSeeAlso({ AttributeTranslation.class, Translation.class, RelationshipEndTranslation.class })
public abstract class AbstractTranslation {

    @XmlElement(name = "Language")
    @XmlSchemaType(name = "unsignedInt")
    protected long language;
    @XmlElement(name = "Caption")
    protected String caption;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElementWrapper(name = "Annotations")
    @XmlElement(name = "Annotation", type = Annotation.class)
    protected List<Annotation> annotations;

    public long getLanguage() {
        return language;
    }

    public void setLanguage(long value) {
        this.language = value;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String value) {
        this.caption = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDisplayFolder() {
        return displayFolder;
    }

    public void setDisplayFolder(String value) {
        this.displayFolder = value;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> value) {
        this.annotations = value;
    }

}
