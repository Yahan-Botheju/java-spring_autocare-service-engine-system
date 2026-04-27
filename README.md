# 🚗 Vehicle Service Management System

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-brightgreen)
![Gradle](https://img.shields.io/badge/Build-Gradle-orange)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![H2](https://img.shields.io/badge/TestDB-H2-lightgrey)

---

# 📌 Overview

This project is a **Spring Boot REST API** designed using **Clean Architecture principles**.

It manages **vehicle service operations** including customers, vehicles, and service tracking.

The system demonstrates:

- Layered architecture (Domain → UseCase → Infrastructure → Web)
- DTO + MapStruct mapping
- Role-based authorization via interceptors
- Business rule enforcement in domain models
- Exception handling with centralized handler
- Pagination support

---

# 📌 Features

- Customer management (CRUD)
- Vehicle management (CRUD)
- Vehicle service tracking (PENDING → IN_PROGRESS → COMPLETED)
- Automatic next service date calculation
- Soft delete support using Hibernate
- Pagination support
- Role-based access control (ADMIN)
- Global exception handling
- Standard API response structure
- Clean Architecture implementation
- Swagger API documentation
- Rich Domain Models

---

# 🛠️ Tech Stack

| Category           | Technology                      |
|------------------|--------------------------------|
| Language         | Java 21                         |
| Framework        | Spring Boot 3.4                 |
| Build Tool       | Gradle                          |
| Database         | PostgreSQL / H2                 |
| ORM              | Spring Data JPA / Hibernate     |
| Mapping          | MapStruct                       |
| API Docs         | Springdoc OpenAPI               |
| Security         | Spring Security                 |
| Boilerplate      | Lombok                          |
| Architecture     | Clean Architecture              |

---

# 🏗️ Architecture Overview

The project follows **Clean Architecture**:

```text id="c9a1q1"
Controller → UseCase → Domain → Repository Interface
                                 ↓
                          Infrastructure (JPA)
```

---

## 🔧 Dependency Injection Strategy (Loose Coupling)

This project uses **explicit Bean Configuration classes** instead of relying only on annotations like `@Service` or `@Repository`.

This ensures the system remains:

- Loosely coupled
- Easily testable
- Fully aligned with Clean Architecture principles

---

### 📌 Why This Approach?

Dependencies are wired manually using:

- `@Configuration`
- `@Bean`

This allows:

- UseCase layer to depend only on interfaces
- Infrastructure layer to provide implementations
- Easy replacement of components (e.g., switching database)

---

### 🏗️ Bean Wiring Example

#### 👤 Customer Module

```java
@Configuration
class CustomerBeanConfig {

    @Bean
    CustomerUseCase customerUseCase(CustomerRepository customerRepository) {
        return new CustomerUseCaseImpl(customerRepository);
    }
}
````

```java
@Configuration
class CustomerPersistenceBeanConfig {

    @Bean
    CustomerRepository customerRepository(
            JpaCustomerRepository jpaCustomerRepository,
            CustomerPersistenceMapper customerPersistenceMapper
    ) {
        return new CustomerRepositoryImpl(jpaCustomerRepository, customerPersistenceMapper);
    }
}
```
---
#### 🚗 Vehicle Module

```java
@Configuration
class VehicleBeanConfig {

    @Bean
    VehicleUseCase vehicleUseCase(
            VehicleRepository vehicleRepository,
            CustomerRepository customerRepository) {
        return new VehicleUseCaseImpl(vehicleRepository, customerRepository);
    }
}
```
```java
@Configuration
class VehiclePersistenceBeanConfig {

    @Bean
    VehicleRepository vehicleRepository(
            JpaVehicleRepository jpaVehicleRepository,
            VehiclePersistenceMapper vehiclePersistenceMapper
    ) {
        return new VehicleRepositoryImpl(jpaVehicleRepository, vehiclePersistenceMapper);
    }
}
```
---
### 🧠 Dependency Flow

```
UseCase (Business Logic)
        ↓ depends on
Repository Interface
        ↓ implemented by
Infrastructure (JPA)
```
---
### 🚀 Advantages
 * Framework-independent business logic
 * Easier unit testing (mock interfaces)
 * Flexible implementation replacement
 * Strong separation of concerns

---

### 💡 Design Note

 * The UseCase layer avoids using @Service to remain framework-independent.

---

### 🔄 Request Flow

```
Client
 ↓
Controller (DTO)
 ↓
Mapper → Domain
 ↓
UseCase
 ↓
Repository Interface
 ↓
Infrastructure (JPA)
 ↓
Database
 ↓
Response (DTO)

```

---

# 📂 Layers Explanation

## 📌 Domain Layer

Contains core business logic.

### Models

- Customer
- Vehicle
- VehicleFuelType (Enum)
- VehicleServiceStatus (Enum)
- VehicleUpdateResult

### Key Business Logic

- Next service date = lastServiceDate + 6 months
- Default vehicle status = PENDING
- Prevent update if service is COMPLETED

### Repositories

- CustomerRepository
- VehicleRepository

---

## 📌 UseCase Layer

Handles application logic.

### CustomerUseCase

- Get all customers
- Save customer
- Update customer
- Delete customer

### VehicleUseCase

- Get all vehicles
- Register vehicle
- Update vehicle
- Delete vehicle

### Rules

- Customer must exist before assigning vehicle
- Completed vehicles cannot be updated
- Service date auto-calculated

---

## 📌 Infrastructure Layer

Handles database operations.

### Customer

- CustomerEntity
- JpaCustomerRepository
- CustomerRepositoryImpl
- CustomerPersistenceMapper

### Vehicle

- VehicleEntity
- JpaVehicleRepository
- VehicleRepositoryImpl
- VehiclePersistenceMapper

---

## 📌 Web Layer

Handles HTTP requests.

### Controllers

- CustomerController
- VehicleController

### DTOs

- CustomerRequestDTO / CustomerResponseDTO
- VehicleRequestDTO / VehicleResponseDTO / VehicleShortInfoDTO

### Mappers

- CustomerWebMapper
- VehicleWebMapper

### Interceptors

- CustomerInterceptor
- VehicleInterceptor

---

## 📌 Security

- Spring Security configured
- CSRF disabled
- Swagger endpoints allowed
- Role-based control via interceptors

---

## 📌 Global Handling

### Exception Handling

Handled using:

````
- ResourceNotFoundException → 404
- ServiceAlreadyCompletedException → 409
- ForbiddenAccessException → 403
- Generic Exception → 500
````
---
### Standard Response

Fields:

- statusCode
- message
- timestamp
- data

---

# 🚀 Features in Detail


- Pagination using PageRequest
- Soft delete using Hibernate
- DTO mapping using MapStruct
- Domain-driven business logic
- Interceptor-based authorization
- Automatic service scheduling

---

# 📂 Project Structure

````
src/main/java/lk/autocare/vehicle_service_system
├── 📁 domain
│   ├── 📁 models
│   │   ├── Customer.java
│   │   ├── Vehicle.java
│   │   └── VehicleServiceStatus.java
│   └── 📁 repositories
│       ├── CustomerRepository.java
│       └── VehicleRepository.java
├── 📁 usecase
│   ├── 📁 customer
│   │   ├── CustomerUseCase.java
│   │   └── CustomerUseCaseImpl.java
│   └── 📁 vehicle
│       ├── VehicleUseCase.java
│       └── VehicleUseCaseImpl.java
├── 📁 infrastructure
│   ├── 📁 customer
│   │   ├── 📁 persistence
│   │   │   ├── 📁 entity (CustomerEntity.java)
│   │   │   └── CustomerRepositoryImpl.java
│   │   └── 📁 config
│   └── 📁 vehicle
├── 📁 web
│   ├── 📁 customer
│   │   ├── 📁 controllers (CustomerController.java)
│   │   └── 📁 DTOs
│   └── 📁 vehicle
├── 📁 GlobalExceptionHandler
│   └── GlobalExceptionHandler.java
├── 📁 GlobalResponseHandler
│   └── StandardResponse.java
└── 📁 spring_security
    └── SecurityConfig.java

`````

---

# ⚙️ API Endpoints


## 👤 Customer APIs

| Method | Endpoint | Description |
|-------|--------|------------|
| GET | /api/v1/autocare/customers/all | Get all customers |
| POST | /api/v1/autocare/customers/register | Register customer |
| PUT | /api/v1/autocare/customers/{id} | Update customer |
| DELETE | /api/v1/autocare/customers/{id} | Delete customer |

---

## 🚗 Vehicle APIs

| Method | Endpoint | Description |
|-------|--------|------------|
| GET | /api/v1/autocare/vehicles/all | Get all vehicles |
| POST | /api/v1/autocare/vehicles/register | Register vehicle |
| PUT | /api/v1/autocare/vehicles/{id} | Update vehicle |
| DELETE | /api/v1/autocare/vehicles/{id} | Delete vehicle |

---

# 🔐 Authorization
## 🔐 Authorization

| Action | Access |
|------|--------|
| View all customers | ADMIN |
| Delete customer | ADMIN |
| Manage vehicles | ADMIN |

 #### Header required:  role: ADMIN

---

# 📚 API Documentation

Swagger UI:

```text id="s2k9x1"
http://localhost:5000/swagger-ui/index.html
```
OpenAPI JSON:
```text id="s2k9x1"
http://localhost:5000/v3/api-docs
```

---

# 📥 Sample Request

```json
{
  "vehicleNumber": "CAB-1234",
  "vehicleModel": "Toyota Prius",
  "vehicleFuelType": "HYBRID",
  "mileage": 50000,
  "lastServiceDate": "2025-01-01",
  "customerId": 1
}

````
---
# 📤 Sample Response

```json id="r2k8v3"
{
  "vehicleId": 1,
  "vehicleNumber": "CAB-1234",
  "vehicleModel": "Toyota Prius",
  "vehicleFuelType": "HYBRID",
  "lastServiceDate": "2025-01-01",
  "nextServiceDate": "2025-07-01",
  "mileage": 50000,
  "customerId": 1,
  "customerName": "John Doe",
  "vehicleServiceStatus": "PENDING"
}
```
---


# ▶️ How to Run

### 1. Clone repository

```bash id="c1m8x2"
git clone https://github.com/yahan-botheju/vehicle-service-system.git
```

### 2. Navigate to project

```bash id="c2p9v1"
cd vehicle-service-system
```

### 3. Run application

```bash id="c3k1x7"
./gradlew bootRun
```

---

# 👨‍💻 Author

Developed as a Clean Architecture practice project focusing on:

 - Real-world backend structure
 - Scalable system design
 - Separation of concerns
---

# ⭐ Project Purpose

This project was built to practice:

 - Clean Architecture
 - Spring Boot advanced structuring
 - DTO & mapping strategies
 - Business rule implementation
 - API design best practices
---

# 📌 Note

 - This is a learning-focused project, but the architecture is strong enough to evolve into a production-ready system.

