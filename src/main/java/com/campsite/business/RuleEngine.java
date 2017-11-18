package com.campsite.business;

import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;

/** TODO: consider Functional Interface for lambda impl  **/
public interface RuleEngine {

    boolean isValid(Reservation target, Reservation before, Reservation after, Gaprule rule);

}
