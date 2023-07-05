package com.hotel.booking.reservation.exception;


public class ReservationDeleteException extends ReservationOperationException {

    public ReservationDeleteException(Long reservationID, String msg) {

        super(String.format("Can't delete reservation that ID is [%s] because [%s]",
                reservationID == null ? "" : reservationID,
                msg));
    }

}
