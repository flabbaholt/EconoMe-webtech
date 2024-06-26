package com.econome.app.model;

import jakarta.persistence.*;

/**
 * This is a JPA entity class for the Currency table in the database.
 * It represents a currency that can be assigned to a transaction.
 */
@Entity
@Table(name = "currency")
public class Currency {

    /**
     * The unique identifier for the currency.
     * It is automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the currency.
     */
    @Column(name = "name")
    private String name;

    // getters and setters

    /**
     * Returns the unique identifier of the currency.
     *
     * @return The unique identifier of the currency.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the currency.
     *
     * @param id The unique identifier of the currency.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the currency.
     *
     * @return The name of the currency.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the currency.
     *
     * @param name The name of the currency.
     */
    public void setName(String name) {
        this.name = name;
    }
}