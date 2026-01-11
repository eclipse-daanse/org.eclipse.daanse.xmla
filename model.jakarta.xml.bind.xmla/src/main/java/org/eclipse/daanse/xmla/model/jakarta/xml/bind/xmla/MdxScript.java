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
@XmlType(name = "MdxScript", propOrder = {

})
public class MdxScript extends AbstractItem {

    @XmlElement(name = "Command")
    @XmlElementWrapper(name = "Commands")
    protected List<Command> commands;
    @XmlElement(name = "DefaultScript")
    protected Boolean defaultScript;
    @XmlElement(name = "CalculationProperty")
    @XmlElementWrapper(name = "CalculationProperties")
    protected List<CalculationProperty> calculationProperties;

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> value) {
        this.commands = value;
    }

    public Boolean isDefaultScript() {
        return defaultScript;
    }

    public void setDefaultScript(Boolean value) {
        this.defaultScript = value;
    }

    public List<CalculationProperty> getCalculationProperties() {
        return calculationProperties;
    }

    public void setCalculationProperties(List<CalculationProperty> value) {
        this.calculationProperties = value;
    }

}
