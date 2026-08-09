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

/**
 * The first element inside {@code <Command>} names no command this model knows.
 * <p>
 * Carries the name so the answer can repeat it. An {@code <Execute>} refused as
 * "no command" when it plainly had one is the kind of message that sends the
 * reader to the wrong end of the wire.
 */
public class UnknownCommandException extends XmlaCodecException {

    private static final long serialVersionUID = 1L;

    private final transient String command;

    public UnknownCommandException(String command) {
        super("the command " + command + " is not one this model describes");
        this.command = command;
    }

    /** The command's element name, verbatim. */
    public String command() {
        return command;
    }
}
