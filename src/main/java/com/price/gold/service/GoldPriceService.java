package com.price.gold.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GoldPriceService {

    private static final Logger logger = LoggerFactory.getLogger(GoldPriceService.class);

    @Value("${goldapi.api.key}")
    private String goldApiKey;

    @Value("${exchangerate.api.key}")
    private String exchangeRateApiKey;

    private static final String GOLD_BASE_URL = "https://www.goldapi.io/api/XAU";
    private static final String EXCHANGE_RATE_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final double TROY_OUNCE_TO_GRAMS = 31.1035;

    public double getGoldPriceInUSD() throws Exception {
        String urlString = GOLD_BASE_URL + "/USD";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("x-access-token", goldApiKey);
        conn.setRequestProperty("Content-Type", "application/json");

        int responseCode = conn.getResponseCode();
        logger.info("Gold API response code: {}", responseCode);
        if (responseCode != 200) {
            throw new Exception("Failed to get gold price: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        logger.info("Gold API Response: {}", content.toString());

        JSONObject json = new JSONObject(content.toString());
        double pricePerTroyOunce = json.getDouble("price");
        return pricePerTroyOunce / TROY_OUNCE_TO_GRAMS;
    }

    public double getUSDBRLExchangeRate() throws Exception {
        String urlString = EXCHANGE_RATE_BASE_URL + exchangeRateApiKey + "/latest/USD";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        int responseCode = conn.getResponseCode();
        logger.info("Exchange Rate API response code: {}", responseCode);
        if (responseCode != 200) {
            throw new Exception("Failed to get exchange rate: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        logger.info("Exchange Rate API Response: {}", content.toString());

        JSONObject json = new JSONObject(content.toString());
        return json.getJSONObject("conversion_rates").getDouble("BRL");
    }

    public double getGoldPriceInBRL() throws Exception {
        double goldPriceInUSD = getGoldPriceInUSD();
        double exchangeRate = getUSDBRLExchangeRate();
        return goldPriceInUSD * exchangeRate;
    }
}
