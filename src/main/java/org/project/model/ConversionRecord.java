package org.project.model;

import java.time.LocalDateTime;

public class ConversionRecord {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double result;
    private LocalDateTime timestamp;

    public ConversionRecord(String fromCurrency, String toCurrency, double amount, double result) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("Converted %.2f %s to %.2f %s at %s", amount, fromCurrency, result, toCurrency, timestamp);
    }
}