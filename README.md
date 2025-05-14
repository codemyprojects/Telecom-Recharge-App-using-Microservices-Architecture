# Telecom-Recharge-App-using-Microservices-Architecture

# Recharge App

## 🔍 Project Overview

The **Recharge App** is a **microservices-based system** developed using **Spring Boot** and **Spring Cloud**. It is designed to simulate a real-world mobile recharge or utility top-up system. The architecture includes multiple decoupled services that collaborate to provide a seamless and scalable solution.

---

## 🎯 Purpose of the Project

The main objective of this project is to demonstrate how to build a distributed system using modern microservices principles. It serves as both a learning tool and a foundational framework for:

- Managing **user authentication** and **authorization**
- Handling **payment** and **recharge** operations
- Sending **notifications** (SMS, email, etc.)
- Logging and **auditing** user actions
- Leveraging **API Gateway** and **Service Discovery** for scalability and routing

This architecture is reflective of real-world enterprise solutions used by telecom companies and digital wallet services.

---

## 🏗️ Microservices Included

1. **API Gateway**  
   - Routes requests to appropriate microservices  
   - Applies security and rate limiting

2. **Auth Service**  
   - Handles login, registration, and token generation (likely using JWT)

3. **Recharge Service**  
   - Core logic for mobile recharges or utility top-ups

4. **Payment Service**  
   - Processes payments and integrates with external payment providers

5. **Operator Service**  
   - Maintains information about telecom or utility operators

6. **Notification Service**  
   - Sends emails, SMS, or in-app alerts

7. **Audit Service**  
   - Logs user activities for compliance and troubleshooting

8. **Service Registry (Eureka)**  
   - Provides service discovery and monitoring capabilities

---

## ⚙️ Tech Stack

- **Java 17+**
- **Spring Boot 3.4.5**
- **Spring Cloud**
- **Eureka for Service Discovery**
- **Maven for Build Automation**
- **Spring Security for Authentication**
- **Spring Web & OpenFeign for HTTP Communication**

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.8+
- Docker (optional, if containerizing)

### Run Locally

```bash
# Navigate to the root project
cd recharge-app

# Build the project
./mvnw clean install

# Run each service (example for auth-service)
cd auth-service
./mvnw spring-boot:run
```

> Each microservice can be started individually. Eureka service registry and API gateway should be started first.

---

## 📌 Future Enhancements

- Integrate Kafka for asynchronous communication
- Add centralized configuration with Spring Cloud Config
- Implement OAuth2-based authorization
- Add UI front-end with Angular or React
