package com.campsite.dao.impl;

import com.campsite.dao.ReservationDAO;
import com.campsite.model.Campsite;
import com.campsite.model.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** THIS CLASS USED EXCLUSIVELY FOR TEST CASES, all other cases should use a repository **/

public class ReservationTestCache implements ReservationDAO {

    List<Reservation> reservations = new ArrayList<Reservation>();

    List<Campsite> campsites = new ArrayList<Campsite>();

    public List<Reservation> getReservations(LocalDate startDate, LocalDate endDate) {
        //TODO:
        return null;
    }

    public List<Reservation> getReservations(LocalDate startDate, LocalDate endDate, int campsiteId) {
        //TODO:
        return null;
    }

    public Reservation getPriorReservation(LocalDate targetDate, int campsiteId) {
        //TODO:
        return null;
    }

    public Reservation getNextReservation(LocalDate targetDate, int campsiteId) {
        //TODO:
        return null;
    }

    /** NOTE, circumvents RuleEngine **/
    public void addReservations(List<Reservation> rez) {
        reservations.addAll(rez);
    }

    public void clearReservations() {
        reservations.clear();
    }

    /** NOTE, circumvents RuleEngine **/
    public void addCampsites(List<Campsite> camps) {
        campsites.addAll(camps);
    }

    public void clearCampsites() {
        campsites.clear();
    }
}
