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
package org.eclipse.daanse.xmla.model.jakarta.xml.bind.xmla;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Batch", propOrder = { "parallel", "bindings", "dataSource", "dataSourceView", "errorConfiguration",
        "create", "alter", "delete", "process", "mergePartitions", "designAggregations", "notifyTableChange", "insert",
        "update", "drop", "updateCells", "backup", "restore", "synchronize", "cancel", "beginTransaction",
        "commitTransaction", "rollbackTransaction", "clearCache", "subscribe", "unsubscribe", "detach", "attach",
        "lock", "unlock", "imageLoad", "imageSave", "cloneDatabase", "setAuthContext", "dbcc", "discover" })
public class Batch {

    @XmlElement(name = "Parallel")
    protected List<Batch.Parallel> parallel;
    @XmlElement(name = "Bindings")
    protected OutOfLineBinding bindings;
    @XmlElement(name = "DataSource")
    protected DataSource dataSource;
    @XmlElement(name = "DataSourceView")
    protected DataSourceView dataSourceView;
    @XmlElement(name = "ErrorConfiguration")
    protected ErrorConfiguration errorConfiguration;
    @XmlElement(name = "Create")
    protected List<Create> create;
    @XmlElement(name = "Alter")
    protected List<Alter> alter;
    @XmlElement(name = "Delete")
    protected List<Delete> delete;
    @XmlElement(name = "Process")
    protected List<Process> process;
    @XmlElement(name = "MergePartitions")
    protected List<MergePartitions> mergePartitions;
    @XmlElement(name = "DesignAggregations")
    protected List<DesignAggregations> designAggregations;
    @XmlElement(name = "NotifyTableChange")
    protected List<NotifyTableChange> notifyTableChange;
    @XmlElement(name = "Insert")
    protected List<Insert> insert;
    @XmlElement(name = "Update")
    protected List<Update> update;
    @XmlElement(name = "Drop")
    protected List<Drop> drop;
    @XmlElement(name = "UpdateCells")
    protected List<UpdateCells> updateCells;
    @XmlElement(name = "Backup")
    protected List<Backup> backup;
    @XmlElement(name = "Restore")
    protected List<Restore> restore;
    @XmlElement(name = "Synchronize")
    protected List<Synchronize> synchronize;
    @XmlElement(name = "Cancel")
    protected List<Cancel> cancel;
    @XmlElement(name = "BeginTransaction")
    protected List<BeginTransaction> beginTransaction;
    @XmlElement(name = "CommitTransaction")
    protected List<CommitTransaction> commitTransaction;
    @XmlElement(name = "RollbackTransaction")
    protected List<RollbackTransaction> rollbackTransaction;
    @XmlElement(name = "ClearCache")
    protected List<ClearCache> clearCache;
    @XmlElement(name = "Subscribe")
    protected List<Subscribe> subscribe;
    @XmlElement(name = "Unsubscribe")
    protected List<Unsubscribe> unsubscribe;
    @XmlElement(name = "Detach")
    protected List<Detach> detach;
    @XmlElement(name = "Attach")
    protected List<Attach> attach;
    @XmlElement(name = "Lock")
    protected List<Lock> lock;
    @XmlElement(name = "Unlock")
    protected List<Unlock> unlock;
    @XmlElement(name = "ImageLoad")
    protected List<ImageLoad> imageLoad;
    @XmlElement(name = "ImageSave")
    protected List<ImageSave> imageSave;
    @XmlElement(name = "CloneDatabase")
    protected List<CloneDatabase> cloneDatabase;
    @XmlElement(name = "SetAuthContext")
    protected List<SetAuthContext> setAuthContext;
    @XmlElement(name = "DBCC")
    protected DBCC dbcc;
    @XmlElement(name = "Discover", namespace = "urn:schemas-microsoft-com:xml-analysis")
    protected List<Discover> discover;
    @XmlAttribute(name = "Transaction")
    protected Boolean transaction;
    @XmlAttribute(name = "ProcessAffectedObjects")
    protected Boolean processAffectedObjects;

    public List<Batch.Parallel> getParallel() {
        return this.parallel;
    }

    public OutOfLineBinding getBindings() {
        return bindings;
    }

    public void setBindings(OutOfLineBinding value) {
        this.bindings = value;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource value) {
        this.dataSource = value;
    }

    public DataSourceView getDataSourceView() {
        return dataSourceView;
    }

    public void setDataSourceView(DataSourceView value) {
        this.dataSourceView = value;
    }

    public ErrorConfiguration getErrorConfiguration() {
        return errorConfiguration;
    }

    public void setErrorConfiguration(ErrorConfiguration value) {
        this.errorConfiguration = value;
    }

    public List<Create> getCreate() {
        return this.create;
    }

    public List<Alter> getAlter() {
        return this.alter;
    }

    public List<Delete> getDelete() {
        return this.delete;
    }

    public List<Process> getProcess() {
        return this.process;
    }

    public List<MergePartitions> getMergePartitions() {
        return this.mergePartitions;
    }

    public List<DesignAggregations> getDesignAggregations() {
        return this.designAggregations;
    }

    public List<NotifyTableChange> getNotifyTableChange() {
        return this.notifyTableChange;
    }

    public List<Insert> getInsert() {
        return this.insert;
    }

    public List<Update> getUpdate() {
        return this.update;
    }

    public List<Drop> getDrop() {
        return this.drop;
    }

    public List<UpdateCells> getUpdateCells() {
        return this.updateCells;
    }

    public List<Backup> getBackup() {
        return this.backup;
    }

    public List<Restore> getRestore() {
        return this.restore;
    }

    public List<Synchronize> getSynchronize() {
        return this.synchronize;
    }

    public List<Cancel> getCancel() {
        return this.cancel;
    }

