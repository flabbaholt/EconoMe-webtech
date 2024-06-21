package com.econome.app.service;

import com.econome.app.model.Currency;
import com.econome.app.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency get(Long id) {
        Optional<Currency> currency = currencyRepository.findById(id);
        return currency.orElse(null);
    }

    public Currency findByName(String name) {
        return currencyRepository.findByName(name);
    }

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }
}