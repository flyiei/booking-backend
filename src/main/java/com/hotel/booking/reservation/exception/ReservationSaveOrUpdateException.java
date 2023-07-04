package com.hotel.booking.reservation.exception;

import com.hotel.booking.reservation.model.ReservationData;

public class ReservationSaveOrUpdateException extends RuntimeException{

    public ReservationSaveOrUpdateException (String msg) {
        super(msg);
    }

    public static String buildErrorMessageForSaveOrUpdate (ReservationData reservationData, String msg) {

        return String.format("Can't save or update ReservationData [%s] because [%s]", reservationData, msg);
    }

}
