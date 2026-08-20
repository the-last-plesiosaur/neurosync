package dev.plesiosaur.gui;

import dev.plesiosaur.model.AppModel;
import dev.plesiosaur.model.AppModelObserver;
import dev.plesiosaur.model.Vault;

import javax.swing.*;
import java.awt.*;

public class MainStatusPanel extends JPanel implements AppModelObserver {

    private final JLabel statusLabel;

    public MainStatusPanel(AppModel m) {
        super();

        m.addObserver(this);

        setPreferredSize(new Dimension(1000, 25));
        setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        statusLabel = new JLabel("Vault: <None>");
        add(statusLabel);
    }

    @Override
    public void vaultOpened(Vault vault) {
        // need to distinguish between new vaults and opened vaults
        statusLabel.setText("Vault: <New>");
    }

    @Override
    public void vaultClosed(Vault vault) {
        statusLabel.setText("Vault: <None>");
    }
}
