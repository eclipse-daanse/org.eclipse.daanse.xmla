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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Attach", propOrder = {

})
public class Attach {

    @XmlElement(name = "Folder", required = true)
    protected String folder;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "AllowOverwrite")
    protected Boolean allowOverwrite;
    @XmlElement(name = "ReadWriteMode", namespace = "http://schemas.microsoft.com/analysisservices/2008/engine/100")
    protected String readWriteMode;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String value) {
        this.folder = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public Boolean isAllowOverwrite() {
        return allowOverwrite;
    }

    public void setAllowOverwrite(Boolean value) {
        this.allowOverwrite = value;
    }

    public String getReadWriteMode() {
        return readWriteMode;
    }

    public void setReadWriteMode(String value) {
        this.readWriteMode = value;
    }

}
