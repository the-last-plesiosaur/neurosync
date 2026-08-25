package dev.plesiosaur.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Vault {

    private final UUID id;
    private final ZonedDateTime created;


    private ShardList shardList;

    private List<VaultObserver> observers = new ArrayList<>();

    public Vault() {
        id = UUID.randomUUID();
        created = ZonedDateTime.now();
        shardList = new ShardList();
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

}
