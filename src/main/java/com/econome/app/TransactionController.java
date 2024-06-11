package com.econome.app;

import com.econome.app.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TransactionController {


    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        Transaction transaction = new Transaction("Restaurant", "Expense", 100.0, "Food", "Cash", "USD", LocalDate.now());
        Transaction transaction2 = new Transaction("Monthly Salary","Income", 1000.0, "Salary", "Bank", "USD", LocalDate.now());
        Transaction transaction3 = new Transaction("Bus ticket","Expense", 50.0, "Transport", "Credit card", "USD", LocalDate.now());

        return List.of(transaction, transaction2, transaction3);
    }
}