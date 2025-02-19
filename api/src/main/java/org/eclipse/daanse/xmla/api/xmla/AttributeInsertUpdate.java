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
package org.eclipse.daanse.xmla.api.xmla;

import java.math.BigInteger;
import java.util.List;

public interface AttributeInsertUpdate {

    String attributeName();

    String name();

    List<Object> keys();

    List<TranslationInsertUpdate> translations();

    String value();

    String customrollup();

    String customrollupproperties();

    String unaryoperator();

    BigInteger skippedlevels();

}
