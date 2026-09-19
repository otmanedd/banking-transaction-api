# Testing Strategy

The Banking Transaction API uses automated tests to verify application behavior.

## Testing Framework

The project uses:

- JUnit 5
- Mockito
- Spring Boot testing support

## Test Areas

The tests can cover important application areas such as:

- authentication
- account creation
- deposits
- withdrawals
- money transfers
- validation
- exception handling

## Unit Testing

Mockito can be used to isolate services from external dependencies such as repositories.

This allows individual business logic to be tested independently.

## Running Tests

Tests can be executed with Maven:

`./mvnw test`

A successful test run helps ensure that existing functionality continues to work after changes.
