package com.econome.app.model;

import jakarta.persistence.*;

/**
 * This is a JPA entity class for the PaymentMethod table in the database.
 * It represents a payment method that can be assigned to a transaction.
 */
@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    /**
     * The unique identifier for the payment method.
     * It is automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the payment method.
     */
    @Column(name = "name")
    private String name;

    // getters and setters

    /**
     * Returns the unique identifier of the payment method.
     *
     * @return The unique identifier of the payment method.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the payment method.
     *
     * @param id The unique identifier of the payment method.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the payment method.
     *
     * @return The name of the payment method.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the payment method.
     *
     * @param name The name of the payment method.
     */
    public void setName(String name) {
        this.name = name;
    }
}