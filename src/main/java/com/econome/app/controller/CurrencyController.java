package com.econome.app.controller;

import com.econome.app.model.Currency;
import com.econome.app.projection.TransactionProjection;
import com.econome.app.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

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

    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
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