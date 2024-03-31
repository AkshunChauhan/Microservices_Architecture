package com.example.orderservice.controller;

import com.example.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Endpoint to place a book order using title only
    @PostMapping("/book")
    @Operation(summary = "Placing book order using title only")
    public ResponseEntity<?> placeBookOrder(@RequestBody String bookTitle) {
        return orderService.placeBookOrder(bookTitle);
    }

    // Endpoint to retrieve all book orders
    @GetMapping("/allorders")
    @Operation(summary = "Retrieve all books orders")
    public ResponseEntity<?> shopBooks() {
        // Return the response body from the order service directly
        return ResponseEntity.ok(orderService.shopAll().getBody());
    }
}
