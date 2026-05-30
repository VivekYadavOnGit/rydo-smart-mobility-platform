# Rydo - Service Communication Design

## Overview

Rydo follows a hybrid communication model:

* **Synchronous Communication (REST APIs)** for request-response operations.
* **Asynchronous Communication (Kafka Events)** for background processing and event-driven workflows.

---

# Synchronous Communication

Synchronous communication is used when an immediate response is required.

## Authentication Flow

```text
React Frontend
      │
      ▼
Spring Cloud Gateway
      │
      ▼
Auth Service
```

### Purpose

* User Login
* User Registration
* JWT Validation
* Role Authorization

---

## User Profile Flow

```text
React Frontend
      │
      ▼
Spring Cloud Gateway
      │
      ▼
User Service
```

### Purpose

* View Profile
* Update Profile
* Manage Driver Information

---

## Ride Search Flow

```text
React Frontend
      │
      ▼
Spring Cloud Gateway
      │
      ▼
Ride Service
```

### Purpose

* Search Rides
* View Ride Details
* Create Ride
* Manage Ride

---

## Booking Flow

```text
React Frontend
      │
      ▼
Spring Cloud Gateway
      │
      ▼
Booking Service
      │
      ▼
Ride Service
```

### Purpose

* Verify Seat Availability
* Reserve Seats
* Confirm Booking

---

# Asynchronous Communication

Asynchronous communication is handled using Kafka.

---

## Ride Created Event

```text
Ride Service
      │
      ▼
RideCreated Event
      │
      ▼
Kafka
      │
 ┌────┴─────────────┐
 ▼                  ▼
Notification     Analytics
Service          Service
```

### Consumers

#### Notification Service

* Notify riders about new rides
* Send email notifications
* Send push notifications

#### Analytics Service

* Update ride statistics
* Generate usage reports
* Monitor platform activity

---

## Booking Confirmed Event

```text
Booking Service
      │
      ▼
BookingConfirmed Event
      │
      ▼
Kafka
      │
 ┌────┴─────────────┐
 ▼                  ▼
Notification     Analytics
Service          Service
```

### Consumers

#### Notification Service

* Booking confirmation message
* Driver notification
* Rider notification

#### Analytics Service

* Booking metrics
* Revenue tracking
* User engagement analysis

---

## Booking Cancelled Event

```text
Booking Service
      │
      ▼
BookingCancelled Event
      │
      ▼
Kafka
      │
 ┌────┴─────────────┐
 ▼                  ▼
Notification     Analytics
Service          Service
```

### Consumers

#### Notification Service

* Cancellation alerts
* Refund notifications

#### Analytics Service

* Cancellation trends
* Service performance analysis

---

## User Registered Event

```text
Auth Service
      │
      ▼
UserRegistered Event
      │
      ▼
Kafka
      │
 ┌────┴─────────────┐
 ▼                  ▼
User Service     Analytics
                Service
```

### Purpose

* Create default user profile
* Track new user registrations

---

# Communication Summary

| Communication                  | Type  | Protocol |
| ------------------------------ | ----- | -------- |
| Gateway → Auth Service         | Sync  | REST     |
| Gateway → User Service         | Sync  | REST     |
| Gateway → Ride Service         | Sync  | REST     |
| Gateway → Booking Service      | Sync  | REST     |
| Booking Service → Ride Service | Sync  | REST     |
| Ride Service → Kafka           | Async | Event    |
| Booking Service → Kafka        | Async | Event    |
| Auth Service → Kafka           | Async | Event    |
| Kafka → Notification Service   | Async | Event    |
| Kafka → Analytics Service      | Async | Event    |

---

# Design Principles

* API Gateway is the single entry point.
* Services communicate via REST when immediate responses are required.
* Kafka is used for event-driven workflows.
* Services remain loosely coupled.
* Each service can scale independently.
* Failure in Notification or Analytics Service does not impact core ride-booking operations.

This architecture provides high scalability, resilience, and maintainability for the Rydo Smart Mobility Platform.
