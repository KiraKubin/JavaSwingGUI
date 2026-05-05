package com.javaSwingGUI;

// Student Name: [Your Name]
// Student ID:   [Your Student ID]
// File: CurrencyModel.java
// Description: Model class for Currency Converter - handles conversion logic (MVC Pattern)

public class CurrencyModel {

    // Exchange rates to JMD
    private static final double USD_TO_JMD = 129.02;
    private static final double CAD_TO_JMD = 97.50;
    private static final double EUR_TO_JMD = 164.33;

    /**
     * Converts a given amount from the specified currency to JMD.
     *
     * @param amount       the foreign currency amount
     * @param currencyType the currency type ("US", "CAN", "Euro")
     * @return the equivalent amount in JMD
     * @throws IllegalArgumentException if the currency type is unknown
     */
    public double convertToJMD(double amount, String currencyType) {
        switch (currencyType) {
            case "US":
                return amount * USD_TO_JMD;
            case "CAN":
                return amount * CAD_TO_JMD;
            case "Euro":
                return amount * EUR_TO_JMD;
            default:
                throw new IllegalArgumentException("Unknown currency type: " + currencyType);
        }
    }
}
