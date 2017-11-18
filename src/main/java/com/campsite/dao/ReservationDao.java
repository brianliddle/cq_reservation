package com.campsite.dao;

import com.campsite.model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationDao {
    List<Reservation> getReservations(Date startDate, Date endDate);


/** Consider for later development **/

//    Reservation getReservation(int reservationId);
//    void createReservation(Reservation r);
//    vod updateReservation(int reservationId, Reservation r);
//    void deleteReservation(int reservationId);

//   List<Reservation> getReservations(int resortId);
//   List<Reservation> getReservations(List<int> resorts, startDate, endDate);
}
