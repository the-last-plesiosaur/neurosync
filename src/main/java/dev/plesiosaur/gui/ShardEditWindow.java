package dev.plesiosaur.gui;

import dev.plesiosaur.model.Shard;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ShardEditWindow extends JDialog implements WindowListener {

    private boolean unsavedChanges = false;


    public ShardEditWindow(JFrame parent, Shard shard) {
        super(parent, shard.getId().toString(), true);

        setSize(new Dimension(800, 500));
        setLocationRelativeTo(parent);


        JMenuBar menuBar = new JMenuBar();
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem closeWindow = new JMenuItem("Close");
        closeWindow.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        editMenu.add(closeWindow);

        setJMenuBar(menuBar);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel challengePanel = new JPanel();
        challengePanel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setTabSize(4);
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
                    unsavedChanges = false;
                    commitButton.setEnabled(false);
                    revertButton.setEnabled(false);
                } else {
                    unsavedChanges = true;
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
            unsavedChanges = false;
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

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(unsavedChanges) {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "You have uncommitted edits. Do you want to close the editor?",
                    "Uncommited Edits",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                dispose();
            }
        } else {
            dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
