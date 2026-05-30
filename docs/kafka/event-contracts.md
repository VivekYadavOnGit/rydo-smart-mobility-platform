# Rydo - Kafka Event Contracts

## Overview

This document defines the event schemas exchanged between microservices through Kafka.

### Design Guidelines

* All events use JSON format.
* Events are immutable.
* Every event contains:

  * Event ID
  * Event Type
  * Timestamp
  * Source Service
* UUIDs are used for entity identifiers.
* Consumers must handle duplicate events safely (idempotency).

---

# Common Event Metadata

Every event should include the following metadata:

```json
{
  "eventId": "EVT-001",
  "eventType": "RideCreatedEvent",
  "timestamp": "2026-06-01T10:00:00Z",
  "sourceService": "ride-service"
}
```

---

# RideCreatedEvent

## Topic

```text
ride-created
```

## Payload

```json
{
  "eventId": "EVT-1001",
  "eventType": "RideCreatedEvent",
  "timestamp": "2026-06-01T10:00:00Z",
  "sourceService": "ride-service",

  "rideId": "RID123",
  "driverId": "USR100",
  "vehicleId": "VEH001",

  "source": "Andheri",
  "destination": "Powai",

  "departureTime": "2026-06-01T11:00:00Z",
  "availableSeats": 3,
  "farePerSeat": 150
}
```

---

# RideBookedEvent

## Topic

```text
ride-booked
```

## Payload

```json
{
  "eventId": "EVT-1002",
  "eventType": "RideBookedEvent",
  "timestamp": "2026-06-01T10:10:00Z",
  "sourceService": "booking-service",

  "bookingId": "BKG101",
  "rideId": "RID123",
  "riderId": "USR200",

  "seatsBooked": 2,
  "bookingAmount": 300,

  "bookingStatus": "CONFIRMED"
}
```

---

# RideCancelledEvent

## Topic

```text
ride-cancelled
```

## Payload

```json
{
  "eventId": "EVT-1003",
  "eventType": "RideCancelledEvent",
  "timestamp": "2026-06-01T10:20:00Z",
  "sourceService": "booking-service",

  "bookingId": "BKG101",
  "rideId": "RID123",
  "riderId": "USR200",

  "reason": "USER_CANCELLED",
  "bookingStatus": "CANCELLED"
}
```

---

# RideStartedEvent

## Topic

```text
ride-started
```

## Payload

```json
{
  "eventId": "EVT-1004",
  "eventType": "RideStartedEvent",
  "timestamp": "2026-06-01T11:00:00Z",
  "sourceService": "ride-service",

  "rideId": "RID123",
  "driverId": "USR100",

  "actualStartTime": "2026-06-01T11:00:00Z"
}
```

---

# RideCompletedEvent

## Topic

```text
ride-completed
```

## Payload

```json
{
  "eventId": "EVT-1005",
  "eventType": "RideCompletedEvent",
  "timestamp": "2026-06-01T13:15:00Z",
  "sourceService": "ride-service",

  "rideId": "RID123",
  "driverId": "USR100",

  "actualEndTime": "2026-06-01T13:15:00Z",
  "totalPassengers": 3,

  "rideStatus": "COMPLETED"
}
```

---

# NotificationEvent

## Topic

```text
notification-events
```

## Payload

```json
{
  "eventId": "EVT-1006",
  "eventType": "NotificationEvent",
  "timestamp": "2026-06-01T10:11:00Z",
  "sourceService": "booking-service",

  "userId": "USR200",

  "notificationType": "BOOKING_CONFIRMED",

  "title": "Booking Confirmed",
  "message": "Your seat has been successfully booked.",

  "channel": "EMAIL"
}
```

---

# Event Flow Examples

## Ride Creation Flow

```text
Ride Service
      │
      ▼
RideCreatedEvent
      │
      ▼
Kafka
      │
 ┌────┴──────────┐
 ▼               ▼
Notification   Analytics
Service        Service
```

---

## Booking Flow

```text
Booking Service
      │
      ▼
RideBookedEvent
      │
      ▼
Kafka
      │
 ┌────┴──────────┐
 ▼               ▼
Notification   Analytics
Service        Service
```

---

## Ride Completion Flow

```text
Ride Service
      │
      ▼
RideCompletedEvent
      │
      ▼
Kafka
      │
 ┌────┴──────────┐
 ▼               ▼
Notification   Analytics
Service        Service
```

---

# Event Versioning Strategy

Future-proofing event contracts:

```json
{
  "eventVersion": "v1"
}
```

Guidelines:

* New optional fields can be added without breaking consumers.
* Existing fields should never be removed.
* Breaking changes require a new event version.
* Consumers should ignore unknown fields.

---

# Event Naming Convention

| Event              | Topic               |
| ------------------ | ------------------- |
| RideCreatedEvent   | ride-created        |
| RideBookedEvent    | ride-booked         |
| RideCancelledEvent | ride-cancelled      |
| RideStartedEvent   | ride-started        |
| RideCompletedEvent | ride-completed      |
| NotificationEvent  | notification-events |

---

# Best Practices

* Keep events lightweight.
* Include only required business data.
* Never expose sensitive information.
* Maintain backward compatibility.
* Use UUIDs for entity identifiers.
* Ensure consumers are idempotent.
* Log all published and consumed events for observability.

This document serves as the contract between all Kafka producers and consumers in the Rydo platform.
