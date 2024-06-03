package com.econome.app.service;

import com.econome.app.model.Transaction;
import com.econome.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository repo;

    public Transaction save(Transaction transaction) {
        return repo.save(transaction);
    }

    public Transaction get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Transaction with ID"+id+"not found"));
    }
}
