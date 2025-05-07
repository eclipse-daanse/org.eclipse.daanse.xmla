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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla_exception;

import java.io.Serializable;

import org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine200.WarningLocationObject;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageLocation", propOrder = {

})
public class MessageLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "Start", required = true, namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected MessageLocation.Start start;
    @XmlElement(name = "End", required = true, namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected MessageLocation.End end;
    @XmlElement(name = "LineOffset", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected Integer lineOffset;
    @XmlElement(name = "TextLength", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected Integer textLength;
    @XmlElement(name = "SourceObject", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected WarningLocationObject sourceObject;
    @XmlElement(name = "DependsOnObject", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected WarningLocationObject dependsOnObject;
    @XmlElement(name = "RowNumber", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
    protected Integer rowNumber;

    public MessageLocation.Start getStart() {
        return start;
    }

    public void setStart(MessageLocation.Start value) {
        this.start = value;
    }

    public MessageLocation.End getEnd() {
        return end;
    }

    public void setEnd(MessageLocation.End value) {
        this.end = value;
    }

    public Integer getLineOffset() {
        return lineOffset;
    }

    public void setLineOffset(Integer value) {
        this.lineOffset = value;
    }

    public Integer getTextLength() {
        return textLength;
    }

    public void setTextLength(Integer value) {
        this.textLength = value;
    }

    public WarningLocationObject getSourceObject() {
        return sourceObject;
    }

    public void setSourceObject(WarningLocationObject value) {
        this.sourceObject = value;
    }

    public WarningLocationObject getDependsOnObject() {
        return dependsOnObject;
    }

    public void setDependsOnObject(WarningLocationObject value) {
        this.dependsOnObject = value;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer value) {
        this.rowNumber = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "AbstractEndStart", propOrder = {

    })
    public abstract static class AbstractEndStart {

        @XmlElement(name = "Line", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
        protected int line;
        @XmlElement(name = "Column", namespace = "urn:schemas-microsoft-com:xml-analysis:mddataset")
        protected int column;

        public int getLine() {
            return line;
        }

        public void setLine(int value) {
            this.line = value;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int value) {
            this.column = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "End", propOrder = {

    })
    public static class End extends AbstractEndStart implements Serializable {
        private static final long serialVersionUID = 1L;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "Start", propOrder = {

    })
    public static class Start extends AbstractEndStart implements Serializable {

        private static final long serialVersionUID = 1L;

    }

}
