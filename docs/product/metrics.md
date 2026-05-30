# Rydo Analytics & Sustainability Metrics

## Overview

To measure the growth, efficiency, and environmental impact of the Rydo platform, a dedicated Analytics Service will collect and process operational data from various microservices. These metrics help administrators monitor platform performance while showcasing Rydo's contribution toward sustainable transportation.

---

# Platform Metrics

## 1. Total Users

### Description

Represents the total number of registered users on the platform, including both riders and drivers.

### Formula

```
Total Users = Count(All Registered Users)
```

### Business Value

* Tracks platform growth.
* Measures user acquisition success.
* Helps estimate future infrastructure requirements.

### Data Source

* Auth Service
* User Service

---

## 2. Total Rides

### Description

Represents the total number of rides created by drivers.

### Formula

```
Total Rides = Count(All Created Rides)
```

### Business Value

* Indicates platform activity.
* Measures driver engagement.
* Helps forecast demand patterns.

### Data Source

* Ride Service

---

## 3. Completed Rides

### Description

Represents rides that successfully reached their destination and were marked as completed.

### Formula

```
Completed Rides = Count(Rides where status = COMPLETED)
```

### Business Value

* Tracks successful ride executions.
* Measures platform reliability.
* Used in performance reporting.

### Data Source

* Ride Service

---

## 4. Average Occupancy

### Description

Measures how effectively vehicle seats are utilized across all completed rides.

### Formula

```
Average Occupancy (%) =
(Total Booked Seats / Total Available Seats)
× 100
```

### Example

```
Available Seats = 1000
Booked Seats = 720

Average Occupancy = 72%
```

### Business Value

* Measures ride-sharing efficiency.
* Higher occupancy means fewer vehicles on roads.
* Important sustainability indicator.

### Data Source

* Ride Service
* Booking Service

---

## 5. Ride Completion Rate

### Description

Measures the percentage of rides that successfully complete compared to all rides created.

### Formula

```
Ride Completion Rate (%) =
(Completed Rides / Total Rides)
× 100
```

### Example

```
Total Rides = 5000
Completed Rides = 4500

Completion Rate = 90%
```

### Business Value

* Indicates service reliability.
* Helps identify cancellation trends.
* Useful for SLA monitoring.

### Data Source

* Ride Service

---

# Sustainability Metrics

These metrics form Rydo's unique value proposition by quantifying environmental benefits generated through ride pooling.

---

## 6. Fuel Saved

### Description

Estimates the amount of fuel conserved due to multiple passengers sharing a single vehicle instead of traveling separately.

### Formula

```
Fuel Saved (Litres) =
(Number of Shared Seats × Average Trip Distance)
÷ Average Vehicle Mileage
```

### Example

```
Shared Seats = 5000
Average Distance = 15 km
Average Mileage = 15 km/l

Fuel Saved = 5000 litres
```

### Business Value

* Demonstrates cost savings.
* Highlights reduced fuel consumption.
* Useful for ESG reporting.

### Data Source

* Ride Service
* Booking Service
* Vehicle Service

---

## 7. Carbon Saved

### Description

Estimates the reduction in carbon dioxide emissions achieved through ride sharing.

### Formula

```
Carbon Saved (kg CO₂) =
Fuel Saved × Emission Factor
```

### Standard Emission Factor

```
Petrol Vehicle ≈ 2.31 kg CO₂/litre

Diesel Vehicle ≈ 2.68 kg CO₂/litre
```

### Example

```
Fuel Saved = 1000 litres

Carbon Saved =
1000 × 2.31

= 2310 kg CO₂
```

### Business Value

* Core sustainability KPI.
* Useful for government partnerships.
* Strengthens Rydo's green mobility vision.
* Can be displayed publicly as environmental impact.

### Data Source

* Analytics Service
* Vehicle Service

---

# Analytics Dashboard

## Admin Dashboard KPIs

### User Statistics

* Total Users
* Active Users
* Drivers
* Riders
* New Registrations

### Ride Statistics

* Total Rides
* Active Rides
* Completed Rides
* Cancelled Rides
* Ride Completion Rate

### Booking Statistics

* Total Bookings
* Successful Bookings
* Cancelled Bookings
* Average Seats Per Ride

### Sustainability Statistics

* Fuel Saved
* Carbon Saved
* Shared Kilometers
* Vehicles Removed From Roads (Estimated)

### Revenue Statistics (Future)

* Platform Revenue
* Commission Earned
* Payment Success Rate

---

# Analytics Service Architecture

### Consumes Events From

* User Service
* Ride Service
* Booking Service
* Vehicle Service
* Payment Service (Future)

### Kafka Events

UserRegisteredEvent

RideCreatedEvent

RideCompletedEvent

BookingConfirmedEvent

BookingCancelledEvent

VehicleRegisteredEvent

### Storage

analytics_db

Tables:

* user_metrics
* ride_metrics
* sustainability_metrics
* booking_metrics

---

# Why These Metrics Matter

Rydo is not just a ride-sharing platform; it is a sustainable mobility ecosystem.

By continuously tracking:

* Ride utilization
* Fuel savings
* Carbon emission reduction
* Vehicle occupancy

Rydo can demonstrate measurable environmental impact while improving transportation efficiency and reducing commuting costs for users.
