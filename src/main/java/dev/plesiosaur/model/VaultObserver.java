package dev.plesiosaur.model;

public interface VaultObserver {
    void dirtyChange(Vault v);
    void fileNameChange(Vault v);
}
