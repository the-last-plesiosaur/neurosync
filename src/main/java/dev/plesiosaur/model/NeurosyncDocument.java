package dev.plesiosaur.model;

import dev.plesiosaur.controller.CommandHistory;
import dev.plesiosaur.persistence.PersistenceEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NeurosyncDocument {

    private Vault vault;
    private File file;
    private final CommandHistory commandHistory;
    private final PersistenceEngine persistenceEngine;

    private final List<NeurosyncDocumentObserver> observers = new ArrayList<>();

    public NeurosyncDocument() {
        this.commandHistory = new CommandHistory();
        this.persistenceEngine = new PersistenceEngine();
    }

    public boolean hasFileName() {
        return file != null;
    }

    public String getFileName() {
        return file.getAbsolutePath();
    }

    public boolean isDirty() {
        return commandHistory.isDirty();
    }

    public void newVault() {
        vault = new Vault();
        fireVaultOpened(vault);
    }

    public void saveAsVault(File file) {
        persistenceEngine.saveAs(this.vault, file);
        commandHistory.markSaved();
        this.file = file;
        fireVaultSaved(vault);
    }

    public void saveVault() {
        persistenceEngine.saveAs(this.vault, this.file);
        commandHistory.markSaved();
        fireVaultSaved(vault);
    }

    public void openVault(File file) {
        vault = persistenceEngine.openVault(file);
        fireVaultOpened(vault);
    }

    public void closeVault() {
        Vault old = vault;
        vault = null;
        commandHistory.clear();
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
            observer.vaultOpened(this, v);
        }
    }

    private void fireVaultClosed(Vault v) {
        for (NeurosyncDocumentObserver observer : observers) {
            observer.vaultClosed(this, v);
        }
    }

    private void fireVaultSaved(Vault v) {
        for (NeurosyncDocumentObserver observer : observers) {
            observer.vaultSaved(this, v);
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
