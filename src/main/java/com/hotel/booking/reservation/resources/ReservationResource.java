package com.hotel.booking.reservation.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.ArrayList;
import java.util.List;

@Path("/reservations")
public class ReservationResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> getReservations () {
        List<String> results = new ArrayList<>();
        results.add("1");
        results.add("2");

        return results;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response saveReservation () {
        return Response.notAcceptable(null).build();
    }


}
