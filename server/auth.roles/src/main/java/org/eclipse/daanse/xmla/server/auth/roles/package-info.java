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
/**
 * How a caller's roles are decided, for every mechanism at once.
 * <p>
 * {@code DefaultRoleResolution} is the service every mechanism references: it
 * collects what each registered {@code RoleProvider} grants, adds the names the
 * mechanism read itself, and translates the union once.
 * {@code ConfiguredRoleMapping} is that translation, driven by a table in the
 * deployment's configuration.
 */
package org.eclipse.daanse.xmla.server.auth.roles;
