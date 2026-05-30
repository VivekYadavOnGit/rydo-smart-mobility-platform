# Rydo - Kafka Topic Design

## Overview

Rydo uses Apache Kafka as the central event streaming platform to enable asynchronous communication between microservices.

Benefits:

* Loose coupling between services
* Improved scalability
* Fault tolerance
* Event-driven architecture
* Better system observability

---

# Kafka Architecture

```text id="xj6v66"
                  Kafka Cluster
                         │
 ┌──────────────┬────────┼──────────────┬──────────────┐
 ▼              ▼        ▼              ▼              ▼
Ride         Booking  Notification   Analytics     Other
Service      Service    Service       Service      Consumers
```

---

# Topic: ride-created

## Producer

```text id="zcfk4e"
Ride Service
```

## Consumers

```text id="tw3jz7"
Notification Service
Analytics Service
```

## Trigger

Published when a driver creates a new ride.

## Event Payload

```json id="hxfm8q"
{
  "rideId": "UUID",
  "driverId": "UUID",
  "source": "Mumbai",
  "destination": "Pune",
  "departureTime": "2026-06-01T09:00:00Z",
  "availableSeats": 3
}
```

## Use Cases

* Notify interested riders
* Update ride analytics
* Track ride creation metrics

---

# Topic: ride-booked

## Producer

```text id="s0y0y3"
Booking Service
```

## Consumers

```text id="d5b9mq"
Notification Service
Analytics Service
Ride Service
```

## Trigger

Published when a booking is confirmed.

## Event Payload

```json id="gf8p8l"
{
  "bookingId": "UUID",
  "rideId": "UUID",
  "riderId": "UUID",
  "seatsBooked": 2,
  "bookingAmount": 500,
  "bookingTime": "2026-06-01T08:30:00Z"
}
```

## Use Cases

* Send booking confirmation
* Update ride occupancy
* Track booking statistics

---

# Topic: ride-cancelled

## Producer

```text id="7h4m1d"
Booking Service
```

## Consumers

```text id="9r4v9s"
Notification Service
Analytics Service
Ride Service
```

## Trigger

Published when a booking is cancelled.

## Event Payload

```json id="c7v0uq"
{
  "bookingId": "UUID",
  "rideId": "UUID",
  "riderId": "UUID",
  "reason": "User Cancelled",
  "cancelledAt": "2026-06-01T08:45:00Z"
}
```

## Use Cases

* Release reserved seats
* Notify driver
* Track cancellation trends

---

# Topic: ride-started

## Producer

```text id="xx2rfd"
Ride Service
```

## Consumers

```text id="yw3sfe"
Notification Service
Analytics Service
```

## Trigger

Published when the driver starts the ride.

## Event Payload

```json id="e1k1w7"
{
  "rideId": "UUID",
  "driverId": "UUID",
  "startedAt": "2026-06-01T09:00:00Z"
}
```

## Use Cases

* Notify riders
* Start trip tracking
* Generate operational metrics

---

# Topic: ride-completed

## Producer

```text id="9ocfgi"
Ride Service
```

## Consumers

```text id="20fw1l"
Notification Service
Analytics Service
```

## Trigger

Published when the ride reaches its destination.

## Event Payload

```json id="a2u5it"
{
  "rideId": "UUID",
  "driverId": "UUID",
  "completedAt": "2026-06-01T12:00:00Z",
  "totalPassengers": 4
}
```

## Use Cases

* Trigger rating workflow
* Generate trip reports
* Update ride completion metrics

---

# Topic: notification-events

## Producer

```text id="6w2g5g"
Auth Service
Ride Service
Booking Service
```

## Consumers

```text id="tq5yyr"
Notification Service
```

## Trigger

Published whenever a user-facing notification is required.

## Event Payload

```json id="wuw8ns"
{
  "userId": "UUID",
  "notificationType": "BOOKING_CONFIRMED",
  "title": "Booking Confirmed",
  "message": "Your booking has been confirmed."
}
```

## Use Cases

* Email notifications
* Push notifications
* SMS notifications
* In-app notifications

---

# Topic Ownership Summary

| Topic               | Producer          | Consumers                     |
| ------------------- | ----------------- | ----------------------------- |
| ride-created        | Ride Service      | Notification, Analytics       |
| ride-booked         | Booking Service   | Notification, Analytics, Ride |
| ride-cancelled      | Booking Service   | Notification, Analytics, Ride |
| ride-started        | Ride Service      | Notification, Analytics       |
| ride-completed      | Ride Service      | Notification, Analytics       |
| notification-events | Multiple Services | Notification Service          |

---

# Consumer Groups

```text id="0crnnh"
notification-group
    └── Notification Service

analytics-group
    └── Analytics Service

ride-group
    └── Ride Service
```

Using separate consumer groups ensures each service receives and processes events independently.

---

# Design Principles

* One event represents one business action.
* Events are immutable after publishing.
* Services communicate through events rather than direct database access.
* Kafka acts as the event backbone of the platform.
* Event payloads should remain lightweight and versioned.
* Consumers should be idempotent to avoid duplicate processing.

This topic design provides a scalable event-driven foundation for the Rydo Smart Mobility Platform.
