package dev.plesiosaur.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Vault {

    private final UUID id;
    private final ZonedDateTime created;

    private boolean dirty;
    private String fileName;
    private ShardList shardList;

    private List<VaultObserver> observers = new ArrayList<>();

    public Vault() {
        id = UUID.randomUUID();
        created = ZonedDateTime.now();
        shardList = new ShardList();
        dirty = false;
        fileName = null;
    }

    public void addVaultObserver(VaultObserver observer) {
        observers.add(observer);
    }

    public void removeVaultObserver(VaultObserver observer) {
        observers.remove(observer);
    }

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }


    public ShardList getShardList() {
        return shardList;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
        for(VaultObserver observer : observers) {
            observer.dirtyChange(this);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public boolean hasFileName() {
        return fileName != null;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        for(VaultObserver observer : observers) {
            observer.fileNameChange(this);
        }
    }
}
