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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Annotation;
import org.eclipse.daanse.xmla.model.record.xmla.AnnotationR;

public class AnnotationConvertor {
    private AnnotationConvertor() {
    }
    public static List<Annotation> convertAnnotationList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Annotation> annotationList) {
        if (annotationList == null) {
            return List.of();
        }
        return annotationList.stream().map(AnnotationConvertor::convertAnnotation).toList();
    }

    public static Annotation convertAnnotation(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Annotation annotation) {
        return new AnnotationR(annotation.getName(),
            Optional.ofNullable(annotation.getVisibility()),
            Optional.ofNullable(annotation.getValue()));
    }

}
