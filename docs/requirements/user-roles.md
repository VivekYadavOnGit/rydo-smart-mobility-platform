# Rydo User Roles & Permissions

## Overview

The Rydo platform follows a **Role-Based Access Control (RBAC)** model to ensure secure and controlled access to system resources. Every authenticated user is assigned a specific role that determines the actions they can perform within the platform.

---

# Rider Role

## Description

A Rider is a user who uses the platform to discover, join, and manage shared rides created by drivers.

### Primary Objectives

* Find affordable rides.
* Share travel costs.
* Reduce commuting expenses.
* Contribute to sustainable transportation.

### Permissions

#### Ride Discovery

* Search available rides.
* View ride details.
* Filter rides by source, destination, time, and vehicle type.
* View driver information and ratings.

#### Booking Management

* Book one or more seats in a ride.
* View booking history.
* View booking status.
* Cancel bookings before ride departure.

#### Profile Management

* Update profile information.
* Change contact details.
* Manage account preferences.

### Restrictions

* Cannot create rides.
* Cannot manage rides created by others.
* Cannot access administrative functions.
* Cannot modify booking information of other users.

### Available Functional Requirements

* FR-001 Register
* FR-002 Login
* FR-003 Logout
* FR-004 Update Profile
* FR-009 Search Rides
* FR-010 Book Seats
* FR-011 Cancel Booking

---

# Driver Role

## Description

A Driver is a registered user who owns a vehicle and can publish rides for passengers to join.

### Primary Objectives

* Offer available seats to passengers.
* Earn compensation for travel expenses.
* Reduce empty vehicle trips.
* Increase vehicle occupancy.

### Permissions

#### Vehicle Management

* Register vehicles.
* Update vehicle information.
* Manage vehicle details.

#### Ride Management

* Create rides.
* Update ride information.
* Cancel rides.
* View ride bookings.
* View passenger information.

#### Passenger Management

* Accept passenger requests (Future Enhancement).
* Reject passenger requests (Future Enhancement).
* Monitor seat occupancy.
* Mark rides as completed.

#### Profile Management

* Update personal profile.
* View driver ratings.
* Manage account settings.

### Restrictions

* Cannot manage rides owned by other drivers.
* Cannot access administrative controls.
* Cannot modify other user accounts.

### Available Functional Requirements

* FR-001 Register
* FR-002 Login
* FR-003 Logout
* FR-004 Update Profile
* FR-005 Register Vehicle
* FR-006 Create Ride
* FR-007 Update Ride
* FR-008 Cancel Ride

---

# Admin Role

## Description

The Admin role is responsible for monitoring, managing, and maintaining the overall health of the Rydo platform.

### Primary Objectives

* Ensure platform integrity.
* Monitor user activities.
* Handle policy violations.
* Maintain operational oversight.

### Permissions

#### User Management

* View all registered users.
* Search users.
* View user details.
* Block suspicious users.
* Suspend accounts.
* Reactivate accounts.

#### Ride Management

* View all rides.
* Monitor ride activity.
* Investigate ride disputes.
* Cancel fraudulent rides if necessary.

#### Platform Monitoring

* View platform analytics.
* View user growth metrics.
* Monitor ride completion rates.
* Monitor sustainability metrics.
* Review booking statistics.

#### Reporting & Moderation

* Handle abuse reports.
* Investigate complaints.
* Review flagged content.
* Generate operational reports.

### Restrictions

* Cannot access user passwords.
* Cannot impersonate users.
* Cannot modify financial records without authorization.

### Available Functional Requirements

* FR-001 Register
* FR-002 Login
* FR-003 Logout
* FR-018 Admin Dashboard
* FR-019 User & Content Management

---

# Role Permission Matrix

| Functionality              | Rider | Driver | Admin |
| -------------------------- | ----- | ------ | ----- |
| Register Account           | ✅     | ✅      | ✅     |
| Login                      | ✅     | ✅      | ✅     |
| Logout                     | ✅     | ✅      | ✅     |
| Update Profile             | ✅     | ✅      | ✅     |
| Register Vehicle           | ❌     | ✅      | ❌     |
| Search Rides               | ✅     | ✅      | ✅     |
| Book Ride                  | ✅     | ❌      | ❌     |
| Cancel Booking             | ✅     | ❌      | ❌     |
| Create Ride                | ❌     | ✅      | ❌     |
| Update Ride                | ❌     | ✅      | ❌     |
| Cancel Ride                | ❌     | ✅      | ✅     |
| View Passenger Details     | ❌     | ✅      | ✅     |
| Accept Passengers          | ❌     | ✅      | ❌     |
| View All Users             | ❌     | ❌      | ✅     |
| Block Users                | ❌     | ❌      | ✅     |
| View All Rides             | ❌     | ❌      | ✅     |
| Access Analytics Dashboard | ❌     | ❌      | ✅     |

---

# RBAC Implementation Strategy

### Roles

```text
ROLE_RIDER
ROLE_DRIVER
ROLE_ADMIN
```

### Authorization Flow

1. User logs in.
2. Auth Service validates credentials.
3. JWT token is generated.
4. User role is embedded within JWT claims.
5. API Gateway validates token.
6. Requested service verifies permissions before processing the request.

### Example JWT Claims

```json
{
  "userId": "12345",
  "email": "user@rydo.com",
  "role": "DRIVER",
  "exp": 1785600000
}
```

---

# Future Roles

### Corporate Partner

* Manage employee ride pools.
* View organization ride statistics.

### Support Executive

* Handle customer complaints.
* Access support dashboards.

### Fleet Manager

* Manage multiple drivers and vehicles.
* Monitor fleet performance.

These roles can be added later without impacting the existing Rider, Driver, and Admin architecture due to the RBAC-based design.
