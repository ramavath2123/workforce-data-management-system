# Workforce Data Management System

A Java 17 and Spring Boot REST API for managing employee profiles and organizational records.

## Features
- Employee CRUD operations
- Search by name, email, department, or role
- Pagination and sorting
- Department and role fields for organizational records
- Spring Data JPA / Hibernate persistence
- Bean Validation and centralized exception handling
- Controller-Service-Repository architecture
- H2 for local startup; MySQL profile for database-backed development
- API integration tests

## Run locally
Requirements: JDK 17+ and Maven 3.9+.

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

## Run with MySQL
Create a database using `CREATE DATABASE workforce_db;`, then set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` environment variables and run:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Endpoints
| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/employees` | Create employee |
| GET | `/api/employees/{id}` | Get employee |
| GET | `/api/employees` | List employees with pagination/sorting |
| GET | `/api/employees/search?keyword=...` | Search employees |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |

Example JSON:
```json
{
  "firstName": "Asha",
  "lastName": "Reddy",
  "email": "asha.reddy@example.com",
  "department": "Engineering",
  "role": "Software Engineer"
}
```

Run tests with `mvn test`. This is a portfolio/learning project; test locally before describing it as production-ready.
