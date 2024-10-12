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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageLoad", propOrder = {

})
public class ImageLoad extends AbstractTarget {

    @XmlElement(name = "ImagePath", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imagePath;
    @XmlElement(name = "ImageUrl", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageUrl;
    @XmlElement(name = "ImageUniqueID", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageUniqueID;
    @XmlElement(name = "ImageVersion", namespace = "http://schemas.microsoft.com/analysisservices/2010/engine/200/200")
    protected String imageVersion;
    @XmlElement(name = "ReadWriteMode", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100", required = true)
    protected String readWriteMode;
    @XmlElement(name = "DataBlock")
    @XmlElementWrapper(name = "Data")
    protected List<String> data;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String value) {
        this.imagePath = value;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String value) {
        this.imageUrl = value;
    }

    public String getImageUniqueID() {
        return imageUniqueID;
    }

    public void setImageUniqueID(String value) {
        this.imageUniqueID = value;
    }

    public String getImageVersion() {
        return imageVersion;
    }

    public void setImageVersion(String value) {
        this.imageVersion = value;
    }

    public String getReadWriteMode() {
        return readWriteMode;
    }

    public void setReadWriteMode(String value) {
        this.readWriteMode = value;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> value) {
        this.data = value;
    }

}
