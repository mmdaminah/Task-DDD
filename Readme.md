# DDD TODO App - Project Documentation

## Overview

This project is a simple **TODO/Task CRUD application** implemented using **Domain-Driven Design (DDD)** principles in a **Java + Spring Boot** environment. The application uses **JPA** for persistence and separates responsibilities across **Domain**, **Usecase**, and **Infrastructure** layers.

The project demonstrates:

* DDD structure with **domain terms** in packages
* Use of **Usecase layer** (instead of generic application layer) with command and query handlers
* Infrastructure layer for persistence and REST API
* Separation of concerns and framework-agnostic domain

---

## Project Structure

```
src/main/java/com/example/todo
  /domain
    /task
      Task.java                # Aggregate root entity
      TaskId.java              # Value object for unique identifier
      TaskStatus.java          # Enum for task status (PENDING, COMPLETED)
      TaskRepository.java      # Domain port (interface) for persistence
      TaskService.java         # Optional domain service for business rules
      TaskCreatedEvent.java    # Optional domain event

  /usecase
    /task
      /command
        CreateTaskCommand.java
        UpdateTaskCommand.java
        DeleteTaskCommand.java
        CreateTaskHandler.java
        UpdateTaskHandler.java
        DeleteTaskHandler.java
      /query
        GetTaskByIdQuery.java
        ListTasksQuery.java
        GetTaskByIdHandler.java
        ListTasksHandler.java
      /dto
        TaskDto.java            # Output model for usecases

  /infrastructure
    /persistence
      TaskJpaEntity.java
      SpringDataTaskRepository.java   # Spring Data JPA repository
      JpaTaskRepositoryAdapter.java   # Implements TaskRepository interface
    /rest
      TaskController.java             # REST API controller
      TaskRequest.java                # Request DTO
    /config
      BeanConfig.java                 # Bean wiring (if needed)

  /common
    BusinessException.java
    ErrorCodes.java
    Result.java
```

### Dependency Rules

* **Domain**: depends on nothing; pure business model.
* **Usecase**: depends on domain (and domain ports); orchestrates commands and queries.
* **Infrastructure**: depends on domain and usecase; implements domain ports (repositories) and exposes REST endpoints.
* **Transactions**: defined at **usecase handler** level using `@Transactional`.

---

## Layer Responsibilities

### Domain

* **Entities**: `Task` (aggregate root) encapsulates business data and behavior.
* **Value Objects**: `TaskId` for unique identifiers.
* **Enums/VOs**: `TaskStatus` for task state.
* **Ports**: `TaskRepository` defines contract for persistence.
* **Domain Services/Events**: optional, for business rules and event-driven design.

### Usecase

* **Commands**: input models for write operations (`CreateTaskCommand`, etc.)
* **Queries**: input models for read operations (`GetTaskByIdQuery`, `ListTasksQuery`)
* **Handlers**: execute business logic, call domain methods, interact with repositories
* **DTOs**: `TaskDto` for returning data to adapters

### Infrastructure

* **Persistence**: `JpaTaskRepositoryAdapter` implements domain port using Spring Data JPA.
* **REST API**: `TaskController` exposes CRUD endpoints.
* **Request/Response DTOs**: map HTTP requests to usecase commands and responses.

### Common

* **Error Handling**: `BusinessException`, `ErrorCodes`
* **Result Wrappers**: `Result` for consistent responses

---

## CRUD Operations Flow

1. **Create Task**

    * REST Controller receives POST `/tasks` with `TaskRequest`
    * Maps to `CreateTaskCommand`
    * `CreateTaskHandler` creates `Task` and calls `TaskRepository.save()`
    * JPA persists `TaskJpaEntity`
    * Returns `TaskDto` to client

2. **Update Task**

    * REST Controller receives PUT `/tasks/{id}`
    * Maps to `UpdateTaskCommand`
    * `UpdateTaskHandler` fetches `Task` from repository, updates fields, saves back

3. **Delete Task**

    * REST Controller receives DELETE `/tasks/{id}`
    * Maps to `DeleteTaskCommand`
    * `DeleteTaskHandler` calls repository to delete entity

4. **Get Task by ID**

    * REST Controller receives GET `/tasks/{id}`
    * Maps to `GetTaskByIdQuery`
    * `GetTaskByIdHandler` fetches task from repository

5. **List All Tasks**

    * REST Controller receives GET `/tasks`
    * `ListTasksHandler` fetches all tasks

---

## Notes & Best Practices

* **Repositories in Domain**: Only interfaces; keep domain framework-agnostic.
* **Transactions at Usecase Handlers**: ensures atomicity without contaminating domain layer.
* **Domain Terms in Usecase Packages**: keeps code readable and aligned with business language.
* **Infrastructure Layer**: contains all adapters (persistence, REST, external services).
* **Validation**: performed in REST controllers; domain assumes valid invariants.
* **CQRS Style**: commands and queries are separated in usecase layer.

---

## Recommended Next Steps

* Implement unit tests for domain logic and handlers
* Add integration tests for REST API with in-memory database (H2)
* Expand domain with additional events or services if needed
* Consider modularization if project grows beyond a single module
