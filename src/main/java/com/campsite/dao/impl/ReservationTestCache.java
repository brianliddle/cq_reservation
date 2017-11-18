package com.campsite.dao.impl;

import com.campsite.dao.ReservationDao;
import com.campsite.model.Campsite;
import com.campsite.model.Reservation;

import java.util.Date;
import java.util.List;

/** THIS CLASS USED EXCLUSIVELY FOR TEST CASES, all other cases should use a repository **/

public class ReservationTestCache implements ReservationDao{

    public List<Reservation> getReservations(Date startDate, Date endDate) {
        /** TODO retrieve json by config file **/
        /** TODO marshal cs_reservations pojo's from json **/
        return null;
    }

    public void addReservations(List<Reservation> reservations) {

    }

    public void clearReservations() {

    }

    public void addCampsites(List<Campsite> campsites) {

    }

    public void clearCampsites() {
        
    }
}
