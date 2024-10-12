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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Command", propOrder = { "statement", "create", "alter", "delete", "process", "mergePartitions",
        "designAggregations", "clearCache", "subscribe", "unsubscribe", "cancel", "beginTransaction",
        "commitTransaction", "rollbackTransaction", "lock", "unlock", "backup", "restore", "synchronize", "attach",
        "detach", "insert", "update", "drop", "updateCells", "notifyTableChange", "batch", "imageLoad", "imageSave",
        "cloneDatabase", "setAuthContext", "dbcc" })
public class Command {

    @XmlElement(name = "Statement")
    protected String statement;
    @XmlElement(name = "Create")
    protected Create create;
    @XmlElement(name = "Alter")
    protected Alter alter;
    @XmlElement(name = "Delete")
    protected Delete delete;
    @XmlElement(name = "Process")
    protected Process process;
    @XmlElement(name = "MergePartitions")
    protected MergePartitions mergePartitions;
    @XmlElement(name = "DesignAggregations")
    protected DesignAggregations designAggregations;
    @XmlElement(name = "ClearCache")
    protected ClearCache clearCache;
    @XmlElement(name = "Subscribe")
    protected Subscribe subscribe;
    @XmlElement(name = "Unsubscribe")
    protected Unsubscribe unsubscribe;
    @XmlElement(name = "Cancel")
    protected Cancel cancel;
    @XmlElement(name = "BeginTransaction")
    protected BeginTransaction beginTransaction;
    @XmlElement(name = "CommitTransaction")
    protected CommitTransaction commitTransaction;
    @XmlElement(name = "RollbackTransaction")
    protected RollbackTransaction rollbackTransaction;
    @XmlElement(name = "Lock")
    protected Lock lock;
    @XmlElement(name = "Unlock")
    protected Unlock unlock;
    @XmlElement(name = "Backup")
    protected Backup backup;
    @XmlElement(name = "Restore")
    protected Restore restore;
    @XmlElement(name = "Synchronize")
    protected Synchronize synchronize;
    @XmlElement(name = "Attach")
    protected Attach attach;
    @XmlElement(name = "Detach")
    protected Detach detach;
    @XmlElement(name = "Insert")
    protected Insert insert;
    @XmlElement(name = "Update")
    protected Update update;
    @XmlElement(name = "Drop")
    protected Drop drop;
    @XmlElement(name = "UpdateCells")
    protected UpdateCells updateCells;
    @XmlElement(name = "NotifyTableChange")
    protected NotifyTableChange notifyTableChange;
    @XmlElement(name = "Batch")
    protected Batch batch;
    @XmlElement(name = "ImageLoad")
    protected ImageLoad imageLoad;
    @XmlElement(name = "ImageSave")
    protected ImageSave imageSave;
    @XmlElement(name = "CloneDatabase")
    protected CloneDatabase cloneDatabase;
    @XmlElement(name = "SetAuthContext")
    protected SetAuthContext setAuthContext;
    @XmlElement(name = "DBCC")
    protected DBCC dbcc;

    public String getStatement() {
        return statement;
    }

    public void setStatement(String value) {
        this.statement = value;
    }

    public Create getCreate() {
        return create;
    }

    public void setCreate(Create value) {
        this.create = value;
    }

    public Alter getAlter() {
        return alter;
    }

    public void setAlter(Alter value) {
        this.alter = value;
    }

    public Delete getDelete() {
        return delete;
    }

    public void setDelete(Delete value) {
        this.delete = value;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process value) {
        this.process = value;
    }

    public MergePartitions getMergePartitions() {
        return mergePartitions;
    }

    public void setMergePartitions(MergePartitions value) {
        this.mergePartitions = value;
    }

    public DesignAggregations getDesignAggregations() {
        return designAggregations;
    }

    public void setDesignAggregations(DesignAggregations value) {
        this.designAggregations = value;
    }

    public ClearCache getClearCache() {
        return clearCache;
    }

    public void setClearCache(ClearCache value) {
        this.clearCache = value;
    }

    public Subscribe getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Subscribe value) {
        this.subscribe = value;
    }

    public Unsubscribe getUnsubscribe() {
        return unsubscribe;
    }

    public void setUnsubscribe(Unsubscribe value) {
        this.unsubscribe = value;
    }

    public Cancel getCancel() {
        return cancel;
    }

    public void setCancel(Cancel value) {
        this.cancel = value;
    }

    public BeginTransaction getBeginTransaction() {
        return beginTransaction;
    }

    public void setBeginTransaction(BeginTransaction value) {
        this.beginTransaction = value;
    }

    public CommitTransaction getCommitTransaction() {
        return commitTransaction;
    }

    public void setCommitTransaction(CommitTransaction value) {
        this.commitTransaction = value;
    }

    public RollbackTransaction getRollbackTransaction() {
        return rollbackTransaction;
    }

    public void setRollbackTransaction(RollbackTransaction value) {
        this.rollbackTransaction = value;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock value) {
        this.lock = value;
    }

    public Unlock getUnlock() {
        return unlock;
    }

    public void setUnlock(Unlock value) {
        this.unlock = value;
    }

    public Backup getBackup() {
        return backup;
    }

    public void setBackup(Backup value) {
        this.backup = value;
    }

    public Restore getRestore() {
        return restore;
    }

    public void setRestore(Restore value) {
        this.restore = value;
    }

    public Synchronize getSynchronize() {
        return synchronize;
    }

    public void setSynchronize(Synchronize value) {
        this.synchronize = value;
    }

    public Attach getAttach() {
        return attach;
    }

    public void setAttach(Attach value) {
        this.attach = value;
    }

    public Detach getDetach() {
        return detach;
    }

    public void setDetach(Detach value) {
        this.detach = value;
    }

    public Insert getInsert() {
        return insert;
    }

    public void setInsert(Insert value) {
        this.insert = value;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update value) {
        this.update = value;
    }

    public Drop getDrop() {
        return drop;
    }

    public void setDrop(Drop value) {
        this.drop = value;
    }

    public UpdateCells getUpdateCells() {
        return updateCells;
    }

    public void setUpdateCells(UpdateCells value) {
        this.updateCells = value;
    }

    public NotifyTableChange getNotifyTableChange() {
        return notifyTableChange;
    }

    public void setNotifyTableChange(NotifyTableChange value) {
        this.notifyTableChange = value;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch value) {
        this.batch = value;
    }

    public ImageLoad getImageLoad() {
        return imageLoad;
    }

    public void setImageLoad(ImageLoad value) {
        this.imageLoad = value;
    }

    public ImageSave getImageSave() {
        return imageSave;
    }

    public void setImageSave(ImageSave value) {
        this.imageSave = value;
    }

    public CloneDatabase getCloneDatabase() {
        return cloneDatabase;
    }

    public void setCloneDatabase(CloneDatabase value) {
        this.cloneDatabase = value;
    }

    public SetAuthContext getSetAuthContext() {
        return setAuthContext;
    }

    public void setSetAuthContext(SetAuthContext value) {
        this.setAuthContext = value;
    }

    public DBCC getDBCC() {
        return dbcc;
    }

    public void setDBCC(DBCC value) {
        this.dbcc = value;
    }
}
