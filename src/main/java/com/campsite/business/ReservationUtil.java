package com.campsite.business;

import com.campsite.model.Reservation;

import java.time.LocalDate;

public class ReservationUtil {
    /**
     * Utility used to thoroughly test scenario's critical to reservation analysis.
     * NOTE, could be replaced with a Lambda for dynamic reservation conflict analysis.
     *
     * @param start - Start of a requested reservation
     * @param end   - End of a requested reservation
     * @param r     - Existing reservation
     * @return
     */
    public static boolean hasConflict(LocalDate start, LocalDate end, Reservation r) {
        return (!end.isBefore(r.getStartDate()) && !start.isAfter(r.getEndDate())) ?
                true : false;
    }
}
