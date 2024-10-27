package org.project.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.project.exception.ApiException;
import org.project.model.Currency;
import org.project.util.ConfigUtil;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeRateService {
    private static final Logger LOGGER = Logger.getLogger(ExchangeRateService.class.getName());
    private static final String API_KEY = ConfigUtil.getApiKey();
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY;

    public List<Currency> getSupportedCurrencies() throws ApiException {
        try {
            URL url = new URL(BASE_URL + "/codes");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new ApiException("No se pudo obtener las monedas soportadas");
            }

            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
            List<Currency> currencies = new ArrayList<>();
            jsonResponse.getAsJsonArray("supported_codes").forEach(element -> {
                String code = element.getAsJsonArray().get(0).getAsString();
                String name = element.getAsJsonArray().get(1).getAsString();
                currencies.add(new Currency(code, name));
            });

            return currencies;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al obtener las monedas soportadas", e);
            throw new ApiException("Error al obtener las monedas soportadas", e);
        }
    }

    public double convertCurrency(String from, String to, double amount) throws ApiException {
        try {
            URL url = new URL(BASE_URL + "/pair/" + from + "/" + to + "/" + amount);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 400) {
                InputStreamReader reader = new InputStreamReader(conn.getErrorStream());
                JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
                String errorType = jsonResponse.get("error-type").getAsString();
                switch (errorType) {
                    case "malformed-request":
                        throw new ApiException("Solicitud malformada. Por favor, revise los códigos de moneda.");
                    case "unsupported-code":
                        throw new ApiException("Código de moneda no soportado. Por favor, intente con otro código.");
                    default:
                        throw new ApiException("Error desconocido al convertir la moneda.");
                }
            }

            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();

            if (!jsonResponse.get("result").getAsString().equals("success")) {
                throw new ApiException("Error desconocido al convertir la moneda.");
            }

            return jsonResponse.get("conversion_result").getAsDouble();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al convertir la moneda", e);
            throw new ApiException("Error al convertir la moneda", e);
        }
    }
}