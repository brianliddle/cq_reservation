package com.campsite.dao.impl;

import com.campsite.business.ReservationHashKey;
import com.campsite.dao.ReservationDAO;
import com.campsite.model.Reservation;

import java.time.LocalDate;
import java.util.*;

import static com.campsite.business.ReservationUtil.hasConflict;

/** THIS CLASS USED EXCLUSIVELY FOR TEST CASES, all other cases should use a repository **/

public class ReservationTestCache implements ReservationDAO {

    //TODO: Convert to a HashMap<campId,List<Reservation>>
    private Map<Integer,ArrayList<Reservation>> reservations = new HashMap<Integer, ArrayList<Reservation>>();

    public List<Reservation> getReservations(LocalDate startDate, LocalDate endDate) {
        //TODO:
        return null;
    }

    public List<Reservation> getReservations(LocalDate startDate, LocalDate endDate, int campsiteId) {
        //TODO:
        return null;
    }

    public Map<ReservationHashKey, Reservation> getNextPriorReservation(LocalDate startDate, LocalDate endDate, int campsiteId)
    {
        Reservation prior = null;
        Reservation next = null;
        Map<ReservationHashKey, Reservation> returnRes =
                new EnumMap<ReservationHashKey, Reservation>(ReservationHashKey.class);

        for (Reservation r : reservations.get(campsiteId)) {
            //start:end dates overlap with existing reservation
            if (hasConflict(startDate, endDate, r)) {
                returnRes.put(ReservationHashKey.CONFLICT, null);
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

    /** NOTE, circumvents RuleEngine **/
    //TODO leverage Collections.sort AFTER make reservations comparable!!!
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

    public void clearReservations() {
        reservations.clear();
    }
}
