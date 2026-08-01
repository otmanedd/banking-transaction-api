# Banking Transaction API 💳

A production-style **RESTful Banking API** built with **Java 17, Spring Boot, PostgreSQL, JWT Authentication, and Swagger/OpenAPI**.

This project simulates real-world banking operations including user authentication, account management, deposits, withdrawals, and money transfers.

The application follows a clean layered architecture and demonstrates backend development best practices such as DTO design, validation, exception handling, security, testing, and API documentation.

---

# 🚀 Features

## 🔐 Authentication & Security

- User registration
- User login
- JWT-based authentication
- Protected API endpoints
- BCrypt password encryption
- Spring Security integration

Authentication flow:

```
Register User
      |
      ↓
Login
      |
      ↓
Receive JWT Token
      |
      ↓
Access Protected APIs
```

---

# 💰 Banking Operations

The API supports:

- Create bank accounts
- Retrieve accounts with pagination
- Get account by ID
- Deposit money
- Withdraw money
- Transfer money between accounts

---

# ⚙️ Backend Features

- RESTful API design
- Clean layered architecture
- DTO pattern
- Input validation
- Global exception handling
- Transaction management using `@Transactional`
- Pagination with Spring Data JPA
- Logging with SLF4J
- Unit testing with JUnit 5 and Mockito

---

# 📖 Documentation

Interactive API documentation:

- Swagger UI
- OpenAPI 3.1

---

# 🏗️ Architecture

The project follows a clean layered architecture:

```
                 Client
                   |
                   ↓
            Controller Layer
                   |
                   ↓
             Service Layer
                   |
                   ↓
          Repository Layer
                   |
                   ↓
          PostgreSQL Database
```

---

# 📂 Project Layers

## Controller Layer

Responsible for handling HTTP requests and returning responses.

Examples:

```
POST /auth/login
POST /accounts
GET  /accounts
POST /accounts/transfer
```

---

## Service Layer

Contains the business logic.

Responsibilities:

- Creating accounts
- Authentication logic
- Checking account balances
- Processing transfers
- Handling banking operations

---

## Repository Layer

Responsible for database communication using:

- Spring Data JPA
- Hibernate

---

## Entity Layer

Represents database tables.

Main entities:

```
User
Account
```

---

## DTO Layer

Separates API request/response objects from database entities.

Benefits:

- Better security
- Cleaner API design
- Prevents exposing database models directly

---

# 🛠️ Technologies

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Spring Boot | Backend framework |
| Spring Security | Authentication & authorization |
| JWT | Secure authentication |
| Spring Data JPA | Database access |
| Hibernate | ORM framework |
| PostgreSQL | Database |
| Maven | Dependency management |
| Swagger/OpenAPI | API documentation |
| JUnit 5 | Testing |
| Mockito | Mock testing |
| Railway | Deployment |

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

Before running the project, install:

## Java

Check:

```bash
java -version
```

Required:

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

Database configuration:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/banking_db
spring.datasource.username=postgres
spring.datasource.password=postgres123
```

Update credentials according to your PostgreSQL setup.

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

Application starts:

```
http://localhost:8080
```

---

# 📖 Swagger API Documentation

After starting the application:

Open:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger allows testing all REST endpoints directly from the browser.

---

# 🔐 Authentication API

## Register User

Endpoint:

```
POST /auth/register
```

Request:

```json
{
  "username": "otmane",
  "password": "password123"
}
```

---

## Login

Endpoint:

```
POST /auth/login
```

Request:

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

## Access Protected Endpoints

Add token:

```
Authorization: Bearer YOUR_JWT_TOKEN
```

Example:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

# 💳 API Endpoints

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

## Get Account By ID

```
GET /accounts/{id}
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

- JUnit 5
- Mockito

---

# ☁️ Deployment

The application can be deployed using:

- Railway
- Render
- AWS

Production configuration should use environment variables:

```
PGHOST
PGPORT
PGDATABASE
PGUSER
PGPASSWORD
JWT_SECRET
```

---

# 🔒 Security Improvements

For production environments:

- Store JWT secrets in environment variables
- Never commit passwords or secrets
- Use HTTPS
- Use secure database credentials
- Implement refresh tokens
- Add rate limiting
- Add audit logging

---

# 📚 Learning Outcomes

This project demonstrates:

✅ Building REST APIs with Spring Boot  
✅ Designing backend architecture  
✅ Implementing JWT authentication  
✅ Working with PostgreSQL databases  
✅ Handling financial transactions safely  
✅ Writing unit tests  
✅ Documenting APIs with Swagger  
✅ Applying clean code principles  

---

# 👨‍💻 Author

**Otmane Dyaf**

GitHub:

https://github.com/otmanedd

LinkedIn:

https://www.linkedin.com/in/otmane-dyaf-a1968b15b/
