# Microservices Communication with Spring Boot

This project demonstrates the implementation of two microservices, BookService and OrderService, using Spring Boot. Microservice A (BookService) implements CRUD operations for the entity 'Book' and uses an H2 database. Microservice B (OrderService) communicates with Microservice A to perform a condition check on the books.

## Microservice A: BookService
Microservice A provides CRUD operations for managing books. It interacts with the H2 database to store and retrieve book data.

### Endpoints:
- **GET /api/books/all**: Retrieves all books from the database.
- **GET /api/books/{title}**: Retrieves a book by its title.
- **POST /api/books/add**: Adds a new book to the database.
- **PATCH /api/books/{id}**: Updates a book by its ID.
- **DELETE /api/books/{id}**: Deletes a book by its ID.

## Microservice B: OrderService
Microservice B communicates with Microservice A to check conditions related to books.

### Endpoints:
- **POST /api/orders/book**: Places a book order using the book title.
- **GET /api/orders/allorders**: Retrieves a list of all books for displaying purposes.

## Inter-Service Communication
Microservice B communicates with Microservice A through RESTful endpoints. Specifically, Microservice B fetches a list of books from Microservice A to perform condition checks.

## Database Integration
Microservice A (BookService) integrates with an H2 database to store book data. The database is used to persist book information for CRUD operations.


## Dependencies
- Spring Boot
- H2 Database
- Maven

## Technologies Used
- Java
- Spring Boot
- RESTful APIs
- H2 Database

## Author
Akshun Chauhan  (AK)

