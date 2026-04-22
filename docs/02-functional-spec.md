# Functional Specification

## Overview

LocaTrack is a lease and tenant management application focused on managing rental workflows in a structured and trackable way.

The system is centered around the concept of a **Lease Case**, which represents the lifecycle of a rental relationship between a tenant and a property.

---

## Core Features

### 1. Property Management

Users can:
- create a property
- update property information
- view list of properties
- View property details

---

### 2. Unit Management
- create a unit within a property
- update unit information
- view unit details
- view units by property

---
### 3. Tenant Management

Users can:
- create a tenant profile
- update tenant information
- view list of tenants
- View tenant details

---

### 4. Lease Case Management

Users can:
- create a lease case (linking a tenant to a property)
- view lease case details
- track lease status (e.g. CREATED, ACTIVE, CLOSED)

---

### 5. Status Workflow Tracking

Users can:
- update lease status
- view status history for each lease case

Each status change is recorded for traceability.

---

### 6. Payment Tracking

Users can:
- register a payment for a lease case
- view payment history
- identify unpaid or delayed payments

---

### 7. Document Management

Users can:
- attach documents to a lease case
- view documents related to a lease

---

## Out of Scope (V1)

The following features are intentionally excluded:
- authentication and user roles (simplified or mocked)
- notifications (SMS, email)
- online payments integration
- multi-property ownership hierarchy
- reporting dashboards
- advanced search and filtering

---

## Functional Principles

- Each lease case links one tenant to one property unit
- A unit cannot have more than one ACTIVE lease case at the same time
- A lease case must always be associated with an existing tenant and an existing unit
- A property can contain multiple units
- A unit belongs to exactly one property
- Each lease case has a lifecycle (CREATED, ACTIVE, CLOSED)
- Each status change must be recorded in a status history for traceability

---

## User Flow (Simplified)

1. Create a property
2. Create units within the property
3. Create a tenant
4. Create a lease case for a unit
5. Update lease status
6. Register payments
7. Attach documents
8. Close lease case