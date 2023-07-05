package com.hotel.booking.reservation.model;

import com.hotel.booking.reservation.repository.dto.Reservation;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationData {

    private Long id;
    @NotEmpty(message = "agency must not be null or empty")
    private String agency;
    @NotEmpty(message = "hotel must not be null or empty")
    private String hotel;
//    @NotEmpty(message = "reserveDate must not be null or empty")
    private String reserveDate;
    @NotEmpty(message = "consumer must not be null or empty")
    private String consumer;


    public static Reservation buildReservation(ReservationData reservationData) {

        return Reservation.builder()
                .agency(reservationData.getAgency())
                .hotel(reservationData.getHotel())
                .reserveDate(reservationData.getReserveDate())
                .consumer(reservationData.getConsumer())
                .build();
    }

    public static ReservationData buildReservationData(Reservation reservation) {

        return reservation != null ? ReservationData.builder()
                .id(reservation.getId())
                .agency(reservation.getAgency())
                .hotel(reservation.getHotel())
                .reserveDate(reservation.getReserveDate())
                .consumer(reservation.getConsumer())
                .build() : null;
    }
}
