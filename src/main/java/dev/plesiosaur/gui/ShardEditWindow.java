package dev.plesiosaur.gui;

import dev.plesiosaur.model.Shard;

import javax.swing.*;
import java.awt.*;

public class ShardEditWindow extends JDialog {


    public ShardEditWindow(JFrame parent, Shard shard) {
        super(parent, "Edit " + shard.getId(), true);

        setSize(new Dimension(800, 500));
        setLocationRelativeTo(parent);

        JTabbedPane tabbedPane = new JTabbedPane();


        JPanel challengePanel = new JPanel();
        challengePanel.setLayout(new FlowLayout());
        challengePanel.add(new JLabel("Challenge"));

        JPanel responsePanel = new JPanel();
        responsePanel.setLayout(new FlowLayout());
        responsePanel.add(new JLabel("Response"));

        tabbedPane.addTab("Challenge", challengePanel);
        tabbedPane.addTab("Response", responsePanel);
        add(tabbedPane);
    }



}
