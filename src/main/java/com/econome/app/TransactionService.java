package com.econome.app;

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
