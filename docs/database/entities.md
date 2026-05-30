# Rydo Domain Models

## User Entity

### Purpose

The User entity represents every individual using the Rydo platform, including Riders, Drivers, and Administrators. It serves as the primary identity record across all services.

### Attributes

| Field         | Type      | Description                                  |
| ------------- | --------- | -------------------------------------------- |
| id            | UUID      | Unique identifier for the user               |
| name          | String    | Full name of the user                        |
| email         | String    | Unique email address used for authentication |
| password      | String    | Securely hashed password                     |
| role          | Enum      | USER, DRIVER, ADMIN                          |
| phone         | String    | User's mobile number                         |
| rating        | Double    | Average rating received from other users     |
| createdAt     | Timestamp | Account creation timestamp                   |
| updatedAt     | Timestamp | Last profile update timestamp                |
| accountStatus | Enum      | ACTIVE, SUSPENDED, DELETED                   |

### Business Rules

* Email must be unique.
* Phone number must be unique.
* Passwords are stored using BCrypt hashing.
* Rating is automatically calculated from ride reviews.
* Drivers must have at least one verified vehicle before creating rides.

---

## Vehicle Entity

### Purpose

The Vehicle entity stores information about vehicles registered by drivers. A driver can own multiple vehicles but may choose only one active vehicle per ride.

### Attributes

| Field              | Type      | Description                   |
| ------------------ | --------- | ----------------------------- |
| id                 | UUID      | Unique vehicle identifier     |
| userId             | UUID      | Driver who owns the vehicle   |
| vehicleType        | Enum      | Bike, Car, SUV, Van           |
| vehicleNumber      | String    | Registration number           |
| model              | String    | Vehicle model name            |
| capacity           | Integer   | Total seating capacity        |
| fuelType           | Enum      | Petrol, Diesel, Electric, CNG |
| verificationStatus | Enum      | Pending, Verified, Rejected   |
| createdAt          | Timestamp | Registration timestamp        |

### Business Rules

* Vehicle number must be unique.
* Vehicle must be verified before ride creation.
* Capacity cannot exceed legal vehicle limits.
* Drivers can update vehicle details anytime.

---

## Ride Entity

### Purpose

The Ride entity represents a trip published by a driver for carpooling or ride-sharing.

### Attributes

| Field          | Type      | Description                                            |
| -------------- | --------- | ------------------------------------------------------ |
| id             | UUID      | Unique ride identifier                                 |
| driverId       | UUID      | Driver creating the ride                               |
| source         | String    | Starting location                                      |
| destination    | String    | Ending location                                        |
| departureTime  | Timestamp | Scheduled departure                                    |
| availableSeats | Integer   | Remaining seats available                              |
| status         | Enum      | CREATED, OPEN, FULL, IN_PROGRESS, COMPLETED, CANCELLED |
| farePerSeat    | Decimal   | Cost per passenger seat                                |
| vehicleId      | UUID      | Vehicle used for ride                                  |
| distance       | Double    | Route distance in kilometers                           |
| createdAt      | Timestamp | Ride creation timestamp                                |

### Business Rules

* Available seats cannot exceed vehicle capacity.
* Departure time must be in the future.
* Only verified drivers can publish rides.
* Ride status changes according to lifecycle events.
* Once a ride starts, bookings cannot be modified.

### Ride Lifecycle

1. CREATED
2. OPEN
3. FULL
4. IN_PROGRESS
5. COMPLETED
6. CANCELLED

---

## Booking Entity

### Purpose

The Booking entity represents seat reservations made by riders for a specific ride.

### Attributes

| Field         | Type      | Description                              |
| ------------- | --------- | ---------------------------------------- |
| id            | UUID      | Unique booking identifier                |
| rideId        | UUID      | Associated ride                          |
| riderId       | UUID      | Passenger making booking                 |
| seatCount     | Integer   | Number of seats reserved                 |
| status        | Enum      | PENDING, CONFIRMED, CANCELLED, COMPLETED |
| bookingTime   | Timestamp | Booking creation time                    |
| paymentStatus | Enum      | PENDING, SUCCESS, FAILED                 |
| totalAmount   | Decimal   | Total fare amount                        |

### Business Rules

* Seat count must be greater than zero.
* Seat count cannot exceed available seats.
* Riders cannot book their own rides.
* Cancelled bookings automatically release seats.
* Booking confirmation updates ride seat inventory.

### Booking Lifecycle

1. PENDING
2. CONFIRMED
3. CANCELLED
4. COMPLETED

---

# Entity Relationships

User (Driver)
│
├── Vehicle (1:N)
│
└── Ride (1:N)
│
└── Booking (1:N)
│
└── User (Rider)

---

# Future Enhancements

### Payment Entity

* UPI Payments
* Wallet Transactions
* Refund Tracking

### Rating Entity

* Driver Reviews
* Rider Reviews
* Ride Feedback

### Location Entity

* Live Tracking
* Route Optimization
* ETA Calculation

### Notification Entity

* Ride Alerts
* Booking Confirmations
* Payment Updates

### AI Recommendation Entity

* Ride Matching Score
* Route Similarity Index
* Dynamic Pricing Suggestions
