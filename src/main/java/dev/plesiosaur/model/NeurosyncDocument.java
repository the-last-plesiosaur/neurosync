package dev.plesiosaur.model;

import dev.plesiosaur.controller.CommandHistory;

import java.util.ArrayList;
import java.util.List;

public class NeurosyncDocument {

    private Vault vault;
    private final CommandHistory commandHistory;

    private final List<NeurosyncDocumentObserver> observers = new ArrayList<>();

    public NeurosyncDocument() {
        this.commandHistory = new CommandHistory();
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

    public void addObserver(NeurosyncDocumentObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NeurosyncDocumentObserver observer) {
        observers.remove(observer);
    }

    private void fireVaultOpened(Vault v) {
        for (NeurosyncDocumentObserver observer : observers) {
            observer.vaultOpened(v);
        }
    }

    private void fireVaultClosed(Vault v) {
        for (NeurosyncDocumentObserver observer : observers) {
            observer.vaultClosed(v);
        }
    }

    public CommandHistory getCommandStack() {
        return commandHistory;
    }

    public boolean hasVault() {
        return vault != null;
    }

    public Vault getVault() {
        return vault;
    }
}
