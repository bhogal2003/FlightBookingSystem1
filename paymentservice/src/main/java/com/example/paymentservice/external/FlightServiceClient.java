package com.example.paymentservice.external;

import com.example.paymentservice.dto.FlightDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightServiceClient {
    @GetMapping("/flights/{id}")
    @CircuitBreaker(name = "flightService", fallbackMethod = "getFlightFallback")
    FlightDto getFlightById(@PathVariable Long id);

    default FlightDto getFlightFallback(Long id, Throwable t) {
        FlightDto flight = new FlightDto();
        flight.id = id;
        flight.flightNumber = "N/A";
        flight.source = "Unknown";
        flight.destination = "Unknown";
        return flight;
    }
}


