# Rydo – Core Microservices Architecture

## 1. Authentication Service

### Overview

The Authentication Service acts as the security gateway of the Rydo platform. It is responsible for managing user identity, authentication, authorization, and session management. Every user interacting with the platform must pass through this service before accessing any protected resources.

### Responsibilities

#### User Registration

* Register new riders and drivers.
* Validate email and phone number uniqueness.
* Securely hash and store user passwords.
* Assign default roles during account creation.
* Trigger verification workflows.

#### User Login

* Authenticate users using email/phone and password.
* Validate credentials against stored records.
* Generate secure access and refresh tokens.
* Maintain active session records.

#### Authorization

* Implement Role-Based Access Control (RBAC).
* Distinguish between Riders, Drivers, Administrators, and Support Staff.
* Provide authorization metadata to downstream services.

#### JWT Management

* Generate JWT access tokens.
* Issue refresh tokens for seamless authentication.
* Validate token authenticity and expiration.
* Support token revocation during logout or security incidents.

### Database Ownership

#### users

Stores:

* User ID
* Email
* Phone Number
* Password Hash
* Account Status
* Created At
* Updated At

#### roles

Stores:

* Role ID
* Role Name
* Permissions

#### tokens

Stores:

* Access Token Metadata
* Refresh Tokens
* Expiration Information
* Revocation Status

### Service APIs

* POST /auth/register
* POST /auth/login
* POST /auth/logout
* POST /auth/refresh
* GET /auth/validate

---

## 2. User Service

### Overview

The User Service manages all user-related information beyond authentication. It serves as the central repository for user profiles, vehicle details, and reputation management within the Rydo ecosystem.

### Responsibilities

#### Profile Management

* Create and update user profiles.
* Store personal information.
* Manage profile pictures.
* Maintain user preferences.

#### Vehicle Management

Drivers can register one or multiple vehicles.

Vehicle information includes:

* Vehicle Type
* Vehicle Number
* Brand
* Model
* Seating Capacity
* Fuel Type
* Verification Status

#### Rating Management

* Store ratings received by drivers.
* Maintain rider credibility scores.
* Calculate average ratings.
* Support review moderation.

### Database Ownership

#### profiles

Stores:

* User ID
* Full Name
* Profile Picture
* Contact Information
* Bio
* Preferences

#### vehicles

Stores:

* Vehicle Details
* Vehicle Verification Status
* Vehicle Documents
* Driver Mapping

### Service APIs

* GET /users/profile
* PUT /users/profile
* POST /vehicles
* GET /vehicles
* PUT /vehicles/{id}
* GET /ratings/{userId}

---

## 3. Ride Service

### Overview

The Ride Service is the heart of the Rydo platform. It manages ride creation, ride discovery, route information, scheduling, and ride lifecycle operations.

### Responsibilities

#### Ride Creation

Drivers can:

* Publish new rides.
* Define source and destination.
* Specify intermediate stops.
* Set departure time.
* Set available seats.
* Define fare per seat.

#### Ride Management

Drivers can:

* Update ride information.
* Cancel rides.
* Close ride bookings.
* Mark rides as completed.

#### Ride Discovery

Riders can:

* Search available rides.
* Filter by destination.
* Filter by departure time.
* Filter by vehicle type.
* View available seats.

#### Ride Status Management

Ride lifecycle:

1. Created
2. Open
3. Fully Booked
4. In Progress
5. Completed
6. Cancelled

### Database Ownership

#### rides

Stores:

* Ride ID
* Driver ID
* Source
* Destination
* Route Information
* Fare
* Available Seats
* Departure Time
* Ride Status

### Service APIs

* POST /rides
* GET /rides
* GET /rides/{id}
* PUT /rides/{id}
* DELETE /rides/{id}
* GET /rides/search

---

## 4. Booking Service

### Overview

The Booking Service manages ride reservations and seat allocation. It ensures that seat inventory remains consistent and prevents overbooking scenarios.

### Responsibilities

#### Seat Booking

* Reserve seats in a ride.
* Validate seat availability.
* Prevent duplicate bookings.
* Handle concurrent booking requests.

#### Booking Management

Riders can:

* View booking history.
* Cancel bookings.
* Track booking status.

Drivers can:

* View passengers.
* Approve or reject requests (optional feature).
* Monitor seat occupancy.

#### Booking Status Workflow

1. Pending
2. Confirmed
3. Cancelled
4. Completed
5. No Show

### Database Ownership

#### bookings

Stores:

* Booking ID
* Ride ID
* Rider ID
* Seat Count
* Booking Status
* Payment Status
* Timestamp

### Service APIs

* POST /bookings
* GET /bookings
* GET /bookings/{id}
* PUT /bookings/{id}
* DELETE /bookings/{id}

---

# Service Communication Flow

### Ride Creation Flow

Driver → Auth Service → User Service (Vehicle Validation) → Ride Service → Ride Published

### Ride Search Flow

Rider → Auth Service → Ride Service → Search Results

### Booking Flow

Rider → Auth Service → Ride Service (Seat Availability) → Booking Service → Booking Confirmation

### Profile Flow

User → Auth Service → User Service → Profile Data

---

# Design Principles

### Database Per Service

Each microservice owns and manages its own database schema. No service directly accesses another service's database.

### Independent Deployment

Services can be developed, tested, deployed, and scaled independently.

### Fault Isolation

Failure in one service should not impact the entire Rydo ecosystem.

### API-First Development

All communication between services occurs through well-defined REST APIs and asynchronous event messaging.

### Scalability

Services such as Ride Service and Booking Service can be scaled horizontally to handle peak traffic during commuting hours.

---

## Suggested Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Cloud
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL (per service)

### Messaging

* Apache Kafka

### Caching

* Redis

### API Gateway

* Spring Cloud Gateway

### Service Discovery

* Eureka Server

### Containerization

* Docker
* Docker Compose
* Kubernetes (Production)

### CI/CD

* GitHub Actions
* Docker Hub
* Kubernetes Deployment Pipeline
