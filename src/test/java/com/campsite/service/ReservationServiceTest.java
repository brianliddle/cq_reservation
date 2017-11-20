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

    @Test
    public void getAvailableCampsites() {
        for (Gaprule rule : gapRules) {
            List<Campsite> campResults =
                    rezSvc.getAvailableCampsites(rule, startDate, endDate);

            switch (rule.getGapSize()) {
                case 1: assertGap1(campResults);
                    break;
                case 2: assertGap2(campResults);
                    break;
                case 3: assertGap3(campResults);
                    break;

                default: Assert.assertTrue(
                        "Gaprule, " +rule.getGapSize()+ ", exists that hasn't been defined yet.",
                        true == false);
            }
        }
    }

    private void assertGap1(List<Campsite> campResults) {
        Assert.assertNotNull(campResults);
        for (Campsite c : campResults) {
            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() == 1 || c.getId() == 3 || c.getId() == 6 || c.getId() == 8);

            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() !=2 && c.getId() != 4 && c.getId() != 5 && c.getId() != 7 && c.getId() != 9);
        }
    }

    private void assertGap2(List<Campsite> campResults) {
        Assert.assertNotNull(campResults);
        for (Campsite c : campResults) {
            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() == 3 || c.getId() == 6 || c.getId() == 8);

            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() !=1 && c.getId() !=2 && c.getId() != 4 && c.getId() != 5 && c.getId() != 7 && c.getId() != 9);
        }
    }

    private void assertGap3(List<Campsite> campResults) {
        Assert.assertNotNull(campResults);
        campResults = rezSvc.getAvailableCampsites(gapRules.get(1), startDate, endDate);
        Assert.assertNotNull(campResults);
        for (Campsite c : campResults) {
            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() == 6 || c.getId() == 8);

            Assert.assertTrue("Invalid camp added to available camps.",
                    c.getId() != 1 && c.getId() != 2 && c.getId() != 3 && c.getId() != 4 && c.getId() != 5 && c.getId() != 7 && c.getId() != 9);
        }
    }
}
