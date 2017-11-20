package com.campsite.business;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
public class GapUtil {

    public static final long gapBetween(LocalDate before, LocalDate after) {
        return DAYS.between(before, after) - 1;
    }
}
