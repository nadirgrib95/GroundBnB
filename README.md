# GroundBnB Backend

## Overview

GroundBnB is a backend application built with **Spring Boot** and **MySQL**, designed to manage property listings, customer accounts, reservations, and reviews. It exposes a set of RESTful APIs that can be consumed by a frontend application (e.g., Angular).

---

## Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA (Hibernate)**
* **Spring Security**
* **MySQL**
* **JWT (JSON Web Tokens)**
* **Maven**

---

## Features

* Customer registration and authentication
* Property listing management
* Reservation system with date validation
* Review system with rating constraints
* Secure API endpoints with JWT authentication
* Relational database design with foreign key constraints

---

## Project Structure

```
com.groundbnb
│
├─ config         → Security and configuration classes
├─ controller     → REST API endpoints
├─ dto            → Data Transfer Objects
├─ entity         → JPA entities (database models)
├─ repository     → JPA repositories (data access layer)
├─ security       → JWT utilities and filters
├─ service        → Business logic layer
└─ GroundBnBBackendApplication.java
```

---

## Database Schema

The application uses the following core tables:

* **customer** → stores user information
* **listing** → stores property data
* **reservation** → stores booking details
* **reviews** → stores user feedback and ratings

Key constraints:

* Unique email for customers
* Rating between 1 and 5
* One review per reservation
* Valid reservation dates (`check_out > check_in`)

---

## Setup Instructions

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd groundbnb-backend
```

### 2. Configure MySQL

Create the database and run your SQL schema:

```sql
CREATE DATABASE groundbnb_db;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/groundbnb_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### 3. Run the application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or from IntelliJ:

* Run `GroundBnBBackendApplication.java`

Server will start on:

```
http://localhost:8080
```

---

## API Endpoints (Examples)

### Authentication

```
POST /api/customers/signup
POST /api/customers/login
```

### Listings

```
GET /api/listings
GET /api/listings/city/{city}
POST /api/listings
```

### Reservations

```
POST /api/reservations
GET /api/reservations/customer/{id}
```

### Reviews

```
POST /api/reviews
GET /api/reviews/listing/{id}
```

---

## Authentication

* JWT-based authentication is used.
* After login, a token is returned.
* Include the token in requests:

```
Authorization: Bearer <your_token>
```

---

## Validation Rules

* Email must be unique
* Rating must be between 1 and 5
* Guest count must be greater than 0
* Check-out date must be after check-in date

---

## Future Improvements

* Image upload for listings
* Pagination and filtering
* Role-based access control (admin/customer)
* Payment integration
* Caching for performance optimization

---

## Notes

* Uses **JPA/Hibernate** for ORM
* Database constraints are enforced both at SQL and application levels
* Designed with scalability and modularity in mind

---
