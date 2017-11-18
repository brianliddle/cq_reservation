package com.campsite.business.impl;

import com.campsite.ReservationBaseTest;
import com.campsite.model.Gaprule;
import com.campsite.model.Reservation;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ReservationRuleTest extends ReservationBaseTest {

    @Test
    //FYI - this is where spock does a great job of simplifying data driven unit testing...
    public void happyPath() {
        ReservationRule ruleEngine = new ReservationRule();

        LocalDate startDate = LocalDate.of(2016, 6, 1);
        LocalDate endDate = LocalDate.of(2016,6,4);

        Reservation beforeRes =
                new Reservation(0,1,LocalDate.of(2016,5,20),LocalDate.of(2016,5,25));

        Reservation afterRes =
                new Reservation(1,1,LocalDate.of(2016,6,7),LocalDate.of(2016,6,9));

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

//    @Test
//    public void gap3FailsAfter() {
//
//    }
//
//    @Test
//    public void gap3FailsBefore() {
//
//    }
//
//    @Test
//    public void gap2FailsAfter() {
//
//    }
//
//    @Test
//    public void gap2FailsBefore() {
//
//    }
//
//    @Test
//    public void gap1FailsAfter() {
//
//    }
//
//    @Test
//    public void gap1FailsBefore() {
//
//    }
}
