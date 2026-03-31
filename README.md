# Todo API (Spring Boot)
Basit bir Todo REST API projesi.  
Spring Boot, JPA ve PostgreSQL kullanır.
## Tech Stack
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- (Opsiyonel) Flyway
## Features
- Todo oluşturma
- Todo listeleme
- Tek todo getirme
- Todo güncelleme
- Todo silme
- Validation ve global error handling
- Pagination / filtering (varsa)
## API Base URL
Local:
`http://localhost:8080`  
(veya sende hangi port varsa)
## Endpoints
- `POST /api/v1/todos`
- `GET /api/v1/todos`
- `GET /api/v1/todos/{id}`
- `PUT /api/v1/todos/{id}`
- `DELETE /api/v1/todos/{id}`
## Sample Request (Create Todo)
```json
{
  "title": "Spring Boot ogren",
  "description": "Ilk todo kaydi",
  "done": false
}
