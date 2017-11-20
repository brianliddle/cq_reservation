package com.campsite.business;
import static com.campsite.business.ReservationUtil.hasConflict;

import com.campsite.model.Reservation;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ReservationUtilTest {

    @Test
    public void hasConflicts() {
        LocalDate start = LocalDate.of(2016, 6, 15);
        LocalDate end = LocalDate.of(2016, 6, 20);
        Reservation r = new Reservation(1,1,start,end);
        Assert.assertTrue(hasConflict(start, end, r));

        r = new Reservation(1,1,LocalDate.of(2016,6,16), LocalDate.of(2016,6,19));
        Assert.assertTrue(hasConflict(start, end, r));

        r = new Reservation(1,1,LocalDate.of(2016,6,16), LocalDate.of(2016,6,21));
        Assert.assertTrue(hasConflict(start, end, r));

        r = new Reservation(1,1,LocalDate.of(2016,6,14), LocalDate.of(2016,6,19));
        Assert.assertTrue(hasConflict(start, end, r));

        r = new Reservation(1,1,LocalDate.of(2016,6,14), LocalDate.of(2016,6,21));
        Assert.assertTrue(hasConflict(start, end, r));
    }

    @Test
    public void hasNoConflicts() {
        LocalDate start = LocalDate.of(2016, 6, 15);
        LocalDate end = LocalDate.of(2016, 6, 20);
        Reservation r = new Reservation(1,1,LocalDate.of(2016,6,12), LocalDate.of(2016,6,14));
        Assert.assertFalse(hasConflict(start, end, r));

        r = new Reservation(1,1,LocalDate.of(2016,6,21), LocalDate.of(2016,6,30));
        Assert.assertFalse(hasConflict(start, end, r));
    }


}
