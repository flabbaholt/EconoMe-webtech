package com.econome.app.controller;

import com.econome.app.model.Currency;
import com.econome.app.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a REST controller for managing currencies.
 * It provides endpoints for creating, retrieving a single currency, retrieving all currencies, and getting exchange rates.
 */
@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    /**
     * The API key for the exchange currency API.
     */
    @Value("${EXCHANGE_CURRENCY_API_KEY}")
    private String apiKey;

    /**
     * The service that provides operations for currencies.
     */
    @Autowired
    private CurrencyService currencyService;

    /**
     * Creates a new currency.
     *
     * @param currency The currency to create.
     * @return The created currency.
     */
    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyService.save(currency);
    }

    /**
     * Retrieves a single currency by its ID.
     *
     * @param id The ID of the currency to retrieve.
     * @return The retrieved currency.
     */
    @GetMapping("/{id}")
    public Currency getCurrency(@PathVariable Long id) {
        return currencyService.get(id);
    }

    /**
     * Retrieves all currencies.
     *
     * @return A list of all currencies.
     */
    @GetMapping
    public List<Currency> getCurrencies() {
        return currencyService.getAll();
    }

    /**
     * Retrieves exchange rates for all currencies.
     *
     * @return A map of currency codes to their exchange rates.
     */
    @GetMapping("/exchangeRates")
    public Map<String, Double> getExchangeRates() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject("https://openexchangerates.org/api/latest.json?app_id="+apiKey, Map.class);

        Map<String, Number> rates = (Map<String, Number>) response.get("rates");
        double eurRate = rates.get("EUR").doubleValue();

        Map<String, Double> recalculatedRates = new HashMap<>();
        for (Map.Entry<String, Number> entry : rates.entrySet()) {
            if (entry.getKey().equals("USD")) {
                recalculatedRates.put(entry.getKey(), 1 / eurRate);
            } else {
                recalculatedRates.put(entry.getKey(), entry.getValue().doubleValue() / eurRate);
            }
        }
        return recalculatedRates;
    }
}