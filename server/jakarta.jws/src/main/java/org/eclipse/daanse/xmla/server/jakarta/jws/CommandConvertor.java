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
package org.eclipse.daanse.xmla.server.jakarta.jws;

import static org.eclipse.daanse.xmla.server.jakarta.jws.AnnotationConvertor.convertAnnotationList;
import static org.eclipse.daanse.xmla.server.jakarta.jws.BindingConvertor.convertBinding;
import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertDuration;
import static org.eclipse.daanse.xmla.server.jakarta.jws.ConvertorUtil.convertToInstant;
import static org.eclipse.daanse.xmla.server.jakarta.jws.MajorObjectConvertor.convertMajorObject;
import static org.eclipse.daanse.xmla.server.jakarta.jws.ObjectReferenceConvertor.convertObjectReference;

import java.util.List;
import java.util.Optional;

import org.eclipse.daanse.xmla.api.engine.ImpersonationInfo;
import org.eclipse.daanse.xmla.api.xmla.Alter;
import org.eclipse.daanse.xmla.api.xmla.Attach;
import org.eclipse.daanse.xmla.api.xmla.AttributeInsertUpdate;
import org.eclipse.daanse.xmla.api.xmla.Backup;
import org.eclipse.daanse.xmla.api.xmla.Batch;
import org.eclipse.daanse.xmla.api.xmla.BeginTransaction;
import org.eclipse.daanse.xmla.api.xmla.Binding;
import org.eclipse.daanse.xmla.api.xmla.Bindings;
import org.eclipse.daanse.xmla.api.xmla.Cancel;
import org.eclipse.daanse.xmla.api.xmla.Cell;
import org.eclipse.daanse.xmla.api.xmla.ClearCache;
import org.eclipse.daanse.xmla.api.xmla.CloneDatabase;
import org.eclipse.daanse.xmla.api.xmla.Command;
import org.eclipse.daanse.xmla.api.xmla.CommitTransaction;
import org.eclipse.daanse.xmla.api.xmla.Create;
import org.eclipse.daanse.xmla.api.xmla.DBCC;
import org.eclipse.daanse.xmla.api.xmla.DataSource;
import org.eclipse.daanse.xmla.api.xmla.DataSourcePermission;
import org.eclipse.daanse.xmla.api.xmla.DataSourceView;
import org.eclipse.daanse.xmla.api.xmla.Delete;
import org.eclipse.daanse.xmla.api.xmla.DesignAggregations;
import org.eclipse.daanse.xmla.api.xmla.Detach;
import org.eclipse.daanse.xmla.api.xmla.Discover;
import org.eclipse.daanse.xmla.api.xmla.Drop;
import org.eclipse.daanse.xmla.api.xmla.ErrorConfiguration;
import org.eclipse.daanse.xmla.api.xmla.Folder;
import org.eclipse.daanse.xmla.api.xmla.ImageLoad;
import org.eclipse.daanse.xmla.api.xmla.ImageSave;
import org.eclipse.daanse.xmla.api.xmla.Insert;
import org.eclipse.daanse.xmla.api.xmla.Location;
import org.eclipse.daanse.xmla.api.xmla.LocationBackup;
import org.eclipse.daanse.xmla.api.xmla.Lock;
import org.eclipse.daanse.xmla.api.xmla.MergePartitions;
import org.eclipse.daanse.xmla.api.xmla.NotifyTableChange;
import org.eclipse.daanse.xmla.api.xmla.ObjectExpansion;
import org.eclipse.daanse.xmla.api.xmla.ObjectReference;
import org.eclipse.daanse.xmla.api.xmla.OutOfLineBinding;
import org.eclipse.daanse.xmla.api.xmla.Properties;
import org.eclipse.daanse.xmla.api.xmla.PropertyList;
import org.eclipse.daanse.xmla.api.xmla.ReadDefinitionEnum;
import org.eclipse.daanse.xmla.api.xmla.ReadWritePermissionEnum;
import org.eclipse.daanse.xmla.api.xmla.Restore;
import org.eclipse.daanse.xmla.api.xmla.Restrictions;
import org.eclipse.daanse.xmla.api.xmla.RollbackTransaction;
import org.eclipse.daanse.xmla.api.xmla.Scope;
import org.eclipse.daanse.xmla.api.xmla.SetAuthContext;
import org.eclipse.daanse.xmla.api.xmla.Source;
import org.eclipse.daanse.xmla.api.xmla.Subscribe;
import org.eclipse.daanse.xmla.api.xmla.Synchronize;
import org.eclipse.daanse.xmla.api.xmla.TableNotification;
import org.eclipse.daanse.xmla.api.xmla.TranslationInsertUpdate;
import org.eclipse.daanse.xmla.api.xmla.Unlock;
import org.eclipse.daanse.xmla.api.xmla.Unsubscribe;
import org.eclipse.daanse.xmla.api.xmla.Update;
import org.eclipse.daanse.xmla.api.xmla.UpdateCells;
import org.eclipse.daanse.xmla.api.xmla.Where;
import org.eclipse.daanse.xmla.api.xmla.WhereAttribute;
import org.eclipse.daanse.xmla.api.xmla.WriteBackTableCreation;
import org.eclipse.daanse.xmla.model.record.engine.ImpersonationInfoR;
import org.eclipse.daanse.xmla.model.record.xmla.AlterR;
import org.eclipse.daanse.xmla.model.record.xmla.AttachR;
import org.eclipse.daanse.xmla.model.record.xmla.AttributeInsertUpdateR;
import org.eclipse.daanse.xmla.model.record.xmla.BackupR;
import org.eclipse.daanse.xmla.model.record.xmla.BatchR;
import org.eclipse.daanse.xmla.model.record.xmla.BeginTransactionR;
import org.eclipse.daanse.xmla.model.record.xmla.BindingsR;
import org.eclipse.daanse.xmla.model.record.xmla.CancelR;
import org.eclipse.daanse.xmla.model.record.xmla.CellR;
import org.eclipse.daanse.xmla.model.record.xmla.ClearCacheR;
import org.eclipse.daanse.xmla.model.record.xmla.CloneDatabaseR;
import org.eclipse.daanse.xmla.model.record.xmla.CommitTransactionR;
import org.eclipse.daanse.xmla.model.record.xmla.CreateR;
import org.eclipse.daanse.xmla.model.record.xmla.DBCCR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourcePermissionR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourceR;
import org.eclipse.daanse.xmla.model.record.xmla.DataSourceViewR;
import org.eclipse.daanse.xmla.model.record.xmla.DeleteR;
import org.eclipse.daanse.xmla.model.record.xmla.DesignAggregationsR;
import org.eclipse.daanse.xmla.model.record.xmla.DetachR;
import org.eclipse.daanse.xmla.model.record.xmla.DiscoverR;
import org.eclipse.daanse.xmla.model.record.xmla.DropR;
import org.eclipse.daanse.xmla.model.record.xmla.ErrorConfigurationR;
import org.eclipse.daanse.xmla.model.record.xmla.FolderR;
import org.eclipse.daanse.xmla.model.record.xmla.ImageLoadR;
import org.eclipse.daanse.xmla.model.record.xmla.ImageSaveR;
import org.eclipse.daanse.xmla.model.record.xmla.InsertR;
import org.eclipse.daanse.xmla.model.record.xmla.LocationBackupR;
import org.eclipse.daanse.xmla.model.record.xmla.LocationR;
import org.eclipse.daanse.xmla.model.record.xmla.LockR;
import org.eclipse.daanse.xmla.model.record.xmla.MergePartitionsR;
import org.eclipse.daanse.xmla.model.record.xmla.NotifyTableChangeR;
import org.eclipse.daanse.xmla.model.record.xmla.OutOfLineBindingR;
import org.eclipse.daanse.xmla.model.record.xmla.ProcessR;
import org.eclipse.daanse.xmla.model.record.xmla.PropertiesR;
import org.eclipse.daanse.xmla.model.record.xmla.PropertyListR;
import org.eclipse.daanse.xmla.model.record.xmla.RestoreR;
import org.eclipse.daanse.xmla.model.record.xmla.RestrictionsR;
import org.eclipse.daanse.xmla.model.record.xmla.RollbackTransactionR;
import org.eclipse.daanse.xmla.model.record.xmla.SetAuthContextR;
import org.eclipse.daanse.xmla.model.record.xmla.SourceR;
import org.eclipse.daanse.xmla.model.record.xmla.StatementR;
import org.eclipse.daanse.xmla.model.record.xmla.SubscribeR;
import org.eclipse.daanse.xmla.model.record.xmla.SynchronizeR;
import org.eclipse.daanse.xmla.model.record.xmla.TableNotificationR;
import org.eclipse.daanse.xmla.model.record.xmla.TranslationInsertUpdateR;
import org.eclipse.daanse.xmla.model.record.xmla.UnlockR;
import org.eclipse.daanse.xmla.model.record.xmla.UnsubscribeR;
import org.eclipse.daanse.xmla.model.record.xmla.UpdateCellsR;
import org.eclipse.daanse.xmla.model.record.xmla.UpdateR;
import org.eclipse.daanse.xmla.model.record.xmla.WhereAttributeR;
import org.eclipse.daanse.xmla.model.record.xmla.WhereR;
import org.eclipse.daanse.xmla.model.record.xmla.XmlaObjectR;
import org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.XmlaObject;
import org.eclipse.daanse.xmla.api.xmla.Process;

