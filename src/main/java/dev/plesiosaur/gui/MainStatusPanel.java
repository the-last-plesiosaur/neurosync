package dev.plesiosaur.gui;

import dev.plesiosaur.model.NeurosyncDocument;
import dev.plesiosaur.model.NeurosyncDocumentObserver;
import dev.plesiosaur.model.Vault;
import dev.plesiosaur.model.VaultObserver;

import javax.swing.*;
import java.awt.*;

public class MainStatusPanel extends JPanel implements NeurosyncDocumentObserver, VaultObserver {

    private final JLabel statusLabel;

    public MainStatusPanel(NeurosyncDocument m) {
        super();

        m.addObserver(this);

        setPreferredSize(new Dimension(1000, 25));
        setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        statusLabel = new JLabel(generateMessage(null));
        add(statusLabel);
    }

    private String generateMessage(Vault v) {
        String s = "Vault: ";

        if(v == null) {
            s += "<NONE>";
        } else {
            if(v.hasFileName()) {
                s += v.getFileName();
            } else {
                s+= "<NEW>";
            }

            if(v.isDirty()) {
                s += " (dirty)";
            }
        }

        return s;
    }

    @Override
    public void vaultOpened(Vault vault) {
        vault.addVaultObserver(this);
        statusLabel.setText(generateMessage(vault));
    }

    @Override
    public void vaultClosed(Vault vault) {
        vault.removeVaultObserver(this);
        statusLabel.setText(generateMessage(null));
    }

    @Override
    public void dirtyChange(Vault v) {
        statusLabel.setText(generateMessage(v));
    }

    @Override
    public void fileNameChange(Vault v) {
        statusLabel.setText(generateMessage(v));
    }
}
