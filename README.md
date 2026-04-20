# Quantity Measurement App – Backend Microservices

A production-ready backend application built using **Spring Boot Microservices Architecture**.
This project is designed to manage secure authentication, quantity measurement operations, and centralized API routing using separate independent services.

It demonstrates real-world backend development concepts such as **Microservices**, **JWT Authentication**, **Spring Security**, **API Gateway**, and scalable deployment practices.

---

## 🚀 Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Cloud Gateway
* Spring Data JPA
* Hibernate ORM
* MySQL / PostgreSQL
* Maven
* Swagger / OpenAPI
* OAuth2 Login (Google)
* REST APIs

---

## 📌 Microservices Architecture

```text
Frontend (Angular / React)
        |
        v
   API Gateway
     /     \
    v       v
Auth Service   QMA Service
```

This architecture separates different responsibilities into multiple services, making the system scalable, maintainable, and production-ready.

---

## 🧩 Services Included

### 🔐 Auth Service

Responsible for authentication and user management.

#### Features:

* User Registration
* User Login
* Secure Password Encryption using BCrypt
* JWT Access Token Generation
* Refresh Token Support
* Role-Based Authorization
* Google OAuth2 Login
* User Validation APIs

#### Main Purpose:

Ensures only authenticated users can access protected APIs.

---

### 📦 QMA Service

Responsible for business logic related to quantity measurement operations.

#### Features:

* Create Data
* Update Data
* Delete Records
* Fetch Data
* Protected APIs using JWT
* User-specific operations

#### Main Purpose:

Handles the core functionality of the application.

---

### 🌐 API Gateway

Acts as a single entry point for all frontend requests.

#### Features:

* Routes requests to microservices
* JWT Token Validation
* Centralized API Access
* Cross-Origin Support (CORS)
* Security Filter Integration

#### Main Purpose:

Frontend communicates only with gateway instead of calling multiple services directly.

---

## 🔒 Security Features

* JWT Authentication
* Access Token + Refresh Token
* BCrypt Password Hashing
* Role-Based Access Control
* Protected REST APIs
* Spring Security Filters
* OAuth2 Login Support

---

## 📁 Project Structure

```text
backend/
│── auth-service/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── security/
│
│── qma-service/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   └── entity/
│
│── api-gateway/
│   └── security/
│
└── README.md
```

---

## 📖 API Documentation

Swagger UI available at:

```text
https://api-gateway-zp6d.onrender.com/webjars/swagger-ui/index.html
https://qma-service-9m9a.onrender.com/swagger-ui/index.html
https://auth-service-2t39.onrender.com/swagger-ui/index.html
```

Use Swagger UI to test APIs directly from browser.

---


## 💡 Real World Concepts Used

* Microservices Communication
* Authentication & Authorization
* Token Based Security
* API Gateway Routing
* Backend Scalability
* Production Deployment

---

## 👨‍💻 Author

**Aryan Malik**

