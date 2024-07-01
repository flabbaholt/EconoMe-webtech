package com.econome.app.service;

import com.econome.app.model.Transaction;
import com.econome.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction get(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction with ID" + id + " not found"));
    }

    public List<Transaction> getTransactionsByYear(int year) {
        return transactionRepository.findAllByYear(year);
    }
}
