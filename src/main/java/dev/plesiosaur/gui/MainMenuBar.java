package dev.plesiosaur.gui;

import dev.plesiosaur.model.AppModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuBar extends JMenuBar implements PropertyChangeListener {

    public static MainMenuBar create(AppModel model) {
        MainMenuBar menuBar = new MainMenuBar();

        model.addPropertyChangeListener(menuBar);

        JMenu vaultMenu = new JMenu("Vault");
        JMenu shardMenu = new JMenu("Shard");
        JMenu jackMenu = new JMenu("Signal Jack");

        menuBar.add(vaultMenu);
        menuBar.add(shardMenu);
        menuBar.add(jackMenu);

        JMenuItem vaultNewItem = new JMenuItem("New");
        JMenuItem vaultOpenItem = new JMenuItem("Open");
        JMenuItem vaultSaveItem = new JMenuItem("Save");
        JMenuItem vaultSaveAsItem = new JMenuItem("Save As");
        JMenuItem vaultUndoItem = new JMenuItem("Undo");
        JMenuItem vaultRedoItem = new JMenuItem("Redo");
        JMenuItem vaultCloseItem = new JMenuItem("Close");


        vaultNewItem.addActionListener(e -> {
           IO.println("Create New Vault");
           model.setVaultOpen(true);
        });

        vaultCloseItem.addActionListener(e -> {
            System.exit(0);
        });

        vaultMenu.add(vaultNewItem);
        vaultMenu.add(vaultOpenItem);
        vaultMenu.add(vaultSaveItem);
        vaultMenu.add(vaultSaveAsItem);
        vaultMenu.addSeparator();
        vaultMenu.add(vaultUndoItem);
        vaultMenu.add(vaultRedoItem);
        vaultMenu.addSeparator();
        vaultMenu.add(vaultCloseItem);

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
        //shardMenu.setEnabled(false);

        JMenuItem signalJackQueued = new JMenuItem("Queued");
        JMenuItem signalJackQueuedByKey = new JMenuItem("Queued by Key");
        JMenuItem signalJackByKey = new JMenuItem("By Key");
        JMenuItem signalJackAll = new JMenuItem("All");

        jackMenu.add(signalJackQueued);
        jackMenu.add(signalJackQueuedByKey);
        jackMenu.add(signalJackByKey);
        jackMenu.add(signalJackAll);

        return menuBar;
    }

    private MainMenuBar() {
        super();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        IO.println("Vaulted opened");
    }
}
