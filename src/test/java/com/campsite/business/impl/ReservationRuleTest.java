package com.campsite.business.impl;

import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ReservationRuleTest  {
    private static final Logger log = LogManager.getLogger(ReservationRuleTest.class);

    @Test
    //FYI - this is where spock does a great job of simplifying data driven unit testing.
    public void reservationAccepted() {
        ReservationRule ruleEngine = new ReservationRule();

        LocalDate startDate = LocalDate.of(2016,6,1);
        LocalDate endDate = LocalDate.of(2016,6,4);

        Reservation beforeRes =
                new Reservation(0,1,LocalDate.of(2016,5,20),LocalDate.of(2016,5,25));

        Reservation afterRes =
                new Reservation(1,1,LocalDate.of(2016,6,9),LocalDate.of(2016,6,11));

        //gap of 1
        Gaprule gap = new Gaprule(0,1);
        Assert.assertTrue("Valid reservation search, gap of 1 failed.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //gap of 2
        gap = new Gaprule(1,2);
        Assert.assertTrue("Valid reservation search, gap of 2 failed.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //gap of 3
        gap = new Gaprule(2,3);
        Assert.assertTrue("Valid reservation search, gap of 3 failed.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));
    }

    @Test
    //FYI - this is where spock does a great job of simplifying data driven unit testing.
    public void reservationAcceptedGap1_RejectedGap2Gap3() {
        ReservationRule ruleEngine = new ReservationRule();

        LocalDate startDate = LocalDate.of(2016,6,1);
        LocalDate endDate = LocalDate.of(2016,6,4);

        Reservation beforeRes =
                new Reservation(0,1,LocalDate.of(2016,5,20),LocalDate.of(2016,5,25));

        Reservation afterRes =
                new Reservation(1,1,LocalDate.of(2016,6,7),LocalDate.of(2016,6,10));

        //gap of 1
        Gaprule gap = new Gaprule(0,1);
        Assert.assertTrue("Valid reservation search, gap of 1 failed.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //gap of 2
        gap = new Gaprule(1,2);
        Assert.assertFalse("Should have rejected reservation with gaprule of 2.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //gap of 3
        gap = new Gaprule(2,3);
        Assert.assertFalse("Should have rejected reservation with gaprule of 3.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));
    }

    @Test
    public void reservationRejected_gapsExist() {
        System.out.println();
        log.info("TEST - reservationRejected_gapsExist");

        ReservationRule ruleEngine = new ReservationRule();

        LocalDate startDate = LocalDate.of(2016, 6, 2);
        LocalDate endDate = LocalDate.of(2016,6,3);

        Reservation beforeRes =
                new Reservation(0,1,LocalDate.of(2016,5,25),LocalDate.of(2016,5,31));

        Reservation afterRes =
                new Reservation(1,1,LocalDate.of(2016,6,5),LocalDate.of(2016,6,9));

        //gap of 1
        Gaprule gap = new Gaprule(0,1);
        Assert.assertFalse("Reservation gap of 1 should have been rejected.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //gap of 2
        gap = new Gaprule(1,2);
        Assert.assertFalse("Reservation gap of 2 should have been rejected.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //gap of 3
        gap = new Gaprule(2,3);
        Assert.assertFalse("Reservation gap of 3 should have been rejected.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));
    }

    @Test
    public void reservationRejected_ReservationDatesOutOfOrder() {
        System.out.println();
        log.info("TEST - reservationRejected_ReservationDatesOutOfOrder");
        ReservationRule ruleEngine = new ReservationRule();

        LocalDate endDate = LocalDate.of(2016, 6, 1);
        LocalDate startDate = LocalDate.of(2016,6,4);

        Reservation beforeRes =
                new Reservation(0,1,LocalDate.of(2016,5,20),LocalDate.of(2016,5,25));

        Reservation afterRes =
                new Reservation(1,1,LocalDate.of(2016,6,7),LocalDate.of(2016,6,9));

        Gaprule gap = new Gaprule(0,1);
        Assert.assertFalse("Start and End reservation dates in reverse order should have been rejected.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));
    }

    @Test
    public void reservationRejected_BeforeResAndAfterRes_OutOfOrder() {
        System.out.println();
        log.info("TEST - reservationRejected_BeforeResAndAfterRes_OutOfOrder");

        ReservationRule ruleEngine = new ReservationRule();

        LocalDate startDate = LocalDate.of(2016, 6, 1);
        LocalDate endDate = LocalDate.of(2016,6,4);

        Reservation afterRes =
                new Reservation(0,1,LocalDate.of(2016,5,20),LocalDate.of(2016,5,25));

        Reservation beforeRes =
                new Reservation(1,1,LocalDate.of(2016,6,7),LocalDate.of(2016,6,9));

        Gaprule gap = new Gaprule(0,1);
        Assert.assertFalse("Before reservation and After reservation in reverse order should have been rejected.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));
    }

    @Test
    public void reservationRejected_reservationConflict() {
        System.out.println();
        log.info("TEST - reservationRejected_reservationConflict");

        ReservationRule ruleEngine = new ReservationRule();

        Gaprule gap = new Gaprule(0,1);

        Reservation beforeRes =
                new Reservation(0,1,LocalDate.of(2016,6,2),LocalDate.of(2016,6,6));

        Reservation afterRes =
                new Reservation(1,1,LocalDate.of(2016,6,20),LocalDate.of(2016,6,23));

        //reservation exists completely between start-end dates
        LocalDate startDate = LocalDate.of(2016,6,1);
        LocalDate endDate = LocalDate.of(2016,6,6);
        Assert.assertFalse("Reservation should be rejected when a reservation conflict exists.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //reservation starts before, but ends between start-end dates.
        startDate = LocalDate.of(2016,6,3);
        endDate = LocalDate.of(2016,6,9);
        Assert.assertFalse("Reservation should be rejected when a reservation conflict exists.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //reservation starts before, but ends on start-date.
        startDate = LocalDate.of(2016,6,6);
        endDate = LocalDate.of(2016,6,11);
        Assert.assertFalse("Reservation should be rejected when a reservation conflict exists.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //reservation starts between start-end dates, but ends after.
        startDate = LocalDate.of(2016,6,1);
        endDate = LocalDate.of(2016,6,4);
        Assert.assertFalse("Reservation should be rejected when a reservation conflict exists.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));

        //reservation starts on end-date, but ends after.
        startDate = LocalDate.of(2016,6,10);
        endDate = LocalDate.of(2016,6,23);
        Assert.assertFalse("Reservation should be rejected when a reservation conflict exists.",
                ruleEngine.isValid(startDate, endDate, beforeRes, afterRes, gap));
    }
}
