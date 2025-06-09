package com.example.paymentservice.external;

import com.example.paymentservice.dto.BookingDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BOOKING-SERVICE")
public interface BookingServiceClient {
    @GetMapping("/bookings/{id}")
    @CircuitBreaker(name = "bookingService", fallbackMethod = "getBookingFallback")
    BookingDto getBookingById(@PathVariable Long id);

    default BookingDto getBookingFallback(Long id, Throwable t) {
        BookingDto booking = new BookingDto();
        booking.id = id;
        booking.seatNumber = "N/A";
        booking.status = "Unavailable";
        return booking;
    }
}


