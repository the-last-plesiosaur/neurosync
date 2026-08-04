package dev.plesiosaur;


import javax.swing.*;
import java.awt.*;

public class Neurosync {
    static void main() {
        SwingUtilities.invokeLater(Neurosync::showGui);
    }

    private static void showGui() {
        JFrame frame = new JFrame("Neurosync");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu importMenu = new JMenu("Import");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(importMenu);

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        frame.setJMenuBar(menuBar);

        JPanel sidePanel = sidebar();
        sidePanel.setPreferredSize(new Dimension(250, 0));

        JPanel mainPanel = mainPanel();
        //mainPanel.setMinimumSize(new Dimension(400, 0));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(sidePanel, BorderLayout.WEST);
        contentPanel.add(mainPanel, BorderLayout.CENTER);

        frame.getContentPane().add(contentPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
}
