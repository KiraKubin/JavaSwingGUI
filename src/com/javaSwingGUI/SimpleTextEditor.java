package com.javaSwingGUI;

// Student Name: [Your Name]
// Student ID:   [Your Student ID]
// File: SimpleTextEditor.java
// Description: Simple Text Editor GUI with File menu (Open, Save, New, Quit)
//              Uses JTextArea, JMenuBar, FileReader, FileWriter with try-catch-finally.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SimpleTextEditor extends JFrame {

    private JTextArea  textArea;
    private JMenuBar   menuBar;
    private JMenu      fileMenu;
    private JMenuItem  newItem;
    private JMenuItem  openItem;
    private JMenuItem  saveItem;
    private JMenuItem  quitItem;

    public SimpleTextEditor() {
        setTitle("Simple Text Editor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // allows Currency Converter to stay open
        buildUI();
        setSize(600, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Builds the editor interface. */
    private void buildUI() {
        // ---- Text Area ----
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        // ---- Menu Bar ----
        menuBar  = new JMenuBar();
        fileMenu = new JMenu("File");

        newItem  = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        quitItem = new JMenuItem("Quit");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(quitItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // ---- Layout ----
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // ---- Listeners ----
        newItem.addActionListener(e -> handleNew());
        openItem.addActionListener(e -> handleOpen());
        saveItem.addActionListener(e -> handleSave());
        quitItem.addActionListener(e -> handleQuit());
    }

    /** Clears the text area for a new document. */
    private void handleNew() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Clear current content and start a new document?",
                "New Document",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            textArea.setText("");
        }
    }

    /**
     * Opens a .txt file using JFileChooser and reads content into the JTextArea.
     * Uses try-catch-finally to ensure the reader is always closed.
     */
    private void handleOpen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Text File");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(selectedFile));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                textArea.setText(sb.toString());
                setTitle("Simple Text Editor - " + selectedFile.getName());

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this,
                        "File not found: " + ex.getMessage(),
                        "Open Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error reading file: " + ex.getMessage(),
                        "Open Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Ensure stream is always closed
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        System.err.println("Failed to close reader: " + ex.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Saves the JTextArea content to a .txt file chosen via JFileChooser.
     * Uses try-catch-finally to ensure the writer is always closed.
     */
    private void handleSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Text File");
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Append .txt if missing
            if (!selectedFile.getName().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }

            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileWriter(selectedFile));
                writer.print(textArea.getText());
                setTitle("Simple Text Editor - " + selectedFile.getName());
                JOptionPane.showMessageDialog(this,
                        "File saved successfully!",
                        "Save", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error saving file: " + ex.getMessage(),
                        "Save Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Ensure stream is always closed
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    /** Closes the text editor window safely. */
    private void handleQuit() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit the text editor?",
                "Quit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}
