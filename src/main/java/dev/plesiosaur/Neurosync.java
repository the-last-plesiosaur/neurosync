package dev.plesiosaur;


import javax.swing.*;
import java.awt.*;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Neurosync {
    static void main() {
        SwingUtilities.invokeLater(Neurosync::showGui);
    }

    private static void showGui() {
        JFrame frame = new JFrame("Neurosync");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Vault");
        JMenu editMenu = new JMenu("Shard");
        JMenu importMenu = new JMenu("Import");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(importMenu);

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Close");

        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        frame.setJMenuBar(menuBar);

        JPanel shardPanel = shardTable();
        frame.getContentPane().add(shardPanel);

        /*
        JPanel sidePanel = sidebar();
        sidePanel.setPreferredSize(new Dimension(250, 0));

        JPanel mainPanel = mainPanel();
        //mainPanel.setMinimumSize(new Dimension(400, 0));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(sidePanel, BorderLayout.WEST);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel, detailsPanel());
        splitPane.setBorder(BorderFactory.createEmptyBorder());

        contentPanel.add(splitPane, BorderLayout.CENTER);

        frame.getContentPane().add(contentPanel);
         */

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel shardTable() {
        JPanel shards = new JPanel();
        shards.setLayout(new BorderLayout(5, 5));
        shards.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        String[] columnNames = new String[]{"Id", "Key", "Review", "Jacks", "Flagged"};
        Object[][] data = {
                {UUID.randomUUID(), "Vault A", ZonedDateTime.now(), 6, false},
                {UUID.randomUUID(), "Vault B", ZonedDateTime.now(), 2, true},
                {UUID.randomUUID(), "Vault C", ZonedDateTime.now(), 0, false},
                {UUID.randomUUID(), "Vault A", ZonedDateTime.now(), 1, true}
        };

        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {  return false; }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        shards.add(scrollPane, BorderLayout.CENTER);

        return shards;
    }

    private static JPanel sidebar() {
        JList<String> sidebar = new JList<>(
                new String[]{"Vault A", "Vault B", "Vault C"}
        );

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JLabel title =  new JLabel("Vaults");

        sidebar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(title, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(sidebar);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel mainPanel() {
        JList<String> sidebar = new JList<>(
                new String[]{"Shard A", "Shard B", "Shard C"}
        );

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JLabel title =  new JLabel("Shards");

        sidebar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(title, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(sidebar);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel detailsPanel() {
        JLabel title = new JLabel("Details");
        //JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel panel = new JPanel();
        panel.add(title);

        return panel;
    }
}
