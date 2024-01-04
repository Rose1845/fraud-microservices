package com.rose.orderservice.controller;

import com.rose.orderservice.model.dto.OrderRequest;
import com.rose.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
 @PostMapping
 @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
 @TimeLimiter(name = "inventory")
 @Retry(name = "inventory")
 @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
     return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops! Something went wrong ,please order after sometime");
    }
}
