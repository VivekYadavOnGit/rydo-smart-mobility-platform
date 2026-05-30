# Rydo Database Design (Entity Definitions)

## Auth Service

### users

Stores authentication and authorization information.

| Column         | Description                   |
| -------------- | ----------------------------- |
| id             | Unique user identifier (UUID) |
| email          | User email (unique)           |
| phone_number   | User mobile number            |
| password_hash  | Encrypted password            |
| account_status | ACTIVE, BLOCKED, SUSPENDED    |
| email_verified | Email verification status     |
| phone_verified | Phone verification status     |
| created_at     | Account creation timestamp    |
| updated_at     | Last update timestamp         |
| last_login_at  | Last login timestamp          |

### roles

Defines application roles.

| Column      | Description          |
| ----------- | -------------------- |
| id          | Role identifier      |
| role_name   | RIDER, DRIVER, ADMIN |
| description | Role description     |
| created_at  | Creation timestamp   |

### user_roles

Maps users to roles.

| Column      | Description          |
| ----------- | -------------------- |
| id          | Mapping identifier   |
| user_id     | Reference to users   |
| role_id     | Reference to roles   |
| assigned_at | Assignment timestamp |

### refresh_tokens

Stores active refresh tokens.

| Column     | Description             |
| ---------- | ----------------------- |
| id         | Token identifier        |
| user_id    | Reference to users      |
| token      | Refresh token value     |
| expires_at | Expiry timestamp        |
| revoked    | Token revocation status |
| created_at | Creation timestamp      |

---

# User Service

### profiles

Stores personal information.

| Column            | Description                 |
| ----------------- | --------------------------- |
| id                | Profile identifier          |
| user_id           | Auth Service user reference |
| first_name        | User first name             |
| last_name         | User last name              |
| profile_photo_url | Profile image URL           |
| gender            | User gender                 |
| date_of_birth     | Date of birth               |
| address           | User address                |
| city              | City                        |
| state             | State                       |
| country           | Country                     |
| emergency_contact | Emergency contact number    |
| rating            | User rating                 |
| created_at        | Creation timestamp          |
| updated_at        | Last update timestamp       |

### vehicles

Stores driver vehicle information.

| Column              | Description                 |
| ------------------- | --------------------------- |
| id                  | Vehicle identifier          |
| user_id             | Driver reference            |
| vehicle_type        | Bike, Car, Auto             |
| brand               | Manufacturer                |
| model               | Vehicle model               |
| registration_number | Registration number         |
| color               | Vehicle color               |
| seat_capacity       | Total seats                 |
| insurance_number    | Insurance identifier        |
| insurance_expiry    | Insurance expiry date       |
| verification_status | PENDING, VERIFIED, REJECTED |
| created_at          | Creation timestamp          |

---

# Ride Service

### rides

Stores ride details created by drivers.

| Column               | Description                              |
| -------------------- | ---------------------------------------- |
| id                   | Ride identifier                          |
| driver_id            | Driver reference                         |
| vehicle_id           | Vehicle reference                        |
| source_location      | Starting point                           |
| destination_location | Ending point                             |
| departure_time       | Ride departure time                      |
| arrival_time         | Estimated arrival time                   |
| available_seats      | Remaining seats                          |
| fare_per_seat        | Cost per seat                            |
| ride_status          | SCHEDULED, STARTED, COMPLETED, CANCELLED |
| ride_type            | POOL, PRIVATE                            |
| created_at           | Creation timestamp                       |
| updated_at           | Last update timestamp                    |

### ride_stops

Stores intermediate stops.

| Column         | Description           |
| -------------- | --------------------- |
| id             | Stop identifier       |
| ride_id        | Associated ride       |
| stop_order     | Sequence number       |
| stop_name      | Stop location         |
| latitude       | Latitude coordinate   |
| longitude      | Longitude coordinate  |
| estimated_time | Expected arrival time |

---

# Booking Service

### bookings

Stores rider bookings.

| Column            | Description                              |
| ----------------- | ---------------------------------------- |
| id                | Booking identifier                       |
| ride_id           | Ride reference                           |
| rider_id          | User reference                           |
| seats_booked      | Number of seats                          |
| booking_amount    | Total booking fare                       |
| booking_status    | PENDING, CONFIRMED, CANCELLED, COMPLETED |
| payment_status    | PENDING, PAID, FAILED, REFUNDED          |
| booking_time      | Booking timestamp                        |
| cancellation_time | Cancellation timestamp                   |
| created_at        | Creation timestamp                       |
| updated_at        | Last update timestamp                    |

---

# Relationships

## Auth Service

```text
users
 ├── user_roles
 │      └── roles
 └── refresh_tokens
```

## User Service

```text
profiles
    │
    └── vehicles
```

## Ride Service

```text
rides
  └── ride_stops
```

## Booking Service

```text
rides
   │
   └── bookings
           │
           └── rider(profile/user)
```

---

# Cross-Service References

```text
Auth Service
    users
      │
      ▼
User Service
    profiles
      │
      ▼
vehicles
      │
      ▼
Ride Service
    rides
      │
      ▼
Booking Service
    bookings
```

Note:

* Each microservice owns its own database.
* References across services are logical references (UUIDs), not foreign keys.
* No direct database joins between services.
* Inter-service data is accessed through APIs or Kafka events.
