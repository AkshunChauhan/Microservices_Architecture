package com.example.bookservice;

import com.example.bookservice.controller.BookController;
import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTests {

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@Test
	public void testGetAllBooks() {
		List<Book> books = Arrays.asList(new Book(), new Book());
		when(bookService.getAllBooks()).thenReturn(books);

		assertEquals(books, bookController.getAllBooks());
	}




	@Test
	public void testAddBook() {
		Book book = new Book();
		when(bookService.addBook(any(Book.class))).thenReturn(ResponseEntity.ok().build());

		ResponseEntity<?> response = bookController.addBook(book);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testUpdateBook() {
		int id = 1;
		Book updatedBook = new Book();
		when(bookService.updateBook(anyInt(), any(Book.class))).thenReturn(ResponseEntity.ok().build());

		ResponseEntity<?> response = bookController.updateBook(id, updatedBook);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testDeleteBook() {
		int id = 1;
		when(bookService.deleteBook(anyInt())).thenReturn(ResponseEntity.ok().build());

		ResponseEntity<?> response = bookController.deleteBook(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
