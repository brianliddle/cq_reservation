package com.campsite.dao.impl;

import com.campsite.business.ReservationHashKey;
import com.campsite.dao.ReservationDAO;
import com.campsite.model.Reservation;

import java.time.LocalDate;
import java.util.*;

import static com.campsite.business.ReservationUtil.hasConflict;

/**
 * ReservationDAO implementation used EXCLUSIVELY for running test cases.
 * All other cases must use a repository.
 */
public class ReservationTestCache implements ReservationDAO {

    private Map<Integer,ArrayList<Reservation>> reservations =
            new HashMap<Integer, ArrayList<Reservation>>();

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
    public Map<ReservationHashKey, Reservation> getNextPriorReservation(LocalDate startDate, LocalDate endDate, int campsiteId)
    {
        Reservation prior = null;
        Reservation next = null;
        Map<ReservationHashKey, Reservation> returnRes =
                new EnumMap<ReservationHashKey, Reservation>(ReservationHashKey.class);

        for (Reservation r : reservations.get(campsiteId)) {
            //Check if start:end dates overlap with existing reservation
            if (hasConflict(startDate, endDate, r)) {
                returnRes.put(ReservationHashKey.CONFLICT, r);
                return returnRes;
            }

            //find the reservation closest to startDate in the future
            if (! r.getEndDate().isAfter(startDate) &&
                    (prior == null || r.getEndDate().isAfter(prior.getEndDate())) )
            { prior = r; }

            //find the reservation closest to endDate date in the past
            if (! r.getStartDate().isBefore(endDate) &&
                    (next == null || r.getStartDate().isBefore(next.getStartDate())) )
            { next = r; }

        }
        returnRes.put(ReservationHashKey.NEXT, next);
        returnRes.put(ReservationHashKey.PRIOR, prior);
        return returnRes;
    }

    /**
     * Adds reservations in bulk.
     *
     * TODO: Add reservation gap and conflict validation
     * TODO: leverage Colleciton.sort to assist with perfomance.
     *
     * NOTE: Currently this is used exclusively for running unit tests.
     *
     * @param resList - Reservations to add.
     */
    public void addReservations(List<Reservation> resList) {
        for (Reservation r : resList) {
            if (reservations.containsKey(r.getCampsiteId())) {
                reservations.get(r.getCampsiteId()).add(r);
            } else {
                ArrayList<Reservation> newList = new ArrayList<Reservation>();
                newList.add(r);
                reservations.put(r.getCampsiteId(), newList);
            }
        }
    }

    /**
     * Clears all reservations.
     * NOTE: Currently this is used exclusively for running unit tests.
     */
    public void clearReservations() {
        reservations.clear();
    }

    /**
     * Retrieve all reservations between a start and enddate.
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Reservation> getReservations(LocalDate startDate, LocalDate endDate) {
        /** STUB for later development **/
        return null;
    }

    /**
     * Retrieve all reservations between a start and enddate for a particular campsite.
     * @param startDate
     * @param endDate
     * @param campsiteId
     * @return
     */
    public List<Reservation> getReservations(LocalDate startDate, LocalDate endDate, int campsiteId) {
        /** STUB for later development **/
        return null;
    }

    /**
     * Creates a reservation.
     * @param r
     * @return
     */
    public int createReservation(Reservation r) {
        /** STUB for later development **/
        return -1;
    }

    /**
     * Updates an existing reservation.
     * @param r
     */
    public void updateReservation(Reservation r) {
        /** STUB for later development **/
    }

}
