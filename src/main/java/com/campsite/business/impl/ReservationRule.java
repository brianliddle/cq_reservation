package com.campsite.business.impl;

import com.campsite.business.RuleEngine;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReservationRule implements RuleEngine {
    private static final Logger log = LogManager.getLogger(RuleEngine.class);

    public boolean isValid(LocalDate startDate, LocalDate endDate, Reservation before, Reservation after, Gaprule rule) {
        if (startDate == null || endDate == null ||
            before == null || after == null ||
            before.getEndDate() == null ||
            after.getStartDate() == null)
        {
            log.error("Undefined dates passed to Reservation gap validation!");
            logDebug(startDate, endDate, before, after, rule);
            return false;
        }

        if (DAYS.between(startDate,endDate) < 0) {
            log.error("Reservation start and end dates are out of sequence. ");
            logDebug(startDate, endDate, before, after, rule);
            return false;
        }

        long beforeGap = DAYS.between(before.getEndDate(), startDate);
        long afterGap = DAYS.between(endDate, after.getEndDate());

        if (beforeGap < 0 || afterGap < 0) {
            log.error("Dates passed to Gap Rule validator are out of sequence!");
            logDebug(startDate, endDate, before, after, rule);
            return false;
        }

        //gap test
        if (beforeGap > rule.getGapSize() && afterGap > rule.getGapSize()) {
            return true;
        }
        return false;
    }

    /** logging utility pulled out for reuse and readability. **/
    private void logDebug(LocalDate startDate, LocalDate endDate, Reservation before, Reservation after, Gaprule rule) {
        log.debug("\nrez.start: " + startDate + "rez.end: " + endDate +
                "\nBefore.endDate: " + before.getEndDate() +
                "\nAfter.startDate: " + after.getStartDate() );
        log.debug("Days between rez.start and before.endDate:: " + DAYS.between(before.getEndDate(), startDate));
        log.debug("Days between after.startDate and rez.endDate:: " + DAYS.between(endDate, after.getEndDate()));
        log.debug("GAP: " + rule.getGapSize());
    }
}
