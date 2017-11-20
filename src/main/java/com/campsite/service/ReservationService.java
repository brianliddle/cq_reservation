package com.campsite.service;

import com.campsite.business.ReservationHashKey;
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
import java.util.List;
import java.util.Map;

import static com.campsite.business.ReservationHashKey.*;

/**
 * Service layer for orchestrating Resercation logic.
 */

public class ReservationService {

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

            Map<ReservationHashKey, Reservation> nextPriorRes =
                    resDao.getNextPriorReservation(startDate, endDate, camp.getId());

            if (nextPriorRes.containsKey(CONFLICT)) {
                continue;
            }

            if (ruleEngine.isValid(startDate,endDate,nextPriorRes.get(PRIOR),nextPriorRes.get(NEXT), rule))
            {
                availCampsites.add(camp);
            }
        }
        return availCampsites;
    }

    public List<Reservation> getUpcomingReservations(int campsiteId) {
        return resDao.getReservations(LocalDate.now(), LocalDate.MAX, campsiteId);
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
