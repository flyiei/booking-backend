package com.hotel.booking.exception.mapper;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.apachecommons.CommonsLog;

@Provider
@CommonsLog
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException ex) {

        log.warn(ex);
        return Response.fromResponse(ex.getResponse()).
                entity(ex.getMessage()).
                type("text/plain").
                build();
    }
}
