package com.javaSwingGUI;

// Student Name: [Your Name]
// Student ID:   [Your Student ID]
// File: CurrencyController.java
// Description: Controller class for Currency Converter (MVC Pattern)

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CurrencyController {

    private CurrencyModel model;
    private CurrencyView  view;

    /**
     * Constructs the controller, wires up the model and view,
     * and attaches button listeners.
     */
    public CurrencyController(CurrencyModel model, CurrencyView view) {
        this.model = model;
        this.view  = view;

        // Attach action listeners
        this.view.addConvertListener(e -> handleConvert());
        this.view.addClearListener(e -> handleClear());
    }

    /** Reads inputs, validates, converts, updates view, and saves history. */
    private void handleConvert() {
        String inputText     = view.getInputAmount();
        String currencyType  = view.getSelectedCurrency();

        // Validate empty input
        if (inputText == null || inputText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter an amount to convert.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double amount    = Double.parseDouble(inputText.trim());
            double jmdResult = model.convertToJMD(amount, currencyType);

            // Format to 2 decimal places
            String resultText = String.format("%.2f", jmdResult);
            view.setJMDResult(resultText);

            // Persist to file
            saveToHistory(amount, currencyType, jmdResult);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid input! Please enter a numeric value.",
                    "Number Format Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Resets all fields in the view. */
    private void handleClear() {
        view.clearFields();
    }

    /**
     * Appends a successful conversion record to conversion_history.txt.
     *
     * @param amount       input foreign-currency amount
     * @param currency     the currency type string
     * @param jmdResult    the calculated JMD result
     */
    private void saveToHistory(double amount, String currency, double jmdResult) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("conversion_history.txt", true))) {
            pw.printf("Converted %.2f %s => %.2f JMD%n", amount, currency, jmdResult);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not write to conversion_history.txt:\n" + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
