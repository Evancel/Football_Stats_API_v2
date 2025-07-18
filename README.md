# ⚽ Football Statistics API v2

A backend REST API for managing comprehensive football data, built with **Spring Boot** and designed following **Clean Architecture** and **Domain-Driven Design (DDD)** principles. This project offers a scalable and testable solution to handle complex sports data.

---

## 🔧 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL Database
- JUnit 5 & Mockito

---

## 🚀 Features

- CRUD operations for:
    - **Players**
    - **Teams**
    - **Coaches**
    - **Matches**
- Aggregated football statistics:
    - Total matches per team
    - Player-specific metrics
    - Match outcomes and win counts
- Structured API endpoints adhering to RESTful standards
- DTO mapping to prevent entity leakage
- Global exception handling

---

## 🔬 Project Structure

```
com.hyperskill
|
├── config/                         # Database configuration utilities
│   └── DatabaseConnectionTester
│
├── controller/                     # REST Controllers
│   ├── PlayerController
│   ├── PlayerMatchController
│   ├── PlayerStatisticsController
│   └── TeamController
│
├── exception/                      # Custom exceptions & global handler
│   ├── GlobalExceptionHandler
│   ├── CoachNotFoundException
│   ├── MatchNotFoundException
│   ├── PlayerMatchNotFoundException
│   ├── PlayerNotFoundException
│   ├── TeamAlreadyExistsException
│   └── TeamNotFoundException
│
├── model/
│   ├── dto/                    # Data Transfer Objects
│   │   ├── CoachDTO
│   │   ├── MatchDTO
│   │   ├── PlayerMatchRequestDTO
│   │   ├── PlayerMatchResponseDTO
│   │   ├── PlayerRequestDTO
│   │   ├── PlayerResponseDTO
│   │   ├── PlayerStatisticsResponseDTO
│   │   ├── PlayerStatsDTO
│   │   ├── TeamRequestDTO
│   │   └── TeamResponseDTO
│   │
│   └── entity/                # JPA Entities
│       ├── Coach
│       ├── Goal
│       ├── Match
│       ├── Person
│       ├── Player
│       ├── PlayerMatch
│       └── Team
│
├── mapper/                         # MapStruct mappers
│   ├── CoachMapper
│   ├── MatchMapper
│   ├── PlayerMapper
│   ├── PlayerMatchMapper
│   └── TeamMapper
│
├── repository/                     # Spring Data JPA Repositories
│   ├── CoachRepository
│   ├── GoalRepository
│   ├── MatchRepository
│   ├── PlayerMatchRepository
│   ├── PlayerRepository
│   └── TeamRepository
│
├── service/                        # Business logic services
│   ├── CoachService
│   ├── MatchService
│   ├── PlayerMatchService
│   ├── PlayerService
│   ├── PlayerStatisticsService
│   └── TeamService
│
├── statistics/                     # Domain-specific statistics utilities
│   ├── CoachStatistics
│   └── TeamStatistics
│
└── FootballStatisticsApplication.java  # Main Spring Boot Application
```

This structure enhances scalability, maintainability, and testability.

---


## 🤝 Usage

Run the project with:
```bash
gradle bootRun
```

Or via your preferred IDE (e.g., IntelliJ IDEA).

Access the H2 Console (for local DB inspection):
```
http://localhost:8080/h2-console
```

---

## 🧪 Testing

Comprehensive unit tests are written using:
- JUnit 5
- Mockito

Run tests with:
```bash
gradle test
```

---

## 💼 Future Enhancements

- Add statistics for Teams and Matches, including pagination and sorting
- Implement championship feature
- Integrate Swagger/OpenAPI for API documentation

---

## 📅 Status

> **Project is under active development and was built as part of a learning journey into advanced backend architecture and domain modeling.**

---


Feel free to contribute, suggest improvements, or raise issues!