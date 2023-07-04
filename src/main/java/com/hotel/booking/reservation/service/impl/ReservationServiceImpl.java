package com.hotel.booking.reservation.service.impl;

import com.hotel.booking.reservation.exception.ReservationSaveOrUpdateException;
import com.hotel.booking.reservation.model.ReservationData;
import com.hotel.booking.reservation.model.ReservationPageData;
import com.hotel.booking.reservation.repository.ReservationRepository;
import com.hotel.booking.reservation.repository.dto.Reservation;
import com.hotel.booking.reservation.service.ReservationService;
import jakarta.persistence.Transient;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    @Transient
    public ReservationData update(Long reservationID, ReservationData reservationData) {
        Assert.notNull(reservationID, "reservationID can not be null");
        Assert.notNull(reservationData, "reservationData can not be null");
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationID);
        Reservation reservation = reservationOpt.isPresent() ? reservationOpt.get() : null;
        if (reservation == null) {

            throw new ReservationSaveOrUpdateException(
                    ReservationSaveOrUpdateException.buildErrorMessageForSaveOrUpdate(
                            reservationData, "ID does not exist!"));
        }
        reservation = ReservationData.buildReservation(reservationData);
        reservation.setId(reservationID);
        return ReservationData.buildReservationData(reservationRepository.save(reservation));
    }

    @Override
    @Transient
    public ReservationData save(ReservationData reservationData) {

        Assert.notNull(reservationData, "reservationData can not be null");
        Reservation reservation = ReservationData.buildReservation(reservationData);
        return ReservationData.buildReservationData(reservationRepository.save(reservation));
    }

    @Override
    public Long calculateTotalPages(Integer pageSize) {
        Long totalItems = reservationRepository.count();
        // This ensures the remainder is accounted.
        return (totalItems + pageSize - 1) / pageSize;
    }

    @Override
    public List<ReservationData> findAllByPaging(Integer pageNum, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id"));
        return reservationRepository.findAll(pageable).stream()
                .map(ReservationData::buildReservationData)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationPageData buildReservationPageData (Integer pageNum, Integer pageSize) {
        return ReservationPageData.builder()
                .reservations(findAllByPaging(pageNum, pageSize))
                .totalPages(calculateTotalPages(pageSize))
                .build();
    }


    @Override
    public List<ReservationData> findAll() {

        return reservationRepository.findAll().stream()
                .map(ReservationData::buildReservationData)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationData findByID(Long id) {
        Assert.notNull(id, "reservation id is required for delete");

        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        return ReservationData.buildReservationData(reservationOpt.isPresent() ? reservationOpt.get() : null);
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, "reservation id is required for delete");
        reservationRepository.deleteById(id);
    }


}
