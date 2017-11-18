package com.campsite.business;

import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;

import java.time.LocalDate;

/** TODO: consider Functional Interface for lambda impl  **/
public interface RuleEngine {

    boolean isValid(LocalDate startDate, LocalDate endDate, Reservation before, Reservation after, Gaprule rule);

}
