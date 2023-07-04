package com.hotel.booking;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class JerseyAppConfig extends ResourceConfig {
    public JerseyAppConfig() {
//        CORS is a security mechanism implemented by
//        web browsers to prevent requests from different
//        origins (domains) unless the server explicitly allows it.
        register(CorsFilter.class);

        //Register resource classes
        packages("com.hotel.booking.reservation.resources");

        //Register exception mappers
        packages("com.hotel.booking.reservation.exception.mapper");
        packages("com.hotel.booking.exception.mapper");
    }

}
