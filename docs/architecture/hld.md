# Rydo - High Level System Architecture

## Architecture Diagram

```text
┌─────────────────────────┐
│     React Frontend      │
└────────────┬────────────┘
             │ HTTPS
             ▼
┌─────────────────────────┐
│ Spring Cloud Gateway    │
└────────────┬────────────┘
             │
 ──────────────────────────────────────────
 │               Microservices             │
 ──────────────────────────────────────────
 │ Auth Service                            │
 │ User Service                            │
 │ Ride Service                            │
 │ Booking Service                         │
 │ Notification Service                    │
 │ Analytics Service                       │
 ──────────────────────────────────────────
             │
             ▼
┌─────────────────────────┐
│     Kafka Event Bus     │
└────────────┬────────────┘
             │
             ▼
┌──────────────────────────────────────┐
│ PostgreSQL + Redis Cache             │
└──────────────────────────────────────┘
```

---

# Service Communication Strategy

## Synchronous Communication (REST APIs)

These services require immediate responses and therefore communicate synchronously through REST APIs.

### Frontend → Gateway

* Login
* Registration
* Search rides
* Book ride
* Cancel booking
* View profile

### Gateway → Auth Service

* Authentication
* JWT validation
* User authorization

### Gateway → User Service

* Fetch user profile
* Update user details

### Gateway → Ride Service

* Search available rides
* Create ride
* Manage rides

### Gateway → Booking Service

* Create booking
* Cancel booking
* Booking status checks

### Booking Service → Ride Service

* Validate seat availability
* Update available seats

---

# Asynchronous Communication (Kafka)

Kafka is used for event-driven communication where immediate responses are not required.

### Ride Created Event

```text
Ride Service
      │
      ▼
RideCreated Event
      │
      ▼
Kafka
      │
 ┌────┼───────────┐
 ▼    ▼           ▼
Notification   Analytics
Service        Service
```

### Booking Confirmed Event

```text
Booking Service
      │
      ▼
BookingConfirmed Event
      │
      ▼
Kafka
      │
 ┌────┼───────────┐
 ▼    ▼           ▼
Notification   Analytics
Service        Service
```

### Booking Cancelled Event

```text
Booking Service
      │
      ▼
BookingCancelled Event
      │
      ▼
Kafka
      │
 ┌────┼───────────┐
 ▼    ▼           ▼
Notification   Analytics
Service        Service
```

---

# Authentication Flow

Authentication is centralized in the Auth Service.

```text
User
 │
 ▼
React Frontend
 │
 ▼
Spring Cloud Gateway
 │
 ▼
Auth Service
 │
 ▼
JWT Token Issued
```

### Responsibilities

Auth Service handles:

* User registration
* User login
* Password encryption
* JWT token generation
* JWT validation
* Role-based authorization

### Flow

1. User logs in.
2. Auth Service validates credentials.
3. JWT token is generated.
4. Frontend stores token.
5. Every request includes JWT token.
6. Gateway validates token before forwarding requests.

---

# Caching Strategy (Redis)

Redis should be used for frequently accessed and temporary data.

## User Service

Cache:

* User profile
* User preferences

Benefit:

* Faster profile retrieval
* Reduced database load

---

## Ride Service

Cache:

* Popular routes
* Frequently searched rides
* Ride availability

Benefit:

* Faster ride searches
* Reduced PostgreSQL queries

---

## Booking Service

Cache:

* Temporary booking sessions
* Booking validation data

Benefit:

* Faster booking process
* Reduced service calls

---

## Auth Service

Cache:

* Active sessions
* JWT blacklist
* OTP verification data

Benefit:

* Faster authentication
* Secure token management

---

# Database Ownership

| Service              | Database Responsibility         |
| -------------------- | ------------------------------- |
| Auth Service         | Credentials, Roles, Permissions |
| User Service         | User Profiles                   |
| Ride Service         | Ride Information                |
| Booking Service      | Booking Records                 |
| Notification Service | Notification Logs               |
| Analytics Service    | Reporting Data                  |

---

# Key Architectural Decisions

* API Gateway acts as the single entry point.
* JWT-based stateless authentication.
* Kafka enables event-driven communication.
* PostgreSQL stores persistent business data.
* Redis provides high-speed caching.
* Each microservice owns its own business domain.
* Services remain independently deployable and scalable.
