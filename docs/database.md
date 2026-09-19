# Database Architecture

The Banking Transaction API uses PostgreSQL as its relational database.

## Database Layer

The application uses Spring Data JPA and Hibernate to communicate with PostgreSQL.

The general data flow is:

Client → Controller → Service → Repository → PostgreSQL

## Repository Layer

Repositories are responsible for database access.

The service layer uses repositories to:

- create accounts
- retrieve accounts
- update account information
- access persistent banking data

## Transactions

Banking operations such as deposits, withdrawals and transfers should be executed transactionally to keep account data consistent.

The application uses Spring's `@Transactional` support for transaction management.

## Database Configuration

Database connection settings should be provided through environment variables instead of storing sensitive credentials directly in the repository.

Example environment variables:

- `PGHOST`
- `PGPORT`
- `PGDATABASE`
- `PGUSER`
- `PGPASSWORD`

