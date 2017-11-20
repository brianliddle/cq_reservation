package com.campsite.dao;

import com.campsite.business.ReservationHashKey;
import com.campsite.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReservationDAO {

    /**
     * Returns a map containing the reservation following and preceding a requested reservation
     * start and end date for a given campsite.
     * NOTE: If a conflict of dates is found, adds the CONFLICT reservation to the map.
     *
     * @param startDate
     * @param endDate
     * @param campsiteId
     * @return
     */
    Map<ReservationHashKey, Reservation> getNextPriorReservation(LocalDate startDate, LocalDate endDate, int campsiteId);

    /**
     * Retrieve all reservations between a start and enddate.
     * @param startDate
     * @param endDate
     * @return
     */
    List<Reservation> getReservations(LocalDate startDate, LocalDate endDate);

    /**
     * Retrieve all reservations between a start and enddate for a particular campsite.
     * @param startDate
     * @param endDate
     * @param campsiteId
     * @return
     */
    List<Reservation> getReservations(LocalDate startDate, LocalDate endDate, int campsiteId);

    /**
     * Creates a reservation.
     * @param r
     * @return
     */
    int createReservation(Reservation r);

    /**
     * Updates an existing reservation.
     * @param r
     */
    void updateReservation(Reservation r);
}
