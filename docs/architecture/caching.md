# Rydo - Redis Caching Strategy

## Overview

Rydo uses Redis as an in-memory data store to improve application performance, reduce database load, and provide faster response times for frequently accessed data.

### Goals

* Reduce PostgreSQL queries
* Improve ride search performance
* Speed up authentication workflows
* Support scalable microservices
* Enhance user experience

---

# Redis Architecture

```text
React Frontend
       │
       ▼
Spring Cloud Gateway
       │
 ┌─────┼──────────────────────┐
 ▼     ▼         ▼            ▼
Auth  User     Ride      Booking
Svc   Svc      Svc        Svc
       │
       ▼
     Redis
       │
       ▼
   PostgreSQL
```

---

# Cache 1: Popular Rides

## Purpose

Frequently searched and highly booked rides should be served directly from Redis instead of querying PostgreSQL repeatedly.

### Examples

* Mumbai → Pune
* Andheri → Powai
* Bangalore → Electronic City

---

## Cache Key

```text
popular-rides:{source}:{destination}
```

### Example

```text
popular-rides:mumbai:pune
```

---

## Cached Data

```json
[
  {
    "rideId": "RID101",
    "driverName": "Rahul",
    "availableSeats": 3,
    "farePerSeat": 250
  }
]
```

---

## TTL

```text
10 minutes
```

---

## Benefits

* Faster ride discovery
* Reduced database load
* Improved response time

---

# Cache 2: User Sessions

## Purpose

Store active user session information for quick authentication and authorization checks.

---

## Cache Key

```text
session:{userId}
```

### Example

```text
session:USR100
```

---

## Cached Data

```json
{
  "userId": "USR100",
  "email": "user@example.com",
  "role": "RIDER",
  "loggedInAt": "2026-06-01T10:00:00Z"
}
```

---

## TTL

```text
24 hours
```

---

## Benefits

* Faster login validation
* Reduced database lookups
* Improved JWT/session management

---

# Cache 3: Ride Search Results

## Purpose

Cache ride search responses for frequently repeated searches.

---

## Cache Key

```text
ride-search:{source}:{destination}:{date}
```

### Example

```text
ride-search:mumbai:pune:2026-06-01
```

---

## Cached Data

```json
[
  {
    "rideId": "RID200",
    "departureTime": "09:00",
    "availableSeats": 2
  }
]
```

---

## TTL

```text
5 minutes
```

---

## Benefits

* Faster search results
* Reduced Ride Service workload
* Better scalability during peak traffic

---

# Cache Flow

## Cache Hit

```text
User Search
      │
      ▼
Ride Service
      │
      ▼
Redis
      │
      ▼
Cache Found
      │
      ▼
Return Response
```

### Result

* No database query required
* Response returned in milliseconds

---

## Cache Miss

```text
User Search
      │
      ▼
Ride Service
      │
      ▼
Redis
      │
      ▼
Cache Miss
      │
      ▼
PostgreSQL
      │
      ▼
Store in Redis
      │
      ▼
Return Response
```

### Result

* Data fetched from database
* Cached for future requests

---

# Cache Invalidation Strategy

## Popular Rides

Invalidate when:

* New ride created
* Ride cancelled
* Ride completed
* Seat availability changes significantly

---

## User Sessions

Invalidate when:

* User logs out
* Token expires
* Account blocked

---

## Ride Search Results

Invalidate when:

* Ride booked
* Ride cancelled
* Ride completed
* Search cache TTL expires

---

# Redis Data Structures

| Use Case            | Data Structure |
| ------------------- | -------------- |
| User Sessions       | Hash           |
| Popular Rides       | List           |
| Ride Search Results | JSON/String    |
| Rate Limiting       | Counter        |
| JWT Blacklist       | Set            |

---

# Additional Future Use Cases

### OTP Storage

```text
otp:{phoneNumber}
```

TTL:

```text
5 minutes
```

---

### JWT Blacklist

```text
blacklist:{tokenId}
```

Used for:

* Logout handling
* Token revocation

---

### API Rate Limiting

```text
rate-limit:{userId}
```

Used for:

* Preventing abuse
* Protecting APIs

---

# Monitoring Metrics

Track:

* Cache Hit Rate
* Cache Miss Rate
* Memory Usage
* Eviction Count
* Average Response Time

Target:

```text
Cache Hit Rate > 80%
```

---

# Best Practices

* Cache only frequently accessed data.
* Keep TTLs short enough to avoid stale data.
* Never store sensitive passwords in Redis.
* Use cache-aside pattern.
* Monitor memory usage regularly.
* Invalidate caches when underlying data changes.

---

# Summary

| Cache               | TTL    | Service      |
| ------------------- | ------ | ------------ |
| Popular Rides       | 10 min | Ride Service |
| User Sessions       | 24 hrs | Auth Service |
| Ride Search Results | 5 min  | Ride Service |

Redis helps Rydo deliver faster ride searches, efficient authentication, and scalable performance while reducing the load on PostgreSQL databases.
