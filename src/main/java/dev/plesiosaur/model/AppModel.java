package dev.plesiosaur.model;

import dev.plesiosaur.controller.CommandStack;

import java.util.ArrayList;
import java.util.List;

public class AppModel {

    private Vault vault;
    private final CommandStack commandStack;

    private final List<AppModelObserver> observers = new ArrayList<>();

    public AppModel() {
        this.commandStack = new CommandStack();
    }

    public void newVault() {
        vault = new Vault();
        fireVaultOpened(vault);
    }

    public void openVault() {

    }

    public void closeVault() {
        Vault old = vault;
        vault = null;
        fireVaultClosed(old);
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

    public boolean hasVault() {
        return vault != null;
    }

    public Vault getVault() {
        return vault;
    }
}
