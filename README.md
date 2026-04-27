# LocaTrack

**LocaTrack** is a fullstack rental management system designed to model a realistic lease lifecycle with a clean and modular backend architecture.

## Code Domain
The system models a rental workflow:

- Properties contain Units
- Tenants rent Units
- LeaseCases represent contractual occupation
- Lease status is lifecycle-driven and fully traceable

## Key Design Decisions
1. Modular Monolith (not microservices)
The backend is structured by domain (property, unit, tenant, lease), avoiding premature microservices complexity.

2. LeaseCase is not CRUD-driven

LeaseCase is treated as a domain entity with lifecycle rules:

- created as DRAFT
- activated via explicit domain action
- cannot be modified arbitrarily

Status transitions are controlled:
- DRAFT → ACTIVE → TERMINATED
- DRAFT → CANCELLED

3. Rent Snapshot Strategy

When a LeaseCase is created:

LeaseCase.monthlyRent = Unit.monthlyRent (at creation time)

This ensures:

- historical consistency
- no dependency on future Unit changes

4. Unit Availability Constraint
   
A Unit cannot have more than one ACTIVE LeaseCase at the same time.

This is enforced:

* at service level (business validation)
* at database level (safety)

5. Append-only Status History

Lease status changes are tracked in a dedicated table:

* no updates
* no deletes
* full traceability

6. No Direct Entity Exposure

The API uses DTOs and mappers:
* entities are not exposed directly
* contracts are controlled and stable


## Tech Stack

### Backend
- Java 17
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- PostgreSQL

### Frontend
- Angular

### Tooling
- Maven 3.9.11
- Docker Compose
- Swagger / OpenAPI

## How to Run
1. Start database

`docker compose up -d
`

2. Run backend

  ` cd backend
   mvn spring-boot:run`

Backend runs on: http://localhost:8080


## API Overview

Main endpoints:

`/api/v1/properties`

`/api/v1/units`

`/api/v1/tenants`

`/api/v1/lease-cases`

Lease-specific endpoints:

`POST /lease-cases/{id}/status-changes`

`GET /lease-cases/{id}/status-history`

## Project Scope (V1)
* Property management
* Unit management
* Tenant management
* Lease lifecycle management

Out of scope (for now):
* authentication
* payments
* documents
* notifications

## Project Documentation

Detailed project notes are available in the [`docs`](./docs) folder:

- [Context](./docs/01-context.md)
- [Functional Specification](./docs/02-functional-spec.md)
- [Architecture](./docs/03-architecture.md)
- [Entity Model](./docs/04-entity-model.md)
- [API Specification](./docs/05-api-spec.md)
- [Architecture Decisions](./docs/06-decisions.md)

## Project Status

This repository is currently in the design and setup phase.  
The domain model, documentation, and architecture are being defined before implementation.

## Author

Personal fullstack architecture project built as a professional portfolio piece.