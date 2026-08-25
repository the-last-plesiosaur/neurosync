package dev.plesiosaur.model;

public interface NeurosyncDocumentObserver {
    void vaultOpened(Vault vault);
    void vaultClosed(Vault vault);
}
