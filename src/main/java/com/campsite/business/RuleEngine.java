package com.campsite.business;

import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;

import java.time.LocalDate;

/**
 * RuleEngine used for Reservation validation.
 */
public interface RuleEngine {

    /**
     * Validates if a requested reservation conflicts with existing reservations scenarios:
     *  - Date overlap of existing reservations with requested reservation
     *  - Is there a minimum gap between existing reservations and requested reservation
     *
     * @param startDate - Requested reservation start date.
     * @param endDate   - Requested reservation end date.
     * @param before    - Existing reservation that preceeds the requested reservation.
     * @param after     - Existing reservation that follows the requested reservation.
     * @param rule      - Gaprule defining the max number of days between reservations that's invalid.
     * @return
     */
    boolean isValid(LocalDate startDate, LocalDate endDate, Reservation before, Reservation after, Gaprule rule);

}
