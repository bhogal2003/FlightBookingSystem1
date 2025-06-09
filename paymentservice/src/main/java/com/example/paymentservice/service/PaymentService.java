package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment processPayment(Payment payment);
    List<Payment> getAllPayments();
}
