package org.project.main;

import org.project.model.Currency;
import org.project.model.ConversionRecord;
import org.project.service.CurrencyConverter;
import org.project.service.ExchangeRateService;
import org.project.exception.ApiException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final CurrencyConverter currencyConverter;
    private final ExchangeRateService exchangeRateService;

    public Menu() {
        this.currencyConverter = new CurrencyConverter();
        this.exchangeRateService = new ExchangeRateService();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*************** Conversor de Monedas ***************");
            System.out.println("1. Listar Codigos de Monedas Disponibles");
            System.out.println("2. Convertir Moneda");
            System.out.println("3. Ver Historial de Conversiones");
            System.out.println("4. Salir");
            System.out.println("*************** Conversiones Rápidas ***************");
            System.out.println("5. USD a COP");
            System.out.println("6. COP a USD");
            System.out.println("7. USD a ARS");
            System.out.println("8. ARS a USD");
            System.out.println("9. EUR a USD");
            System.out.println("10. USD a EUR");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    listCurrencies();
                    break;
                case 2:
                    convertCurrency(scanner);
                    break;
                case 3:
                    viewConversionHistory();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                case 5:
                    quickConvert("USD", "COP", scanner);
                    break;
                case 6:
                    quickConvert("COP", "USD", scanner);
                    break;
                case 7:
                    quickConvert("USD", "ARS", scanner);
                    break;
                case 8:
                    quickConvert("ARS", "USD", scanner);
                    break;
                case 9:
                    quickConvert("EUR", "USD", scanner);
                    break;
                case 10:
                    quickConvert("USD", "EUR", scanner);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void listCurrencies() {
        try {
            List<Currency> currencies = exchangeRateService.getSupportedCurrencies();
            currencies.forEach(currency -> System.out.println(currency.getCode() + " - " + currency.getName()));
        } catch (ApiException e) {
            System.out.println("Error al listar monedas: " + e.getMessage());
        }
    }

    private void convertCurrency(Scanner scanner) {
        try {
            System.out.print("Ingrese El codigo moneda de origen: ");
            String from = scanner.next().toUpperCase();
            System.out.print("Ingrese Codigo de la moneda de destino: ");
            String to = scanner.next().toUpperCase();
            double amount = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Ingrese la cantidad a convertir: ");
                    amount = scanner.nextDouble();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    scanner.next(); // Clear the invalid input
                }
            }

            double result = currencyConverter.convert(from, to, amount);
            System.out.println("\n*************** Resultado de la Conversión ***************");
            System.out.println("Convertido: " + result + " " + to);
            System.out.println("*******************************************************\n");
        } catch (ApiException e) {
            System.out.println("Error al convertir moneda: " + e.getMessage());
        }
    }

    private void quickConvert(String from, String to, Scanner scanner) {
        try {
            double amount = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Ingrese la cantidad a convertir: ");
                    amount = scanner.nextDouble();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    scanner.next(); // Clear the invalid input
                }
            }

            double result = currencyConverter.convert(from, to, amount);
            System.out.println("\n*************** Resultado de la Conversión ***************");
            System.out.println("Convertido: " + result + " " + to);
            System.out.println("*******************************************************\n");
        } catch (ApiException e) {
            System.out.println("Error al convertir moneda: " + e.getMessage());
        }
    }

    private void viewConversionHistory() {
        List<ConversionRecord> history = currencyConverter.getConversionHistory();
        if (history.isEmpty()) {
            System.out.println("No hay conversiones registradas.");
        } else {
            history.forEach(System.out::println);
        }
    }
}