package com.econome.app.controller;

import com.econome.app.projection.TransactionProjection;
import com.econome.app.repository.*;
import com.econome.app.model.*;
import com.econome.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.econome.app.projection.BalanceProjection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This is a REST controller for managing transactions.
 * It provides endpoints for creating, deleting, and retrieving transactions, as well as retrieving balance information.
 */
@RestController
public class TransactionController {
    /**
     * The API key for the exchange currency API.
     */
    @Value("${EXCHANGE_CURRENCY_API_KEY}")
    private String apiKey;

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final TypeRepository typeRepository;
    private final CurrencyRepository currencyRepository;

    /**
     * Constructor for the TransactionController.
     *
     * @param transactionRepository The repository for transactions.
     * @param categoryRepository The repository for categories.
     * @param paymentMethodRepository The repository for payment methods.
     * @param typeRepository The repository for types.
     * @param currencyRepository The repository for currencies.
     */
    public TransactionController(TransactionRepository transactionRepository, CategoryRepository categoryRepository, PaymentMethodRepository paymentMethodRepository, TypeRepository typeRepository, CurrencyRepository currencyRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.typeRepository = typeRepository;
        this.currencyRepository = currencyRepository;
    }

    /**
     * Retrieves all transactions.
     *
     * @return A list of all transactions.
     */
    @GetMapping("/transactions")
    public List<TransactionProjection> getTransactions() {
        return transactionRepository.findAllProjectedBy();
    }

    @GetMapping("/totalBalance/{year}/{month}")
    public Double getTotalBalance(@PathVariable Integer year, @PathVariable Integer month) {
        List<BalanceProjection> balanceProjections = transactionRepository.getTotalBalance(year, month);
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

        double totalBalanceInEuro = 0;

        for (BalanceProjection balanceProjection : balanceProjections) {
            BigDecimal amount = balanceProjection.getAmount();
            String currency = balanceProjection.getCurrency();
            double exchangeRate = recalculatedRates.get(currency);
            System.out.println("Amount "+amount +": Rate for " + currency + " is " + exchangeRate + "resulting in " + amount.doubleValue() / exchangeRate);

            double amountInEuro = amount.doubleValue() / exchangeRate;
            totalBalanceInEuro += amountInEuro;
        }

        totalBalanceInEuro = Math.round(totalBalanceInEuro * 100.0) / 100.0;

        return totalBalanceInEuro;
    }

    // Neuen Endpunkt für das Dashboard hinzufügen
    @GetMapping("/transactions/dashboard")
    public List<TransactionProjection> getTransactionsByYear(@RequestParam int year) {
        return transactionRepository.findAllByYear(year);
    }

    @GetMapping("/transactions/getYears")
    public List<Integer> getYears() {
        return transactionRepository.getYears();
    }

    @GetMapping("/transactions/getMonths")
    public List<Integer> getMonths() {
        return transactionRepository.getMonths();
    }

    @GetMapping("/transactions/getMonthsByYear/{year}")
    public List<Integer> getMonthsByYear(@PathVariable Integer year) {
        return transactionRepository.getMonthsByYear(year);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Map<String, String> payload) {
        Transaction transaction = new Transaction();

        transaction.setName(payload.get("name"));
        transaction.setAmount(new BigDecimal(payload.get("amount")));
        transaction.setTransactionDate(LocalDate.parse(payload.get("transactionDate")));

        Long categoryId = Long.parseLong(payload.get("categoryId"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        transaction.setCategory(category);

        Long paymentMethodId = Long.parseLong(payload.get("paymentMethodId"));
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow(() -> new RuntimeException("PaymentMethod not found"));
        transaction.setPaymentMethod(paymentMethod);

        Long typeId = Long.parseLong(payload.get("typeId"));
        Type type = typeRepository.findById(typeId).orElseThrow(() -> new RuntimeException("Type not found"));
        transaction.setType(type);

        Long currencyId = Long.parseLong(payload.get("currencyId"));
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(() -> new RuntimeException("Currency not found"));
        transaction.setCurrency(currency);

        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/transactions/deleteById/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}
