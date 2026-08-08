package dev.plesiosaur.gui;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

    public static MainMenuBar create() {
        MainMenuBar menuBar = new MainMenuBar();

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

        JMenuItem signalJackStart = new JMenuItem("Start");
        jackMenu.add(signalJackStart);


        return menuBar;
    }

    private MainMenuBar() {
        super();
    }

}
