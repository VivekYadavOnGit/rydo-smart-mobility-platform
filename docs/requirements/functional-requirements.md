# Rydo Functional Requirements Specification (FRS)

## Overview

Functional Requirements define the core capabilities that the Rydo platform must provide to users. These requirements serve as the foundation for system design, development, testing, and deployment.

---

# Authentication Module

## FR-001: User Registration

### Description

The system shall allow new users to create an account using their email address and phone number.

### Actors

* Rider
* Driver

### Preconditions

* User is not already registered.
* Email and phone number are unique.

### Main Flow

1. User enters registration details.
2. System validates input data.
3. Password is securely hashed.
4. User account is created.
5. Default role is assigned.
6. Success response is returned.

### Postconditions

* User account exists in the system.
* User can proceed to login.

### Priority

High

---

## FR-002: User Login

### Description

The system shall authenticate registered users and issue secure JWT tokens.

### Actors

* Rider
* Driver
* Admin

### Preconditions

* User account exists.
* Account status is active.

### Main Flow

1. User enters credentials.
2. System validates credentials.
3. JWT access token is generated.
4. Refresh token is generated.
5. Authentication response is returned.

### Postconditions

* User gains access to protected resources.

### Priority

High

---

## FR-003: User Logout

### Description

The system shall allow authenticated users to terminate active sessions.

### Actors

* Rider
* Driver
* Admin

### Preconditions

* User is logged in.

### Main Flow

1. User initiates logout.
2. Access token is invalidated.
3. Refresh token is revoked.
4. Session ends.

### Postconditions

* User must authenticate again for protected operations.

### Priority

High

---

# User Module

## FR-004: Update User Profile

### Description

The system shall allow users to update their personal profile information.

### Actors

* Rider
* Driver

### Preconditions

* User is authenticated.

### Main Flow

1. User opens profile settings.
2. User updates details.
3. System validates inputs.
4. Profile is updated.

### Editable Fields

* Name
* Phone Number
* Profile Picture
* Preferences

### Postconditions

* Updated profile information is stored.

### Priority

Medium

---

## FR-005: Register Vehicle

### Description

The system shall allow drivers to register vehicle information.

### Actors

* Driver

### Preconditions

* User role must be DRIVER.
* User is authenticated.

### Main Flow

1. Driver enters vehicle details.
2. System validates information.
3. Vehicle record is created.
4. Vehicle is marked for verification.

### Postconditions

* Vehicle becomes available for future ride creation.

### Priority

High

---

# Ride Module

## FR-006: Create Ride

### Description

The system shall allow drivers to publish rides for passengers.

### Actors

* Driver

### Preconditions

* Driver is authenticated.
* Driver has a registered vehicle.
* Vehicle is verified.

### Main Flow

1. Driver selects vehicle.
2. Driver enters route information.
3. Driver enters departure time.
4. Driver specifies available seats.
5. Ride is published.

### Ride Information

* Source
* Destination
* Departure Time
* Available Seats
* Fare Per Seat

### Postconditions

* Ride becomes visible in search results.

### Priority

Critical

---

## FR-007: Update Ride

### Description

The system shall allow drivers to modify ride details before departure.

### Actors

* Driver

### Preconditions

* Driver owns the ride.
* Ride has not started.

### Main Flow

1. Driver selects ride.
2. Driver updates ride details.
3. System validates modifications.
4. Ride record is updated.

### Editable Fields

* Departure Time
* Available Seats
* Fare
* Route Information

### Postconditions

* Updated ride information becomes available to passengers.

### Priority

Medium

---

## FR-008: Cancel Ride

### Description

The system shall allow drivers to cancel published rides.

### Actors

* Driver

### Preconditions

* Driver owns the ride.
* Ride status is not COMPLETED.

### Main Flow

1. Driver selects ride.
2. Driver confirms cancellation.
3. Ride status changes to CANCELLED.
4. Passengers are notified.

### Postconditions

* Ride is removed from search results.
* Existing bookings are cancelled.

### Priority

High

---

# Booking Module

## FR-009: Search Rides

### Description

The system shall allow passengers to search for available rides.

### Actors

* Passenger

### Preconditions

* User is authenticated.

### Main Flow

1. Passenger enters source and destination.
2. Passenger optionally selects filters.
3. System retrieves matching rides.
4. Results are displayed.

### Search Filters

* Source
* Destination
* Departure Time
* Vehicle Type
* Available Seats
* Price Range

### Postconditions

* Matching rides are displayed.

### Priority

Critical

---

## FR-010: Book Seats

### Description

The system shall allow passengers to reserve seats in an available ride.

### Actors

* Passenger

### Preconditions

* User is authenticated.
* Ride is active.
* Seats are available.

### Main Flow

1. Passenger selects ride.
2. Passenger specifies seat count.
3. System validates seat availability.
4. Booking record is created.
5. Ride seat inventory is updated.

### Postconditions

* Booking status becomes CONFIRMED.
* Available seats decrease.

### Priority

Critical

---

## FR-011: Cancel Booking

### Description

The system shall allow passengers to cancel existing bookings.

### Actors

* Passenger

### Preconditions

* Booking exists.
* Ride has not started.

### Main Flow

1. Passenger selects booking.
2. Passenger confirms cancellation.
3. Booking status changes to CANCELLED.
4. Seats are released back to the ride.

### Postconditions

* Ride inventory is updated.
* Passenger receives cancellation confirmation.

### Priority

High

---

# Future Functional Requirements (Phase 2)

## FR-012

Passenger can make online payments.

## FR-013

Driver can accept or reject booking requests.

## FR-014

Users can rate and review each other.

## FR-015

Users receive ride notifications.

## FR-016

Users can track ride location in real time.

## FR-017

System recommends matching rides using AI.

## FR-018

Admin can monitor platform analytics.

## FR-019

Admin can manage users and reported content.

## FR-020

System calculates fuel and carbon savings automatically.

---

# Requirement Priority Summary

### Critical

* FR-006 Create Ride
* FR-009 Search Rides
* FR-010 Book Seats

### High

* FR-001 Register
* FR-002 Login
* FR-003 Logout
* FR-005 Register Vehicle
* FR-008 Cancel Ride
* FR-011 Cancel Booking

### Medium

* FR-004 Update Profile
* FR-007 Update Ride

### Low (Future)

* Payments
* Reviews
* Notifications
* Real-Time Tracking
* AI Recommendations
* Analytics
