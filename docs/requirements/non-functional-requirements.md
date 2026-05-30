# Rydo Non-Functional Requirements (NFR)

## Overview

Non-Functional Requirements define the quality attributes, performance expectations, security standards, scalability targets, and operational constraints of the Rydo platform. These requirements ensure that the system is reliable, secure, maintainable, and capable of supporting future growth.

---

# NFR-001: Performance

## Description

The Rydo platform shall provide fast and responsive user interactions to ensure a smooth experience for riders and drivers.

### Requirements

#### Search Response Time

* Ride search requests shall return results within **2 seconds** under normal operating conditions.
* Frequently searched routes shall be cached using Redis.

#### API Response Time

* Authentication APIs: < 500 ms
* Ride Search APIs: < 2 seconds
* Booking APIs: < 1 second
* Profile APIs: < 1 second

#### Database Performance

* Indexed columns shall be used for:

  * Source
  * Destination
  * Driver ID
  * Rider ID
  * Departure Time
  * Ride Status

### Success Criteria

* 95% of requests meet defined response time targets.
* Average API latency remains below 500 ms.

---

# NFR-002: Security

## Description

The system shall protect user data, prevent unauthorized access, and ensure secure communication across all services.

### Authentication Security

#### JWT Authentication

* All protected endpoints shall require valid JWT tokens.
* Access tokens shall have limited validity periods.
* Refresh tokens shall be securely managed.

#### Password Security

* Passwords shall never be stored in plain text.
* BCrypt hashing shall be used for password encryption.
* Password validation rules:

  * Minimum 8 characters
  * At least one uppercase letter
  * At least one lowercase letter
  * At least one number
  * At least one special character

### Data Security

#### API Security

* HTTPS shall be enforced in production.
* Sensitive data shall be encrypted in transit.
* Input validation shall prevent injection attacks.

#### Access Control

* Role-Based Access Control (RBAC) shall be implemented.
* Drivers, Riders, and Admins shall have separate permissions.

### Success Criteria

* Zero plaintext password storage.
* Zero unauthorized endpoint access.
* All communications encrypted using TLS.

---

# NFR-003: Availability

## Description

The Rydo platform shall remain available and operational for users with minimal downtime.

### Requirements

#### System Availability

* Target uptime: **99%**
* Scheduled maintenance shall be announced in advance.
* Critical services shall support automatic restart.

#### Fault Tolerance

* Service failures shall not bring down the entire platform.
* Microservices shall be independently deployable.
* Health checks shall continuously monitor service status.

### Recovery Requirements

#### Recovery Time Objective (RTO)

* Less than 30 minutes

#### Recovery Point Objective (RPO)

* Less than 15 minutes

### Success Criteria

* Monthly uptime ≥ 99%
* Service recovery within defined thresholds.

---

# NFR-004: Scalability

## Description

The system shall support increasing numbers of users, rides, and booking requests without significant degradation in performance.

### Concurrent Users

The platform shall support:

* 1,000+ concurrent users (MVP)
* 10,000+ concurrent users (Future Scaling)
* Horizontal scaling capability through container orchestration.

### Horizontal Scaling

The following services must be independently scalable:

* Auth Service
* User Service
* Ride Service
* Booking Service
* Notification Service

### Scaling Strategy

#### Application Layer

* Docker Containers
* Kubernetes Pods
* Auto Scaling Policies

#### Database Layer

* Read Replicas
* Database Partitioning
* Connection Pooling

#### Cache Layer

* Redis Clustering

### Success Criteria

* System remains responsive under 1,000+ concurrent users.
* No service bottlenecks during peak commute hours.

---

# NFR-005: Reliability

## Description

The system shall consistently perform expected operations without failures.

### Requirements

* Booking transactions shall be atomic.
* No seat overbooking shall occur.
* Failed transactions shall be rolled back automatically.
* Event delivery through Kafka shall ensure message reliability.

### Success Criteria

* Booking consistency = 100%
* No duplicate booking records.
* No data corruption incidents.

---

# NFR-006: Maintainability

## Description

The system shall be easy to modify, test, and extend.

### Requirements

* Microservices architecture.
* Clean Architecture principles.
* Modular code structure.
* API documentation through Swagger/OpenAPI.
* Unit and integration testing.

### Code Quality Targets

* Minimum 80% unit test coverage.
* Static code analysis using SonarQube.
* Consistent coding standards.

### Success Criteria

* New features can be added with minimal impact.
* Independent service deployments.

---

# NFR-007: Usability

## Description

The platform shall provide an intuitive and user-friendly experience.

### Requirements

* Mobile-responsive design.
* Simple ride creation workflow.
* Simple booking workflow.
* Consistent UI components.
* Accessible navigation.

### Success Criteria

* New users can create and book rides without training.
* Reduced user drop-off during booking.

---

# NFR-008: Observability & Monitoring

## Description

The system shall provide visibility into service health, performance, and failures.

### Requirements

#### Monitoring

* Prometheus Metrics
* Grafana Dashboards

#### Logging

* Centralized Logging
* Structured JSON Logs

#### Tracing

* Distributed Tracing
* Request Correlation IDs

### Metrics to Track

* API Response Times
* Error Rates
* Booking Success Rate
* Active Users
* Ride Completion Rate
* Fuel Saved
* Carbon Saved

### Success Criteria

* Critical issues detected within 5 minutes.
* Root cause identification through centralized logs.

---

# NFR-009: Data Integrity

## Description

The platform shall ensure accuracy and consistency of stored data.

### Requirements

* Database constraints.
* Foreign key validation.
* Transaction management.
* Event-driven consistency using Kafka.

### Success Criteria

* No orphan records.
* Consistent ride and booking data.

---

# NFR-010: Deployment & DevOps

## Description

The platform shall support automated deployments and continuous delivery.

### Requirements

#### CI/CD Pipeline

* GitHub Actions
* Automated Build
* Automated Testing
* Docker Image Creation
* Automated Deployment

#### Environment Strategy

* Development
* Staging
* Production

### Infrastructure

* Docker
* Kubernetes
* NGINX Ingress
* PostgreSQL
* Redis
* Kafka

### Success Criteria

* Zero manual deployment steps.
* Successful rollback mechanism.

---

# NFR Summary

| ID      | Category        | Requirement              |
| ------- | --------------- | ------------------------ |
| NFR-001 | Performance     | Search response < 2 sec  |
| NFR-002 | Security        | JWT + BCrypt + HTTPS     |
| NFR-003 | Availability    | 99% uptime               |
| NFR-004 | Scalability     | 1000+ concurrent users   |
| NFR-005 | Reliability     | No overbooking           |
| NFR-006 | Maintainability | Modular microservices    |
| NFR-007 | Usability       | Simple and responsive UI |
| NFR-008 | Monitoring      | Logs, Metrics, Traces    |
| NFR-009 | Data Integrity  | Consistent transactions  |
| NFR-010 | DevOps          | Automated CI/CD          |
