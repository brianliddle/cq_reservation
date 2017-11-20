package com.campsite.business.impl;

import com.campsite.business.RuleEngine;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.campsite.business.GapUtil.gapBetween;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReservationRule implements RuleEngine {
    private static final Logger log = LogManager.getLogger(RuleEngine.class);

    public boolean isValid(LocalDate startDate, LocalDate endDate, Reservation before, Reservation after, Gaprule rule) {
        //TODO: consider throwing an exception to notify the caller of bad data.
        if (startDate == null || endDate == null)
        {
            log.error("Undefined dates passed to Reservation gap validation!");
            logDebug(startDate, endDate, before, after, rule);
            return false;
        }

        //TODO: consider throwing an exception to notify the request of bad data.
        if (DAYS.between(startDate,endDate) < 0) {
            log.error("Reservation start and end dates are out of sequence. ");
            logDebug(startDate, endDate, before, after, rule);
            return false;
        }

        //If before or after reservations are null,
        // than nothing exists prior a before reservation or following an after reservation.
        LocalDate beforeEndDate = (before == null) ? LocalDate.MIN : before.getEndDate();
        LocalDate afterStartDate = (after == null) ? LocalDate.MAX : after.getStartDate();

        long beforeGap = gapBetween(beforeEndDate, startDate);
        long afterGap = gapBetween(endDate, afterStartDate);

        //TODO: indicates a reservation exists for this date.  consider notifying of existing reservation.
        if (beforeGap < 0 || afterGap < 0) {
            log.error("Dates passed to Gap Rule validator are out of sequence!");
            logDebug(startDate, endDate, before, after, rule);
            return false;
        }

        //gap test)
        if ((beforeGap == 0 || beforeGap > rule.getGapSize()) &&
            (afterGap  == 0 || afterGap  > rule.getGapSize()) )
        {
            return true;
        }
        return false;
    }

    /** logging utility pulled out for reuse and readability. **/
    private void logDebug(LocalDate startDate, LocalDate endDate, Reservation before, Reservation after, Gaprule rule) {

        String strEndDate = (endDate == null) ? "undefined" : endDate.toString();
        String strStartDate = (startDate == null) ? "undefined" : startDate.toString();
        String strBeforeStart = (before == null || before.getStartDate() == null) ? "undefined" : before.getStartDate().toString();
        String strBeforeEnd = (before == null || before.getEndDate() == null) ? "undefined" : before.getEndDate().toString();
        String strAfterStart = (after == null || after.getStartDate() == null) ? "undefined" : after.getStartDate().toString();
        String strAfterEnd = (after == null || after.getEndDate() == null) ? "undefined" : after.getEndDate().toString();

        String debugMsg =
                "\n\tTarget reservation - " + strStartDate + " :: " + strEndDate +
                "\n\tBefore reservation - " + strBeforeStart + " :: " + strBeforeEnd +
                "\n\tAfter reservation  - " + strAfterStart + " :: " + strAfterEnd +
                "\n\tGAP: " + rule.getGapSize();

        if (startDate != null && endDate != null &&
                before != null && after != null &&
                before.getEndDate() != null &&
                after.getStartDate() != null)
        {
            debugMsg +=
                    "\n\tDays between target.start and before.endDate:: " + DAYS.between(before.getEndDate(), startDate) +
                    "\n\tDays between after.startDate and target.endDate:: " + DAYS.between(endDate, after.getEndDate());
        }
        debugMsg += "\n";
        log.debug(debugMsg);
    }
}
