package com.hotel.booking.reservation.service;

import com.hotel.booking.reservation.model.ReservationData;


import java.util.List;

public interface ReservationService {

    ReservationData update(Long reservationID, ReservationData reservationData);

    ReservationData save(ReservationData reservationData);

    List<ReservationData> findAllByPaging(Integer pageNum, Integer pageSize);

    List<ReservationData> findAll();

    ReservationData findByID(Long id);

    void delete(Long id);

    Long calculateTotalPages(Integer pageSize);
}
