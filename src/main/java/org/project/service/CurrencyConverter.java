package org.project.service;

import org.project.exception.ApiException;
import org.project.model.ConversionRecord;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConverter {
    private final ExchangeRateService exchangeRateService;
    private final List<ConversionRecord> conversionHistory;

    public CurrencyConverter() {
        this.exchangeRateService = new ExchangeRateService();
        this.conversionHistory = new ArrayList<>();
    }

    public double convert(String from, String to, double amount) throws ApiException, ApiException {
        double result = exchangeRateService.convertCurrency(from, to, amount);
        conversionHistory.add(new ConversionRecord(from, to, amount, result));
        return result;
    }

    public List<ConversionRecord> getConversionHistory() {
        return conversionHistory;
    }
}