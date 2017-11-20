package com.campsite.dao.impl;

import com.campsite.business.ReservationConflictException;
import com.campsite.dao.ReservationDAO;
import com.campsite.model.Campsite;
import com.campsite.model.Reservation;
import static com.campsite.business.ReservationUtil.hasConflict;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

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

    public Reservation getPriorReservation(LocalDate startDate, LocalDate endDate, int campsiteId)
        throws ReservationConflictException
    {
        Reservation prior = null;

        for (Reservation r : reservations.get(campsiteId)) {
            //if targetDate is in between a reservation, immediately return this reservation.
            //if (targetDate.isBefore(r.getEndDate()) && targetDate.isAfter(r.getStartDate())) {
            if (hasConflict(startDate, endDate, r)) {
                throw new ReservationConflictException();
            }

            //find the reservation closest to the target date in the future
            if (! r.getEndDate().isAfter(startDate) &&
                (prior == null ||
                 r.getEndDate().isAfter(prior.getEndDate())
                ) )
            { prior = r; }

        }
        return prior;
    }

    public Reservation getNextReservation(LocalDate startDate, LocalDate endDate, int campsiteId)
            throws ReservationConflictException
    {
        Reservation next = null;

        for (Reservation r : reservations.get(campsiteId)) {
            //if targetDate is in between a reservation, immediately return this reservation.
            //if (targetDate.isBefore(r.getEndDate()) && targetDate.isAfter(r.getStartDate())) {
            if (hasConflict(startDate, endDate, r)) {
                throw new ReservationConflictException();
            }

            //find the reservation closest to the target date in the past
            if (! r.getStartDate().isBefore(endDate) &&
                    (next == null || r.getStartDate().isBefore(next.getStartDate())) )
            { next = r; }
        }
        return next;
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
