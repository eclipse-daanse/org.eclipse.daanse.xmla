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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.mdschema;

import java.io.Serializable;
import java.util.List;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.DirectQueryPushableEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.InterfaceNameEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.OriginEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.ParameterInfoXml;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_rowset.Row;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * The MDSCHEMA_FUNCTIONS schema rowset returns information about the functions that are currently
 * available for use in the DAX and MDX languages.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdSchemaFunctionsResponseRowXml")
public class MdSchemaFunctionsResponseRowXml extends Row implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 6574249705189275613L;

    /**
     * The name of the function.
     */
    @XmlElement(name = "FUNCTION_NAME", required = false)
    private String functionalName;

    /**
     * A description of the function.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * A description the parameters accepted by the function.
     */
    @XmlElement(name = "PARAMETER_LIST", required = true)
    private String parameterList;

    /**
     * The OLE DB data type that is returned by the function.
     */
    @XmlElement(name = "RETURN_TYPE", required = false)
    private Integer returnType;

    /**
     * The possible values are as follows: (0x1) MSOLAP (0x2) UDF (0x3) RELATIONAL (0x4) SCALAR
     */
    @XmlElement(name = "ORIGIN", required = false)
    private OriginEnum origin;

    /**
     * A logical classification of the type of function. For example: DATETIME LOGICAL FILTER
     */
    @XmlElement(name = "INTERFACE_NAME", required = false)
    private InterfaceNameEnum interfaceName;

    /**
     * The library that implements the function.
     */
    @XmlElement(name = "LIBRARY_NAME", required = false)
    private String libraryName;

    /**
     * @deprecated Unused
     */
    @XmlElement(name = "DLL_NAME", required = false)
    @Deprecated(since = "deprecated in specification")
    private String dllName;

    /**
     * @deprecated Unused
     */
    @XmlElement(name = "HELP_FILE", required = false)
    @Deprecated(since = "deprecated in specification")
    private String helpFile;

    /**
     * @deprecated Unused
     */
    @XmlElement(name = "HELP_CONTEXT", required = false)
    @Deprecated(since = "deprecated in specification")
    private String helpContext;

    /**
     * The type of object on which this function can be called. For example, the Children MDX function
     * can be called on a Member object.
     */
    @XmlElement(name = "OBJECT", required = false)
    private String object;

    /**
     * The caption of the function.
     */
    @XmlElement(name = "CAPTION", required = false)
    private String caption;

    /**
     * The parameters that can be provided to this function.
     */
    @XmlElement(name = "PARAMETERINFO")
    private List<ParameterInfoXml> parameterInfo;

    /**
     * A bitmask that indicates the scenarios in which this function can be used when the model is in
     * DirectQuery mode. The possible values are as follows: (0x1) MEASURE: This function can be used in
     * measure expressions. (0x2) CALCCOL: This function can be used in calculated column expressions.
     */
    @XmlElement(name = "DIRECTQUERY_PUSHABLE", required = false)
    private DirectQueryPushableEnum directQueryPushable;

    public String getFunctionalName() {
        return functionalName;
    }

    public void setFunctionalName(String functionalName) {
        this.functionalName = functionalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParameterList() {
        return parameterList;
    }

    public void setParameterList(String parameterList) {
        this.parameterList = parameterList;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public OriginEnum getOrigin() {
        return origin;
    }

    public void setOrigin(OriginEnum origin) {
        this.origin = origin;
    }

    public InterfaceNameEnum getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(InterfaceNameEnum interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated(since = "deprecated in specification")
    public String getDllName() {
        return dllName;
    }

    /**
     * @deprecated
     * @param dllName
     */
    @Deprecated(since = "deprecated in specification")
    public void setDllName(String dllName) {
        this.dllName = dllName;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated(since = "deprecated in specification")
    public String getHelpFile() {
        return helpFile;
    }

    /**
     * @deprecated
     * @param helpFile
     */
    @Deprecated(since = "deprecated in specification")
    public void setHelpFile(String helpFile) {
        this.helpFile = helpFile;
    }

    /**
     * @deprecated
     * @return
     */
    @Deprecated(since = "deprecated in specification")
    public String getHelpContext() {
        return helpContext;
    }

    /**
     * @deprecated
     * @param helpContext
     */
    @Deprecated(since = "deprecated in specification")
    public void setHelpContext(String helpContext) {
        this.helpContext = helpContext;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<ParameterInfoXml> getParameterInfo() {
        return parameterInfo;
    }

    public void setParameterInfo(List<ParameterInfoXml> parameterInfo) {
        this.parameterInfo = parameterInfo;
    }

    public DirectQueryPushableEnum getDirectQueryPushable() {
        return directQueryPushable;
    }

    public void setDirectQueryPushable(DirectQueryPushableEnum directQueryPushable) {
        this.directQueryPushable = directQueryPushable;
    }
}
