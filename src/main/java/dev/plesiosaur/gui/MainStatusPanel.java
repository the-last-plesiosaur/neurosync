package dev.plesiosaur.gui;

import dev.plesiosaur.controller.CommandHistory;
import dev.plesiosaur.controller.CommandHistoryObserver;
import dev.plesiosaur.model.NeurosyncDocument;
import dev.plesiosaur.model.NeurosyncDocumentObserver;
import dev.plesiosaur.model.Vault;
import dev.plesiosaur.model.VaultObserver;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainStatusPanel extends JPanel implements NeurosyncDocumentObserver, CommandHistoryObserver {

    private final JLabel statusLabel;
    private final NeurosyncDocument document;

    public MainStatusPanel(NeurosyncDocument d) {
        super();

        this.document = d;

        d.addObserver(this);
        d.getCommandStack().addObserver(this);

        setPreferredSize(new Dimension(1000, 25));
        setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        statusLabel = new JLabel(generateMessage(d));
        add(statusLabel);
    }

    private String generateMessage(NeurosyncDocument neurosyncDocument) {
        String s = "Vault: ";

        if(neurosyncDocument.getVault() == null) {
            s += "<NONE>";
        } else {
            if(neurosyncDocument.hasFileName()) {
                s += neurosyncDocument.getFileName();
            } else {
                s+= "<NEW>";
            }

            if(neurosyncDocument.isDirty()) {
                s += " (dirty)";
            }
        }

        return s;
    }

    @Override
    public void vaultOpened(NeurosyncDocument neurosyncDocument, Vault vault) {
        //vault.addVaultObserver(this);
        statusLabel.setText(generateMessage(neurosyncDocument));
    }

    @Override
    public void vaultClosed(NeurosyncDocument neurosyncDocument, Vault vault) {
        //vault.removeVaultObserver(this);
        statusLabel.setText(generateMessage(neurosyncDocument));
    }

    @Override
    public void vaultSaved(NeurosyncDocument neurosyncDocument, Vault vault) {
        statusLabel.setText(generateMessage(neurosyncDocument));
    }

    @Override
    public void commandStackChanged(CommandHistory cs) {
        statusLabel.setText(generateMessage(document));
    }
}
