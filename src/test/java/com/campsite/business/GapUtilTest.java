package com.campsite.business;

import static com.campsite.business.GapUtil.gapBetween;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

/** The following tests the GapUtil, as well as be a point of
 * conversation to discuss discrepancies on what I believe is the accepted
 * test criteria, vs the expected results which have some conflicts.
 *
 * Please note, that for all test cases we're using a
 * start date of 2016-06-07 and an end date of 2016-06-10
 **/

public class GapUtilTest {
    @Test
    public void gapTest() {
        GapUtil util = new GapUtil();

        // Gap of 2, rejected by gaprules of 2 and 3.
        // {"campsiteId": 1, "startDate": "2016-06-01", "endDate": "2016-06-04"},
        // {"campsiteId": 1, "startDate": "2016-06-11", "endDate": "2016-06-13"},
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,4), LocalDate.of(2016,6,7)) == 2);
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,10), LocalDate.of(2016,6,11)) == 0);

        //conflict - N/A
        // {"campsiteId": 2, "startDate": "2016-06-08", "endDate": "2016-06-09"}, res conflict

        //TODO: Discuss why this is accepted for gaprule = 3 scenario.
        // Gap of 3, accepted for gaprule of 2, rejected for gaprule of 3.
        // {"campsiteId": 3, "startDate": "2016-06-04", "endDate": "2016-06-06"},
        // {"campsiteId": 3, "startDate": "2016-06-14", "endDate": "2016-06-16"},
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,6), LocalDate.of(2016,6,7)) == 0);
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,10), LocalDate.of(2016,6,14)) == 3);

        // Gap of 1 and 2, rejected for gaprules of 2 and 3.
        // {"campsiteId": 4, "startDate": "2016-06-03", "endDate": "2016-06-05"}, gap of 1
        // {"campsiteId": 4, "startDate": "2016-06-13", "endDate": "2016-06-14"}, gap of 2
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,5), LocalDate.of(2016,6,7)) == 1);
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,10), LocalDate.of(2016,6,13)) == 2);

        //TODO: Discuss why this is accepted in acceptance criteria, I don't believe it should be.
        // Gap of 1, rejected for gaprules of 2 and 3.
        // {"campsiteId": 5, "startDate": "2016-06-03", "endDate": "2016-06-06"}, gap of 0
        // {"campsiteId": 5, "startDate": "2016-06-12", "endDate": "2016-06-14"}, ??? gap of 1... shouldn't be allowed...
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,6), LocalDate.of(2016,6,7)) == 0);
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,10), LocalDate.of(2016,6,12)) == 1);

        // Gap of 0, accepted.
        // {"campsiteId": 6, "startDate": "2016-06-04", "endDate": "2016-06-06"}, gap of 0
        // {"campsiteId": 6, "startDate": "2016-06-11", "endDate": "2016-06-12"}, gap of 0
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,6), LocalDate.of(2016,6,7)) == 0);
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,10), LocalDate.of(2016,6,11)) == 0);

        // Gap of 2 and a conflict, rejected.
        // {"campsiteId": 7, "startDate": "2016-06-03", "endDate": "2016-06-04"}, gap of 2
        // {"campsiteId": 7, "startDate": "2016-06-07", "endDate": "2016-06-09"}, conflict
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,4), LocalDate.of(2016,6,7)) == 2);

        // Gap of 0, accepted.
        // {"campsiteId": 8, "startDate": "2016-06-01", "endDate": "2016-06-02"}, ~
        // {"campsiteId": 8, "startDate": "2016-06-05", "endDate": "2016-06-06"}, gap of 0

        //TODO: Discuss why this is accepted in acceptance criteria, I don't believe it should be.
        // Gap of 1, rejected by gaprules of 2 and 3.
        // {"campsiteId": 9, "startDate": "2016-06-03", "endDate": "2016-06-05"}, ???? gap of 1 - why is this allowed ???
        // {"campsiteId": 9, "startDate": "2016-06-12", "endDate": "2016-06-16"}, ???? gap of 1 - why is this allowed ???
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,5), LocalDate.of(2016,6,7)) == 1);
        Assert.assertTrue(gapBetween(LocalDate.of(2016,6,10), LocalDate.of(2016,6,12)) == 1);

    }
}
