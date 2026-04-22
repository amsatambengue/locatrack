API Spec

## 1. Purpose

This document defines the minimal API surface for LocaTrack V1.

Its goal is to clarify:
- the main exposed resources,
- the main allowed operations,
- the business actions that are not simple CRUD,
- the key backend constraints that must be enforced.

It is intentionally lighter than a full OpenAPI/Swagger contract.
Detailed payload schemas, examples, and field-level validation rules will be defined later in the executable API documentation.

---

## 2. API Style

### General conventions
- REST-style HTTP API
- JSON request/response bodies
- plural resource names
- backend-generated technical IDs
- backend is the source of truth

### Base path
```http
/api/v1
```

---

### Core Backend Constraints

The backend must enforce the following rules:

* a unit cannot have more than one ACTIVE lease case at the same time,
* a lease case must reference an existing tenant,
* a lease case must reference an existing unit,
* payment records and case documents must reference an existing lease case,
* LeaseCase.monthlyRent is copied from Unit.monthlyRent at creation time,
* copied contractual data is preserved and not recomputed later,
* lease status changes must be traceable in status history.
