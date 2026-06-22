# EmployeeManagementSystem



A production-oriented Spring Boot REST API project built incrementally from basic CRUD operations to advanced concepts such as DTOs, Validation, Exception Handling, Logging, Swagger, Pagination, Custom Queries, Entity Relationships, and Auditing.

---

## 🚀 Features

### Phase 1 - Basic CRUD
- Create Employee
- Get Employee by Id
- Get All Employees
- Update Employee
- Delete Employee

---

### Phase 2 - Intermediate Features

#### DTO Layer
- Request DTO
- Response DTO
- Entity ↔ DTO Mapping

#### Exception Handling
- Global Exception Handling
- Custom Exceptions
- Validation Exception Handling

#### Validation
- Name cannot be empty
- Salary must be positive

#### Logging
- SLF4J Logging
- INFO and ERROR Logs

#### API Documentation
- Swagger / OpenAPI

#### Pagination & Sorting
- Pageable APIs
- Sorting by any field

#### Custom Queries
- Find Employee By Name
- Find Employee By Department
- Find Employees By Salary Range
- Find Employees With Salary Greater Than

#### Relationships
- One-To-Many
- Many-To-One

Department ↔ Employee Relationship

```
Department (1)
     |
     | One-To-Many
     |
Employee (Many)
```

---

### Phase 3 - Advanced Features

#### Auditing
Automatically maintains:

- createdAt
- updatedAt

using:

- `@PrePersist`
- `@PreUpdate`

---

## 🛠 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Swagger/OpenAPI
- SLF4J Logging
- Jakarta Validation

---

## 📂 Project Structure

```
src/main/java
│
├── controller
├── service
├── repository
├── entity
├── dto
├── mapper
├── exception
├── config
└── EmployeeManagementApplication
```

---

## Database Schema

### Department Table

| id | department_name |
|----|---------------|
| 1 | IT |
| 2 | HR |

---

### Employee Table

| id | name | salary | department_id | created_at | updated_at |
|----|------|--------|---------------|------------|------------|
| 1 | Ram | 100000 | 1 | 2026-06-22 10:00 | 2026-06-22 10:00 |

---

## API Endpoints

### Department APIs

| Method | Endpoint | Description |
|----------|----------|------------|
| POST | /departments | Create Department |
| GET | /departments | Get All Departments |

---

### Employee APIs

| Method | Endpoint | Description |
|----------|----------|------------|
| POST | /employees/add | Create Employee |
| GET | /employees | Get All Employees |
| GET | /employees/{id} | Get Employee By Id |
| PUT | /employees/update/{id} | Update Employee |
| DELETE | /employees/{id} | Delete Employee |

---

### Pagination and Sorting

```http
GET /employees/page-sort?page=0&size=5&field=name
```

---

### Custom Query APIs

#### Find Employee By Name

```http
GET /employees/name?name=Ram
```

#### Find Employee By Department

```http
GET /employees/department?department=IT
```

#### Find Employees With Salary Greater Than

```http
GET /employees/salary-greater?salary=50000
```

#### Find Employees Between Salary Range

```http
GET /employees/salary-range?minSalary=30000&maxSalary=80000
```

---

## Request Examples

### Create Department

```json
{
  "departmentName": "IT"
}
```

### Create Employee

```json
{
  "name": "Ram",
  "departmentId": 1,
  "salary": 100000
}
```

### Update Employee

```json
{
  "name": "Ramesh",
  "departmentId": 2,
  "salary": 120000
}
```

---

## Validation

### EmployeeRequestDto

```java
@NotBlank(message = "Name should not be empty")
private String name;

@Positive(message = "Salary should be positive")
private Double salary;
```

---

## Exception Handling

Implemented using:

- EmployeeNotFoundException
- RuntimeException
- MethodArgumentNotValidException
- GlobalExceptionHandler

---

## Logging

Implemented using SLF4J

Examples:

```java
log.info("Creating employee with name: {}", dto.getName());

log.info("Updating employee with id: {}", id);

log.error("Employee not found");
```

---

## Swagger Documentation

After starting the application:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Auditing

Employee records automatically maintain:

```java
private LocalDateTime createdAt;
private LocalDateTime updatedAt;
```

using:

```java
@PrePersist
public void prePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
}

@PreUpdate
public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
}
```

---

## Future Enhancements

### Security
- Spring Security
- JWT Authentication
- Role-Based Authorization
- BCrypt Password Encryption

### Performance
- Caching
- Redis

### Additional Features
- File Upload
- Email Service
- Scheduler
- Async Processing

### Microservices
- Eureka Server
- API Gateway
- OpenFeign
- Resilience4J

### Messaging
- Kafka Integration

### Deployment
- Docker
- CI/CD Pipeline
- AWS Deployment

---

## Author

### Rajyasree

**Software Engineer | Java | Spring Boot | Microservices | Kafka | Apache Flink | MongoDB**

---

## Current Progress

### Phase 1 ✅ Completed
### Phase 2 ✅ Completed
### Phase 3 🚧 In Progress

- ✅ Audit Fields
- ⬜ Spring Security + JWT Authentication
- ⬜ Role-Based Authorization
- ⬜ Password Encryption
- ⬜ File Upload
- ⬜ Redis
- ⬜ Caching
- ⬜ Kafka Integration
- ⬜ Docker
- ⬜ Deployment

---
