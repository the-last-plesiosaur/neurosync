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
        frame.setSize(800, 600);

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

        frame.getContentPane().add(sidePanel);

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
        panel.add(new JScrollPane(sidebar), BorderLayout.CENTER);

        return panel;
    }
}
