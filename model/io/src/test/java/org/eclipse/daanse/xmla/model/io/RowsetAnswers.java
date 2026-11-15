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
 *   Stefan Bischof (bipolis.org) - initial
 */
package org.eclipse.daanse.xmla.model.io;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assumptions;

/**
 * The answers of Analysis Services 2017 that the model is checked against: the rows of {@code DISCOVER_SCHEMA_ROWSETS}
 * and {@code DISCOVER_PROPERTIES} and the inline schema of each rowset's answer. They lie in a folder outside this
 * repository, named by the system property {@value #PROPERTY}; LOCAL-ROWSET-ANSWERS.md of this module lists the files
 * and how to get them. A test that reads them is skipped when its file is not there.
 */
final class RowsetAnswers {

    /** The system property that names the folder. */
    static final String PROPERTY = "xmla.rowset.answers";

    /** The folder, by default beside this repository's working tree, relative to this module. */
    static final Path FOLDER = Path.of(System.getProperty(PROPERTY, "../../../xmla-rowset-answers"));

    private RowsetAnswers() {
    }

    /** The file of that name in the folder, whether it is there or not. */
    static Path file(String name) {
        return FOLDER.resolve(name);
    }

    /** Aborts the calling test, which then counts as skipped, unless the file of that name is in the folder. */
    static void assumePresent(String name) {
        Path file = file(name);
        Assumptions.assumeTrue(Files.isRegularFile(file),
                () -> "no rowset answers at " + file.toAbsolutePath().normalize() + " (set -D" + PROPERTY + ")");
    }
}
