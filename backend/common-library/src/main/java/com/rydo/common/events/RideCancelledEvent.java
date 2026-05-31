package com.rydo.events;

import java.time.LocalDateTime;

public class RideCancelledEvent {

    private String rideId;
    private String cancelledBy;
    private String reason;
    private LocalDateTime cancelledAt;

    public RideCancelledEvent() {
    }

    public RideCancelledEvent(String rideId, String cancelledBy,
                              String reason, LocalDateTime cancelledAt) {
        this.rideId = rideId;
        this.cancelledBy = cancelledBy;
        this.reason = reason;
        this.cancelledAt = cancelledAt;
    }

    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }

    public String getCancelledBy() { return cancelledBy; }
    public void setCancelledBy(String cancelledBy) { this.cancelledBy = cancelledBy; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getCancelledAt() { return cancelledAt; }
    public void setCancelledAt(LocalDateTime cancelledAt) { this.cancelledAt = cancelledAt; }
}