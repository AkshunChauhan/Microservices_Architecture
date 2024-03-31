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

    @PostMapping("/book")
    @Operation(summary = "Placing book order using title only")
    public ResponseEntity<?> placeBookOrder(@RequestBody String bookTitle) {
        return orderService.placeBookOrder(bookTitle);
    }

    @GetMapping("/allorders")
    @Operation(summary = "all book orders")
    public ResponseEntity<?> shopBooks() {
        return ResponseEntity.ok(orderService.shopAll().getBody());
    }

}
