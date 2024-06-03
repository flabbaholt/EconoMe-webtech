package com.econome.app.controller;

import com.econome.app.model.Currency;
import com.econome.app.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

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
}