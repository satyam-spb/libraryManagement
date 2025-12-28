# Library Management System (Spring Boot + MySQL)

This is a backend REST API built using Spring Boot and MySQL.  
The project focuses on clean layering, correct use of JPA, and building a non-trivial CRUD system without shortcuts.

The goal of this project is learning and demonstrating backend fundamentals the right way, not just making endpoints work.

---

## What this project does

Currently, the system supports managing books in a library.

Implemented features:
- Create a book
- Get all books
- Get a book by ID
- Update a book
- Delete a book

Books are persisted in MySQL using JPA/Hibernate.  
Schema generation and data seeding are handled automatically at startup.

---

## Tech stack

- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven

---

## Project structure

com.springTutorial.libraryManagement
├── controller
│ └── BookController.java
├── service
│ └── BookService.java
├── repository
│ └── BookRepository.java
├── entity
│ └── Book.java
├── config
│ └── DataInitializer.java
└── LibraryManagementApplication.java


### Layer responsibilities

- Controller  
  Handles HTTP requests and responses only.

- Service  
  Contains business logic and orchestrates repository calls.

- Repository  
  Data access layer using Spring Data JPA.

- Entity  
  JPA-mapped domain model.

This separation is intentional and enforced throughout the project.

---

## Database model

### Book table

| Column | Type   | Constraints                |
|------|--------|----------------------------|
| id   | Long   | Primary key, auto-generated |
| title | String | Not null                   |
| author | String | Not null                  |
| isbn | String | Unique, not null            |

ISBN is used as a real-world unique identifier for books.

---

## Configuration

### Database setup

Create a database in MySQL:

```sql
CREATE DATABASE library_db;
