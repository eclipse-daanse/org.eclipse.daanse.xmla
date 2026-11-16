/*
 * Copyright (c) 2026 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.server.jdk.httpserver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

/**
 * The server turns Nagle's algorithm off for the JDK's server before it creates one, unless the process set the switch
 * itself: with it on, an answer can wait for the client's delayed acknowledgement. The switch is a system property the
 * JDK reads once per process, so what is tested here is that it is set; its effect on the time of an answer shows only
 * where a server is the first in its process.
 */
@ResourceLock("system-properties")
class TheServerTurnsNagleOffTest {

    private String before;

    @BeforeEach
    void remember() {
        before = System.getProperty(JdkHttpServer.NO_DELAY);
    }

    @AfterEach
    void restore() {
        if (before == null) {
            System.clearProperty(JdkHttpServer.NO_DELAY);
        } else {
            System.setProperty(JdkHttpServer.NO_DELAY, before);
        }
    }

    @Test
    void theSwitchIsSetWhenNobodySetIt() {
        System.clearProperty(JdkHttpServer.NO_DELAY);

        JdkHttpServer.noDelayUnlessSet();

        assertThat(System.getProperty(JdkHttpServer.NO_DELAY)).isEqualTo("true");
    }

    @Test
    void aSwitchTheProcessSetStands() {
        System.setProperty(JdkHttpServer.NO_DELAY, "false");

        JdkHttpServer.noDelayUnlessSet();

        assertThat(System.getProperty(JdkHttpServer.NO_DELAY)).isEqualTo("false");
    }
}
