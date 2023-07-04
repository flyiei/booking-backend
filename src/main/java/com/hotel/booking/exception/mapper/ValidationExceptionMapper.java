package com.hotel.booking.exception.mapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.apachecommons.CommonsLog;

@Provider
@CommonsLog
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException ex) {
        log.warn(ex);
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
            message.append(cv.getPropertyPath() + " " + cv.getMessage() + "\n");
        }

        return Response.status(Response.Status.BAD_REQUEST).
                entity(message.toString()).
                type("text/plain").
                build();
    }
}
