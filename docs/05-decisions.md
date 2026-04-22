# Architecture and Design Decisions

This document captures the main design choices made for LocaTrack and the reasoning behind them.

---

## 1. Modular Monolith Instead of Microservices

### Decision
The backend is implemented as a modular monolith.

### Why
This project is designed as a focused fullstack portfolio application.  
Using microservices at this stage would add unnecessary complexity:
- multiple deployments
- service-to-service communication
- more difficult debugging
- higher operational overhead

A modular monolith provides a better balance between:
- simplicity
- maintainability
- architectural clarity
- learning value

---

## 2. Property / Unit Separation

### Decision
The domain model separates **Property** and **Unit**.

### Why
A property may contain multiple rentable units.  
The rental relationship does not apply to the whole property in all cases, but to a specific unit.

This makes the model more realistic and avoids flattening the business domain.

---

## 3. LeaseCase as the Central Business Object

### Decision
The system is centered around the **LeaseCase** entity.

### Why
The lease case represents the lifecycle of a rental relationship between a tenant and a unit.

It acts as the central object for:
- status changes
- payment tracking
- document attachment
- business traceability

This makes the workflow explicit and avoids treating the system as a simple CRUD application.

---

## 4. Monthly Rent Is Copied into LeaseCase

### Decision
The monthly rent is stored in the lease case, even though the unit also has a monthly rent value.

### Why
The rent defined on the unit represents the current rent for that unit.  
The rent stored on the lease case represents the contractual rent agreed at the time the lease was created.

This avoids historical inconsistencies when the rent of a unit changes over time.

---

## 5. No Authentication and Roles in V1

### Decision
Authentication and role-based access control are excluded from version 1.

### Why
The first goal of the project is to establish a strong fullstack and domain architecture foundation.

Adding authentication now would significantly increase complexity:
- login flow
- JWT handling
- route protection
- role permissions
- secured API design

For version 1, the project assumes a simplified single-user context.

---

## 6. No Physical Delete for Core Business Entities

### Decision
The first version does not rely on physical deletion for core entities such as properties, units, tenants, and lease cases.

### Why
In business systems, deleting core records can break consistency and traceability.

The project favors stability and historical coherence over full CRUD symmetry.

---

## 7. Business Rules Are Enforced in the Backend

### Decision
The backend is the source of truth for business rules.

### Why
The frontend may guide the user, but the backend must enforce consistency.

Examples:
- a unit cannot have more than one ACTIVE lease case at the same time
- a lease case must reference an existing tenant and an existing unit
- each status change must be recorded in history

This keeps the system reliable and prevents invalid states.

---

## 8. DTO-Based API Design

### Decision
The API is designed around DTOs rather than exposing persistence entities directly.

### Why
DTOs help:
- separate API contracts from database models
- control what is exposed
- reduce coupling
- keep the API stable even if persistence changes

This improves maintainability and API clarity.

---

## 9. Minimal V1 Scope

### Decision
The first version focuses only on the core rental workflow.

### Included
- property management
- unit management
- tenant management
- lease case management
- payment tracking
- document attachment
- status history

### Excluded
- authentication
- notifications
- online payments
- reporting dashboards
- advanced filtering
- ownership hierarchy
- maintenance workflows

### Why
A smaller but coherent scope is more valuable than a broader but unfinished system.

---

## 10. Documentation Is Part of the Project

### Decision
The project includes structured documentation in the repository.

### Why
The goal is not only to produce code, but to demonstrate:
- architectural thinking
- clarity of reasoning
- project structure
- maintainability

The documentation supports both implementation and portfolio value.