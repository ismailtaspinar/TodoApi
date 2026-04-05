# TODO API

Production-ready REST API for todo management, built with Java and Spring Boot.

## Live Demo

- Base URL: `https://todo-api-03jc.onrender.com`
- Health check example: `GET https://todo-api-03jc.onrender.com/api/v1/todos`

## Project Highlights

- Clean CRUD endpoints for todo operations
- Layered architecture (Controller, Service, Repository)
- Input validation with clear API error responses
- Global exception handling for consistent output
- Render deployment configuration included (`Dockerfile`, `render.yaml`)
- Profile-based configuration for flexible environments

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- H2 (fallback profile for quick startup)
- Maven

## API Endpoints

- `POST /api/v1/todos` - Create todo
- `GET /api/v1/todos` - List todos
- `GET /api/v1/todos/{id}` - Get single todo
- `PUT /api/v1/todos/{id}` - Update todo
- `DELETE /api/v1/todos/{id}` - Delete todo

## Example Request

`POST /api/v1/todos`

```json
{
  "title": "Learn Spring Boot",
  "description": "Build and deploy a REST API",
  "done": false
}
```

## Local Run

```bash
./mvnw spring-boot:run
```

Default local URL:

- `http://localhost:8080`

## Deployment

Application is deployed on Render and can run with:

- `prod` profile (H2-based quick startup)
- `prod,prod-postgres` profile (PostgreSQL-backed persistent mode)

Render files included in repository root:

- `Dockerfile`
- `render.yaml`

## Notes

- Free Render instances may sleep after inactivity.
- First request after sleep can be slower (cold start).
