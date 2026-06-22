# EmployeeManagementSystem
# Employee Management System

A Spring Boot REST API project developed incrementally from basic CRUD operations to advanced concepts such as DTOs, Exception Handling, Validation, Logging, Swagger, Pagination, Custom Queries, and Entity Relationships.

## 🚀 Features

### Phase 1 - Basic CRUD
- Create Employee
- Get Employee by Id
- Get All Employees
- Update Employee
- Delete Employee

### Phase 2 - Intermediate Features
- DTO Layer (RequestDTO & ResponseDTO)
- Global Exception Handling
- Custom Exceptions
- Validation using Jakarta Validation
- Logging using SLF4J
- Swagger/OpenAPI Documentation
- Pagination and Sorting
- Custom Queries using Spring Data JPA
- One-To-Many and Many-To-One Relationships

## 🛠 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Swagger/OpenAPI
- SLF4J Logging
- Hibernate

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

## Entity Relationship

### Department ↔ Employee

```
Department (1)
    |
    |
    | One-To-Many
    |
Employee (Many)
```

- One Department can have many Employees.
- One Employee belongs to one Department.

## API Endpoints

### Department APIs

| Method | Endpoint | Description |
|----------|---------|------------|
| POST | /departments | Create Department |
| GET | /departments | Get All Departments |

### Employee APIs

| Method | Endpoint | Description |
|----------|---------|------------|
| POST | /employees/add | Create Employee |
| GET | /employees | Get All Employees |
| GET | /employees/{id} | Get Employee By Id |
| PUT | /employees/update/{id} | Update Employee |
| DELETE | /employees/{id} | Delete Employee |

### Pagination and Sorting

```
GET /employees/page-sort?page=0&size=5&field=name
```

### Custom Query APIs

#### Find Employee By Name

```
GET /employees/name?name=Ram
```

#### Find Employee By Department

```
GET /employees/department?department=IT
```

#### Find Salary Greater Than

```
GET /employees/salary-greater?salary=50000
```

#### Find Employees Between Salary Range

```
GET /employees/salary-range?minSalary=30000&maxSalary=80000
```

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

## Validation

- Name cannot be empty.
- Salary must be positive.
- Department must exist.

## Exception Handling

Global Exception Handling using:

- EmployeeNotFoundException
- RuntimeException
- MethodArgumentNotValidException

## Logging

Implemented using SLF4J.

Examples:

```java
log.info("Creating employee with name: {}", dto.getName());

log.info("Updating employee with id: {}", id);

log.error("Employee not found");
```

## Swagger Documentation

After running the application:

```
http://localhost:8080/swagger-ui/index.html
```

## Database Schema

### Department Table

| id | department_name |
|----|---------------|
| 1 | IT |
| 2 | HR |

### Employee Table

| id | name | salary | department_id |
|----|------|--------|---------------|
| 1 | Ram | 100000 | 1 |

## Future Enhancements

- Spring Security + JWT
- Audit Fields
- File Upload
- Caching
- Redis
- Kafka Integration
- Microservices Architecture
- API Gateway
- Eureka Server
- OpenFeign
- Docker
- CI/CD Pipeline
- Deployment to AWS

## Author

**Rajyasree**

Java | Spring Boot | Microservices | Kafka | Apache Flink | MongoDB

```

This README is suitable for a GitHub project and looks professional enough for interviews and recruiters.
