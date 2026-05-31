package com.rydo.events;

import java.time.LocalDateTime;

public class RideCreatedEvent {

    private String rideId;
    private String driverId;
    private String source;
    private String destination;
    private int availableSeats;
    private LocalDateTime createdAt;

    public RideCreatedEvent() {
    }

    public RideCreatedEvent(String rideId, String driverId, String source,
                            String destination, int availableSeats,
                            LocalDateTime createdAt) {
        this.rideId = rideId;
        this.driverId = driverId;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.createdAt = createdAt;
    }

    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}