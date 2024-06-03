package com.econome.app.controller;

import com.econome.app.model.PaymentMethod;
import com.econome.app.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentMethods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping
    public PaymentMethod createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod);
    }

    @GetMapping("/{id}")
    public PaymentMethod getPaymentMethod(@PathVariable Long id) {
        return paymentMethodService.get(id);
    }
}