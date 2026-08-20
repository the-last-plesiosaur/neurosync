package dev.plesiosaur.model;

import dev.plesiosaur.controller.CommandStack;

import java.util.ArrayList;
import java.util.List;

public class AppModel {

    private Vault vault;

    private final ShardList shardList;
    private final CommandStack commandStack;

    private final List<AppModelObserver> observers = new ArrayList<>();

    public AppModel() {
        this.shardList = new ShardList();
        this.commandStack = new CommandStack();
    }

    public void newVault() {
        if(vault != null) {
            // close vault
        }

        vault = new Vault();
        fireVaultOpened(vault);
    }

    public void openVault() {

    }

    public void closeVault() {
        fireVaultClosed(vault);
        vault = null;
    }

    public void addObserver(AppModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AppModelObserver observer) {
        observers.remove(observer);
    }

    private void fireVaultOpened(Vault v) {
        for (AppModelObserver observer : observers) {
            observer.vaultOpened(v);
        }
    }

    private void fireVaultClosed(Vault v) {
        for (AppModelObserver observer : observers) {
            observer.vaultClosed(v);
        }
    }

    public CommandStack getCommandStack() {
        return commandStack;
    }


    public ShardList getShardList() {
        return shardList;
    }
}
