# Banking Transaction API 💳

A production-style RESTful Banking API built with **Java 17, Spring Boot, PostgreSQL, JWT Authentication, and Swagger/OpenAPI**.

This project simulates real-world banking operations such as creating bank accounts, depositing money, withdrawing money, and transferring money between accounts.

The application follows a clean layered architecture and demonstrates backend development best practices.

---

# 🚀 Features

## Authentication & Security

* User registration
* User login
* JWT-based authentication
* Protected API endpoints
* BCrypt password encryption

## Banking Operations

* Create bank accounts
* Get accounts with pagination
* Deposit money
* Withdraw money
* Transfer money between accounts

## Backend Features

* RESTful API design
* DTO pattern
* Layered architecture
* Input validation
* Global exception handling
* Transaction management with `@Transactional`
* Pagination using Spring Data JPA
* Logging with SLF4J
* Unit testing with JUnit and Mockito

## Documentation

* Swagger UI
* OpenAPI documentation

---

# 🏗️ Architecture

The project follows a clean layered architecture:

```
Controller
    |
    ↓
Service
    |
    ↓
Repository
    |
    ↓
Database (PostgreSQL)
```

## Project Layers

### Controller Layer

Responsible for handling HTTP requests and returning responses.

Examples:

```
POST /accounts
GET /accounts
POST /accounts/transfer
```

---

### Service Layer

Contains the business logic.

Examples:

* Creating accounts
* Checking balances
* Processing money transfers
* Handling banking operations

---

### Repository Layer

Responsible for communication with the database using Spring Data JPA.

---

### Entity Layer

Represents database tables.

Main entities:

* User
* Account

---

### DTO Layer

Separates API request/response objects from database entities.

---

# 🛠️ Technologies

| Technology      | Purpose                          |
| --------------- | -------------------------------- |
| Java 17         | Programming language             |
| Spring Boot     | Backend framework                |
| Spring Security | Authentication and authorization |
| JWT             | Secure API authentication        |
| Spring Data JPA | Database access                  |
| Hibernate       | ORM                              |
| PostgreSQL      | Database                         |
| Maven           | Dependency management            |
| Swagger/OpenAPI | API documentation                |
| JUnit 5         | Testing framework                |
| Mockito         | Mock testing                     |
| Railway         | Cloud deployment                 |

---

# 📂 Project Structure

```
src/main/java/com/serhat/bankingtransactionapi

├── config
│   ├── SecurityConfig.java
│   ├── JwtAuthenticationFilter.java
│   └── OpenApiConfig.java
│
├── controller
│   ├── AccountController.java
│   └── AuthController.java
│
├── service
│   ├── AccountService.java
│   ├── AuthService.java
│   └── JwtService.java
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
│
├── exception
│
└── BankingTransactionApiApplication.java
```

---

# ⚙️ Requirements

Before running this project, install:

## Java

Check:

```bash
java -version
```

Recommended:

```
Java 17+
```

---

## Maven

Check:

```bash
mvn -version
```

---

## PostgreSQL

Create database:

```sql
CREATE DATABASE banking_db;
```

---

# 🔧 Configuration

Database configuration is located in:

```
src/main/resources/application.properties
```

Default local configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/banking_db
spring.datasource.username=postgres
spring.datasource.password=postgres123
```

You can change these values according to your PostgreSQL setup.

---

# ▶️ How to Run

## 1. Clone Repository

```bash
git clone https://github.com/otmanedd/banking-transaction-api.git
```

Go into the project:

```bash
cd banking-transaction-api
```

---

## 2. Build Project

macOS / Linux:

```bash
./mvnw clean package
```

Windows:

```bash
mvnw.cmd clean package
```

---

## 3. Start Application

macOS / Linux:

```bash
./mvnw spring-boot:run
```

The application runs on:

```
http://localhost:8080
```

---

# 📖 Swagger API Documentation

After starting the application, open:

```
http://localhost:8080/swagger-ui.html
```

Swagger provides an interactive interface to test all API endpoints.

---

# 🔐 Authentication Flow

The API uses JWT authentication.

## Step 1: Register User

Endpoint:

```
POST /auth/register
```

Example request:

```json
{
  "username": "otmane",
  "password": "password123"
}
```

Response:

```
User registered successfully
```

---

## Step 2: Login

Endpoint:

```
POST /auth/login
```

Example request:

```json
{
  "username": "otmane",
  "password": "password123"
}
```

Response:

```
JWT Token
```

---

## Step 3: Use Token

For protected endpoints add:

```
Authorization: Bearer YOUR_JWT_TOKEN
```

Example:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

# 💰 API Endpoints

## Authentication

### Register

```
POST /auth/register
```

### Login

```
POST /auth/login
```

---

# Accounts

## Create Account

```
POST /accounts
```

Example:

```json
{
  "accountNumber": "DE1001",
  "ownerName": "Otmane",
  "balance": 5000
}
```

---

## Get Accounts

Supports pagination:

```
GET /accounts?page=0&size=10
```

---

## Deposit Money

```
POST /accounts/deposit
```

---

## Withdraw Money

```
POST /accounts/withdraw
```

---

## Transfer Money

```
POST /accounts/transfer
```

---

# 🧪 Testing

Run tests:

```bash
./mvnw test
```

Testing technologies:

* JUnit 5
* Mockito

---

# ☁️ Deployment

The application can be deployed using cloud platforms:

* Railway
* Render
* AWS

Production database configuration uses environment variables:

```
PGHOST
PGPORT
PGDATABASE
PGUSER
PGPASSWORD
```

---

# 🔒 Security Improvements

For production environments:

* Store JWT secrets in environment variables
* Never commit passwords or secrets
* Use HTTPS
* Use secure database credentials

---

# 📚 Learning Outcomes

This project demonstrates:

✅ Building REST APIs with Spring Boot
✅ Designing backend architecture
✅ Implementing JWT authentication
✅ Working with PostgreSQL databases
✅ Managing financial transactions safely
✅ Writing unit tests
✅ Documenting APIs with Swagger
✅ Deploying backend applications

---

# 👨‍💻 Author

**Otmane Dyaf**

GitHub:

https://github.com/otmanedd

LinkedIn:

https://www.linkedin.com/in/otmane-dyaf-a1968b15b/
