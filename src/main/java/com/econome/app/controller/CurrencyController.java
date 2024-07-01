package com.econome.app.controller;

import com.econome.app.model.Currency;
import com.econome.app.service.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;
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
     * A list of valid currency abbreviations.
     */
    private List<String> validCurrencies;

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Resource resource = new ClassPathResource("currency_abbreviations.json");
            Map<String, List<String>> jsonMap = mapper.readValue(resource.getInputStream(), Map.class);
            validCurrencies = jsonMap.get("currencies");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load currency abbreviations", e);
        }
    }
  
    /**
     * Creates a new currency.
     *
     * @param currency The currency to create.
     * @return The created currency.
     */
 
    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        if (!validCurrencies.contains(currency.getName())) {
            throw new IllegalArgumentException("Invalid currency: " + currency.getName());
        }

        Currency existingCurrency = currencyService.findByName(currency.getName());
        if (existingCurrency != null) {
            return existingCurrency;
        }

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
        Map<String, Double> recalculatedRates = new HashMap<>();
        try {
            Map<String, Object> response = restTemplate.getForObject("https://openexchangerates.org/api/latest.json?app_id="+apiKey, Map.class);
            Map<String, Number> rates = (Map<String, Number>) response.get("rates");
            double eurRate = rates.get("EUR").doubleValue();
            for (Map.Entry<String, Number> entry : rates.entrySet()) {
                if (entry.getKey().equals("USD")) {
                    recalculatedRates.put(entry.getKey(), 1 / eurRate);
                } else {
                    recalculatedRates.put(entry.getKey(), entry.getValue().doubleValue() / eurRate);
                }
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Resource resource = new ClassPathResource("exchangerates.json");
                Map<String, Double> jsonMap = mapper.readValue(resource.getInputStream(), Map.class);
                recalculatedRates = jsonMap;
            } catch (IOException ioException) {
                throw new RuntimeException("Failed to load exchange rates from local file", ioException);
            }
        }
        return recalculatedRates;
    }
}