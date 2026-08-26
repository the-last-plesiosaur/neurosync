package dev.plesiosaur.gui;

import dev.plesiosaur.controller.AppController;
import dev.plesiosaur.model.NeurosyncDocument;
import dev.plesiosaur.model.NeurosyncDocumentObserver;
import dev.plesiosaur.model.Vault;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainMenuBar extends JMenuBar {

    public MainMenuBar(JFrame frame, NeurosyncDocument document, AppController controller) {
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

        vaultUndoItem.addActionListener(e -> {
           document.getCommandStack().undo();
        });

        vaultRedoItem.addActionListener(e -> {
            document.getCommandStack().redo();
        });

        document.getCommandStack().addObserver(cs -> {
            vaultUndoItem.setEnabled(cs.canUndo());
            vaultRedoItem.setEnabled(cs.canRedo());
        });

        vaultNewItem.addActionListener(e -> {
            // Select directory before creation
            controller.newVault();
        });

        vaultCloseItem.addActionListener(e -> {
           // Check for dirty and confirm
           controller.closeVault();
        });


        vaultSaveAsItem.addActionListener(e -> {
           JFileChooser chooser = new JFileChooser();
           int selection = chooser.showSaveDialog(frame);
           if(selection == JFileChooser.APPROVE_OPTION) {
               File file = chooser.getSelectedFile();
               controller.saveAsVault(file);
           }
        });

        vaultSaveItem.addActionListener(e -> {
            if(document.hasFileName()) {
                controller.saveVault();
            } else {
                JFileChooser chooser = new JFileChooser();
                int selection = chooser.showSaveDialog(frame);
                if(selection == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    controller.saveAsVault(file);
                }
            }
        });


        vaultOpenItem.addActionListener(e -> {
           JFileChooser chooser = new JFileChooser();
            int selection = chooser.showOpenDialog(frame);
            if(selection == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                controller.openVault(file);
            }
        });

        vaultExitItem.addActionListener(e -> {
            /*
            if(document.isVaultDirty()) {
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
             */

            System.exit(0);
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

        shardRekeyItem.addActionListener(e -> {
           JPanel panel = new JPanel();
           panel.setLayout(new GridLayout(2, 2, 5, 5));

            panel.add(new JLabel("Shard Key:"));
            String[] keyOptions = document.getVault().getShardList().getUniqueKeys().toArray(String[]::new);
            JComboBox<String> keyField = new JComboBox<>(keyOptions);
            panel.add(keyField);

            panel.add(new JLabel("New Shard Key:"));
            JTextField newKeyField = new JTextField();
            panel.add(newKeyField);

            int result = JOptionPane.showConfirmDialog(
                    frame,
                    panel,
                    "Shards to Rekey",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if(result == JOptionPane.OK_OPTION) {
                controller.rekeyShardsByKey(keyField.getSelectedItem().toString(), newKeyField.getText());
            }
        });

        shardImportItem.addActionListener(e -> {
            showNotImplementedError(frame);
        });

        shardPurgeItem.addActionListener(e -> {
           JPanel panel = new JPanel();
           panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
           panel.add(new JLabel("Shard Key:"));

           String[] keyOptions = document.getVault().getShardList().getUniqueKeys().toArray(String[]::new);
           JComboBox<String> keyField = new JComboBox<>(keyOptions);
           panel.add(keyField);

           int result = JOptionPane.showConfirmDialog(
                   frame,
                   panel,
                   "Shards to Purge",
                   JOptionPane.OK_CANCEL_OPTION,
                   JOptionPane.PLAIN_MESSAGE
           );

           if(result == JOptionPane.OK_OPTION) {
               controller.purgeShardsByKey(keyField.getSelectedItem().toString());
           }
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

        signalJackQueued.addActionListener(e -> { showNotImplementedError(frame); });
        signalJackQueuedByKey.addActionListener(e -> { showNotImplementedError(frame); });
        signalJackByKey.addActionListener(e -> { showNotImplementedError(frame); });
        signalJackAll.addActionListener(e -> { showNotImplementedError(frame); });

        // Add all menus to bar

        this.add(vaultMenu);
        this.add(shardMenu);
        this.add(jackMenu);

        // AppModel property changes
        document.addObserver(new NeurosyncDocumentObserver() {
            @Override
            public void vaultOpened(NeurosyncDocument neurosyncDocument, Vault vault) {
                shardMenu.setEnabled(true);
                jackMenu.setEnabled(true);
                vaultNewItem.setEnabled(false);
                vaultOpenItem.setEnabled(false);
                vaultSaveItem.setEnabled(true);
                vaultSaveAsItem.setEnabled(true);
                vaultCloseItem.setEnabled(true);
            }

            @Override
            public void vaultClosed(NeurosyncDocument neurosyncDocument, Vault vault) {
                shardMenu.setEnabled(false);
                jackMenu.setEnabled(false);
                vaultNewItem.setEnabled(true);
                vaultOpenItem.setEnabled(true);
                vaultSaveItem.setEnabled(false);
                vaultSaveAsItem.setEnabled(false);
                vaultCloseItem.setEnabled(false);
            }


            @Override
            public void vaultSaved(NeurosyncDocument neurosyncDocument, Vault vault) {
                // noop
            }

        });
    }

    private void showNotImplementedError(JFrame frame) {
         JOptionPane.showMessageDialog(
                 frame,
                 "This feature not implemented yet",
                 "Not Implemented",
                 JOptionPane.ERROR_MESSAGE
         );

    }


}
