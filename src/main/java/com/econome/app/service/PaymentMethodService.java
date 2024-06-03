package com.econome.app.service;

import com.econome.app.model.PaymentMethod;
import com.econome.app.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public PaymentMethod get(Long id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(id);
        return paymentMethod.orElse(null);
    }
}