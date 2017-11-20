package com.campsite.business;

import com.campsite.model.Reservation;

import java.time.LocalDate;

public class ReservationUtil {
    public static boolean hasConflict(LocalDate start, LocalDate end, Reservation r) {

        if (!end.isBefore(r.getStartDate()) && !start.isAfter(r.getEndDate())) {
            return true;
        }
        return false;
    }
}
