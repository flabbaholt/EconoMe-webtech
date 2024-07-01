package com.econome.app.service;

import com.econome.app.model.Transaction;
import com.econome.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is a service class for managing transactions.
 * It provides methods for saving a transaction, retrieving a single transaction by its ID, and retrieving all transactions by a specific year.
 */
@Service
public class TransactionService {

    /**
     * The repository that provides CRUD operations for transactions.
     */
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Saves a transaction to the database.
     *
     * @param transaction The transaction to save.
     * @return The saved transaction.
     */
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves a single transaction by its ID.
     *
     * @param id The ID of the transaction to retrieve.
     * @return The retrieved transaction.
     * @throws RuntimeException if no transaction with the given ID exists.
     */
    public Transaction get(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction with ID" + id + " not found"));
    }

    /**
     * Retrieves all transactions from the database by a specific year.
     *
     * @param year The year to filter transactions.
     * @return A list of transactions from the specified year.
     */
    public List<Transaction> getTransactionsByYear(int year) {
        return transactionRepository.findAllByYear(year);
    }
}