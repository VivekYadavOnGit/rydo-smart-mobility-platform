package com.rydo.events;

import java.time.LocalDateTime;

public class RideBookedEvent {

    private String bookingId;
    private String rideId;
    private String riderId;
    private int seatsBooked;
    private LocalDateTime bookedAt;

    public RideBookedEvent() {
    }

    public RideBookedEvent(String bookingId, String rideId, String riderId,
                           int seatsBooked, LocalDateTime bookedAt) {
        this.bookingId = bookingId;
        this.rideId = rideId;
        this.riderId = riderId;
        this.seatsBooked = seatsBooked;
        this.bookedAt = bookedAt;
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }

    public String getRiderId() { return riderId; }
    public void setRiderId(String riderId) { this.riderId = riderId; }

    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }

    public LocalDateTime getBookedAt() { return bookedAt; }
    public void setBookedAt(LocalDateTime bookedAt) { this.bookedAt = bookedAt; }
}