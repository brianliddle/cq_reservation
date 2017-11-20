package com.campsite.service;

import com.campsite.ReservationBaseTest;
import com.campsite.model.Campsite;
import com.campsite.model.Gaprule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class ReservationServiceTest extends ReservationBaseTest {
    private static final Logger log = LogManager.getLogger(ReservationServiceTest.class);

    @Test
    public void getAvailableCampsites_gap2() {
        log.info("TEST - getAvailableCampsites_gap2");

        //TODO: make the test generic so that we're not coupled to gap data
        List<Campsite> campResults =
                rezSvc.getAvailableCampsites(gapRules.get(0), startDate, endDate);

        Assert.assertNotNull(campResults);
        for (Campsite c : campResults) {
            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() == 3 || c.getId() == 6 || c.getId() == 8);

            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() !=1 && c.getId() !=2 && c.getId() != 4 && c.getId() != 5 && c.getId() != 7 && c.getId() != 9);
        }
        Assert.assertTrue(campResults.size() == 3);
    }
    @Test
    public void getAvailableCampsites_gap3() {
        log.info("TEST - getAvailableCampsites_gap3");
        //TODO: make the test generic so that we're not coupled to gap data
        List<Campsite> campResults =
                rezSvc.getAvailableCampsites(gapRules.get(1), startDate, endDate);

        Assert.assertNotNull(campResults);
        campResults = rezSvc.getAvailableCampsites(gapRules.get(1), startDate, endDate);
        Assert.assertNotNull(campResults);
        for (Campsite c : campResults) {
            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() == 6 || c.getId() == 8);

            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() != 1 && c.getId() != 2 && c.getId() != 3 && c.getId() != 4 && c.getId() != 5 && c.getId() != 7 && c.getId() != 9);
        }
        Assert.assertTrue(campResults.size() == 2);
    }

}
