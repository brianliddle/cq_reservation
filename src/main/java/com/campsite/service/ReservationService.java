package com.campsite.service;

import com.campsite.business.RuleEngine;
import com.campsite.business.impl.ReservationRule;
import com.campsite.dao.ReservationDao;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;

import java.util.Date;
import java.util.List;

public class ReservationService {
    /** TODO Dependency inject **/
    private ReservationDao resDao;
    public void setResDao(ReservationDao resDao) {
        this.resDao = resDao;
    }

    /** TODO Dependency inject **/
    private RuleEngine ruleEngine = new ReservationRule();

    public ReservationService() {}

    public int makeReservation(Reservation reservation) {
        return -1;
    }

    public List<Reservation> getAvailableReservations(Gaprule rule, Date startDate, Date endDate, int campsiteId) {
        return null;
    }


}
