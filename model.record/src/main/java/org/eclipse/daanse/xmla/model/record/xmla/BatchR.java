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
package org.eclipse.daanse.xmla.model.record.xmla;

import java.util.List;

import org.eclipse.daanse.xmla.api.xmla.Alter;
import org.eclipse.daanse.xmla.api.xmla.Attach;
import org.eclipse.daanse.xmla.api.xmla.Backup;
import org.eclipse.daanse.xmla.api.xmla.Batch;
import org.eclipse.daanse.xmla.api.xmla.BeginTransaction;
import org.eclipse.daanse.xmla.api.xmla.Cancel;
import org.eclipse.daanse.xmla.api.xmla.ClearCache;
import org.eclipse.daanse.xmla.api.xmla.CloneDatabase;
import org.eclipse.daanse.xmla.api.xmla.CommitTransaction;
import org.eclipse.daanse.xmla.api.xmla.Create;
import org.eclipse.daanse.xmla.api.xmla.DBCC;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.Delete;
import org.eclipse.daanse.xmla.api.xmla.DesignAggregations;
import org.eclipse.daanse.xmla.api.xmla.Detach;
import org.eclipse.daanse.xmla.api.xmla.Discover;
import org.eclipse.daanse.xmla.api.xmla.Drop;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.ImageLoad;
import org.eclipse.daanse.xmla.api.xmla.ImageSave;
import org.eclipse.daanse.xmla.api.xmla.Insert;
import org.eclipse.daanse.xmla.api.xmla.Lock;
import org.eclipse.daanse.xmla.api.xmla.MergePartitions;
import org.eclipse.daanse.xmla.api.xmla.NotifyTableChange;
import org.eclipse.daanse.xmla.api.xmla.OutOfLineBinding;
import org.eclipse.daanse.xmla.api.xmla.Process;
import org.eclipse.daanse.xmla.api.xmla.Restore;
import org.eclipse.daanse.xmla.api.xmla.RollbackTransaction;
import org.eclipse.daanse.xmla.api.xmla.SetAuthContext;
import org.eclipse.daanse.xmla.api.xmla.Subscribe;
import org.eclipse.daanse.xmla.api.xmla.Synchronize;
import org.eclipse.daanse.xmla.api.xmla.Unlock;
import org.eclipse.daanse.xmla.api.xmla.Unsubscribe;
import org.eclipse.daanse.xmla.api.xmla.Update;
import org.eclipse.daanse.xmla.api.xmla.UpdateCells;

public record BatchR(List<Batch.Parallel> parallel, OutOfLineBinding bindings, DataSource dataSource,
        DataSourceView dataSourceView, ErrorConfiguration errorConfiguration, List<Create> create, List<Alter> alter,
        List<Delete> delete, List<Process> process, List<MergePartitions> mergePartitions,
        List<DesignAggregations> designAggregations, List<NotifyTableChange> notifyTableChange, List<Insert> insert,
        List<Update> update, List<Drop> drop, List<UpdateCells> updateCells, List<Backup> backup, List<Restore> restore,
        List<Synchronize> synchronize, List<Cancel> cancel, List<BeginTransaction> beginTransaction,
        List<CommitTransaction> commitTransaction, List<RollbackTransaction> rollbackTransaction,
        List<ClearCache> clearCache, List<Subscribe> subscribe, List<Unsubscribe> unsubscribe, List<Detach> detach,
        List<Attach> attach, List<Lock> lock, List<Unlock> unlock, List<ImageLoad> imageLoad, List<ImageSave> imageSave,
        List<CloneDatabase> cloneDatabase, List<SetAuthContext> setAuthContext, DBCC dbcc, List<Discover> discover,
        Boolean transaction, Boolean processAffectedObjects) implements Batch {

    public record Parallel(List<Process> process, Integer maxParallel) implements Batch.Parallel {

    }

}
