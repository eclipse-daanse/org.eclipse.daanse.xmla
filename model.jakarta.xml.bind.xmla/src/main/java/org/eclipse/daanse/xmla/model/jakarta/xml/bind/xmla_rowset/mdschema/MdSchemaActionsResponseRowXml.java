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

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.ActionTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.CoordinateTypeEnum;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.enums.InvocationEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This schema rowset returns information about literals supported by the server.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MdSchemaActionsResponseRowXml")
public class MdSchemaActionsResponseRowXml extends AbstractMdSchemaResponseRowXml implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 8909756522012364431L;

    /**
     * The name of this action.
     */
    @XmlElement(name = "ACTION_NAME", required = false)
    private String actionName;

    /**
     * A bitmask that is used to specify the action type. 0x01 - Action type is URL. 0x02 - Action type
     * is HTML. 0x04 - Action type is Statement. 0x08 - Action type is Dataset. 0x10 - Action type is
     * Rowset. 0x20 - Action type is Commandline. 0x40 - Action type is Proprietary. 0x80 - Action type
     * is Report. 0x100 - Action type is DrillThrough. If the action is PROPRIETARY (0x40), then a value
     * MUST be provided in the APPLICATION column.
     */
    @XmlElement(name = "ACTION_TYPE", required = false)
    private ActionTypeEnum actionType;

    /**
     * An MDX expression that specifies an object or a coordinate in the multidimensional space in which
     * the action is performed. The COORDINATE MUST resolve to the object specified in COORDINATE_TYPE.
     */
    @XmlElement(name = "COORDINATE", required = false)
    private String coordinate;

    /**
     * An enumeration that specifies how the COORDINATE restriction column is interpreted. The possible
     * values are as follows: 1 - Action coordinate refers to the cube. 2 - Action coordinate refers to
     * a dimension. 3 - Action coordinate refers to a level. 4 - Action coordinate refers to a member. 5
     * - Action coordinate refers to a set. 6 - Action coordinate refers to a cell.
     */
    @XmlElement(name = "COORDINATE_TYPE", required = false)
    private CoordinateTypeEnum coordinateType;

    /**
     * The caption for the action. The action name is used if no caption was specified and no
     * translations were specified when the action was created or altered.
     */
    @XmlElement(name = "ACTION_CAPTION", required = false)
    private String actionCaption;

    /**
     * A description of the action.
     */
    @XmlElement(name = "DESCRIPTION", required = false)
    private String description;

    /**
     * The expression or content of the action that is to be run.
     */
    @XmlElement(name = "CONTENT", required = false)
    private String content;

    /**
     * The name of the application that is to be used to run the action.
     */
    @XmlElement(name = "APPLICATION", required = false)
    private String application;

    /**
     * Information about how to invoke the action: 1 - Indicates a regular action used during normal
     * operations. This is the default value for this column. 2 - Indicates that the action is performed
     * when the cube is first opened. 4 - Indicates that the action is performed as part of a batch
     * operation.
     */
    @XmlElement(name = "INVOCATION", required = false)
    private InvocationEnum invocation;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public ActionTypeEnum getActionType() {
        return actionType;
    }

    public void setActionType(ActionTypeEnum actionType) {
        this.actionType = actionType;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public CoordinateTypeEnum getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(CoordinateTypeEnum coordinateType) {
        this.coordinateType = coordinateType;
    }

    public String getActionCaption() {
        return actionCaption;
    }

    public void setActionCaption(String actionCaption) {
        this.actionCaption = actionCaption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public InvocationEnum getInvocation() {
        return invocation;
    }

    public void setInvocation(InvocationEnum invocation) {
        this.invocation = invocation;
    }
}
