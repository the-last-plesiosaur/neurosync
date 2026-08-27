package dev.plesiosaur.gui;

import dev.plesiosaur.model.Shard;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ShardEditWindow extends JDialog {


    public ShardEditWindow(JFrame parent, Shard shard) {
        super(parent, "Edit " + shard.getId(), true);

        setSize(new Dimension(800, 500));
        setLocationRelativeTo(parent);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel challengePanel = new JPanel();
        challengePanel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setText(shard.getChallengeText());
        JScrollPane scrollPane = new JScrollPane(textArea);
        challengePanel.add(scrollPane, BorderLayout.CENTER);

        JButton previewButton = new JButton("Preview");
        JButton commitButton = new JButton("Commit");
        commitButton.setEnabled(false);

        JButton revertButton = new JButton("Revert");
        revertButton.setEnabled(false);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { handleTextChange(); }

            @Override
            public void removeUpdate(DocumentEvent e) { handleTextChange(); }

            @Override
            public void changedUpdate(DocumentEvent e) { handleTextChange(); }

            private void handleTextChange() {
                if(textArea.getText().trim().equals(shard.getChallengeText())) {
                    commitButton.setEnabled(false);
                    revertButton.setEnabled(false);
                } else {
                    commitButton.setEnabled(true);
                    revertButton.setEnabled(true);
                }
            }
        });

        commitButton.addActionListener(e -> {
            String newChallengeText = textArea.getText().trim();
            shard.setChallengeText(newChallengeText);
            commitButton.setEnabled(false);
            revertButton.setEnabled(false);
        });

        revertButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to revert your changes?",
                    "Confirm Revert",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if(response == JOptionPane.YES_OPTION) {
                textArea.setText(shard.getChallengeText());
                commitButton.setEnabled(false);
                revertButton.setEnabled(false);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(previewButton);
        buttonPanel.add(commitButton);
        buttonPanel.add(revertButton);

        challengePanel.add(buttonPanel, BorderLayout.SOUTH);




        JPanel responsePanel = new JPanel();
        responsePanel.setLayout(new FlowLayout());
        responsePanel.add(new JLabel("Response"));

        tabbedPane.addTab("Challenge", challengePanel);
        tabbedPane.addTab("Response", responsePanel);
        add(tabbedPane);
    }



}
