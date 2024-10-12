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

import java.util.List;

public non-sealed interface Batch extends Command {

    List<Parallel> parallel();

    OutOfLineBinding bindings();

    DataSource dataSource();

    DataSourceView dataSourceView();

    ErrorConfiguration errorConfiguration();

    List<Create> create();

    List<Alter> alter();

    List<Delete> delete();

    List<Process> process();

    List<MergePartitions> mergePartitions();

    List<DesignAggregations> designAggregations();

    List<NotifyTableChange> notifyTableChange();

    List<Insert> insert();

    List<Update> update();

    List<Drop> drop();

    List<UpdateCells> updateCells();

    List<Backup> backup();

    List<Restore> restore();

    List<Synchronize> synchronize();

    List<Cancel> cancel();

    List<BeginTransaction> beginTransaction();

    List<CommitTransaction> commitTransaction();

    List<RollbackTransaction> rollbackTransaction();

    List<ClearCache> clearCache();

    List<Subscribe> subscribe();

    List<Unsubscribe> unsubscribe();

    List<Detach> detach();

    List<Attach> attach();

    List<Lock> lock();

    List<Unlock> unlock();

    List<ImageLoad> imageLoad();

    List<ImageSave> imageSave();

    List<CloneDatabase> cloneDatabase();

    List<SetAuthContext> setAuthContext();

    DBCC dbcc();

    List<Discover> discover();

    Boolean transaction();

    Boolean processAffectedObjects();

    public interface Parallel {

        List<Process> process();

        Integer maxParallel();
    }

}
