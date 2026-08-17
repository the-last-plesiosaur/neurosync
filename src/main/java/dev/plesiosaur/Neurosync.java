package dev.plesiosaur;


import dev.plesiosaur.controller.AppController;
import dev.plesiosaur.gui.MainMenuBar;
import dev.plesiosaur.gui.ShardTableModel;
import dev.plesiosaur.model.AppModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Neurosync {
    static void main() {
        SwingUtilities.invokeLater(Neurosync::showGui);
    }

    private static void showGui() {
        AppModel model = new AppModel();
        AppController controller = new AppController(model);

        JFrame frame = new JFrame("Neurosync");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        MainMenuBar mmb = new  MainMenuBar(frame, model, controller);
        frame.setJMenuBar(mmb);

        JPanel shardPanel = shardTable(model, controller);
        frame.getContentPane().add(shardPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel shardTable(AppModel model, AppController controller) {
        JPanel shards = new JPanel();
        shards.setLayout(new BorderLayout(5, 5));
        shards.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        ShardTableModel stm = new ShardTableModel(model.getShardList(), controller);

        JTable table = new JTable(stm);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);

        table.setAutoCreateRowSorter(true);


        JPopupMenu popup = tableMenu();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { handlePopup(e); }

            @Override
            public void mouseReleased(MouseEvent e) { handlePopup(e); }

            private void handlePopup(MouseEvent e) {
                if(e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if(row >= 0 && row < source.getRowCount()) {
                        if(!source.isRowSelected(row)) {
                            source.changeSelection(row, column, false, false);
                        }

                        popup.show(source, e.getX(), e.getY());
                    }
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        shards.add(scrollPane, BorderLayout.CENTER);

        return shards;
    }


    private static JPopupMenu tableMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem rekeyShard = new JMenuItem("Rekey");
        JMenuItem freezeShard = new JMenuItem("Freeze");
        JMenuItem flagShard = new JMenuItem("Flag");
        JMenuItem purgeShard = new JMenuItem("Purge");

        menu.add(rekeyShard);
        menu.add(freezeShard);
        menu.add(flagShard);
        menu.addSeparator();
        menu.add(purgeShard);

        return menu;
    }

}