public class CommandConvertor {
    private CommandConvertor() {
    }

    public static List<Command> convertCommandList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Command> commandList) {
        if (commandList == null) {
            return List.of();
        }
        return commandList.stream().map(CommandConvertor::convertCommand).toList();

    }

    private static Command convertCommand(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Command command) {
        if (command != null) {
            if (command.getStatement() != null) {
                return new StatementR(command.getStatement());
            }
            if (command.getCreate() != null) {
                return convertCreate(command.getCreate());
            }
            if (command.getAlter() != null) {
                convertAlter(command.getAlter());
            }
            if (command.getDelete() != null) {
                convertDelete(command.getDelete());
            }
            if (command.getProcess() != null) {
                convertProcess(command.getProcess());
            }
            if (command.getMergePartitions() != null) {
                return convertMergePartitions(command.getMergePartitions());
            }
            if (command.getDesignAggregations() != null) {
                return convertDesignAggregations(command.getDesignAggregations());
            }
            if (command.getClearCache() != null) {
                return convertClearCache(command.getClearCache());
            }
            if (command.getSubscribe() != null) {
                return convertSubscribe(command.getSubscribe());
            }
            if (command.getUnsubscribe() != null) {
                return convertUnsubscribe(command.getUnsubscribe());
            }
            if (command.getCancel() != null) {
                return convertCancel(command.getCancel());
            }
            if (command.getBeginTransaction() != null) {
                return convertBeginTransaction(command.getBeginTransaction());
            }
            if (command.getCommitTransaction() != null) {
                return convertCommitTransaction(command.getCommitTransaction());
            }
            if (command.getRollbackTransaction() != null) {
                return convertRollbackTransaction(command.getRollbackTransaction());
            }
            if (command.getLock() != null) {
                return convertLock(command.getLock());
            }
            if (command.getUnlock() != null) {
                return convertUnlock(command.getUnlock());
            }
            if (command.getBackup() != null) {
                return convertBackup(command.getBackup());
            }
            if (command.getRestore() != null) {
                return convertRestore(command.getRestore());
            }
            if (command.getSynchronize() != null) {
                return convertSynchronize(command.getSynchronize());
            }
            if (command.getAttach() != null) {
                return convertAttach(command.getAttach());
            }
            if (command.getDetach() != null) {
                return convertDetach(command.getDetach());
            }
            if (command.getInsert() != null) {
                return convertInsert(command.getInsert());
            }
            if (command.getUpdate() != null) {
                return convertUpdate(command.getUpdate());
            }
            if (command.getDrop() != null) {
                return convertDrop(command.getDrop());
            }
            if (command.getUpdateCells() != null) {
                return convertUpdateCells(command.getUpdateCells());
            }
            if (command.getNotifyTableChange() != null) {
                return convertNotifyTableChange(command.getNotifyTableChange());
            }
            if (command.getBatch() != null) {
                return convertBatch(command.getBatch());
            }
            if (command.getImageLoad() != null) {
                return convertImageLoad(command.getImageLoad());
            }
            if (command.getImageSave() != null) {
                return convertImageSave(command.getImageSave());
            }
            if (command.getCloneDatabase() != null) {
                return convertCloneDatabase(command.getCloneDatabase());
            }
            if (command.getSetAuthContext() != null) {
                return convertSetAuthContext(command.getSetAuthContext());
            }
            if (command.getDBCC() != null) {
                return convertDBCC(command.getDBCC());
            }
        }
        return null;
    }

    private static DBCC convertDBCC(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DBCC dbcc) {
        if (dbcc != null) {
            return new DBCCR(convertObjectReference(dbcc.getObject()));
        }
        return null;

    }

    private static SetAuthContext convertSetAuthContext(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.SetAuthContext setAuthContext) {
        if (setAuthContext != null) {
            return new SetAuthContextR(setAuthContext.getToken(), setAuthContext.getDatabaseID());
        }
        return null;

    }

    private static CloneDatabase convertCloneDatabase(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.CloneDatabase cloneDatabase) {
        if (cloneDatabase != null) {
            return new CloneDatabaseR(
                    convertObjectReference(
                            cloneDatabase.getObject() == null ? null : cloneDatabase.getObject().getDatabaseID()),
                    convertCloneDatabaseTarget(cloneDatabase.getTarget()));
        }
        return null;

    }

    private static CloneDatabase.Target convertCloneDatabaseTarget(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.CloneDatabase.Target target) {
        if (target != null) {
            return new CloneDatabaseR.Target(target.getDbStorageLocation(), target.getDatabaseName(),
                    target.getDatabaseID());
        }
        return null;
    }

    private static ImageSave convertImageSave(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ImageSave imageSave) {
        if (imageSave != null) {
            return new ImageSaveR(convertObjectReference(imageSave.getObject()), imageSave.isData());
        }
        return null;
    }

    private static ImageLoad convertImageLoad(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ImageLoad imageLoad) {
        if (imageLoad != null) {
            return new ImageLoadR(imageLoad.getImagePath(), imageLoad.getImageUrl(), imageLoad.getImageUniqueID(),
                    imageLoad.getImageVersion(), imageLoad.getReadWriteMode(), imageLoad.getDbStorageLocation(),
                    imageLoad.getDatabaseName(), imageLoad.getDatabaseID(),
                    imageLoad.getData());
        }
        return null;

    }

    private static Batch convertBatch(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Batch batch) {
        if (batch != null) {
            return new BatchR(convertBatchParallelList(batch.getParallel()),
                    convertOutOfLineBinding(batch.getBindings()), convertDataSource(batch.getDataSource()),
                    convertDataSourceView(batch.getDataSourceView()),
                    convertErrorConfiguration(batch.getErrorConfiguration()), convertCreateList(batch.getCreate()),
                    convertAlterList(batch.getAlter()), convertDeleteList(batch.getDelete()),
                    convertProcessList(batch.getProcess()), convertMergePartitionsList(batch.getMergePartitions()),
                    convertDesignAggregationsList(batch.getDesignAggregations()),
                    convertNotifyTableChangeList(batch.getNotifyTableChange()), convertInsertList(batch.getInsert()),
                    convertUpdateList(batch.getUpdate()), convertDropList(batch.getDrop()),
                    convertUpdateCellsList(batch.getUpdateCells()), convertBackupList(batch.getBackup()),
                    convertRestoreList(batch.getRestore()), convertSynchronizeList(batch.getSynchronize()),
                    convertCancelList(batch.getCancel()), convertBeginTransactionList(batch.getBeginTransaction()),
                    convertCommitTransactionList(batch.getCommitTransaction()),
                    convertRollbackTransactionList(batch.getRollbackTransaction()),
                    convertClearCacheList(batch.getClearCache()), convertSubscribeList(batch.getSubscribe()),
                    convertUnsubscribeList(batch.getUnsubscribe()), convertDetachList(batch.getDetach()),
                    convertAttachList(batch.getAttach()), convertLockList(batch.getLock()),
                    convertUnlockList(batch.getUnlock()), convertImageLoadList(batch.getImageLoad()),
                    convertImageSaveList(batch.getImageSave()), convertCloneDatabaseList(batch.getCloneDatabase()),
                    convertSetAuthContextList(batch.getSetAuthContext()), convertDBCC(batch.getDBCC()),
                    convertDiscoverList(batch.getDiscover()), batch.getTransaction(),
                    batch.getProcessAffectedObjects());
        }
        return null;
    }

    private static List<SetAuthContext> convertSetAuthContextList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.SetAuthContext> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertSetAuthContext).toList();
        }
        return List.of();
    }

    private static List<CloneDatabase> convertCloneDatabaseList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.CloneDatabase> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertCloneDatabase).toList();
        }
        return List.of();
    }

    private static List<ImageSave> convertImageSaveList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ImageSave> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertImageSave).toList();
        }
        return List.of();
    }

    private static List<ImageLoad> convertImageLoadList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ImageLoad> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertImageLoad).toList();
        }
        return List.of();
    }

    private static List<Unlock> convertUnlockList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Unlock> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertUnlock).toList();
        }
        return List.of();
    }

    private static List<Lock> convertLockList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Lock> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertLock).toList();
        }
        return List.of();
    }

    private static List<Attach> convertAttachList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Attach> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertAttach).toList();
        }
        return List.of();
    }

    private static List<Detach> convertDetachList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Detach> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertDetach).toList();
        }
        return List.of();
    }

    private static List<Unsubscribe> convertUnsubscribeList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Unsubscribe> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertUnsubscribe).toList();
        }
        return List.of();
    }

    private static List<Subscribe> convertSubscribeList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Subscribe> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertSubscribe).toList();
        }
        return List.of();
    }

    private static List<ClearCache> convertClearCacheList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ClearCache> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertClearCache).toList();
        }
        return List.of();
    }

    private static List<RollbackTransaction> convertRollbackTransactionList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.RollbackTransaction> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertRollbackTransaction).toList();
        }
        return List.of();
    }

    private static List<CommitTransaction> convertCommitTransactionList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.CommitTransaction> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertCommitTransaction).toList();
        }
        return List.of();
    }

    private static List<BeginTransaction> convertBeginTransactionList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.BeginTransaction> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertBeginTransaction).toList();
        }
        return List.of();
    }

    private static List<Cancel> convertCancelList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Cancel> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertCancel).toList();
        }
        return List.of();
    }

    private static List<Synchronize> convertSynchronizeList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Synchronize> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertSynchronize).toList();
        }
        return List.of();
    }

    private static List<Restore> convertRestoreList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Restore> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertRestore).toList();
        }
        return List.of();
    }

    private static List<Backup> convertBackupList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Backup> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertBackup).toList();
        }
        return List.of();
    }

    private static List<UpdateCells> convertUpdateCellsList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.UpdateCells> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertUpdateCells).toList();
        }
        return List.of();
    }

    private static List<Drop> convertDropList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Drop> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertDrop).toList();
        }
        return List.of();
    }

    private static List<Update> convertUpdateList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Update> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertUpdate).toList();
        }
        return List.of();
    }

    private static List<Insert> convertInsertList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Insert> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertInsert).toList();
        }
        return List.of();
    }

    private static List<NotifyTableChange> convertNotifyTableChangeList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.NotifyTableChange> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertNotifyTableChange).toList();
        }
        return List.of();
    }

    private static List<DesignAggregations> convertDesignAggregationsList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DesignAggregations> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertDesignAggregations).toList();
        }
        return List.of();
    }

    private static List<MergePartitions> convertMergePartitionsList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MergePartitions> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertMergePartitions).toList();
        }
        return List.of();
    }

    private static List<Delete> convertDeleteList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Delete> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertDelete).toList();
        }
        return List.of();
    }

    private static List<Alter> convertAlterList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Alter> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertAlter).toList();
        }
        return List.of();
    }

    private static List<Create> convertCreateList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Create> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertCreate).toList();
        }
        return List.of();
    }

    private static List<Batch.Parallel> convertBatchParallelList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Batch.Parallel> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertBatchParallel).toList();
        }
        return List.of();
    }

    private static Batch.Parallel convertBatchParallel(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Batch.Parallel parallel) {
        if (parallel != null) {
            return new BatchR.Parallel(convertProcessList(parallel.getProcess()), parallel.getMaxParallel());
        }
        return null;
    }

    private static List<Process> convertProcessList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Process> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertProcess).toList();
        }
        return List.of();
    }

    private static List<Discover> convertDiscoverList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Discover> list) {
        if (list != null) {
            return list.stream().map(CommandConvertor::convertDiscover).toList();
        }
        return List.of();
    }

    private static Discover convertDiscover(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Discover discover) {
        if (discover != null) {
            return new DiscoverR(discover.getRequestType(), convertRestrictions(discover.getRestrictions()),
                    convertProperties(discover.getProperties()));
        }
        return null;
    }

    private static Properties convertProperties(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Properties properties) {
        if (properties != null) {
            return new PropertiesR(convertPropertyList(properties.getPropertyList()));
        }
        return null;
    }

    private static PropertyList convertPropertyList(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.PropertyList p) {
        if (p != null) {
            return new PropertyListR(p.getDataSourceInfo(), p.getTimeout(), p.getUserName(), p.getPassword(),
                    p.getLocaleIdentifier(), p.getCatalog(), p.getStateSupport(), p.getContent(), p.getFormat(),
                    p.getAxisFormat(), p.getBeginRange(), p.getEndRange(), p.getMdxSupport(), p.getProviderName(),
                    p.getProviderVersion(), p.getDbmsVersion(), p.getProviderType(), p.getShowHiddenCubes(),
                    p.getSqlSupport(), p.getTransactionDDL(), p.getMaximumRows(), p.getRoles(), p.getVisualMode(),
                    p.getEffectiveRoles(), p.getEffectiveUserName(), p.getServerName(), p.getCatalogLocation(),
                    p.getDbpropCatalogTerm(), p.getDbpropCatalogUsage(), p.getDbpropColumnDefinition(),
                    p.getDbpropConcatNullBehavior(), p.getDbpropDataSourceReadOnly(), p.getDbpropGroupBy(),
                    p.getDbpropHeterogeneousTables(), p.getDbpropIdentifierCase(), p.getDbpropMaxIndexSize(),
                    p.getDbpropMaxOpenChapters(), p.getDbpropMaxRowSize(), p.getDbpropMaxRowSizeIncludeBlob(),
                    p.getDbpropMaxTablesInSelect(), p.getDbpropMultiTableUpdate(), p.getDbpropNullCollation(),
                    p.getDbpropOrderByColumnsInSelect(), p.getDbpropOutputParameterAvailable(),
                    p.getDbpropPersistentIdType(), p.getDbpropPrepareAbortBehavior(),
                    p.getDbpropPrepareCommitBehavior(), p.getDbpropProcedureTerm(), p.getDbpropQuotedIdentifierCase(),
                    p.getDbpropSchemaUsage(), p.getDbpropSqlSupport(), p.getDbpropSubqueries(),
                    p.getDbpropSupportedTxnDdl(), p.getMdpropMdxSubqueries(), p.getDbpropSupportedTxnIsoLevels(),
                    p.getDbpropSupportedTxnIsoRetain(), p.getDbpropTableTerm(), p.getMdpropAggregateCellUpdate(),
                    p.getMdpropAxes(), p.getMdpropFlatteningSupport(), p.getMdpropMdxCaseSupport(),
                    p.getMdpropMdxDescFlags(), p.getMdpropMdxDrillFunctions(), p.getMdpropMdxFormulas(),
                    p.getMdpropMdxJoinCubes(), p.getMdpropMdxMemberFunctions(), p.getMdpropMdxNonMeasureExpressions(),
                    p.getMdpropMdxNumericFunctions(), p.getMdpropMdxObjQualification(), p.getMdpropMdxOuterReference(),
                    p.getMdpropMdxQueryByProperty(), p.getMdpropMdxRangeRowset(), p.getMdpropMdxSetFunctions(),
                    p.getMdpropMdxSlicer(), p.getMdpropMdxStringCompop(), p.getMdpropNamedLevels(),
                    p.getDbpropMsmdMDXCompatibility(), p.getDbpropMsmdSQLCompatibility(),
                    p.getDbpropMsmdMDXUniqueNameStyle(), p.getDbpropMsmdCachePolicy(), p.getDbpropMsmdCacheRatio(),
                    p.getDbpropMsmdCacheMode(), p.getDbpropMsmdCompareCaseSensitiveStringFlags(),
                    p.getDbpropMsmdCompareCaseNotSensitiveStringFlags(), p.getDbpropMsmdFlattened2(),
                    p.getDbpropInitMode(), p.getSspropInitAppName(), p.getSspropInitWsid(), p.getSspropInitPacketsize(),
                    p.getReadOnlySession(), p.getSecuredCellValue(), p.getNonEmptyThreshold(), p.getSafetyOptions(),
                    p.getDbpropMsmdCacheRatio2(), p.getDbpropMsmdUseFormulaCache(), p.getDbpropMsmdDynamicDebugLimit(),
                    p.getDbpropMsmdDebugMode(), p.getDialect(), p.getImpactAnalysis(), p.getSqlQueryMode(),
                    p.getClientProcessID(), p.getCube(), p.getReturnCellProperties(), p.getCommitTimeout(),
                    p.getForceCommitTimeout(), p.getExecutionMode(), p.getRealTimeOlap(), p.getMdxMissingMemberMode(),
                    p.getMdpropMdxNamedSets(), p.getDbpropMsmdSubqueries(), p.getDbpropMsmdAutoExists(),
                    p.getCustomData(), p.getDisablePrefetchFacts(), p.getUpdateIsolationLevel(),
                    p.getDbpropMsmdErrorMessageMode(), p.getMdpropMdxDdlExtensions(), p.getResponseEncoding(),
                    p.getMemoryLockingMode(), p.getDbpropMsmdOptimizeResponse(), p.getDbpropMsmdActivityID(),
                    p.getDbpropMsmdRequestID(), p.getReturnAffectedObjects(), p.getDbpropMsmdRequestMemoryLimit(),
                    p.getApplicationContext());
        }
        return null;
    }

    private static Restrictions convertRestrictions(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Restrictions restrictions) {
        if (restrictions != null) {
            return new RestrictionsR(restrictions.getRestrictionList());
        }
        return null;
    }

    private static NotifyTableChange convertNotifyTableChange(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.NotifyTableChange notifyTableChange) {
        if (notifyTableChange != null) {
            return new NotifyTableChangeR(convertObjectReference(notifyTableChange.getObject()),
                    convertTableNotificationList(notifyTableChange.getTableNotifications()));
        }
        return null;

    }

    private static List<TableNotification> convertTableNotificationList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.TableNotification> tableNotificationList) {
        if (tableNotificationList != null) {
            return tableNotificationList.stream().map(CommandConvertor::convertTableNotification).toList();
        }
        return List.of();
    }

    private static TableNotification convertTableNotification(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.TableNotification tableNotification) {
        if (tableNotification != null) {
            return new TableNotificationR(tableNotification.getDbTableName(),
                    Optional.ofNullable(tableNotification.getDbSchemaName()));
        }
        return null;
    }

    private static UpdateCells convertUpdateCells(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.UpdateCells updateCells) {
        if (updateCells != null) {
            return new UpdateCellsR(convertCellsList(updateCells.getCell()));
        }
        return null;
    }

    private static List<Cell> convertCellsList(List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Cell> cellList) {
        if (cellList != null) {
            return cellList.stream().map(CommandConvertor::convertCell).toList();
        }
        return List.of();
    }

    private static Cell convertCell(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Cell cell) {
        if (cell != null) {
            return new CellR(cell.getValue(), cell.getCellOrdinal());
        }
        return null;
    }

    private static Drop convertDrop(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Drop drop) {
        if (drop != null) {
            return new DropR(convertObject(drop.getObject()), drop.isDeleteWithDescendants(),
                    convertWhere(drop.getWhere()));
        }
        return null;

    }

    private static org.eclipse.daanse.xmla.api.xmla.XmlaObject convertObject(XmlaObject object) {
        if (object != null) {
            return new XmlaObjectR(object.getDatabase(), object.getCube(), object.getDimension());
        }
        return null;
    }

    private static Where convertWhere(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Where where) {
        if (where != null) {
            return new WhereR(convertWhereAttribute(where.getAttribute()));
        }
        return null;
    }

    private static WhereAttribute convertWhereAttribute(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.WhereAttribute attribute) {
        if (attribute != null) {
            return new WhereAttributeR(attribute.getAttributeName(),
                    attribute.getKeys());
        }
        return null;
    }

    private static Update convertUpdate(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Update update) {
        if (update != null) {
            return new UpdateR(convertObject(update.getObject()),
                    convertAttributeInsertUpdateList(
                            update.getAttributes()),
                    update.isMoveWithDescendants(), update.isMoveToRoot(), convertWhere(update.getWhere()));
        }
        return null;

    }

    private static List<AttributeInsertUpdate> convertAttributeInsertUpdateList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.AttributeInsertUpdate> attributeList) {
        if (attributeList != null) {
            return attributeList.stream().map(CommandConvertor::convertAttributeInsertUpdate).toList();
        }
        return List.of();
    }

    private static AttributeInsertUpdate convertAttributeInsertUpdate(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.AttributeInsertUpdate attributeInsertUpdate) {
        if (attributeInsertUpdate != null) {
            return new AttributeInsertUpdateR(attributeInsertUpdate.getAttributeName(), attributeInsertUpdate.getName(),
                    attributeInsertUpdate.getKeys(),
                    convertTranslationInsertUpdateList(attributeInsertUpdate.getTranslations()),
                    attributeInsertUpdate.getValue(), attributeInsertUpdate.getCustomrollup(),
                    attributeInsertUpdate.getCustomrollupproperties(), attributeInsertUpdate.getUnaryoperator(),
                    attributeInsertUpdate.getSkippedlevels());
        }
        return null;
    }

    private static List<TranslationInsertUpdate> convertTranslationInsertUpdateList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.TranslationInsertUpdate> translationList) {
        if (translationList != null) {
            return translationList.stream().map(CommandConvertor::convertTranslationInsertUpdate).toList();
        }
        return List.of();
    }

    private static TranslationInsertUpdate convertTranslationInsertUpdate(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.TranslationInsertUpdate translationInsertUpdate) {
        if (translationInsertUpdate != null) {
            return new TranslationInsertUpdateR(translationInsertUpdate.getLanguage(),
                    translationInsertUpdate.getName());
        }
        return null;
    }

    private static Insert convertInsert(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Insert insert) {
        if (insert != null) {
            return new InsertR(convertObject(insert.getObject()), convertAttributeInsertUpdateList(
                    insert.getAttributes()));
        }
        return null;
    }

    private static Detach convertDetach(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Detach detach) {
        if (detach != null) {
            return new DetachR(convertObjectReference(detach.getObject()), detach.getPassword());
        }
        return null;

    }

    private static Attach convertAttach(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Attach attach) {
        if (attach != null) {
            return new AttachR(attach.getFolder(), attach.getPassword(), attach.isAllowOverwrite(),
                    attach.getReadWriteMode());
        }
        return null;

    }

    private static Synchronize convertSynchronize(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Synchronize synchronize) {
        if (synchronize != null) {
            return new SynchronizeR(convertSource(synchronize.getSource()), synchronize.getSynchronizeSecurity(),
                    synchronize.isApplyCompression(), synchronize.getDbStorageLocation(), convertLocationList(
                            synchronize.getLocations()));
        }
        return null;

    }

    private static Source convertSource(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Source source) {
        if (source != null) {
            return new SourceR(convertObjectReference(source.getObject()), source.getConnectionString());
        }
        return null;
    }

    private static Restore convertRestore(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Restore restore) {
        if (restore != null) {
            return new RestoreR(restore.getDatabaseName(), restore.getDatabaseID(), restore.getFile(),
                    restore.getSecurity(), restore.isAllowOverwrite(), restore.getPassword(),
                    restore.getDbStorageLocation(), restore.getReadWriteMode(),
                    convertLocationList(restore.getLocations()));
        }
        return null;

    }

    private static List<Location> convertLocationList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Location> locationList) {
        if (locationList != null) {
            return locationList.stream().map(CommandConvertor::convertLocation).toList();
        }
        return List.of();
    }

    private static Location convertLocation(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Location location) {
        if (location != null) {
            return new LocationR(location.getDataSourceType(), location.getConnectionString(),
                    convertFolderList(location.getFolders()),
                    location.getFile(), location.getDataSourceID());
        }
        return null;
    }

    private static List<Folder> convertFolderList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Folder> folderList) {
        if (folderList != null) {
            return folderList.stream().map(CommandConvertor::convertFolder).toList();
        }
        return List.of();
    }

    private static Folder convertFolder(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Folder folder) {
        if (folder != null) {
            return new FolderR(folder.getOriginal(), folder.getNewPath());
        }
        return null;
    }

    private static Backup convertBackup(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Backup backup) {
        if (backup != null) {
            return new BackupR(convertObjectReference(backup.getObject()), backup.getFile(), backup.getSecurity(),
                    backup.isApplyCompression(), backup.isAllowOverwrite(), backup.getPassword(),
                    backup.isBackupRemotePartitions(), convertLocationBackupList(
                            backup.getLocations()));
        }
        return null;

    }

    private static List<LocationBackup> convertLocationBackupList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.LocationBackup> locationList) {
        if (locationList != null) {
            return locationList.stream().map(CommandConvertor::convertLocationBackup).toList();
        }
        return List.of();

    }

    private static LocationBackup convertLocationBackup(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.LocationBackup locationBackup) {
        if (locationBackup != null) {
            return new LocationBackupR(locationBackup.getFile(), locationBackup.getDataSourceID());
        }
        return null;
    }

    private static Unlock convertUnlock(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Unlock unlock) {
        if (unlock != null) {
            return new UnlockR(unlock.getID());
        }
        return null;

    }

    private static Lock convertLock(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Lock lock) {
        if (lock != null) {
            return new LockR(lock.getID(), convertObjectReference(lock.getObject()), lock.getMode());
        }
        return null;

    }

    private static RollbackTransaction convertRollbackTransaction(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.RollbackTransaction rollbackTransaction) {
        if (rollbackTransaction != null) {
            return new RollbackTransactionR();
        }
        return null;

    }

    private static CommitTransaction convertCommitTransaction(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.CommitTransaction commitTransaction) {
        if (commitTransaction != null) {
            return new CommitTransactionR(commitTransaction.getDurabilityGuarantee());
        }
        return null;

    }

    private static BeginTransaction convertBeginTransaction(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.BeginTransaction beginTransaction) {
        if (beginTransaction != null) {
            return new BeginTransactionR();
        }
        return null;

    }

    public static Cancel convertCancel(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Cancel cancel) {
        if (cancel != null) {
            return new CancelR(cancel.getConnectionID(), cancel.getSessionID(), cancel.getSPID(),
                    cancel.isCancelAssociated());
        }
        return null;

    }

    private static Unsubscribe convertUnsubscribe(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Unsubscribe unsubscribe) {
        if (unsubscribe != null) {
            return new UnsubscribeR(unsubscribe.getSubscriptionId());
        }
        return null;

    }

    private static Subscribe convertSubscribe(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Subscribe subscribe) {
        if (subscribe != null) {
            return new SubscribeR(convertObjectReference(subscribe.getObject()), subscribe.getSubscriptionId());
        }
        return null;

    }

    public static ClearCache convertClearCache(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ClearCache clearCache) {
        if (clearCache != null) {
            return new ClearCacheR(convertObjectReference(clearCache.getObject()));
        }
        return null;

    }

    private static DesignAggregations convertDesignAggregations(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DesignAggregations designAggregations) {
        if (designAggregations != null) {
            return new DesignAggregationsR(convertObjectReference(designAggregations.getObject()),
                    convertDuration(designAggregations.getTime()), designAggregations.getSteps(),
                    designAggregations.getOptimization(), designAggregations.getStorage(),
                    designAggregations.isMaterialize(),
                    designAggregations.getQueries());
        }
        return null;

    }

    private static MergePartitions convertMergePartitions(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.MergePartitions mergePartitions) {
        if (mergePartitions != null) {
            return new MergePartitionsR(
                    convertObjectReferencList(
                            mergePartitions.getSources()),
                    convertObjectReference(mergePartitions.getTarget()));
        }
        return null;
    }

    private static List<ObjectReference> convertObjectReferencList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ObjectReference> source) {
        if (source != null) {
            return source.stream().map(ObjectReferenceConvertor::convertObjectReference).toList();
        }
        return List.of();
    }

    private static Process convertProcess(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Process process) {
        if (process != null) {
            return new ProcessR(process.getType(), convertObjectReference(process.getObject()),
                    convertBindings(process.getBindings()), convertDataSource(process.getDataSource()),
                    convertDataSourceView(process.getDataSourceView()),
                    convertErrorConfiguration(process.getErrorConfiguration()),
                    convertWriteBackTableCreation(process.getWriteBackTableCreation()));
        }
        return null;
    }

    private static WriteBackTableCreation convertWriteBackTableCreation(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.WriteBackTableCreation writeBackTableCreation) {
        if (writeBackTableCreation != null) {
            return WriteBackTableCreation.fromValue(writeBackTableCreation.value());
        }
        return null;
    }

    public static ErrorConfiguration convertErrorConfiguration(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ErrorConfiguration errorConfiguration) {
        if (errorConfiguration != null) {
            return new ErrorConfigurationR(Optional.ofNullable(errorConfiguration.getKeyErrorLimit()),
                    Optional.ofNullable(errorConfiguration.getKeyErrorLogFile()),
                    Optional.ofNullable(errorConfiguration.getKeyErrorAction()),
                    Optional.ofNullable(errorConfiguration.getKeyErrorLimitAction()),
                    Optional.ofNullable(errorConfiguration.getKeyNotFound()),
                    Optional.ofNullable(errorConfiguration.getKeyDuplicate()),
                    Optional.ofNullable(errorConfiguration.getNullKeyConvertedToUnknown()),
                    Optional.ofNullable(errorConfiguration.getNullKeyNotAllowed()),
                    Optional.ofNullable(errorConfiguration.getCalculationError()));
        }
        return null;
    }

    public static DataSourceView convertDataSourceView(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataSourceView dataSourceView) {
        if (dataSourceView != null) {
            return new DataSourceViewR(dataSourceView.getName(), dataSourceView.getID(),
                    convertToInstant(dataSourceView.getCreatedTimestamp()),
                    convertToInstant(dataSourceView.getLastSchemaUpdate()), dataSourceView.getDescription(),
                    convertAnnotationList(dataSourceView.getAnnotations() == null ? null
                            : dataSourceView.getAnnotations()),
                    dataSourceView.getDataSourceID());
        }
        return null;
    }

    public static DataSource convertDataSource(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataSource dataSource) {
        if (dataSource != null) {
            return new DataSourceR(dataSource.getName(), dataSource.getID(),
                    convertToInstant(dataSource.getCreatedTimestamp()),
                    convertToInstant(dataSource.getLastSchemaUpdate()), dataSource.getDescription(),
                    convertAnnotationList(
                            dataSource.getAnnotations() == null ? null : dataSource.getAnnotations()),
                    dataSource.getManagedProvider(), dataSource.getConnectionString(),
                    dataSource.getConnectionStringSecurity(),
                    convertImpersonationInfo(dataSource.getImpersonationInfo()), dataSource.getIsolation(),
                    dataSource.getMaxActiveConnections(), convertDuration(dataSource.getTimeout()),
                    convertDataSourcePermissionList(dataSource.getDataSourcePermissions()),
                    convertImpersonationInfo(dataSource.getQueryImpersonationInfo()), dataSource.getQueryHints());
        }
        return null;
    }

    private static List<DataSourcePermission> convertDataSourcePermissionList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataSourcePermission> dataSourcePermissionList) {
        if (dataSourcePermissionList != null) {
            return dataSourcePermissionList.stream().map(CommandConvertor::convertDataSourcePermission).toList();
        }
        return List.of();
    }

    private static DataSourcePermission convertDataSourcePermission(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.DataSourcePermission dataSourcePermission) {
        if (dataSourcePermission != null) {
            return new DataSourcePermissionR(dataSourcePermission.getName(),
                    Optional.ofNullable(dataSourcePermission.getID()),
                    Optional.ofNullable(convertToInstant(dataSourcePermission.getCreatedTimestamp())),
                    Optional.ofNullable(convertToInstant(dataSourcePermission.getLastSchemaUpdate())),
                    Optional.ofNullable(dataSourcePermission.getDescription()),
                    Optional.ofNullable(convertAnnotationList(dataSourcePermission.getAnnotations() == null ? null
                            : dataSourcePermission.getAnnotations())),
                    dataSourcePermission.getRoleID(), Optional.ofNullable(dataSourcePermission.isProcess()),
                    Optional.ofNullable(ReadDefinitionEnum.fromValue(dataSourcePermission.getReadDefinition())),
                    Optional.ofNullable(ReadWritePermissionEnum.fromValue(dataSourcePermission.getRead())),
                    Optional.ofNullable(ReadWritePermissionEnum.fromValue(dataSourcePermission.getWrite())));
        }
        return null;
    }

    public static ImpersonationInfo convertImpersonationInfo(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.engine.ImpersonationInfo impersonationInfo) {
        if (impersonationInfo != null) {
            return new ImpersonationInfoR(impersonationInfo.getImpersonationMode(),
                    Optional.ofNullable(impersonationInfo.getAccount()),
                    Optional.ofNullable(impersonationInfo.getPassword()),
                    Optional.ofNullable(impersonationInfo.getImpersonationInfoSecurity()));
        }
        return null;
    }

    private static Bindings convertBindings(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Bindings bindings) {
        if (bindings != null) {
            return new BindingsR(convertOutOfLineBindingList(bindings.getBinding()));
        }
        return null;
    }

    private static List<OutOfLineBinding> convertOutOfLineBindingList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding> bindingList) {
        if (bindingList != null) {
            return bindingList.stream().map(CommandConvertor::convertOutOfLineBinding).toList();
        }
        return List.of();
    }

    private static OutOfLineBinding convertOutOfLineBinding(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding outOfLineBinding) {
        if (outOfLineBinding != null) {
            return new OutOfLineBindingR(outOfLineBinding.getDatabaseID(), outOfLineBinding.getDimensionID(),
                    outOfLineBinding.getCubeID(), outOfLineBinding.getMeasureGroupID(),
                    outOfLineBinding.getPartitionID(), outOfLineBinding.getMiningModelID(),
                    outOfLineBinding.getMiningStructureID(), outOfLineBinding.getAttributeID(),
                    outOfLineBinding.getCubeDimensionID(), outOfLineBinding.getMeasureID(),
                    outOfLineBinding.getParentColumnID(), outOfLineBinding.getColumnID(),
                    convertBinding(outOfLineBinding.getSource()),
                    convertBinding(outOfLineBinding.getNameColumn() == null ? null
                            : outOfLineBinding.getNameColumn().getSource()),
                    convertBinding(outOfLineBinding.getSkippedLevelsColumn() == null ? null
                            : outOfLineBinding.getSkippedLevelsColumn().getSource()),
                    convertBinding(outOfLineBinding.getCustomRollupColumn() == null ? null
                            : outOfLineBinding.getCustomRollupColumn().getSource()),
                    convertBinding(outOfLineBinding.getCustomRollupPropertiesColumn() == null ? null
                            : outOfLineBinding.getCustomRollupPropertiesColumn().getSource()),
                    convertBinding(outOfLineBinding.getValueColumn() == null ? null
                            : outOfLineBinding.getValueColumn().getSource()),
                    convertBinding(outOfLineBinding.getUnaryOperatorColumn() == null ? null
                            : outOfLineBinding.getUnaryOperatorColumn().getSource()),
                    convertOutOfLineBindingKeyColumns(outOfLineBinding.getKeyColumns()),
                    convertOutOfLineBindingForeignKeyColumns(outOfLineBinding.getForeignKeyColumns()),
                    convertOutOfLineBindingTranslations(outOfLineBinding.getTranslations()));
        }
        return null;

    }

    private static OutOfLineBinding.Translations convertOutOfLineBindingTranslations(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.Translations translations) {
        if (translations != null) {
            return new OutOfLineBindingR.Translations(
                    convertOutOfLineBindingTranslationsTranslationList(translations.getTranslation()));
        }
        return null;

    }

    private static List<OutOfLineBinding.Translations.Translation> convertOutOfLineBindingTranslationsTranslationList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.Translations.Translation> translationList) {
        if (translationList != null) {
            return translationList.stream().map(CommandConvertor::convertOutOfLineBindingTranslationsTranslation)
                    .toList();
        }
        return List.of();
    }

    private static OutOfLineBinding.Translations.Translation convertOutOfLineBindingTranslationsTranslation(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.Translations.Translation translation) {
        if (translation != null) {
            return new OutOfLineBindingR.Translations.Translation(translation.getLanguage(),
                    convertBinding(translation.getSource()));
        }
        return null;
    }

    private static List<Binding> convertOutOfLineBindingForeignKeyColumns(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.ForeignKeyColumns foreignKeyColumns) {
        if (foreignKeyColumns != null) {
            return convertOutOfLineBindingForeignKeyColumnsForeignKeyColumnList(
                    foreignKeyColumns.getForeignKeyColumn());
        }
        return List.of();

    }

    private static List<Binding> convertOutOfLineBindingForeignKeyColumnsForeignKeyColumnList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn> foreignKeyColumnList) {
        if (foreignKeyColumnList != null) {
            return foreignKeyColumnList.stream()
                    .map(CommandConvertor::convertOutOfLineBindingForeignKeyColumnsForeignKeyColumn).toList();
        }
        return List.of();
    }

    private static Binding convertOutOfLineBindingForeignKeyColumnsForeignKeyColumn(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.ForeignKeyColumns.ForeignKeyColumn foreignKeyColumn) {
        if (foreignKeyColumn != null) {
            return convertBinding(foreignKeyColumn.getSource());
        }
        return null;
    }

    private static List<Binding> convertOutOfLineBindingKeyColumns(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.KeyColumns keyColumns) {
        if (keyColumns != null) {
            return convertOutOfLineBindingKeyColumnsKeyColumnList(keyColumns.getKeyColumn());
        }
        return List.of();
    }

    private static List<Binding> convertOutOfLineBindingKeyColumnsKeyColumnList(
            List<org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.KeyColumns.KeyColumn> keyColumnList) {
        if (keyColumnList != null) {
            return keyColumnList.stream().map(CommandConvertor::convertOutOfLineBindingKeyColumnsKeyColumn).toList();
        }
        return List.of();
    }

    private static Binding convertOutOfLineBindingKeyColumnsKeyColumn(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.OutOfLineBinding.KeyColumns.KeyColumn keyColumn) {
        if (keyColumn != null) {
            return convertBinding(keyColumn.getSource());
        }
        return null;
    }

    private static Delete convertDelete(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Delete delete) {
        if (delete != null) {
            return new DeleteR(convertObjectReference(delete.getObject()), delete.isIgnoreFailures());
        }
        return null;
    }

    private static Alter convertAlter(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Alter alter) {
        if (alter != null) {
            return new AlterR(convertObjectReference(alter.getObject()),
                    convertMajorObject(alter.getObjectDefinition()), convertScope(alter.getScope()),
                    alter.isAllowCreate(), convertObjectExpansion(alter.getObjectExpansion()));
        }
        return null;
    }

    public static ObjectExpansion convertObjectExpansion(
            org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.ObjectExpansion objectExpansion) {
        if (objectExpansion != null) {
            return ObjectExpansion.fromValue(objectExpansion.value());
        }
        return null;
    }

    private static Create convertCreate(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Create create) {
        if (create != null) {
            return new CreateR(convertObjectReference(create.getParentObject()),
                    convertMajorObject(create.getObjectDefinition()), convertScope(create.getScope()),
                    create.isAllowOverwrite());
        }
        return null;
    }

    public static Scope convertScope(org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla.Scope scope) {
        if (scope != null) {
            return Scope.fromValue(scope.value());
        }
        return null;
    }

}
