package com.hotel.booking.exception.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.apachecommons.CommonsLog;

@Provider
@CommonsLog
public class UncatchedExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex) {
        log.error(ex);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ex.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

}

