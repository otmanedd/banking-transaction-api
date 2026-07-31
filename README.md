# Banking Transaction API (Spring Boot)

Backend-focused project simulating real-world banking operations.

A production-ready RESTful Banking API built with Java, Spring Boot, and PostgreSQL.

This project demonstrates clean backend architecture using layered design, DTO pattern, validation, transaction management, global exception handling, JWT authentication, logging, pagination, and unit testing.

---

## Live Demo

This project is a backend-only REST API.  
The root URL may return `403 Forbidden` because there is no public homepage endpoint.

API Base URL:  
https://banking-transaction-api-production.up.railway.app

Swagger UI:  
https://banking-transaction-api-production.up.railway.app/swagger-ui.html

OpenAPI Docs:  
https://banking-transaction-api-production.up.railway.app/v3/api-docs

> Use Swagger UI to test and interact with the API endpoints.
---

## Features

- Create bank accounts
- Deposit money into accounts
- Withdraw money from accounts
- Transfer money between accounts

- JWT-based authentication and authorization
- DTO-based request and response structure
- Input validation with Hibernate Validator
- Global exception handling
- Transaction management with @Transactional

- Pagination support (page, size)
- Logging with SLF4J
- Unit testing with JUnit and Mockito
- Swagger API documentation

- Clean layered architecture (Controller → Service → Repository)
- Cloud deployment using Railway
- RESTful API design principles

---

## Architecture

The project follows a layered architecture:

Controller → Service → Repository → Entity → Database

- Controller Layer: Handles HTTP requests and API endpoints
- Service Layer: Contains business logic
- Repository Layer: Handles database operations via Spring Data JPA
- Entity Layer: Represents database tables
- DTO Layer: Separates API models from database models
- Security Layer: Handles JWT authentication and authorization
- Exception Handling: Centralized error handling using @RestControllerAdvice

---

## Project Structure

```
com.serhat.bankingtransactionapi
│
├── config
│   ├── OpenApiConfig.java
│   └── SecurityConfig.java
│
├── controller
│   ├── AccountController.java
│   └── AuthController.java
│
├── service
│   ├── AccountService.java
│   └── AuthService.java
│
├── repository
│   ├── AccountRepository.java
│   └── UserRepository.java
│
├── entity
│   ├── Account.java
│   └── User.java
│
├── dto
│   ├── CreateAccountRequest.java
│   ├── AccountResponse.java
│   ├── DepositRequest.java
│   ├── WithdrawRequest.java
│   ├── TransferRequest.java
│   ├── AuthRequest.java
│   └── AuthResponse.java
│
├── exception
│   ├── AccountNotFoundException.java
│   ├── DuplicateAccountNumberException.java
│   ├── InsufficientBalanceException.java
│   └── GlobalExceptionHandler.java
│
└── BankingTransactionApiApplication.java
```

---

## Authentication

This API uses JWT (JSON Web Token) for authentication.

Flow:

1. Register a user
2. Login to receive a JWT token
3. Use the token in requests

Header format:

Authorization: Bearer <your_token>

---

## Transaction Management

All money operations (deposit, withdraw, transfer) are handled using @Transactional to ensure data consistency.

If any step fails, the entire transaction is rolled back.

---

## API Endpoints

### Auth

- POST /auth/register → Register user
- POST /auth/login → Login and receive JWT

### Accounts

- GET /accounts → List accounts (paginated)
- POST /accounts → Create account
- POST /accounts/deposit → Deposit money
- POST /accounts/withdraw → Withdraw money
- POST /accounts/transfer → Transfer money

---

## Pagination Example

GET /accounts?page=0&size=10

---

## Example Request

POST /accounts

```json
{
  "accountNumber": "TR1001",
  "ownerName": "Serhat",
  "balance": 5000
}
```

---

## Example Response

```json
{
  "accountNumber": "TR1001",
  "ownerName": "Serhat",
  "balance": 5000
}
```

---

## Error Response Example

```json
{
  "timestamp": "2026-04-11T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Insufficient balance"
}
```

---

## Database

- PostgreSQL (Railway managed service)
- Connection via environment variables:
  - PGHOST
  - PGPORT
  - PGDATABASE
  - PGUSER
  - PGPASSWORD
- Hibernate: ddl-auto=update

---

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Swagger (OpenAPI)
- JWT (Authentication)
- SLF4J (Logging)
- JUnit and Mockito (Testing)
- Railway (Cloud Deployment)

---

## How to Run

1. Clone the repository

```
git clone https://github.com/SerhatSerce/banking-transaction-api.git
```

2. Configure database via environment variables

3. Run the application

```
./mvnw spring-boot:run
```

4. Open Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

## Learning Outcomes

- Building REST APIs with Spring Boot
- Designing layered backend architecture
- Implementing JWT authentication
- Managing transactions and data consistency
- Applying validation and exception handling
- Writing unit tests with Mockito
- Using pagination in APIs
- Logging backend operations
- Deploying backend applications to cloud
- Documenting APIs with Swagger

---

## Author

Serhat