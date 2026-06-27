# Task API

A RESTful CRUD API built with Spring Boot 4 for managing tasks, with a prebuilt React frontend served via Docker.

## Tech Stack

- **Java 25** with Spring Boot 4.1.0
- **Spring Data JPA** with H2 in-memory database
- **Spring Validation** for request validation
- **Docker** for the frontend UI

## Getting Started

### Prerequisites

- Java 21+ (JetBrains Runtime recommended)
- Maven (or use the included `./mvnw` wrapper)
- Docker (for the frontend UI)

### Run the API

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

### Run the Frontend UI

```bash
docker compose up
```

The UI will be available at `http://localhost:3000`.

> The frontend connects to the Spring Boot API running on `localhost:8080`.

## API Endpoints

Base URL: `/api/v1/tasks`

| Method | Endpoint              | Description       | Status |
|--------|-----------------------|-------------------|--------|
| GET    | `/api/v1/tasks`       | List all tasks    | 200    |
| POST   | `/api/v1/tasks`       | Create a task     | 201    |
| PUT    | `/api/v1/tasks/{id}`  | Update a task     | 200    |
| DELETE | `/api/v1/tasks/{id}`  | Delete a task     | 204    |

## Request & Response Examples

### Create a Task — `POST /api/v1/tasks`

**Request:**
```json
{
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "priority": "HIGH"
}
```

**Response `201 Created`:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "priority": "HIGH",
  "status": "TODO"
}
```

### List Tasks — `GET /api/v1/tasks`

**Response `200 OK`:**
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Buy groceries",
    "priority": "HIGH",
    "status": "TODO"
  }
]
```

### Update a Task — `PUT /api/v1/tasks/{id}`

**Request:**
```json
{
  "title": "Buy groceries",
  "description": "Milk, eggs, bread, butter",
  "priority": "MEDIUM",
  "status": "IN_PROGRESS"
}
```

**Response `200 OK`:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Buy groceries",
  "priority": "MEDIUM",
  "status": "IN_PROGRESS"
}
```

### Delete a Task — `DELETE /api/v1/tasks/{id}`

**Response `204 No Content`** — no body returned.

## Error Handling

All errors return a consistent shape:

```json
{
  "message": "Task with id '550e8400-e29b-41d4-a716-446655440000' not found."
}
```

| Scenario            | Status |
|---------------------|--------|
| Task not found      | 404    |
| Validation failure  | 400    |

## Project Structure

```
src/
└── main/
    └── java/com/darktrace/task/
        ├── controller/        # REST controllers & exception handlers
        ├── domain/            # DTOs, request/response objects
        │   ├── dto/
        │   └── entity/
        ├── exception/         # Custom exceptions
        ├── mapper/            # Entity <-> DTO mappers
        ├── repository/        # Spring Data JPA repositories
        └── service/           # Business logic
            └── impl/
```

## Database

Uses **H2 in-memory database** — data resets on every restart. The H2 console is available at:

```
http://localhost:8080/h2-console
```

## Running with curl

```bash
# List all tasks
curl http://localhost:8080/api/v1/tasks

# Create a task
curl -X POST http://localhost:8080/api/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "My task", "priority": "HIGH"}'

# Update a task
curl -X PUT http://localhost:8080/api/v1/tasks/{id} \
  -H "Content-Type: application/json" \
  -d '{"title": "Updated task", "priority": "LOW", "status": "DONE"}'

# Delete a task
curl -X DELETE http://localhost:8080/api/v1/tasks/{id}
```
