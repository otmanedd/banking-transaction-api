# API Documentation

## Authentication

### Register User

`POST /auth/register`

Creates a new user account.

### Login

`POST /auth/login`

Authenticates a user and returns a JWT token.

---

## Accounts

### Create Account

`POST /accounts`

Creates a new bank account.

### Get Accounts

`GET /accounts`

Returns accounts with pagination support.

Example:

`GET /accounts?page=0&size=10`

### Get Account by ID

`GET /accounts/{id}`

Returns a specific bank account.

### Deposit Money

`POST /accounts/deposit`

Deposits money into an account.

### Withdraw Money

`POST /accounts/withdraw`

Withdraws money from an account.

### Transfer Money

`POST /accounts/transfer`

Transfers money between two accounts.

---

## Authentication

Protected endpoints require a JWT token:

`Authorization: Bearer YOUR_JWT_TOKEN`
