package com.econome.app;

//import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a financial transaction in the application.
 * Each transaction has an id, amount, type, category, payment method, currency, description, and date.
 */
//@Entity
public class Transaction {

    private TransactionType type;
    private double amount;
    private String category;
    private String paymentMethod;
    private String currency;
    private String description;
    private LocalDate date;

    //Constructors
    public Transaction(String type, double amount, String category, String paymentMethod, String currency, String description, LocalDate date) {
        this.type = TransactionType.valueOf(type.toUpperCase());
        this.amount = amount;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
        this.description = description;
        this.date = date;
    }
    // getters and setters

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
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