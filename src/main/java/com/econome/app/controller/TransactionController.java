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

    @GetMapping("/totalBalance/{year}/{month}")
    public Double getTotalBalance(@PathVariable Integer year, @PathVariable Integer month) {
        return transactionRepository.getTotalBalance(year, month);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/transactions/deleteById/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}