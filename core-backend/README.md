# AegisSight Core Backend

AegisSight Core Backend is a Spring Boot modular monolith for the AegisSight prototype. It receives structured AI inference results, validates and stores detections, creates incidents, generates alerts, and exposes dashboard APIs for frontend integration.

## Architecture

The codebase is organized by backend module:

- `auth`: operator login and JWT authentication
- `camera`: backend-managed camera/source metadata
- `detection`: AI inference ingestion
- `incident`: incident listing, detail, critical incidents, and lifecycle updates
- `alert`: alert listing generated from incidents
- `common`: shared enums, response DTOs, validation helpers, and exception handling

Each module follows the existing flow:

```text
Controller -> Service / UseCase -> Repository -> Database
```

## Prerequisites

- Java 21
- Maven Wrapper included in this repository
- PostgreSQL 14+

## Configuration

Create a local `.env` or export the variables shown in `.env.example`.

Required variables:

- `DB_URL`: PostgreSQL JDBC URL
- `DB_USERNAME`: database username
- `DB_PASSWORD`: database password
- `JWT_SECRET`: JWT signing secret, at least 32 random bytes

Optional variables:

- `SERVER_PORT`: defaults to `8080`
- `JWT_EXPIRATION_SECONDS`: defaults to `86400`
- `CORS_ALLOWED_ORIGINS`: comma-separated frontend origins
- `JPA_SHOW_SQL`: defaults to `false`

## Database Setup

Create the PostgreSQL database and user, then start the application. Flyway applies migrations from `src/main/resources/db/migration`.

```powershell
.\mvnw.cmd spring-boot:run
```

Default seeded operator:

- Username: `operator@aegissight.local`
- Password: `password123`

## Running Tests

Tests use an in-memory H2 database with the `test` profile.

```powershell
.\mvnw.cmd clean test
```

## API Overview

All API routes are under `/api`.

Public routes:

- `POST /api/auth/login`
- `GET /api/health`

Authenticated routes:

- `POST /api/detections`
- `GET /api/incidents`
- `GET /api/incidents/{id}`
- `PUT /api/incidents/{id}/status`
- `GET /api/incidents/critical`
- `GET /api/alerts`
- `GET /api/cameras`
- `POST /api/cameras`

## Authentication

Login request:

```http
POST /api/auth/login
Content-Type: application/json
```

```json
{
  "username": "operator@aegissight.local",
  "password": "password123"
}
```

Use the returned token on protected calls:

```http
Authorization: Bearer <accessToken>
```

## API Contract

The backend API contract lives at:

```text
../docs/api-contract/backend-api-contract.md
```
