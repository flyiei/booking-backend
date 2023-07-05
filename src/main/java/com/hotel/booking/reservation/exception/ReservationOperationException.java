package com.hotel.booking.reservation.exception;

public abstract class ReservationOperationException extends RuntimeException{

    public ReservationOperationException (String msg) {
        super(msg);
    }

}
