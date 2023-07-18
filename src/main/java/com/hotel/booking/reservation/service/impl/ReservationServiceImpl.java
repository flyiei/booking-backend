package com.hotel.booking.reservation.service.impl;

import com.hotel.booking.reservation.exception.ReservationDeleteException;
import com.hotel.booking.reservation.exception.ReservationSaveAndUpdateException;
import com.hotel.booking.reservation.model.ReservationData;
import com.hotel.booking.reservation.repository.ReservationRepository;
import com.hotel.booking.reservation.repository.dto.Reservation;
import com.hotel.booking.reservation.service.ReservationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public ReservationData update(Long reservationID, ReservationData reservationData) {
        Assert.notNull(reservationID, "reservationID can not be null for update");
        Assert.notNull(reservationData, "reservationData can not be null for update");
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationID);
        Reservation reservation = reservationOpt.isPresent() ? reservationOpt.get() : null;
        if (reservation == null) {

            throw new ReservationSaveAndUpdateException(reservationID, "ID does not exist!");
        }
        reservation = ReservationData.buildReservation(reservationData);
        reservation.setId(reservationID);
        return ReservationData.buildReservationData(reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ReservationData save(ReservationData reservationData) {

        Assert.notNull(reservationData, "reservationData can not be null for save");
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
    public List<ReservationData> findAll() {

        return reservationRepository.findAll().stream()
                .map(ReservationData::buildReservationData)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationData findByID(Long reservationID) {
        Assert.notNull(reservationID, "reservation id is required for query");

        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationID);
        return ReservationData.buildReservationData(reservationOpt.isPresent() ? reservationOpt.get() : null);
    }

    @Override
    @Transactional
    public void delete(Long reservationID) {
        Assert.notNull(reservationID, "reservation id is required for delete");
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationID);
        if (reservationOpt.isPresent()) {
            reservationRepository.deleteById(reservationID);
        } else {
            throw new ReservationDeleteException(reservationID, "ID does not exist!");
        }

    }


}
