package com.price.gold.controller;

import com.price.gold.service.GoldPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/gold-price")
public class GoldPriceController {

    private static final Logger logger = LoggerFactory.getLogger(GoldPriceController.class);

    @Autowired
    private GoldPriceService goldPriceService;

    @GetMapping
    public GoldPriceResponse getGoldPrice(@RequestParam String currency) {
        logger.info("Received request to get gold price in currency: {}", currency);

        try {
            double price;
            if (currency.equalsIgnoreCase("USD")) {
                price = goldPriceService.getGoldPriceInUSD();
            } else if (currency.equalsIgnoreCase("BRL")) {
                price = goldPriceService.getGoldPriceInBRL();
            } else {
                logger.warn("Unsupported currency: {}", currency);
                return new GoldPriceResponse("Moeda não suportada");
            }
            return new GoldPriceResponse(price);
        } catch (Exception e) {
            logger.error("Error getting gold price", e);
            return new GoldPriceResponse("Erro ao obter preço do ouro: " + e.getMessage());
        }
    }

    public static class GoldPriceResponse {
        private String status;
        private double price;

        public GoldPriceResponse(double price) {
            this.status = "success";
            this.price = price;
        }

        public GoldPriceResponse(String status) {
            this.status = status;
            this.price = 0.0;
        }

        public String getStatus() {
            return status;
        }

        public double getPrice() {
            return price;
        }
    }
}

