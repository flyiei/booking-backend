package com.hotel.booking.reservation.exception;

import com.hotel.booking.reservation.model.ReservationData;

public class ReservationSaveAndUpdateException extends ReservationOperationException {

    public ReservationSaveAndUpdateException(ReservationData reservationData, String msg) {
        super(String.format("Can't save or update ReservationData [%s] because [%s]",
                reservationData == null ? "" : reservationData,
                msg));
    }

    public ReservationSaveAndUpdateException(Long reservationID, String msg) {
        super(String.format("Can't save or update ReservationData with ID [%s] because [%s]",
                reservationID == null ? "" : reservationID,
                msg));
    }
}
