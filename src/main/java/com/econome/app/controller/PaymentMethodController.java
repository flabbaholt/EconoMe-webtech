package com.econome.app.controller;

import com.econome.app.model.Currency;
import com.econome.app.model.PaymentMethod;
import com.econome.app.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<PaymentMethod> getPaymentMethod() {
        return paymentMethodService.getAll();
    }
}