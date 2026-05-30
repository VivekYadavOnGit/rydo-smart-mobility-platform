# Rydo - Sequence Diagrams

## Overview

This document describes the major business flows within the Rydo platform using sequence diagrams.

These diagrams demonstrate how services interact through synchronous REST APIs and asynchronous Kafka events.

---

# 1. User Registration Flow

## Sequence

```text id="6mbvrm"
User
 │
 ▼
Frontend
 │
 ▼
API Gateway
 │
 ▼
Auth Service
 │
 ▼
PostgreSQL
 │
 ▼
UserRegisteredEvent
 │
 ▼
Kafka
 │
 ├──────────────► User Service
 │                   │
 │                   ▼
 │              Create Profile
 │
 └──────────────► Analytics Service
```

## Outcome

* User account created
* JWT credentials generated
* Default profile created
* Registration metrics updated

---

# 2. User Login Flow

## Sequence

```text id="3f0lnf"
User
 │
 ▼
Frontend
 │
 ▼
API Gateway
 │
 ▼
Auth Service
 │
 ▼
Validate Credentials
 │
 ▼
Generate JWT Token
 │
 ▼
Frontend
```

## Outcome

* User authenticated
* JWT Access Token issued
* Refresh Token generated

---

# 3. Ride Creation Flow

## Sequence

```text id="x0h0n4"
Driver
 │
 ▼
Frontend
 │
 ▼
API Gateway
 │
 ▼
Ride Service
 │
 ▼
PostgreSQL
 │
 ▼
RideCreatedEvent
 │
 ▼
Kafka
 │
 ├──────────────► Notification Service
 │
 └──────────────► Analytics Service
```

## Outcome

* Ride created successfully
* Riders can discover the ride
* Notifications triggered
* Analytics updated

---

# 4. Ride Search Flow

## Sequence

```text id="v57lgm"
Rider
 │
 ▼
Frontend
 │
 ▼
API Gateway
 │
 ▼
Ride Service
 │
 ▼
Redis Cache
 │
 ▼
PostgreSQL (Fallback)
 │
 ▼
Available Rides
 │
 ▼
Frontend
```

## Outcome

* Fast ride discovery
* Reduced database load
* Improved user experience

---

# 5. Ride Booking Flow

## Sequence

```text id="3h87gm"
Rider
 │
 ▼
Frontend
 │
 ▼
API Gateway
 │
 ▼
Booking Service
 │
 ▼
Ride Service
 │
 ▼
Validate Seat Availability
 │
 ▼
Booking Service
 │
 ▼
Create Booking
 │
 ▼
PostgreSQL
 │
 ▼
Booking Confirmed
 │
 ▼
Frontend
```

## Outcome

* Seat availability verified
* Booking created
* Rider receives confirmation

---

# 6. Booking Confirmation Event Flow

## Sequence

```text id="vdr5xg"
Booking Service
 │
 ▼
RideBookedEvent
 │
 ▼
Kafka
 │
 ├──────────────► Notification Service
 │                    │
 │                    ▼
 │             Send Confirmation
 │
 ├──────────────► Analytics Service
 │                    │
 │                    ▼
 │             Update Metrics
 │
 └──────────────► Ride Service
                      │
                      ▼
               Update Available Seats
```

## Outcome

* Rider notified
* Ride occupancy updated
* Analytics recorded

---

# 7. Ride Cancellation Flow

## Sequence

```text id="jlwmph"
Rider
 │
 ▼
Frontend
 │
 ▼
API Gateway
 │
 ▼
Booking Service
 │
 ▼
Cancel Booking
 │
 ▼
PostgreSQL
 │
 ▼
RideCancelledEvent
 │
 ▼
Kafka
```

## Event Consumers

```text id="3l3q87"
Kafka
 │
 ├──────────────► Ride Service
 │                    │
 │                    ▼
 │             Restore Seats
 │
 ├──────────────► Notification Service
 │                    │
 │                    ▼
 │             Send Cancellation Alert
 │
 └──────────────► Analytics Service
                      │
                      ▼
               Update Statistics
```

## Outcome

* Booking cancelled
* Seats released
* Notifications sent

---

# 8. Ride Start Flow

## Sequence

```text id="63e0v3"
Driver
 │
 ▼
Frontend
 │
 ▼
API Gateway
 │
 ▼
Ride Service
 │
 ▼
Update Ride Status
 │
 ▼
RideStartedEvent
 │
 ▼
Kafka
 │
 ├──────────────► Notification Service
 │
 └──────────────► Analytics Service
```

## Outcome

* Ride marked as started
* Riders notified
* Trip analytics initiated

---

# 9. Ride Completion Flow

## Sequence

```text id="h3o9yr"
Driver
 │
 ▼
Frontend
 │
 ▼
API Gateway
 │
 ▼
Ride Service
 │
 ▼
Update Ride Status
 │
 ▼
RideCompletedEvent
 │
 ▼
Kafka
 │
 ├──────────────► Notification Service
 │
 └──────────────► Analytics Service
```

## Outcome

* Ride completed
* Rating workflow triggered
* Trip metrics updated

---

# 10. Notification Flow

## Sequence

```text id="c6b7gp"
Auth Service
Ride Service
Booking Service
        │
        ▼
NotificationEvent
        │
        ▼
Kafka
        │
        ▼
Notification Service
        │
 ┌──────┼──────────┐
 ▼      ▼          ▼
Email   SMS      Push
```

## Outcome

* Centralized notification handling
* Multi-channel communication
* Decoupled messaging architecture

---

# Architecture Summary

```text id="5v8p2l"
Frontend
    │
    ▼
API Gateway
    │
 ┌──┼──────────────────────┐
 ▼  ▼      ▼      ▼        ▼
Auth User  Ride  Booking Notification
Svc  Svc   Svc   Svc      Svc
             │
             ▼
          Kafka
             │
      ┌──────┴───────┐
      ▼              ▼
Notification   Analytics
Service        Service
```

---

# Interview Talking Points

### Why Sequence Diagrams?

* Visualize business workflows.
* Clarify service responsibilities.
* Identify synchronous and asynchronous boundaries.
* Improve system maintainability.
* Help onboard new developers quickly.

### Key Architectural Highlights

* API Gateway Pattern
* JWT Authentication
* Event-Driven Architecture
* Kafka-based Communication
* Redis Caching
* Database-per-Service Pattern
* Loose Coupling Between Services
* Scalable Microservices Design

These diagrams represent the core workflows of the Rydo Smart Mobility Platform and demonstrate enterprise-level system design practices.
