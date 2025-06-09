package com.example.paymentservice.controller;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.external.BookingServiceClient;
import com.example.paymentservice.external.FlightServiceClient;
import com.example.paymentservice.external.UserServiceClient;
import com.example.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserServiceClient userClient;
    private final FlightServiceClient flightClient;
    private final BookingServiceClient bookingClient;

    public PaymentController(PaymentService paymentService, UserServiceClient userClient,
                             FlightServiceClient flightClient, BookingServiceClient bookingClient) {
        this.paymentService = paymentService;
        this.userClient = userClient;
        this.flightClient = flightClient;
        this.bookingClient = bookingClient;
    }

    @PostMapping
    public Payment makePayment(@RequestBody Payment payment) {
        userClient.getUserById(payment.getUserId());
        flightClient.getFlightById(payment.getFlightId());
        bookingClient.getBookingById(payment.getBookingId());
        return paymentService.processPayment(payment);
    }

    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAllPayments();
    }
}
