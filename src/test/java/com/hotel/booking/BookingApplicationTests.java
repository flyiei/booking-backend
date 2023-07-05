package com.hotel.booking;

import com.hotel.booking.reservation.exception.ReservationDeleteException;
import com.hotel.booking.reservation.exception.ReservationSaveAndUpdateException;
import com.hotel.booking.reservation.model.ReservationData;
import com.hotel.booking.reservation.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@SpringBootTest
@ActiveProfiles("test")// using application-test.properties
// Spring context should be dirtied and reloaded before each test method.
// This ensures a clean state for each test.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookingApplicationTests {

    @Autowired
    private ReservationServiceImpl reservationService;

    @Test
    void testFindReservationsByPaging() {
        Assertions.assertEquals(0,
                reservationService.findAll().size(),
                "Table should be empty when testing start");
        int totalItems = 10, pageSize = 4;
        for (int i = 1; i <= totalItems; i++) {
            ReservationData reservationData = ReservationData.builder()
                    .agency("agency" + i)
                    .consumer("consumer" + i)
                    .hotel("hotel" + i)
                    .build();
            reservationService.save(reservationData);
        }
        List<ReservationData> reservationPage1 = reservationService.findAllByPaging(0, pageSize);
        Assertions.assertEquals(pageSize, reservationPage1.size());
        for (int i = 0; i < pageSize; i++) {
            Assertions.assertEquals(i + 1, reservationPage1.get(i).getId().longValue());
        }
        List<ReservationData> reservationPage2 = reservationService.findAllByPaging(1, pageSize);
        Assertions.assertEquals(pageSize, reservationPage2.size());
        for (int i = 0; i < pageSize; i++) {
            Assertions.assertEquals(i + 1 + pageSize, reservationPage2.get(i).getId().longValue());
        }
        List<ReservationData> reservationPage3 = reservationService.findAllByPaging(2, pageSize);
        Assertions.assertEquals(2, reservationPage3.size());
    }

    @Test
    public void testUpdateReservationWithID() {
        Assertions.assertEquals(0,
                reservationService.findAll().size(),
                "Table should be empty when testing start");
        ReservationData reservationData = ReservationData.builder()
                .agency("agency1")
                .consumer("consumer1")
                .hotel("hotel1")
                .build();
        ReservationData newReservationData = reservationService.save(reservationData);
        Long rightID = newReservationData.getId();
        Long wrongID = 99L;
        newReservationData.setId(wrongID);
        try {
            reservationService.update(wrongID, newReservationData);
        } catch (ReservationSaveAndUpdateException ex) {
            Assertions.assertNotNull(ex);
        }
        newReservationData.setId(rightID);
        String newAgency = "newAgency";
        newReservationData.setAgency(newAgency);
        newReservationData = reservationService.update(rightID, newReservationData);
        Assertions.assertEquals(newAgency, newReservationData.getAgency());
    }


    @Test
    public void testDeleteReservationWithID() {
        Assertions.assertEquals(0,
                reservationService.findAll().size(),
                "Table should be empty when testing start");
        ReservationData reservationData = ReservationData.builder()
                .agency("agency1")
                .consumer("consumer1")
                .hotel("hotel1")
                .build();
        ReservationData newReservationData = reservationService.save(reservationData);
        Long rightID = newReservationData.getId();
        Long wrongID = 99L;
        try {
            reservationService.delete(wrongID);
        } catch (ReservationDeleteException ex) {
            Assertions.assertNotNull(ex);
        }
        Assertions.assertNotNull(reservationService.findByID(rightID));
        reservationService.delete(rightID);
        Assertions.assertNull(reservationService.findByID(rightID));

    }


}
