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
```

## Ucretsiz API Yayina Alma (Once yayinla, sonra DB bagla)
Bu projeyi ucretsiz sekilde yayinlamak icin repository'e `Dockerfile` ve `render.yaml` eklendi.
Uygulama artik veritabani bilgisi olmadan da acilir (gecici olarak H2 in-memory kullanir).

### 1) Projeyi GitHub'a push et
- Bu projeyi GitHub repository'ne gonder.

### 2) Render'da deploy et
- [Render](https://render.com) hesabina gir.
- **New + -> Blueprint** sec ve bu repository'i bagla.
- Render, otomatik olarak `render.yaml` dosyasini okuyup servisi olusturacak.
- Bu asamada DB env var'larini girmesen de deploy olur.
- `SPRING_PROFILES_ACTIVE=prod` zaten `render.yaml` icinde set ediliyor.

### 3) API'yi test et
Deploy tamamlandiktan sonra Render sana bir URL verecek:
- `GET https://<senin-servisin>.onrender.com/api/v1/todos`

### 4) Sonra PostgreSQL bagla (Neon)
- [Neon](https://neon.tech) uzerinde ucretsiz PostgreSQL olustur.
- Render servisinde Environment kisimina su degiskenleri gir:
  - `DB_URL` (JDBC format: `jdbc:postgresql://...`)
  - `DB_USERNAME`
  - `DB_PASSWORD`
- Opsiyonel: Flyway migration acmak istersen `FLYWAY_ENABLED=true` ekle.

### Notlar
- Render free planda servis bir sure trafik olmazsa uykuya gecebilir.
- Ilk isteklerde soguk baslangic sebebiyle kisa bir gecikme olabilir.
- H2 gecici oldugu icin servis yeniden baslarsa veriler sifirlanir. Kalici veri icin Postgres baglamalisin.
