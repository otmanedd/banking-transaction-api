# Authentication

The Banking Transaction API uses JWT-based authentication.

## Authentication Flow

1. A user registers through `/auth/register`.
2. The user logs in through `/auth/login`.
3. The API returns a JWT token.
4. The client sends the token with protected requests.
5. Spring Security validates the token before allowing access.

## JWT Request

Protected endpoints require the following HTTP header:

`Authorization: Bearer YOUR_JWT_TOKEN`

## Security

Passwords are protected using BCrypt.

JWT authentication is handled through Spring Security.

Sensitive configuration such as database credentials and JWT secrets should be provided through environment variables.
