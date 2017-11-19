package com.campsite.service;

import com.campsite.business.RuleEngine;
import com.campsite.business.impl.ReservationRule;
import com.campsite.dao.CampsiteDAO;
import com.campsite.dao.ReservationDAO;
import com.campsite.model.Campsite;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** NOTE, keeping as a concrete class until multiple usecases involving a generic contract is identified **/

public class ReservationService {
    private static final Logger log = LogManager.getLogger(ReservationService.class);

    /** TODO Dependency Inject **/
    private ReservationDAO resDao;
    public void setResDao(ReservationDAO resDao) {
        this.resDao = resDao;
    }

    /** TODO Dependency Inject **/
    private CampsiteDAO campDao;
    public void setCampDao(CampsiteDAO campDao) { this.campDao = campDao; }

    /** TODO Dependency Inject **/
    private RuleEngine ruleEngine = new ReservationRule();

    public ReservationService() {}

    public List<Campsite> getAvailableCampsites(Gaprule rule, LocalDate startDate, LocalDate endDate) {
        List<Campsite> availCampsites = new ArrayList<Campsite>();

        for (Campsite camp : campDao.getCampsites()) {
            if (ruleEngine.isValid(startDate, endDate,
                    resDao.getPriorReservation(startDate, camp.getId()),
                    resDao.getNextReservation(endDate, camp.getId()),
                    rule))
            {
                availCampsites.add(camp);
            }
        }
        return availCampsites;
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
