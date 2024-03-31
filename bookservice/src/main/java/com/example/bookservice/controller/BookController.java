package com.example.bookservice.controller;

import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all")
    @Operation(summary = "Get all the books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{title}")
    @Operation(summary = "Get book by title")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @PostMapping("/add")
    @Operation(summary = "Add new book")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PatchMapping("/{id}")
    @Operation(summary="Update Book by id")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody Book updatedBook){
        return bookService.updateBook(id,updatedBook);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete Book by id")
    public ResponseEntity<?> updateBook(@PathVariable int id){
        return bookService.deleteBook(id);
    }
}
