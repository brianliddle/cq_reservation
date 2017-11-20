package com.campsite.dao;

import com.campsite.business.ReservationConflictException;
import com.campsite.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
    List<Reservation> getReservations(LocalDate startDate, LocalDate endDate, int campsiteId);

    Reservation getPriorReservation(LocalDate startDate, LocalDate endDate, int campsiteId) throws ReservationConflictException;

    Reservation getNextReservation(LocalDate startDate, LocalDate endDate, int campsiteId) throws ReservationConflictException;


/** Consider for later development **/

//    Reservation getReservation(int reservationId);
//    void createReservation(Reservation r);
//    vod updateReservation(int reservationId, Reservation r);
//    void deleteReservation(int reservationId);

//   List<Reservation> getReservations(int resortId);
//   List<Reservation> getReservations(List<int> resorts, startDate, endDate);
}
