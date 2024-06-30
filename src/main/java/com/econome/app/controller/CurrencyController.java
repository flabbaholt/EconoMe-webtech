package com.econome.app.controller;

import com.econome.app.model.Currency;
import com.econome.app.projection.TransactionProjection;
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

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Value("${EXCHANGE_CURRENCY_API_KEY}")
    private String apiKey;

    @Autowired
    private CurrencyService currencyService;

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

    @GetMapping("/{id}")
    public Currency getCurrency(@PathVariable Long id) {
        return currencyService.get(id);
    }

    @GetMapping
    public List<Currency> getCurrencies() {
        return currencyService.getAll();
    }

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