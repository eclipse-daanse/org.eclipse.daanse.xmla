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

import java.time.Duration;
import java.util.List;
import java.util.Optional;

/**
 * The ProactiveCachingQueryBinding complex type represents a binding to a collection of query
 * notifications for proactive caching. The base type has no elements.
 */
public interface ProactiveCachingQueryBinding extends ProactiveCachingBinding {

    /**
     * @return The interval for running the queries. default 1 second
     */
    Optional<Duration> refreshInterval();

    /**
     * @return A collection of objects of type QueryNotification.
     */
    List<QueryNotification> queryNotifications();

}
