/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.server.auth.roles;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.daanse.xmla.api.auth.Claims;
import org.eclipse.daanse.xmla.api.auth.RoleMapping;
import org.eclipse.daanse.xmla.api.auth.RoleProvider;
import org.eclipse.daanse.xmla.api.auth.RoleResolution;
import org.eclipse.daanse.xmla.api.auth.Roles;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * The one place the role sources are put together.
 * <p>
 * Before this existed, each mechanism declared the provider whiteboard and the
 * optional mapping for itself, and two of them applied the mapping twice - once
 * to the groups they had read and again to the providers' answers. A mapping
 * therefore never saw the whole set, and a rule over a combination could not
 * fire. Here the union is formed first and mapped once.
 */
@Component(service = RoleResolution.class)
public class DefaultRoleResolution implements RoleResolution {

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    private final List<RoleProvider> providers = new CopyOnWriteArrayList<>();

    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY)
    private volatile RoleMapping mapping;

    @Override
    public Set<String> resolve(Principal principal, Claims claims, Collection<String> external) {
        return Roles.resolve(principal, claims, external, providers, mapping);
    }
}
