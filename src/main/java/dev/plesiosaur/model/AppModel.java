package dev.plesiosaur.model;

import dev.plesiosaur.controller.CommandStack;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppModel {

    public static String VAULT_OPEN = "VAULT_OPEN";
    public static String VAULT_DIRTY = "VAULT_DIRTY";

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private boolean isVaultOpen = false;
    private boolean isVaultDirty = false;
    private final ShardList shardList;
    private final CommandStack commandStack;

    public AppModel() {
        this.shardList = new ShardList();
        this.commandStack = new CommandStack();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public CommandStack getCommandStack() {
        return commandStack;
    }

    public boolean isVaultOpen() {
        return isVaultOpen;
    }

    public void setVaultOpen(boolean vaultOpen) {
        boolean previous = isVaultOpen;
        isVaultOpen = vaultOpen;
        propertyChangeSupport.firePropertyChange(VAULT_OPEN, previous, isVaultOpen);
    }

    public boolean isVaultDirty() {
        return isVaultDirty;
    }

    public void setVaultDirty(boolean vaultDirty) {
        boolean previous = isVaultDirty;
        isVaultDirty = vaultDirty;
        propertyChangeSupport.firePropertyChange(VAULT_DIRTY, previous, isVaultDirty);
    }

    public ShardList getShardList() {
        return shardList;
    }
}
