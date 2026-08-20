package dev.plesiosaur.model;

public interface AppModelObserver {
    void vaultOpened(Vault vault);
    void vaultClosed(Vault vault);
}
