package com.hotel.booking.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationPageData {

    List<ReservationData> reservations = new ArrayList<>();
    Long totalPages=0L;

}
