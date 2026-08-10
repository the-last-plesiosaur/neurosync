package dev.plesiosaur.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppModel {

    public static String VAULT_OPEN = "VAULT_OPEN";

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private boolean isVaultOpen = false;

    public AppModel() { }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public boolean isVaultOpen() {
        return isVaultOpen;
    }

    public void setVaultOpen(boolean vaultOpen) {
        boolean previous = this.isVaultOpen;
        isVaultOpen = vaultOpen;
        propertyChangeSupport.firePropertyChange(VAULT_OPEN, previous, isVaultOpen);
    }
}
