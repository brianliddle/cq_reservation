package com.campsite.service;

import com.campsite.business.ReservationHashKey;
import com.campsite.business.RuleEngine;
import com.campsite.business.impl.ReservationRule;
import com.campsite.dao.CampsiteDAO;
import com.campsite.dao.ReservationDAO;
import com.campsite.error.ReservationConflictException;
import com.campsite.model.Campsite;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.campsite.business.ReservationHashKey.*;

/**
 * Service layer for orchestrating Reservation logic.
 * It is intended that all outfacing API's interface exclusively with this Service to
 * access the Reservation application.   (Webservice, REST API, MVC UI, etc...)
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

    /**
     * Returns a list of campsites that are available to be reserved.
     * Validates against reservations that are in conflict as well as reservations that don't
     * meet the minimum gap requirements between existing reservations.
     *
     * @param rule       - Defines how many days (a gap) is invalid between reservations.
     * @param startDate  - Start date of the requested reservation.
     * @param endDate    - End date of the requested reservation.
     * @return
     */
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

    /**
     * Creates a new reservation if the reservation.id is 0, or updates if it's defined.
     * Throws ReservationConflictException if an existing reservation conflicts via date overlaps
     * or if it doesn't meet the minimum gap requirements between reservations.
     * @param reservation  - the reservation to create / update.
     * @param rule         - Gaprule that defines minimum gap requirements between reservations.
     * @return the id of the reservation
     * @throws ReservationConflictException
     */
    public int createUpdateReservation(Reservation reservation, Gaprule rule) throws ReservationConflictException {
        /** STUB for later development **/
        return -1;
    }

    /**
     * Deletes an existing reservation.
     * @param reservationId
     */
    public void deleteReservation(int reservationId) {
        /** STUB for later development **/
    }

}
