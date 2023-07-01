package com.hotel.booking.reservation.repository;

import com.hotel.booking.reservation.repository.dto.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
