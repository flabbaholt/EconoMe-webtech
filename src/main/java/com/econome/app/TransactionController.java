package com.econome.app;

import com.econome.app.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TransactionController {

    //private final TransactionRepository transactionRepository;

   // public TransactionController(TransactionRepository transactionRepository) {
    //    this.transactionRepository = transactionRepository;
   // }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        Transaction transaction = new Transaction("Expense", 100.0, "Food", "Cash", "USD", "Lunch", LocalDate.now());
        Transaction transaction2 = new Transaction("Income", 1000.0, "Salary", "Bank", "USD", "Monthly salary", LocalDate.now());
        Transaction transaction3 = new Transaction("Expense", 50.0, "Transport", "Credit card", "USD", "Bus ticket", LocalDate.now());

        return List.of(transaction, transaction2, transaction3);
    }
}