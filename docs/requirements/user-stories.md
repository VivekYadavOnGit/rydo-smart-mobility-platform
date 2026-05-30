# Rydo User Stories

## Overview

User Stories capture system requirements from the perspective of end users. They help ensure that development remains focused on delivering value to Riders, Drivers, and Administrators while supporting agile planning and sprint execution.

---

# Epic 1: Ride Discovery & Booking

## US-001: Search for Available Rides

### User Story

**As a Rider, I want to search for rides so that I can find transportation to my destination.**

### Acceptance Criteria

* Rider can enter source and destination locations.
* Rider can view matching rides.
* Rider can filter rides by:

  * Departure Time
  * Vehicle Type
  * Available Seats
  * Price Range
* Search results are returned within 2 seconds.
* Only active rides are displayed.

### Priority

Critical

### Related Functional Requirements

* FR-009 Search Rides

---

## US-002: Book a Ride

### User Story

**As a Rider, I want to book seats in a ride so that I can travel with other passengers.**

### Acceptance Criteria

* Rider can select a ride.
* Rider can choose the number of seats.
* System validates seat availability.
* Booking confirmation is generated.
* Available seat count is updated automatically.

### Priority

Critical

### Related Functional Requirements

* FR-010 Book Seats

---

## US-003: Cancel a Booking

### User Story

**As a Rider, I want to cancel my booking so that I can modify my travel plans when necessary.**

### Acceptance Criteria

* Rider can view active bookings.
* Rider can cancel a booking before ride departure.
* Released seats become available again.
* Booking status changes to CANCELLED.

### Priority

High

### Related Functional Requirements

* FR-011 Cancel Booking

---

# Epic 2: Ride Management

## US-004: Create a Ride

### User Story

**As a Driver, I want to create a ride so that passengers can join my journey.**

### Acceptance Criteria

* Driver can select a registered vehicle.
* Driver can provide:

  * Source
  * Destination
  * Departure Time
  * Available Seats
  * Fare Per Seat
* Ride is published successfully.
* Ride appears in search results.

### Priority

Critical

### Related Functional Requirements

* FR-006 Create Ride

---

## US-005: Update a Ride

### User Story

**As a Driver, I want to update ride details so that passengers always have accurate trip information.**

### Acceptance Criteria

* Driver can modify ride details before departure.
* Updated information is reflected immediately.
* Existing bookings remain unaffected.

### Priority

Medium

### Related Functional Requirements

* FR-007 Update Ride

---

## US-006: Cancel a Ride

### User Story

**As a Driver, I want to cancel a ride so that I can handle unexpected changes in my travel plans.**

### Acceptance Criteria

* Driver can cancel an active ride.
* Passengers receive cancellation notifications.
* Associated bookings are cancelled.
* Ride is removed from future search results.

### Priority

High

### Related Functional Requirements

* FR-008 Cancel Ride

---

# Epic 3: User Management

## US-007: Register an Account

### User Story

**As a New User, I want to register an account so that I can access Rydo services.**

### Acceptance Criteria

* User provides valid registration details.
* Email and phone number are unique.
* Account is created successfully.
* User can log in after registration.

### Priority

Critical

### Related Functional Requirements

* FR-001 Register

---

## US-008: Login to the Platform

### User Story

**As a User, I want to log in securely so that I can access my rides and bookings.**

### Acceptance Criteria

* Valid credentials are accepted.
* JWT token is generated.
* User gains access to authorized resources.
* Invalid credentials are rejected.

### Priority

Critical

### Related Functional Requirements

* FR-002 Login

---

## US-009: Update Profile Information

### User Story

**As a User, I want to update my profile information so that my account details remain accurate.**

### Acceptance Criteria

* User can edit personal details.
* Changes are validated.
* Updated profile is stored successfully.

### Priority

Medium

### Related Functional Requirements

* FR-004 Update Profile

---

# Epic 4: Vehicle Management

## US-010: Register a Vehicle

### User Story

**As a Driver, I want to register my vehicle so that I can create rides for passengers.**

### Acceptance Criteria

* Driver enters vehicle details.
* Vehicle information is validated.
* Vehicle is linked to the driver's account.
* Vehicle becomes available for ride creation.

### Priority

High

### Related Functional Requirements

* FR-005 Register Vehicle

---

# Epic 5: Platform Administration

## US-011: Monitor Platform Rides

### User Story

**As an Admin, I want to monitor rides so that misuse can be prevented.**

### Acceptance Criteria

* Admin can view all rides.
* Admin can search rides by status.
* Admin can identify suspicious activities.
* Admin can review ride history.

### Priority

High

### Related Functional Requirements

* FR-018 Admin Dashboard

---

## US-012: Manage Users

### User Story

**As an Admin, I want to manage users so that platform policies are enforced.**

### Acceptance Criteria

* Admin can view registered users.
* Admin can search users.
* Admin can block or suspend accounts.
* Admin can reactivate eligible users.

### Priority

High

### Related Functional Requirements

* FR-019 User Management

---

# Future User Stories (Phase 2)

## US-013

**As a Rider, I want to make secure online payments so that I can confirm bookings instantly.**

## US-014

**As a Driver, I want to receive booking requests so that I can manage passenger approvals.**

## US-015

**As a User, I want to rate and review other users so that trust can be built within the platform.**

## US-016

**As a Rider, I want to track my ride in real time so that I know the driver's current location.**

## US-017

**As an Admin, I want to view analytics dashboards so that platform performance can be monitored.**

## US-018

**As a User, I want to see my environmental impact so that I can understand my contribution to sustainability.**

---

# Story Prioritization

### Sprint 1 (MVP Foundation)

* US-007 Register
* US-008 Login
* US-009 Update Profile
* US-010 Register Vehicle

### Sprint 2 (Core Ride Flow)

* US-004 Create Ride
* US-001 Search Rides
* US-002 Book Ride
* US-003 Cancel Booking

### Sprint 3 (Management Features)

* US-005 Update Ride
* US-006 Cancel Ride
* US-011 Monitor Rides
* US-012 Manage Users

### Sprint 4 (Advanced Features)

* Payments
* Notifications
* Ratings & Reviews
* Analytics
* Sustainability Dashboard
