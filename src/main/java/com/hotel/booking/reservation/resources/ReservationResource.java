package com.hotel.booking.reservation.resources;

import com.hotel.booking.reservation.model.ReservationData;
import com.hotel.booking.reservation.service.ReservationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Path("/v1")
public class ReservationResource {

    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GET
    @Path("/reservations")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ReservationData> getReservations(@QueryParam("pageNum") @NotNull Integer pageNum,
                                                 @QueryParam("pageSize") @NotNull Integer pageSize) {

        return reservationService.findAllByPaging(pageNum, pageSize);
    }

    @GET
    @Path("/reservations/totalPages")
    @Produces({MediaType.APPLICATION_JSON})
    public Long getReservationsTotalPages(@QueryParam("pageSize") @NotNull Integer pageSize) {

        return reservationService.calculateTotalPages(pageSize);
    }

    @GET
    @Path("/reservations/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getReservationByID(@PathParam("id") Long id) {

        return Response.ok(reservationService.findByID(id)).build();
    }

    @DELETE
    @Path("/reservations/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteReservation(@PathParam("id") Long id) {
        reservationService.delete(id);
        return Response.ok("success").build();
    }

    @PUT
    @Path("/reservations/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateReservation(@PathParam ("id") Long id, @Valid @NotNull ReservationData reservationData) {

        return Response.ok(reservationService.update(id, reservationData)).build();
    }


    @POST
    @Path("/reservation")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response saveReservation(@Valid @NotNull ReservationData reservationData) {

        return Response.ok(reservationService.save(reservationData)).build();
    }


}
