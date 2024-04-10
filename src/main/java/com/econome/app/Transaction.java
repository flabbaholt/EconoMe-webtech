package com.econome.app;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a financial transaction in the application.
 * Each transaction has an id, amount, type, category, payment method, currency, description, and date.
 */
@Entity
public class Transaction {

    /**
     * The unique identifier of the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The amount of the transaction.
     */
    private double amount;

    /**
     * The type of the transaction (income or expense).
     */
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    /**
     * The category of the transaction.
     */
    @ManyToOne
    private Category category;

    /**
     * The payment method used for the transaction.
     */
    private String paymentMethod;

    /**
     * The currency in which the transaction was made.
     */
    private String currency;

    /**
     * A description of the transaction.
     */
    private String description;

    /**
     * The date of the transaction.
     */
    private LocalDate date;

    // getters and setters
}

/**
 * Represents the type of a transaction.
 * A transaction can either be an income or an expense.
 */
enum TransactionType {
    /**
     * Represents an income transaction.
     */
    INCOME,

    /**
     * Represents an expense transaction.
     */
    EXPENSE
}