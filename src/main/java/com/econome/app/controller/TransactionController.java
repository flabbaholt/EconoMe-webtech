package com.econome.app.controller;

import com.econome.app.model.Transaction;
import com.econome.app.repository.TransactionRepository;
import com.econome.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository, TransactionService transactionService) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(@RequestParam(required = false) Integer year) {
        if (year != null) {
            return transactionService.getTransactionsByYear(year);
        } else {
            return transactionRepository.findAll();
        }
    }

    @GetMapping("/totalBalance/{year}/{month}")
    public Double getTotalBalance(@PathVariable Integer year, @PathVariable Integer month) {
        return transactionRepository.getTotalBalance(year, month);
    }

    @GetMapping("/getYears")
    public List<Integer> getYears() {
        return transactionRepository.getYears();
    }

    @GetMapping("/getMonths")
    public List<Integer> getMonths() {
        return transactionRepository.getMonths();
    }

    @GetMapping("/getMonthsByYear/{year}")
    public List<Integer> getMonthsByYear(@PathVariable Integer year) {
        return transactionRepository.getMonthsByYear(year);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}
