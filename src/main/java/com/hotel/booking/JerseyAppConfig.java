package com.hotel.booking;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class JerseyAppConfig extends ResourceConfig {
    public JerseyAppConfig() {
        packages("com.hotel.booking.reservation.resources");
    }

}
