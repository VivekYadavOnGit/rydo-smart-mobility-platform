# Rydo - API Contracts

## Overview

This document defines the REST API contracts for all Rydo microservices.

### Base URL

```text
/api/v1
```

### Authentication

Protected APIs require:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

# Auth Service

Base Path:

```text
/api/v1/auth
```

---

## Register User

### Endpoint

```http
POST /api/v1/auth/register
```

### Request

```json
{
  "email": "john@example.com",
  "phoneNumber": "9876543210",
  "password": "Password@123",
  "role": "RIDER"
}
```

### Response

```json
{
  "userId": "USR100",
  "message": "Registration successful"
}
```

---

## Login

### Endpoint

```http
POST /api/v1/auth/login
```

### Request

```json
{
  "email": "john@example.com",
  "password": "Password@123"
}
```

### Response

```json
{
  "accessToken": "jwt-token",
  "refreshToken": "refresh-token",
  "expiresIn": 3600
}
```

---

## Refresh Token

### Endpoint

```http
POST /api/v1/auth/refresh
```

### Request

```json
{
  "refreshToken": "refresh-token"
}
```

### Response

```json
{
  "accessToken": "new-access-token"
}
```

---

## Logout

### Endpoint

```http
POST /api/v1/auth/logout
```

### Response

```json
{
  "message": "Logout successful"
}
```

---

# User Service

Base Path:

```text
/api/v1/users
```

---

## Get Profile

### Endpoint

```http
GET /api/v1/users/profile
```

### Response

```json
{
  "userId": "USR100",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "rating": 4.8
}
```

---

## Update Profile

### Endpoint

```http
PUT /api/v1/users/profile
```

### Request

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "city": "Mumbai"
}
```

### Response

```json
{
  "message": "Profile updated successfully"
}
```

---

## Add Vehicle

### Endpoint

```http
POST /api/v1/users/vehicles
```

### Request

```json
{
  "vehicleType": "CAR",
  "brand": "Hyundai",
  "model": "i20",
  "registrationNumber": "MH01AB1234",
  "seatCapacity": 4
}
```

### Response

```json
{
  "vehicleId": "VEH001",
  "message": "Vehicle added successfully"
}
```

---

## Get My Vehicles

### Endpoint

```http
GET /api/v1/users/vehicles
```

---

# Ride Service

Base Path:

```text
/api/v1/rides
```

---

## Create Ride

### Endpoint

```http
POST /api/v1/rides
```

### Request

```json
{
  "source": "Andheri",
  "destination": "Powai",
  "departureTime": "2026-06-10T09:00:00",
  "availableSeats": 3,
  "farePerSeat": 150
}
```

### Response

```json
{
  "rideId": "RID101",
  "message": "Ride created successfully"
}
```

---

## Search Rides

### Endpoint

```http
GET /api/v1/rides/search
```

### Query Parameters

```text
source=Andheri
destination=Powai
date=2026-06-10
```

### Response

```json
[
  {
    "rideId": "RID101",
    "driverName": "Rahul",
    "availableSeats": 3,
    "farePerSeat": 150
  }
]
```

---

## Get Ride Details

### Endpoint

```http
GET /api/v1/rides/{rideId}
```

---

## Update Ride

### Endpoint

```http
PUT /api/v1/rides/{rideId}
```

---

## Cancel Ride

### Endpoint

```http
DELETE /api/v1/rides/{rideId}
```

---

## Start Ride

### Endpoint

```http
POST /api/v1/rides/{rideId}/start
```

### Response

```json
{
  "message": "Ride started"
}
```

---

## Complete Ride

### Endpoint

```http
POST /api/v1/rides/{rideId}/complete
```

### Response

```json
{
  "message": "Ride completed"
}
```

---

# Booking Service

Base Path:

```text
/api/v1/bookings
```

---

## Book Ride

### Endpoint

```http
POST /api/v1/bookings
```

### Request

```json
{
  "rideId": "RID101",
  "seatsBooked": 2
}
```

### Response

```json
{
  "bookingId": "BKG101",
  "bookingStatus": "CONFIRMED"
}
```

---

## Get My Bookings

### Endpoint

```http
GET /api/v1/bookings
```

### Response

```json
[
  {
    "bookingId": "BKG101",
    "rideId": "RID101",
    "status": "CONFIRMED"
  }
]
```

---

## Get Booking Details

### Endpoint

```http
GET /api/v1/bookings/{bookingId}
```

---

## Cancel Booking

### Endpoint

```http
DELETE /api/v1/bookings/{bookingId}
```

### Response

```json
{
  "message": "Booking cancelled successfully"
}
```

---

# Notification Service

Base Path:

```text
/api/v1/notifications
```

---

## Get Notifications

### Endpoint

```http
GET /api/v1/notifications
```

### Response

```json
[
  {
    "id": "NOT001",
    "title": "Booking Confirmed",
    "message": "Your ride has been booked.",
    "read": false
  }
]
```

---

## Mark Notification Read

### Endpoint

```http
PATCH /api/v1/notifications/{notificationId}/read
```

### Response

```json
{
  "message": "Notification marked as read"
}
```

---

# Analytics Service

Base Path:

```text
/api/v1/analytics
```

---

## Dashboard Metrics

### Endpoint

```http
GET /api/v1/analytics/dashboard
```

### Response

```json
{
  "totalUsers": 1000,
  "totalRides": 500,
  "totalBookings": 350,
  "activeDrivers": 120
}
```

---

## Ride Statistics

### Endpoint

```http
GET /api/v1/analytics/rides
```

### Response

```json
{
  "completedRides": 450,
  "cancelledRides": 50
}
```

---

# Common Error Response

All services return standardized errors.

```json
{
  "timestamp": "2026-06-01T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/v1/rides"
}
```

---

# HTTP Status Codes

| Code | Meaning               |
| ---- | --------------------- |
| 200  | Success               |
| 201  | Resource Created      |
| 400  | Bad Request           |
| 401  | Unauthorized          |
| 403  | Forbidden             |
| 404  | Not Found             |
| 409  | Conflict              |
| 500  | Internal Server Error |

---

# API Design Principles

* RESTful API design
* JWT-based authentication
* Consistent response structure
* Standardized error handling
* Pagination for large datasets
* API versioning (`/api/v1`)
* OpenAPI/Swagger support

This document serves as the source of truth for frontend and backend development across all Rydo microservices.
