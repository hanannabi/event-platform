## Event Management Microservices System

A production-ready microservices-based Event Management platform built using **Java 17, Spring Boot, Docker**, and **GitHub Actions CI/CD**, deployed on **Render**.

---

## Features

- Two independently deployable microservices:
  - **User Service**
  - **Event Management Service**
- **RESTful APIs** for user registration, login, event creation, event search.
- **Event-driven architecture** using **Apache Kafka** (user-created & event-created topics).
- **Containerized** using Docker with multi-stage builds.
- Automated **CI/CD pipelines** using GitHub Actions:
  - Build → Test → Docker Image → Push to Docker Hub → Deploy to Render.
- **MySQL** databases for persistent storage.
- **Postman Collection** included for API testing.
- Clean architecture with layered approach: controller → service → repository → entity.

##  Postman Collection: ./postman/Event-Management-API-Collection.json
---

