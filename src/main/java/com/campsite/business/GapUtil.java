package com.campsite.business;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
/**
 * NOTE, this class is simply a placeholder to thoroughly test GAP logic, as well as
 * to have a discussion about the definition of gaps between reservations.
 * Please see comments in GapUtilTest for further details / discussion.
 *
 * NOTE, this could also be converted to a Lambda to allow for more dynamic gap analysis scenarios.
 * **/
public class GapUtil {
    public static final long gapBetween(LocalDate before, LocalDate after) {
        return DAYS.between(before, after) - 1;
    }
}
