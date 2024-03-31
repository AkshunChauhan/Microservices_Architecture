package com.example.bookservice.service;

import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public ResponseEntity<?> getBookByTitle(String title) {
        Book book = bookRepository.findByTitleIgnoreCase(title);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<?> addBook(Book book) {
        //check if the book title is empty
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        //check if the book author is empty
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.save(book);
        return ResponseEntity.ok("Book Saved: \n"+ book);
    }

    public ResponseEntity<?> updateBook(int id, Book updatedBook) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            if (updatedBook.getTitle() == null || updatedBook.getTitle().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            //check if the book author is empty
            if (updatedBook.getAuthor() == null || updatedBook.getAuthor().isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            //set new book values
            Book existingBook = book.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            bookRepository.save(existingBook);

            return ResponseEntity.ok("Book Update: \n"+existingBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteBook(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Book Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
