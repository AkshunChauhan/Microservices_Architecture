package com.example.bookservice.controller;

import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Endpoint to retrieve all books
    @GetMapping("/all")
    @Operation(summary = "Get all the books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Endpoint to retrieve a book by its title
    @GetMapping("/{title}")
    @Operation(summary = "Get book by title")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        ResponseEntity<?> responseEntity = bookService.getBookByTitle(title);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            // Customize the response for book not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with title: " + title);
        }
        // Return the original response if the book is found
        return responseEntity;
    }


    // Endpoint to add a new book
    @PostMapping("/add")
    @Operation(summary = "Add new book")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // Endpoint to update a book by its id
    @PatchMapping("/{id}")
    @Operation(summary="Update Book by id")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody Book updatedBook){
        return bookService.updateBook(id,updatedBook);
    }

    // Endpoint to delete a book by its id
    @DeleteMapping("/{id}")
    @Operation(summary="Delete Book by id")
    public ResponseEntity<?> deleteBook(@PathVariable int id){
        return bookService.deleteBook(id);
    }
}