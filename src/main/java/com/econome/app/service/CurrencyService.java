package com.econome.app.service;

import com.econome.app.model.Currency;
import com.econome.app.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a service class for managing currencies.
 * It provides methods for saving, retrieving a single currency by its ID, and retrieving all currencies.
 */
@Service
public class CurrencyService {

    /**
     * The repository that provides CRUD operations for currencies.
     */
    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Saves a currency to the database.
     *
     * @param currency The currency to save.
     * @return The saved currency.
     */
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    /**
     * Retrieves a single currency by its ID.
     *
     * @param id The ID of the currency to retrieve.
     * @return The retrieved currency, or null if no currency with the given ID exists.
     */
    public Currency get(Long id) {
        Optional<Currency> currency = currencyRepository.findById(id);
        return currency.orElse(null);
    }


    public Currency findByName(String name) {
        return currencyRepository.findByName(name);
    }

    /**
     * Retrieves all currencies from the database.
     *
     * @return A list of all currencies.
     */
    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }
}