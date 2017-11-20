package com.campsite.business;

import com.campsite.model.Reservation;

import java.time.LocalDate;

public class ReservationUtil {
    /** Utility used to thoroughly test scenario's critical to reservation analysis. **/
    public static boolean hasConflict(LocalDate start, LocalDate end, Reservation r) {
        return (!end.isBefore(r.getStartDate()) && !start.isAfter(r.getEndDate())) ?
                true : false;
    }
}
