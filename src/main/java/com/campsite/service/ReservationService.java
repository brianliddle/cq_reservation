package com.campsite.service;

import com.campsite.business.RuleEngine;
import com.campsite.business.impl.ReservationRule;
import com.campsite.dao.ReservationDao;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** NOTE, keeping as a concrete class until multiple usecases involving a generic contract is identified **/

public class ReservationService {
    /** TODO Dependency inject **/
    private ReservationDao resDao;
    public void setResDao(ReservationDao resDao) {
        this.resDao = resDao;
    }

    /** TODO Dependency inject **/
    private RuleEngine ruleEngine = new ReservationRule();

    public ReservationService() {}

    public List<Reservation> getAvailableReservations(Gaprule rule, Date startDate, Date endDate, int campsiteId) {
        List<Reservation> availReservations = new ArrayList<Reservation>();
        List<Reservation> currentReservations = resDao.getReservations(startDate, endDate);
        for (Reservation r : currentReservations) {
            //TODO:
        }
        return availReservations;
    }


    /** TODO Future considerations **/
//    public int makeReservation(Reservation reservation) {
//        return -1;
//    }
//
//    public void deleteReservation(int reservationId) {}
//
//    public void updateReservation(int reservationId, Reservation reservation) {}

}
