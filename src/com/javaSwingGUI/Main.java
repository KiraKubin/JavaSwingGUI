package com.javaSwingGUI;
// Student Name: [Your Name]
// Student ID:   [Your Student ID]
// File: Main.java
// Description: Entry point – launches the Currency Converter and the Simple Text Editor.

import com.javaSwingGUI.CurrencyController;
import com.javaSwingGUI.CurrencyModel;
import com.javaSwingGUI.CurrencyView;
import com.javaSwingGUI.SimpleTextEditor;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Run GUI creation on the Event Dispatch Thread (EDT) as per Swing best practice
        SwingUtilities.invokeLater(() -> {
            // ---- Part 1: Currency Converter (MVC) ----
            CurrencyModel model      = new CurrencyModel();
            CurrencyView view       = new CurrencyView();
            CurrencyController controller = new CurrencyController(model, view);

            // ---- Part 2: Simple Text Editor ----
            new SimpleTextEditor();
        });
    }
}