    public List<BeginTransaction> getBeginTransaction() {
        return this.beginTransaction;
    }

    public List<CommitTransaction> getCommitTransaction() {
        return this.commitTransaction;
    }

    public List<RollbackTransaction> getRollbackTransaction() {
        return this.rollbackTransaction;
    }

    public List<ClearCache> getClearCache() {
        return this.clearCache;
    }

    public List<Subscribe> getSubscribe() {
        return this.subscribe;
    }

    public List<Unsubscribe> getUnsubscribe() {
        return this.unsubscribe;
    }

    public List<Detach> getDetach() {
        return this.detach;
    }

    public List<Attach> getAttach() {
        return this.attach;
    }

    public List<Lock> getLock() {
        return this.lock;
    }

    public List<Unlock> getUnlock() {
        return this.unlock;
    }

    public List<ImageLoad> getImageLoad() {
        return this.imageLoad;
    }

    public List<ImageSave> getImageSave() {
        return this.imageSave;
    }

    public List<CloneDatabase> getCloneDatabase() {
        return this.cloneDatabase;
    }

    public List<SetAuthContext> getSetAuthContext() {
        return this.setAuthContext;
    }

    public DBCC getDBCC() {
        return dbcc;
    }

    public void setDBCC(DBCC value) {
        this.dbcc = value;
    }

    public List<Discover> getDiscover() {
        return this.discover;
    }

    public boolean isTransaction() {
        return transaction;
    }

    public void setTransaction(boolean value) {
        this.transaction = value;
    }

    public boolean isProcessAffectedObjects() {
        return processAffectedObjects;
    }

    public void setProcessAffectedObjects(boolean value) {
        this.processAffectedObjects = value;
    }

    public void setParallel(List<Parallel> parallel) {
        this.parallel = parallel;
    }

    public void setCreate(List<Create> create) {
        this.create = create;
    }

    public void setAlter(List<Alter> alter) {
        this.alter = alter;
    }

    public void setDelete(List<Delete> delete) {
        this.delete = delete;
    }

    public void setProcess(List<Process> process) {
        this.process = process;
    }

    public void setMergePartitions(List<MergePartitions> mergePartitions) {
        this.mergePartitions = mergePartitions;
    }

    public void setDesignAggregations(List<DesignAggregations> designAggregations) {
        this.designAggregations = designAggregations;
    }

    public void setNotifyTableChange(List<NotifyTableChange> notifyTableChange) {
        this.notifyTableChange = notifyTableChange;
    }

    public void setInsert(List<Insert> insert) {
        this.insert = insert;
    }

    public void setUpdate(List<Update> update) {
        this.update = update;
    }

    public void setDrop(List<Drop> drop) {
        this.drop = drop;
    }

    public void setUpdateCells(List<UpdateCells> updateCells) {
        this.updateCells = updateCells;
    }

    public void setBackup(List<Backup> backup) {
        this.backup = backup;
    }

    public void setRestore(List<Restore> restore) {
        this.restore = restore;
    }

    public void setSynchronize(List<Synchronize> synchronize) {
        this.synchronize = synchronize;
    }

    public void setCancel(List<Cancel> cancel) {
        this.cancel = cancel;
    }

    public void setBeginTransaction(List<BeginTransaction> beginTransaction) {
        this.beginTransaction = beginTransaction;
    }

    public void setCommitTransaction(List<CommitTransaction> commitTransaction) {
        this.commitTransaction = commitTransaction;
    }

    public void setRollbackTransaction(List<RollbackTransaction> rollbackTransaction) {
        this.rollbackTransaction = rollbackTransaction;
    }

    public void setClearCache(List<ClearCache> clearCache) {
        this.clearCache = clearCache;
    }

    public void setSubscribe(List<Subscribe> subscribe) {
        this.subscribe = subscribe;
    }

    public void setUnsubscribe(List<Unsubscribe> unsubscribe) {
        this.unsubscribe = unsubscribe;
    }

    public void setDetach(List<Detach> detach) {
        this.detach = detach;
    }

    public void setAttach(List<Attach> attach) {
        this.attach = attach;
    }

    public void setLock(List<Lock> lock) {
        this.lock = lock;
    }

    public void setUnlock(List<Unlock> unlock) {
        this.unlock = unlock;
    }

    public void setImageLoad(List<ImageLoad> imageLoad) {
        this.imageLoad = imageLoad;
    }

    public void setImageSave(List<ImageSave> imageSave) {
        this.imageSave = imageSave;
    }

    public void setCloneDatabase(List<CloneDatabase> cloneDatabase) {
        this.cloneDatabase = cloneDatabase;
    }

    public void setSetAuthContext(List<SetAuthContext> setAuthContext) {
        this.setAuthContext = setAuthContext;
    }

    public void setDiscover(List<Discover> discover) {
        this.discover = discover;
    }

    public Boolean getTransaction() {
        return transaction;
    }

    public void setTransaction(Boolean transaction) {
        this.transaction = transaction;
    }

    public Boolean getProcessAffectedObjects() {
        return processAffectedObjects;
    }

    public void setProcessAffectedObjects(Boolean processAffectedObjects) {
        this.processAffectedObjects = processAffectedObjects;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "process" })
    public static class Parallel {

        @XmlElement(name = "Process")
        protected List<Process> process;
        @XmlAttribute(name = "MaxParallel")
        protected Integer maxParallel;

        public List<Process> getProcess() {
            return this.process;
        }

        public int getMaxParallel() {
            if (maxParallel == null) {
                return 0;
            } else {
                return maxParallel;
            }
        }

        public void setMaxParallel(int value) {
            this.maxParallel = value;
        }

        public void setProcess(List<Process> process) {
            this.process = process;
        }
    }

}
