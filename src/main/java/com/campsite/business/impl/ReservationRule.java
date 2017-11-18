package com.campsite.business.impl;

import com.campsite.business.RuleEngine;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;

import java.time.LocalDate;

public class ReservationRule implements RuleEngine {

    public boolean isValid(LocalDate startDate, LocalDate endDate, Reservation before, Reservation after, Gaprule rule) {
        return false;
    }
}
