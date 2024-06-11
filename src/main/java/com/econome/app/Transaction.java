package com.econome.app;

//import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a financial transaction in the application.
 * Each transaction has an id, amount, type, category, payment method, currency, description, and date.
 */
//@Entity
public class Transaction {

    private String name;
    private String type;
    private double amount;
    private String category;
    private String paymentMethod;
    private String currency;
    private LocalDate date;

    //Constructors
    public Transaction(String name, String type, double amount, String category, String paymentMethod, String currency, LocalDate date) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
        this.date = date;
    }
    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
