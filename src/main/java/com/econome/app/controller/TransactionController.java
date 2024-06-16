package com.econome.app.controller;

import com.econome.app.projection.TransactionProjection;
import com.econome.app.repository.*;
import com.econome.app.model.*;
import com.econome.app.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final TypeRepository typeRepository;
    private final CurrencyRepository currencyRepository;

    public TransactionController(TransactionRepository transactionRepository, CategoryRepository categoryRepository, PaymentMethodRepository paymentMethodRepository, TypeRepository typeRepository, CurrencyRepository currencyRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.typeRepository = typeRepository;
        this.currencyRepository = currencyRepository;
    }

    @GetMapping("/transactions")
    public List<TransactionProjection> getTransactions() {
        return transactionRepository.findAllProjectedBy();
    }

    @GetMapping("/totalBalance/{year}/{month}")
    public Double getTotalBalance(@PathVariable Integer year, @PathVariable Integer month) {
        return transactionRepository.getTotalBalance(year, month);
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