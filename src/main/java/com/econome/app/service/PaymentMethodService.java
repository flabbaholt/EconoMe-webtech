package com.econome.app.service;

import com.econome.app.model.PaymentMethod;
import com.econome.app.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a service class for managing payment methods.
 * It provides methods for saving, retrieving a single payment method by its ID, and retrieving all payment methods.
 */
@Service
public class PaymentMethodService {

    /**
     * The repository that provides CRUD operations for payment methods.
     */
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    /**
     * Saves a payment method to the database.
     *
     * @param paymentMethod The payment method to save.
     * @return The saved payment method.
     */
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    /**
     * Retrieves a single payment method by its ID.
     *
     * @param id The ID of the payment method to retrieve.
     * @return The retrieved payment method, or null if no payment method with the given ID exists.
     */
    public PaymentMethod get(Long id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(id);
        return paymentMethod.orElse(null);
    }

    /**
     * Retrieves all payment methods from the database.
     *
     * @return A list of all payment methods.
     */
    public List<PaymentMethod> getAll() {
        return paymentMethodRepository.findAll();
    }
}