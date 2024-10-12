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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine.ImpersonationInfo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Assembly", propOrder = { "impersonationInfo" })
@XmlSeeAlso({ ComAssembly.class, ClrAssembly.class })
public abstract class Assembly extends AbstractItem {

    @XmlElement(name = "ImpersonationInfo")
    protected ImpersonationInfo impersonationInfo;

    public ImpersonationInfo getImpersonationInfo() {
        return impersonationInfo;
    }

    public void setImpersonationInfo(ImpersonationInfo value) {
        this.impersonationInfo = value;
    }

}
