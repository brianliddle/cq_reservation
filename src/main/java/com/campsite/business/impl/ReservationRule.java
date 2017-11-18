package com.campsite.business.impl;

import com.campsite.business.RuleEngine;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;

public class ReservationRule implements RuleEngine {

    public boolean isValid(Reservation target, Reservation before, Reservation after, Gaprule rule) {
        return false;
    }
}
