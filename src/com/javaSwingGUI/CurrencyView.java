package com.javaSwingGUI;

// Student Name: [Your Name]
// Student ID:   [Your Student ID]
// File: CurrencyView.java
// Description: View class for Currency Converter GUI (MVC Pattern)

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CurrencyView extends JFrame {

    // --- Components ---
    private JTextField  inputField;
    private JComboBox<String> currencyCombo;
    private JTextField  jmdResultField;
    private JButton     convertButton;
    private JButton     clearButton;

    public CurrencyView() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        buildUI();
        pack();
        setLocationRelativeTo(null); // centre on screen
        setVisible(true);
    }

    /** Constructs and arranges all Swing components. */
    private void buildUI() {
        // ---- Main panel with GridLayout for labels + fields ----
        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));

        // Row 1 – Input amount
        gridPanel.add(new JLabel("Input $:"));
        inputField = new JTextField(15);
        gridPanel.add(inputField);

        // Row 2 – Currency type
        gridPanel.add(new JLabel("Currency Type:"));
        currencyCombo = new JComboBox<>(new String[]{"US", "CAN", "Euro"});
        gridPanel.add(currencyCombo);

        // Row 3 – JMD result (read-only)
        gridPanel.add(new JLabel("JMD Amount $:"));
        jmdResultField = new JTextField(15);
        jmdResultField.setEditable(false);
        gridPanel.add(jmdResultField);

        // ---- Button panel ----
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 8));
        convertButton = new JButton("Convert");
        clearButton   = new JButton("Clear");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        // ---- Assemble frame ----
        setLayout(new BorderLayout());
        add(gridPanel,   BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // ---- Getters used by Controller ----

    public String getInputAmount() {
        return inputField.getText();
    }

    public String getSelectedCurrency() {
        return (String) currencyCombo.getSelectedItem();
    }

    public void setJMDResult(String result) {
        jmdResultField.setText(result);
    }

    /** Resets all fields and returns combo to first item. */
    public void clearFields() {
        inputField.setText("");
        jmdResultField.setText("");
        currencyCombo.setSelectedIndex(0);
    }

    // ---- Listener registration methods ----

    public void addConvertListener(ActionListener listener) {
        convertButton.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }
}

