package com.example.paymentservice.external;

import com.example.paymentservice.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {
    @GetMapping("/users/{id}")
    @CircuitBreaker(name = "userService", fallbackMethod = "getUserFallback")
    UserDto getUserById(@PathVariable Long id);

    default UserDto getUserFallback(Long id, Throwable t) {
        UserDto user = new UserDto();
        user.id = id;
        user.name = "Fallback User";
        user.email = "N/A";
        return user;
    }
}
