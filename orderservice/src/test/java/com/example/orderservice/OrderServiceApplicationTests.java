package com.example.orderservice;

import com.example.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceApplicationTests {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private OrderService orderService;

	@Test
	void placeBookOrder_BookFound() {
		// Arrange
		String bookTitle = "Sample Book";
		String bookDetails = "Sample book details";
		when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(bookDetails);

		// Act
		ResponseEntity<?> responseEntity = orderService.placeBookOrder(bookTitle);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Order placed for Book:\n" + bookDetails, responseEntity.getBody());
	}

	@Test
	void placeBookOrder_BookNotFound() {
		// Arrange
		String bookTitle = "Nonexistent Book";
		when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(null);

		// Act
		ResponseEntity<?> responseEntity = orderService.placeBookOrder(bookTitle);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Book Not found!", responseEntity.getBody());
	}

	@Test
	void shopAll_Success() {
		// Arrange
		String bookCatalog = "Sample book catalog";
		when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(bookCatalog);

		// Act
		ResponseEntity<?> responseEntity = orderService.shopAll();

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Book Service Catalog:\n" + bookCatalog, responseEntity.getBody());
	}

	@Test
	void shopAll_Error() {
		// Arrange
		when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(new RuntimeException("Error"));

		// Act
		ResponseEntity<?> responseEntity = orderService.shopAll();

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Error: \nError", responseEntity.getBody());
	}
}
