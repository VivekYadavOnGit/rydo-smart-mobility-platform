package com.rydo.events;

import java.time.LocalDateTime;

public class RideCompletedEvent {

    private String rideId;
    private String driverId;
    private LocalDateTime completedAt;

    public RideCompletedEvent() {
    }

    public RideCompletedEvent(String rideId, String driverId,
                              LocalDateTime completedAt) {
        this.rideId = rideId;
        this.driverId = driverId;
        this.completedAt = completedAt;
    }

    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}