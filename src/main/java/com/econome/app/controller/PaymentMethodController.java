package com.econome.app.controller;

import com.econome.app.model.PaymentMethod;
import com.econome.app.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a REST controller for managing payment methods.
 * It provides endpoints for creating, retrieving a single payment method and retrieving all payment methods.
 */
@RestController
@RequestMapping("/paymentMethods")
public class PaymentMethodController {

    /**
     * The service that provides operations for payment methods.
     */
    @Autowired
    private PaymentMethodService paymentMethodService;

    /**
     * Creates a new payment method.
     *
     * @param paymentMethod The payment method to create.
     * @return The created payment method.
     */
    @PostMapping
    public PaymentMethod createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod);
    }

    /**
     * Retrieves a single payment method by its ID.
     *
     * @param id The ID of the payment method to retrieve.
     * @return The retrieved payment method.
     */
    @GetMapping("/{id}")
    public PaymentMethod getPaymentMethod(@PathVariable Long id) {
        return paymentMethodService.get(id);
    }

    /**
     * Retrieves all payment methods.
     *
     * @return A list of all payment methods.
     */
    @GetMapping
    public List<PaymentMethod> getPaymentMethod() {
        return paymentMethodService.getAll();
    }
}