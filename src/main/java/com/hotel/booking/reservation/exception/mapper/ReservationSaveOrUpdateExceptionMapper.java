package com.hotel.booking.reservation.exception.mapper;

import com.hotel.booking.reservation.exception.ReservationSaveOrUpdateException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.apachecommons.CommonsLog;

@Provider
@CommonsLog
public class ReservationSaveOrUpdateExceptionMapper implements ExceptionMapper<ReservationSaveOrUpdateException> {
    @Override
    public Response toResponse(ReservationSaveOrUpdateException ex) {
        log.warn(ex);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ex.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
