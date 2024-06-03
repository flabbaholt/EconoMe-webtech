package com.econome.app.controller;

import com.econome.app.projection.TransactionProjection;
import com.econome.app.repository.TransactionRepository;
import com.econome.app.model.Transaction;
import com.econome.app.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

    }

    @GetMapping("/transactions")
    public List<TransactionProjection> getTransactions() {
        return transactionRepository.findAllProjectedBy();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /*@GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        Transaction transaction = new Transaction("Restaurant", "Expense", 100.0, "Food", "Cash", "USD", LocalDate.now());
        Transaction transaction2 = new Transaction("Monthly Salary","Income", 1000.0, "Salary", "Bank", "USD", LocalDate.now());
        Transaction transaction3 = new Transaction("Bus ticket","Expense", 50.0, "Transport", "Credit card", "USD", LocalDate.now());

        return List.of(transaction, transaction2, transaction3);
    }*/
}