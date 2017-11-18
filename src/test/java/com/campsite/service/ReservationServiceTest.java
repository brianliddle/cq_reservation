package com.campsite.service;

import com.campsite.ReservationBaseTest;
import com.campsite.model.Campsite;
import com.campsite.model.Gaprule;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReservationServiceTest extends ReservationBaseTest {

    @Test
    public void getAvailableCampsites() {

        List<Campsite> campResults = rezSvc.getAvailableCampsites(gapRules.get(0), startDate, endDate);
        Assert.assertNotNull(campResults);
        Assert.assertTrue(campResults.size() > 0);
        //TODO: real test

        campResults = rezSvc.getAvailableCampsites(gapRules.get(1), startDate, endDate);
        Assert.assertNotNull(campResults);
        Assert.assertTrue(campResults.size() > 0);
        //TODO: real test
    }
}
