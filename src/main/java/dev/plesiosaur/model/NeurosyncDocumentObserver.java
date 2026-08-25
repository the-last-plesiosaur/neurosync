package dev.plesiosaur.model;

import java.io.File;

public interface NeurosyncDocumentObserver {
    void vaultOpened(NeurosyncDocument neurosyncDocument, Vault vault);
    void vaultClosed(NeurosyncDocument neurosyncDocument, Vault vault);
    void vaultSaved(NeurosyncDocument neurosyncDocument, Vault vault);
}
