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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import static org.eclipse.daanse.xmla.server.jakarta.jws.AnnotationConvertor.convertAnnotationList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertToInstant;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.xmla.Member;
import org.eclipse.daanse.xmla.api.xmla.Role;
import org.eclipse.daanse.xmla.model.record.xmla.MemberR;
import org.eclipse.daanse.xmla.model.record.xmla.RoleR;

public class RoleConvertor {

    private RoleConvertor() {
    }

    public static Role convertRole(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Role role) {
        if (role != null) {
            return new RoleR(role.getName(),
                Optional.ofNullable(role.getID()),
                Optional.ofNullable(convertToInstant(role.getCreatedTimestamp())),
                Optional.ofNullable(convertToInstant(role.getLastSchemaUpdate())),
                Optional.ofNullable(role.getDescription()),
                Optional.ofNullable(convertAnnotationList(role.getAnnotations() == null ? null : role.getAnnotations())),
                Optional.ofNullable(convertMemberList(role.getMembers())));
        }
        return null;
    }

    private static List<Member> convertMemberList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Member> list) {
        if (list != null) {
            return list.stream().map(RoleConvertor::convertMember).toList();
        }
        return List.of();
    }

    private static Member convertMember(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Member member) {
        if (member != null) {
            return new MemberR(Optional.ofNullable(member.getName()),
                Optional.ofNullable(member.getSid()));
        }
        return null;
    }

    public static List<Role> convertRoleList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Role> list) {
        if (list != null) {
            return list.stream().map(RoleConvertor::convertRole).toList();
        }
        return List.of();
    }
}
