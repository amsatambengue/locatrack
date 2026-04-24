# Entity Model

## Overview

LocaTrack is centered around the management of rental workflows through a set of core business entities.

The central business object is the **Lease Case**, which links a tenant to a specific property unit and tracks the lifecycle of that rental relationship.

---

## Core Entities

### 1. Property

Represents a building or real estate asset that can contain one or multiple rental units.

Main attributes:
- id
- name
- address
- description
- createdAt

---

### 2. Unit

Represents a rentable unit inside a property.

Main attributes:
- id
- reference
- floor
- unitType
- roomCount
- monthlyRent
- status
- propertyId
- createdAt

Examples:
- studio
- apartment
- shop
- office

---

### 3. Tenant

Represents a tenant profile.

Main attributes:
- id
- firstName
- lastName
- phone
- email
- active
- idNumber
- createdAt

---

### 4. LeaseCase

Represents the lifecycle of a rental relationship between one tenant and one unit.

Main attributes:
- id
- tenant
- unit
- startDate
- endDate
- monthlyRent
- depositAmount
- status
- notes
- createdAt
- updatedAt

Lease status:
- DRAFT : initial state, create but nit yet active
- ACTIVE: lease is in progress
- TERMINATED: lease has ended successfully
- CANCELLED: lease has been cancelled

Authorized lease case status transitions;
- DRAFT -> ACTIVE
- DRAFT -> CANCELLED
- ACTIVE -> TERMINATED
- ACTIVE -> CANCELLED

To avoid because reactivating a historic lease case create problems quick.
When a lease case is active again, it is considered a new lease case:
- TERMINATED -> ACTIVE
- CANCELLED -> ACTIVE

---

### 5. PaymentRecord

Represents a payment event attached to a lease case.

Main attributes:
- id
- leaseCaseId
- dueMonth
- amountDue
- amountPaid
- paymentDate
- paymentStatus
- notes
- createdAt

---

### 6. CaseDocument

Represents a document attached to a lease case.

Main attributes:
- id
- leaseCaseId
- fileName
- documentType
- storagePath
- uploadedAt

Examples:
- lease contract
- ID copy
- payment proof
- other document

---

### 7. LeaseStatusHistory

Represents the history of lease status changes.
Append-only trace of status changes

Main attributes:
- id
- leaseCaseId
- fromStatus
- toStatus
- changedAt
- reason

---

## Relationships and Cardinalities

### Property → Unit
- One property can contain many units
- One unit belongs to exactly one property

Cardinality:
- Property `1` → `0..*` Unit
- 
---

### Unit → LeaseCase
- One unit can have many lease cases over time
- One lease case is linked to exactly one unit
- One unit cannot have more than one ACTIVE lease case at the same time

Cardinality:
- Unit `1` → `0..*` LeaseCase

---

### Tenant → LeaseCase
- One tenant can have many lease cases over time
- One lease case is linked to exactly one tenant

Cardinality:
- Tenant `1` → `0..*` LeaseCase

---

### LeaseCase → PaymentRecord
- One lease case can have many payment records
- One payment record belongs to exactly one lease case

Cardinality:
- LeaseCase `1` → `0..*` PaymentRecord

---

### LeaseCase → CaseDocument
- One lease case can have many documents
- One document belongs to exactly one lease case

Cardinality:
- LeaseCase `1` → `0..*` CaseDocument

---

### LeaseCase → LeaseStatusHistory
- One lease case can have many status history entries
- One status history entry belongs to exactly one lease case

Cardinality:
- LeaseCase `1` → `0..*` LeaseStatusHistory

---

## Main Business Constraints

- A lease case must reference an existing tenant
- A lease case must reference an existing unit
- A unit cannot have more than one ACTIVE lease case at the same time
- Each status change must be recorded in the status history
- A document cannot exist without a lease case
- A payment record cannot exist without a lease case

---

## Simplified Diagram

```mermaid
classDiagram
class Property
class Unit
class Tenant
class LeaseCase
class PaymentRecord
class CaseDocument
class LeaseStatusHistory

Property "1" --> "0..*" Unit
Unit "1" --> "0..*" LeaseCase
Tenant "1" --> "0..*" LeaseCase
LeaseCase "1" --> "0..*" PaymentRecord
LeaseCase "1" --> "0..*" CaseDocument
LeaseCase "1" --> "0..*" LeaseStatusHistory