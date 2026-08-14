package dev.plesiosaur.gui;

import dev.plesiosaur.AppController;
import dev.plesiosaur.model.AppModel;

import javax.swing.*;
import java.io.File;

public class MainMenuBar extends JMenuBar {

    public MainMenuBar(JFrame frame, AppModel model, AppController controller) {
        super();

        // Vault Menu

        JMenu vaultMenu = new JMenu("Vault");

        JMenuItem vaultNewItem = new JMenuItem("New...");
        JMenuItem vaultOpenItem = new JMenuItem("Open...");
        JMenuItem vaultSaveItem = new JMenuItem("Save");
        JMenuItem vaultSaveAsItem = new JMenuItem("Save As...");
        JMenuItem vaultCloseItem = new JMenuItem("Close");
        JMenuItem vaultUndoItem = new JMenuItem("Undo");
        JMenuItem vaultRedoItem = new JMenuItem("Redo");
        JMenuItem vaultExitItem = new JMenuItem("Exit Neurosync");

        vaultSaveItem.setEnabled(false);
        vaultSaveAsItem.setEnabled(false);
        vaultCloseItem.setEnabled(false);

        vaultUndoItem.setEnabled(false);
        vaultRedoItem.setEnabled(false);

        vaultNewItem.addActionListener(e -> {
            // Select directory before creation
            controller.newVault();
        });

        vaultCloseItem.addActionListener(e -> {
           // Check for dirty and confirm
           controller.closeVault();
        });

        vaultOpenItem.addActionListener(e -> {
           JFileChooser chooser = new JFileChooser();
            int selection = chooser.showOpenDialog(frame);
            if(selection == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                controller.openVault(file.getAbsolutePath());
            }
        });

        vaultExitItem.addActionListener(e -> {
            if(model.isVaultDirty()) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Vault has unsaved changes. Exit anyway?",
                        "Select an Option",
                        JOptionPane.YES_NO_OPTION);

                if(response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        });

        vaultMenu.add(vaultNewItem);
        vaultMenu.add(vaultOpenItem);
        vaultMenu.add(vaultSaveItem);
        vaultMenu.add(vaultSaveAsItem);
        vaultMenu.add(vaultCloseItem);
        vaultMenu.addSeparator();
        vaultMenu.add(vaultUndoItem);
        vaultMenu.add(vaultRedoItem);
        vaultMenu.addSeparator();
        vaultMenu.add(vaultExitItem);

        // Shard Menu

        JMenu shardMenu = new JMenu("Shard");
        shardMenu.setEnabled(false);

        JMenuItem shardNewItem = new JMenuItem("New");
        JMenuItem shardRekeyItem = new JMenuItem("Rekey");
        //JMenuItem shardFreezeItem = new JMenuItem("Freeze");
        JMenuItem shardImportItem = new JMenuItem("Import");
        JMenuItem shardPurgeItem = new JMenuItem("Purge");

        shardMenu.add(shardNewItem);
        shardMenu.add(shardImportItem);
        shardMenu.addSeparator();
        shardMenu.add(shardRekeyItem);
        shardMenu.addSeparator();
        shardMenu.add(shardPurgeItem);

        shardNewItem.addActionListener(e -> {
            controller.newShard();
        });

        // Signal Jack Menu

        JMenu jackMenu = new JMenu("Signal Jack");
        jackMenu.setEnabled(false);

        JMenuItem signalJackQueued = new JMenuItem("Queued");
        JMenuItem signalJackQueuedByKey = new JMenuItem("Queued by Key");
        JMenuItem signalJackByKey = new JMenuItem("By Key");
        JMenuItem signalJackAll = new JMenuItem("All");

        jackMenu.add(signalJackQueued);
        jackMenu.add(signalJackQueuedByKey);
        jackMenu.add(signalJackByKey);
        jackMenu.add(signalJackAll);

        // Add all menus to bar

        this.add(vaultMenu);
        this.add(shardMenu);
        this.add(jackMenu);

        // AppModel property changes

        model.addPropertyChangeListener(e -> {
          if(e.getPropertyName().equals(AppModel.VAULT_OPEN) && e.getNewValue().equals(true)) {
              shardMenu.setEnabled(true);
              jackMenu.setEnabled(true);
              vaultSaveItem.setEnabled(true);
              vaultSaveAsItem.setEnabled(true);
              vaultCloseItem.setEnabled(true);
          } else if (e.getPropertyName().equals(AppModel.VAULT_OPEN) && e.getNewValue().equals(false)) {
              shardMenu.setEnabled(false);
              jackMenu.setEnabled(false);
              vaultSaveItem.setEnabled(false);
              vaultSaveAsItem.setEnabled(false);
              vaultCloseItem.setEnabled(false);
          }
        });



    }


}
