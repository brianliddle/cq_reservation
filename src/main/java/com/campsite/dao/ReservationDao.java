package com.campsite.dao;

import com.campsite.business.ReservationHashKey;
import com.campsite.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReservationDAO {
    List<Reservation> getReservations(LocalDate startDate, LocalDate endDate, int campsiteId);
    Map<ReservationHashKey, Reservation> getNextPriorReservation(LocalDate startDate, LocalDate endDate, int campsiteId);

/** Consider for later development **/

//    Reservation getReservation(int reservationId);
//    void createReservation(Reservation r);
//    vod updateReservation(int reservationId, Reservation r);
//    void deleteReservation(int reservationId);

//   List<Reservation> getReservations(int resortId);
//   List<Reservation> getReservations(List<int> resorts, startDate, endDate);
}
