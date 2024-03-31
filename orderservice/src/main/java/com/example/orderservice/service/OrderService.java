package com.example.orderservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class OrderService {
    private final RestTemplate restTemplate;

    // Constructor injection of RestTemplate
    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to place a book order by title
    public ResponseEntity<?> placeBookOrder(String bookTitle) {
        // Utilize other microservice API to gather book details
        String bookServiceUrl = "http://localhost:8081/api/books/" + bookTitle;
        // Retrieve book details from the book service
        String bookDetails = restTemplate.getForObject(bookServiceUrl, String.class);
        System.out.println("bookDetails: " + bookDetails);
        // Check if the book details were retrieved successfully
        if (bookDetails == null) {
            return ResponseEntity.badRequest().body("Book Not found!");
        }
        System.out.println("Book found!");
        // Return a success response with the book details
        return ResponseEntity.ok("Order placed for Book:\n" + bookDetails);
    }

    // Method to retrieve all books from the book service
    public ResponseEntity<?> shopAll() {
        try {
            // Define the URL to retrieve all books from the book service
            String bookServiceUrl = "http://localhost:8081/api/books/all";
            // Retrieve the book catalog from the book service
            String bookCatalog = restTemplate.getForObject(bookServiceUrl, String.class);
            // Return a success response with the book catalog
            return ResponseEntity.ok("Book Service Catalog:\n" + bookCatalog);
        } catch (Exception e) {
            // Return an error response if an exception occurs during retrieval
            return ResponseEntity.badRequest().body("Error: \n" + e.getMessage());
        }
    }
}