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
package org.eclipse.daanse.xmla.model.io;

import java.util.List;

/**
 * The enumerations DISCOVER_ENUMERATORS reports: [MS-SSAS] vocabularies, not
 * server facts. Each is reported as its simple name with its constants' names
 * and ordinals.
 */
public final class XmlaEnumerators {

    /** How a property may be used; OLE DB's three-valued access. */
    public enum Access {
        Read, Write, ReadWrite
    }

    /** How a client authenticates against a data source. */
    public enum AuthenticationMode {
        Unauthenticated, Authenticated, Integrated
    }

    /** What kind of data a provider serves. */
    public enum ProviderType {
        TDP, MDP, DMP
    }

    /**
     * The tree operations MDSCHEMA_MEMBERS accepts; [MS-SSAS]'s MDTREEOP_*
     * constants.
     */
    public enum TreeOp {
        MDTREEOP_CHILDREN, MDTREEOP_SIBLINGS, MDTREEOP_PARENT, MDTREEOP_SELF, MDTREEOP_DESCENDANTS, MDTREEOP_ANCESTORS
    }

    /** The enumerations, in the order they are reported. */
    public static final List<Class<? extends Enum<?>>> ALL = List.of(Access.class, AuthenticationMode.class,
            ProviderType.class, TreeOp.class);

    private XmlaEnumerators() {
        // static access only
    }
}
